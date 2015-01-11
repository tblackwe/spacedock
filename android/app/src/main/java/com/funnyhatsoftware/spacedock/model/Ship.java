package com.funnyhatsoftware.spacedock.model;

import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Created by Thomas on 1/6/2015.
 */
@Table("Ships")
public class Ship extends Card {
    @Column("class")
    String shipClass;
    @Column("classid")
    String classid;
    @Column("attack")
    int attack;
    @Column("agility")
    int agility;
    @Column("hull")
    int hull;
    @Column("shield")
    int shield;

    @Column("evasive")
    int evasive;
    @Column("targetlock")
    int targetlock;
    @Column("scan")
    int scan;
    @Column("battlestations")
    int battlestations;
    @Column("cloak")
    int cloak;
    @Column("sensorecho")
    int sensorecho;
    @Column("regenerate")
    int regenerate;
    @Column("crew")
    int crew;
    @Column("tech")
    int tech;
    @Column("weapon")
    int weapon;
    @Column("borg")
    int borg;
    @Column("squad")
    int squad;

    public String getShipClass() {
        return shipClass;
    }

    public String getClassid() {
        return classid;
    }

    public int getAttack() {
        return attack;
    }

    public int getAgility() {
        return agility;
    }

    public int getHull() {
        return hull;
    }

    public int getShield() {
        return shield;
    }

    public int getEvasive() {
        return evasive;
    }

    public int getTargetlock() {
        return targetlock;
    }

    public int getScan() {
        return scan;
    }

    public int getBattlestations() {
        return battlestations;
    }

    public int getCloak() {
        return cloak;
    }

    public int getSensorecho() {
        return sensorecho;
    }

    public int getRegenerate() {
        return regenerate;
    }

    public int getCrew() {
        return crew;
    }

    public int getTech() {
        return tech;
    }

    public int getWeapon() {
        return weapon;
    }

    public int getBorg() {
        return borg;
    }

    public int getSquad() {
        return squad;
    }
}
