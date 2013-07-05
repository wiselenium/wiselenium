# What is wiselenium?

wiselenium is a framework built upon [Selenium WebDriver](http://seleniumhq.org/) to ease the creation of tests in a strongly typed manner.  
It makes it possible to easily create and use your own elements, taking Page Objects to the next level.

# 1 minute example

Suppose you want to test for the values within the "wiselenium table of features" below:

<table name="wiselenium-features">
    <tbody>
        <tr>
            <td>Provides strongly typed HTML elements with built-in methods</td>
            <td>Yes</td>
        </tr>
        <tr>
            <td>Lets you easily create and use your own elements</td>
            <td>Yes</td>
        </tr>
        <tr>
            <td>Removes the burden of handling frames with WebDriver</td>
            <td>Yes</td>
        </tr>
        <tr>
            <td>Takes the Page Object Pattern to the next level</td>
            <td>Yes</td>
        </tr>
        <tr>
            <td>Adds convenience for test creation</td>
            <td>Yes</td>
        </tr>
    </tbody>
</table>
  
Then you just have to find the table and use its built-in methods for the test:

```java
public class GitHubExample extends WiseTestNG<GitHubExample> {
  
  @Test
  public void shouldTestWiseleniumTableOfFeatures() {
		this.get("https://github.com/andreschaffer/wiselenium");
		Table table = this.findElement(Table.class, By.name("wiselenium-features"));
		
		for (TableRow bodyRow : table.getBody().and().getRows())
			assertTrue(bodyRow.getCell(1).getText().contains("Yes"));
	}
  
}
```

Want to use the Page Object Pattern? No problem, you can still make use of the table element:

```java
public class WiseleniumPage extends Page<WiseleniumPage> {
	
	@FindBy(name = "wiselenium-features")
	private Table tableOfFeatures;
	
	// ...
	
}
```

## Setup

```xml
<dependency>
  	<groupId>com.github.wiselenium</groupId>
  	<artifactId>wiselenium-testng</artifactId>
  	<version>0.1.0</version>
</dependency>
```

And you're ready to go!

## Elements available
wiselenium already offers you a lot of HTML elements:  
`Text`,`Button`,`Hyperlink`,`Checkbox`,`Radiobutton`,`Img`,`Label`,`Select`,`MultiSelect`,`Table`,`Frame`, etc.

## The Page Object pattern
wiselenium supports the Page Object Pattern and has its own Factory `initElements(...)` methods.  
Its instance variables will be located the same way Selenium/WebDriver does, with the only difference that it can inject other elements than WebElements.  
A page must have either a no-arg constructor or a constructor that takes a WebDriver as only argument. You don't have to start your pages from the zero, because wiselenium provides the `Page` base class that already fulfills these requirements and offers many other convenience methods. Consider extending it!  

### Page methods
The `Page` class provides many conveninent self-explanatory methods:  
`findElement(Class elementClass, By by)`, `initNextPage(Class<E> clazz)`, `executeScript(String script)`, `takeScreenShot(String fileName)`, `waitFor(long timeOutInSeconds)`, `load()`, `isLoaded()`.  
`load()` and `isLoaded()` methods refer to the `LoadableComponent` interface. For more information, check [Selenium wiki](https://code.google.com/p/selenium/wiki/LoadableComponent).  
If these methods don't fulfill your needs, you can always get the original WebDriver from the `getWrappedDriver()` method and use as needed.

### @AjaxElement
Sometimes you have to wait for an element to be present, usually until an AJAX call completes. In these cases, you can use wiselenium `@AjaxElement` annotation and have it's location strategy configured to wait for the element:
```java
public class DummyPage extends Page<DummyPage> {
	
	@AjaxElement(timeOutInSeconds=5) // default value
	private Button button;
	
}
```

## Create and use your Elements
An Element can be any type annotated with `@Field`, `@Container` or `@Frame` and must have a no-arg constructor.  
  - Field: an atomic element that doesn't contain any other elements, like an input text.  
  - Container: represents an element that do contain others, like a table. wiselenium automatically initializes every element inside a container on a lazy mode for you.  
  - Frame: represents a HTML frame.  wiselenium will automatically switchTo the frame scope before any of its methods is called, and switchTo the previous scope afterwards. It also initializes every element inside a frame on a lazy mode for you.  

You can combine and use them in any way you want!  
As of a Page, you don't have to start your elements from the zero, because wiselenium provides the `BasicField`, `BasicContainer` and `BasicFrame` as base classes for you.  
Note that wiselenium will always proxy your class and keep the original `WebElement`. To retrieve and use it, just call the `WiseUnwrapper.unwrapWebElement(...)` method passing in your element instance.  
  
When using any wiselenium `findElement(...)` method, you may pass an interface type. If so, wiselenium will lookup for it's implementation class (check out wiselenium lookup strategy on the code javadoc).

## Frame usage
As stated in the table of features, wiselenium removes the burden of handling frames with WebDriver.  
Any Frame initialized with the wiselenium facilities will automatically and transparently handle its "switchTo" burden. So, lets say that you have a frame with an input text that you want to manage. You can either:  

1) Create and use your Frame element:
```java
public class DummyFrame extends BasicFrame<DummyFrame> {

	private Text text;

	public void type(String value) {
		this.text.sendKeys(value);
	}
	
	public String getValue() {
		return this.text.getValue();
	}

}
```

```java
public class DummyTest extends WiseTestNG<DummyTest> {

	@Test
	public void shouldUseFrameTransparently() {
		DummyFrame frame = this.findElement(DummyFrame.class, By.name("frameName"));
		String value = "dont have to switchTo frame!";
		frame.type(value);
		assertEquals(frame.getValue(), value);
	}

}
```
As can be seen, once the frame was found, we could use its inner elements without the burden of switching contexts!  
  
2) Or you can find the frame as a `BasicFrame` and use it to find the input text:
```java
public class DummyTest extends WiseTestNG<DummyTest> {

	@Test
	public void shouldUseExportedTextTransparently() {
		BasicFrame frame = this.findElement(BasicFrame.class, By.name("frameName"));
		Text text = frame.findElement(Text.class, By.name("text"));
		String value = "input text field was exported!";
		assertEquals(text.sendKeys(value).and().getValue(), value);
	}

}
```
Note that the `Frame.findElement(...)` method just exported the input text so that it could be used transparently even outside the frame!  
As a matter of fact, the `BasicFrame` has a method called `exportInnerElement(E element)` that does just that. The trick is that its `findElement(...)` method makes use of the `exportInnerElement(E element)` under the hood.

## The test class
wiselenium provides the `WiseTestNG` class to ease the configuration of your tests with the TestNG framework.

### The browser of the test
WiseTestNG will automatically open a browser on a beforeClass lifecyle and always close if afterClass. Firefox is the default browser, which can be changed in two ways:  
  
1) Override the `initDriver()` method to return the wanted browser.  
```java
@Override
public WebDriver initDriver() {
	return new FirefoxDriver();
}
```  

Note that wiselenium offers a `Driver` enum to help you instantiate the driver:
```java
@Override
public WebDriver initDriver() {
	return Driver.CHROME.initDriver();
}
```  
As known, Chrome and IE require the extra configuration of setting their exe drivers on your path (see [IE Driver wiki](https://code.google.com/p/selenium/wiki/InternetExplorerDriver)), but wiselenium `Driver` already does that for you.  
  
2) Pass in the browser as a parameter in the `testng.xml` ([TestNG documentation](http://testng.org/doc/index.html)).
```xml
<parameter name="browser" value="firefox"/>
```
The values must match a `Driver` enum value.

### The URL of the test
WiseTestNG can also navigate to a specific URL on a beforeClass lifecyle. For that, you can either override the `getUrl()` method or pass in the url parameter in the `testng.xml`.
```xml
<parameter name="url" value="http://www.google.com"/>
```

### The pages of the test
WiseTestNG will inject all page instance variables annotated with `@Page` for you.
```java
public class GitHubExample extends WiseTestNG<GitHubExample> {

	@Page
	private DummyPage page;
	
}
```

### The failure of the test
WiseTestNG will automatically take a screenshot of the browser screen when there's a test failure. You can change the directory to which the screenshots will be saved by overriding the method:
```java
public String getScreenShotPath() {
		return "target/tests-screenshots/"; // default value
}
```

### The methods of the test
The `WiseTestNG` class provides a lot of convenient self-explanatory methods:  
`findElement(Class elementClass, By by)`, `initElements(Class pageClass)`, `get(String url)`, `getUrl()`, `takeScreenShot(String fileName)`, `waitFor(long timeOutInSeconds)`.  
If these methods don't fulfill your needs, you can always get the original WebDriver from the `getDriver()` method and use as needed.


# Code Health
wiselenium is developed with a real concern for code quality.  
This is [Sonar's](http://www.sonarsource.org/) wiselenium dashboard running with the default 'Sonar Way with Findbugs' profile, except for some rules regarding braces ('If Stmts Must Use Braces', etc).  
<img src='etc/sonar.png'/>

# About the developers

http://www.linkedin.com/in/andrericardoschaffer
