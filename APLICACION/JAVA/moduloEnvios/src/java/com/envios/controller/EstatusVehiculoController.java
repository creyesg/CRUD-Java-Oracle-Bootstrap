package com.envios.controller;

import com.envios.db12017.EstatusVehiculo;
import com.envios.facade.EstatusVehiculoFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estatusVehiculoController")
@ViewScoped
public class EstatusVehiculoController extends AbstractController<EstatusVehiculo> {

    @Inject
    private VehiculoController vehiculoCodVehiculoController;
    @Inject
    private MobilePageController mobilePageController;

    public EstatusVehiculoController() {
        // Inform the Abstract parent controller of the concrete EstatusVehiculo Entity
        super(EstatusVehiculo.class);
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
        EstatusVehiculo selected = this.getSelected();
        if (selected != null && vehiculoCodVehiculoController.getSelected() == null) {
            vehiculoCodVehiculoController.setSelected(selected.getVehiculoCodVehiculo());
        }
    }

}
