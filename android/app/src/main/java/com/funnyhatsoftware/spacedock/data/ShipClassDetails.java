
package com.funnyhatsoftware.spacedock.data;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

import android.text.TextUtils;

public class ShipClassDetails extends ShipClassDetailsBase {

    public static ShipClassDetails find(String newShipClass) {
        for (ShipClassDetails details: Universe.getUniverse().shipClassDetails.values()) {
            if (details.getName().equals(newShipClass)) {
                return details;
            }
        }
        return null;
    }

    public String getMovesSummary() {
        TreeSet<String> specials = new TreeSet<String>();
        for (Maneuver m : mManeuvers) {
            if (m.mKind.equals("about")) {
                specials.add("come about");
            } else if (m.mSpeed < 0 && m.mKind.equals("straight")) {
                specials.add("backup");
            }
        }
        return TextUtils.join(", ", specials);
    }

    public void removeAllManeuvers() {
        mManeuvers.clear();
    }

    public void addManeuver(Maneuver maneuver) {
        mManeuvers.add(maneuver);
    }

    public void updateManeuvers(ArrayList<Map<String, Object>> m) {
        mManeuvers.clear();
        for (Map<String,Object> oneMove: m) {
            Maneuver maneuver = new Maneuver();
            mManeuvers.add(maneuver);
            maneuver.update(oneMove);
        }
    }

    public boolean hasRearFiringArc() {
        return !mRearArc.isEmpty();
    }

    public Maneuver getManeuver(int speed, String kind) {
        for (Maneuver m : mManeuvers) {
            if (m.mSpeed == speed && m.mKind.equals(kind)) {
                return m;
            }
        }
        return null;
    }

    public java.util.Set<Integer> speeds() {
        java.util.HashSet<Integer> spds = new java.util.HashSet<Integer>();
        for (Maneuver m : mManeuvers) {
            spds.add(m.mSpeed);
        }
        return spds;
    }

    @Override
    public void update(Map<String, Object> data) {
        super.update(data);

        @SuppressWarnings("unchecked")
        ArrayList<Map<String, Object>> maneuvers =
                (ArrayList<Map<String, Object>>) data.get("Maneuvers");
        if (maneuvers != null) {
            updateManeuvers(maneuvers);
        }
    }
}
