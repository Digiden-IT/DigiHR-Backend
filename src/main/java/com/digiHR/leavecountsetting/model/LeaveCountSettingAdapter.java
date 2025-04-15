/*package com.digiHR.leavecountsetting.model;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonDeserializationContext;
import java.lang.reflect.Type;

public class LeaveCountSettingAdapter implements JsonSerializer<LeaveCountSetting>, JsonDeserializer<LeaveCountSetting> {

    @Override
    public JsonElement serialize( LeaveCountSetting setting, Type typeOfSrc, JsonSerializationContext context ) {
        return new JsonPrimitive( setting.getTotalLeave() );
    }

    @Override
    public LeaveCountSetting deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
        LeaveCountSetting setting = new LeaveCountSetting();
        setting.setTotalLeave( json.getAsString() );

        return setting;
    }
}*/
