package main;

import java.io.File;

import gui.ActionHandler;
import gui.Gui;
import gui.GuiManager;

public class ModSwitcher {
	
	public static String NAME = "Mod Switcher";
	
	
	public static MSManager manager;
	public static ActionHandler action;
	public static GuiManager guiManager;
	
	public static void main(String[] args) {
		
		manager = new MSManager();
		guiManager = new GuiManager();
		action = new ActionHandler(manager, guiManager);
		
		Gui.mainFrame();
	}
	
	public static File getDisableFolder(File modFolder) {
		String modPath = modFolder.getAbsolutePath();
		return new File(new StringBuilder(modPath).append(File.separator + "disabled").toString());
	}

}