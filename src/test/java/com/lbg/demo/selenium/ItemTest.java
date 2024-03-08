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

public class ItemTest {
	private RemoteWebDriver driver;

	@LocalServerPort
	private int port;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test
	@Order(1)
	void testGetItem() {
		this.driver.get("http://localhost:3000");
		WebElement itemButton = this.driver
				.findElement(By.cssSelector("#root > nav > div > div:nth-child(2) > a:nth-child(1)"));
		itemButton.click();

		WebElement resultName = this.driver.findElement(By.cssSelector("#root > div > div > div > div > h4"));
		Assertions.assertEquals("Boondocker 26L Overnighter Backpack - Primary", resultName.getText());

		WebElement resultPrice = this.driver.findElement(By.cssSelector("#root > div > div > div > div > h6"));
		Assertions.assertEquals("£ 70.00", resultPrice.getText());
		WebElement resultQuantity = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div:nth-child(6)"));
		Assertions.assertEquals("Items left: 100", resultQuantity.getText());
	}

	@Test
	@Order(2)
	void testCreateItem() throws InterruptedException {
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

		WebElement resultName = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div:nth-child(2) > h4"));
		Assertions.assertEquals("Youth Mainframe MIPS Helmet - Black", resultName.getText());

		WebElement resultPrice = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div:nth-child(2) > h6"));
		Assertions.assertEquals("£ 100.00", resultPrice.getText());

		WebElement resultItems = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div:nth-child(2) > div:nth-child(6)"));
		Assertions.assertEquals("Items left: 100", resultItems.getText());

	}

}