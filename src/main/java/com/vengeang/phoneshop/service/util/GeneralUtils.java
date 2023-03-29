package com.vengeang.phoneshop.service.util;

import java.util.List;
import java.util.stream.Collectors;

public class GeneralUtils {
//    Convert list of string to integer
    public static List<Integer> convertStringToInteger(List<String> stringList){
        return stringList.stream()
                .map(s -> s.length())
                .collect(Collectors.toList());
    }
    public static List<Integer> getEvenNumber(List<Integer> list){
        return list.stream()
                .filter(x->x%2==0)
                .collect(Collectors.toList());
    }
}
