package com.envios.db12017;

import com.envios.db12017.Despacho;
import com.envios.db12017.Entrega;
import com.envios.db12017.EnviosPK;
import com.envios.db12017.Paquete;
import com.envios.db12017.Tracking;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-05T16:55:24")
@StaticMetamodel(Envios.class)
public class Envios_ { 

    public static volatile SingularAttribute<Envios, EnviosPK> enviosPK;
    public static volatile SingularAttribute<Envios, String> descripcion;
    public static volatile CollectionAttribute<Envios, Paquete> paqueteCollection;
    public static volatile SingularAttribute<Envios, Date> fechaEnvio;
    public static volatile SingularAttribute<Envios, BigInteger> codTracking;
    public static volatile SingularAttribute<Envios, Despacho> despachoCodDespacho;
    public static volatile SingularAttribute<Envios, BigInteger> idUnidad;
    public static volatile SingularAttribute<Envios, BigInteger> codPaquete;
    public static volatile CollectionAttribute<Envios, Entrega> entregaCollection;
    public static volatile CollectionAttribute<Envios, Tracking> trackingCollection;
    public static volatile SingularAttribute<Envios, String> usuarioEnvio;
    public static volatile SingularAttribute<Envios, String> nombreEnvia;

}