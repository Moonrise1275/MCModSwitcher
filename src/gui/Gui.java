package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import main.ModSwitcher;

public class Gui {
	
	public static void mainFrame() {
		Frame frm = new Frame(ModSwitcher.NAME);
		GuiManager guiManager = ModSwitcher.guiManager;
		
		frm.setBounds(200, 100, 700, 600);
		frm.setLayout(new BorderLayout());
		
		WindowListener listen = new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		};
		
		ActionListener action = ModSwitcher.action;
		
		frm.addWindowListener(listen);
		
		guiManager.btnGO.addActionListener(action);
		guiManager.btn1.addActionListener(action);
		guiManager.btn2.addActionListener(action);
		guiManager.btn3.addActionListener(action);

		
		guiManager.btn1.setEnabled(false);
		guiManager.btn2.setEnabled(false);
		guiManager.btn3.setEnabled(false);
		
		frm.add(guiManager.scrollEnable, BorderLayout.WEST);
		frm.add(guiManager.scrollDisable, BorderLayout.EAST);
		

		frm.add(guiManager.top, BorderLayout.NORTH);
		frm.add(guiManager.bottom, BorderLayout.CENTER);
		
		frm.setVisible(true);
		guiManager.top.setVisible(true);
		guiManager.bottom.setVisible(true);
		
	}

}
