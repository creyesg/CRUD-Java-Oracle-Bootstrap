package com.envios.controller;

import com.envios.db12017.Mensajeros;
import com.envios.db12017.EstatusMensajero;
import java.util.Collection;
import com.envios.facade.MensajerosFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "mensajerosController")
@ViewScoped
public class MensajerosController extends AbstractController<Mensajeros> {

    @Inject
    private VehiculoController vehiculoCodVehiculoController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEstatusMensajeroCollectionEmpty;

    public MensajerosController() {
        // Inform the Abstract parent controller of the concrete Mensajeros Entity
        super(Mensajeros.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        vehiculoCodVehiculoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEstatusMensajeroCollectionEmpty();
    }

    public boolean getIsEstatusMensajeroCollectionEmpty() {
        return this.isEstatusMensajeroCollectionEmpty;
    }

    private void setIsEstatusMensajeroCollectionEmpty() {
        Mensajeros selected = this.getSelected();
        if (selected != null) {
            MensajerosFacade ejbFacade = (MensajerosFacade) this.getFacade();
            this.isEstatusMensajeroCollectionEmpty = ejbFacade.isEstatusMensajeroCollectionEmpty(selected);
        } else {
            this.isEstatusMensajeroCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EstatusMensajero entities
     * that are retrieved from Mensajeros and returns the navigation outcome.
     *
     * @return navigation outcome for EstatusMensajero page
     */
    public String navigateEstatusMensajeroCollection() {
        Mensajeros selected = this.getSelected();
        if (selected != null) {
            MensajerosFacade ejbFacade = (MensajerosFacade) this.getFacade();
            Collection<EstatusMensajero> selectedEstatusMensajeroCollection = ejbFacade.findEstatusMensajeroCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EstatusMensajero_items", selectedEstatusMensajeroCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/estatusMensajero/index";
    }

    /**
     * Sets the "selected" attribute of the Vehiculo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVehiculoCodVehiculo(ActionEvent event) {
        Mensajeros selected = this.getSelected();
        if (selected != null && vehiculoCodVehiculoController.getSelected() == null) {
            vehiculoCodVehiculoController.setSelected(selected.getVehiculoCodVehiculo());
        }
    }

}
