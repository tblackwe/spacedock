package com.funnyhatsoftware.spacedock.model;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Created by Thomas on 1/9/2015.
 */
@Table("Sets")
public class Release extends Model{
    @Key
    @Column("id")
    String id;
    @Column("release")
    String release;
    @Column("set_group")
    String setGroup;
    @Column("name")
    String name;

    public String getId() {
        return id;
    }

    public String getRelease() {
        return release;
    }

    public String getSetGroup() {
        return setGroup;
    }

    public String getName() {
        return name;
    }
}
