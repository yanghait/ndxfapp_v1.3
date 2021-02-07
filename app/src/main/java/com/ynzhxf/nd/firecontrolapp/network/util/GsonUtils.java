package com.ynzhxf.nd.firecontrolapp.network.util;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GsonUtils {

    public static Gson gson;

    public static final TypeAdapter<BigDecimal> BIG_DECIMAL = new TypeAdapter<BigDecimal>() {
        @Override
        public BigDecimal read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return new BigDecimal(0);
            }
            try {
                String string = in.nextString();
                if (string.length() > 0)
                    return new BigDecimal(string);
                return null;
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public void write(JsonWriter out, BigDecimal value) throws IOException {
            out.value(value);
        }
    };

    public static final TypeAdapter<Map<String, Map<String, String>>> HASHMAP = new TypeAdapter<Map<String, Map<String, String>>>() {
        @Override
        public Map<String, Map<String, String>> read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            if (peek == JsonToken.STRING) {
                return new HashMap<>();
            }
            if (peek == JsonToken.BEGIN_OBJECT) {
                return null;
            }
            return null;
        }

        @Override
        public void write(JsonWriter out, Map value) throws IOException {
            HASHMAP.write(out, value);
        }
    };

}
