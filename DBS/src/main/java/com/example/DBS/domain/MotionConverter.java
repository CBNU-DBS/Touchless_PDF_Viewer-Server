package com.example.DBS.domain;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class MotionConverter implements AttributeConverter<Motion, String>
{
    @Override
    public String convertToDatabaseColumn(Motion attribute) {
        return attribute.getCode();
    }

    @Override
    public Motion convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(Motion.class).stream()
                .filter(e -> e.getCode().equals(dbData))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException());
    }
}
