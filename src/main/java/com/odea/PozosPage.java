package com.odea;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.odea.MapaLeaflet.MapaLeaflet;
import com.odea.dao.DAOService;
import com.odea.domain.Pozo;
import com.odea.domain.Yacimiento;

public class PozosPage extends BasePage {
	
	private static final long serialVersionUID = 1L;

	@SpringBean
	private DAOService daoService;
	
	private WebMarkupContainer listViewContainer;
	private IModel<List<Pozo>> lstPozosModel;
	private MapaLeaflet mapa;
	
	private Pozo pozoBusqueda;
	
	public PozosPage() {
		
		this.pozoBusqueda = new Pozo();
		
		this.lstPozosModel = new LoadableDetachableModel<List<Pozo>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Pozo> load() {
				return PozosPage.this.daoService.buscarPozos(PozosPage.this.pozoBusqueda);
			}
		};
		

		PageableListView<Pozo> pozosListView = new PageableListView<Pozo>("pozosListView", this.lstPozosModel, 20) {

			private static final long serialVersionUID = 1L;

			@Override
            protected void populateItem(ListItem<Pozo> item) {
            	final Pozo pozo = item.getModel().getObject();   
            	
            	item.add(new Label("cidPozo", new Model<String>(pozo.getCID())));
            	item.add(new Label("nombrePozo", new Model<String>(pozo.getNombre())));
            	item.add(new Label("yacimientoPozo", new Model<Yacimiento>(pozo.getYacimiento())));
            	item.add(new Label("coordenadaXPozo", new Model<Double>(pozo.getCoordenadaX())));
            	item.add(new Label("coordenadaYPozo", new Model<Double>(pozo.getCoordenadaX())));
            	item.add(new Label("coordenadaZPozo", new Model<Double>(pozo.getCoordenadaX())));
            	item.add(new Label("RKBPozo", new Model<Double>(pozo.getRKB())));
            	item.add(new Label("MRPozo", new Model<Double>(pozo.getMR())));
            	item.add(new Label("nivelReferenciaPozo", new Model<Integer>(pozo.getNivelReferencia())));
            	item.add(new Label("linSismPozo", new Model<String>(pozo.getLinSism())));
            	item.add(new Label("ptoExpPozo", new Model<String>(pozo.getPtoExp())));
            	item.add(new Label("categoriaPozo", new Model<String>(pozo.getCategoria())));
            	item.add(new Label("estadoPozo", new Model<String>(pozo.getEstado())));
            	item.add(new Label("metExtraccionPozo", new Model<String>(pozo.getMetExtraccion())));
            	item.add(new Label("observacionesPozo", new Model<String>(pozo.getObservaciones())));
            }
        };
        
        
		this.listViewContainer = new WebMarkupContainer("listViewContainer");
		this.listViewContainer.setOutputMarkupId(true);
        this.listViewContainer.add(pozosListView);
        this.listViewContainer.add(new AjaxPagingNavigator("navigator", pozosListView));
        this.add(this.listViewContainer);
        
        

        Form<Pozo> form = new Form<Pozo>("form", new CompoundPropertyModel<Pozo>(new Pozo()));
        form.setOutputMarkupId(true);
        this.add(form);
        
        TextField<String> nombre = new TextField<String>("nombre");
        form.add(nombre);
		
        DropDownChoice<Yacimiento> dropDownYacimiento = new DropDownChoice<Yacimiento>("yacimiento", this.daoService.getYacimientos());
        form.add(dropDownYacimiento);
        
        AjaxButton limpiarButton = new AjaxButton("limpiarButton") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				form.setDefaultModelObject(new Pozo());
				PozosPage.this.pozoBusqueda = new Pozo();
				
				target.add(form);
				target.add(PozosPage.this.listViewContainer);
				target.add(PozosPage.this.mapa);
			}
        	
		};
		form.add(limpiarButton);
        
        
        AjaxButton submitButton = new AjaxButton("submitButton") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				Pozo pozo = (Pozo) form.getModelObject();
				PozosPage.this.pozoBusqueda = pozo;
				
				target.add(PozosPage.this.listViewContainer);
				target.add(PozosPage.this.mapa);
			}
        	
		};
		form.add(submitButton);
        
		this.mapa = new MapaLeaflet("mapaLeaflet") {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Pozo> getDTO() {
				return PozosPage.this.daoService.buscarPozos(PozosPage.this.pozoBusqueda);
			}
			
		};
		this.add(this.mapa);
		
    }
}
