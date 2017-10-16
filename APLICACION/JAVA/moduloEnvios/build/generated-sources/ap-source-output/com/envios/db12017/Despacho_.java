package com.envios.db12017;

import com.envios.db12017.Envios;
import com.envios.db12017.EstatusDespacho;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-05T16:55:24")
@StaticMetamodel(Despacho.class)
public class Despacho_ { 

    public static volatile SingularAttribute<Despacho, BigDecimal> codDespacho;
    public static volatile SingularAttribute<Despacho, BigInteger> numeroHojaDespacho;
    public static volatile SingularAttribute<Despacho, BigInteger> idEstatusDespacho;
    public static volatile SingularAttribute<Despacho, BigInteger> codPaquete;
    public static volatile CollectionAttribute<Despacho, Envios> enviosCollection;
    public static volatile SingularAttribute<Despacho, String> usuarioDespacho;
    public static volatile CollectionAttribute<Despacho, EstatusDespacho> estatusDespachoCollection;
    public static volatile SingularAttribute<Despacho, Date> fechaDespacho;

}