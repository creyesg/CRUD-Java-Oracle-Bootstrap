package com.envios.controller;

import com.envios.db12017.EmpresaTracking;
import com.envios.facade.EmpresaTrackingFacade;
import com.envios.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "empresaTrackingController")
@ViewScoped
public class EmpresaTrackingController extends AbstractController<EmpresaTracking> {

    @Inject
    private MobilePageController mobilePageController;

    public EmpresaTrackingController() {
        // Inform the Abstract parent controller of the concrete EmpresaTracking Entity
        super(EmpresaTracking.class);
    }

}
