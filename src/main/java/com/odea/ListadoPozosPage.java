package com.odea;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.odea.components.confirmPanel.ConfirmationLink;
import com.odea.dao.DAOService;
import com.odea.domain.Pozo;
import com.odea.domain.Yacimiento;

public class ListadoPozosPage extends BasePage {
	
	private static final long serialVersionUID = 1L;

	@SpringBean
	private DAOService daoService;
	
	private WebMarkupContainer listViewContainer;
	private IModel<List<Pozo>> lstPozosModel;
	
	public ListadoPozosPage() {
		
		this.lstPozosModel = new LoadableDetachableModel<List<Pozo>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Pozo> load() {
				return ListadoPozosPage.this.daoService.getPozos();
			}
		};
		
		
		BookmarkablePageLink<AltaPozoPage> linkAltaPozo = new BookmarkablePageLink<AltaPozoPage>("linkAltaPozo", AltaPozoPage.class);
		this.add(linkAltaPozo);
		

		PageableListView<Pozo> pozosListView = new PageableListView<Pozo>("pozosListView", this.lstPozosModel, 20) {

			private static final long serialVersionUID = 1L;

			@Override
            protected void populateItem(ListItem<Pozo> item) {
            	final Pozo pozo = item.getModel().getObject();   
            	
            	item.add(new Label("cidPozo", new Model<String>(pozo.getCID())));
            	item.add(new Label("nombrePozo", new Model<String>(pozo.getNombre())));
            	item.add(new Label("yacimientoPozo", new Model<Yacimiento>(pozo.getYacimiento())));
            	item.add(new Label("coordenadaXPozo", new Model<Double>(pozo.getCoordenadaX())));
            	item.add(new Label("coordenadaYPozo", new Model<Double>(pozo.getCoordenadaY())));
            	item.add(new Label("coordenadaZPozo", new Model<Double>(pozo.getCoordenadaZ())));
            	item.add(new Label("RKBPozo", new Model<Double>(pozo.getRKB())));
            	item.add(new Label("MRPozo", new Model<Double>(pozo.getMR())));
            	item.add(new Label("nivelReferenciaPozo", new Model<Integer>(pozo.getNivelReferencia())));
            	item.add(new Label("linSismPozo", new Model<String>(pozo.getLinSism())));
            	item.add(new Label("ptoExpPozo", new Model<String>(pozo.getPtoExp())));
            	item.add(new Label("categoriaPozo", new Model<String>(pozo.getCategoria())));
            	item.add(new Label("estadoPozo", new Model<String>(pozo.getEstado())));
            	item.add(new Label("metExtraccionPozo", new Model<String>(pozo.getMetExtraccion())));
            	item.add(new Label("observacionesPozo", new Model<String>(pozo.getObservaciones())));
            	
            	
            	PageParameters parameters = new PageParameters().add("pozoID", pozo.getID()).add("pozoCID", pozo.getCID()).add("pozoNombre", pozo.getNombre()).add("pozoYacimientoID", pozo.getYacimiento().getID()).add("pozoYacimientoNombre", pozo.getYacimiento().getNombre()).add("pozoCoordX", pozo.getCoordenadaX()).add("pozoCoordY", pozo.getCoordenadaY()).add("pozoCoordZ", pozo.getCoordenadaZ()).add("pozoRKB", pozo.getRKB()).add("pozoMR", pozo.getMR()).add("pozoNivelReferencia", pozo.getNivelReferencia()).add("pozoLinSism", pozo.getLinSism()).add("pozoPtoExp", pozo.getPtoExp()).add("pozoCategoria", pozo.getCategoria()).add("pozoEstado", pozo.getEstado()).add("pozoMetExtraccion", pozo.getMetExtraccion()).add("pozoObservaciones", pozo.getObservaciones()).add("pozoPosMapaX", pozo.getPosicionMapaX()).add("pozoPosMapaY", pozo.getPosicionMapaY());
            	
            	BookmarkablePageLink<ModificarPozoPage> botonModificarPozo = new BookmarkablePageLink<ModificarPozoPage>("modifyLink", ModificarPozoPage.class, parameters);
            	
            	item.add(botonModificarPozo);
            	
            	
            	ConfirmationLink<Pozo> botonBajaPozo = new ConfirmationLink<Pozo>("deleteLink","\\u00BFEst\\xE1 seguro de que desea borrar este pozo?", new Model<Pozo>(pozo)) {

					private static final long serialVersionUID = 1L;

					@Override
                    public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                        ListadoPozosPage.this.daoService.bajaPozo(this.getModelObject());
                        ajaxRequestTarget.add(ListadoPozosPage.this.listViewContainer);
                    }
                    
                };
            
                item.add(botonBajaPozo);
            }
        };
        
        
		this.listViewContainer = new WebMarkupContainer("listViewContainer");
		this.listViewContainer.setOutputMarkupId(true);
        this.listViewContainer.add(pozosListView);
        this.listViewContainer.add(new AjaxPagingNavigator("navigator", pozosListView));
        this.add(this.listViewContainer);
        
        		
    }
}
