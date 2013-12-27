package org.siven.cdsync;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sun.jna.platform.FileMonitor.FileListener;

public class CyberDojo {

	final By FILES_LIST = By.xpath("//div[@id='filename_list']/div");

	final By NEW = By.id("new")

	final By NEW_FILENAME = By.id("new_filename")

	final By NEW_FILE_OK = By.id("new_file_ok")

	final By TEST = By.id("test")

	private WebDriver driver;
	private outputPath = ""

	public CyberDojo(WebDriver driver) {
		this.driver = driver
	}

	public void setOutputPath(String output){
		this.outputPath = output
	}

	public void getUrl(String url){
		println "Open "+url
		driver.get(url);
	}

	public void downloadFiles(){
		driver.findElements(FILES_LIST).each {element->
			downloadWebFile(element.getText())
		}
	}



	public void downloadFile(String fileName){
		downloadWebFile(fileName)
	}


	public void uploadFiles(){
		removeMissingFiles()
		new File(outputPath).eachFile() {
		if(!hiddenFile(it.name))
			updateWebFile(it.name)
		}
	}



	public void runTests(){
		println "RunTests"
		driver.findElement(TEST).click()
	}

	

	public void exit(){
		driver.quit();
	}
	
	//Local file
	private boolean hiddenFile(String fileName){
		fileName.charAt(0)=='.'
	}

	private void removeMissingFiles(){
		driver.findElements(FILES_LIST).each {
			if(!localFileExist(it.text)){
				deleteWebFile(it.text)
			}
		}
	}

	private void writeToLocalFile(String fileName, String content) {
		new File(outputPath+fileName).write(content)
	}

	private String readLocalFile(String fileName){
		new File(outputPath+fileName).text
	}

	private boolean localFileExist(String fileName){
		new File(outputPath+fileName).exists()
	}


	//Web file functions

	private void createWebFile(String fileName){
		driver.findElement(NEW).click()
		driver.findElement(NEW_FILENAME).clear()
		driver.findElement(NEW_FILENAME).sendKeys(fileName)
		driver.findElement(NEW_FILE_OK).click()
		println "file Created " + fileName
	}

	private updateWebFile(String fileName) {
		if(webFileExist(fileName)){
			selectFile(fileName)
		}else{
			createWebFile(fileName)
		}
		try{
			driver.findElement(contentLocator(fileName)).clear()
			driver.findElement(contentLocator(fileName)).sendKeys( readLocalFile(fileName))
			println("uploaded " +fileName)
		}catch (Exception){}
	}

	private downloadWebFile(String fileName) {
		selectFile(fileName)
		writeToLocalFile(fileName,  driver.findElement(contentLocator(fileName)).getAttribute("value"))
		println "Download "+fileName
	}

	private void deleteWebFile(String fileName){
		selectFile(fileName)
		driver.findElement(By.id("delete")).click()
		driver.findElement(By.xpath("//span[text()[contains(.,'ok')]]")).click()
		println "file deleted " + fileName
	}



	private boolean webFileExist(String fileName){
		driver.findElement(By.id("filename_list")).text.contains(fileName)
	}

	private void selectFile(String fileName){
		driver.findElement(By.id("radio_"+ fileName)).click()
	}

	private By contentLocator(String fileName){
		By.id("file_content_for_"+fileName)
	}

}
