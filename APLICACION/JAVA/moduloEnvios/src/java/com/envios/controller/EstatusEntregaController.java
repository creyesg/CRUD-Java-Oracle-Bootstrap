package com.envios.controller;

import com.envios.db12017.EstatusEntrega;
import com.envios.facade.EstatusEntregaFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estatusEntregaController")
@ViewScoped
public class EstatusEntregaController extends AbstractController<EstatusEntrega> {

    @Inject
    private EntregaController entregaCodEntregaController;
    @Inject
    private MobilePageController mobilePageController;

    public EstatusEntregaController() {
        // Inform the Abstract parent controller of the concrete EstatusEntrega Entity
        super(EstatusEntrega.class);
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
        EstatusEntrega selected = this.getSelected();
        if (selected != null && entregaCodEntregaController.getSelected() == null) {
            entregaCodEntregaController.setSelected(selected.getEntregaCodEntrega());
        }
    }

}
