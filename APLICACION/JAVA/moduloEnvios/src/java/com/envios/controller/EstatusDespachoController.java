package com.envios.controller;

import com.envios.db12017.EstatusDespacho;
import com.envios.facade.EstatusDespachoFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estatusDespachoController")
@ViewScoped
public class EstatusDespachoController extends AbstractController<EstatusDespacho> {

    @Inject
    private DespachoController despachoCodDespachoController;
    @Inject
    private MobilePageController mobilePageController;

    public EstatusDespachoController() {
        // Inform the Abstract parent controller of the concrete EstatusDespacho Entity
        super(EstatusDespacho.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        despachoCodDespachoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Despacho controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDespachoCodDespacho(ActionEvent event) {
        EstatusDespacho selected = this.getSelected();
        if (selected != null && despachoCodDespachoController.getSelected() == null) {
            despachoCodDespachoController.setSelected(selected.getDespachoCodDespacho());
        }
    }

}
