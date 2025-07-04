package org.example.studyregistry;

import java.util.ArrayList;
import java.util.List;

public class StudyTaskManager {
    private static StudyTaskManager instance;
    private StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    List<Registry> registryList;
    List<String> weekResponsibilities = List.of();

    private StudyTaskManager(){
        this.registryList = new ArrayList<>();
    }

    public static StudyTaskManager getStudyTaskManager(){
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    public List<String> getWeekResponsibilities() {
        return weekResponsibilities;
    }

    public void setUpWeek(WeekSetupParameters params){
        this.weekResponsibilities = List.of(
                params.planName(),
                params.objectiveTitle(),
                params.objectiveDescription(),
                params.materialTopic(),
                params.materialFormat(),
                params.goal(),
                params.reminderTitle(),
                params.reminderDescription(),
                params.mainTaskTitle(),
                params.mainHabit(),
                params.mainCardStudy()
        );
    }

    public void handleSetUpWeek(List<String> stringProperties){
        WeekSetupParameters params = new WeekSetupParameters(
                stringProperties.get(0),
                stringProperties.get(1),
                stringProperties.get(2),
                stringProperties.get(3),
                stringProperties.get(4),
                stringProperties.get(5),
                stringProperties.get(6),
                stringProperties.get(7),
                stringProperties.get(8),
                stringProperties.get(9),
                stringProperties.get(10)
        );
        setUpWeek(params);
    }

    public void addRegistry(Registry registry){
        registryList.add(registry);
    }

    public void removeRegistry(Registry registry){
        registryList.remove(registry);
    }

    public List<Registry> getRegistryList(){
        return registryList;
    }

    public List<String> searchInRegistries(String text){
        List<String> response = new ArrayList<>();
        for(Registry registry : registryList){
            String name = registry.getName() != null ? registry.getName() : "";
            if (name.toLowerCase().contains(text.toLowerCase())){
                response.add(name);
            }
        }
        return response;
    }

    public record WeekSetupParameters(
            String planName,
            String objectiveTitle,
            String objectiveDescription,
            String materialTopic,
            String materialFormat,
            String goal,
            String reminderTitle,
            String reminderDescription,
            String mainTaskTitle,
            String mainHabit,
            String mainCardStudy
    ) {}
}
