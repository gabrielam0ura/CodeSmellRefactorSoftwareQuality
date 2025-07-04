package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class StudyObjective extends Registry {
    private String title;
    private String description;
    private String topic;
    private Integer practicedDays;
    private LocalDateTime startDate;
    private Double duration;
    private String objectiveInOneLine;
    private String objectiveFullDescription;
    private String motivation;

    private Scanner scanner = new Scanner(System.in);

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return topic;
    }

    public Integer getPracticedDays() {
        return practicedDays;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Double getDuration() {
        return duration;
    }

    public String getObjectiveInOneLine() {
        return objectiveInOneLine;
    }

    public String getObjectiveFullDescription() {
        return objectiveFullDescription;
    }

    public String getMotivation() {
        return motivation;
    }

    @Override
    public String toString() {
        return "StudyObjective [title:" + title + ", description:" + description + (topic != null ? ", topic:" + topic : "")
                + (practicedDays != null ? ", practicedDays:" + practicedDays : "") + (duration != null ? ", duration:" + duration : "")
                + (objectiveInOneLine != null ? ", objective summary:" + objectiveInOneLine : "") + (objectiveFullDescription != null ? ", objective full description:" + objectiveFullDescription : "")
                + (motivation != null ? ", motivation:" + motivation : "") + "]";
    }

    public StudyObjective(String title, String description) {
        this.title = title;
        this.description = description;
        this.name = title;
    }

    public void handleSetRegistry(Integer id, String name, Integer priority, boolean isActive) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.isActive = isActive;
    }

    public void handleSetTextualInfo(String title, String description, String topic, String objectiveInOneLine, String objectiveFullDescription, String motivation) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.objectiveInOneLine = objectiveInOneLine;
        this.objectiveFullDescription = objectiveFullDescription;
        this.motivation = motivation;
    }

    public void handleSetTime(Integer practicedDays, int day, int month, int year, Double duration) {
        this.practicedDays = practicedDays;
        this.duration = duration;
        this.startDate = LocalDateTime.of(year, month, day, 0, 0);
    }

    public void handleSetObjective(StudyObjectiveData data) {
        handleSetRegistry(data.getId(), data.getName(), data.getPriority(), data.isActive());
        handleSetTextualInfo(
                data.getTitle(), data.getDescription(), data.getTopic(),
                data.getObjectiveInOneLine(), data.getObjectiveFullDescription(), data.getMotivation()
        );
        this.practicedDays = data.getPracticedDays();
        this.duration = data.getDuration();
        this.startDate = data.getStartDate();
    }

    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties, Double duration, boolean isActive) {
        StudyObjectiveData data = new StudyObjectiveData(
                intProperties.get(0), intProperties.get(1), intProperties.get(2),
                intProperties.get(3), intProperties.get(4), intProperties.get(5),
                stringProperties.get(0), stringProperties.get(1), stringProperties.get(2),
                stringProperties.get(3), stringProperties.get(4), stringProperties.get(5),
                stringProperties.get(6), duration, isActive
        );
        handleSetObjective(data);
        return data.getId();
    }

    private StudyObjectiveData readStudyObjectiveDataFromInput() {
        System.out.println("Type the following info: Integer id, Integer priority, Integer practicedDays, " +
                "int day, int month, int year, String name, String title, String description, String topic, " +
                "String objectiveInOneLine, String objectiveFullDescription, String motivation, Double duration, boolean isActive\n");

        Integer[] intFields = readIntegerFields();
        String[] stringFields = readStringFields();
        Double duration = readDouble("duration");
        boolean isActive = readBoolean("isActive");

        return new StudyObjectiveData(
                intFields[0], intFields[1], intFields[2],
                intFields[3], intFields[4], intFields[5],
                stringFields[0], stringFields[1], stringFields[2], stringFields[3],
                stringFields[4], stringFields[5], stringFields[6],
                duration, isActive
        );
    }

    private Integer[] readIntegerFields() {
        Integer id = readInteger("id");
        Integer priority = readInteger("priority");
        Integer practicedDays = readInteger("practicedDays");
        int day = readInt("day");
        int month = readInt("month");
        int year = readInt("year");
        return new Integer[]{id, priority, practicedDays, day, month, year};
    }

    private String[] readStringFields() {
        String name = readString("name");
        String title = readString("title");
        String description = readString("description");
        String topic = readString("topic");
        String objectiveInOneLine = readString("objectiveInOneLine");
        String objectiveFullDescription = readString("objectiveFullDescription");
        String motivation = readString("motivation");
        return new String[]{name, title, description, topic, objectiveInOneLine, objectiveFullDescription, motivation};
    }

    private Integer readInteger(String fieldName) {
        System.out.print("Enter " + fieldName + ": ");
        return Integer.parseInt(getInput());
    }

    private int readInt(String fieldName) {
        System.out.print("Enter " + fieldName + ": ");
        return Integer.parseInt(getInput());
    }

    private String readString(String fieldName) {
        System.out.print("Enter " + fieldName + ": ");
        return getInput();
    }

    private Double readDouble(String fieldName) {
        System.out.print("Enter " + fieldName + ": ");
        return Double.parseDouble(getInput());
    }

    private boolean readBoolean(String fieldName) {
        System.out.print("Enter " + fieldName + " (true/false): ");
        return Boolean.parseBoolean(getInput());
    }

    public void handleSetObjectiveFromInput() {
        handleMethodHeader("(Study Objective Edit)");
        StudyObjectiveData data = readStudyObjectiveDataFromInput();
        handleSetObjective(data);
    }

    private String getInput() {
        return scanner.nextLine();
    }

    private void handleMethodHeader(String header) {
        System.out.println(header);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
