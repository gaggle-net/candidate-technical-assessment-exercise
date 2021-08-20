package com.polymathus.gaggle.service;

import org.json.simple.JSONObject;

/**
 * The Search interface.
 */
public interface Search {
    public abstract JSONObject search(JSONObject searchCriteria);
}
