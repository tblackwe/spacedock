package com.funnyhatsoftware.spacedock.model;

import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Created by Thomas on 1/9/2015.
 */
@Table("Captains")
public class Captain extends Card {

    @Column("skill")
    int skill;
    @Column("talent")
    int talent;

    public int getSkill() {
        return skill;
    }

    public int getTalent() {
        return talent;
    }
}
