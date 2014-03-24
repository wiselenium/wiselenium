# What is wiselenium?

wiselenium extends the [Selenium WebDriver Page Factory] (https://code.google.com/p/selenium/wiki/PageFactory) to support better UI component abstractions.

# When to use it?

If you are writing tests on pages with UI components that deserve a better abstraction other than the WebElement, there's a good chance that wiselenium will come in handy.

For instance, widgets, JSF, vaadin, home-brewed components, etc.

Also, you should consider using wiselenium if you rather rely on the behavior of your components instead of their structure, so that your tests can easily keep up to them when they evolve (and they will).

# 1 minute example

Suppose you want to test the values within the wiselenium table of features below:

<table name="wiselenium-features">
    <tbody>
    	<tr>
            <td>Takes the Page Object Pattern to the next level</td>
            <td>Yes</td>
        </tr>
        <tr>
            <td>Provides strongly typed HTML components with built-in methods</td>
            <td>Yes</td>
        </tr>
        <tr>
            <td>Lets you easily create and use your own components</td>
            <td>Yes</td>
        </tr>
        <tr>
            <td>Removes the burden of handling frames with WebDriver</td>
            <td>Yes</td>
        </tr>
        <tr>
            <td>Adds convenience for test creation</td>
            <td>Yes</td>
        </tr>
    </tbody>
</table>
  
How many lines of code you'd have to write for that test depending solely on the WebElements?  

Wouldn't it be a lot simpler if you could take advantage of a table abstraction?  

Check out how it could be done using wiselenium:

```java
public class GitHubTestExample extends WiseTest {
	
	@Test
	public void shouldTestWiseleniumTableOfFeatures() {
		this.get("https://github.com/wiselenium/wiselenium");
		Table table = this.findElement(Table.class, By.name("wiselenium-features"));
		
		for (TableRow row : table.getBody().getRows())
			assertEquals(row.getCell(1).getText(), "Yes");
	}
	
}
```

How about using that table abstraction with the Page Object Pattern? 

```java
public class WiseleniumPage {
	
	@FindBy(name = "wiselenium-features")
	private Table tableOfFeatures;
	
	// ...
	
}
```

```java
public class GitHubTestExample extends WiseTest {
	
	@Test
	public void shouldTestWiseleniumTableOfFeatures() {
		this.get("https://github.com/wiselenium/wiselenium");
		WiseleniumPage wiseleniumPage = this.initElements(WiseleniumPage.class);
		
		// ...
	}
	
}
```

Pretty simple, isn't it?

# How to add wiselenium to your project

wiselenium comes in 3 different modules to help you out on your tests:  
- wiselenium-factory: This is the wiselenium core. It is built upon Selenium WebDriver Framework, providing an improved Page Factory and is completely independent of the other modules.
- wiselenium-elements: This module depends on the wiselenium-factory and offers a lot of HTML components abstractions.
- wiselenium-testng: This module depends on the wiselenium-factory and offers ease of configuration for your tests using the [TestNG](http://testng.org/doc/index.html) framework.

## wiselenium-factory

```xml
<dependency>
	<groupId>com.github.wiselenium</groupId>
	<artifactId>wiselenium-factory</artifactId>
	<version>0.3.0</version>
</dependency>
```

#### How to create and use your Components
A component can be any type annotated with `@Component` and a no-arg constructor.  
Each of its encapsulated components, frames and WebElements will be automatically initialized on a lazy mode. That way, you can easily create some really nice structures.  
If you want access to its corresponding WebElement, you can use the `@Root` annotation on a WebElement member to have it injected.


#### How to create and use your Pages
A page can be any type with either a no-arg constructor or a constructor that takes a WebDriver as only argument.  
As of wiselenium Components, your page inner components, frames and WebElements will be automatically injected.  
The `@Root` annotation will also work here if you want to inject the WebDriver for the page.


#### Putting it all together in an example

1) Creating your component:
```java
@Component
public class QueryComponent {

	@Root
	private WebElement root;
	
	@FindBy(id = "t")
	private Text text;
	
	@FindBy(id = "b")
	private Button button;

	// ...

}
```

2) Creating your page:
```java
public class SearchPage {

	@Root
	private WebDriver driver;

	@FindBy(id = "q")
	private QueryComponent queryComponent;
	
	// ...

}
```

3) Creating your test with your page:
```java
public class SearchTest extends WiseTest {

	@Test
	public void shouldSearch() {
		SearchPage page = this.initElements(SearchPage.class);
		// ...
	}

}
```

#### Please note
The examples shown have made use of the wiselenium-testng module so far (by extending the `WiseTest` base class). 
However, it's use is totally facultative.  
So if you _don't_ want to use the wiselenium-testng module, it is important to store the WebDriver of your test at the WiseContext before anything else, as it is used internally by wiselenium.
```java
	WebDriver webDriver = new FirefoxDriver();
	WiseContext.setDriver(webDriver);
```

All `WiseTest` convenience methods used on the tests (`findElement`, `initElements`, etc) can be found at `Wiselenium` and `WisePageFactory` classes, so that you are able to use wiselenium along with any other test framework.

#### Frames with no pain
We all know how painful it can be to use Selenium WebDriver when the page has lots of frames. All the switchTo(frame) burden...  
What if we could create abstractions of these frames and simply use them, ignoring the need of all these switchTo(frame) explicit instructions?  
If dealing with frames is part of your reality and this would make a real difference on your tests, good news: wiselenium transparently handles this for you!  
Creating a frame abstraction is no harder than creating a component. Like a component, it can be any type annotated with `@Frame` and must have a no-arg constructor.  
When you use a frame on your page, wiselenium will automatically switchTo that frame context before any of its methods are called and switchTo the previous context afterwards. This way, even nested frame structures are transparently handled. Every component, frame and WebElement inside it will be also initialized on a lazy mode.  

1) Creating your frame:
```java
@Frame
public class DummyFrame {

	private Text text;

	public void type(String value) {
		this.text.sendKeys(value);
	}
	
	public String getValue() {
		return this.text.getValue();
	}

}
```

2) Creating your test using your frame:
```java
public class DummyTest extends WiseTest {

	@Test
	public void shouldUseFrameTransparently() {
		DummyFrame frame = this.findElement(DummyFrame.class, By.name("frameName"));
		String value = "dont have to switchTo frame!";
		frame.type(value);
		assertEquals(frame.getValue(), value);
	}

}
```
As can be seen, once the frame was found, we could use its inner members without the burden of switching contexts!  

#### Waiting for ajax elements
Sometimes you have to wait for an element to be present, usually until an AJAX call completes. In these cases, wiselenium offers you the `@AjaxElement` annotation so that its location strategy is configured to wait for the element for a certain amount of time:
```java
public class DummyPage {
	
	@AjaxElement(timeOutInSeconds=5) // 5 is the default value
	private Button button;
	
}
```

#### Dynamically decorating WebElements
If you need to dynamically turn a simple WebElement into a specific Component, use the `decorateElement(Class<E> elementClass, WebElement webElement)` method.  
When using any wiselenium `findElement(...)` or `decorateElement(...)` method, you may pass an interface type. If so, wiselenium will lookup for it's implementation class following the pattern `{classPackage}.impl.{className}Impl`.


## wiselenium-elements
```xml
<dependency>
	<groupId>com.github.wiselenium</groupId>
	<artifactId>wiselenium-elements</artifactId>
	<version>0.3.0</version>
</dependency>
```
Some of the HTML components abstractions that this module offers:  
`Text`,`Button`,`Hyperlink`,`Checkbox`,`Radiobutton`,`Img`,`Label`,`Select`,`MultiSelect`,`Table`,`Frame`, etc.  
It also provides a `Page` base class with many built-in methods that you can extend.


## wiselenium-testng
```xml
<dependency>
	<groupId>com.github.wiselenium</groupId>
	<artifactId>wiselenium-testng</artifactId>
	<version>0.3.0</version>
</dependency>
```
This module mainly offers the `WiseTest` base class with some convenience features.  

#### Page initialization
Every page field annotated with `@Page` is automatically injected on the test.
```java
public class GitHubExample extends WiseTest {

	@Page
	private GitHubPage page;
	
}
```

#### Browser configuration
The browser is automatically opened on a beforeClass lifecyle and closed afterClass.  
Firefox is the default browser, and that can be changed overriding the `initDriver()` method to return the driver you want.

#### Screenshots on test failures
One of the golden rules of good testing is that you should be able to diagnose why a test failed without having to rerun it. For that, wiselenium automatically takes a screenshot when a test fails.  
Set the directory to which the screenshots will be saved by overriding the method `getTestFailureScreenShotPath()`. Defaults to `target/test-failure-screenshots/`.

#### Convenience methods
Many convenience self-explanatory methods are provided:  
`findElement(Class elementClass, By by)`, `initElements(Class pageClass)`, `get(String url)`, `takeScreenShot(String fileName)`, `waitFor(long timeOutInSeconds)`, `getDriver()`.  

# Happy testing!

# Contact Info
If you want to make contact, please feel free to message me at wiselenium@gmail.com .

# About the developers
http://www.linkedin.com/in/andrericardoschaffer
