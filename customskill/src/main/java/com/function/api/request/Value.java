package com.function.api.request;

/** 
 * A class for generating a request body interface
 * for custom skill Azure Functions
 */
public class Value{

  private String recordId;
  Data data;

 // Getter Methods 
  public String getRecordId() {
    return recordId;
  }

  public Data getData() {
    return data;
  }

 // Setter Methods 
  public void setRecordId( String recordId ) {
    this.recordId = recordId;
  }

  public void setData( Data dataObject ) {
    this.data = dataObject;
  }

}