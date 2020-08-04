package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import com.function.api.request.*;
import com.function.api.response.*;
import com.function.service.FreshnessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

/**
 * Freshness calculation running as a HTTP Triggered Azure Function.
 */
public class Function {
    @FunctionName("FreshnessFunction")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Get JSON Body as string
        final String jsonBody =  request.getBody().orElse(null);

        context.getLogger().info("requestBody: " + jsonBody);

        try {
            Gson gsonParser = new GsonBuilder()
                                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                    .setPrettyPrinting()
                                    .serializeNulls()
                                    .create();

            // Parse JSON Body request to an Object 
            Values valueObject = gsonParser.fromJson(jsonBody, Values.class);
            List<Value> valueList = valueObject.values; 
            
            if (valueList == null) {
                return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass the object in the request body").build();
            } else {
                List<ResponseValue> values = new ArrayList<>();
                ResponseValues respvalues = new ResponseValues();
                FreshnessService freshnessService = new FreshnessService();

                // Check all array elements of Request Body
                for (Value value : valueList)
                {
                    // // Find Frequency code in Tags List
                    String docFrequency = value.getData().frequency;
                    if (docFrequency!=null) {                      
                        // print variables
                        context.getLogger().info(String.format("RecordId: [%s], Published: [%s], Frequency: [%s]", value.getRecordId(), value.getData().published, docFrequency));

                        // Decaying function calculation
                        double freshnessValue = freshnessService.getFreshnessValue(value.getData().published, docFrequency);

                        // Set Response JSON values
                        ResponseValue respValue = new ResponseValue();
                        respValue.setRecordId(value.getRecordId());
                        ResponseData dataObject = new ResponseData();
                        dataObject.freshness = freshnessValue;
                        respValue.setResponseData(dataObject);
                        respValue.setWarnings(null);
                        respValue.setErrors(null);
                        values.add(respValue);
                    }
                }

                // Return Response
                respvalues.values = values;
                return request.createResponseBuilder(HttpStatus.OK)
                              .header("Content-Type", "application/json")
                              .body(gsonParser.toJson(respvalues))
                              .build();
            }
            
        } catch (Exception e) {
            // Handle error and return HTTP response
            context.getLogger().log(Level.SEVERE, e.getMessage(), jsonBody);
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body(e).build();
        }
    }
}
