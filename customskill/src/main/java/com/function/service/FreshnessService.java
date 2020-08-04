package com.function.service;

import java.util.Date;
import java.util.HashMap;
import org.joda.time.Days;
import org.joda.time.DateTime;

/* A class for generating Freshness value */
public class FreshnessService {
    public double getFreshnessValue(Date publishedDate, String docFrequency) {  
        // Key Value pairs for Frequency per document type 
        HashMap<String, Integer> freq = new HashMap<String, Integer>();  
        freq.put("Daily", 1);
        freq.put("Weekly", 7);
        freq.put("Bi-Weekly", 14);
        freq.put("Monthly", 30);
        freq.put("Quarterly", 90);
        freq.put("Yearly", 365);

        // In case we'll retrieve any key which is not available in our map
        Integer DEFAULT = 0;
        Integer documentFrequency=  freq.getOrDefault(docFrequency, DEFAULT);
        double freshnessResult = decayingFunction(publishedDate,documentFrequency);
        return freshnessResult;
    }

    public double decayingFunction(Date publishDate, int documentFrequency){
        int numberOfDaysOld = Days.daysBetween(new DateTime(publishDate).toLocalDate(),DateTime.now().toLocalDate()).getDays();
        double value = 1.0 - ((double)numberOfDaysOld / (double)documentFrequency);
        if (value<0){
            return 0;
        }
        else{
            return value;
        }
    } 
}