package com.jdfaster.view;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class SessionView extends VerticalLayout implements View {

	public static final String VIEW_NAME = "Session";
	
	public SessionView() {
		super.addComponent(createTopBar());
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public HorizontalLayout createTopBar() {
		Label title = new Label("Session");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H1);
		title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(title);
		layout.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
		return layout;
	}

}
