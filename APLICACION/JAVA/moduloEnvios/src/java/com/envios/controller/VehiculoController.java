package com.envios.controller;

import com.envios.db12017.Vehiculo;
import com.envios.db12017.EstatusVehiculo;
import com.envios.db12017.TipoVehiculo;
import com.envios.db12017.Mensajeros;
import java.util.Collection;
import com.envios.facade.VehiculoFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "vehiculoController")
@ViewScoped
public class VehiculoController extends AbstractController<Vehiculo> {

    @Inject
    private PaqueteController paqueteCodPaqueteController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEstatusVehiculoCollectionEmpty;
    private boolean isTipoVehiculoCollectionEmpty;
    private boolean isMensajerosCollectionEmpty;

    public VehiculoController() {
        // Inform the Abstract parent controller of the concrete Vehiculo Entity
        super(Vehiculo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        paqueteCodPaqueteController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEstatusVehiculoCollectionEmpty();
        this.setIsTipoVehiculoCollectionEmpty();
        this.setIsMensajerosCollectionEmpty();
    }

    public boolean getIsEstatusVehiculoCollectionEmpty() {
        return this.isEstatusVehiculoCollectionEmpty;
    }

    private void setIsEstatusVehiculoCollectionEmpty() {
        Vehiculo selected = this.getSelected();
        if (selected != null) {
            VehiculoFacade ejbFacade = (VehiculoFacade) this.getFacade();
            this.isEstatusVehiculoCollectionEmpty = ejbFacade.isEstatusVehiculoCollectionEmpty(selected);
        } else {
            this.isEstatusVehiculoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EstatusVehiculo entities
     * that are retrieved from Vehiculo and returns the navigation outcome.
     *
     * @return navigation outcome for EstatusVehiculo page
     */
    public String navigateEstatusVehiculoCollection() {
        Vehiculo selected = this.getSelected();
        if (selected != null) {
            VehiculoFacade ejbFacade = (VehiculoFacade) this.getFacade();
            Collection<EstatusVehiculo> selectedEstatusVehiculoCollection = ejbFacade.findEstatusVehiculoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EstatusVehiculo_items", selectedEstatusVehiculoCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/estatusVehiculo/index";
    }

    public boolean getIsTipoVehiculoCollectionEmpty() {
        return this.isTipoVehiculoCollectionEmpty;
    }

    private void setIsTipoVehiculoCollectionEmpty() {
        Vehiculo selected = this.getSelected();
        if (selected != null) {
            VehiculoFacade ejbFacade = (VehiculoFacade) this.getFacade();
            this.isTipoVehiculoCollectionEmpty = ejbFacade.isTipoVehiculoCollectionEmpty(selected);
        } else {
            this.isTipoVehiculoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of TipoVehiculo entities
     * that are retrieved from Vehiculo and returns the navigation outcome.
     *
     * @return navigation outcome for TipoVehiculo page
     */
    public String navigateTipoVehiculoCollection() {
        Vehiculo selected = this.getSelected();
        if (selected != null) {
            VehiculoFacade ejbFacade = (VehiculoFacade) this.getFacade();
            Collection<TipoVehiculo> selectedTipoVehiculoCollection = ejbFacade.findTipoVehiculoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoVehiculo_items", selectedTipoVehiculoCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/tipoVehiculo/index";
    }

    /**
     * Sets the "selected" attribute of the Paquete controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePaqueteCodPaquete(ActionEvent event) {
        Vehiculo selected = this.getSelected();
        if (selected != null && paqueteCodPaqueteController.getSelected() == null) {
            paqueteCodPaqueteController.setSelected(selected.getPaqueteCodPaquete());
        }
    }

    public boolean getIsMensajerosCollectionEmpty() {
        return this.isMensajerosCollectionEmpty;
    }

    private void setIsMensajerosCollectionEmpty() {
        Vehiculo selected = this.getSelected();
        if (selected != null) {
            VehiculoFacade ejbFacade = (VehiculoFacade) this.getFacade();
            this.isMensajerosCollectionEmpty = ejbFacade.isMensajerosCollectionEmpty(selected);
        } else {
            this.isMensajerosCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Mensajeros entities that
     * are retrieved from Vehiculo and returns the navigation outcome.
     *
     * @return navigation outcome for Mensajeros page
     */
    public String navigateMensajerosCollection() {
        Vehiculo selected = this.getSelected();
        if (selected != null) {
            VehiculoFacade ejbFacade = (VehiculoFacade) this.getFacade();
            Collection<Mensajeros> selectedMensajerosCollection = ejbFacade.findMensajerosCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Mensajeros_items", selectedMensajerosCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/mensajeros/index";
    }

}
