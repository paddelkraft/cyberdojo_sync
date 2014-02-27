package org.siven.cdsync;

public class Factory {
	
	public static CyberDojo getCyberDojo(FileSystem fs){
		def cyberDojo = new CyberDojo()
			File urlFile = fs.getFile(".cyber-dojo")
			if (urlFile.exists()){
				cyberDojo.localFs = fs
				cyberDojo.kata = new Kata(urlFile.text)
			} else {
				throw new Exception("No .cyber-dojo file found")
			}
		
		cyberDojo
	}
	
	public static CyberDojo getCyberDojo(){
		getCyberDojo(new LocalFileSystem())
	}
	

	public static CyberDojo getCyberDojo(FileSystem fs, CdKata kata){
		def cyberDojo = new CyberDojo() 		
		cyberDojo.localFs = fs
		cyberDojo.kata = kata
		cyberDojo
	}
	
	public static FileSystem getFileSystem(String path){
		new LocalFileSystem(path)
	}
	
	public static CdKata getKata(String url){
		new Kata (url)
	}


}
