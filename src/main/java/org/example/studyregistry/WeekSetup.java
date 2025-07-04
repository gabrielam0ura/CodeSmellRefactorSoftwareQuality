package org.example.studyregistry;

import org.example.studyregistry.StudyObjective;
import org.example.studyregistry.StudyPlan;
import org.example.studyregistry.StudyMaterial;

import java.util.ArrayList;
import java.util.List;

public class WeekSetup {
    private final StudyPlan studyPlan;
    private final StudyMaterial studyMaterial;
    private final String mainTask;
    private final String mainHabit;
    private final String mainCard;

    public WeekSetup(StudyPlan studyPlan,
                     StudyMaterial studyMaterial,
                     String mainTask,
                     String mainHabit,
                     String mainCard) {
        this.studyPlan = studyPlan;
        this.studyMaterial = studyMaterial;
        this.mainTask = mainTask;
        this.mainHabit = mainHabit;
        this.mainCard = mainCard;
    }
    public List<String> generateWeekResponsibilities() {
        List<String> list = new ArrayList<>();
        addPlanInfo(list);
        addObjectiveInfo(list);
        addPlanSteps(list);
        addStudyMaterials(list);
        addMainElements(list);
        return list;
    }

    private void addPlanInfo(List<String> list) {
        list.add("Plan: " + studyPlan.getName());
    }

    private void addObjectiveInfo(List<String> list) {
        StudyObjective obj = studyPlan.getObjective();
        list.add("Objective: " + obj.getTitle());
        list.add("Topic: " + obj.getTopic());
        list.add("Motivation: " + obj.getMotivation());
    }

    private void addPlanSteps(List<String> list) {
        list.addAll(studyPlan.getSteps());
    }

    private void addStudyMaterials(List<String> list) {
        StudyObjective obj = studyPlan.getObjective();
        String topic = obj.getTopic() != null ? obj.getTopic() : "";
        list.addAll(studyMaterial.searchInMaterials(topic));
    }

    private void addMainElements(List<String> list) {
        list.add("Main Task: " + mainTask);
        list.add("Main Habit: " + mainHabit);
        list.add("Main Card: " + mainCard);
    }
}
