package com.function.api.request;

import java.util.List;

/** 
 * A class for generating a request body interface for 
 * Azure Cognitive Search Custom Skill integration
 * This Values class contains List<Value> objects
 * We can send multiple queries in same Request Body using Values
 */
public class Values{
    public List<Value> values;
}