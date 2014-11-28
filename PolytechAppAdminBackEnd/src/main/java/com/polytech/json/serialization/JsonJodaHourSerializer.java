package com.polytech.json.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Romain on 26/11/2014.
 */
public class JsonJodaHourSerializer extends JsonSerializer<DateTime> {
    @Override
    public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");

        jsonGenerator.writeString(formatter.withLocale(Locale.FRENCH).print(dateTime));
    }
}
