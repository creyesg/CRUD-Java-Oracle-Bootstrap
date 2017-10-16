package com.envios.controller;

import com.envios.db12017.Paquete;
import com.envios.db12017.Vehiculo;
import com.envios.db12017.EstatusPaquete;
import java.util.Collection;
import com.envios.facade.PaqueteFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "paqueteController")
@ViewScoped
public class PaqueteController extends AbstractController<Paquete> {

    @Inject
    private EnviosController enviosController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isVehiculoCollectionEmpty;
    private boolean isEstatusPaqueteCollectionEmpty;

    public PaqueteController() {
        // Inform the Abstract parent controller of the concrete Paquete Entity
        super(Paquete.class);
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
        this.setIsVehiculoCollectionEmpty();
        this.setIsEstatusPaqueteCollectionEmpty();
    }

    public boolean getIsVehiculoCollectionEmpty() {
        return this.isVehiculoCollectionEmpty;
    }

    private void setIsVehiculoCollectionEmpty() {
        Paquete selected = this.getSelected();
        if (selected != null) {
            PaqueteFacade ejbFacade = (PaqueteFacade) this.getFacade();
            this.isVehiculoCollectionEmpty = ejbFacade.isVehiculoCollectionEmpty(selected);
        } else {
            this.isVehiculoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Vehiculo entities that
     * are retrieved from Paquete and returns the navigation outcome.
     *
     * @return navigation outcome for Vehiculo page
     */
    public String navigateVehiculoCollection() {
        Paquete selected = this.getSelected();
        if (selected != null) {
            PaqueteFacade ejbFacade = (PaqueteFacade) this.getFacade();
            Collection<Vehiculo> selectedVehiculoCollection = ejbFacade.findVehiculoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Vehiculo_items", selectedVehiculoCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/vehiculo/index";
    }

    public boolean getIsEstatusPaqueteCollectionEmpty() {
        return this.isEstatusPaqueteCollectionEmpty;
    }

    private void setIsEstatusPaqueteCollectionEmpty() {
        Paquete selected = this.getSelected();
        if (selected != null) {
            PaqueteFacade ejbFacade = (PaqueteFacade) this.getFacade();
            this.isEstatusPaqueteCollectionEmpty = ejbFacade.isEstatusPaqueteCollectionEmpty(selected);
        } else {
            this.isEstatusPaqueteCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EstatusPaquete entities
     * that are retrieved from Paquete and returns the navigation outcome.
     *
     * @return navigation outcome for EstatusPaquete page
     */
    public String navigateEstatusPaqueteCollection() {
        Paquete selected = this.getSelected();
        if (selected != null) {
            PaqueteFacade ejbFacade = (PaqueteFacade) this.getFacade();
            Collection<EstatusPaquete> selectedEstatusPaqueteCollection = ejbFacade.findEstatusPaqueteCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EstatusPaquete_items", selectedEstatusPaqueteCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/estatusPaquete/index";
    }

    /**
     * Sets the "selected" attribute of the Envios controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEnvios(ActionEvent event) {
        Paquete selected = this.getSelected();
        if (selected != null && enviosController.getSelected() == null) {
            enviosController.setSelected(selected.getEnvios());
        }
    }

}
