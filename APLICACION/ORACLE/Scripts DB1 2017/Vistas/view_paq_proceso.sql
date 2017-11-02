
CREATE OR REPLACE VIEW view_paq_proceso
 AS 
 SELECT ord.ORDER_ID, ord.CLIENT_ID, ord.DIRECCION, ord.FEC_ORDER AS fecha_orden, 
(SELECT max(env.FEC_ENVIO) FROM ENVIOS env WHERE env.ORDER_ID = ord.ORDER_ID) AS fecha_envio, 
(SELECT nvl(max(trk.FEC_TRACK), 'Entregado') FROM TRACKING_T trk WHERE trk.ORDER_ID = ord.ORDER_ID) AS Fec_ultimo_Track 
 FROM ORDERS ord
 WHERE ord.ID_STATUS <> 5
 ORDER BY ord.ORDER_ID;
 
 
 
