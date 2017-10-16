package com.envios.db12017;

import com.envios.db12017.Entrega;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-05T16:55:24")
@StaticMetamodel(DireccionesEntregas.class)
public class DireccionesEntregas_ { 

    public static volatile SingularAttribute<DireccionesEntregas, BigInteger> zona;
    public static volatile SingularAttribute<DireccionesEntregas, BigDecimal> direccionEntrega;
    public static volatile SingularAttribute<DireccionesEntregas, Entrega> entregaCodEntrega;
    public static volatile SingularAttribute<DireccionesEntregas, String> avenida;
    public static volatile SingularAttribute<DireccionesEntregas, BigInteger> codEntrega;
    public static volatile SingularAttribute<DireccionesEntregas, String> municipio;
    public static volatile SingularAttribute<DireccionesEntregas, String> calle;
    public static volatile SingularAttribute<DireccionesEntregas, String> noVivienda;
    public static volatile SingularAttribute<DireccionesEntregas, String> provincia;
    public static volatile SingularAttribute<DireccionesEntregas, String> pais;

}