package com.envios.db12017;

import com.envios.db12017.DireccionesEntregas;
import com.envios.db12017.Envios;
import com.envios.db12017.EstatusEntrega;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-05T16:55:24")
@StaticMetamodel(Entrega.class)
public class Entrega_ { 

    public static volatile CollectionAttribute<Entrega, EstatusEntrega> estatusEntregaCollection;
    public static volatile SingularAttribute<Entrega, BigInteger> numeroHojaDespacho;
    public static volatile SingularAttribute<Entrega, String> direccionEntrega;
    public static volatile SingularAttribute<Entrega, BigInteger> estatusEntrega;
    public static volatile SingularAttribute<Entrega, BigDecimal> codEntrega;
    public static volatile SingularAttribute<Entrega, String> nombreEntrega;
    public static volatile SingularAttribute<Entrega, Date> fechaEntrega;
    public static volatile CollectionAttribute<Entrega, DireccionesEntregas> direccionesEntregasCollection;
    public static volatile SingularAttribute<Entrega, String> usuarioEntrega;
    public static volatile SingularAttribute<Entrega, Envios> envios;

}