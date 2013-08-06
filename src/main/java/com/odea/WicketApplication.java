package com.odea;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;


public class WicketApplication extends WebApplication
{    	
	
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return DashboardPage.class;
	}

	
	
	@Override
	public void init()
	{
		super.init();
		
		this.mountPage("PozosPage", PozosPage.class);
		this.mountPage("ListadoPozosPage", ListadoPozosPage.class);
		
		this.mountPage("AltaPozoPage", AltaPozoPage.class);
		this.mountPage("ModificarPozoPage", ModificarPozoPage.class);
		
		this.mountPage("DashboardPage", DashboardPage.class);
		
        this.getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        
	}
}
