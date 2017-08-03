package com.jdfaster.view;

import com.jdfaster.view.menu.JdfasterMenu;
import com.jdfaster.view.menu.JdfasterNavigator;
import com.jdfaster.view.vaadin.Application;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class MainScreen extends HorizontalLayout {

	public MainScreen(Application application) {
		CssLayout viewArea = new CssLayout();
		viewArea.setSizeFull();
		
		// create Navigator
		@SuppressWarnings("static-access")
		final JdfasterNavigator navigator = new JdfasterNavigator(application.getCurrent(), viewArea);
		
		// Menu Layout
		final JdfasterMenu menuArea = new JdfasterMenu(navigator);
		
		
		// 뷰 변경시 발생되는 이벤트 리스너 
		ViewChangeListener viewChangeListener = new ViewChangeListener() {
			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				return true;
			}
			@Override
			public void afterViewChange(ViewChangeEvent event) {
				menuArea.setSelectedItem(event.getViewName());
			}
		};
		
		// navigator view 변경 이벤트 발생
		navigator.addViewChangeListener(viewChangeListener);
		
		// MainScreen 에 메뉴영역 + 동적 변경 뷰 영역 순서대로 추가
		super.addComponents(menuArea, viewArea);
		// 동적 변경 뷰 영역이 빈 영역 모두 사용하기 
		super.setExpandRatio(viewArea, 1);
		super.setSizeFull();
	}

}
