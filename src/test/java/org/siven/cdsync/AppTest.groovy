package org.siven.cdsync;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;


/**
 * Unit test for simple App.
 */
public class AppTest
{
	static CyberDojoSync cds;
	static syncDir = "temp/"
    
	@BeforeClass
	public static void setup(){
		new File(syncDir+ ".cyber-dojo").write("http://www.cyber-dojo.com/kata/edit/389CAB302A?avatar=alligator")
		cds = new CyberDojoSync(syncDir)
	}
	
	
	
	@Test
	public void getAllFiles(){
		cds.getFiles();
	}
	
	@Test
	public void uploadFiles()
	{
		File file = new File(syncDir+"test.txt")
		file.write("Test innehåll")
		cds.uploadFiles()
		file.delete()
		cds.uploadFiles()
	}
	
	@Test
	public void test()
	{
		cds.getFiles()
		cds.test()
	}
	
	
	
	
	
	@AfterClass
	public static void tearDown(){
		cds.exit();
	}
}
