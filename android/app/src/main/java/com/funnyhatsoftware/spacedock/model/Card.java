package com.funnyhatsoftware.spacedock.model;

import com.funnyhatsoftware.spacedock.datatype.Faction;
import com.funnyhatsoftware.spacedock.datatype.Special;
import com.funnyhatsoftware.spacedock.datatype.Unique;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;

/**
 * Created by Thomas on 1/7/2015.
 */
public abstract class Card extends Model {
    @Key
    @Column("id")
    String id;
    @Column("title")
    String title;
    @Column("uniq")
    int uniq;
    @Column("faction")
    int faction;
    @Column("faction2")
    int faction2;
    @Column("ability")
    String ability;
    @Column("cost")
    int cost;
    @Column("special")
    String special;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Unique getUnique() {
        switch (uniq) {
            case 1:
                return Unique.PRIME;
            case -1:
                return Unique.MIRROR;
            default:
                return null;
        }
    }

    public List<Faction> getFactions() {
        List<Faction> retVal = new ArrayList<Faction>();
        retVal.add(determineFaction(faction));
        if (0 < faction2) {
            retVal.add(determineFaction(faction2));
        }
        return retVal;
    }

    private Faction determineFaction(int faction) {
        switch (faction) {
            case 1:
                return Faction.Federation;
            case 2:
                return Faction.Klingon;
            case 3:
                return Faction.Romulan;
            case 4:
                return Faction.Dominion;
            case 5:
                return Faction.Borg;
            case 6:
                return Faction.Species_8472;
            case 7:
                return Faction.Kazon;
            case 8:
                return Faction.Bajoran;
            case 9:
                return Faction.Ferengi;
            case 10:
                return Faction.Vulcan;
            case 11:
                return Faction.Independent;
            case 12:
                return Faction.Mirror;
            default:
                return null;
        }
    }

    public String getAbility() {
        return ability;
    }

    public int getCost() {
        return cost;
    }

    public List<Special> getSpecials() {
        //TODO convert CSV to special list
        return null;
    }
}
