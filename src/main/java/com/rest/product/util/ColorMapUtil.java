package com.rest.product.util;

import java.util.HashMap;

public class ColorMapUtil {

    private static HashMap<String,String> colorMap =new HashMap<>();

    public static HashMap<String,String> getColorMap(){
        colorMap.put("red","#FF0000");
        colorMap.put("green","#339900");
        return colorMap;
    }
}
