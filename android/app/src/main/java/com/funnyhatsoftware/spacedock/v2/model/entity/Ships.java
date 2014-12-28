package com.funnyhatsoftware.spacedock.v2.model.entity;

import com.funnyhatsoftware.spacedock.v2.model.entity.card.Ship;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Thomas on 10/25/2014.
 */

public class Ships {
    @ElementList
    private List<Ship> shipList;

    public List<Ship> getShipList() {
        return shipList;
    }

    public void setShipList(List<Ship> shipList) {
        this.shipList = shipList;
    }
}
