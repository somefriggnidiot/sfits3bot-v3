package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class BotMenuBar {
	private static JMenuBar menuBar;
	
	public BotMenuBar() {
		menuBar = new JMenuBar();
	}
	
	public static JMenuBar getMenuBar()
	{
		return menuBar;
	}
	
	private static void addFileMenu() {
		JMenuItem menuItem;
		
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Clear Console", KeyEvent.VK_C);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BotUi.clearConsole();
			}
		});
		menu.add(menuItem);
		
        menu.addSeparator();
        
        menuItem = new JMenuItem("Load Config", KeyEvent.VK_L);
        menuItem.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    			Config.loadConfigFile();
	        }
		});
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Save Config", KeyEvent.VK_S);
        menuItem.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    			Config.saveConfigFile();
	        }
		});
        menu.add(menuItem);
	        
        menu.addSeparator();
        
        menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        menuItem.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		Core.confirmExit();
            }
        });
        menu.add(menuItem);
	}
	
	private static void addServerMenu() {
		JMenuItem menuItem;
		
	    JMenu menu = new JMenu("Server");
		menu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Edit Connection Info", KeyEvent.VK_E);
		menuItem.addActionListener(new ActionListener() {
		    @Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(BotUi.getFrame(), ConnectionConfig.createServerInfoPanel(), "Connection Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (option == JOptionPane.OK_OPTION) {
					ConnectionConfig.updateConfig();
					Config.saveConfig();
					
					if (Server.isConnected())
					{
						ConnectionConfig.updateNickname();
					}
					System.out.println(Core.timeStamp() + "Connection information updated.\n\tChanges will take effect next time the bot connects.");
				} else if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
					return;
				}
	    	}
		});
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Connect", KeyEvent.VK_C);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Server.connect();
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Disconnect", KeyEvent.VK_D);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Server.isConnected()) {
					Server.disconnect();
					System.out.println(Core.timeStamp() + "Bot disconnected from console.");
				} else {
					System.out.println(Core.timeStamp() + "Bot is not currently connected to a server.");
				}
			}
		});
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("List Clients", KeyEvent.VK_L);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Server.isConnected()) {
					Server.printOnlineClients();
				} else {
					System.out.println(Core.timeStamp() + "Bot is not currently connected to a server.");
				}
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("List Channels", KeyEvent.VK_H);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Server.isConnected()) {
		    		Server.printChannels();
				} else {
					System.out.println(Core.timeStamp() + "Bot is not currently connected to a server.");
				}
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("List Server Groups", KeyEvent.VK_G);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Server.isConnected()) {
		    		Server.printServerGroups();
				} else {
					System.out.println(Core.timeStamp() + "Bot is not currently connected to a server.");
				}
			}
		});
		menu.add(menuItem);
		
		/*
		 * SUBMENU
		 */
		JMenuItem submenu = new JMenuItem();
		submenu = new JMenu("Print Server Snapshot");
		submenu.setMnemonic(KeyEvent.VK_S);
		menu.add(submenu);
		
		menuItem = new JMenuItem("With Empty Channels", KeyEvent.VK_W);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Server.isConnected()) {
		    		Server.printSnapshot(false);
				} else {
					System.out.println(Core.timeStamp() + "Bot is not currently connected to a server.");
				}
			}
		});
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Without Empty Channels", KeyEvent.VK_O);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Server.isConnected()) {
		    		Server.printSnapshot(true);
				} else {
					System.out.println(Core.timeStamp() + "Bot is not currently connected to a server.");
				}
			}
		});
		submenu.add(menuItem);
		/*
		 * END SUBMENU
		 */
	}
	
	private static void addHelpMenu() {
		JMenuItem menuItem;
		
		JMenu menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Information", KeyEvent.VK_E);
		menuItem.addActionListener(new ActionListener()
		{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showOptionDialog(BotUi.getFrame(), Core.getInfoPanel(), ("SFI Bot " + Core.getVersion(true) + " Information"), JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
		}
		});
		menu.add(menuItem);
	}
	
	private static void addModuleMenu(List<Module> modules) {
		
	}
	
	enum Module {
		Module0(0), Module1(1), Module2(2), Module3(3);
		
		private Integer value;			
		Module(Integer value) {
			this.value = value;
		}
		
	}
	
}
