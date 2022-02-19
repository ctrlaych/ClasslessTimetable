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
import javax.swing.JOptionPane;

/**
 *
 * @author Hammad
 */
public class AddSection extends javax.swing.JFrame {

    /**
     * Creates new form AddSection
     */
    Admin AD;

    public final void fillCList() {
        for (int i = 0; i < AD.getDept().getCourseList().size(); i++) {
            cList.addItem(AD.getDept().getCourseList().get(i).getName());
        }
    }

    public AddSection(Admin ad) {
        initComponents();
        AD = ad;
        fillCList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cList = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        dayList = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        room = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        roomLmt = new javax.swing.JTextField();
        timeList = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admin Home -> Add Section");
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Enter name:");

        name.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel1.setText("Add Section");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Enter day:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Enter time:");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cList.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cListItemStateChanged(evt);
            }
        });
        cList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cListFocusGained(evt);
            }
        });
        cList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cListMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cListMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cListMouseReleased(evt);
            }
        });
        cList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cListActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Select course:");

        dayList.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        dayList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        dayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayListActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Enter room:");

        room.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Enter room limit:");

        roomLmt.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        roomLmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomLmtActionPerformed(evt);
            }
        });

        timeList.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        timeList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        timeList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dayList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(timeList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(room)
                                        .addComponent(roomLmt, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dayList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(timeList, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(room, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(roomLmt, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (name.getText().isEmpty() || room.getText().isEmpty() || roomLmt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all fields");
        } else if (cList.getSelectedItem().equals("Select") || dayList.getSelectedItem().equals("Select") || timeList.getSelectedItem().equals("Select")) {
            JOptionPane.showMessageDialog(null, "Please select an option");
        } else {
            Connection dbconn = Database.con();
            int secId = 0;
            int courseId = 0;

            if (dbconn != null) {
                try {
                    PreparedStatement st;
                    st = dbconn.prepareStatement("select * from section");
                    ResultSet res = st.executeQuery();

                    while (res.next()) {
                        secId = res.getInt("id");
                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                secId++;

                try {
                    PreparedStatement st;
                    st = dbconn.prepareStatement("select * from course where name = ?");
                    st.setString(1, cList.getSelectedItem().toString());
                    ResultSet res = st.executeQuery();

                    if (res.next()) {
                        courseId = res.getInt("id");
                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }

                try {
                    PreparedStatement st;
                    st = dbconn.prepareStatement("insert into section values (?,?,?,?,?,?,?,?,?)");
                    st.setInt(1, secId);
                    st.setString(2, name.getText().toString());
                    st.setString(3, dayList.getSelectedItem().toString());
                    st.setString(4, timeList.getSelectedItem().toString());
                    st.setString(5, room.getText().toString());
                    st.setInt(6, Integer.parseInt(roomLmt.getText().toString()));
                    st.setInt(7, courseId);
                    st.setInt(8, 0);
                    st.setInt(9, 0);
                    st.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }

                DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime lt = LocalTime.parse(timeList.getSelectedItem().toString(), df);

                Course C = new Course();
                int index = 0;
                for (int i = 0; i < AD.getDept().getCourseList().size(); i++) {
                    if (AD.getDept().getCourseList().get(i).getId() == courseId) {
                        C = AD.getDept().getCourseList().get(i);
                        index = i;
                        break;
                    }
                }

                Section s = new Section(name.getText().toString(), dayList.getSelectedItem().toString(), lt,
                        room.getText().toString(), Integer.parseInt(roomLmt.getText().toString()), C);
                AD.getDept().getCourseList().get(index).getSectionList().add(s);

                JOptionPane.showMessageDialog(null, "Section added successfully");
                this.dispose();

            } else {
                System.out.println("Connection not availabale");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cListActionPerformed
        // TODO add your handling code here:
        timeList.removeAllItems();
        timeList.addItem("Select");
        dayList.removeAllItems();
        dayList.addItem("Select");
        
        if (cList.getSelectedItem().toString().contains(" Lab")) {
            dayList.addItem("Monday");
            dayList.addItem("Tuesday");
            dayList.addItem("Wednesday");
            dayList.addItem("Thursday");
            dayList.addItem("Friday");
            
            timeList.addItem("08:00:00");
            timeList.addItem("11:00:00");
            timeList.addItem("02:00:00");
            
        } else if (!cList.getSelectedItem().toString().equals("Select")) {
            
            dayList.addItem("Monday");
            dayList.addItem("Tuesday");
            dayList.addItem("Wednesday");
            
            timeList.addItem("08:00:00");
            timeList.addItem("09:30:00");
            timeList.addItem("11:00:00");
            timeList.addItem("12:30:00");
            timeList.addItem("02:00:00");
            timeList.addItem("03:30:00");
            timeList.addItem("05:00:00");
        }
    }//GEN-LAST:event_cListActionPerformed

    private void dayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayListActionPerformed

    private void roomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomActionPerformed

    private void roomLmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomLmtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomLmtActionPerformed

    private void timeListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeListActionPerformed

    private void cListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cListMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cListMouseReleased

    private void cListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cListMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cListMouseClicked

    private void cListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cListItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cListItemStateChanged

    private void cListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cListMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cListMousePressed

    private void cListFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cListFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cListFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[], Admin ad) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    System.out.println("taki taki");
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddSection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddSection(ad).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cList;
    private javax.swing.JComboBox<String> dayList;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField name;
    private javax.swing.JTextField room;
    private javax.swing.JTextField roomLmt;
    private javax.swing.JComboBox<String> timeList;
    // End of variables declaration//GEN-END:variables
}
