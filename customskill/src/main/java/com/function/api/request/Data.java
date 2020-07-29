package com.function.api.request;

import java.sql.Date;
import java.util.List;

/** 
 * A class for generating a request body interface
 * for custom skill Azure Functions
 */

public class Data {

    public String frequency;
    public Date published;

    // Getter Methods    
     public String getFrequency() {
       return frequency;
     }

     public Date getPublishDate() {
      return published;
    }
   
    // Setter Methods 
     public void setFrequency(String frequency ) {
       this.frequency = frequency;
     }
     
     public void setPublishDate(Date published) {
      this.published = published;
    }

}