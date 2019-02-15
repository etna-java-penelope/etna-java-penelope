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
import com.crm.Tools.Constant;
import com.crm.application.AppCRM;

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppCRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppCRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppCRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppCRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppCRM().setVisible(true);
            }
        });

       /* try {
            System.out.println(KernelFactory.getModule("User").run());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {
            System.out.println("Init database connection ...");
            MySql.init(Constant.DATABASE_NAME, Constant.DATABASE_USER, Constant.DATABASE_PASS);
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
                System.out.println(t.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

      /*  System.out.println("Init user ...");
        Map<String, Object> data = new HashMap<>();
        data.put("id", 38); data.put("lastname", "Thomas"); data.put("firstname", "Kaczor"); data.put("email", "kaczor_a@etna-alternance.net"); data.put("password", "testtest"); data.put("username", "Amity"); data.put("phone", "0650135452"); data.put("address", "10 rue de paris"); data.put("city", "Paris"); data.put("zipcode", "75001"); data.put("roles", "Admin"); data.put("status", "activate");

        User tmp = new User(data);
        try {
            Module<User> d = new UserModule();
                        d.findByAttr(tmp, "lastname");
            System.out.println(d.insertData(tmp) ? "Insert OK" : "Insert Failed");
            //tmp.setPassword("alexalex"); tmp.setId(1);
            //System.out.println(d.updateData(tmp) ? "Update OK" : "Update Failed");
            //System.out.println(d.deleteData(tmp) ? "Delete OK" : "Delete Failed");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
