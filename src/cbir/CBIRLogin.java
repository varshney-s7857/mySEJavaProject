
package cbir;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class CBIRLogin extends JPanel {
	public Image backgroundImage;
        public static JFrame frame = new JFrame("Content Based Image Retrieval Project");
	public static JButton button = null;

	public CBIRLogin() {
		try {
			File file = new File("CBIR1.jpg");
			this.backgroundImage = ImageIO.read(file);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public static void main(String[] args) {
		
		frame.setContentPane(new CBIRLogin());
		frame.getContentPane().setLayout(null);
                
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
        	frame.getContentPane().add(getSubmit());
                
		frame.setSize(700, 525);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	protected void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, null);
	}

	public static JButton getSubmit() {
		if (button == null) {
			button = new JButton();
			button.setText("Enter");
			button.setSize(button.getPreferredSize());
			button.setLocation(300, 400);
			button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				frame.dispose();
       	new User_Login().setVisible(true);
					
					
					
				}
				
			});
		}
		
		return button;
	}
}
