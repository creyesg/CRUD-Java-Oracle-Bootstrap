package com.envios.controller;

import com.envios.db12017.EstatusMensajero;
import com.envios.facade.EstatusMensajeroFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estatusMensajeroController")
@ViewScoped
public class EstatusMensajeroController extends AbstractController<EstatusMensajero> {

    @Inject
    private MensajerosController mensajerosCodMensajeroController;
    @Inject
    private MobilePageController mobilePageController;

    public EstatusMensajeroController() {
        // Inform the Abstract parent controller of the concrete EstatusMensajero Entity
        super(EstatusMensajero.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        mensajerosCodMensajeroController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Mensajeros controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareMensajerosCodMensajero(ActionEvent event) {
        EstatusMensajero selected = this.getSelected();
        if (selected != null && mensajerosCodMensajeroController.getSelected() == null) {
            mensajerosCodMensajeroController.setSelected(selected.getMensajerosCodMensajero());
        }
    }

}
