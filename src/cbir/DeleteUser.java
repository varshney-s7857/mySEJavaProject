package cbir;


import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

public class DeleteUser extends JFrame implements ActionListener{

	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JLabel usernamelbl = null;
	private JTextField userName = null;
	
	private JLabel rolelbl = null;
	private JLabel passwordlbl = null;
	private JPasswordField password = null;
	private JLabel cpasswordlbl = null;
	private JPasswordField cpassword = null;
	private JButton submit = null;
	private JButton reset = null;
	private JComboBox role = null;
	private JButton delete = null;
	Container content;

	
	public DeleteUser() {
		super();
		initialize();
		content = getContentPane();
		content.setBackground(Color.PINK);
		
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
		
	}
	private void initialize() {
        this.setSize(new java.awt.Dimension(600,400));
        this.setContentPane(getJPanel());
        this.setTitle("Delete User !");
        this.setVisible(true);	
	}

	
	
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			
			rolelbl = new JLabel();
			rolelbl.setBounds(new java.awt.Rectangle(32,121,116,30));
			rolelbl.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			rolelbl.setText("User Role :");
			rolelbl.setForeground(new java.awt.Color(200,118,20));
reset = new JButton();
reset.setBounds(new java.awt.Rectangle(260,240,91,27));
reset.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
reset.setText("Reset");
reset.setForeground(new java.awt.Color(200,118,20));
reset.addActionListener(this);

usernamelbl = new JLabel();
usernamelbl.setBounds(new java.awt.Rectangle(32,45,156,30));
usernamelbl.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
usernamelbl.setText("User ID :");
usernamelbl.setForeground(new java.awt.Color(200,118,20));

delete=new JButton();
delete.setBounds(new java.awt.Rectangle(160,240,91,27));
delete.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
delete.setText("Delete");
delete.setForeground(new java.awt.Color(200,118,20));
delete.addActionListener(this);

jPanel1 = new JPanel();
jPanel1.setLayout(null);
jPanel1.setBounds(new java.awt.Rectangle(22,35,550,300));
jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Delete User !", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14), java.awt.Color.red));
jPanel1.add(usernamelbl, null);
jPanel1.add(getUserName(), null);
jPanel1.add(rolelbl, null);

jPanel1.add(reset, null);
jPanel1.add(getRole(), null);
jPanel1.add(delete, null);

}
return jPanel1;
}

	
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(getJPanel1(), null);
		}
		return jPanel;
	}

	
	private JTextField getUserName() {
		if (userName == null) {
			userName = new JTextField();
			userName.setBounds(new java.awt.Rectangle(256,45,210,33));
		}
		return userName;
	}

	
	private JComboBox getRole() {
		if (role == null) {
			role = new JComboBox();
			role.setBounds(new java.awt.Rectangle(258,126,210,33));
			role.addItem("Select Role");
			role.addItem("Member");
			
		}
		return role;
	}

	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Delete"))
		{
			String userID=userName.getText();
			String role1=role.getSelectedItem().toString();
			UserDAO UD = new UserDAO();
			if(!userID.equals("") && role1.equalsIgnoreCase("member"))
			{
				UD.deleteUser(userID);
				JOptionPane.showMessageDialog(rootPane,"User is Deleted successfully");
				userName.setText("");
				role.setSelectedIndex(0);
			}
		    else
			{
					JOptionPane.showMessageDialog(rootPane,"User is not Avilable");
			}
		}
	
	    else
		{
				
			
		if(ae.getActionCommand().equals("Reset"))
		{
			userName.setText("");
			role.setSelectedIndex(0);
                        userName.requestFocus();
					
		}

		return;
	}

	}
}

