package com.jdfaster.view.menu;

import java.util.Iterator;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class JdfasterMenu extends CssLayout{
	
	private CssLayout menuPart;
	private CssLayout menuItems;
	
	public JdfasterMenu(final JdfasterNavigator navigator) {
		super.setPrimaryStyleName(ValoTheme.MENU_ROOT);
		
		// menu part layout
		menuPart = new CssLayout();
		menuPart.addStyleName(ValoTheme.MENU_PART);
		
		
		final Label title = new Label("<h3 style=\"text-align:center\"><strong>Jdfaster</strong> <br> PerformanceTest</h3>", ContentMode.HTML);
		title.setSizeUndefined();
		
		// Title Scale Layout
		final HorizontalLayout top = new HorizontalLayout();
		top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		top.addStyleName(ValoTheme.MENU_TITLE);
		top.setSpacing(true);
		top.addComponent(title);
		
		menuPart.addComponent(top);
		
		// 사용자 메뉴 생성 ( 화살표 밑으로 ,,, 나중에 구현 ) 
		final MenuBar userMenu = new MenuBar();
		userMenu.addStyleName("user-menu");
		menuPart.addComponent(userMenu);
		
		// 메뉴 아이템 리스트 레이아웃
		menuItems = new CssLayout();
		menuItems.setPrimaryStyleName("valo-menu-items");
		
		for(Navi item  : JdfasterNavigator.naviMaps.values()) {
			
			final Button naviBtn = new Button(item.getViewName(), new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					navigator.navigateTo(item.getFragement());
				}
			});
			//버튼에 Fragment 값 넣기 
			naviBtn.setData(item.getFragement());
			naviBtn.setPrimaryStyleName(ValoTheme.MENU_ITEM);
			naviBtn.setIcon(item.getIcon());
			menuItems.addComponent(naviBtn);
			
		}
		menuPart.addComponent(menuItems);
		
		
		
		
		
		// 메뉴 파트 레이아웃을 RootLayout 에 추가 
		super.addComponent(menuPart);
		
	}
	
	public void setSelectedItem(String viewName) {
		if(menuItems.getComponentCount() <= 0) {
			return;
		}
		for(final Iterator<Component> it = menuItems.iterator(); it.hasNext();) {
			final Component item = it.next();
			if(item instanceof Button) {
				final Button naviBtn = (Button) item;
				naviBtn.removeStyleName("selected");
				String fragment = (String)naviBtn.getData();
				if(fragment.equals(viewName)) {
					// parameter 로 넘어온 메뉴 아이템 버튼만 selected 상태 처리 
					item.setStyleName("selected");
				}
			}
			
		}
		
	}

}
