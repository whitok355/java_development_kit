package service;

import java.time.LocalDateTime;

public class CurrentDate {

    public String getCurrentDateAndTime(){
        return String.format("[%s-%s-%s %s:%s:%s] ", String.valueOf(
                LocalDateTime.now().getYear()),
                String.valueOf(LocalDateTime.now().getMonthValue() < 10 ? "0" + LocalDateTime.now().getMonthValue(): LocalDateTime.now().getMonthValue()),
                String.valueOf(LocalDateTime.now().getDayOfMonth()),
                String.valueOf(LocalDateTime.now().getHour()),
                String.valueOf(LocalDateTime.now().getMinute()),
                String.valueOf(LocalDateTime.now().getSecond()));
    }
}
