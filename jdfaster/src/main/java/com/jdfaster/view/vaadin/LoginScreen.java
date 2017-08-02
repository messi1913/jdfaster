package com.jdfaster.view.vaadin;

import org.hibernate.validator.internal.util.privilegedactions.GetConstraintValidatorList;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class LoginScreen extends VerticalLayout{
	public LoginScreen() {
		setSizeFull(); // 레이아웃 사이즈를 화면 전체로 설정한다. 
		Component loginForm = buildForm();
		addComponent(loginForm);
		setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
		
	}
	
	private Component buildForm() {
		final VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setSizeUndefined();
		loginPanel.setSpacing(true);
		loginPanel.addComponent(buildLabels());
		loginPanel.addComponent(buildFields());
		return loginPanel;
	}
	
	private Component buildLabels() {
		Label label = new Label("Welcome to vaadin seminar");
		label.addStyleName(ValoTheme.LABEL_H4);
		label.addStyleName(ValoTheme.LABEL_COLORED);
		return label;
	}
	
	private Component buildFields() {
		final TextField email = new TextField("Email");
		email.setIcon(VaadinIcons.USER);
		email.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		
		final PasswordField password = new PasswordField("Password");
		password.setIcon(VaadinIcons.LOCK);
		password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		
		final Button signin = new Button("Sign in");
		signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
		signin.focus();
		signin.setClickShortcut(KeyCode.ENTER);
		signin.addClickListener(event -> {
			//(email.getValue(), password.getValue());
			
		});
		
		HorizontalLayout fields = new HorizontalLayout();
		fields.setSpacing(true);
		fields.addComponents(email, password, signin);
		fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);
		
		
		
		return fields;
	}

}
