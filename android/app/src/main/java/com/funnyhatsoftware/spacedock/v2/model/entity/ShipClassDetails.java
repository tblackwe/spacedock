package com.funnyhatsoftware.spacedock.v2.model.entity;

import com.funnyhatsoftware.spacedock.v2.model.entity.card.ShipClassDetail;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Thomas on 10/26/2014.
 */
@Root
public class ShipClassDetails {
    @Attribute(required = false)
    private String schemaLocation;
    @ElementList
    List<ShipClassDetail> shipClassDetailList;

    public List<ShipClassDetail> getShipClassDetailList() {
        return shipClassDetailList;
    }

    public void setShipClassDetailList(List<ShipClassDetail> shipClassDetailList) {
        this.shipClassDetailList = shipClassDetailList;
    }
}
