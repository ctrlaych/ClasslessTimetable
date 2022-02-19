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
public class StudentHome extends javax.swing.JFrame {

    /**
     * Creates new form StudentHome
     */
    int ID;
    Department D;
    Student ST;
    int DEPTID;

    public final void fetchData(int id) {
        String NAME = null;
        String DEPARTMENT = null;
        int did = 0;
        String USERNAME = null;
        String PASSWORD = null;
        int BATCHNUM = 0;
        int sId = 0;
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
                us = dbconn.prepareStatement("Select * from student where userId = ?");
                us.setInt(1, id);
                ResultSet re = us.executeQuery();
                if (re.next()) {
                    BATCHNUM = re.getInt("batchNumber");
                    sId = re.getInt("id");
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
        ST = new Student(sId, NAME, D, "Student", USERNAME, PASSWORD, BATCHNUM);
        name.setText(NAME);
        department.setText(DEPARTMENT);
        username.setText(USERNAME);
        password.setText(PASSWORD);
        batchNum.setText(String.valueOf(BATCHNUM));
    }

    public final void fetchCourses() {
        Connection dbconn = Database.con();
        if (dbconn != null) {
            try {
                PreparedStatement st;
                st = dbconn.prepareStatement("Select * from course where deptId = ? and batchNumber = ?");
                st.setInt(1, DEPTID);
                st.setInt(2, ST.getBatchNumber());
                ResultSet res = st.executeQuery();

                ArrayList<Course> cList = new ArrayList<Course>();
                while (res.next()) {
                    int id = res.getInt("id");
                    int ls = res.getInt("labStatus");
                    String code = res.getString("code");
                    String cName = res.getString("name");
                    int credHrs = res.getInt("creditHours");
                    int bn = res.getInt("batchNumber");
                    Course c = new Course(id, code, cName, credHrs, bn, D, ls);
                    cList.add(c);
                }
                ST.getDept().setCourseList(cList);

            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
            }
        } else {
            System.out.println("Connection not availabale");
        }
    }

    public final void fetchSections() {
        Connection dbconn = Database.con();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");

        if (dbconn != null) {
            for (int i = 0; i < ST.getDept().getCourseList().size(); i++) {
                try {
                    PreparedStatement st;
                    st = dbconn.prepareStatement("Select * from section where courseId = ?");
                    st.setInt(1, ST.getDept().getCourseList().get(i).getId());
                    ResultSet res = st.executeQuery();
                    ArrayList<Section> sList = new ArrayList<Section>();
                    while (res.next()) {
                        String name = res.getString("name");
                        String day = res.getString("day");
                        String time = res.getString("time");
                        int status = res.getInt("status");

                        LocalTime lt = LocalTime.parse(time, df);

                        String room = res.getString("room");
                        int roomLmt = res.getInt("roomLimit");
                        int instrId = res.getInt("instructorId");
                        Section s = new Section(name, day, lt, room, roomLmt, ST.getDept().getCourseList().get(i));
                        s.setStatus(status);
                        sList.add(s);
                    }
                    ST.getDept().getCourseList().get(i).setSectionList(sList);
                } catch (SQLException ex) {
                    System.out.println(ex.getErrorCode());
                }
            }
        } else {
            System.out.println("Connection not availabale");
        }
    }

    public StudentHome(int id) {
        initComponents();
        ID = id;
        fetchData(ID);
        fetchCourses();
        fetchSections();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        password = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        department = new javax.swing.JLabel();
        asd = new javax.swing.JLabel();
        batchNum = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Home");
        setResizable(false);

        jButton5.setText("Generate Timetable");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Logout");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel1.setText("Student Home");

        jLabel2.setText("Name:");

        jLabel3.setText("Username:");

        jLabel4.setText("Department:");

        jLabel5.setText("Password:");

        name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        username.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        asd.setText("Batch number:");

        batchNum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(asd))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(batchNum, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(50, 50, 50)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(21, 21, 21)
                            .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(batchNum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(asd))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new SelectCoursesAndSections(ST).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentHome(id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asd;
    private javax.swing.JLabel batchNum;
    private javax.swing.JLabel department;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
