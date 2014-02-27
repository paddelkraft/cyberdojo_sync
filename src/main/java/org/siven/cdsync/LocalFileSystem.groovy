package org.siven.cdsync;

public class LocalFileSystem implements FileSystem {
	
	static final String CURRENT_DIR = "./"
	
	String outputPath
	
	LocalFileSystem(){
		this(LocalFileSystem.CURRENT_DIR)
	}
	
	LocalFileSystem(String outputPath){
		this.outputPath = outputPath
	}
	
	public boolean isHiddenFile(String fileName){
		fileName.startsWith(".")
	}
	
	/* (non-Javadoc)
	 * @see org.siven.cdsync.FileSystem#outputDir()
	 */
	public List<String> getFiles(){
		def dir = new File (outputPath)
		List<String> files = new ArrayList<String>()
		dir.eachFile() {
				files.add(it.name)
		}
		files
	}

	/* (non-Javadoc)
	 * @see org.siven.cdsync.FileSystem#writeToFile(java.lang.String, java.lang.String)
	 */
	public void writeToFile(String fileName, String content) {
		new File(outputPath+fileName).write(content)
	}

	/* (non-Javadoc)
	 * @see org.siven.cdsync.FileSystem#readFile(java.lang.String)
	 */
	public String readFile(String fileName){
		new File(outputPath+fileName).text
	}

	/* (non-Javadoc)
	 * @see org.siven.cdsync.FileSystem#fileExists(java.lang.String)
	 */
	public boolean fileExists(String fileName){
		new File(outputPath+fileName).exists()
	}
	
	/* (non-Javadoc)
	 * @see org.siven.cdsync.FileSystem#visibleFileCount()
	 */
	public int visibleFileCount(){
		int visibleFiles = 0
		new File(outputPath).eachFile() { 
			if(!isHiddenFile(it.getName())){
				visibleFiles ++
			}
		}
		visibleFiles
	}
	
	public File getFile(String fileName){
		new File(outputPath+fileName)
	}
	
	 


}
