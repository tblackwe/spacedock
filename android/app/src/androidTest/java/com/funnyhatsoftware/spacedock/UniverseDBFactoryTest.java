package com.funnyhatsoftware.spacedock;

import android.test.AndroidTestCase;

import com.funnyhatsoftware.spacedock.model.Admiral;
import com.funnyhatsoftware.spacedock.model.Borg;
import com.funnyhatsoftware.spacedock.model.Captain;
import com.funnyhatsoftware.spacedock.model.Crew;
import com.funnyhatsoftware.spacedock.model.Release;
import com.funnyhatsoftware.spacedock.model.ReleaseMap;
import com.funnyhatsoftware.spacedock.model.Ship;
import com.funnyhatsoftware.spacedock.model.ShipClass;
import com.funnyhatsoftware.spacedock.model.Talent;
import com.funnyhatsoftware.spacedock.model.Tech;
import com.funnyhatsoftware.spacedock.model.Weapon;

import junit.framework.Assert;

import java.util.List;

import se.emilsjolander.sprinkles.Query;
import se.emilsjolander.sprinkles.Sprinkles;

public class UniverseDBFactoryTest extends AndroidTestCase {

    public void testUniverse() throws Exception {
        Sprinkles.init(getContext(), "spacedock.db", 1);

        final List<Release> releases = Query.all(Release.class).get().asList();
        Assert.assertEquals(89, releases.size());

        final List<Ship> ships = Query.all(Ship.class).get().asList();
        Assert.assertEquals(151, ships.size());
        final List<ShipClass> shipClasses = Query.all(ShipClass.class).get().asList();
        Assert.assertEquals(62, shipClasses.size());
        final List<Admiral> admirals = Query.all(Admiral.class).get().asList();
        Assert.assertEquals(9, admirals.size());
        final List<Captain> captains = Query.all(Captain.class).get().asList();
        Assert.assertEquals(142, captains.size());
        final List<Crew> crew = Query.all(Crew.class).get().asList();
        Assert.assertEquals(150, crew.size());
        final List<Talent> talent = Query.all(Talent.class).get().asList();
        Assert.assertEquals(81, talent.size());
        final List<Tech> tech = Query.all(Tech.class).get().asList();
        Assert.assertEquals(76, tech.size());
        final List<Borg> borg = Query.all(Borg.class).get().asList();
        Assert.assertEquals(16, borg.size());
        final List<Weapon> weapon = Query.all(Weapon.class).get().asList();
        Assert.assertEquals(75, weapon.size());
        //Should add all items
        final List<ReleaseMap> releaseMap = Query.all(ReleaseMap.class).get().asList();
        Assert.assertEquals(868, releaseMap.size());
    }
}