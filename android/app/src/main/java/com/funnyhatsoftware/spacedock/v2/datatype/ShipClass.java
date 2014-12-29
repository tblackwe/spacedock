package com.funnyhatsoftware.spacedock.v2.datatype;

/**
 * Created by Thomas on 10/25/2014.
 */
public enum ShipClass {
    AERIE("Aerie Class"),
    BREL("B'Rel Class"),
    BREL2("B'rel Class"),
    BAJORAN_INTERCEPTOR("Bajoran Interceptor"),
    BAJORAN_SCOUT_SHIP("Bajoran Scout Ship"),
    BORG_CUBE("Borg Cube"),
    BORG_OCTAHEDRON("Borg Octahedron"),
    BORG_SCOUT_CUBE("Borg Scout Cube"),
    BORG_SPHERE("Borg Sphere"),
    BORG_TACTICAL_CUBE("Borg Tactical Cube"),
    BORG_TYPE_03("Borg Type 03"),
    BREEN_BATTLE_CRUISER("Breen Battle Cruiser"),
    CARDASSIAN_GALOR("Cardassian Galor Class"),
    CARDASSIAN_KELDON("Cardassian Keldon Class"),
    CONSTELLATION("Constellation Class"),
    CONSTITUTION("Constitution Class"),
    CONSTITUTION_REFIT("Constitution Refit Class"),
    DKORA("D'Kora Class"),
    DKYR("D'Kyr Class"),
    DDERIDEX("D'deridex Class"),
    D7("D7 Class"),
    DEFIANT("Defiant Class"),
    DEFIANT_MIRROR("Defiant Class (Mirror)"),
    EXCELSIOR("Excelsior Class"),
    FEDERATION_ATTACK_FIGHTER("Federation Attack Fighter"),
    FEDERATION_NX("Federation NX Class"),
    GALAXY("Galaxy Class"),
    GALAXY_MIRROR("Galaxy Class (Mirror)"),
    GORN_RAIDER("Gorn Raider"),
    HIDEKI_CLASS_ATTACK_FIGHTER("Hideki Class Attack Fighter"),
    INTREPID("Intrepid Class"),
    JEMHADAR_ATTACK_SHIP("Jem'Hadar Attack Ship"),
    JEMHADAR_BATTLE_CRUISER("Jem'Hadar Battle Cruiser"),
    JEMHADAR_BATTLESHIP("Jem'Hadar Battleship"),
    KTINGA("K'T'Inga Class"),
    KVORT("K'Vort Class"),
    KLINGON_BOP("Klingon Bird-of-Prey"),
    KAZON_RAIDER("Kazon Raider"),
    MAQUIS_RAIDER("Maquis Raider"),
    MAQUIS_RAIDER_2("Maquis Raider (Rear Arc)"),
    MIRANDA("Miranda Class"),
    NEBULA("Nebula Class"),
    NEGHVAR("Negh'var Class"),
    NEGHVAR_MIRROR("Negh'var Class (Mirror)"),
    NOR_CLASS_ORBITAL_SPACE_STATION("Nor Class Orbital Space Station"),
    NOVA("Nova Class"),
    PREDATOR("Predator Class"),
    RAPTOR("Raptor Class"),
    REMAN_WARBIRD("Reman Warbird"),
    ROMULAN_BIRDOFPREY("Romulan Bird-Of-Prey Class"),
    ROMULAN_BIRD_OF_PREY("Romulan Bird of Prey Class"),
    ROMULAN_SCIENCE_VESSEL("Romulan Science Vessel"),
    ROMULAN_SCOUT_VESSEL("Romulan Scout Vessel"),
    SABER("Saber Class"),
    SOVEREIGN("Sovereign Class"),
    SPECIES_8472_BIOSHIP("Species 8472 Bioship"),
    SUUROK("Suurok Class"),
    THOLIAN_VESSEL("Tholian Vessel"),
    VALDORE("Valdore Class"),
    VORCHA("Vor'cha Class");

    private String text;

    ShipClass(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static ShipClass fromText(String text){
        for (ShipClass shipClass : ShipClass.values()){
            if (shipClass.text.equals(text)){
                return shipClass;
            }
        }
        return null;
    }
}
