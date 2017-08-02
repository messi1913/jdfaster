package com.jdfaster.view.menu;

import com.jdfaster.view.DashBoardView;
import com.jdfaster.view.SessionView;
import com.vaadin.icons.VaadinIcons;

public final class LoadingDataGenerator {
	static {
		createNavis();
	}
	
	private static void createNavis() {
		JdfasterNavigator.naviMaps.put("", new Navi(DashBoardView.VIEW_NAME, "DashBoard", DashBoardView.class, VaadinIcons.HOME));
		JdfasterNavigator.naviMaps.put("session", new Navi(SessionView.VIEW_NAME, "Session", SessionView.class, VaadinIcons.CUBE));
	}
}
