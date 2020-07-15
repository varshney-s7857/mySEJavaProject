package cbir;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.color.*;

public class User_Login extends JFrame{

	
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JPanel loginpanel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	
	private JLabel userNamelabel = null;
	private JTextField userName = null;
	private JLabel passwordlabel = null;
	private JButton submit = null;
	private JButton reset = null;
	private JPasswordField password = null;
	private JLabel errorlabel = null;

	public User_Login() {
		super();
		initialize();
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
        this.setSize(new java.awt.Dimension(750,600));
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setContentPane(getJPanel());
        this.setTitle("Content Based Image Retrieval System Login Page");
        
			
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new java.awt.Rectangle(100,30,800,27));
			jLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 23));
			jLabel.setText("Content Based Image Retrieval System");
			jLabel.setForeground(new java.awt.Color(150,57,120));
			
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, null);
		
			
			jPanel.add(getLoginpanel(), null);
			jPanel.setBackground(Color.pink);
		}
		return jPanel;
	}

	
	private JPanel getLoginpanel() {
		if (loginpanel == null) {
			errorlabel = new JLabel();
			errorlabel.setBounds(new java.awt.Rectangle(125,240,345,33));
			errorlabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			errorlabel.setForeground(new java.awt.Color(249,57,4));
			errorlabel.setText("");
			passwordlabel = new JLabel();
			passwordlabel.setBounds(new java.awt.Rectangle(76,105,103,27));
			passwordlabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			passwordlabel.setText("User Pass :");
			passwordlabel.setForeground(java.awt.Color.blue);
			userNamelabel = new JLabel();
			userNamelabel.setBounds(new java.awt.Rectangle(74,49,106,26));
			userNamelabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14));
			userNamelabel.setForeground(java.awt.Color.blue);
			userNamelabel.setText("User ID :");
			loginpanel = new JPanel();
			loginpanel.setLayout(null);
			loginpanel.setBounds(new java.awt.Rectangle(125,173,473,278));
			loginpanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Login", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", java.awt.Font.BOLD, 18), new java.awt.Color(150,57,120)));
			
			loginpanel.add(userNamelabel, null);
			loginpanel.add(getUserName(), null);
			loginpanel.add(passwordlabel, null);
			
			loginpanel.add(getSubmit(), null);
			loginpanel.add(getReset(), null);
			loginpanel.add(getPassword(), null);

			loginpanel.add(errorlabel, null);
		}
		return loginpanel;
	}

	
	private JTextField getUserName() {
		if (userName == null) {
			userName = new JTextField();
			userName.setBounds(new java.awt.Rectangle(213,52,205,25));
		}
		return userName;
	}

	
	private JButton getSubmit() {
		if (submit == null) {
			submit = new JButton();
			submit.setBounds(new java.awt.Rectangle(213,215,86,26));
			submit.setText("Login");
			submit.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
			submit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					UserDAO UD = new UserDAO();
					if(!userName.getText().equals("")&&!password.getText().equals(""))
					{
						
						try
						{
						if(UD.verifyUser(userName.getText(),password.getText()))
						{
							if(UD.getRole().equals("a"))
							{
								
								int flag=1;
								if(flag==1)
								{
									
							    new frmAdminAccount(userName.getText()).setVisible(true);
								}
								else
								{
									System.out.println("Hardware Problem !");
								}
							    dispose();
							}
							else if(UD.getRole().equals("m"))
							{
								int flag=1;
								if(flag==1)
								{
									new frmUserAccount(userName.getText()).setVisible(true);
								}
								else
								{
									System.out.println("Hardware Problem !");
								}
																
								dispose();
							}

						}
						else
						{
							password.setText("");
							errorlabel.setText("Invalid User Id or Password");
						}
						}
						catch(Exception exception)
						{
							exception.printStackTrace();
						}
					
					}
					else
						errorlabel.setText("Enter Your User Id And Password");
					
				
				}
			});
		}
		return submit;
	}

	
	private JButton getReset() {
		if (reset == null) {
			reset = new JButton();
			reset.setBounds(new java.awt.Rectangle(341,215,68,26));
			reset.setText("Reset");
			reset.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 12));
			reset.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				    userName.setText("");
				    password.setText("");
				}
			});
		}
		return reset;
	}

	private JPasswordField getPassword() {
		if (password == null) {
			password = new JPasswordField();
			password.setBounds(new java.awt.Rectangle(213,106,205,24));
		}
		return password;
	}
	

}
