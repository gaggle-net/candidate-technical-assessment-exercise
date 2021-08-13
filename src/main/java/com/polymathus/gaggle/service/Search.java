package com.polymathus.gaggle.service;

import org.json.simple.JSONObject;

public interface Search {
    public abstract JSONObject search(JSONObject searchCriteria);      //@todo: to think through:  service in/out is JSON; where/when do we start using Java specific ojects
}
