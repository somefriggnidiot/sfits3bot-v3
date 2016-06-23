package core;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Core {
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//Config.loadConfig();
            	BotUi.createAndShowGUI();
            }
        });
	}
	
	public static String getVersion(boolean withBuild) {
		/**	MAJOR CHANGE NOTES
		 * 		Version 0
		 * 			Information largely stored in fields.
		 * 		Version 1
		 * 			Information moved to context menus and tool-bar.
		 * 			Console view is primary focus.
		 * 		Version 2
		 * 			
		 */
		int major = 2;
		
		/**	MINOR CHANGE NOTES
		 * 		TODO: Set bot to read/write server as little as possible.
		 */
		int minor = 0;
		
		/**	RELEASE NOTES
		 * 
		 */
		int release = 0; 	
		
		int build = 0;
		
		if (withBuild) {
			return "v." + major + "." + minor + "." + release + "." + build;
		} else {
			return "v." + major + "." + minor + "." + release;
		}
	}
	
	public static void confirmExit() {
		if (BotUi.getTitle().contains("-")) {
			JPanel panel = new JPanel();
			JLabel label = new JLabel("The bot is still connected. Are you sure you wish to exit?");
			panel.add(label);
			int confirm = JOptionPane.showConfirmDialog(BotUi.getFrame(), panel, "Warning!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if (confirm == JOptionPane.OK_OPTION) {
				System.exit(0);
			} else { 
				return;
			}
		} else { 
			System.exit(0);
		}
	}

	public static String timeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return("[" + dateFormat.format(System.currentTimeMillis()) + "] ");
	}

	public static Object getInfoPanel() {
		//TODO Create webpage and display that instead.
		JPanel panel = new JPanel(new GridBagLayout());
		JLabel label;
		GridBagConstraints c = new GridBagConstraints();
		
		label = new JLabel("<html>"
				+ "This bot is currently in active development, and features are likely to break frequently and inconsitently.<br />"
				+ "For questions, suggestions, and feedback, send an email to <a href=\"mailto:idiot@somefriggnidiot.com\">idiot@somefriggnidiot.com</a><br />"
				+ "<br />"
				+ "Full Version ID: " + getVersion(true)
				+ "</html>");
		
		c.gridx = 0;
		c.gridy = 0;
		panel.add(label, c);
		
		label = new JLabel("<html>For questions, suggestions, and feedback, send an email to <a href=\"mailto:idiot@somefriggnidiot.com\">idiot@somefriggnidiot.com</a></html>");
		c.gridy = 1;
		panel.add(label, c);
		
		label = new JLabel("Full Version Information: " + getVersion(true));
		c.gridy = 2;
		panel.add(label, c);
		
		return panel;
	}
}
