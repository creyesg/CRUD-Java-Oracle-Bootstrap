package com.envios.db12017;

import com.envios.db12017.Envios;
import com.envios.db12017.EstatusPaquete;
import com.envios.db12017.Vehiculo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-05T16:55:24")
@StaticMetamodel(Paquete.class)
public class Paquete_ { 

    public static volatile SingularAttribute<Paquete, BigInteger> codVehiculo;
    public static volatile SingularAttribute<Paquete, BigInteger> numeroHojaDespacho;
    public static volatile SingularAttribute<Paquete, Date> fechaEnvio;
    public static volatile SingularAttribute<Paquete, BigInteger> estatusPaquete;
    public static volatile SingularAttribute<Paquete, BigDecimal> codPaquete;
    public static volatile SingularAttribute<Paquete, String> usuarioEnvioPaquete;
    public static volatile CollectionAttribute<Paquete, EstatusPaquete> estatusPaqueteCollection;
    public static volatile SingularAttribute<Paquete, Date> fechaDespacho;
    public static volatile CollectionAttribute<Paquete, Vehiculo> vehiculoCollection;
    public static volatile SingularAttribute<Paquete, Envios> envios;

}