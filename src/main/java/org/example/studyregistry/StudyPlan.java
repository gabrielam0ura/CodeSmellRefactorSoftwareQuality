package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPlan extends Registry {
    private StudyObjective objective;
    private List<String> steps;

    public StudyPlan(String planName, StudyObjective objective, List<StudyMaterial> materials) {
        this.name = planName;
        this.objective = objective;
        this.steps = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Plan: " + name + ",\nObjective: " + objective.getDescription() + ",\nSteps: " + String.join(", ", steps);
    }

    public List<String> getSteps() {
        return steps;
    }

    public StudyObjective getObjective() {
        return objective;
    }

    public void assignObjective(StudyObjective objective) {
        this.objective = objective;
    }

    public void addSingleStep(String toAdd) {
        steps.add(toAdd);
    }

    // assignSteps agora recebe o objeto e usa método para obter a lista
    public void assignSteps(StudyStepsParameters params) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.steps = params.toStepsList(formatter);
    }

    // handleAssignSteps usa builder para criar objeto de parâmetros
    public void handleAssignSteps(List<String> stringProperties, Integer numberOfSteps, boolean isImportant,
                                  LocalDateTime startDate, LocalDateTime endDate) {
        StudyStepsParameters params = StudyStepsParameters.builder()
                .firstStep(stringProperties.get(0))
                .resetStudyMechanism(stringProperties.get(1))
                .consistentStep(stringProperties.get(2))
                .seasonalSteps(stringProperties.get(3))
                .basicSteps(stringProperties.get(4))
                .mainObjectiveTitle(stringProperties.get(5))
                .mainGoalTitle(stringProperties.get(6))
                .mainMaterialTopic(stringProperties.get(7))
                .mainTask(stringProperties.get(8))
                .numberOfSteps(numberOfSteps)
                .isImportant(isImportant)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        assignSteps(params);
    }

    // Classe encapsulando parâmetros com builder e método para lista formatada
    public static class StudyStepsParameters {
        private String firstStep;
        private String resetStudyMechanism;
        private String consistentStep;
        private String seasonalSteps;
        private String basicSteps;
        private String mainObjectiveTitle;
        private String mainGoalTitle;
        private String mainMaterialTopic;
        private String mainTask;
        private Integer numberOfSteps;
        private boolean isImportant;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        private StudyStepsParameters() {}

        public List<String> toStepsList(DateTimeFormatter formatter) {
            return new ArrayList<>(Arrays.asList(
                    firstStep,
                    resetStudyMechanism,
                    consistentStep,
                    seasonalSteps,
                    basicSteps,
                    "Number of steps: " + numberOfSteps,
                    "Is it important to you? " + isImportant,
                    startDate.format(formatter),
                    endDate.format(formatter),
                    mainObjectiveTitle,
                    mainGoalTitle,
                    mainMaterialTopic,
                    mainTask
            ));
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private final StudyStepsParameters params = new StudyStepsParameters();

            public Builder firstStep(String firstStep) {
                params.firstStep = firstStep;
                return this;
            }

            public Builder resetStudyMechanism(String resetStudyMechanism) {
                params.resetStudyMechanism = resetStudyMechanism;
                return this;
            }

            public Builder consistentStep(String consistentStep) {
                params.consistentStep = consistentStep;
                return this;
            }

            public Builder seasonalSteps(String seasonalSteps) {
                params.seasonalSteps = seasonalSteps;
                return this;
            }

            public Builder basicSteps(String basicSteps) {
                params.basicSteps = basicSteps;
                return this;
            }

            public Builder mainObjectiveTitle(String mainObjectiveTitle) {
                params.mainObjectiveTitle = mainObjectiveTitle;
                return this;
            }

            public Builder mainGoalTitle(String mainGoalTitle) {
                params.mainGoalTitle = mainGoalTitle;
                return this;
            }

            public Builder mainMaterialTopic(String mainMaterialTopic) {
                params.mainMaterialTopic = mainMaterialTopic;
                return this;
            }

            public Builder mainTask(String mainTask) {
                params.mainTask = mainTask;
                return this;
            }

            public Builder numberOfSteps(Integer numberOfSteps) {
                params.numberOfSteps = numberOfSteps;
                return this;
            }

            public Builder isImportant(boolean isImportant) {
                params.isImportant = isImportant;
                return this;
            }

            public Builder startDate(LocalDateTime startDate) {
                params.startDate = startDate;
                return this;
            }

            public Builder endDate(LocalDateTime endDate) {
                params.endDate = endDate;
                return this;
            }

            public StudyStepsParameters build() {
                return params;
            }
        }
    }
}
