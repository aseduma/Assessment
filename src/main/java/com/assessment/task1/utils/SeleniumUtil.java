/**
 * 
 */
package com.assessment.task1.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.assessment.report.model.Report;
import com.assessment.report.model.Status;
import com.assessment.task2.config.DriverFactory;

/**
 * @author w5100593
 *
 */
public class SeleniumUtil {
	private static WebDriver driver() {
		return DriverFactory.instance().getWebDriver();
	}

	public static void setElement(String locator, String data) {
		if (data == null) {
			return;
		}
		String js = "function getElement(locator) {\r\n" + "	var element = document.getElementById(locator);\r\n"
				+ "	if(element != null){\r\n" + "		return element;\r\n" + "	}\r\n"
				+ "	element = document.getElementsByName(locator)[0];\r\n" + "	if(element != null){\r\n"
				+ "		return element;\r\n" + "	}\r\n"
				+ "	return document.evaluate(locator, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;\r\n"
				+ "}\r\n" + "\r\n" + "var locator = arguments[0];\r\n" + "var element = getElement(locator);\r\n"
				+ "var data = arguments[1];\r\n" + "var tagName = element.tagName.toLowerCase();\r\n" + "\r\n"
				+ "var isReadOnly = (element.getAttribute('readonly') != null && element.getAttribute('readonly') != '');\r\n"
				+ "if(!isReadOnly){\r\n" + "	if(tagName == 'select'){ \r\n"
				+ "		for(var i = 0; i < element.options.length; i++) {\r\n"
				+ "			if(element.options[i].text.toUpperCase() == data.toUpperCase()){\r\n"
				+ "				element.options[i].setAttribute('selected',true); return;\r\n" + "			}\r\n"
				+ "		}\r\n" + "	}else {\r\n" + "		var type = element.getAttribute('type');\r\n"
				+ "		if(type != null){\r\n" + "			type = type.toLowerCase();\r\n" + "		}\r\n"
				+ "		if(tagName == 'input' || tagName == 'textarea'){ \r\n" + "			if(type == 'checkbox'){\r\n"
				+ "				data = data.toLowerCase();\r\n"
				+ "				element.checked= (data == 'true');return;\r\n"
				+ "			}else if(type == 'radio'){\r\n"
				+ "				var isDataBlank = (0 == data.toString().trim().length);\r\n"
				+ "				if(!isDataBlank){\r\n" + "					var elementValue = element.value;\r\n"
				+ "					if(elementValue === elementValue.toUpperCase()){\r\n"
				+ "						data = data.toUpperCase();\r\n" + "					}\r\n"
				+ "					element.setAttribute('value',data);return;\r\n" + "				}\r\n"
				+ "			}else{\r\n" + "				element.value=data;return;\r\n" + "			}\r\n"
				+ "		}\r\n" + "	}\r\n" + "}		";
		((JavascriptExecutor) driver()).executeScript(js, locator, data);
	}

	public static void clickElement(By by) {
		WebElement webElement = driver().findElement(by);
		webElement.click();
	}

	private static WebElement assertedRowWebElement = null;

	private static List<Map<String, String>> getTableHashMap(By by, Map<String, String> map) {
		assertedRowWebElement = null;

		boolean isFilter = map != null && map.size() > 0;

		List<Map<String, String>> rowMaps = new ArrayList<>();

		WebElement disabledButton = null;
		do {
			WebElement webElement = driver().findElement(by);

			List<WebElement> headWebElements = webElement.findElement(By.tagName("thead"))
					.findElements(By.tagName("th"));
			List<WebElement> rowWebElements = webElement.findElement(By.tagName("tbody"))
					.findElements(By.tagName("tr"));

			if (headWebElements == null || headWebElements.isEmpty())
				return null;

			if (rowWebElements == null || rowWebElements.isEmpty())
				return null;

			String heading = "";
			for (WebElement headWebElement : headWebElements) {
				String text = headWebElement.getText() != null ? headWebElement.getText() : "";
				if (!text.isEmpty())
					heading += text + "|";
			}

			String[] headingArray = heading.split("\\|");

			for (WebElement rowWebElement : rowWebElements) {
				Map<String, String> rowMap = new HashMap<>();

				int columnIndex = 0;
				for (WebElement columnWebElement : rowWebElement.findElements(By.tagName("td"))) {
					if (columnIndex < headingArray.length) {
						String text = columnWebElement.getText() != null ? columnWebElement.getText() : "";
						rowMap.put(headingArray[columnIndex], text);
					} else {
						break;
					}
					columnIndex++;
				}
				if (rowMap != null && rowMap.size() > 0) {

					if (isFilter && !assertor(rowMap, map).isFail()) {
						assertedRowWebElement = rowWebElement;
						rowMaps = new ArrayList<>();
						rowMaps.add(rowMap);
						return rowMaps;
					}
					rowMaps.add(rowMap);

				}
			}
		} while (disabledButton == null);
		return rowMaps;
	}

	private static String getAttributeValue(WebElement webElement, String attribute) {
		try {
			return ((JavascriptExecutor) driver())
					.executeScript("return arguments[0].getAttribute(arguments[1]);", webElement, attribute).toString();
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 
	 * @param by
	 * @param tagName
	 * @param attribute
	 * @param tagText
	 */
	public static void clickAssertedElement(By by, String tagName, String attribute, String tagText) {

		List<WebElement> tagColumnWebElements = assertedRowWebElement.findElements(By.tagName(tagName));
		for (WebElement columnWebElement : tagColumnWebElements) {
			String columnText = null;
			try {
				columnText = getAttributeValue(columnWebElement, attribute);
			} catch (Exception e) {
			}
			if (columnText == null || columnText.isEmpty()) {
				columnText = columnWebElement.getText();
			}
			if ((tagText == null || tagText.isEmpty()) || (columnText != null && columnText.contains(tagText))) {
				columnWebElement.click();
				break;
			}
		}

	}

	public static Report assertor(By by, Map<String, String> expectedMap) {

		List<Map<String, String>> maps = getTableHashMap(by, expectedMap);

		if (maps == null || maps.isEmpty()) {
			return new Report(Status.FAIL, by.toString() + " data not found", expectedMap);
		}

		Report report = null;
		for (Map<String, String> map : maps) {
			// System.out.println(hashMap);
			// System.out.println(expectedHashMap);

			report = assertor(map, expectedMap);
			// System.out.println(resultMapper.getStatus());
			if (!report.isFail()) {
				return report;
			}
		}
		return new Report(Status.FAIL, "No record matched", expectedMap);

	}

	private static Report assertor(Map<String, String> actualMap, Map<String, String> expectedMap) {
		for (String key : actualMap.keySet()) {
			String expected = expectedMap.containsKey(key) ? expectedMap.get(key) : null;
			Report report = assertor(key, actualMap.get(key), expected);
			if (report.getStatus() == Status.FAIL) {
				return new Report(Status.FAIL, report.getComments());
			}
		}
		return new Report(Status.PASS);
	}

	private static Report assertor(String key, String actual, String expected) {

		if (expected == null || actual == null || expected.isEmpty() || actual.isEmpty())
			return new Report(Status.PASS);

		if (actual.matches(expected.replace("%", "\\.*"))) {
			return new Report(Status.PASS);
		} else if (actual.contains(expected) || actual.startsWith(expected) || expected.startsWith(actual)
				|| actual.startsWith(expected.replace("%", "")) || actual.endsWith(expected)) {
			return new Report(Status.PASS);
		} else if (actual.replaceAll(",", "").contains(expected.replaceAll(",", ""))) {
			return new Report(Status.PASS);
		} else if (actual.equalsIgnoreCase(expected)) {
			return new Report(Status.PASS);
		}

		if (key == null) {
			key = "";
		}
		// System.out.println(key + " Expected <" + expected + "> but we get <" + actual
		// + ">");
		return new Report(Status.FAIL, (key + " Expected <" + expected + "> but we get <" + actual + ">").trim());
	}

	public static String takeScreenshot() {
		String screenshotsBaseDir = System.getProperty("dir.report.dir") + "/"
				+ new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/"
				+ System.getProperty("dir.report.web.screenshot.dir") + "/";
		new File(screenshotsBaseDir).mkdirs();

		try {
			File scrFile = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.FILE);

			String filePath = screenshotsBaseDir + System.currentTimeMillis() + ".png";
			File destFile = new File(filePath);
			FileUtils.copyFile(scrFile, destFile);
			return screenshotsBaseDir.substring(screenshotsBaseDir.lastIndexOf("screenshots")) + destFile.getName();
		} catch (WebDriverException e) {
		} catch (IOException e) {
		}

		return null;
	}
}
