package org.siven.cdsync;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.Assert;

public class CyberDojoTest {
	
	KataMock kata = null
	FsMock fs = null
	CyberDojo cyberDojo = null
		
	@Before
	public void SetupCyberdojoWithMocks() {
		cyberDojo = new CyberDojo()
		kata = new KataMock()
		fs = new FsMock()
		cyberDojo.kata = kata
		cyberDojo.localFs = fs
	}
	
	@Test
	public void shouldDownloadFile(){
		SetupCyberdojoWithMocks()
		cyberDojo.downloadFiles()
		Assert.assertEquals("File.txt|File contents", fs.fileName + "|" + fs.content)
	}

	
	
	@Test
	public void shouldUploadExixtingFile(){
		cyberDojo.uploadFiles();
		Assert.assertEquals("File.txt|File contents", kata.fileName + "|" + kata.content)
	}


	@Test
	public void shouldDeleteFile(){
		fs.exist = false
		cyberDojo.uploadFiles();
		Assert.assertTrue(kata.delete)
	}
	
	@Test
	public void shouldRunTests(){
		fs.exist = false
		cyberDojo.runTests()
		Assert.assertTrue(kata.testsRun)
	}
	
	

	private class FsMock implements FileSystem {
		def content
		def fileName
		def exist = true
		

		public List<String> getFiles(){
			def list =new ArrayList()
			list.add("File.txt")
			list
		}

		public void writeToFile(String fileName, String content){
			this.fileName = fileName
			this.content = content
		}

		public String readFile(String fileName){
			return "File contents"
		}

		public boolean fileExists(String fileName){
			exist
		}

		public int visibleFileCount(){
			1
		}

		public File getFile(String fileName) {
		    null;
		}

		public boolean isHiddenFile(String fileName) {
			return false;
		}
		
		
	}

	private class KataMock implements CdKata{
		boolean delete = false;
		String content = ""
		String fileName = ""
		def testsRun = false
		
		public void setUrl(String url){

		}

		public void test(){
			testsRun = true
		}

		public void done(){

		}

		public  List getFileNames(){
			def list =new ArrayList()
			list.add("File.txt")
			list
		}

		public String fileContent(String fileName){
			"File contents"
		}

		public void updateFile(String fileName, String fileContents) {
			this.fileName = fileName
			this.content = fileContents;
			
		}

		public void deleteFile(String fileName) {
			delete = true
			
		}
	}


}



