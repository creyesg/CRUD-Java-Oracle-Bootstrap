package com.envios.db12017;

import com.envios.db12017.Envios;
import com.envios.db12017.EstatusTracking;
import com.envios.db12017.PaisEmpresaTracking;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-05T16:55:24")
@StaticMetamodel(Tracking.class)
public class Tracking_ { 

    public static volatile SingularAttribute<Tracking, BigInteger> codEmpresaTracking;
    public static volatile SingularAttribute<Tracking, BigInteger> codEstatusTracking;
    public static volatile SingularAttribute<Tracking, BigInteger> numeroHojaDespacho;
    public static volatile SingularAttribute<Tracking, BigDecimal> codTracking;
    public static volatile CollectionAttribute<Tracking, PaisEmpresaTracking> paisEmpresaTrackingCollection;
    public static volatile CollectionAttribute<Tracking, EstatusTracking> estatusTrackingCollection;
    public static volatile SingularAttribute<Tracking, BigInteger> codPaisTracking;
    public static volatile SingularAttribute<Tracking, Envios> envios;

}