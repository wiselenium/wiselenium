# What is wiselenium?

wiselenium is a framework built upon [Selenium WebDriver](http://seleniumhq.org/) to ease the creation of tests using strongly typed components.  
Easily create your own UI component types and use them in your tests!

# 1 minute example

Suppose you want to test for the values within the "wiselenium table of features" below:

<table name="wiselenium-features">
    <tbody>
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
            <td>Takes the Page Object Pattern to the next level</td>
            <td>Yes</td>
        </tr>
        <tr>
            <td>Adds convenience for test creation</td>
            <td>Yes</td>
        </tr>
    </tbody>
</table>
  
Then you can just find the table and use its built-in methods:

```java
public class GitHubExample extends WiseTest {
	
	@Test
	public void shouldTestWiseleniumTableOfFeatures() {
		this.get("https://github.com/wiselenium/wiselenium");
		Table table = this.findElement(Table.class, By.name("wiselenium-features"));
		
		for (TableRow bodyRow : table.getBody().getRows())
			assertEquals(bodyRow.getCell(1).getText(), "Yes");
	}
	
}
```

Want to use the Page Object Pattern? OK, it would look like this:

```java
public class WiseleniumPage {
	
	@FindBy(name = "wiselenium-features")
	private Table tableOfFeatures;
	
	// ...
	
}
```

# wiselenium modules
wiselenium has 3 different modules to help you out on your tests: wiselenium-factory, wiselenium-elements, wiselenium-testng.

## wiselenium-factory
This is wiselenium core. Main resources:  
  - @Component = type annotation to identify your components.  
  - @Frame = type annotation to identify your frames.  
  - WisePageFactory.class = page factory that understands components annotated with @Component or @Frame.  
  - Wiselenium.class = facade with locator methods that also works with annotated components.  
  - WiseContext.class = driver storage for the thread.  
  - @Root = field annotation to mark the root element for injection on your objects. That is, WebDrivers for pages and WebElements for components.  
  
Note: If you're using the wiselenium-testng module, then you won't have to bother with the WisePageFactory, Wiselenium and WiseContext classes, because it offers convenience methods that wrap these classes methods themselves.

#### Page Object Pattern
A page must have either a no-arg constructor or a constructor that takes a WebDriver as only argument.  
All component, frame and WebElement fields will be injected by the wiselenium page factory.  
If the no-arg constructor is used, you can annotate a WebDriver field with @Root and have it automatically injected also.  

#### Create your Components
A component is any type annotated with `@Component` and must have a no-arg constructor.  
Every other component, frame or WebElement inside a component will be automatically initialized on a lazy mode. That way, you can easily create really nice structures!  
You can have the component corresponding WebElement injected by annotating a WebElement field of your component with @Root.  

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

3) Using your page on the test:
```java
public class SearchTest extends WiseTest {

	@Test
	public void shouldSearch() {
		SearchPage page = this.initElements(SearchPage.class);
		// ...
	}

}
```

#### Create your Frames
A component is any type annotated with `@Frame` and must have a no-arg constructor.  
wiselenium will automatically switchTo the frame context before any of its methods are called, and switchTo the previous context afterwards. This way, even nested frame structures are transparently handled. Every component, frame or WebElement inside it is also initialized on a lazy mode.  

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

2) Creating the test using your frame:
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

#### @AjaxElement
Sometimes you have to wait for an element to be present, usually until an AJAX call completes. In these cases, you can use the `@AjaxElement` annotation and have it's location strategy configured to wait for the element:
```java
public class DummyPage {
	
	@AjaxElement(timeOutInSeconds=5) // default value
	private Button button;
	
}
```

#### Locating components
wiselenium facade offers a `findElement(Class<E> elementClass, By by)` method that is able to locate your components.  
If you need to dynamically turn a simple WebElement into a specific Component, use the `decorateElement(Class<E> elementClass, WebElement webElement)` method.  
When using any wiselenium `findElement(...)` or `decorateElement(...)` method, you may pass an interface type. If so, wiselenium will automatically lookup for it's implementation class following the pattern `{classPackage}.impl.{className}Impl`.

#### Storing the driver for the thread
If your're not using the wiselenium-testng module, it is important to store the WebDriver of your test at the WiseContext with the `setDriver` method, as it is used in some different places along wiselenium.


## wiselenium-elements
This module depends on the wiselenium-factory module and offers a lot of HTML components implementations:  
`Text`,`Button`,`Hyperlink`,`Checkbox`,`Radiobutton`,`Img`,`Label`,`Select`,`MultiSelect`,`Table`,`Frame`, etc.  
It also offers a `Page` base class with many built-in methods.


## wiselenium-testng
This module depends on the wiselenium-factory module and eases the configuration of your tests using the [TestNG](http://testng.org/doc/index.html) framework. Main resources:  
  - WiseTest.class = test base class that can be extended.

#### Browser configuration
The browser will be automatically open on a beforeClass lifecyle and closed afterClass.  
Firefox is the default browser, and can be changed overriding the `initDriver()` method.

#### Page initialization
All page instances annotated with `@Page` are automatically injected for you.
```java
public class GitHubExample extends WiseTest {

	@Page
	private GitHubPage page;
	
}
```

#### Screenshots on test failures
A screenshot is automatically taken when a test fails.  
Set the directory to which the screenshots will be saved by overriding the method `getTestFailureScreenShotPath()`. Defaults to `target/test-failure-screenshots/`.

#### Convenience methods
A lot of convenient self-explanatory methods are provided:  
`findElement(Class elementClass, By by)`, `initElements(Class pageClass)`, `get(String url)`, `takeScreenShot(String fileName)`, `waitFor(long timeOutInSeconds)`, `getDriver()`.  

#### Storing the driver for the thread
The WiseTest base class automatically stores the driver at the WiseContext for you.


# Project Setup
Soon to be deployed at the Maven Central Repository.

# About the developers
http://www.linkedin.com/in/andrericardoschaffer

# Contact Info
Please feel free to make contact at wiselenium@gmail.com
