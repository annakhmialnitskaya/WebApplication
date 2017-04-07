package by.htp.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalculatePage extends Page {

	private static final String URL = "http://localhost:8080/WebApp/";
	private static final String INPUT_TERM1_ID = "term1";
	private static final String INPUT_TERM2_ID = "term2";
	private static final String CALCULATE_BUTTON_ID = "calculate_btn";

	public CalculatePage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		getDriver().get(URL);
	}

	public void fillTerm1(String value) {
		fillTerm(value, INPUT_TERM1_ID);
	}

	public void fillTerm2(String value) {
		fillTerm(value, INPUT_TERM2_ID);
	}

	public ResultPage getResultPage() {
		WebElement calculateButton = driver.findElement(By.id(CALCULATE_BUTTON_ID));
		calculateButton.submit();
		return new ResultPage(driver);
	}

	private void fillTerm(String value, String id) {
		WebElement termInput = driver.findElement(By.id(id));
		termInput.sendKeys(value);
	}
}
