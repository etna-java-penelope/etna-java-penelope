package com.crm.main;

import com.crm.application.NewCRM;

import javax.swing.*;

/**
 *
 * @author kaczor_a
 * @author abolfa_m
 */
public final class MainCRM {

    /**
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        // HomeApp app = new HomeApp();
        // calcul calc = new calcul();
        // System.out.println("Hello World!");

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewCRM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new NewCRM().setVisible(true));
    }
}

