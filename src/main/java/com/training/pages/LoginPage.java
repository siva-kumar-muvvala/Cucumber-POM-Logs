package com.training.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

public class LoginPage {

	private final WebDriver driver;

	private final By username = By.id("username");

	private final By password = By.id("password");

	private final By loginButton = By.cssSelector("button[type='submit']");

	private final By flashMessage = By.id("flash");

	public LoginPage(WebDriver driver) {

		this.driver = driver;

	}

	public void open(String url) {

		driver.get(url);

	}

	public void enterUsername(String userNameValue) {

		driver.findElement(username).clear();

		driver.findElement(username).sendKeys(userNameValue);

	}

	public void enterPassword(String passwordValue) {

		driver.findElement(password).clear();

		driver.findElement(password).sendKeys(passwordValue);

	}

	public void clickLogin() {

		driver.findElement(loginButton).click();

	}

	public String getFlashMessage() {

		return driver.findElement(flashMessage).getText();

	}

	// Keep old method for backward compatibility if needed

	public String getSuccessMessage() {

		return getFlashMessage();

	}

}