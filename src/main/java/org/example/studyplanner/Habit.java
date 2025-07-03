package org.example.studyplanner;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class Habit implements PlannerMaterial{
    private Integer id;
    private Boolean isConcluded;
    private String name;
    private String motivation;
    private LocalTime dailyDedicationTime;
    private LocalDateTime startDate;

    Habit(String name, String motivation, LocalTime dailyDedicationTime, Integer id, LocalDateTime startDate, Boolean concluded) {
        this.isConcluded = concluded;
        this.id = id;
        this.name = name;
        this.motivation = motivation;
        this.dailyDedicationTime = dailyDedicationTime;
        this.startDate = startDate;
    }

    Habit(String name, String motivation, Integer id){
        this.isConcluded = false;
        this.id = id;
        this.name = name;
        this.motivation = motivation;
    }

    public String formatWithRecords(List<LocalDateTime> records, Function<LocalDateTime, String> dateFormatter) {
        StringBuilder response = new StringBuilder();
        response.append("[ Habit: ")
                .append(this.name)
                .append(". Records: ");

        for (LocalDateTime record : records) {
            response.append(dateFormatter.apply(record)).append(", ");
        }

        if (!records.isEmpty()) {
            response.setLength(response.length() - 2); // remove ", "
        }

        response.append("]\n");
        return response.toString();
    }

    @Override
    public String toString() {
        String status = isConcluded ? "Concluded" : "Not Concluded";
        return MessageFormat.format("[({3}) Habit {0}: {1}, {2}]", id, name, motivation, status);
    }

    public boolean getIsConcluded() {
        return isConcluded;
    }
    public void setIsConcluded(Boolean isConcluded) {
        this.isConcluded = isConcluded;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMotivation() {
        return motivation;
    }
    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public LocalTime getDailyDedicationTime() {
        return dailyDedicationTime;
    }

    public void increaseDailyDedicationTime(Integer minutes) {
        this.dailyDedicationTime = dailyDedicationTime.plusMinutes(minutes);
    }
    public void decreaseDailyDedicationTime(Integer minutes) {
        this.dailyDedicationTime = dailyDedicationTime.minusMinutes(minutes);
    }
    public long getDaysDifference (){
        LocalDateTime ldt = LocalDateTime.now();
        Duration duration = Duration.between(this.startDate, ldt);
        return duration.toDays();
    }

}
