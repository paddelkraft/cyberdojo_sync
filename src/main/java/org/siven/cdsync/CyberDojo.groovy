package org.siven.cdsync;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sun.jna.platform.FileMonitor.FileListener;

public class CyberDojo {

	static final By FILE_LIST = By.xpath("//div[@id='filename_list']/div")

	private WebDriver driver;
	private output = ""
	
	public CyberDojo(WebDriver driver) {
		this.driver = driver
	}
	
	public void setOutput(String output){
		this.output = output
	}

	public void getUrl(String url){
		println "Open "+url
		driver.get(url);
	}
	
	public void downloadFiles(){
		driver.findElements(FILE_LIST).each {element->
			String fileName = element.getText()
			element.click();
			writeToFile(fileName,  driver.findElement(By.xpath("//textarea[@data-filename='"+fileName+"']")).getAttribute("value"))
			println "Download "+fileName
		}
	}

	public void downloadFile(String fileName){
		driver.findElement(By.id("radio_"+ fileName)).click()
		writeToFile(fileName,  driver.findElement(By.xpath("//textarea[@data-filename='"+fileName+"']")).getAttribute("value"))
		println "Download "+fileName
	}

	
	public void uploadFiles(){
		removeMissingFiles()
		new File(output).eachFile() {
			if(fileExistOnWeb(it.name)){
				driver.findElement(By.id("radio_"+ it.name)).click() 
			}else{
				createWebFile(it.name)
			}
			try{
				driver.findElement(By.id("file_content_for_"+it.name)).clear()
				driver.findElement(By.id("file_content_for_"+it.name)).sendKeys( readFile(it.name))
				println("uploaded " +it.name)
			}catch (Exception){}
		}
	}
	
	public void runTests(){
		println "RunTests"
		driver.findElement(By.id("test")).click()
	}
	
	private void removeMissingFiles(){
		driver.findElements(FILE_LIST).each {
			if(!fileExistLocal(it.text)){
				deleteWebFile(it.text)
			}
		}
	}
	
	private void writeToFile(String fileName, String content) {
		new File(output+fileName).write(content)
	}
	
	private String readFile(String fileName){
		new File(output+fileName).text
	}
	
	private void createWebFile(String fileName){
		driver.findElement(By.id("new")).click()
		driver.findElement(By.id("new_filename")).clear()
		driver.findElement(By.id("new_filename")).sendKeys(fileName)
		driver.findElement(By.id("new_file_ok")).click()
		println "file Created " + fileName
	}
	
	private void deleteWebFile(String fileName){
		driver.findElement(By.id("radio_"+ fileName)).click()
		driver.findElement(By.id("delete")).click()
		driver.findElement(By.xpath("//span[text()[contains(.,'ok')]]")).click()
		println "file deleted " + fileName
	}
	
	private boolean fileExistLocal(String fileName){
		new File(output+fileName).exists()
	}
	
	private boolean fileExistOnWeb(String fileName){
		driver.findElement(By.id("filename_list")).text.contains(fileName)
	}
	
	public void exit(){
		driver.quit();
	}

}
