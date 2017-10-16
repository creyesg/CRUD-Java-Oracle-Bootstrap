package com.envios.controller;

import com.envios.db12017.EstatusPaquete;
import com.envios.facade.EstatusPaqueteFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estatusPaqueteController")
@ViewScoped
public class EstatusPaqueteController extends AbstractController<EstatusPaquete> {

    @Inject
    private PaqueteController paqueteCodPaqueteController;
    @Inject
    private MobilePageController mobilePageController;

    public EstatusPaqueteController() {
        // Inform the Abstract parent controller of the concrete EstatusPaquete Entity
        super(EstatusPaquete.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        paqueteCodPaqueteController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Paquete controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePaqueteCodPaquete(ActionEvent event) {
        EstatusPaquete selected = this.getSelected();
        if (selected != null && paqueteCodPaqueteController.getSelected() == null) {
            paqueteCodPaqueteController.setSelected(selected.getPaqueteCodPaquete());
        }
    }

}
