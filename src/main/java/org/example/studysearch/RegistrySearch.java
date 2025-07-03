package org.example.studysearch;

import java.util.List;

public class RegistrySearch implements Search<String> {

    private final SearchLog searchLog = new SearchLog("Registry Search");
    private final RegistrySearchHandler searchHandler = new RegistrySearchHandler();

    @Override
    public List<String> search(String text) {
        List<String> results = searchHandler.performSearch(text);
        searchLog.addSearchHistory(text);
        searchLog.incrementUsage();
        results.add("\nLogged in: " + searchLog.getLogName());
        return results;
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}
