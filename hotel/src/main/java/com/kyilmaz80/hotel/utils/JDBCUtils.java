package com.kyilmaz80.hotel.utils;
import com.kyilmaz80.hotel.DomainConstants;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


import static java.sql.Connection.*;
public class JDBCUtils {
    private static final Properties settings = new Properties();
    private static String fileSeparator;
    private static String filePath = "src/main/resources/jdbc.properties";

    private static String url;
    private static String url2;
    private static String ip;
    private static String username;
    private static String password;
    private static String driver;

    private static String logFile;

    public static String error;

    //private static BasicDataSource ds = new BasicDataSource();

    private static int transactionIsolationLevel = TRANSACTION_READ_COMMITTED;

    static {
        fileSeparator = System.getProperty("file.separator");
        FileReader in = null;
        try {
            in = new FileReader(filePath);
        } catch (FileNotFoundException e1) {
            System.out.println("File is not found: " + filePath);
            e1.printStackTrace();
        }
        try {
            settings.load(in);
        } catch (IOException e) {
            System.out.println("Problem with IO: " + e.getMessage());
            e.printStackTrace();
        }

        // settings.list(System.out);
        // System.out.println();
        url = settings.getProperty("url");
        url2 = settings.getProperty("url2");
        username = settings.getProperty("username");
        password = settings.getProperty("password");
        driver = settings.getProperty("driver");
        logFile = settings.getProperty("logFile");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(driver + " is not found: " + e.getMessage());
        }
    }

    public static Connection getConnection() {

        if (logFile != null) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new File(logFile));
            } catch (FileNotFoundException e) {
                System.out.println("In DriverManagerExample, problem with creating log file " + e.getMessage());
            }
            DriverManager.setLogWriter(writer);
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            //connection.setTransactionIsolation(transactionIsolationLevel);
        } catch (SQLException e) {
            error = e.getMessage();
            System.out.println("In JDBCUtil, problem with getting a connection: " + error);
        } finally {
            if (!error.isEmpty() && error.matches(DomainConstants.ERROR_NO_DATABASE_REGEX)) {
                System.out.println("Unknown database match!");
                //TODO: create init db tables
                /*
                System.out.println("Creating bunbled database...");
                try {
                    connection = DriverManager.getConnection(url2, username, password);
                    String path = new File(ClassLoader.getSystemClassLoader().
                            getResource("Hotel.sql").getFile()).toPath().toString();
                    DBUtils.executeBatchedSQL(path, connection, 10);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                 */
            }
        }
        // Properties clientInfo = new Properties();
        // clientInfo.setProperty("verifyServerCertificate", "false") ;
        // clientInfo.setProperty("useSSL", "false") ;
        // try {
        // connection.setClientInfo(clientInfo);
        // } catch (SQLClientInfoException e) {
        // e.printStackTrace();
        // }
        return connection;
    }

    /*
     * https://gitbox.apache.org/repos/asf?p=commons-dbcp.git;a=blob_plain;f=doc/BasicDataSourceExample.java;hb=HEAD
     */
    /*
    public static Connection getConnectionFromDataSource() {

        ds.setDriverClassName(driver);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setUrl(url);

        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            System.out.println("In JDBCUtil, problem with getting a connection: " + e.getMessage());
        }
        return connection;
    }


     */
    public void setTransactionIsolationLevel(int transactionIsolationLevel) {
        this.transactionIsolationLevel = transactionIsolationLevel;
    }

    public static String getProperty(String propertyName) {
        String propertyValue = switch (propertyName) {
            case "url" -> url;
            case "url2" -> url2;
            case "username" -> username;
            case "password" -> password;
            case "driver" -> driver;
            default -> null;

//		case "database":
//			propertyValue = database; break;
        };

        return propertyValue;
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
//		Connection conn = getConnectionFromDataSource();
        if (conn != null)
            System.out.println(conn);
        else
            System.out.println("No connection!");


    }

}
