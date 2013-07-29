package com.odea;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.odea.dao.DAOService;
import com.odea.domain.Pozo;
import com.odea.domain.Yacimiento;

public abstract class EditarPozoPage extends BasePage {
	
	private static final long serialVersionUID = 1L;

	@SpringBean
	protected DAOService daoService;
	
	protected abstract void insertarPozo(Pozo pozo);
	
	
	
	protected void preparePage(Pozo pozo) {
		
        BookmarkablePageLink<ListadoPozosPage> linkVolver = new BookmarkablePageLink<ListadoPozosPage>("linkVolver", ListadoPozosPage.class);
        this.add(linkVolver);
        
		LoadableDetachableModel<List<Yacimiento>> lstYacimientosModel = new LoadableDetachableModel<List<Yacimiento>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Yacimiento> load() {
				return daoService.getYacimientos();
			}
		};
        
        
		Form<Pozo> form = new Form<Pozo>("form", new CompoundPropertyModel<Pozo>(pozo));
		this.add(form);
		
		/*Panel de errores*/
		
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackPanel");
		feedbackPanel.setOutputMarkupId(true);
		form.add(feedbackPanel);
		
	
		
		/*Campos de Pozo*/
		
		RequiredTextField<String> CID = new RequiredTextField<String>("CID");
		CID.setLabel(Model.of("CID"));
		form.add(CID);
				
		RequiredTextField<String> nombre = new RequiredTextField<String>("nombre");
		nombre.setLabel(Model.of("Nombre"));
		form.add(nombre);
		
		DropDownChoice<Yacimiento> yacimiento = new DropDownChoice<Yacimiento>("yacimiento", lstYacimientosModel);
    	yacimiento.setLabel(Model.of("Yacimiento"));
    	yacimiento.setRequired(true);
    	form.add(yacimiento);
		
		RequiredTextField<Double> coordenadaX = new RequiredTextField<Double>("coordenadaX");
		coordenadaX.setLabel(Model.of("Coordenada X"));
		form.add(coordenadaX);
		
		RequiredTextField<Double> coordenadaY = new RequiredTextField<Double>("coordenadaY");
		coordenadaY.setLabel(Model.of("Coordenada Y"));
		form.add(coordenadaY);

		RequiredTextField<Double> coordenadaZ = new RequiredTextField<Double>("coordenadaZ");
		coordenadaZ.setLabel(Model.of("Coordenada Z"));
		form.add(coordenadaZ);
		
		RequiredTextField<Double> rkb = new RequiredTextField<Double>("RKB");
		rkb.setLabel(Model.of("RKB"));
		form.add(rkb);
		
		RequiredTextField<String> mr = new RequiredTextField<String>("MR");
		mr.setLabel(Model.of("MR"));
		form.add(mr);
		
		RequiredTextField<Integer> nivelReferencia = new RequiredTextField<Integer>("nivelReferencia");
		nivelReferencia.setLabel(Model.of("Nivel de Referencia"));
		form.add(nivelReferencia);
		
		RequiredTextField<String> linSism = new RequiredTextField<String>("linSism");
		linSism.setLabel(Model.of("Lin. Sism."));
		form.add(linSism);
		
		RequiredTextField<String> ptoExp = new RequiredTextField<String>("ptoExp");
		ptoExp.setLabel(Model.of("Pto. Exp."));
		form.add(ptoExp);
		
		RequiredTextField<String> categoria = new RequiredTextField<String>("categoria");
		categoria.setLabel(Model.of("Categoría"));
		form.add(categoria);
		
		RequiredTextField<String> estado = new RequiredTextField<String>("estado");
		estado.setLabel(Model.of("Estado"));
		form.add(estado);
		
		RequiredTextField<String> metExtraccion = new RequiredTextField<String>("metExtraccion");
		metExtraccion.setLabel(Model.of("Met. Extracción"));
		form.add(metExtraccion);
		
		RequiredTextField<String> observaciones = new RequiredTextField<String>("observaciones");
		observaciones.setLabel(Model.of("Observaciones"));
		form.add(observaciones);
		
		RequiredTextField<Double> posMapaX = new RequiredTextField<Double>("posicionMapaX");
		posMapaX.setLabel(Model.of("Posición mapa - X:"));
		form.add(posMapaX);
		
		RequiredTextField<Double> posMapaY = new RequiredTextField<Double>("posicionMapaY");
		posMapaY.setLabel(Model.of("Posición mapa - Y:"));
		form.add(posMapaY);
		
		
		
		
		AjaxButton submit = new AjaxButton("submitButton") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				try {
					Pozo pozo = (Pozo)form.getModelObject();
					insertarPozo(pozo);					
					this.setResponsePage(ListadoPozosPage.class);
					
				} catch (Exception ex) {
					error(ex.getMessage());
					target.add(feedbackPanel);
				} 
				
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedbackPanel);
			}
			
		};
		
		form.add(submit);

	}

	
}
