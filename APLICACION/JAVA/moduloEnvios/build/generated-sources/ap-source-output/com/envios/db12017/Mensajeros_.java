package com.envios.db12017;

import com.envios.db12017.EstatusMensajero;
import com.envios.db12017.Vehiculo;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-05T16:55:24")
@StaticMetamodel(Mensajeros.class)
public class Mensajeros_ { 

    public static volatile SingularAttribute<Mensajeros, BigInteger> estatusMensajero;
    public static volatile SingularAttribute<Mensajeros, Vehiculo> vehiculoCodVehiculo;
    public static volatile SingularAttribute<Mensajeros, String> primerApellidoMensajero;
    public static volatile SingularAttribute<Mensajeros, String> primerNombreMensajero;
    public static volatile SingularAttribute<Mensajeros, BigDecimal> codMensajero;
    public static volatile SingularAttribute<Mensajeros, String> segundoNombreMensajero;
    public static volatile SingularAttribute<Mensajeros, String> segundoApellidoMensajero;
    public static volatile CollectionAttribute<Mensajeros, EstatusMensajero> estatusMensajeroCollection;

}