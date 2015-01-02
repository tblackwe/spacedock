package com.funnyhatsoftware.spacedock.v2.model;

import com.funnyhatsoftware.spacedock.v2.model.entity.card.Admiral;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.Captain;
import com.funnyhatsoftware.spacedock.v2.model.entity.card.Ship;
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

    private final Ship ship;
    private Admiral admiral;
    private Captain captain;
    private Crew[] crewSlots;
    private Weapon[] weaponSlots;
    private Tech[] techSlots;
    private Borg[] borgSlots;

    public ShipContainer(Ship ship) {
        this.ship = ship;
        this.crewSlots = new Crew[ship.getCrew()];
        this.weaponSlots = new Weapon[ship.getWeapon()];
        this.techSlots = new Tech[ship.getTech()];
        this.borgSlots = new Borg[ship.getBorg()];
    }

    public Admiral getAdmiral() {
        return admiral;
    }

    public void setAdmiral(Admiral admiral) {
        this.admiral = admiral;
    }

    public Captain getCaptain() {
        return captain;
    }

    public void setCaptain(Captain captain) {
        this.captain = captain;
    }

    public Crew[] getCrewSlots() {
        return crewSlots;
    }

    public void setCrewSlots(Crew[] crewSlots) {
        this.crewSlots = crewSlots;
    }

    public Weapon[] getWeaponSlots() {
        return weaponSlots;
    }

    public void setWeaponSlots(Weapon[] weaponSlots) {
        this.weaponSlots = weaponSlots;
    }

    public Tech[] getTechSlots() {
        return techSlots;
    }

    public void setTechSlots(Tech[] techSlots) {
        this.techSlots = techSlots;
    }

    public Borg[] getBorgSlots() {
        return borgSlots;
    }

    public void setBorgSlots(Borg[] borgSlots) {
        this.borgSlots = borgSlots;
    }
}
