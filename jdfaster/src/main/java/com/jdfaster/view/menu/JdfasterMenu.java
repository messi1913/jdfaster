package com.jdfaster.view.menu;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class JdfasterMenu extends CssLayout{
	
	private CssLayout menuPart;
	
	public JdfasterMenu(final JdfasterNavigator navigator) {
		super.setPrimaryStyleName(ValoTheme.MENU_ROOT);
		
		// menu part layout
		menuPart = new CssLayout();
		menuPart.addStyleName(ValoTheme.MENU_PART);
		
		
		final Label title = new Label("<h3><strong>Jdfaster</strong> PerformanceTest</h3>", ContentMode.HTML);
		title.setSizeUndefined();
		
		// Title Scale Layout
		final HorizontalLayout top = new HorizontalLayout();
		top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		top.addStyleName(ValoTheme.MENU_TITLE);
		top.setSpacing(true);
		top.addComponent(title);
		
		menuPart.addComponent(top);
		// 메뉴 파트 레이아웃을 RootLayout 에 추가 
		super.addComponent(menuPart);
		
	}

}
