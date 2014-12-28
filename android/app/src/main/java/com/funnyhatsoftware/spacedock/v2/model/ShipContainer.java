package com.funnyhatsoftware.spacedock.v2.model;

import com.funnyhatsoftware.spacedock.v2.model.entity.card.Admiral;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.Captain;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.upgrade.Borg;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.upgrade.Crew;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.upgrade.Tech;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.upgrade.Weapon;

/**
 * Created by Thomas on 10/24/2014.
 */
public class ShipContainer extends Container {
    @Override
    public int getTotalCost() {
        return 0;
    }

    private Admiral admiral;
    private Captain captain;
    private Crew[] crewSlots = new Crew[0];
    private Weapon[] weaponSlots = new Weapon[0];
    private Tech[] techSlots = new Tech[0];
    private Borg[] borgSlots = new Borg[0];
}
