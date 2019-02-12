package com.crm.tools;

import java.awt.*;

/**
 * Constants
 */
public class Constants {

    // APPLICATION WINDOWS
    public static String APP_TITLE = "Our Application";
    public static Dimension APP_SIZE = new Dimension(700,700);
    // public static Point APP_LOCATION = new Point(0,0);
    // public static Integer APP_LOCATION = 0;

    // SATABASE
    public static String DATABASE_URL = "jdbc:mysql://localhost:3306/base_personnes";
    public static String DATABASE_USER = "root";
    public static String DATABASE_PASSWORD = "";

    // Drivers Jdbc
    public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // Cle Url base de donnees
    public static String DATABASE_URL_KEY = "DATABASE_URL_KEY";
    // Cle utilisateur
    public static String DATABASE_USER_KEY = "DATABASE_USER_KEY";
    // Cle mot passe
    public static String DATABASE_PASSWORD_KEY = "DATABASE_PASSWORD_KEY";

    // Drivers Jdbc key
    public static String JDBC_DRIVER_KEY = "JDBC_DRIVER_KEY";

    // Constante IMC
    public static double LIMITE_INF_MAIGRE = 16.5;
    public static double LIMITE_SUP_MAIGRE = 18.5;

    public static double LIMITE_INF_SURPOIDS = 25;
    public static double LIMITE_SUP_SURPOIDS = 30;

    // Constante des identifications de notifications
    public static Integer STATE_DELETE_JSON = 2;
    public static Integer STATE_CREATE_XML = 3;
    public static Integer STATE_DELETE_XML = 4;
    public static Integer STATE_CREATE_CSV = 5;
    public static Integer STATE_DELETE_CSV = 6;
}
