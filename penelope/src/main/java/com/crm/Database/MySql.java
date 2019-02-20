package com.crm.Database;


import com.crm.Tools.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MySql {
    private static MySql Db;
    private static Boolean isReady = false;
    private static String DBName;
    private static String Username;
    private static String Password;
    private static String Driver = "com.mysql.jdbc.Driver";
    private static String Url = "jdbc:mysql://localhost:8889/";
    private Connection Conn;


    /**
     * Constructor MySql Singleton Class
     *
     * @param DBName   dbname
     * @param Username username
     * @param Password password
     */
    private MySql(String DBName, String Username, String Password) {
        try {
            Class.forName(Driver).newInstance();
            this.Conn = DriverManager.getConnection(Url + DBName, Username, Password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method must be exec in first, is used to init DbConnection (save dbname, username, password) After that use getDbCon !
     *
     * @param db   (dbname mysql)
     * @param user (username connection)
     * @param pass (password connection)
     * @return true or false if init success...
     */
    public static Boolean init(String db, String user, String pass) {
        DBName = db;
        Username = user;
        Password = pass;
        isReady = true;

        return (true);
    }

    /**
     * This method is used to get singleton Instance DB
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized MySql getDBInstance() throws Exception {
        if (!isReady)
            throw new Exception("Error mysql db not init. Run ::init(db, user, pass) before");
        if (Db == null)
            Db = new MySql(DBName, Username, Password);
        return (Db);
    }

    private static <T> Boolean prepareQuerySQL(PreparedStatement query, T data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        Class<?> myClass = data.getClass();
        int idx = 1;

        for (Field field : Utils.getFieldFromClass(data)) {
            if (field.getType().getSimpleName().equals("int") && (!field.getName().equals("id"))) {
                query.setInt(idx++, (Integer) myClass.getMethod("get" + Utils.ucFirst(field.getName())).invoke(data));
            } else if (field.getType().getSimpleName().equals("Long")) {
                query.setDouble(idx++, (Long) myClass.getMethod("get" + Utils.ucFirst(field.getName())).invoke(data));
            } else if (field.getType().getSimpleName().equals("Float")) {
                query.setFloat(idx++, (Float) myClass.getMethod("get" + Utils.ucFirst(field.getName())).invoke(data));
            } else if (field.getType().getSimpleName().equals("Double")) {
                query.setDouble(idx++, (Double) myClass.getMethod("get" + Utils.ucFirst(field.getName())).invoke(data));
            } else if (field.getType().getSimpleName().equals("String")) {
                query.setString(idx++, (String) myClass.getMethod("get" + Utils.ucFirst(field.getName())).invoke(data));
            } else if (field.getType().getSimpleName().equals("Boolean")) {
                query.setBoolean(idx++, (Boolean) myClass.getMethod("get" + Utils.ucFirst(field.getName())).invoke(data));
            }
        }
        if ((int) myClass.getMethod("getId").invoke(data) > 0)
            query.setInt(idx++, (int) myClass.getMethod("getId").invoke(data));
        return (query.executeUpdate() == (1));
    }

    public static <T> Boolean insert(T data) throws Exception {
        StringBuilder value = new StringBuilder("(default, ");

        // i + 2 (avoid multiple , at end) (skip ID because of AUTO_INCREMENT)
        for (int i = 0; i < Utils.getFieldFromClass(data).length - 1; i++) {
            value.append("? ");
            if (i + 2 != Utils.getFieldFromClass(data).length)
                value.append(", ");
        }
        value.append(");");

        PreparedStatement insert = Db.Conn.prepareStatement("INSERT INTO " + Utils.getClassName(data).toLowerCase() + " VALUES " + value.toString());
        return (prepareQuerySQL(insert, data));
    }

    public static <T> Boolean update(T data) throws Exception {
        StringBuilder value = new StringBuilder();
        String updateTableSQL = "UPDATE " + Utils.getClassName(data) + " SET ";

        int idx = 1;
        for (Field field : Utils.getFieldFromClass(data)) {
            if (!field.getName().equals("id")) {
                value.append(field.getName().toLowerCase() + " = ?");
                if (idx != Utils.getFieldFromClass(data).length)
                    value.append(", ");
            }
            idx++;
        }
        value.append(" WHERE ID = ?;");

        PreparedStatement update = Db.Conn.prepareStatement(updateTableSQL + value);
        return (prepareQuerySQL(update, data));
    }

    public static <T> Boolean delete(T data) throws Exception {
        Class<?> myClass = data.getClass();
        String query = "DELETE FROM " + Utils.getClassName(data) + " WHERE id = ?";

        PreparedStatement delete = Db.Conn.prepareStatement(query);
        System.out.println("ID TO DELETE =" + myClass.getMethod("getId").invoke(data));
        delete.setInt(1, (Integer) myClass.getMethod("getId").invoke(data));

        return (delete.executeUpdate() == 1);
    }

    public static <T> List<T> findByAttr(T data, String attr) throws Exception {
        List<T> toRet = new ArrayList<>();
        Map<String, Object> toAdd = new HashMap<>();

        Class<?> myClass = data.getClass();
        String query = "SELECT * FROM " + Utils.getClassName(data) + " WHERE " + attr + " = '" + myClass.getMethod("get" + Utils.ucFirst(attr)).invoke(data).toString() + "';";

        Statement selectByAttr = Db.Conn.createStatement();
        ResultSet rs = selectByAttr.executeQuery(query);

        int numberOfColumns = rs.getMetaData().getColumnCount();
        int i;
        while (rs.next()) {
            for (i = 1; i <= numberOfColumns; i++) {
                String columnName = rs.getMetaData().getColumnName(i);
                String value = rs.getString(i);
                toAdd.put(columnName, value);
            }
            Constructor constructor = myClass.getConstructor(Map.class);
            toRet.add((T) constructor.newInstance(toAdd));
        }
        return (toRet);
    }

    public static <T> List<T> findAll(T data) throws Exception {
        List<T> toRet = new ArrayList<>();
        Map<String, Object> toAdd = new HashMap<>();

        Class<?> myClass = data.getClass();
        String query = "SELECT * FROM " + Utils.getClassName(data);

        Statement selectByAttr = Db.Conn.createStatement();
        ResultSet rs = selectByAttr.executeQuery(query);

        int i;
        while (rs.next()) {
            for (i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                String columnName = rs.getMetaData().getColumnName(i);
                String value = rs.getString(i);
                toAdd.put(columnName, value);
            }
            Constructor constructor = myClass.getConstructor(Map.class);
            toRet.add((T) constructor.newInstance(toAdd));
        }
        return (toRet);
    }
}
