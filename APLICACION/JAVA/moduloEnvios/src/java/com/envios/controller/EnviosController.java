package com.envios.controller;

import com.envios.db12017.Envios;
import com.envios.db12017.Entrega;
import com.envios.db12017.Tracking;
import com.envios.db12017.Paquete;
import java.util.Collection;
import com.envios.facade.EnviosFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "enviosController")
@ViewScoped
public class EnviosController extends AbstractController<Envios> {

    @Inject
    private DespachoController despachoCodDespachoController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEntregaCollectionEmpty;
    private boolean isTrackingCollectionEmpty;
    private boolean isPaqueteCollectionEmpty;

    public EnviosController() {
        // Inform the Abstract parent controller of the concrete Envios Entity
        super(Envios.class);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setEnviosPK(new com.envios.db12017.EnviosPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        despachoCodDespachoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEntregaCollectionEmpty();
        this.setIsTrackingCollectionEmpty();
        this.setIsPaqueteCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the Despacho controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDespachoCodDespacho(ActionEvent event) {
        Envios selected = this.getSelected();
        if (selected != null && despachoCodDespachoController.getSelected() == null) {
            despachoCodDespachoController.setSelected(selected.getDespachoCodDespacho());
        }
    }

    public boolean getIsEntregaCollectionEmpty() {
        return this.isEntregaCollectionEmpty;
    }

    private void setIsEntregaCollectionEmpty() {
        Envios selected = this.getSelected();
        if (selected != null) {
            EnviosFacade ejbFacade = (EnviosFacade) this.getFacade();
            this.isEntregaCollectionEmpty = ejbFacade.isEntregaCollectionEmpty(selected);
        } else {
            this.isEntregaCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Entrega entities that are
     * retrieved from Envios and returns the navigation outcome.
     *
     * @return navigation outcome for Entrega page
     */
    public String navigateEntregaCollection() {
        Envios selected = this.getSelected();
        if (selected != null) {
            EnviosFacade ejbFacade = (EnviosFacade) this.getFacade();
            Collection<Entrega> selectedEntregaCollection = ejbFacade.findEntregaCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Entrega_items", selectedEntregaCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/entrega/index";
    }

    public boolean getIsTrackingCollectionEmpty() {
        return this.isTrackingCollectionEmpty;
    }

    private void setIsTrackingCollectionEmpty() {
        Envios selected = this.getSelected();
        if (selected != null) {
            EnviosFacade ejbFacade = (EnviosFacade) this.getFacade();
            this.isTrackingCollectionEmpty = ejbFacade.isTrackingCollectionEmpty(selected);
        } else {
            this.isTrackingCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Tracking entities that
     * are retrieved from Envios and returns the navigation outcome.
     *
     * @return navigation outcome for Tracking page
     */
    public String navigateTrackingCollection() {
        Envios selected = this.getSelected();
        if (selected != null) {
            EnviosFacade ejbFacade = (EnviosFacade) this.getFacade();
            Collection<Tracking> selectedTrackingCollection = ejbFacade.findTrackingCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Tracking_items", selectedTrackingCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/tracking/index";
    }

    public boolean getIsPaqueteCollectionEmpty() {
        return this.isPaqueteCollectionEmpty;
    }

    private void setIsPaqueteCollectionEmpty() {
        Envios selected = this.getSelected();
        if (selected != null) {
            EnviosFacade ejbFacade = (EnviosFacade) this.getFacade();
            this.isPaqueteCollectionEmpty = ejbFacade.isPaqueteCollectionEmpty(selected);
        } else {
            this.isPaqueteCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Paquete entities that are
     * retrieved from Envios and returns the navigation outcome.
     *
     * @return navigation outcome for Paquete page
     */
    public String navigatePaqueteCollection() {
        Envios selected = this.getSelected();
        if (selected != null) {
            EnviosFacade ejbFacade = (EnviosFacade) this.getFacade();
            Collection<Paquete> selectedPaqueteCollection = ejbFacade.findPaqueteCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Paquete_items", selectedPaqueteCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/paquete/index";
    }

}
