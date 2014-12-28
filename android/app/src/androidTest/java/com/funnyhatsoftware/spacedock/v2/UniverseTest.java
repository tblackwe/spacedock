package com.funnyhatsoftware.spacedock.v2;

import android.test.AndroidTestCase;

import com.funnyhatsoftware.spacedock.v2.model.entity.card.Ship;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.ShipClassDetail;

import junit.framework.Assert;

import java.util.List;

public class UniverseTest extends AndroidTestCase {

    public void testGetShips() throws Exception {
        final List<Ship> ships = UniverseFactory.getShips(getContext());
        Assert.assertNotNull(ships);
        Assert.assertEquals(132, ships.size());
    }

    public void testGetShipClassDetails() throws Exception {
        final List<ShipClassDetail> shipClassDetails = UniverseFactory.getShipClassDetails(getContext());
        Assert.assertNotNull(shipClassDetails);
        Assert.assertEquals(57, shipClassDetails.size());
    }
}