package com.odea.MapaLeaflet;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.JavaScriptContentHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.util.template.PackageTextTemplate;

import com.google.gson.Gson;
import com.odea.components.ajax.AbstractInitializableComponentBehavior;
import com.odea.domain.Pozo;

public abstract class MapaLeaflet extends FormComponentPanel<Date> implements IHeaderContributor {
    
	private static final long serialVersionUID = 1L;
	
	private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String ENCODING = Application.get().getMarkupSettings().getDefaultMarkupEncoding();

    private AbstractDefaultAjaxBehavior ajaxBehavior;

    
	public MapaLeaflet(String id) {
        super(id);
        this.setOutputMarkupId(true);
        
		this.add(new AbstractInitializableComponentBehavior(){

			private static final long serialVersionUID = 1L;

			public String getInitJSCall() {
				return "setMap();";
			}
			
		});
		
		
        this.ajaxBehavior = new AbstractDefaultAjaxBehavior() {
            @Override
            protected void respond(AjaxRequestTarget target) {    
                if (getRequest().getRequestParameters().getParameterNames().contains("updateF")) {
                    MapaLeaflet.this.respondLocation();
                }
                
            }
        };
        
		this.add(this.ajaxBehavior);
    }
	
	
    private String toJson(List<Pozo> pozo) {
    	Gson gson = new Gson();
    	return gson.toJson(pozo);
    }
    
    private void respondLocation() {
    	List<Pozo> pozos = this.getDTO();
    	
        String jsonResultList = this.toJson(pozos);
        TextRequestHandler jsonHandler = new TextRequestHandler(JSON_CONTENT_TYPE, ENCODING, jsonResultList);
        getRequestCycle().scheduleRequestHandlerAfterCurrent(jsonHandler);
    }
    
    
    @Override
    protected void onAfterRender() {
        String uniqueName = Long.toString(Calendar.getInstance().getTimeInMillis());
        new JavaScriptContentHeaderItem(this.prepareJSInitCall(), uniqueName + "js", null).render(this.getResponse());
        super.onAfterRender();
    }
    
    
    private String prepareJSInitCall() {
        Map<String, CharSequence> map = new HashMap<String, CharSequence>(4);
        map.put("url", this.ajaxBehavior.getCallbackUrl());
        map.put("mapaLeafletId", this.getMarkupId());
        PackageTextTemplate packageTextTemplate = new PackageTextTemplate(MapaLeaflet.class, "MapaLeaflet.js", "text/javascript");
        return packageTextTemplate.asString(map);
    }
    
    protected abstract List<Pozo> getDTO();
 
}
