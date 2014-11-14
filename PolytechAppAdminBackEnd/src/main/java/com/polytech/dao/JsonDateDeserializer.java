/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polytech.dao;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.stereotype.Component;

/**
 * Used to serialize Java.util.Date, which is not a common JSON
 * type, so we have to create a custom serialize method;.
 *
 * @author Loiane Groner
 * http://loianegroner.com (English)
 * http://loiane.com (Portuguese)
 */
@Component
public class JsonDateDeserializer extends JsonDeserializer<Date>{

	


    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext dc) throws IOException, JsonProcessingException {
        		
 if(jsonparser.getText() != null && !jsonparser.getText().trim().equals(""))
                {
                        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                        String date = jsonparser.getText();
                        try {
                                return format.parse(date);
                        } catch (ParseException e) {
                                throw new RuntimeException(e);
                        }
                }
                else
                {
                        return null;
                }
		

	
    }

}