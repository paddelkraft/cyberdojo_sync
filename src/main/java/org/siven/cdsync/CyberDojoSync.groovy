package org.siven.cdsync;



public class CyberDojoSync {

	private CyberDojo cyberDojo;

	public CyberDojoSync( String url, String syncDir){
		cyberDojo(url, syncDir)
	}

	public CyberDojoSync(String syncDir){
		File urlFile =new File(syncDir+".cyber-dojo")
		if (urlFile.exists()){
			String url = new File(syncDir+".cyber-dojo").text
			cyberDojo(url,syncDir)
		} else {
			throw new Exception("No .cyber-dojo file found")
		}
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
		cyberDojo.done()
	}

	private void cyberDojo(String url, String syncDir){
		cyberDojo = new CyberDojo()
		cyberDojo.setOutputPath(syncDir)
		cyberDojo.getUrl(url);
	}

	public static void main (String... args){
		if(args.length==0){
			println "Cyber-Dojo_Sync"
			println "Save url to kata in a file called .cyber-dojo"
			println "CyberDojoSync get  //Fetches the files from kata"
			println "CyberDojoSync test //Uploads files runs tests and gets the output"
			return
		}
		try{
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
		}catch (Exception e){
			println e.message
		}
	}
}
