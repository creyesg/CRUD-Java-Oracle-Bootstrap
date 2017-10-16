package com.envios.controller;

import com.envios.db12017.Tracking;
import com.envios.db12017.PaisEmpresaTracking;
import com.envios.db12017.EstatusTracking;
import java.util.Collection;
import com.envios.facade.TrackingFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "trackingController")
@ViewScoped
public class TrackingController extends AbstractController<Tracking> {

    @Inject
    private EnviosController enviosController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isPaisEmpresaTrackingCollectionEmpty;
    private boolean isEstatusTrackingCollectionEmpty;

    public TrackingController() {
        // Inform the Abstract parent controller of the concrete Tracking Entity
        super(Tracking.class);
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
        this.setIsPaisEmpresaTrackingCollectionEmpty();
        this.setIsEstatusTrackingCollectionEmpty();
    }

    public boolean getIsPaisEmpresaTrackingCollectionEmpty() {
        return this.isPaisEmpresaTrackingCollectionEmpty;
    }

    private void setIsPaisEmpresaTrackingCollectionEmpty() {
        Tracking selected = this.getSelected();
        if (selected != null) {
            TrackingFacade ejbFacade = (TrackingFacade) this.getFacade();
            this.isPaisEmpresaTrackingCollectionEmpty = ejbFacade.isPaisEmpresaTrackingCollectionEmpty(selected);
        } else {
            this.isPaisEmpresaTrackingCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of PaisEmpresaTracking
     * entities that are retrieved from Tracking and returns the navigation
     * outcome.
     *
     * @return navigation outcome for PaisEmpresaTracking page
     */
    public String navigatePaisEmpresaTrackingCollection() {
        Tracking selected = this.getSelected();
        if (selected != null) {
            TrackingFacade ejbFacade = (TrackingFacade) this.getFacade();
            Collection<PaisEmpresaTracking> selectedPaisEmpresaTrackingCollection = ejbFacade.findPaisEmpresaTrackingCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PaisEmpresaTracking_items", selectedPaisEmpresaTrackingCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/paisEmpresaTracking/index";
    }

    public boolean getIsEstatusTrackingCollectionEmpty() {
        return this.isEstatusTrackingCollectionEmpty;
    }

    private void setIsEstatusTrackingCollectionEmpty() {
        Tracking selected = this.getSelected();
        if (selected != null) {
            TrackingFacade ejbFacade = (TrackingFacade) this.getFacade();
            this.isEstatusTrackingCollectionEmpty = ejbFacade.isEstatusTrackingCollectionEmpty(selected);
        } else {
            this.isEstatusTrackingCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EstatusTracking entities
     * that are retrieved from Tracking and returns the navigation outcome.
     *
     * @return navigation outcome for EstatusTracking page
     */
    public String navigateEstatusTrackingCollection() {
        Tracking selected = this.getSelected();
        if (selected != null) {
            TrackingFacade ejbFacade = (TrackingFacade) this.getFacade();
            Collection<EstatusTracking> selectedEstatusTrackingCollection = ejbFacade.findEstatusTrackingCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EstatusTracking_items", selectedEstatusTrackingCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/estatusTracking/index";
    }

    /**
     * Sets the "selected" attribute of the Envios controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEnvios(ActionEvent event) {
        Tracking selected = this.getSelected();
        if (selected != null && enviosController.getSelected() == null) {
            enviosController.setSelected(selected.getEnvios());
        }
    }

}
