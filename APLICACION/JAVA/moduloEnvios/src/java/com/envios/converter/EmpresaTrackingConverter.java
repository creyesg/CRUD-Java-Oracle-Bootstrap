package com.envios.converter;

import com.envios.db12017.EmpresaTracking;
import com.envios.facade.EmpresaTrackingFacade;
import com.envios.controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "empresaTrackingConverter")
public class EmpresaTrackingConverter implements Converter {

    private EmpresaTrackingFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.getEjbFacade().find(getKey(value));
    }

    java.math.BigDecimal getKey(String value) {
        java.math.BigDecimal key;
        key = new java.math.BigDecimal(value);
        return key;
    }

    String getStringKey(java.math.BigDecimal value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof EmpresaTracking) {
            EmpresaTracking o = (EmpresaTracking) object;
            return getStringKey(o.getCodEmpresaTracking());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EmpresaTracking.class.getName()});
            return null;
        }
    }

    private EmpresaTrackingFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(EmpresaTrackingFacade.class).get();
        return this.ejbFacade;
    }
}
