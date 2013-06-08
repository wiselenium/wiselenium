/**
 * Copyright (c) 2013 Andre Ricardo Schafferimport java.util.List;

import org.wiselenium.core.element.field.Option;
ng a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * */

package org.wiselenium.core.element.container;

import java.util.List;

import org.wiselenium.core.element.field.Option;

/**
 * Represents a HTML Select.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
public interface Select extends Container<Select> {
	
	/**
	 * Gets the options of this select.
	 * 
	 * @return The options of this select.
	 * @since 0.1.0
	 */
	List<Option> getOptions();
	
	/**
	 * Returns the selected option.
	 * 
	 * @return The selected option.
	 * @since 0.1.0
	 */
	Option getSelectedOption();
	
	/**
	 * Returns the selected value.
	 * 
	 * @return The selected value.
	 * @since 0.1.0
	 */
	String getSelectedValue();
	
	/**
	 * Returns the selected visible text.
	 * 
	 * @return The selected visible text.
	 * @since 0.1.0
	 */
	String getSelectedVisibleText();
	
	/**
	 * Selects the option at the given index. This is done by examing the "index" attribute of an
	 * element, and not merely by counting.
	 * 
	 * @param index The option at this index will be selected.
	 * @return This select element to allow chain calls.
	 * @since 0.1.0
	 */
	Select selectByIndex(int index);
	
	/**
	 * Selects the option that has a value matching the argument. That is, when given "foo" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param value The value to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.1.0
	 */
	Select selectByValue(String value);
	
	/**
	 * Selects the option that displays text matching the argument. That is, when given "Bar" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param text The visible text to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.1.0
	 */
	Select selectByVisibleText(String text);
	
	/**
	 * Selects an option of this select if not already selected.
	 * 
	 * @param option The option to be selected.
	 * @return This select instance in order to allow chain calls.
	 * @since 0.1.0
	 */
	Select selectOption(Option option);
	
}
