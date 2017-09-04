package com.sellf.exerciseconvertini.utils;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

import java.lang.reflect.Type;

import static org.joda.time.LocalDateTime.parse;

public class DateTypeAdapter implements JsonDeserializer<LocalDateTime>,
        JsonSerializer<LocalDateTime> {

    public JsonElement serialize(LocalDateTime dateTime, Type type, JsonSerializationContext context) {

        JsonPrimitive jsonPrimitive;
        try {
            jsonPrimitive = new JsonPrimitive(dateTime.toString("YYYY-MM-dd HH:mm:ss.SSSSSS"));
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrimitive = new JsonPrimitive(dateTime.toString("HH:mm"));
        }

        return jsonPrimitive;
    }

    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {


        DateTimeParser[] parsers = {
                DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss.SSSSSS").getParser(),
                DateTimeFormat.forPattern("HH:mm").getParser()};
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().append(null, parsers).toFormatter();

        return parse(json.getAsString(), formatter);
    }

}