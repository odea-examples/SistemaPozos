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
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.odea.MapaLeaflet.MapaLeaflet;
import com.odea.components.confirmPanel.ConfirmationLink;
import com.odea.dao.DAOService;
import com.odea.domain.Pozo;
import com.odea.domain.Yacimiento;

public class DashboardPage extends BasePage {
	
	private static final long serialVersionUID = 1L;

	@SpringBean
	private DAOService daoService;
	
	private MapaLeaflet mapa;
	private Pozo pozoBusqueda;
	private WebMarkupContainer listViewContainer;
	private IModel<List<Pozo>> lstPozosModel;
	
	public DashboardPage() {
		
		this.pozoBusqueda = new Pozo();

		this.lstPozosModel = new LoadableDetachableModel<List<Pozo>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Pozo> load() {
				return DashboardPage.this.daoService.getPozos();
			}
		};

		
		PageableListView<Pozo> pozosListView = new PageableListView<Pozo>("pozosListView", this.lstPozosModel, 4) {

			private static final long serialVersionUID = 1L;

			@Override
            protected void populateItem(ListItem<Pozo> item) {
            	final Pozo pozo = item.getModel().getObject();   
            	
            	item.add(new Label("nombrePozo", new Model<String>(pozo.getNombre())));
          
            }
        };
        
        
		this.listViewContainer = new WebMarkupContainer("listViewContainer");
		this.listViewContainer.setOutputMarkupId(true);
        this.listViewContainer.add(pozosListView);
        this.listViewContainer.add(new AjaxPagingNavigator("navigator", pozosListView));
        this.add(this.listViewContainer);

        
        Form<Pozo> form = new Form<Pozo>("form", new CompoundPropertyModel<Pozo>(this.pozoBusqueda));
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
				DashboardPage.this.pozoBusqueda = new Pozo();
				form.setDefaultModelObject(DashboardPage.this.pozoBusqueda);
				
				target.add(form);
				target.add(DashboardPage.this.mapa);
			}
        	
		};
		form.add(limpiarButton);
        
        
        AjaxButton submitButton = new AjaxButton("submitButton") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				target.add(DashboardPage.this.mapa);
			}
        	
		};
		form.add(submitButton);
        
		this.mapa = new MapaLeaflet("mapaLeaflet") {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Pozo> getDTO() {
				return DashboardPage.this.daoService.buscarPozos(DashboardPage.this.pozoBusqueda);
			}
			
		};
		
		this.add(this.mapa);
		
    }
}
