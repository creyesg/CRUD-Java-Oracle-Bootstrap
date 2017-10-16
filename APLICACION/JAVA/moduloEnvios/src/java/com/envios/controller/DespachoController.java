package com.envios.controller;

import com.envios.db12017.Despacho;
import com.envios.db12017.Envios;
import com.envios.db12017.EstatusDespacho;
import java.util.Collection;
import com.envios.facade.DespachoFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "despachoController")
@ViewScoped
public class DespachoController extends AbstractController<Despacho> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEnviosCollectionEmpty;
    private boolean isEstatusDespachoCollectionEmpty;

    public DespachoController() {
        // Inform the Abstract parent controller of the concrete Despacho Entity
        super(Despacho.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEnviosCollectionEmpty();
        this.setIsEstatusDespachoCollectionEmpty();
    }

    public boolean getIsEnviosCollectionEmpty() {
        return this.isEnviosCollectionEmpty;
    }

    private void setIsEnviosCollectionEmpty() {
        Despacho selected = this.getSelected();
        if (selected != null) {
            DespachoFacade ejbFacade = (DespachoFacade) this.getFacade();
            this.isEnviosCollectionEmpty = ejbFacade.isEnviosCollectionEmpty(selected);
        } else {
            this.isEnviosCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Envios entities that are
     * retrieved from Despacho and returns the navigation outcome.
     *
     * @return navigation outcome for Envios page
     */
    public String navigateEnviosCollection() {
        Despacho selected = this.getSelected();
        if (selected != null) {
            DespachoFacade ejbFacade = (DespachoFacade) this.getFacade();
            Collection<Envios> selectedEnviosCollection = ejbFacade.findEnviosCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Envios_items", selectedEnviosCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/envios/index";
    }

    public boolean getIsEstatusDespachoCollectionEmpty() {
        return this.isEstatusDespachoCollectionEmpty;
    }

    private void setIsEstatusDespachoCollectionEmpty() {
        Despacho selected = this.getSelected();
        if (selected != null) {
            DespachoFacade ejbFacade = (DespachoFacade) this.getFacade();
            this.isEstatusDespachoCollectionEmpty = ejbFacade.isEstatusDespachoCollectionEmpty(selected);
        } else {
            this.isEstatusDespachoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EstatusDespacho entities
     * that are retrieved from Despacho and returns the navigation outcome.
     *
     * @return navigation outcome for EstatusDespacho page
     */
    public String navigateEstatusDespachoCollection() {
        Despacho selected = this.getSelected();
        if (selected != null) {
            DespachoFacade ejbFacade = (DespachoFacade) this.getFacade();
            Collection<EstatusDespacho> selectedEstatusDespachoCollection = ejbFacade.findEstatusDespachoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EstatusDespacho_items", selectedEstatusDespachoCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/estatusDespacho/index";
    }

}
