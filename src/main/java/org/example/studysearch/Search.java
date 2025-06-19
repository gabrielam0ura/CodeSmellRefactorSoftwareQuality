package org.example.studysearch;

import java.util.List;

public interface Search<T> {
    public List<T> search(String text);

}
