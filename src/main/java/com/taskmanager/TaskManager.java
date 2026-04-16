package com.taskmanager;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class TaskManager {
    private List<Task> tasks;
    private Repo repository;

    // public TaskManager()throws IOException{
    // json_file = new File("/home/alichliyah/IdeaProjects/Task
    // Tracker/src/file.json");
    // objectMapper = new ObjectMapper();
    // if (json_file.exists() && json_file.length()>0){
    // tasks= objectMapper.readValue(json_file,new TypeReference<List<Task>>(){});
    // syncIdCounter();
    // }
    // else {
    // tasks = new ArrayList<Task>();
    // }
    // }
    public TaskManager(Repo repository) throws SQLException {
        this.repository = repository;
        this.tasks = repository.loadAll();
        // syncIdCounter();
    }

    public void add(Task task) throws SQLException {
        repository.insert(task);
    }

    public void mark(int id, String status) throws SQLException {
        status = status.toUpperCase();
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setUpdatedAt(LocalDateTime.now().toString());
                task.setStatus(Status.valueOf(status));
                int code = repository.update(task);
                return;
            }
        }
    }

    public void updateTaskbyId(int id, String description) throws SQLException {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setUpdatedAt(LocalDateTime.now().toString());
                task.setDescription(description);
                int code = repository.update(task);
                return;
            }
        }
    }

    // method not needed anymore
    // private void syncIdCounter() {
    // int max = 0;
    // for (Task task : tasks) {
    // if (task.getId() > max) {
    // max = task.getId();
    // }
    // }
    // Task.setId_counter(max);
    // }

    public void deleteTask(int id) throws SQLException {
        for (Task task : tasks) {
            if (task.getId() == id) {
                repository.delete(task);
                return;
            }
        }
    }

    public void listAll() {
        for (Task task : tasks) {
            System.out.println(task.toString() + "\n");
        }
    }

    public void listByStatus(String status) throws IllegalArgumentException {
        boolean isEmpty = true;
        status = status.toUpperCase();
        Status targeted_status = Status.valueOf(status);
        System.out.print("tasks with the status: " + targeted_status);
        for (Task task : tasks) {
            if (task.getStatus().equals(targeted_status)) {
                System.out.println("");// for a cleaner look
                System.out.println(task.toString());
                isEmpty = false;
            }
        }
        if (isEmpty) {
            System.out.println(": 0 (there's no tasks with the status: " + targeted_status + ")");
        }
    }
}
