package com.funnyhatsoftware.spacedock.model;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Created by Thomas on 1/11/2015.
 */
@Table("Sets_Map")
public class ReleaseMap extends Model {
    @Key
    @Column("_id")
    int key;
    @Column("id")
    String id;
    @Column("set_id")
    String setId;

    public String getId() {
        return id;
    }

    public String getSetId() {
        return setId;
    }
}
