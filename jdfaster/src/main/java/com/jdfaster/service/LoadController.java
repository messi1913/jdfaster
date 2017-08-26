package com.jdfaster.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class LoadController {
	private static final Logger logger = LoggerFactory.getLogger(LoadController.class);

	private ScenarioExecutor scenarioExecutor = new DefaultScenarioExecutor();

	public void setScenarioExecutor(ScenarioExecutor scenarioExecutor) {
		this.scenarioExecutor = scenarioExecutor;
	}

	private String status;
	private int concurrentUserSize;
	private List<Target> targets;
	private ThreadPoolTaskExecutor taskExecutor;

	public String getStatus() {
		return status;
	}

	public int getConcurrentUserSize() {
		return concurrentUserSize;
	}

	public int getCurrentUserSize() {
		return taskExecutor == null ? 0 : taskExecutor.getPoolSize();
	}

	public List<Target> getTargets() {
		return targets;
	}

	public LoadController() {
		status = "created";
	}

	private static final Set<String> STARTABLE;
	static {
		STARTABLE = new HashSet<String>();
		STARTABLE.add("created");
		STARTABLE.add("stopped");
	}

	public void start(int concurrentUserSize, List<Target> targets) throws Exception {
		synchronized (this) {
			if (!STARTABLE.contains(status))
				throw new IllegalStateException(
						"Cannot start load, because of current status of LoadController:" + status);

			if (concurrentUserSize < 1)
				new IllegalArgumentException("concurrentUserSize must be higher than 0!");
			if (targets == null || targets.isEmpty())
				new IllegalArgumentException("targets must not be empty!");
			for (Target item : targets) {
				String tn = item.getName();
				if (tn == null || tn.trim().length() == 0)
					throw new IllegalArgumentException("targetName must not be empty!");
				if (item.getLoadFactor() <= 0)
					throw new IllegalArgumentException("loadFactor must be higher than 0!");
			}

			status = "starting";
		}

		try {
			taskExecutor = new ThreadPoolTaskExecutor();
			taskExecutor.setCorePoolSize(1);
			taskExecutor.setMaxPoolSize(concurrentUserSize);
			taskExecutor.setQueueCapacity(0);
			taskExecutor.initialize();

			this.concurrentUserSize = concurrentUserSize;
			this.targets = targets;

			final List<Target> tgs = new ArrayList<Target>();
			for (Target item : targets) {
				int lf = item.getLoadFactor();
				for (int i = 0; i < lf; i++)
					tgs.add(item);
			}

			final List<Long> counter = new ArrayList<Long>();
			counter.add(0L);

			final Random r = new Random();
			final int n = tgs.size() - 1;
			for (int i = 0; i < concurrentUserSize; i++) {
				taskExecutor.execute(new Runnable() {
					@Override
					public void run() {
						while ("started".equals(status) || "starting".equals(status)) {
							try {
								synchronized (counter) {
									Long cnt = counter.get(0);
									counter.set(0, cnt + 1);
								}
								Target target = tgs.get(n == 0 ? 0 : r.nextInt(n));
								scenarioExecutor.execute(target);
							} catch (Exception e) {
								logger.error(e.getMessage(), e);
							}
						}
					}
				});
			}

			synchronized (this) {
				status = "started";
			}
		} catch (Exception e) {
			synchronized (this) {
				status = "failed";
			}
			stop();
		}
	}

	private static final Set<String> UNSTOPPABLE;
	static {
		UNSTOPPABLE = new HashSet<String>();
		UNSTOPPABLE.add("starting");
	}

	public void stop() {
		if (taskExecutor == null)
			return;

		synchronized (this) {
			if (UNSTOPPABLE.contains(status))
				throw new IllegalStateException(
						"Cannot stop load, because of current status of LoadController:" + status);
			status = "stopping";
		}

		try {
			taskExecutor.destroy();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			synchronized (this) {
				status = "stopped";
				taskExecutor = null;
			}
		}
	}
}
