package com.taskmanager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public interface Repo {
    public ArrayList<Task> loadAll() throws SQLException;

    public int insert(Task task) throws SQLException;

    public int update(Task task) throws SQLException;

    public int delete(Task task) throws SQLException;

}
