package com.crm;

/*
 *
 * Main App
 * My Penelope F
 *
 */

import com.crm.Database.MySql;
import com.crm.Entity.User;
import com.crm.Module.Dao.Module;
import com.crm.Module.UserModule;
import com.crm.Tools.Constants;
import com.crm.application.AppCRM;

import javax.swing.*;
import java.util.List;

/**
 *
 * @author kaczor_a
 * @author abolfa_m
 */
public class Main {

    /**
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppCRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppCRM().setVisible(true);
            }
        });


        try {
            System.out.println("Init database connection ...");
            MySql.init(Constants.DATABASE_NAME, Constants.DATABASE_USER, Constants.DATABASE_PASS);
            MySql db = MySql.getDBInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            User tmp = new User();
            tmp.setLastname("Alexandre");
            Module<User> d = new UserModule();
            //List<User> data = d.findByAttr(tmp, "lastname");
            List<User> data = d.findAll(tmp);
            for (User t : data) {
                System.out.println(t.getFirstname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
