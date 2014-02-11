package main;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class MSManager {
	
	public File modFolder;
	
	private List<ModContainer> enableMod;
	private List<ModContainer> disableMod;
	
	public MSManager() {
		this.enableMod = new LinkedList<ModContainer>();
		this.disableMod = new LinkedList<ModContainer>();
		
	}
	
	public void loadMods(File modFolder) {
		
		this.modFolder = modFolder;
		
		this.enableMod.clear();
		this.disableMod.clear();
		
		for (File mod : modFolder.listFiles()) {
			String modName = mod.getName();
			if (modName.endsWith(".jar") || modName.endsWith(".zip"))
				this.enableMod.add(new ModContainer(mod));
		}
		
		for (File mod : ModSwitcher.getDisableFolder(modFolder).listFiles()) {
			String modName = mod.getName();
			if (modName.endsWith(".jar") || modName.endsWith(".zip"))
				this.disableMod.add(new ModContainer(mod));
		}
	}
	
	public boolean enable(ModContainer mod) {
		
		System.out.println(mod.modName + " is enabled");
		
		boolean flag1 = false;
		boolean flag2 = false;
		if (this.disableMod.contains(mod)) {
			disableMod.remove(mod);
			flag1 = true;
		}
		if (!this.enableMod.contains(mod)) {
			enableMod.add(mod);
			flag2 = true;
		}
		mod.modFile.renameTo(new File(mod.modFile.getAbsolutePath().replaceFirst(mod.modFile.getName(), "disabled" + File.separator + mod.modFile.getName())));
		return flag1 && flag2 && true;
	}
	
	public boolean disable(ModContainer mod) {
		
		System.out.println(mod.modName + " is disabled");

		boolean flag1 = false;
		boolean flag2 = false;
		if (this.enableMod.contains(mod)) {
			enableMod.remove(mod);
			flag1 = true;
		}
		if (!this.disableMod.contains(mod)) {
			disableMod.add(mod);
			flag2 = true;
		}
		mod.modFile.renameTo(new File(mod.modFile.getAbsolutePath().replaceFirst(File.separator + "disabled", "")));
		return flag1 && flag2 && true;
	}
	
	public List<ModContainer> getList(String ed) {
		if (ed == "e")
			return this.enableMod;
		else if (ed == "d")
			return this.disableMod;
		else return null;
	}
	
	public ModContainer getContainerFromString(String ed, String name) {
		List<ModContainer> list = null;
		if (ed == "e")
			list = this.enableMod;
		else if (ed == "d")
			list = this.disableMod;
		
		for (ModContainer mod : list) {
			if (mod.modName == name) 
				return mod;
		}
		System.out.println(name + " is nowhere");
		return null;
	}

}
