package com.example.forage.dto;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;

@Converter(autoApply = false)
public class DurationConverter implements AttributeConverter<Duration, String> {

    @Override
    public String convertToDatabaseColumn(Duration duration) {
        if (duration == null) return null;

        long seconds = duration.getSeconds();
        return seconds + " seconds";
    }

    @Override
    public Duration convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        String[] parts = dbData.split(" ");
        long seconds = Long.parseLong(parts[0]);

        return Duration.ofSeconds(seconds);
    }
}