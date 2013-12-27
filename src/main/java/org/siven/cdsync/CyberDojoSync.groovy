package org.siven.cdsync;

import org.openqa.selenium.firefox.FirefoxDriver
//import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class CyberDojoSync {
	
	private CyberDojo cyberDojo;

	public CyberDojoSync( String url, String syncDir){
		cyberDojo(url, syncDir)
	}
	
	public CyberDojoSync(String syncDir){
		String url = new File(syncDir+".cyber-dojo").text
		cyberDojo(url,syncDir)
	}
	
	public CyberDojoSync(){
		this("./");
	}
	
	public void setPath(String path){
		cyberDojo.outputPath = path
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
	
	private void cyberDojo(String url, String syncDir){
		cyberDojo = new CyberDojo(new FirefoxDriver())
		cyberDojo.setOutputPath(syncDir)
		cyberDojo.getUrl(url);
	}
	
	public static void main (String... args){
		if(args.length==0){
			println "Cyber-Dojo_Sync"
			println "Save url to kata in a file called .cyber-dojo"
			println "CyberDojoSync get  //Fetches the files from kata"
			println "CyberDojoSync test //Uploads files runs tests and gets the output"
		}
		if(args[0]=="get"){
			CyberDojoSync cds = new CyberDojoSync()
			cds.getFiles();
			cds.exit();
		} else if(args[0]=="test"){
			CyberDojoSync cds = new CyberDojoSync()
			cds.test();
			cds.exit();
		} else{
			println "Cyber-Dojo_Sync"
			println "Save url to kata in a file called .cyber-dojo"
			println "CyberDojoSync get  //Fetches the files from kata"
			println "CyberDojoSync test //Uploads files runs tests and gets the output" 
		}
	}
}
