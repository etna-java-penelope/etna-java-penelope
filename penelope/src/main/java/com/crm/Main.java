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
import java.io.File;
import java.util.List;

/**
 *
 * @author kaczor_a
 * @author abolfa_m
 */
public class Main
{
    /**
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception
    {
        try {
            MySql.init(Constants.DATABASE_NAME, Constants.DATABASE_USER, Constants.DATABASE_PASS);
            MySql db = MySql.getDBInstance();
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppCRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new AppCRM().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
