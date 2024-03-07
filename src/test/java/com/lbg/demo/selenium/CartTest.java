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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

	void testAddToCart() {

		this.driver.get("http://localhost:" + this.port);

		WebElement shopPage = this.driver
				.findElement(By.cssSelector("#root > nav > div > div:nth-child(2) > a:nth-child(2) > strong"));
		shopPage.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#root > div > div > button")));

		WebElement addItem = this.driver.findElement(By.cssSelector("#root > div > div:nth-child(3) > button"));

		this.driver.executeScript("arguments[0].scrollIntoView(true);", addItem);
		this.driver.executeScript("arguments[0].click();", addItem);

		WebElement cartPage = this.driver.findElement(By.cssSelector(
				"#root > nav > div > div.float-rigth.navbar-nav > a.nav-link.icon-cart > strong > svg > path"));
		cartPage.click();

		WebDriverWait hold = new WebDriverWait(driver, Duration.ofSeconds(10));
		hold.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#root > div > div > div:nth-child(3) > div > table > tbody > tr > td:nth-child(1) > h6")));

		WebElement cartItemName = this.driver.findElement(By
				.cssSelector("#root > div > div > div:nth-child(3) > div > table > tbody > tr > td:nth-child(1) > h6"));

		Assertions.assertEquals("Boondocker 26L Overnighter Backpack - Primary", cartItemName.getText());

		WebElement cartItemPrice = this.driver.findElement(By
				.cssSelector("#root > div > div > div:nth-child(3) > div > table > tbody > tr > td:nth-child(2) > h6"));

		Assertions.assertEquals("£ " + "70.00", cartItemPrice.getText());
	}

	@Test
	@Order(1)
	void personalisedCartTest() {

		this.driver.get("http://localhost:" + this.port);

		WebElement cartPage = this.driver.findElement(By.cssSelector(
				"#root > nav > div > div.float-rigth.navbar-nav > a.nav-link.icon-cart > strong > svg > path"));
		cartPage.click();

		WebElement customerName = this.driver
				.findElement(By.cssSelector("#root > div > div > div.cart-container > h2"));

		WebDriverWait hold = new WebDriverWait(driver, Duration.ofSeconds(10));
		hold.until(ExpectedConditions.textToBePresentInElementValue(customerName, "Augustina"));

		Assertions.assertEquals("Agustina Di Paolo", customerName.getText());

	}

}
