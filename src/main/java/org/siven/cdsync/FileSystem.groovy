package org.siven.cdsync;

import java.io.File;

public interface FileSystem {

	public List<String> getFiles();

	public void writeToFile(String fileName, String content);

	public String readFile(String fileName);

	public boolean fileExists(String fileName);
	
	public File getFile(String fileName );

	public int visibleFileCount();
	
	public boolean isHiddenFile(String fileName)

}