package com.kyilmaz80.hotel.utils;

import com.kyilmaz80.hotel.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DBUtils {
    private static final Pattern COMMENT_PATTERN = Pattern.compile("–.*|/\\*(.|[\\r\\n])*?\\*/");

    public static void executeStatement(String sqlString, String name) {
        Connection connection;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB connection problem!");
                return;
            }
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setString(1, name);
            ps.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void executeStatement(String sqlString, String col1, Double col2) {
        Connection connection;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB connection problem!");
                return;
            }
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setString(1, col1);
            ps.setDouble(2, col2);
            ps.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void executeStatement(String sqlString, LocalDateTime col1, Integer col2) {
        Connection connection;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB connection problem!");
                return;
            }
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setObject(1, col1.toString());
            ps.setInt (2, col2);
            ps.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void executeStatement(String sqlString, int id) {
        Connection connection;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB connection problem!");
                return;
            }
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setInt(1, id);
            ps.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeStatement(String sqlString, Map<String,Object> columnsMap ) {
        Connection connection;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB connection problem!");
                return;
            }
            PreparedStatement ps = connection.prepareStatement(sqlString);
            // ps.setInt(1, id);
            int parameterIndex = 1;
            for(Map.Entry<String,Object> entry : columnsMap.entrySet() ) {
                System.out.println( "entry.getKey: " +  entry.getKey() + " entry.getValue() : " + entry.getValue());
                ps.setObject(parameterIndex++, entry.getValue());
            }

            ps.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet getSelectResultSetFromTable(String sqlString, String searchString) {
        Connection connection;
        ResultSet rs;
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
        Connection connection;
        ResultSet rs;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB Connection problem!");
                return null;
            }

            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setString(1, searchString);
            ps.setString(2, filterColumn);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getSelectResultSetFromTable(String sqlString, LocalDate d1, LocalDate d2) {
        Connection connection;
        ResultSet rs;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB Connection problem!");
                return null;
            }

            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setString(1, d1.toString());
            ps.setString(2, d2.toString());
            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getSelectResultSetFromTable(String sqlString, Map<String, String> criteria) {
        Connection connection;
        ResultSet rs;
        try {
            connection = JDBCUtils.getConnection();
            if (connection == null) {
                ViewUtils.showAlert("DB Connection problem!");
                return null;
            }

            PreparedStatement ps = connection.prepareStatement(sqlString);
            System.out.println("Query in resultset: " + sqlString);

            int parameterIndex = 1;

            for (Map.Entry<String, String> entry : criteria.entrySet()) {
                if (parameterIndex == 1) {
                    System.out.println("setting param " + parameterIndex + " " + entry.getValue());
                    ps.setObject(parameterIndex++, entry.getValue());
                } else {
                    System.out.println("setting param " + parameterIndex + " " + entry.getValue());
                    ps.setObject(parameterIndex++, entry.getValue());
                }
            }

            rs = ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getSelectResultSetFromTable(String sqlString) {
        Connection connection;
        ResultSet rs;
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

    public ObservableList<Object> selectEntityList(String columnsStr, String tableName) {
        ObservableList<Object> newList = FXCollections.observableArrayList();
        String query = "SELECT %s FROM %s";
        String className;

        query = String.format(query, columnsStr, tableName);

        //String sqlString = "SELECT * FROM Room WHERE ? = ?";
        String sqlString = query;


        ResultSet rs = new DBUtils().getSelectResultSetFromTable(sqlString);

        if (rs == null) {
            System.out.println(JDBCUtils.error);
            return null;
        }

        try {
            while (rs.next()) {
                className = "com.kyilmaz80.hotel.models." + tableName;
                Class<?> clazz;
                Field[] fields;
                Object entity = null;
                try {
                    // Load the class
                    clazz = Class.forName(className);
                    entity = clazz.getDeclaredConstructor().newInstance();
                    fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        String fieldName = field.getName();
                        Object value = rs.getObject(fieldName);
                        String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Method setter = clazz.getMethod(setterName, field.getType());
                        System.out.println("invoking: " + setterName);
                        setter.invoke(entity, value);
                    }

                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                         IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (entity != null) {
                    newList.add(entity);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return newList;

    }


    public ObservableList<Object> selectEntityListFilter(Map<String, Pair<String, String>> columnsMap, String tableName) {
        ObservableList<Object> newList = FXCollections.observableArrayList();
        //String query = "SELECT * FROM %s WHERE %s %s ?";
        String query = "SELECT * FROM %s WHERE %s %s %s";
        String defaultOperator = " = ";

        // <column_name, column_value>
        Map<String, String> columnsMap2 = new HashMap<>();
        int i = 0;
        for (Map.Entry<String, Pair<String, String>> entry : columnsMap.entrySet()) {
            Pair<String, String> columnInfo = entry.getValue();
            String columnValue = columnInfo.getKey();
            String operator = columnInfo.getValue().equals("LIKE") ? " LIKE " : defaultOperator;
            operator = columnInfo.getValue().equals("BETWEEN") ? " BETWEEN " : operator;
            columnsMap2.put(entry.getKey(), columnValue);

            // Use different placeholders for LIKE operator
            String placeholder = columnInfo.getValue().equals("LIKE") ? "CONCAT('%', ?, '%')": "?";

            if (i == 0) {
                query = String.format(query, tableName, StringUtils.filterStr(entry.getKey()), operator, placeholder);
            } else {
                // Use AND directly in the format string
                query = String.format("%s AND %s %s %s", query, StringUtils.filterStr(entry.getKey()), operator, placeholder);
            }
            i++;

        }

        //String sqlString = "SELECT * FROM Room WHERE ? = ?";
        String sqlString = query;
        System.out.println("QUERY: "+ query);

        /*
        https://stackoverflow.com/questions/3135973/variable-column-names-using-prepared-statements
        This indicates a bad DB design. The user shouldn't need to know about the column names. Create
        a real DB column which holds those "column names" and store the data along it instead.
        And any way, no, you cannot set column names as PreparedStatement values. You can only set
        column values as PreparedStatement values
        If you'd like to continue in this direction, you need to sanitize the column names (to avoid SQL Injection)
        and concatenate/build the SQL string yourself. Quote the separate column names and use String#replace() to
        escape the same quote inside the column name.
         */

        ResultSet rs = getSelectResultSetFromTable(sqlString, columnsMap2);

        if (rs == null) {
            System.out.println(JDBCUtils.error);
            return null;
        }

        try {
            while (rs.next()) {
                String className = "com.kyilmaz80.hotel.models." + tableName;
                Class<?> clazz = null;
                Field[] fields;
                Object entity = null;
                try {
                    // Load the class
                    clazz = Class.forName(className);
                    entity = clazz.getDeclaredConstructor().newInstance();
                    fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        String fieldName = field.getName();
                        Object value = rs.getObject(fieldName);
                        String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Method setter = clazz.getMethod(setterName, field.getType());
                        setter.invoke(entity, value);
                    }

                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                         IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (entity != null) {
                    newList.add(entity);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //rooms = newList;
        return newList;
    }


    public ObservableList<Object> selectEntityListOrFilter(Map<String, Pair<String, String>> columnsMap, String tableName) {
        ObservableList<Object> newList = FXCollections.observableArrayList();
        //String query = "SELECT * FROM %s WHERE %s %s ?";
        String query = "SELECT * FROM %s WHERE %s %s %s";
        String defaultOperator = " = ";

        // <column_name, column_value>
        Map<String, String> columnsMap2 = new HashMap<>();
        int i = 0;
        for (Map.Entry<String, Pair<String, String>> entry : columnsMap.entrySet()) {
            Pair<String, String> columnInfo = entry.getValue();
            String columnValue = columnInfo.getKey();
            String operator = columnInfo.getValue().equals("LIKE") ? " LIKE " : defaultOperator;
            operator = columnInfo.getValue().equals("BETWEEN") ? " BETWEEN " : operator;
            columnsMap2.put(entry.getKey(), columnValue);

            // Use different placeholders for LIKE operator
            String placeholder = columnInfo.getValue().equals("LIKE") ? "CONCAT('%', ?, '%')": "?";

            if (i == 0) {
                query = String.format(query, tableName, StringUtils.filterStr(entry.getKey()), operator, placeholder);
            } else {
                // Use AND directly in the format string
                query = String.format("%s OR %s %s %s", query, StringUtils.filterStr(entry.getKey()), operator, placeholder);
            }
            i++;

        }

        //String sqlString = "SELECT * FROM Room WHERE ? = ?";
        String sqlString = query;
        System.out.println("QUERY: "+ query);

        /*
        https://stackoverflow.com/questions/3135973/variable-column-names-using-prepared-statements
        This indicates a bad DB design. The user shouldn't need to know about the column names. Create
        a real DB column which holds those "column names" and store the data along it instead.
        And any way, no, you cannot set column names as PreparedStatement values. You can only set
        column values as PreparedStatement values
        If you'd like to continue in this direction, you need to sanitize the column names (to avoid SQL Injection)
        and concatenate/build the SQL string yourself. Quote the separate column names and use String#replace() to
        escape the same quote inside the column name.
         */

        ResultSet rs = getSelectResultSetFromTable(sqlString, columnsMap2);

        if (rs == null) {
            System.out.println(JDBCUtils.error);
            return null;
        }

        try {
            while (rs.next()) {
                String className = "com.kyilmaz80.hotel.models." + tableName;
                Class<?> clazz = null;
                Field[] fields;
                Object entity = null;
                try {
                    // Load the class
                    clazz = Class.forName(className);
                    entity = clazz.getDeclaredConstructor().newInstance();
                    fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        String fieldName = field.getName();
                        Object value = rs.getObject(fieldName);
                        String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Method setter = clazz.getMethod(setterName, field.getType());
                        setter.invoke(entity, value);
                    }

                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                         IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (entity != null) {
                    newList.add(entity);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //rooms = newList;
        return newList;
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
