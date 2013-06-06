package org.wiselenium.core.element.frame;

import static org.testng.Assert.assertEquals;
import static org.wiselenium.core.util.TestResourceFinder.getAbsolutePath;

import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class FramePage extends Page<FramePage> {
	
	private static final String TITLE = "page for frame tests";
	private static final String URL = getAbsolutePath("frame.html");
	
	private FrameA frame_a;
	private FrameB frame_b;
	private FrameC frame_c;
	
	
	public FrameA getFrame_a() {
		return this.frame_a;
	}
	
	public FrameB getFrame_b() {
		return this.frame_b;
	}
	
	public FrameC getFrame_c() {
		return this.frame_c;
	}
	
	@Override
	protected void isLoaded() {
		assertEquals(this.getTitle(), TITLE);
		assertEquals(this.getWrappedDriver().getCurrentUrl(), URL);
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
