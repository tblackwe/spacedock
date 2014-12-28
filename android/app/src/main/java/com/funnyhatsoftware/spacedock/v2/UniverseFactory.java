package com.funnyhatsoftware.spacedock.v2;

import android.content.Context;
import android.content.res.AssetManager;

import com.funnyhatsoftware.spacedock.v2.model.entity.ShipClassDetails;
import com.funnyhatsoftware.spacedock.v2.model.entity.Ships;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.Ship;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.ShipClassDetail;


import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Thomas on 10/25/2014.
 */
public class UniverseFactory {
    private static final String SHIP_DATA = "ship_data.xml";
    private static final String SHIP_CLASS_DATA = "ship_class_data.xml";
    private static List<Ship> ships;
    private static List<ShipClassDetail> shipClassDetails;
    private static final Serializer SERIALIZER = new Persister();

    public static List<Ship> getShips(Context context) throws Exception {
        if (null == ships) {
            AssetManager am = context.getAssets();
            final InputStream inputStream = am.open(SHIP_DATA);
            Ships serializedShips = SERIALIZER.read(Ships.class, inputStream);
            if (null != serializedShips) {
                ships = serializedShips.getShipList();
            }
        }
        return ships;
    }

    public static List<ShipClassDetail> getShipClassDetails(Context context) throws Exception {
        if (null == shipClassDetails) {
            AssetManager am = context.getAssets();
            final InputStream inputStream = am.open(SHIP_CLASS_DATA);
            ShipClassDetails serializedShipClassDetails = SERIALIZER.read(ShipClassDetails.class, inputStream);
            if (null != serializedShipClassDetails) {
                shipClassDetails = serializedShipClassDetails.getShipClassDetailList();
            }
        }
        return shipClassDetails;
    }
}
