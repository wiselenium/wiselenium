# What is wiselenium?

wiselenium is a framework built upon [Selenium WebDriver](http://seleniumhq.org/) to ease the creation of tests in a strongly typed manner.  
It makes it possible to easily create and use your own UI component types in your tests.

# 1 minute example

Suppose you want to test for the values within the "wiselenium table of features" below:

<table name="wiselenium-features">
    <tbody>
        <tr>
            <td>Provides strongly typed HTML elements with built-in methods</td>
            <td>Yes</td>
        </tr>
        <tr>
            <td>Lets you create and use your own UI elements</td>
            <td>Yes</td>
        </tr>
        <tr>
            <td>Supports the Page Object pattern</td>
            <td>Yes</td>
        </tr>
        <tr>
            <td>Adds convenience for test creation</td>
            <td>Yes</td>
        </tr>
        </tr>
            <td>Transparently overcomes some driver-specific issues (e.g. IE)</td>
            <td>Yes (Soon!)</td>
        </tr>
    </tbody>
</table>
  
Then all you'd have to do is lookup for the table and use its built-in methods for the test:

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

## Maven

To setup your project and start using wiselenium, just add its dependency (not yet deployed to the Maven Central Repository!) to your `pom.xml`:
```xml
<dependency>
	<groupId>org.wiselenium</groupId>
	<artifactId>wiselenium-core</artifactId>
	<version>0.1.0</version>
	<scope>test</scope>
</dependency>
```

## WiseTestNG&lt;T&gt; and your test class

The WiseTestNG class is intended to ease the configuration of your wiselenium tests with the TestNG framework:

### Browser
It is set to automatically open the browser on a beforeClass lifecyle and always close it afterClass. Firefox will be the default browser, which can be changed in two ways:  
  
1) Override the `initDriver()` method to return the wanted browser.  
```java
@Override
public WebDriver initDriver() {
	return new FirefoxDriver(this.getDesiredCapabilities());
}
```  
Notice the `getDesiredCapabilities()` method which can be overridden as well.
  
  
2) Pass in the browser as a parameter through `testng.xml` ([TestNG documentation](http://testng.org/doc/index.html)).
```xml
<parameter name="browser" value="firefox"/>
```
The values must match any wiselenium `Driver` enum value.

### URL
Another parameter facility that you can take advantage of is the url, which, if present, will make WiseTestNG navigate to its value on a beforeClass lifecycle.
```xml
<parameter name="url" value="http://www.google.com"/>
```

### Page Injection
All page instance variables marked with the `@Page` annotation will be injected on your test.
```java
public class GitHubExample extends WiseTestNG<GitHubExample> {

	@Page
	private DummyPage page;
	
}

```

A page must have either a no-arg constructor or a constructor that takes a WebDriver as only argument. You don't have to start your pages from the zero, because wiselenium provides the `Page` base class that already fulfills this requirement and many other convenience methods. Just extend it.

### Find Element(s)
As one could see from wiselenium "1 minute example", WiseTestNG makes it possible to find and use elements other than `WebElement`. In fact, the `Page` base class also offers this possibility.
The only prerequisite is that the class of the element being searched is annotated with either `@Field` , `@Container` or `@Frame`.  
A Field should be an element that doesn't contain any other elements, like an input, whereas a Container represents an element that do contain others, like a Table. By default, wiselenium automatically initializes every element inside a Page or a Container in a lazy mode.  
The class of the element being search can (and should) be an interface. It's implementation class will be automatically lookedup. Check out wiselenium lookup strategy on the code javadoc.

### Create and use your Element
As already said, an Element is identified by the annotations `@Field`, `@Container` and `@Frame`. Just annotate your element class with them, set a no-arg constructor and start using it!  
Notice that wiselenium will always proxy your class and keep the original `WebElement`. To retrieve and use it, just call the `WiseUnwrapper.unwrapWebElement(...)` method passing in your element instance.  
Again, you don't have to start your elements from the zero, as wiselenium provides base classes for them (Field/BasicField, Container/BasicContainer).

# Code Health
wiselenium is developed with a real concern for code quality.  
This is [Sonar's](http://www.sonarsource.org/) wiselenium dashboard running with the default 'Sonar Way with Findbugs' profile, except for some rules regarding braces ('If Stmts Must Use Braces', 'For Loops Must Use Braces', 'If Else Stmts Must Use Braces').  
<img src='etc/sonar.png'/>

# About the developers

http://www.linkedin.com/in/andrericardoschaffer