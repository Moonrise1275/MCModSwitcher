package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import main.MSManager;
import main.ModContainer;

public class ActionHandler implements ActionListener {
		
	public MSManager manager;
	public GuiManager guiManager;
	
	public ActionHandler(MSManager man, GuiManager gui) {
		this.manager = man;
		this.guiManager = gui;
	}
	
	public void actionPerformed(ActionEvent evt) {
		System.out.println(evt.paramString());
		String type = evt.getActionCommand();
		
		switch(EnumButton.valueOf(type)) {
		case Go:
			File modFolder = new File(guiManager.path.getText());
			new File(new StringBuilder(guiManager.path.getText()).append(File.separator + "disabled").toString()).mkdir();
			
			manager.loadMods(modFolder);
			guiManager.loadList(manager);
			guiManager.btn1.setEnabled(true);
			guiManager.btn2.setEnabled(true);
			guiManager.btn3.setEnabled(true);
			break;
			
		case Enable:
			for (ModContainer mod : guiManager.disable.getSelectedValuesList()) {
				System.out.println(mod + " is enabled");
				manager.enable(mod);
			}
			guiManager.loadList(manager);
			break;
			
		case Disable:
			for (ModContainer mod : guiManager.enable.getSelectedValuesList()) {
				System.out.println(mod + " is disabled");
				manager.disable(mod);
			}
			guiManager.loadList(manager);
			break;
			
		case Exit:
			System.exit(0);
			break;
			
		default:
			break;
			
		}
	}
}
