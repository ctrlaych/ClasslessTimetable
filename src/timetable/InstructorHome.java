/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Hammad
 */
public class InstructorHome extends javax.swing.JFrame {

    /**
     * Creates new form InstructorHome
     */
    int ID;
    Department D;
    Instructor T;
    int DEPTID;
    ArrayList<Section> secList = new ArrayList<Section>();

    public final void fetchData(int id) {
        String NAME = null;
        String DEPARTMENT = null;
        int did = 0;
        String USERNAME = null;
        String PASSWORD = null;
        int tId = 0;
        int deptId = 0;
        Connection dbconn = Database.con();
        if (dbconn != null) {
            try {

                PreparedStatement us;
                us = dbconn.prepareStatement("Select * from user where id = ?");
                us.setInt(1, id);
                ResultSet re = us.executeQuery();
                if (!re.next()) {
                    JOptionPane.showMessageDialog(null, "Error Fetching Data", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    NAME = re.getString("name");
                    USERNAME = re.getString("username");
                    PASSWORD = re.getString("password");
                    deptId = re.getInt("dept");
                    DEPTID = deptId;
                }

            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
            }

            try {
                PreparedStatement us;
                us = dbconn.prepareStatement("Select * from instructor where userId = ?");
                us.setInt(1, id);
                ResultSet re = us.executeQuery();
                if (re.next()) {
                    tId = re.getInt("id");
                }

            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
            }

            try {
                PreparedStatement st;
                st = dbconn.prepareStatement("Select * from department WHERE id = ?");
                st.setInt(1, deptId);
                ResultSet res = st.executeQuery();
                if (!res.next()) {
                    JOptionPane.showMessageDialog(null, "Error Fetching Data", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    did = res.getInt("id");
                    DEPARTMENT = res.getString("name");
                }

            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
            }
        } else {
            System.out.println("Connection not availabale");
        }

        D = new Department(did, DEPARTMENT);
        T = new Instructor(tId, NAME, D, "Instructor", USERNAME, PASSWORD);
        name.setText(NAME);
        department.setText(DEPARTMENT);
        username.setText(USERNAME);
        password.setText(PASSWORD);
    }

    public final void fetchSections(int id) {
        String NAME = null;
        String DAY = null;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime lt;
        String TIME = null;
        String ROOM = null;
        int ROOMLMT = 0;
        int STATUS = 0;

        Connection dbconn = Database.con();
        if (dbconn != null) {
            try {
                PreparedStatement us;
                us = dbconn.prepareStatement("Select * from section where instructorId = ?");
                us.setInt(1, 0);
                ResultSet re = us.executeQuery();
                while (re.next()) {
                    NAME = re.getString("name");
                    DAY = re.getString("day");
                    TIME = re.getString("time");
                    lt = LocalTime.parse(TIME, df);
                    ROOM = re.getString("room");
                    ROOMLMT = re.getInt("roomLimit");
                    int cId = re.getInt("courseId");
                    STATUS = re.getInt("status");

                    Course COURSE = null;

                    try {
                        PreparedStatement us2;
                        us2 = dbconn.prepareStatement("Select * from course where id = ?");
                        us2.setInt(1, cId);
                        ResultSet re2 = us2.executeQuery();
                        if (!re2.next()) {
                            JOptionPane.showMessageDialog(null, "Error Fetching Data", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {

                            String code = re2.getString("code");
                            String name = re2.getString("name");
                            int credHrs = re2.getInt("creditHours");
                            int batchNum = re2.getInt("batchNumber");
                            int ls = re2.getInt("labStatus");
                            COURSE = new Course(cId, code, name, credHrs, batchNum, T.getDept(), ls);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getErrorCode());
                    }

                    Section s = new Section(NAME, DAY, lt, ROOM, ROOMLMT, STATUS);
                    s.setCourse(COURSE);
                    COURSE.getSectionList().add(s);
                    secList.add(s);
                }

            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
            }

        } else {
            System.out.println("Connection not availabale");
        }
    }

    public InstructorHome(int id) {
        initComponents();
        ID = id;
        fetchData(ID);
        fetchSections(ID);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        name = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        department = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        password = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Instructor Home");
        setResizable(false);

        jButton6.setText("Logout");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel1.setText("Instructor Home");

        jLabel2.setText("Name:");

        jLabel3.setText("Username:");

        jLabel4.setText("Department:");

        jLabel5.setText("Password:");

        jButton5.setText("Select Timeslots");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setText("View Sections");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(39, 39, 39)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(21, 21, 21)
                                .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(112, 112, 112)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(119, 119, 119))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(department, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new SelectTimeslots(T, secList).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        new ViewTeachSections(T).setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[], int id) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InstructorHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InstructorHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InstructorHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InstructorHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InstructorHome(id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel department;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel name;
    private javax.swing.JLabel password;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
