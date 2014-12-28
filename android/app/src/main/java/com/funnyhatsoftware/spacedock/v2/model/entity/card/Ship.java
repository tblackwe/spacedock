package com.funnyhatsoftware.spacedock.v2.model.entity.card;

import com.funnyhatsoftware.spacedock.v2.datatype.Action;
import com.funnyhatsoftware.spacedock.v2.datatype.ShipClass;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Thomas on 10/25/2014.
 */
public class Ship extends Card {
    @Element(name = "ShipClass")
    private ShipClass shipClass;
    //Stats
    @Element(name = "Attack")
    private int attack;
    @Element(name = "Agility")
    private int agility;
    @Element(name = "Hull")
    private int hull;
    @Element(name = "Shield")
    private int shield;

    @ElementList(name = "Actions", required = false)
    private List<Action> actions;

    @Element(name = "Crew", required = false)
    private int crew;
    @Element(name = "Weapon", required = false)
    private int weapon;
    @Element(name = "Tech", required = false)
    private int tech;
    @Element(name = "Borg", required = false)
    private int borg;
    @Element(name = "CaptainEligible", required = false)
    private boolean captainEligible = true;

    public ShipClass getShipClass() {
        return shipClass;
    }

    public void setShipClass(ShipClass shipClass) {
        this.shipClass = shipClass;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getHull() {
        return hull;
    }

    public void setHull(int hull) {
        this.hull = hull;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }

    public int getTech() {
        return tech;
    }

    public void setTech(int tech) {
        this.tech = tech;
    }

    public int getBorg() {
        return borg;
    }

    public void setBorg(int borg) {
        this.borg = borg;
    }

    public boolean isCaptainEligible() {
        return captainEligible;
    }

    public void setCaptainEligible(boolean captainEligible) {
        this.captainEligible = captainEligible;
    }
}
