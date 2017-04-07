package by.htp.web;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalculateTest {

	private static final String DRIVER_NAME = "webdriver.chrome.driver";
	private static final String DRIVER_LOCATION = "C:/programs/selenium/chromedriver.exe";

	private static final String MESSAGE_INCORRECT_TERM = "incorrect term!";
	private static final String MESSAGE_IS_EMPTY_TERM = "term is empty!";

	private static WebDriver driver;

	@BeforeClass
	public static void initBrowser() {
		System.setProperty(DRIVER_NAME, DRIVER_LOCATION);
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	@Test
	public void positive_PositiveTerms_Test() {
		ResultPage resultPage = calculateCheckResult("5", "6", Constants.RESULT_PAGE);
		// Compare result
		Assert.assertEquals("11", resultPage.returnResult());
	}

	@Test
	public void positive_NegativeTerms_Test() {
		ResultPage resultPage = calculateCheckResult("-5", "-6", Constants.RESULT_PAGE);
		// Compare result
		Assert.assertEquals("-11", resultPage.returnResult());
	}

	@Test
	public void positive_NegativePositiveTerms_Test() {
		ResultPage resultPage = calculateCheckResult("5", "-6", Constants.RESULT_PAGE);
		// Compare result
		Assert.assertEquals("-1", resultPage.returnResult());
	}

	@Test
	public void positive_ZeroPositiveTerms_Test() {
		ResultPage resultPage = calculateCheckResult("5", "0", Constants.RESULT_PAGE);
		// Compare result
		Assert.assertEquals("5", resultPage.returnResult());
	}

	@Test
	public void positive_ZeroZeroTerms_Test() {
		ResultPage resultPage = calculateCheckResult("0", "0", Constants.RESULT_PAGE);
		// Compare result
		Assert.assertEquals("0", resultPage.returnResult());
	}

	@Test
	public void negative_OneLetter_Test() {
		ResultPage resultPage = calculateCheckResult("a", "6", Constants.ERROR_PAGE);
		// Compare result
		Assert.assertEquals(MESSAGE_INCORRECT_TERM, resultPage.returnErrorMessage());
	}

	@Test
	public void negative_TwoLetters_Test() {
		ResultPage resultPage = calculateCheckResult("a", "b", Constants.ERROR_PAGE);
		// Compare result
		Assert.assertEquals(MESSAGE_INCORRECT_TERM, resultPage.returnErrorMessage());
	}

	@Test
	public void negative_OneSymbol_Test() {
		ResultPage resultPage = calculateCheckResult("&", "6", Constants.ERROR_PAGE);
		// Compare result
		Assert.assertEquals(MESSAGE_INCORRECT_TERM, resultPage.returnErrorMessage());
	}

	@Test
	public void negative_TwoSymbol_Test() {
		ResultPage resultPage = calculateCheckResult("?", "*", Constants.ERROR_PAGE);
		// Compare result
		Assert.assertEquals(MESSAGE_INCORRECT_TERM, resultPage.returnErrorMessage());
	}

	@Test
	public void negative_OneEmptyTerm_Test() {
		ResultPage resultPage = calculateCheckResult("", "6", Constants.ERROR_PAGE);
		// Compare result
		Assert.assertEquals(MESSAGE_IS_EMPTY_TERM, resultPage.returnErrorMessage());
	}

	@Test
	public void negative_TwoEmptyTerms_Test() {
		ResultPage resultPage = calculateCheckResult("", "", Constants.ERROR_PAGE);
		// Compare result
		Assert.assertEquals(MESSAGE_IS_EMPTY_TERM, resultPage.returnErrorMessage());
	}

	private ResultPage calculateCheckResult(String term1, String term2, String resultPageTitle) {
		// Open home page
		CalculatePage calculatePage = new CalculatePage(driver);
		calculatePage.open();
		// Fill first term
		calculatePage.fillTerm1(term1);
		// Fill second term
		calculatePage.fillTerm2(term2);
		// Open result page
		ResultPage resultPage = calculatePage.getResultPage();
		// Check page title
		Assert.assertTrue(resultPage.getTitle().equals(resultPageTitle));
		return resultPage;
	}

	@AfterClass
	public static void destroyBrowser() {
		driver.quit();
	}
}
