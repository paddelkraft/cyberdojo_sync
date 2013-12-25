package org.siven.cdsync;

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class CyberDojoSync {
	
	private CyberDojo cyberDojo;

	public CyberDojoSync( String url, String syncDir){
		cyberDojo(url, syncDir)
	}
	
	public CyberDojoSync(String syncDir){
		String url = new File(syncDir+".cyber-dojo").text
		cyberDojo(url,syncDir)
	}
	
	public void setPath(String path){
		cyberDojo.output = path
	}
	
	public void downloadFile(int index){
		cyberDojo.downloadFile(index)
	}
	
	public void getFiles(){
		cyberDojo.downloadFiles();
	}
	
	public void uploadFiles(){
		cyberDojo.uploadFiles()
	}
	
	public void test(){
		cyberDojo.uploadFiles();
		cyberDojo.runTests()
		cyberDojo.downloadFile("output")
	}
	
	public void exit(){
		cyberDojo.exit()
	}
	
	private cyberDojo(String url, String syncDir){
		cyberDojo = new CyberDojo(new FirefoxDriver())
		cyberDojo.setOutput(syncDir)
		cyberDojo.getUrl(url);
	}
}
