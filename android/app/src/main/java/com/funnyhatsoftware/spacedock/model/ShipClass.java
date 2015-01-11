package com.funnyhatsoftware.spacedock.model;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Created by Thomas on 1/7/2015.
 */
@Table("ShipClasses")
public class ShipClass extends Model {

    @Key
    @Column("id")
    String id;
    @Column("name")
    String name;
    @Column("frontArc")
    int frontArc;
    @Column("rearArc")
    int rearArc;
    @Column("redMoves")
    String redMoves;
    @Column("whiteMoves")
    String whiteMoves;
    @Column("greenMoves")
    String greenMoves;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrontArc() {
        return frontArc;
    }

    public int getRearArc() {
        return rearArc;
    }

    public String getRedMoves() {
        return redMoves;
    }

    public String getWhiteMoves() {
        return whiteMoves;
    }

    public String getGreenMoves() {
        return greenMoves;
    }
}
