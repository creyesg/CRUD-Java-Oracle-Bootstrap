package com.envios.controller;

import com.envios.db12017.EstatusTracking;
import com.envios.facade.EstatusTrackingFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estatusTrackingController")
@ViewScoped
public class EstatusTrackingController extends AbstractController<EstatusTracking> {

    @Inject
    private TrackingController trackingCodTrackingController;
    @Inject
    private MobilePageController mobilePageController;

    public EstatusTrackingController() {
        // Inform the Abstract parent controller of the concrete EstatusTracking Entity
        super(EstatusTracking.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        trackingCodTrackingController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Tracking controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareTrackingCodTracking(ActionEvent event) {
        EstatusTracking selected = this.getSelected();
        if (selected != null && trackingCodTrackingController.getSelected() == null) {
            trackingCodTrackingController.setSelected(selected.getTrackingCodTracking());
        }
    }

}
