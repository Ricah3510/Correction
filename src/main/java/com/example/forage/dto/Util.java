package com.example.forage.dto;

import java.time.*;
import java.util.Date;

import com.example.forage.model.Parametre;

public class Util {
    public static long difference(LocalDateTime date1, LocalDateTime date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        return java.time.Duration.between(date1, date2).toHours();
    }

    public static Duration calculDureeOuvree0(LocalDateTime start, LocalDateTime end, Parametre p) {

        if (start == null || end == null) return Duration.ZERO;

        LocalTime debutTravail = p.getHeureDebut(); // 08:00
        LocalTime finTravail = p.getHeureFin();     // 17:00

        Duration total = Duration.ZERO;

        LocalDate currentDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();

        while (!currentDate.isAfter(endDate)) {

            LocalDateTime debutJour = LocalDateTime.of(currentDate, debutTravail);
            LocalDateTime finJour = LocalDateTime.of(currentDate, finTravail);

            LocalDateTime realStart = start.isAfter(debutJour) ? start : debutJour;
            LocalDateTime realEnd = end.isBefore(finJour) ? end : finJour;

            if (realStart.isBefore(realEnd)) {
                total = total.plus(Duration.between(realStart, realEnd));
            }

            currentDate = currentDate.plusDays(1);

            start = LocalDateTime.of(currentDate, debutTravail);
        }

        return total;
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Duration calculDureeOuvree(LocalDateTime start, LocalDateTime end, Parametre p) {

        if (start == null || end == null) return Duration.ZERO;
    
        LocalTime debutTravail = p.getHeureDebut(); // 08:00
        LocalTime finTravail = p.getHeureFin();     // 17:00
    
        Duration total = Duration.ZERO;
    
        LocalDate currentDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();
    
        while (!currentDate.isAfter(endDate)) {
    
            DayOfWeek day = currentDate.getDayOfWeek();
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                currentDate = currentDate.plusDays(1);
                start = LocalDateTime.of(currentDate, debutTravail);
                continue;
            }
    
            LocalDateTime debutJour = LocalDateTime.of(currentDate, debutTravail);
            LocalDateTime finJour = LocalDateTime.of(currentDate, finTravail);
    
            LocalDateTime realStart = start.isAfter(debutJour) ? start : debutJour;
            LocalDateTime realEnd = end.isBefore(finJour) ? end : finJour;
    
            if (realStart.isBefore(realEnd)) {
                total = total.plus(Duration.between(realStart, realEnd));
            }
    
            currentDate = currentDate.plusDays(1);
    
            start = LocalDateTime.of(currentDate, debutTravail);
        }
    
        return total;
    }
}