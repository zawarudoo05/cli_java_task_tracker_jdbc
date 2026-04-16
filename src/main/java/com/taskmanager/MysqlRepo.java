package com.taskmanager;

import java.sql.*;
import java.util.ArrayList;

public class MysqlRepo implements Repo {

    private String LOADING_QUERY = "SELECT * FROM tasks";
    private String UPDATING_QUERY = "UPDATE tasks SET description = ?,status = ?, updatedAt= ? WHERE id = ?";
    private String INSERTING_QUERY = "INSERT INTO tasks (status,description,createdAt,updatedAt) VALUES(?,?,?,?)";
    private String DELETING_QUERY = "DELETE FROM tasks WHERE id = ?";

    public MysqlRepo() {
    };

    @Override
    public ArrayList<Task> loadAll() throws SQLException {
        ArrayList<Task> task_list = new ArrayList<Task>();
        try (
                Connection cn = Database.getConnection();
                Statement stm = cn.createStatement();
                ResultSet resultSet = stm.executeQuery(LOADING_QUERY);) {
            // looping throught the resultSet, and stocking each record in a new task object
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                task_list.add(new Task(id, description));

            }
        }

        return task_list;
    }

    @Override
    public int insert(Task task) throws SQLException {
        int result;
        try (
                Connection cn = Database.getConnection();
                PreparedStatement stm = cn.prepareStatement(INSERTING_QUERY);) {

            stm.setString(1, task.getStatus().getLabel());
            stm.setString(2, task.getDescription());
            stm.setString(3, task.getCreatedAt());
            stm.setString(4, task.getUpdatedAt());

            result = stm.executeUpdate();
        }
        return result;
    }

    @Override
    public int update(Task task) throws SQLException {
        int result;
        try (
                Connection cn = Database.getConnection();
                PreparedStatement stm = cn.prepareStatement(UPDATING_QUERY);) {

            stm.setString(1, task.getDescription());
            stm.setString(2, task.getStatus().getLabel());
            stm.setString(3, task.getUpdatedAt());
            stm.setInt(4, task.getId());

            result = stm.executeUpdate();
        }

        return result;
    }

    @Override
    public int delete(Task task) throws SQLException {
        int result;

        try (
                Connection cn = Database.getConnection();
                PreparedStatement stm = cn.prepareStatement(DELETING_QUERY);) {
            stm.setInt(1, task.getId());
            result = stm.executeUpdate();
        }
        return result;
    }
}
