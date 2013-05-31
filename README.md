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

# In progress...

In progress...

# Code Health

wiselenium is developed with a real concern for code quality.  
This is [Sonar's](http://www.sonarsource.org/) wiselenium dashboard running with the default 'Sonar Way with Findbugs' profile, except for some rules regarding braces ('If Stmts Must Use Braces', 'For Loops Must Use Braces', 'If Else Stmts Must Use Braces').  
<img src='etc/sonar.png'/>

# About the developers

http://www.linkedin.com/in/andrericardoschaffer