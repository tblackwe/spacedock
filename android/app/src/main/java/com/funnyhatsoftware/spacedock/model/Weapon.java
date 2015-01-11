package com.funnyhatsoftware.spacedock.model;

import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Created by Thomas on 1/11/2015.
 */
@Table("Weapon")
public class Weapon extends Card {
    @Column("attack")
    int attack;
    @Column("range")
    String range;

    public int getAttack() {
        return attack;
    }

    public String getRange() {
        return range;
    }
}
