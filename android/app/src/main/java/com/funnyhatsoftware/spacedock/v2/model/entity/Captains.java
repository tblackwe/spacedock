package com.funnyhatsoftware.spacedock.v2.model.entity;

import com.funnyhatsoftware.spacedock.v2.model.entity.card.Captain;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Thomas on 12/27/2014.
 */
public class Captains {
    @ElementList
    private List<Captain> captainList;
    @Attribute(required=false)
    private String schemaLocation;

    public List<Captain> getCaptainList() {
        return captainList;
    }

    public void setCaptainList(List<Captain> captainList) {
        this.captainList = captainList;
    }

    public String getSchemaLocation() {
        return schemaLocation;
    }

    public void setSchemaLocation(String schemaLocation) {
        this.schemaLocation = schemaLocation;
    }
}

