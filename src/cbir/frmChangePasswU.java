
package cbir;

/**
 *
 * @author saurabh
 */

import java.sql.*;
import javax.swing.JOptionPane;

public class frmChangePasswU extends javax.swing.JFrame {
    UserDAO UD = new UserDAO();
Statement st=null;
ResultSet rs=null;
String uname="";

    public frmChangePasswU(String uname) {
        initComponents();
        this.uname=uname;
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        passwNew = new javax.swing.JPasswordField();
        passwOld = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Change Password Module");

        jPanel1.setBackground(new java.awt.Color(255, 204, 51));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Algerian", 0, 18)); // NOI18N
        jLabel1.setText("Change Password module");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(240, 20, 280, 24);

        jPanel2.setBackground(new java.awt.Color(240, 15, 38));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Change Password Panel"));
        jPanel2.setLayout(null);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/create-icon.png"))); // NOI18N
        jButton1.setText("Change");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(180, 310, 130, 40);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-delete.gif"))); // NOI18N
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(360, 310, 140, 40);

        jLabel4.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel4.setText("old password");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(80, 120, 120, 40);

        jLabel6.setFont(new java.awt.Font("Algerian", 0, 14)); // NOI18N
        jLabel6.setText("New password");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(80, 180, 140, 40);

        passwNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwNewActionPerformed(evt);
            }
        });
        jPanel2.add(passwNew);
        passwNew.setBounds(240, 180, 310, 40);

        passwOld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwOldActionPerformed(evt);
            }
        });
        jPanel2.add(passwOld);
        passwOld.setBounds(240, 120, 310, 40);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 60, 700, 430);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String oldp=passwOld.getText();
        String newp=passwNew.getText();
        
        try
        {
            st=UD.getConnection().createStatement();
            rs=st.executeQuery("select * from user_details where userid='"+uname+"'");
            if(rs.next())
            {
                
                if(oldp.equals(rs.getString("password")))
                {
           
            st.execute("update user_details set password='"+newp+"' where userid='"+uname+"'");
            
            JOptionPane.showMessageDialog(rootPane, "Your Password is Successfully Changed");
            passwOld.setText("");
            passwNew.setText("");
            
                }
                else
                {
                JOptionPane.showMessageDialog(rootPane, "Old Password is not Correct ... Try Again ...");
                passwOld.setText("");
                passwNew.setText("");
                passwOld.grabFocus();
                }
                
                
            }
             
            
        }
        catch(Exception ex)
        {
            
        }
       
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void passwNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwNewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwNewActionPerformed

    private void passwOldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwOldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwOldActionPerformed

    public static void main(String args[]) {
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
        //</editor-fold>
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField passwNew;
    private javax.swing.JPasswordField passwOld;
    // End of variables declaration//GEN-END:variables

}
