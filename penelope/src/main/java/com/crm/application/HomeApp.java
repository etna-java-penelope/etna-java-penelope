package com.crm.application;

import com.crm.Tools.Constants;

import javax.swing.*;

/**
 * MainApp
 */
public class HomeApp extends JFrame {
    public HomeApp() {
        this.setTitle(Constants.APP_TITLE);
        this.setSize(Constants.APP_SIZE);
		// this.setLocation(Constants.APP_LOCATION);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
		this.setResizable(false);
    }
}
