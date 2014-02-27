package org.siven.cdsync

public class CyberDojo {

	

	private CdKata kata;
	private FileSystem localFs
	
	public CyberDojo(){
//		this(LocalFileSystem.CURRENT_DIR)
	}
	
	
//	public CyberDojo(String outputPath) {
//		localFs = new LocalFileSystem(outputPath)
//		File urlFile = localFs.getFile(".cyber-dojo")
//		if (urlFile.exists()){
//			this.kata = new Kata(urlFile.text)
//		} else {
//			throw new Exception("No .cyber-dojo file found")
//		}
//	}
	
//	public CyberDojo(String outputPath, String url) {
//		this(outputPath)
//		this.url = url
//		
//	}
//	
	public void downloadFiles(){
		kata.fileNames.each {
			downloadFile(it)
		}
	}

	public void downloadFile(String fileName){
		println "DownLoad " +fileName 
		localFs.writeToFile(fileName, kata.fileContent(fileName))
	}

	public void uploadFiles(){
		removeMissingFiles()
		localFs.getFiles().each {
		if(!localFs.isHiddenFile(it))
			kata.updateFile(it, localFs.readFile(it))
		}
	}

	public void runTests(){
		kata.test()
	}

	public void done(){
		kata.done()
	}
	
	
	private void removeMissingFiles(){
		kata.fileNames.each {
			if(!localFs.fileExists(it)){
				kata.deleteFile(it)
			}
		}
	}

	public FileSystem getLocalFs(){
		localFs
	}

	

	

}
