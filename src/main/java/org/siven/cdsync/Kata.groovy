package org.siven.cdsync;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Kata {
	final By FILES_LIST = By.xpath("//div[@id='filename_list']/div");

	final By NEW = By.id("new")

	final By NEW_FILENAME = By.id("new_filename")

	final By NEW_FILE_OK = By.id("new_file_ok")

	final By TEST = By.id("test")

	private WebDriver driver;

	Kata(){
		this. driver = new FirefoxDriver()
	}

	void setUrl(String url){
		driver.get(url)
	}

	void test(){
		println "RunTests"
		driver.findElement(TEST).click()
	}
	
	void done(){
		driver.quit()
	}

	List getFileNames(){
		List fileNames = []
		driver.findElements(FILES_LIST).each {element->
			fileNames.add(element.getText())
		}
		return fileNames
	}

	String fileContent(String fileName) {
		selectFile(fileName)
		driver.findElement(contentLocator(fileName)).getAttribute("value")

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

	private void createFile(String fileName){
		driver.findElement(NEW).click()
		driver.findElement(NEW_FILENAME).clear()
		driver.findElement(NEW_FILENAME).sendKeys(fileName)
		driver.findElement(NEW_FILE_OK).click()
		println "file Created " + fileName
	}

	private updateFile(String fileName, String content) {
		if(webFileExist(fileName)){
			selectFile(fileName)
		}else{
			createFile(fileName)
		}
		try{
			driver.findElement(contentLocator(fileName)).clear()
			driver.findElement(contentLocator(fileName)).sendKeys( content)
			println("uploaded " +fileName)
		}catch (Exception){}
	}


	private void selectFile(String fileName){
		driver.findElement(By.id("radio_"+ fileName)).click()
	}

	private By contentLocator(String fileName){
		By.id("file_content_for_"+fileName)
	}


}
