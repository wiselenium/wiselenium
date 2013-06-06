package org.wiselenium.core.element.frame;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.wiselenium.core.element.container.Table;
import org.wiselenium.core.element.container.TableBody;
import org.wiselenium.core.element.container.TableCell;
import org.wiselenium.core.element.container.TableRow;
import org.wiselenium.core.element.field.Radiobutton;
import org.wiselenium.core.element.field.Text;
import org.wiselenium.core.element.frame.impl.BasicFrame;
import org.wiselenium.core.test.Page;
import org.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class FrameTest extends WiseTestNG<FrameTest> {
	
	@Page
	FramePage page;
	
	
	@BeforeMethod
	public void initPage() {
		this.page.load();
		this.page.isLoaded();
	}
	
	@Test
	public void shouldFindElement() {
		FrameA frame_a = this.page.getFrame_a();
		Text text = frame_a.findElement(Text.class, By.id("text"));
		assertNotNull(text);
		assertNotNull(text.getId());
	}
	
	@Test
	public void shouldFindElements() {
		FrameA frame_a = this.page.getFrame_a();
		List<Radiobutton> radios = frame_a.findElements(Radiobutton.class, By.name("sex"));
		assertFalse(radios.isEmpty());
		for (Radiobutton radio : radios)
			assertNotNull(radio.getAttribute("name"));
	}
	
	@Test
	public void shouldFindElementWithBasicFrame() {
		BasicFrame<?> frame = this.findElement(BasicFrame.class, By.id("frame_a"));
		Text text = frame.findElement(Text.class, By.id("text"));
		assertNotNull(text);
		assertNotNull(text.getId());
	}
	
	@Test
	public void shouldUseFrameInnerContainerOutsideFrameTransparently() {
		Table table = this.page.getFrame_a().getTable();
		TableBody body = table.getBody();
		for (TableRow row : body.getRows()) {
			assertNotNull(row);
			for (TableCell cell : row.getCells()) {
				assertNotNull(cell);
				assertTrue(!"".equals(cell.getText()));
			}
		}
	}
	
	@Test
	public void shouldUseFrameInnerFieldOutsideFrameTransparently() {
		String keys = "testProxyInnerElement";
		FrameA frame_a = this.page.getFrame_a();
		Text text = frame_a.getText();
		assertEquals(text.sendKeys(keys).and().getValue(), keys);
	}
	
	@Test
	public void shouldUseFrameInnerFrameAndWebElementOutsideFrameTransparently() {
		String keys = "testProxyInnerElement";
		WebElement webElement = this.page.getFrame_c().getFrame_ca().getWebElement();
		webElement.sendKeys(keys);
		assertEquals(webElement.getAttribute("value"), keys);
	}
	
	@Test
	public void shouldUseFrameTransparently() {
		String keys = "testFrame";
		FrameA frame_a = this.page.getFrame_a();
		FrameB frame_b = this.page.getFrame_b();
		FrameC frame_c = this.page.getFrame_c();
		
		assertEquals(frame_c.sendKeysToFrameCBAText(keys).and().getFrameCBATextValue(), keys);
		assertEquals(frame_a.sendKeysToText(keys).and().getTextValue(), keys);
		assertEquals(frame_c.sendKeysToFrameCAText(keys).and().getFrameCATextValue(), keys);
		assertEquals(frame_c.sendKeysToFrameCBBText(keys).and().getFrameCBBTextValue(), keys);
		assertEquals(frame_b.sendKeysToText(keys).and().getTextValue(), keys);
	}
	
}
