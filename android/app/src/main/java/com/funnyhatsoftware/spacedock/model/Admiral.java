package com.funnyhatsoftware.spacedock.model;

import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Created by Thomas on 1/9/2015.
 */
@Table("Admirals")
public class Admiral extends Captain {
    @Column("skillmodifier")
    int skillModifier;
    @Column("admiral_ability")
    String admiralAbility;

    public int getSkillModifier() {
        return skillModifier;
    }

    public String getAdmiralAbility() {
        return admiralAbility;
    }
}
