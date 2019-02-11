package jl.products.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.io.IOException;

public class PriceDeserializer extends StdDeserializer<String> {

    public PriceDeserializer() {
        this(null);
    }

    public PriceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public String deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String nowPriceSingle = "";
        if(node.getNodeType().equals(JsonNodeType.STRING))
            nowPriceSingle = node.asText();
        return nowPriceSingle;
    }
}