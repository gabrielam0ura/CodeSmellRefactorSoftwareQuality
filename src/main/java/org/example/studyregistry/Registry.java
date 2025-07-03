package org.example.studyregistry;

public abstract class Registry {
    Integer id;
    String name;
    Integer priority;
    boolean isActive;

    public Registry() {}

    public Registry(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPriority() {
        return priority;
    }

    public boolean isActive() {
        return isActive;
    }
}
