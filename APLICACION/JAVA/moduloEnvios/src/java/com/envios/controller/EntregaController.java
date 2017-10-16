package com.envios.controller;

import com.envios.db12017.Entrega;
import com.envios.db12017.EstatusEntrega;
import com.envios.db12017.DireccionesEntregas;
import java.util.Collection;
import com.envios.facade.EntregaFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "entregaController")
@ViewScoped
public class EntregaController extends AbstractController<Entrega> {

    @Inject
    private EnviosController enviosController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEstatusEntregaCollectionEmpty;
    private boolean isDireccionesEntregasCollectionEmpty;

    public EntregaController() {
        // Inform the Abstract parent controller of the concrete Entrega Entity
        super(Entrega.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        enviosController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEstatusEntregaCollectionEmpty();
        this.setIsDireccionesEntregasCollectionEmpty();
    }

    public boolean getIsEstatusEntregaCollectionEmpty() {
        return this.isEstatusEntregaCollectionEmpty;
    }

    private void setIsEstatusEntregaCollectionEmpty() {
        Entrega selected = this.getSelected();
        if (selected != null) {
            EntregaFacade ejbFacade = (EntregaFacade) this.getFacade();
            this.isEstatusEntregaCollectionEmpty = ejbFacade.isEstatusEntregaCollectionEmpty(selected);
        } else {
            this.isEstatusEntregaCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EstatusEntrega entities
     * that are retrieved from Entrega and returns the navigation outcome.
     *
     * @return navigation outcome for EstatusEntrega page
     */
    public String navigateEstatusEntregaCollection() {
        Entrega selected = this.getSelected();
        if (selected != null) {
            EntregaFacade ejbFacade = (EntregaFacade) this.getFacade();
            Collection<EstatusEntrega> selectedEstatusEntregaCollection = ejbFacade.findEstatusEntregaCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EstatusEntrega_items", selectedEstatusEntregaCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/estatusEntrega/index";
    }

    public boolean getIsDireccionesEntregasCollectionEmpty() {
        return this.isDireccionesEntregasCollectionEmpty;
    }

    private void setIsDireccionesEntregasCollectionEmpty() {
        Entrega selected = this.getSelected();
        if (selected != null) {
            EntregaFacade ejbFacade = (EntregaFacade) this.getFacade();
            this.isDireccionesEntregasCollectionEmpty = ejbFacade.isDireccionesEntregasCollectionEmpty(selected);
        } else {
            this.isDireccionesEntregasCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DireccionesEntregas
     * entities that are retrieved from Entrega and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DireccionesEntregas page
     */
    public String navigateDireccionesEntregasCollection() {
        Entrega selected = this.getSelected();
        if (selected != null) {
            EntregaFacade ejbFacade = (EntregaFacade) this.getFacade();
            Collection<DireccionesEntregas> selectedDireccionesEntregasCollection = ejbFacade.findDireccionesEntregasCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DireccionesEntregas_items", selectedDireccionesEntregasCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/direccionesEntregas/index";
    }

    /**
     * Sets the "selected" attribute of the Envios controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEnvios(ActionEvent event) {
        Entrega selected = this.getSelected();
        if (selected != null && enviosController.getSelected() == null) {
            enviosController.setSelected(selected.getEnvios());
        }
    }

}
