package by.htp.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultPage extends Page {

	private static final String RESULT_ELEMENT_ID = "calculate_result";
	private static final String ERROR_ELEMENT_ID = "error_message";

	public ResultPage(WebDriver driver) {
		super(driver);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String returnResult() {
		WebElement resultElement = driver.findElement(By.id(RESULT_ELEMENT_ID));
		return resultElement.getText();
	}

	public String returnErrorMessage() {
		WebElement resultElement = driver.findElement(By.id(ERROR_ELEMENT_ID));
		return resultElement.getText();
	}

}
