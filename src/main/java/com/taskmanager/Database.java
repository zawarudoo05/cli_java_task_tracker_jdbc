package com.taskmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

import io.github.cdimascio.dotenv.Dotenv;

class Database {
    private static MysqlDataSource mysqldatasource;
    static {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("DB_JDBC_URL");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        mysqldatasource = new MysqlDataSource();
        mysqldatasource.setUrl(url);
        mysqldatasource.setUser(user);
        mysqldatasource.setPassword(password);
    }

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = mysqldatasource.getConnection();
        return connection;

    }

    // public static void close(Connection cn) throws SQLException {
    // cn.close();
    // }

    // public static void close(Connection cn, Statement stm) throws SQLException {
    // stm.close();
    // cn.close();
    // }

    // public static void close(Connection cn, PreparedStatement stm) throws
    // SQLException {
    // stm.close();
    // cn.close();
    // }

    // public static void close(Connection cn, PreparedStatement stm, ResultSet
    // rSet) throws SQLException {
    // rSet.close();
    // stm.close();
    // cn.close();
    // }

    // public static void close(Connection cn, Statement stm, ResultSet rSet) throws
    // SQLException {
    // rSet.close();
    // stm.close();
    // cn.close();
    // }
}
