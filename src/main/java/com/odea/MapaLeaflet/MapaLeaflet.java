package com.odea.MapaLeaflet;

import java.util.Date;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class MapaLeaflet extends FormComponentPanel<Date> implements IHeaderContributor {
    
	private static final long serialVersionUID = 1L;

	public MapaLeaflet(String id) {
        super(id);
        this.setOutputMarkupId(true);
        
    }
	
	@Override
	public void renderHead(IHeaderResponse response)
	{
	    super.renderHead(response);
	    this.renderJS(response, "MapaLeaflet.js");
	}
	
    private void renderJS(IHeaderResponse response, String jsFile) {
        JavaScriptResourceReference js = new JavaScriptResourceReference(MapaLeaflet.class, jsFile);
        response.render(JavaScriptReferenceHeaderItem.forReference(js));
    }
}
