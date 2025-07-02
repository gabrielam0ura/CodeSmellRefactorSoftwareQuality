package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class TodoTracker {
    private List<ToDo> toDos = new ArrayList<>();
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;
    private static TodoTracker instance;


    private TodoTracker() {
        this.tracker = new HashMap<>();
        this.toDos = new ArrayList<>();
        this.nextId = 1;
    }

    public static TodoTracker getInstance() {
        if (instance == null) {
            instance = new TodoTracker();
        }
        return instance;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (ToDo toDo : toDos) {
            str.append(formatToDoWithTimestamps(toDo));
        }
        String response = str.toString();
        return response.isEmpty() ? "No ToDos found" : response;
    }

    private String formatToDoWithTimestamps(ToDo toDo) {
        StringBuilder builder = new StringBuilder();
        builder.append(toDo.toString()).append("\n");

        List<LocalDateTime> todosDate = tracker.get(toDo.getId());
        if (todosDate == null) {
            builder.append("No tracks found\n");
        } else {
            builder.append(formatTimestamps(todosDate));
        }

        return builder.toString();
    }

    private String formatTimestamps(List<LocalDateTime> timestamps) {
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (LocalDateTime ldt : timestamps) {
            builder.append(formatter.format(ldt)).append("\n");
        }
        return builder.toString();
    }

    public void addToDoExecutionTime(Integer id){
        List<LocalDateTime> et = tracker.computeIfAbsent(id, k -> new ArrayList<>());
        LocalDateTime now = LocalDateTime.now();
        et.add(now);
    }

    public List<ToDo> getToDos() {
        return toDos;
    }

    public ToDo getToDoById(Integer id) {
        for (ToDo toDo : toDos) {
            if (toDo.getId() == id) {
                return toDo;
            }
        }
        return null;
    }

    public Integer addToDo(String title, String description, Integer priority) {
        ToDo toAdd = new ToDo.Builder()
                .id(nextId)
                .title(title)
                .description(description)
                .priority(priority)
                .build();
        nextId++;
        this.toDos.add(toAdd);
        return toAdd.getId();
    }

    public void removeToDo(Integer id) {
        toDos.removeIf(toDo -> toDo.getId() == id);
    }

    public List<ToDo> sortTodosByPriority() {
        List<ToDo> sortedToDos = new ArrayList<>(toDos);
        sortedToDos.sort(Comparator.comparingInt(ToDo::getPriority));
        return sortedToDos;
    }

    public List<String> searchInTodos(String search) {
        List<String> todos = new ArrayList<>();
        for (ToDo toDo : toDos) {
            if (toDo.getTitle().toLowerCase().contains(search.toLowerCase()) || toDo.getDescription().toLowerCase().contains(search.toLowerCase())) {
                todos.add(toDo.toString());
            }
        }
        return todos;
    }


}
