package com.jdfaster.jdfsample.services.test.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.jdfaster.jdfsample.services.order.MesOrder;

public class MesTestUtils {
	private static int lineSize = 200;
	private static List<String> LINES_ALL = new ArrayList<String>(lineSize);
	private static List<String> LINES_RUNNING = new ArrayList<String>(lineSize);

	static {
		for (int i = 1; i <= lineSize; i++) {
			String index;
			{
				if (i < 10)
					index = "00" + i;
				else if (i < 100)
					index = "0" + i;
				else
					index = "" + i;
			}
			LINES_ALL.add("L-" + index);
		}
	}

	public static List<String> getAllLineCodes() {
		return LINES_ALL;
	}

	public static MesOrder getOrder(String lineCode) throws Exception {
		return null;
	}

	public static String getLineCode() {
		synchronized (LINES_ALL) {
			List<String> linesWaiting = new ArrayList<String>(LINES_ALL);
			linesWaiting.removeAll(LINES_RUNNING);
			if (linesWaiting.isEmpty())
				throw new IllegalStateException("Line Master Data is not enough for Testing");
			int index = ThreadLocalRandom.current().nextInt(linesWaiting.size());
			String lineCode = linesWaiting.get(index);
			LINES_RUNNING.add(lineCode);
			return lineCode;
		}
	}

	public static void releaseLineCode(String lineCode) {
		synchronized (LINES_ALL) {
			LINES_RUNNING.remove(lineCode);
		}
	}
}
