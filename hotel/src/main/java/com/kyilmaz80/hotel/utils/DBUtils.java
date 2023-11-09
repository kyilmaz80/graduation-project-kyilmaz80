package com.kyilmaz80.hotel.utils;

import com.kyilmaz80.hotel.ViewUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DBUtils {
    private static final Pattern COMMENT_PATTERN = Pattern.compile("–.*|/\\*(.|[\\r\\n])*?\\*/");

    public static void executeStatement(String sqlString, String name) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB connection problem!");
                return;
            }
            String updateString = sqlString;
            PreparedStatement ps = connection.prepareStatement(updateString);
            ps.setString(1, name);
            ps.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void executeStatement(String sqlString, String col1, Double col2 ) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB connection problem!");
                return;
            }
            String updateString = sqlString;
            PreparedStatement ps = connection.prepareStatement(updateString);
            ps.setString(1, col1);
            ps.setDouble(2, col2.doubleValue());
            ps.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void executeStatement(String sqlString, int id) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB connection problem!");
                return;
            }
            String updateString = sqlString;
            PreparedStatement ps = connection.prepareStatement(updateString);
            ps.setInt(1, id);
            ps.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getSelectResultSetFromTable(String sqlString, String searchString) {
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB Connection problem!");
                return null;
            }

            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setString(1, "%" + searchString + "%");
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getSelectResultSetFromTable(String sqlString, String searchString, String filterColumn) {
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB Connection problem!");
                return null;
            }

            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setString(1, "%" + searchString + "%");
            ps.setString(2, filterColumn);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getSelectResultSetFromTable(String sqlString) {
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB Connection problem!");
                return null;
            }

            PreparedStatement ps = connection.prepareStatement(sqlString);
            //ps.setString(1, "%" + searchString + "%");
            //ps.setString(2, filterColumn);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }


    //TODO: FUTURE USE SQL DB INIT
    //https://www.baeldung.com/java-run-sql-script
    static void executeBatchedSQL(String scriptFilePath, Connection connection, int batchSize) throws Exception {
        List<String> sqlStatements = parseSQLScript(scriptFilePath);
        executeSQLBatches(connection, sqlStatements, batchSize);
    }

    //https://www.baeldung.com/java-run-sql-script
    static List<String> parseSQLScript(String scriptFilePath) throws IOException {
        List<String> sqlStatements = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFilePath))) {
            StringBuilder currentStatement = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher commentMatcher = COMMENT_PATTERN.matcher(line);
                line = commentMatcher.replaceAll("");

                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                currentStatement.append(line).append(" ");

                if (line.endsWith(";")) {
                    sqlStatements.add(currentStatement.toString());
                    System.out.println(currentStatement.toString());
                    currentStatement.setLength(0);
                }
            }
        } catch (IOException e) {
            throw e;
        }
        return sqlStatements;
    }

    static void executeSQLBatches(Connection connection, List<String> sqlStatements, int batchSize)
            throws SQLException {
        int count = 0;
        Statement statement = connection.createStatement();

        for (String sql : sqlStatements) {
            statement.addBatch(sql);
            count++;

            if (count % batchSize == 0) {
                System.out.println("Executing batch");
                statement.executeBatch();
                statement.clearBatch();
            }
        }
        if (count % batchSize != 0) {
            statement.executeBatch();
        }
        connection.commit();
    }


}
