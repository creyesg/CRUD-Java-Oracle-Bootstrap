package com.envios.converter;

import com.envios.db12017.Envios;
import com.envios.facade.EnviosFacade;
import com.envios.controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "enviosConverter")
public class EnviosConverter implements Converter {

    private EnviosFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.getEjbFacade().find(getKey(value));
    }

    com.envios.db12017.EnviosPK getKey(String value) {
        com.envios.db12017.EnviosPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new com.envios.db12017.EnviosPK();
        //key.setNumeroHojaDespacho(values[0]);
        //key.setCodEntrega(values[1]);
        return key;
    }

    String getStringKey(com.envios.db12017.EnviosPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getNumeroHojaDespacho());
        sb.append(SEPARATOR);
        sb.append(value.getCodEntrega());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Envios) {
            Envios o = (Envios) object;
            return getStringKey(o.getEnviosPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Envios.class.getName()});
            return null;
        }
    }

    private EnviosFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(EnviosFacade.class).get();
        return this.ejbFacade;
    }
}
