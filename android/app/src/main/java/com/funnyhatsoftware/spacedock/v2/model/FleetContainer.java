package com.funnyhatsoftware.spacedock.v2.model;

import com.funnyhatsoftware.spacedock.v2.model.entity.card.resource.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 10/24/2014.
 */
public class FleetContainer extends Container {

    private final List<Resource> resources = new ArrayList<Resource>();
    private final List<ShipContainer> ships = new ArrayList<ShipContainer>();

    @Override
    public int getTotalCost() {
        int cost = 0;
        for (Resource resource : resources) {
            cost += resource.getCost();
        }
        for (ShipContainer ship : ships) {
            cost += ship.getTotalCost();
        }
        return cost;
    }
}
