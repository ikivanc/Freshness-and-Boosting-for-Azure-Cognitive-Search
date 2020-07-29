package com.function.api.response;
/** 
 * A class for handling 
 * Custom Skill, Azure Functions reponse
 */
public class ResponseData {

    public Double freshness;

    // Getter Methods    
     public Double getFreshness() {
      return freshness;
    }
   
    // Setter Methods 
     public void setFreshness( Double freshness ) {
       this.freshness = freshness;
     }
}