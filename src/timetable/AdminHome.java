/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetable;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Hammad
 */
public class AdminHome extends javax.swing.JFrame {

    /**
     * Creates new form AdminHome
     */
    int ID;
    Department D;
    Admin AD;
    int DEPTID;

    public final void fetchData(int id) {
        String NAME = null;
        String DEPARTMENT = null;
        int did = 0;
        String USERNAME = null;
        String PASSWORD = null;
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
        AD = new Admin(NAME, D, "Admin", USERNAME, PASSWORD);
        D.setAdmin(AD);
        name.setText(NAME);
        department.setText(DEPARTMENT);
        username.setText(USERNAME);
        password.setText(PASSWORD);
    }

    public final void fetchInstructor_Student() {
        Connection dbconn = Database.con();
        if (dbconn != null) {
            try {
                PreparedStatement st;
                st = dbconn.prepareStatement("Select * from user where userType = ?");
                st.setString(1, "instructor");
                ResultSet res = st.executeQuery();
                ArrayList<Instructor> tList = new ArrayList<Instructor>();
                while (res.next()) {
                    int id = res.getInt("id");
                    int tid = 0;

                    try {
                        PreparedStatement st2;
                        st2 = dbconn.prepareStatement("Select * from instructor where userId = ?");
                        st2.setInt(1, id);
                        ResultSet res2 = st2.executeQuery();
                        if (res2.next()) {
                            tid = res2.getInt("id");
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getErrorCode());
                    }

                    String tName = res.getString("name");
                    String tUsername = res.getString("username");
                    String tPassword = res.getString("password");
                    Instructor t = new Instructor(tid, tName, D, "Instructor", tUsername, tPassword);
                    tList.add(t);
                }
                AD.setInstructorList(tList);

            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
            }

            try {
                PreparedStatement st;
                st = dbconn.prepareStatement("Select * from user where userType = ?");
                st.setString(1, "student");
                ResultSet res = st.executeQuery();
                ArrayList<Student> sList = new ArrayList<Student>();
                while (res.next()) {
                    String sName = res.getString("name");
                    String sUsername = res.getString("username");
                    String sPassword = res.getString("password");
                    Student s = new Student(sName, D, "Student", sUsername, sPassword);
                    sList.add(s);
                }
                AD.setStudentList(sList);

            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
            }

            try {
                PreparedStatement st;
                st = dbconn.prepareStatement("Select * from student");
                ResultSet res = st.executeQuery();
                int index = 0;
                while (res.next()) {
                    int batch = res.getInt("batchNumber");
                    AD.getStudentList().get(index).setBatchNumber(batch);
                    index += 1;
                }

            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
            }
        } else {
            System.out.println("Connection not availabale");
        }
    }

    public final void fetchCourses() {
        Connection dbconn = Database.con();
        if (dbconn != null) {
            try {
                PreparedStatement st;
                st = dbconn.prepareStatement("Select * from course where deptId = ?");
                st.setInt(1, DEPTID);
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
                AD.getDept().setCourseList(cList);

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
            for (int i = 0; i < AD.getDept().getCourseList().size(); i++) {
                try {
                    PreparedStatement st;
                    st = dbconn.prepareStatement("Select * from section where courseId = ?");
                    st.setInt(1, AD.getDept().getCourseList().get(i).getId());
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
                        Section s = new Section(name, day, lt, room, roomLmt, AD.getDept().getCourseList().get(i));
                        s.setStatus(status);

                        for (int j = 0; j < AD.getInstructorList().size(); j++) {
                            if (AD.getInstructorList().get(j).getId() == instrId) {
                                s.setInstructor(AD.getInstructorList().get(j));
                                break;
                            }
                        }

                        sList.add(s);
                    }
                    AD.getDept().getCourseList().get(i).setSectionList(sList);

                } catch (SQLException ex) {
                    System.out.println(ex.getErrorCode());
                }
            }
        } else {
            System.out.println("Connection not availabale");
        }
    }

    public AdminHome(int id) {
        initComponents();
        ID = id;
        fetchData(ID);
        fetchInstructor_Student();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        department = new javax.swing.JLabel();
        password = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Home");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel1.setText("Admin Home");

        jLabel2.setText("Name:");

        jLabel3.setText("Username:");

        jLabel4.setText("Department:");

        jLabel5.setText("Password:");

        jButton1.setText("View Students");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("View Instructors");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("View Courses");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("View Sections");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Add Student");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Add Instructor");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Add Course");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Add Section");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Logout");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addComponent(jButton4)
                                    .addComponent(jButton7))
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton5)
                                            .addComponent(jButton8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton9)
                                            .addComponent(jButton6))))))))
                .addContainerGap(49, Short.MAX_VALUE))
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
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new ViewStudents(AD).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new ViewInstructors(AD).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new ViewCourses(AD).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new ViewSections(AD).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        new AddInstructor(AD).setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        new AddSection(AD).setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new AddStudent(AD).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        new AddCourse(AD).setVisible(true);
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
            java.util.logging.Logger.getLogger(AdminHome.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHome(id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel department;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
