package org.example.studysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLog {
    private String logName;
    private List<String> searchHistory;
    private Map<String, Integer> searchCount;
    private boolean isLocked;
    private int numUsages;

    public SearchLog(String logName) {
        this.logName = logName;
        this.searchHistory = new ArrayList<>();
        this.searchCount = new HashMap<>();
        this.isLocked = false;
        this.numUsages = 0;
    }

    public String getFormattedLog() {
        String response = getLogName() + " was used: " + getNumUsages() + " times\nSearch Log\n";
        response += String.join(", ", getSearchHistory());
        return response;
    }

    public void addSearchHistory(String newSearch) {
        searchHistory.add(newSearch);
    }

    public void incrementUsage() {
        numUsages++;
    }

    public void withLock(boolean locked) {
        this.isLocked = locked;
    }

    public void withSearchCount(Map<String, Integer> newSearchCount) {
        this.searchCount = newSearchCount;
    }

    public void registerSearch(String text) {
        addSearchHistory(text);
        incrementUsage();
    }

    public String getLogName() {
        return logName;
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }

    public int getNumUsages() {
        return numUsages;
    }

    public String logSearch(String text) {
        registerSearch(text);
        return "\nLogged in: " + getLogName();
    }
}
