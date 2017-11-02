
CREATE OR REPLACE VIEW view_paq_entregados
 AS 
 SELECT ord.ORDER_ID, ord.CLIENT_ID, ord.DIRECCION, ord.FEC_ORDER AS fecha_orden, 
(SELECT max(env.FEC_ENVIO) FROM ENVIOS env WHERE env.ORDER_ID = ord.ORDER_ID) AS fecha_envio, 
(SELECT nvl(max(trk.FEC_TRACK), 'Pendiente') FROM TRACKING_T trk WHERE trk.ORDER_ID = ord.ORDER_ID) AS Fecha_entrega 
 FROM ORDERS ord
 WHERE ord.ID_STATUS = 5
 ORDER BY ord.ORDER_ID;



