package org.siven.cdsync;



public class CyberDojoSync {

	private CyberDojo cyberDojo;

	public CyberDojoSync(String syncDir, String url){
		cyberDojo(syncDir,url)
	}

	public CyberDojoSync(String syncDir){
			cyberDojo(syncDir)
	}

	public CyberDojoSync(){
		def localFs = new LocalFileSystem()
		File urlFile = localFs.getFile(".cyber-dojo")
		if (!urlFile.exists()){
			createUrlFile()
		} 
		cyberDojo()
	}
	
	public void createUrlFile(){
		println "Could not find .cyber-dojo file containing a link to the kata to sync "
		print "do you want to create one now? (y/n):"
		if("yY".contains(CommandLine.input.charAt(0))){
			print "What kata url do you want to sync?:"
			new LocalFileSystem().writeToFile( ".cyber-dojo"
										 	 , CommandLine.input)
		} 
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
		if(cyberDojo.localFs.visibleFileCount()==0){
			println "There are no files available locally to test"
			println "run \"CyberDojoSync get\" to fetch the files in the kata"
			return
		}
		cyberDojo.uploadFiles();
		cyberDojo.runTests()
		cyberDojo.downloadFile("output")
	}

	public void exit(){
		cyberDojo.done()
	}

	private void cyberDojo(String syncDir, String url){
		cyberDojo = Factory.getCyberDojo(Factory.getFileSystem(syncDir), Factory.getKata(url))
	}

	private cyberDojo(String syncDir) {
		cyberDojo = Factory.getCyberDojo(Factory.getFileSystem(syncDir))
		
	}
	
	private void cyberDojo(){
		cyberDojo = Factory.getCyberDojo()
		
	}

	
	public static void main (String... args){
		if(args.length==0){
			
			help()
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
				help()
			}
		}catch (Exception e){
			println e.message
		}
	}

	private static help() {
		println "Cyber-Dojo_Sync"
		println "Save url to kata in a file called .cyber-dojo"
		println "CyberDojoSync get  //Fetches the files from kata"
		println "CyberDojoSync test //Uploads files runs tests and gets the output"
	}
}
