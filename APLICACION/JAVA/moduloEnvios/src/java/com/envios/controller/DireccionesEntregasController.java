package com.envios.controller;

import com.envios.db12017.DireccionesEntregas;
import com.envios.facade.DireccionesEntregasFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "direccionesEntregasController")
@ViewScoped
public class DireccionesEntregasController extends AbstractController<DireccionesEntregas> {

    @Inject
    private EntregaController entregaCodEntregaController;
    @Inject
    private MobilePageController mobilePageController;

    public DireccionesEntregasController() {
        // Inform the Abstract parent controller of the concrete DireccionesEntregas Entity
        super(DireccionesEntregas.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        entregaCodEntregaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Entrega controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEntregaCodEntrega(ActionEvent event) {
        DireccionesEntregas selected = this.getSelected();
        if (selected != null && entregaCodEntregaController.getSelected() == null) {
            entregaCodEntregaController.setSelected(selected.getEntregaCodEntrega());
        }
    }

}
