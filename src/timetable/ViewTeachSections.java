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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hammad
 */
public class ViewTeachSections extends javax.swing.JFrame {

    /**
     * Creates new form ViewTeachSections
     */
    Instructor T;
    ArrayList<Section> secList2 = new ArrayList<Section>();

    public final void fillTable() {
        DefaultTableModel tbModel = (DefaultTableModel) table.getModel();
        for (int i = 0; i < T.getSectionList().size(); i++) {
            LocalTime lt = T.getSectionList().get(i).getTime();
            String secDay = "";

            if (!T.getSectionList().get(i).getCourse().getName().contains(" Lab")) {
                lt = lt.plusHours(1);
                lt = lt.plusMinutes(30);

                if (T.getSectionList().get(i).getDay().equals("Monday")) {
                    secDay = ", Wednesday";
                } else if (T.getSectionList().get(i).getDay().equals("Tuesday")) {
                    secDay = ", Thursday";
                } else if (T.getSectionList().get(i).getDay().equals("Wednesday")) {
                    secDay = ", Friday";
                }
            } else {
                lt = lt.plusHours(3);
            }
            
            String timing = "";
            String day = "";
            timing = T.getSectionList().get(i).getTime().toString() + " - " + lt.toString();
            day = T.getSectionList().get(i).getDay() + secDay;
            
            tbModel.insertRow(tbModel.getRowCount(), new Object[]{T.getSectionList().get(i).getName(),
            day,timing, T.getSectionList().get(i).getRoom(),
                T.getSectionList().get(i).getRoomLimit(), T.getSectionList().get(i).getCourse().getName()});
        }
    }

    public final void fetchSections2() {
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
                us.setInt(1, T.getId());
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
                    secList2.add(s);
                }

            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
            }

        } else {
            System.out.println("Connection not availabale");
        }
    }
    
    public ViewTeachSections(Instructor t) {
        initComponents();
        T = t;
        fetchSections2();
        T.setSectionList(secList2);
        fillTable();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel1.setText("View Sections");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Day", "Time", "Room", "Room Limit", "Course"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[], Instructor t) {
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
            java.util.logging.Logger.getLogger(ViewTeachSections.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewTeachSections.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewTeachSections.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewTeachSections.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewTeachSections(t).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
