
package com.funnyhatsoftware.spacedock.data;

import java.util.ArrayList;
import java.util.Comparator;

public class Upgrade extends UpgradeBase {
    
    static class UpgradeComparitor implements Comparator<Upgrade> {
        @Override
        public int compare(Upgrade o1, Upgrade o2) {
            int factionCompare = o1.getFaction().compareTo(o2.getFaction());
            if (factionCompare == 0) {
                int titleCompare = o1.getTitle().compareTo(o2.getTitle());
                if (titleCompare == 0) {
                    return DataUtils.compareInt(o2.getCost(), o1.getCost());
                }
                return titleCompare;
            }
            return factionCompare;
        }
    }

    public static Upgrade placeholder(String upType) {
        return Universe.getUniverse().findOrCreatePlaceholder(upType);
    }


    public int limitForShip(EquippedShip targetShip) {
        if (isCaptain()) {
            return 1;
        }

        if (isTalent()) {
            return targetShip.getTalent();
        }

        String special = mSpecial;
        if (special == null) {
            special = "";
        }

        if (special.equals("OnlyForRomulanScienceVessel")
                || special.equals("OnlyForRaptorClassShips")) {
            String shipClass = targetShip.getShip().getShipClass();
            if (!shipClass.equals(targetShipClass())) {
                return 0;
            }
        }

        if (isWeapon()) {
            return targetShip.getWeapon();
        }

        if (isCrew()) {
            return targetShip.getCrew();
        }

        if (isTech()) {
            return targetShip.getTech();
        }

        return 0;
    }

    public boolean isTech() {
        return mUpType.equals("Tech");
    }

    public boolean isCrew() {
        return mUpType.equals("Crew");
    }

    public boolean isWeapon() {
        return mUpType.equals("Weapon");
    }

    public boolean isTalent() {
        return mUpType.equals("Talent");
    }

    public boolean isCaptain() {
        return mUpType.equals("Captain");
    }

    private boolean isDominion() {
        return getFaction().equals("Dominion");
    }

    public String targetShipClass() {

        if (mSpecial.equals("OnlyForRomulanScienceVessel")) {
            return "Romulan Science Vessel";
        } else if (mSpecial.equals("OnlyForRaptorClassShips")) {
            return "Raptor Class";
        }

        return "";
    }

    @Override
    public boolean isPlaceholder() {
        return getPlaceholder();
    }

    private String upSortType() {
        if (isTalent()) {
            return "AATalent";
        }
        return mUpType;
    }

    public int compareTo(Upgrade upgrade) {
        String upTypeMe = upSortType();
        String upTypeOther = upgrade.upSortType();
        int r = upTypeMe.compareTo(upTypeOther);
        if (r == 0) {
            boolean selfIsPlaceholder = isPlaceholder();
            boolean otherIsPlaceholder = upgrade.isPlaceholder();

            if (selfIsPlaceholder == otherIsPlaceholder) {
                return getTitle().compareToIgnoreCase(upgrade.getTitle());
            }

            if (selfIsPlaceholder) {
                return 1;
            }

            return -1;
        }

        if (upTypeMe.equals("Captain")) {
            return -1;
        }

        if (upTypeOther.equals("Captain")) {
            return 1;
        }

        return r;
    }

    public String getPlainDescription() {
        if (isPlaceholder()) {
            return getTitle();
        }

        return String.format("%s (%s)", getTitle(), getUpType());
    }

    public int calculateCostForShip(EquippedShip equippedShip) {
        if (isPlaceholder()) {
            return 0;
        }

        int cost = getCost();

        Ship ship = equippedShip.getShip();
        String shipFaction = ship.getFaction();
        String upgradeFaction = mFaction;
        Captain captain = equippedShip.getCaptain();

        if (isCaptain()) {
            Captain selfCaptain = (Captain) this;
            if (selfCaptain.isZeroCost()) {
                return 0;
            }
        }

        String captainSpecial = captain.getSpecial();
        String upgradeSpecial = getSpecial();

        if (isTalent()) {
            if (captainSpecial.equals("BaselineTalentCostToThree") && upgradeFaction.equals("Federation")) {
                cost = 3;
            }
        } else if (isCrew()) {
            if (captainSpecial.equals("CrewUpgradesCostOneLess")) {
                cost -= 1;
            }

            if (upgradeSpecial.equals("costincreasedifnotromulansciencevessel")) {
                cost += 5;
            }
        } else if (isWeapon()) {
            if (captainSpecial.equals("WeaponUpgradesCostOneLess")) {
                cost -= 1;
            }
        }

        if (upgradeSpecial.equals("costincreasedifnotbreen")) {
            if (!ship.isBreen()) {
                cost += 5;
            }
        } else if (upgradeSpecial.equals("PenaltyOnShipOtherThanDefiant")) {
            if (!ship.isDefiant()) {
                cost += 5;
            }
        } else if (upgradeSpecial.equals("PlusFivePointsNonJemHadarShips")) {
            if (!ship.isJemhadar()) {
                cost += 5;
            }
        } else if (upgradeSpecial.equals("PenaltyOnShipOtherThanKeldonClass")) {
            cost += 5;
        }

        if (!shipFaction.equals(upgradeFaction) && !equippedShip.getIsResourceSideboard()
                && !equippedShip.getFlagshipFaction().equals(upgradeFaction)) {
            if (captainSpecial.equals("UpgradesIgnoreFactionPenalty") && !isCaptain()) {
                // do nothing
            } else if (captainSpecial.equals("NoPenaltyOnFederationOrBajoranShip")) {
                if (!(ship.isFederation() || ship.isBajoran())) {
                    cost += 1;
                }
            } else if (captainSpecial.equals("CaptainAndTalentsIgnoreFactionPenalty")
                    && (isTalent() || isCaptain())) {
                // do nothing
            } else {
                cost += 1;
            }
        }

        if (captainSpecial.equals("OneDominionUpgradeCostsMinusTwo")) {
            if (isDominion()) {
                EquippedUpgrade most = equippedShip.mostExpensiveUpgradeOfFaction("Dominion");
                if (this == most.getUpgrade()) {
                    cost -= 2;
                }
            }
        } else if (captainSpecial.equals("AddTwoCrewSlotsDominionCostBonus")) {
            if (isDominion()) {
                ArrayList<EquippedUpgrade> all = equippedShip.allUpgradesOfFaction("Dominion");
                int index = -1;
                for (int i = 0; i < all.size(); ++i) {
                    EquippedUpgrade eu = all.get(i);
                    Upgrade upgradeToTest = eu.getUpgrade();
                    if (this == upgradeToTest) {
                        index = i;
                        break;
                    }
                }

                if (index != -1 && index < 2) {
                    cost -= 1;
                }
            }
        }

        return cost;

    }
    
}
