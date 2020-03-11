package test.automation.api;

import test.automation.utility.ParseJsonResponse;
import test.automation.utility.PropertyFileReader;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ApiTest {

	public static final String API_ProperityFile = "api.properties";
	ParseJsonResponse jsonResponseParser;
	RequestApi request;
	PropertyFileReader configFile;

	String gallery = "Gallery";
	String Name = "Carbon credits";

	@BeforeTest
	public void setUp() throws IOException {
		configFile = new PropertyFileReader(API_ProperityFile);
		request = new RequestApi(configFile.getRequestUrl(), configFile.getRequestMethod());
		jsonResponseParser = new ParseJsonResponse(request.createAndSendRequest());

	}

	@Test
	public void Test_01_verifyName() {
		Assert.assertEquals(jsonResponseParser.getNameFromRootObject(), Name, "Name not matched");
		request.logMessage(Name+": matched");
	}

	@Test
	public void Test_02_verifyIsCanRelist() {
		Assert.assertTrue(jsonResponseParser.CanWeRelist(), "Can relist false");
		request.logMessage("CanRelist = true working fine as expected");
	}

	@Test
	public void Test_03_verifyElementTextInPromotionsDescriptionT() {

		Assert.assertTrue(jsonResponseParser.getPromotionDescription(gallery).contains("2x larger image"),
				"Promotion description" + "text not matched");
		request.logMessage("The Promotions element with Name = \"Gallery\" has a Description that contains the text \"2x larger image\"");
	}

	@AfterTest
	public void closeAPIConnection() {
		request.connectionClose();

	}

}
