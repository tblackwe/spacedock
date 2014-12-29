package com.funnyhatsoftware.spacedock.v2;

import android.test.AndroidTestCase;

import com.funnyhatsoftware.spacedock.v2.datatype.ShipClass;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.Admiral;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.Captain;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.Ship;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.ShipClassDetail;

import junit.framework.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class UniverseTest extends AndroidTestCase {

    public void testGetShips() throws Exception {
        final List<Ship> ships = UniverseFactory.getShips(getContext());
        Assert.assertNotNull(ships);
        Assert.assertEquals("Incorrect number of ships", 139, ships.size());
    }

    public void testGetShipClassDetails() throws Exception {
        final List<ShipClassDetail> shipClassDetails = UniverseFactory.getShipClassDetails(getContext());
        Assert.assertNotNull(shipClassDetails);
        if (ShipClass.values().length != shipClassDetails.size()) {
            ShipClass[] clone = ShipClass.values().clone();
            SortedSet shipClassSet = new TreeSet(Arrays.asList(clone));
            for (ShipClassDetail detail : shipClassDetails) {
                final ShipClass shipClass = ShipClass.fromText(detail.getName());
                if (null != shipClass) {
                    shipClassSet.remove(shipClass);
                } else {
                    Assert.fail("MISSING ENUM " + detail.getName());
                }
            }
            if (!shipClassSet.isEmpty()) {
                Assert.fail("MISSING CLASSES: " + shipClassSet);
            }
        }
    }

    public void testGetCaptainsDetails() throws Exception {
        final List<Captain> details = UniverseFactory.getCaptains(getContext());
        Assert.assertNotNull(details);
        Assert.assertEquals("Incorrect number of captains", 120, details.size());
    }

    public void testGetAdmiralsDetails() throws Exception {
        final List<Admiral> details = UniverseFactory.getAdmirals(getContext());
        Assert.assertNotNull(details);
        Assert.assertEquals("Incorrect number of admirals", 9, details.size());
    }
}