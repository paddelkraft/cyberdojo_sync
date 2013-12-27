package org.siven.cdsync

public class CyberDojo {

	

	private Kata kata;
	private outputPath = "./"

	public CyberDojo() {
		this.kata = new Kata ()
	}

	public void setOutputPath(String output){
		this.outputPath = output
	}

	public void getUrl(String url){
		println "Open "+url
		kata.url = url;
	}

	public void downloadFiles(){
		kata.fileNames.each {
			downloadFile(it)
		}
	}

	public void downloadFile(String fileName){
		writeToLocalFile(fileName, kata.fileContent(fileName))
	}

	public void uploadFiles(){
		removeMissingFiles()
		new File(outputPath).eachFile() {
		if(!hiddenFile(it.name))
			kata.updateFile(it.name, readLocalFile(it.name))
		}
	}

	public void runTests(){
		kata.test()
	}

	public void done(){
		kata.done()
	}
	
	//Local file
	private boolean hiddenFile(String fileName){
		fileName.charAt(0)=='.'
	}

	private void removeMissingFiles(){
		kata.fileNames.each {
			if(!localFileExists(it)){
				kata.deleteWebFile(it)
			}
		}
	}

	private void writeToLocalFile(String fileName, String content) {
		new File(outputPath+fileName).write(content)
	}

	private String readLocalFile(String fileName){
		new File(outputPath+fileName).text
	}

	private boolean localFileExists(String fileName){
		new File(outputPath+fileName).exists()
	}


	

	

}
