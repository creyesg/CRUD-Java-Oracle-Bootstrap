package com.envios.db12017;

import com.envios.db12017.EstatusVehiculo;
import com.envios.db12017.Mensajeros;
import com.envios.db12017.Paquete;
import com.envios.db12017.TipoVehiculo;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-05T16:55:24")
@StaticMetamodel(Vehiculo.class)
public class Vehiculo_ { 

    public static volatile SingularAttribute<Vehiculo, BigDecimal> codVehiculo;
    public static volatile SingularAttribute<Vehiculo, Paquete> paqueteCodPaquete;
    public static volatile CollectionAttribute<Vehiculo, TipoVehiculo> tipoVehiculoCollection;
    public static volatile CollectionAttribute<Vehiculo, Mensajeros> mensajerosCollection;
    public static volatile SingularAttribute<Vehiculo, BigInteger> estatusVehiculo;
    public static volatile SingularAttribute<Vehiculo, BigInteger> codMensajero;
    public static volatile SingularAttribute<Vehiculo, BigInteger> codPaquete;
    public static volatile SingularAttribute<Vehiculo, BigInteger> tipoVehiculo;
    public static volatile CollectionAttribute<Vehiculo, EstatusVehiculo> estatusVehiculoCollection;

}