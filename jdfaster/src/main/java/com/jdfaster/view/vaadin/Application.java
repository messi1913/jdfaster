package com.jdfaster.view.vaadin;

import com.jdfaster.view.MainScreen;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@Theme("valo")
@SpringUI(path = "/ui")
@SuppressWarnings("serial")
public class Application extends UI {

	@Override
	protected void init(VaadinRequest request) {
//		setContent(new LoginScreen());
		
		super.setContent(new MainScreen(this));
		// 현재 요청된 주소(Loaction) 값에 맞게 뷰를 동적으로 전환 
		super.getNavigator().navigateTo(super.getNavigator().getState());
		return;
	}

}
