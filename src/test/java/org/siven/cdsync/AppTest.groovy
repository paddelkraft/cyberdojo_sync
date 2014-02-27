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
		new File(syncDir).deleteDir()
		new File(syncDir).mkdir()
		new File(syncDir+ ".cyber-dojo").write("http://www.cyber-dojo.com/kata/edit/E53531?avatar=koala")
		cds = new CyberDojoSync(syncDir)
	}
	
	@Test
	public void uploadWithNewAndMissingFiles()
	{
		println "//uploadWithNewAndMissingFiles()"
		cds.getFiles()
		File file = new File(syncDir+"test.txt")
		file.write("Test innehåll")
		cds.uploadFiles()
		file.delete()
		cds.test()
	}
	
	@Test
	public void test()
	{
		println "//Test"
		cds.getFiles()
		cds.test()
	}
	
	@AfterClass
	public static void tearDown(){
		cds.exit();
	}
	
	
}
