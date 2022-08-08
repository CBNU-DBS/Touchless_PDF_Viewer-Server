package com.example.DBS.domain;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class FunctionConverter implements AttributeConverter<Function, String> {
    @Override
    public String convertToDatabaseColumn(Function attribute) {
        return attribute.getCode();
    }

    @Override
    public Function convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(Function.class).stream()
                .filter(e -> e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
