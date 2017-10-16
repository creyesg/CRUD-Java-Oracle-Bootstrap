package com.envios.controller;

import com.envios.db12017.TipoVehiculo;
import com.envios.facade.TipoVehiculoFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoVehiculoController")
@ViewScoped
public class TipoVehiculoController extends AbstractController<TipoVehiculo> {

    @Inject
    private VehiculoController vehiculoCodVehiculoController;
    @Inject
    private MobilePageController mobilePageController;

    public TipoVehiculoController() {
        // Inform the Abstract parent controller of the concrete TipoVehiculo Entity
        super(TipoVehiculo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        vehiculoCodVehiculoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Vehiculo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVehiculoCodVehiculo(ActionEvent event) {
        TipoVehiculo selected = this.getSelected();
        if (selected != null && vehiculoCodVehiculoController.getSelected() == null) {
            vehiculoCodVehiculoController.setSelected(selected.getVehiculoCodVehiculo());
        }
    }

}
