package com.funnyhatsoftware.spacedock.v2.model.entity;

import com.funnyhatsoftware.spacedock.v2.model.entity.card.Admiral;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Thomas on 12/27/2014.
 */
public class Admirals {
    @Attribute(required = false)
    private String schemaLocation;
    @ElementList
    private List<Admiral> admiralList;

    public List<Admiral> getAdmiralList() {
        return admiralList;
    }

    public void setAdmiralList(List<Admiral> admiralList) {
        this.admiralList = admiralList;
    }
}
