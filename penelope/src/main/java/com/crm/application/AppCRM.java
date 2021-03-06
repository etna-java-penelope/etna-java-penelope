/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.crm.application;

import com.crm.Database.MySql;
import com.crm.Entity.Role;
import com.crm.Entity.User;
import com.crm.Kernel.KernelFactory;
import com.crm.Module.Dao.Module;
import com.crm.Module.MessageDao.IMessageOperation;
import com.crm.Module.MessageDao.MessageDelivery;
import com.crm.Module.MessageDao.MessageOperation;
// import com.crm.Module.MessageDao.*;
// import com.crm.Module.MessageDao.*;
import com.crm.Module.RoleModule;
import com.crm.Module.UserModule;
import com.crm.Tools.Constants;
//import javafx.scene.control.Alert;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Meghdad BSSI
 */
public class AppCRM extends JFrame
{
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JtxtAddress;
    private javax.swing.JTextArea JtxtAreaMessage;
    private javax.swing.JTextField JtxtEmail;
    private javax.swing.JTextField JtxtFirstname;
    private javax.swing.JTextField JtxtLastname;
    private javax.swing.JTextField JtxtPassword;
    private javax.swing.JTextField JtxtPhone;
    private javax.swing.JTextField JtxtUsername;
    private javax.swing.JTextField JtxtVille;
    private javax.swing.JLabel adressLabel;
    private javax.swing.JButton buttonCreateRole;
    private javax.swing.JButton buttonCreateUser;
    private javax.swing.JButton buttonSendMessage;
    private javax.swing.JTextArea descriptionRole;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JComboBox<String> jCmbRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel nomLabel;
    private javax.swing.JScrollPane panelScrollModule;
    private javax.swing.JScrollPane panelScrollRole;
    private javax.swing.JScrollPane panelScrollUser;
    private javax.swing.JScrollPane panelSendMessage;
    private javax.swing.JLabel passLabel;
    private javax.swing.JLabel prenomLabel;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JTextField roleName;
    private javax.swing.JPanel tabAddRole;
    private javax.swing.JPanel tabAddUser;
    private javax.swing.JPanel tabModuleList;
    private javax.swing.JPanel tabRole;
    private javax.swing.JPanel tabSendMessage;
    private javax.swing.JPanel tabUser;
    private javax.swing.JTable tableModule;
    private javax.swing.JTable tableRole;
    private javax.swing.JTable tableUser;
    private javax.swing.JLabel telLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel villeLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form AppCRM
     */
    public AppCRM() throws Exception
    {
        initComponents();
        initComponentsSQL();
    }

    private void initUser() throws Exception
    {
        Module<User> u = new UserModule();
        List<User> uData = u.findAll(new User());
        Object[][] userObj = new Object[uData.size()][5];
        for (User p : uData) {
            userObj[p.getId() - 1][0] = p.getFirstname();
            userObj[p.getId() - 1][1] = p.getLastname();
            userObj[p.getId() - 1][2] = p.getEmail();
            userObj[p.getId() - 1][3] = p.getAddress();
            userObj[p.getId() - 1][4] = p.getRoles();
        }
        tableUser.setModel(new javax.swing.table.DefaultTableModel(userObj, new String [] {"Prénom", "Nom", "Email", "Username", "Role"})
        {
            Class[] types = new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class};
            public Class getColumnClass(int columnIndex) { return types [columnIndex]; }
        });

        panelScrollUser.setViewportView(tableUser);
    }

    private void initRole() throws Exception
    {
        Module<Role> r = new RoleModule();
        List<Role> rData = r.findAll(new Role());
        Object[][] roleObj = new Object[rData.size()][3];
        jCmbRole.removeAllItems();

        for (Role p : rData) {
            roleObj[p.getId() - 1][0] = p.getId();
            roleObj[p.getId() - 1][1] = p.getName();
            roleObj[p.getId() - 1][2] = p.getDescription();
            jCmbRole.addItem(p.getName());
        }
        tableRole.setModel(new javax.swing.table.DefaultTableModel(roleObj, new String [] {"ID", "Nom", "Description"})
        {
            Class[] types = new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class};
            public Class getColumnClass(int columnIndex) { return types [columnIndex]; }
        });
        panelScrollRole.setViewportView(tableRole);

    }

    private void initModule()
    {
        Collection listModule = KernelFactory.listModule();
        Object[][] moduleObj = new Object[listModule.size()][3];
        int idx = 0;
        for (Object tmp : listModule) {
            String[] name = tmp.toString().split("/");
            moduleObj[idx][0] = idx + 1;
            moduleObj[idx][1] = name[name.length - 1];
            moduleObj[idx][2] = "TRUE";
            idx++;
        }
        tableModule.setModel(new javax.swing.table.DefaultTableModel(moduleObj , new String [] {"ID", "Nom", "Etat"})
        {
            Class[] types = new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class};
            public Class getColumnClass(int columnIndex) { return types [columnIndex]; }
        });
        panelScrollModule.setViewportView(tableModule);
    }

    private void initComponentsSQL() throws Exception
    {
        MySql.init(Constants.DATABASE_NAME, Constants.DATABASE_USER, Constants.DATABASE_PASS);

        initUser();
        initRole();
        initModule();
        jTabbedPane1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                switch(jTabbedPane1.getSelectedIndex()) {
                    case 1: //user
                        try {
                            initUser();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;

                    case 3: // role
                        try {
                            initRole();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;

                }
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabModuleList = new javax.swing.JPanel();
        panelScrollModule = new javax.swing.JScrollPane();
        tableModule = new javax.swing.JTable();
        tabUser = new javax.swing.JPanel();
        panelScrollUser = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        tabAddUser = new javax.swing.JPanel();
        JtxtFirstname = new javax.swing.JTextField();
        JtxtLastname = new javax.swing.JTextField();
        JtxtEmail = new javax.swing.JTextField();
        JtxtPassword = new javax.swing.JTextField();
        JtxtAddress = new javax.swing.JTextField();
        JtxtVille = new javax.swing.JTextField();
        jCmbRole = new javax.swing.JComboBox<>();
        jCmbRole.removeAllItems();
        jCmbRole.addItem("Admin");
        jCmbRole.addItem("Moderateur");
        jCmbRole.addItem("Utilisateur");
        prenomLabel = new javax.swing.JLabel();
        nomLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        telLabel = new javax.swing.JLabel();
        adressLabel = new javax.swing.JLabel();
        villeLabel = new javax.swing.JLabel();
        roleLabel = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        JtxtUsername = new javax.swing.JTextField();
        JtxtPhone = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        buttonCreateUser = new javax.swing.JButton();
        tabRole = new javax.swing.JPanel();
        panelScrollRole = new javax.swing.JScrollPane();
        tableRole = new javax.swing.JTable();
        tabAddRole = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        roleName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionRole = new javax.swing.JTextArea();
        buttonCreateRole = new javax.swing.JButton();
        tabSendMessage = new javax.swing.JPanel();
        panelSendMessage = new javax.swing.JScrollPane();
        JtxtAreaMessage = new javax.swing.JTextArea();
        buttonSendMessage = new javax.swing.JButton();
        messageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("My Pénélope F");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(102, 102, 102));

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // jTabbedPane1MouseClicked(evt);
            }
        });

        tableModule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nom", "State"
            }
        ));
        panelScrollModule.setViewportView(tableModule);

        javax.swing.GroupLayout tabModuleListLayout = new javax.swing.GroupLayout(tabModuleList);
        tabModuleList.setLayout(tabModuleListLayout);
        tabModuleListLayout.setHorizontalGroup(
            tabModuleListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScrollModule, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1557, Short.MAX_VALUE)
        );
        tabModuleListLayout.setVerticalGroup(
            tabModuleListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScrollModule, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Les Modules", tabModuleList);

        tabUser.setName(""); // NOI18N
        tabUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                // tabUserMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // tabUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // tabUserMouseEntered(evt);
            }
        });

        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Prénom", "Nom", "Email", "Username", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        panelScrollUser.setViewportView(tableUser);

        javax.swing.GroupLayout tabUserLayout = new javax.swing.GroupLayout(tabUser);
        tabUser.setLayout(tabUserLayout);
        tabUserLayout.setHorizontalGroup(
            tabUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScrollUser, javax.swing.GroupLayout.DEFAULT_SIZE, 1557, Short.MAX_VALUE)
        );
        tabUserLayout.setVerticalGroup(
            tabUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScrollUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Utilisateurs", tabUser);

        jCmbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        prenomLabel.setText("Prénom");

        nomLabel.setText("Nom");

        emailLabel.setText("Email");

        passLabel.setText("Mot de passe");

        usernameLabel.setText("Username");

        telLabel.setText("Téléphone");

        adressLabel.setText("Adresse");

        villeLabel.setText("Ville");

        roleLabel.setText("Role");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel1.setText("Creation d'utilisateur");

        buttonCreateUser.setText("Valider");
        buttonCreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabAddUserLayout = new javax.swing.GroupLayout(tabAddUser);
        tabAddUser.setLayout(tabAddUserLayout);
        tabAddUserLayout.setHorizontalGroup(
            tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAddUserLayout.createSequentialGroup()
                .addGap(644, 644, 644)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabAddUserLayout.createSequentialGroup()
                .addGap(456, 456, 456)
                .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonCreateUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tabAddUserLayout.createSequentialGroup()
                        .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailLabel)
                            .addComponent(passLabel)
                            .addComponent(telLabel)
                            .addComponent(adressLabel)
                            .addComponent(villeLabel)
                            .addComponent(nomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roleLabel)
                            .addComponent(usernameLabel)
                            .addComponent(prenomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JtxtLastname)
                            .addComponent(JtxtEmail)
                            .addComponent(JtxtPassword)
                            .addComponent(JtxtVille)
                            .addComponent(jCmbRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(tabAddUserLayout.createSequentialGroup()
                                .addComponent(JtxtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(JtxtAddress)
                            .addComponent(JtxtUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JtxtPhone, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(345, 345, 345)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
        );
        tabAddUserLayout.setVerticalGroup(
            tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAddUserLayout.createSequentialGroup()
                .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabAddUserLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1)
                        .addGap(53, 53, 53)
                        .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JtxtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prenomLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomLabel)
                            .addComponent(JtxtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailLabel)
                            .addComponent(JtxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passLabel)
                            .addComponent(JtxtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usernameLabel)
                            .addComponent(JtxtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(telLabel)
                            .addComponent(JtxtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(adressLabel)
                            .addComponent(JtxtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JtxtVille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(villeLabel)))
                    .addGroup(tabAddUserLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabAddUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roleLabel)
                    .addComponent(jCmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(buttonCreateUser)
                .addContainerGap(231, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ajouter Utilisateur", null, tabAddUser, "");

        tabRole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // tabRoleMouseClicked(evt);
            }
        });

        tableRole.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Prénom", "Nom", "Email", "Username", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        panelScrollRole.setViewportView(tableRole);

        javax.swing.GroupLayout tabRoleLayout = new javax.swing.GroupLayout(tabRole);
        tabRole.setLayout(tabRoleLayout);
        tabRoleLayout.setHorizontalGroup(
            tabRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScrollRole, javax.swing.GroupLayout.DEFAULT_SIZE, 1557, Short.MAX_VALUE)
        );
        tabRoleLayout.setVerticalGroup(
            tabRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelScrollRole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Les Roles", tabRole);

        jLabel2.setText("Creation de role");

        jLabel3.setText("Nom");

        jLabel4.setText("Description");

        roleName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // roleNameActionPerformed(evt);
            }
        });

        descriptionRole.setColumns(20);
        descriptionRole.setRows(5);
        jScrollPane1.setViewportView(descriptionRole);

        buttonCreateRole.setText("Valider");
        buttonCreateRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateRoleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabAddRoleLayout = new javax.swing.GroupLayout(tabAddRole);
        tabAddRole.setLayout(tabAddRoleLayout);
        tabAddRoleLayout.setHorizontalGroup(
            tabAddRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAddRoleLayout.createSequentialGroup()
                .addGroup(tabAddRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabAddRoleLayout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addGroup(tabAddRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonCreateRole, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tabAddRoleLayout.createSequentialGroup()
                                .addGroup(tabAddRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(167, 167, 167)
                                .addGroup(tabAddRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roleName, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(tabAddRoleLayout.createSequentialGroup()
                        .addGap(646, 646, 646)
                        .addComponent(jLabel2)))
                .addContainerGap(581, Short.MAX_VALUE))
        );
        tabAddRoleLayout.setVerticalGroup(
            tabAddRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAddRoleLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addGap(42, 42, 42)
                .addGroup(tabAddRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(roleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tabAddRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addComponent(buttonCreateRole)
                .addContainerGap(385, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ajouter Role", tabAddRole);

        tabSendMessage.setVerifyInputWhenFocusTarget(false);

        JtxtAreaMessage.setColumns(20);
        JtxtAreaMessage.setRows(5);
        panelSendMessage.setViewportView(JtxtAreaMessage);

        buttonSendMessage.setText("Envoyer");
        buttonSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendMessageActionPerformed(evt);
            }
        });

        messageLabel.setText("Votre Message");

        javax.swing.GroupLayout tabSendMessageLayout = new javax.swing.GroupLayout(tabSendMessage);
        tabSendMessage.setLayout(tabSendMessageLayout);
        tabSendMessageLayout.setHorizontalGroup(
            tabSendMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabSendMessageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabSendMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSendMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 1537, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabSendMessageLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonSendMessage))
                    .addGroup(tabSendMessageLayout.createSequentialGroup()
                        .addComponent(messageLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabSendMessageLayout.setVerticalGroup(
            tabSendMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabSendMessageLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(messageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSendMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSendMessage)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Message", tabSendMessage);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1307, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        setSize(new java.awt.Dimension(1333, 789));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateUserActionPerformed
        // TODO add your handling code here:
        try {
            Module<User> moduleUser = new UserModule();
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("lastname",  JtxtLastname.getText());
            userMap.put("firstname", JtxtFirstname.getText());
            userMap.put("email", JtxtEmail.getText());
            userMap.put("password", JtxtPassword.getText());
            userMap.put("username", JtxtUsername.getText());
            userMap.put("phone", JtxtPhone.getText());
            userMap.put("address", JtxtAddress.getText());
            userMap.put("city", JtxtVille.getText());
            userMap.put("zipcode", 75001);
            userMap.put("roles", jCmbRole.getSelectedItem().toString());
            userMap.put("status", "OK");
            if (moduleUser.insertData(new User(userMap)))
                JOptionPane.showMessageDialog(new JFrame(), "Succes: Role, L'utilisateur à bien été ajouter en base de donnée.", "Alert", JOptionPane.WARNING_MESSAGE);
            else
                JOptionPane.showMessageDialog(new JFrame(), "Erreur: Role, L'utilisateur n'a pas pu être ajouter en base de donnée.", "Alert", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_buttonCreateUserActionPerformed

    private void buttonCreateRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateRoleActionPerformed
        // TODO add your handling code here:
        try {
            Module<Role> moduleRole = new RoleModule();
            Map<String, Object> roleMap = new HashMap<>();
            roleMap.put("name",  roleName.getText());
            roleMap.put("description", descriptionRole.getText());
            if (moduleRole.insertData(new Role(roleMap)))
                JOptionPane.showMessageDialog(new JFrame(), "Succes: Role, Le role à bien été ajouter en base de donnée.", "Alert", JOptionPane.WARNING_MESSAGE);
            else
                JOptionPane.showMessageDialog(new JFrame(), "Erreur: Role, Le role n'a pas pu être ajouter en base de donnée.", "Alert", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_buttonCreateRoleActionPerformed

    private void buttonSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendMessageActionPerformed
        // TODO add your handling code here:
        try {
            Module<User> u = new UserModule();
            List<User> users = u.findAll(new User());
            IMessageOperation msgOperation = new MessageOperation();

            for (User usr : users)
                msgOperation.addNewMessage(new MessageDelivery(usr));

            msgOperation.sendNotification(JtxtAreaMessage.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_buttonSendMessageActionPerformed
}
