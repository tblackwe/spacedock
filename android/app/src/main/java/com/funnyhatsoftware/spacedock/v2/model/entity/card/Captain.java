package com.funnyhatsoftware.spacedock.v2.model.entity.card;

import org.simpleframework.xml.Element;

/**
 * Created by Thomas on 10/25/2014.
 */
public class Captain extends Card {
    @Element(name = "Ability", required = false)
    private String ability;
    @Element(name = "Talent", required = false)
    private int talent;
}
