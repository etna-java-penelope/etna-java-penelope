package com.crm.Tools;

import java.awt.*;
import java.io.File;

/**
 * Constants
 */
public class Constants {

    // APPLICATION WINDOWS
    public static String APP_TITLE = "Our Application";
    public static Dimension APP_SIZE = new Dimension(700,700);
    // public static Point APP_LOCATION = new Point(0,0);
    // public static Integer APP_LOCATION = 0;


    /* directory */
    public static String DIRECTORY_MODULE = "./src/main/java/com/crm/Module";
    /*public static String DIRECTORY_MODULE = System.getProperty("user.dir")+
                                                    File.separator+"penelope"+
                                                    File.separator+"src"+
                                                    File.separator+"main"+
                                                    File.separator+"java"+
                                                    File.separator+"com"+
                                                    File.separator+"crm"+
                                                    File.separator+"Module";*/
    public static String DIRECTORY_KERNEL = "Kernel";
    public static String DIRECTORY_ENTITY = "Entity";


    /* extension */
    public static String EXTENSION_JAVA = "*.java";
    public static String WORD_MODULE = "Module";


    /* database */
    public static String DATABASE_NAME = "penelope_f";
    public static String DATABASE_USER = "root";
    public static String DATABASE_PASS = "";


    /* execution */
    public static Boolean RUN_MODULE = true;
    public static Boolean STOP_MODULE = false;

}
