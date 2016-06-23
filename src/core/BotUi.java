package core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import utils.*;

@SuppressWarnings("unused")
public class BotUi {
	private static JFrame frame = new JFrame();
    private static JTextArea console = new JTextArea();
	private static JCheckBoxMenuItem slowModeBox = null;
	private static JCheckBoxMenuItem idleCheckerBox = null;
	private static JCheckBoxMenuItem serverActivityBox = null;
	private static JCheckBoxMenuItem chatDisplayBox = null;
	private static JCheckBoxMenuItem chatCommandsBox = null;
	
    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu, submenu;
        JMenuItem menuItem;
        
        return menuBar;
    }
	
    private Container createContentPane() {
        //Create the content pane.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        console = new JTextArea(20, 70);
        console.setEditable(false);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(console));
		System.setOut(printStream);
		//TODO : Allow only if debug on. 
		//	System.setErr(printStream);
        JScrollPane scrollPane = new JScrollPane(console);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
        scrollBar.addAdjustmentListener(new SmartScroller(scrollPane));

        contentPane.add (scrollPane, BorderLayout.CENTER);

        return contentPane;
    }
	
	public static void createAndShowGUI() 
	{
        //Create and set up the window.
    	frame = new JFrame("sFITs3 Bot (" + Core.getVersion(false) + ")");
    	try {
			frame.setIconImage(Toolkit.getDefaultToolkit().createImage(new URL("http://somefriggnidiot.com/favicon.ico")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//frame.setDefaultCloseOperation(Main.confirmExit());
    	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	frame.addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent we) {
    			Core.confirmExit();
    		}
		});
    	frame.setResizable(true);
    	frame.setPreferredSize(new Dimension(800,400));
    	frame.setMinimumSize(new Dimension(800,400));
        
        BotUi botUi = new BotUi();
        frame.setJMenuBar(botUi.createMenuBar());
        frame.setContentPane(botUi.createContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
        //TODO Tutorial if bot staff groups not set.
	}

	public static String getTitle() {
		return frame.getTitle();
	}

	public static JFrame getFrame() {
		return frame;
	}

	public static void clearConsole() {
		console.setText("");
	}
}
