package com.funnyhatsoftware.spacedock.v2;

import android.test.AndroidTestCase;

import com.funnyhatsoftware.spacedock.v2.model.entity.Captains;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.Admiral;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.Captain;
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

    public void testGetCaptainsDetails() throws Exception {
        final List<Captain> details = UniverseFactory.getCaptains(getContext());
        Assert.assertNotNull(details);
        Assert.assertEquals(57, details.size());
    }

    public void testGetAdmiralsDetails() throws Exception {
        final List<Admiral> details = UniverseFactory.getAdmirals(getContext());
        Assert.assertNotNull(details);
        Assert.assertEquals(57, details.size());
    }
}