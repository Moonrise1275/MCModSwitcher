package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.MSManager;
import main.ModContainer;

public class GuiManager {
	
	private DefaultListModel<ModContainer> enableList;
	private DefaultListModel<ModContainer> disableList;
	
	public boolean[] isSelectedEnable;
	public boolean[] isSelectedDisable;
	
	public ActionHandler action;
	
	public JLabel pathLabel;
	public JTextField path;
	public Button btnGO;
	
	public Button btn1;
	public Button btn2;
	public Button btn3;
	
	public JList<ModContainer> enable;
	public JList<ModContainer> disable;
	
	public JScrollPane scrollEnable;
	public JScrollPane scrollDisable;
	
	public JPanel top;
	public JPanel bottom;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GuiManager() {
		this.enableList = new DefaultListModel<ModContainer>();
		this.disableList = new DefaultListModel<ModContainer>();
		
		isSelectedEnable = new boolean[300];
		isSelectedDisable = new boolean[300];
		for (@SuppressWarnings("unused") boolean b : isSelectedEnable) {
			b = false; }
		for (@SuppressWarnings("unused") boolean b : isSelectedDisable) {
			b = false; }
				
		pathLabel = new JLabel("Mod Folder");
		path = new JTextField(25);
		btnGO = new Button("Go");
		
		btn1 = new Button("Enable");
		btn2 = new Button("Disable");
		btn3 = new Button("Exit");
		btn1.addActionListener(action);
		btn2.addActionListener(action);
		btn3.addActionListener(action);
		
		enable = new JList(enableList);
		disable = new JList(disableList);
		enable.addListSelectionListener( 
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						if(!e.getValueIsAdjusting()) {
							for (int i=e.getFirstIndex(); i<=e.getLastIndex(); i++) {
								isSelectedEnable[i] = !isSelectedEnable[i];
							}
						}
					}
				});
		disable.addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						if(!e.getValueIsAdjusting()) {
							for (int i=e.getFirstIndex(); i<=e.getLastIndex(); i++) {
								isSelectedDisable[i] = !isSelectedDisable[i];
							}
						}
					}
				});
		
		scrollEnable = new JScrollPane(enable);
		scrollDisable = new JScrollPane(disable);
		
		top = new JPanel();
		bottom = new JPanel();
		top.setLayout(new FlowLayout());
		bottom.setLayout(new FlowLayout());
		
		top.add(pathLabel);
		top.add(path);
		top.add(btnGO);
		
		bottom.add(btn1, BorderLayout.CENTER);
		bottom.add(btn2, BorderLayout.CENTER);
		bottom.add(btn3, BorderLayout.CENTER);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void loadList(MSManager manager) {
		this.enableList.clear();
		this.disableList.clear();
		
		for (ModContainer mod : manager.getList("e")) {
			enableList.addElement(mod);
		}
		for (ModContainer mod : manager.getList("d")) {
			disableList.addElement(mod);
		}
		
		enable = new JList(enableList);
		disable = new JList(disableList);
		
		enable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		enable.setLayoutOrientation(JList.VERTICAL);
		enable.setVisibleRowCount(20);
		
		disable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		disable.setLayoutOrientation(JList.VERTICAL);
		disable.setVisibleRowCount(20);
	}

}
