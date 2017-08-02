package com.jdfaster.view.menu;

import java.util.LinkedHashMap;
import java.util.Map;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class JdfasterNavigator extends Navigator {
	
	public JdfasterNavigator(UI ui, ComponentContainer container) {
		super(ui, container); // Componet Layout
		
		LoadingDataGenerator loadingDataGenerator = new LoadingDataGenerator();
		
		for(Navi item : naviMaps.values()) {
			super.addView(item.getFragement(), item.getViewClass());
//			activeNaviMaps.put(item.getFragement(), item);
		}
	}
	
	public static final Map<String, Navi> naviMaps = new LinkedHashMap<>();
	
	private Map<String, Navi> activeNaviMaps;
	
	public Map<String, Navi> getActiveNaviMaps(){
		return activeNaviMaps;
	}

}
