package com.funnyhatsoftware.spacedock.v2.model.entity.card;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Thomas on 10/26/2014.
 */
@Root
public class ShipClassDetail {

    @Element(name = "Name")
    String name;
    @Element(name = "Id")
    String id;
    @ElementList(name = "Maneuvers")
    List<Maneuver> maneuvers;
    @Element(name = "FrontArc")
    int frontArc = 0;
    @Element(name = "RearArc",required = false)
    int rearArc = 0;

    static class Maneuver {
        @Attribute
        String color;
        @Attribute
        String kind;
        @Attribute
        String speed;
    }
}
