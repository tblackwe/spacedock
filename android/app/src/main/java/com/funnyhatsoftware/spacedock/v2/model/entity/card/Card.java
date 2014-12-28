package com.funnyhatsoftware.spacedock.v2.model.entity.card;

import com.funnyhatsoftware.spacedock.v2.datatype.Release;
import com.funnyhatsoftware.spacedock.v2.datatype.Faction;
import com.funnyhatsoftware.spacedock.v2.datatype.Special;
import com.funnyhatsoftware.spacedock.v2.datatype.Unique;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Thomas on 10/25/2014.
 */
@Root
public abstract class Card {
    @Element(name = "Title")
    protected String title;
    @Element(name = "Text", required = false)
    protected String text;
    @Element(name = "Release", required = false)
    protected String release;
    @Element(name = "Releases", required = false)
    protected List<String> releases;
    @ElementList(name = "Factions")
    protected List<Faction> factions;
    @Element(name = "Cost")
    protected int cost;
    @ElementList(name = "Specials", required = false)
    protected List<Special> specials;
    @Element(name = "Unique",required=false)
    protected Unique unique = null;
    @Element(name = "Id")
    protected String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public List<Faction> getFactions() {
        return factions;
    }

    public void setFactions(List<Faction> factions) {
        this.factions = factions;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Special> getSpecials() {
        return specials;
    }

    public void setSpecials(List<Special> specials) {
        this.specials = specials;
    }

    public Unique getUnique() {
        return unique;
    }

    public void setUnique(Unique unique) {
        this.unique = unique;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getReleases() {
        return releases;
    }

    public void setReleases(List<String> releases) {
        this.releases = releases;
    }
}
