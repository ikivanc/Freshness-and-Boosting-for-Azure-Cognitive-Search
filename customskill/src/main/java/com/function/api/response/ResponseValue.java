package com.function.api.response;

/** 
 * A class for handling 
 * Custom Skill, Azure Functions reponse
 */
public class ResponseValue {
    private String recordId;
    private ResponseData data;
    private String errors = null;
    private String warnings = null;


    // Getter Methods 
    public String getRecordId() {
        return recordId;
    }

    public ResponseData getResponseData() {
        return data;
      }

    public String getErrors() {
        return errors;
    }

    public String getWarnings() {
        return warnings;
    }

    // Setter Methods 
    public void setRecordId( String recordId ) {
        this.recordId = recordId;
    }

    public void setResponseData(ResponseData dataObject ) {
        this.data = dataObject;
      }
    
    public void setErrors( String errors ) {
        this.errors = errors;
    }

    public void setWarnings( String warnings ) {
        this.warnings = warnings;
    }
      
}