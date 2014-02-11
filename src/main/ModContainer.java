package main;

import java.io.File;

public class ModContainer {
	
	public File modFile;
	public String modName;
	
	public ModContainer(File file) {
		this.modFile = file;
		this.modName = getName(file);
		
	}
	
	public static String getName(File file) {
		String name = file.getName();
		if (name.endsWith(".disabled"))
				name.replace(".disabled", "");
		return name;
	}
	
	@Override
	public String toString() {
		return this.modName;
	}

}
