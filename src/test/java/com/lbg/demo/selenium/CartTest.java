package com.lbg.demo.selenium;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:paistinaSchema.sql",
		"classpath:paistinaData.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class CartTest {
	private RemoteWebDriver driver;

	@LocalServerPort
	private int port;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test
	@Order(2)

	void testAddToCart() throws InterruptedException {

		this.driver.get("http://localhost:3000");
		WebElement itemButton = this.driver
				.findElement(By.cssSelector("#root > nav > div > div:nth-child(2) > a:nth-child(1)\r\n"));
		itemButton.click();

		String itemName = "Youth Mainframe MIPS Helmet - Black";
		WebElement name = this.driver.findElement(By.cssSelector("#root > div > div > form > input:nth-child(2)"));
		name.sendKeys(itemName);

		String itemPrice = "100";
		WebElement price = this.driver.findElement(By.cssSelector("#root > div > div > form > input:nth-child(4)"));
		price.sendKeys(itemPrice);

		String itemQuantity = "100";
		WebElement quantity = this.driver.findElement(By.cssSelector("#root > div > div > form > input:nth-child(6)"));
		quantity.sendKeys(itemQuantity);

		String itemUrl = "https://i1.adis.ws/i/jpl/ti_EAHMP4AL0350_a?w=800&h=800";
		WebElement url = this.driver.findElement(By.cssSelector("#root > div > div > form > input:nth-child(8)"));
		url.sendKeys(itemUrl);
		Thread.sleep(1000);

		WebElement createButton = this.driver.findElement(By.cssSelector("#root > div > div > form > button"));
		createButton.click();

		Thread.sleep(1000);
		
		
		WebElement shopPage = this.driver.findElement(By.cssSelector("#root > nav > div > div:nth-child(2) > a:nth-child(2) > strong"));
		shopPage.click();


//		Thread.sleep(1000);
		
		
		WebElement addItem = this.driver.findElement(By.cssSelector("#root > div > div:nth-child(3) > button"));
		addItem.click();

//		this.driver.executeScript("arguments[0].scrollIntoView(true);", addItem);
//		this.driver.executeScript("arguments[0].click();", addItem);

		WebElement cartPage = this.driver.findElement(By.cssSelector(
				"#root > nav > div > div.float-rigth.navbar-nav > a.nav-link.icon-cart > strong > svg > path"));
		cartPage.click();

//		WebDriverWait hold = new WebDriverWait(driver, Duration.ofSeconds(10));
//		hold.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
//				"#root > div > div > div:nth-child(3) > div > table > tbody > tr > td:nth-child(1) > h6")));

		WebElement cartItemName = this.driver.findElement(By
				.cssSelector("#root > div > div > div:nth-child(3) > div > table > tbody > tr > td:nth-child(1) > h6"));

		Assertions.assertEquals("Boondocker 26L Overnighter Backpack - Primary", cartItemName.getText());

		WebElement cartItemPrice = this.driver.findElement(By
				.cssSelector("#root > div > div > div:nth-child(3) > div > table > tbody > tr > td:nth-child(2) > h6"));

		Assertions.assertEquals("£ 70.00", cartItemPrice.getText());
	}

	@Test
	@Order(1)
	void personalisedCartTest() throws InterruptedException {

		this.driver.get("http://localhost:" + this.port);

		WebElement cartPage = this.driver.findElement(By.cssSelector(
				"#root > nav > div > div.float-rigth.navbar-nav > a.nav-link.icon-cart > strong > svg > path"));
		cartPage.click();
		Thread.sleep(1000);

		WebElement customerName = this.driver
				.findElement(By.cssSelector("#root > div > div > div.cart-container > h2"));

//		WebDriverWait hold = new WebDriverWait(driver, Duration.ofSeconds(10));
//		hold.until(ExpectedConditions.textToBePresentInElementValue(customerName, "Augustina"));

		Assertions.assertEquals("Agustina Di Paola's Cart", customerName.getText());

	}

}
