package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyMaterial;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.List;

public class GeneralSearch implements Search<String> {
    private SearchLog searchLog = new SearchLog("General Search");

    public GeneralSearch() {}

    @Override
    public List<String> search(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(searchCards(text));
        results.addAll(searchHabits(text));
        results.addAll(searchTodos(text));
        results.addAll(searchMaterials(text));
        results.addAll(searchRegistries(text));

        logSearch(text);
        results.add("\nLogged in: " + searchLog.getLogName());
        return results;
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }

    // 🔽 Métodos auxiliares encapsulam a lógica de cada classe externa
    private List<String> searchCards(String text) {
        return CardManager.getCardManager().searchInCards(text);
    }

    private List<String> searchHabits(String text) {
        return HabitTracker.getHabitTracker().searchInHabits(text);
    }

    private List<String> searchTodos(String text) {
        return TodoTracker.getInstance().searchInTodos(text);
    }

    private List<String> searchMaterials(String text) {
        return StudyMaterial.getStudyMaterial().searchInMaterials(text);
    }

    private List<String> searchRegistries(String text) {
        return StudyTaskManager.getStudyTaskManager().searchInRegistries(text);
    }

    private void logSearch(String text) {
        searchLog.registerSearch(text);
    }
}
