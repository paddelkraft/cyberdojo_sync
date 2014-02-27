package org.siven.cdsync;

import java.util.List;

public interface CdKata {

	public abstract void setUrl(String url);

	public abstract void test();

	public abstract void done();

	public abstract List getFileNames();

	public abstract String fileContent(String fileName);
	
	public void updateFile(String fileName, String fileContents);
	
	public void deleteFile(String fileName);

}