package com.lbg.demo;

import java.time.Duration;

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

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
//@Sql(scripts = { "classpath:cart-schema.sql",
//		"classpath:cart-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

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

//	@Test
//	@Order(1)
	@Test
	@Order(1)
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
//		this.driver.executeScript("arguments[0].scrollIntoView(true);", createButton);
//		this.driver.executeScript("arguments[0].click();", createButton);
		createButton.click();
		Thread.sleep(1000);

	}
}
