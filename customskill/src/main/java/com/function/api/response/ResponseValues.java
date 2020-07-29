package com.function.api.response;

import java.util.ArrayList;
import java.util.List;

/** 
 * A class for handling 
 * Custom Skill, Azure Functions reponse
 */
public class ResponseValues {
    public List<ResponseValue> values;

    public ResponseValues() {
        this.values = new ArrayList<>();
    }

    public void add(ResponseValue respValue) {
        this.values.add (new ResponseValue());
    }
}