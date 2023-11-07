package com.kyilmaz80.hotel.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {
    public static void executeStatement(String sqlString, String name) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String updateString = sqlString;
            PreparedStatement ps = connection.prepareStatement(updateString);
            ps.setString(1, name);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeStatement(String sqlString, int id) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String updateString = sqlString;
            PreparedStatement ps = connection.prepareStatement(updateString);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
