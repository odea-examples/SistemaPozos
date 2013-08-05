package com.odea;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;
	
	public BasePage(){
		super();

		WebMarkupContainer listadoPozosPage = this.armarWmc("ListadoPozosPage");
		WebMarkupContainer pozosPage = this.armarWmc("PozosPage");
		WebMarkupContainer dashboardPage = this.armarWmc("DashboardPage");

		this.add(listadoPozosPage);
		this.add(pozosPage);
		this.add(dashboardPage);
	}
	
	private WebMarkupContainer armarWmc(String idWmc) {
		
		String page = this.getRequest().getUrl().toString();
		
		WebMarkupContainer wmc = new WebMarkupContainer(idWmc);
		
		if((page.equals(idWmc))){
			wmc.add(new AttributeModifier("class","current"));
		}
		
		return wmc;
	}
	
			
}


