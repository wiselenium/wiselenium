/**
 * Copyright (c) 2013 Andre Ricardo Schaffer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
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
 */
package com.github.wiselenium.elements.component;

import java.util.List;

/**
 * Represents a HTML Multiple Select.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.3.0
 */
public interface MultiSelect extends Component<MultiSelect> {
	
	/**
	 * Deselects all options.
	 * 
	 * @return This select instance to allow chain calls.
	 * @since 0.3.0
	 */
	MultiSelect deselectAll();
	
	/**
	 * Deselects all options at the given indexes. This is done by examing the "index" attribute of
	 * an element, and not merely by counting.
	 * 
	 * @param indexes The option at this index will be selected.
	 * @return This select element to allow chain calls.
	 * @since 0.3.0
	 */
	MultiSelect deselectByIndex(int... indexes);
	
	/**
	 * Deselects all options that have a value matching the argument. That is, when given "foo" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param values The values to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.3.0
	 */
	MultiSelect deselectByValue(String... values);
	
	/**
	 * Deselects all options that display text matching the argument. That is, when given "Bar" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param texts The visible texts to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.3.0
	 */
	MultiSelect deselectByVisibleText(String... texts);
	
	/**
	 * Deselects some options of this select if not already deselected.
	 * 
	 * @param options The options to be deselected.
	 * @return This select instance in order to allow chain calls.
	 * @since 0.3.0
	 */
	MultiSelect deselectOptions(Option... options);
	
	/**
	 * Gets the options of this select.
	 * 
	 * @return The options of this select.
	 * @since 0.3.0
	 */
	List<Option> getOptions();
	
	/**
	 * Returns the selected options.
	 * 
	 * @return The selected options.
	 * @since 0.3.0
	 */
	List<Option> getSelectedOptions();
	
	/**
	 * Returns the selected values.
	 * 
	 * @return The selected values.
	 * @since 0.3.0
	 */
	List<String> getSelectedValues();
	
	/**
	 * Returns the selected visible texts.
	 * 
	 * @return The selected visible texts.
	 * @since 0.3.0
	 */
	List<String> getSelectedVisibleTexts();
	
	/**
	 * Selects all options.
	 * 
	 * @return This select instance to allow chain calls.
	 * @since 0.3.0
	 */
	MultiSelect selectAll();
	
	/**
	 * Selects all options at the given indexes. This is done by examing the "index" attribute of an
	 * element, and not merely by counting.
	 * 
	 * @param indexes The option at this index will be selected.
	 * @return This select element to allow chain calls.
	 * @since 0.3.0
	 */
	MultiSelect selectByIndex(int... indexes);
	
	/**
	 * Selects all options that have a value matching the argument. That is, when given "foo" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param values The values to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.3.0
	 */
	MultiSelect selectByValue(String... values);
	
	/**
	 * Selects all options that display text matching the argument. That is, when given "Bar" this
	 * would select an option like: &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param texts The visible texts to match against.
	 * @return This select element to allow chain calls.
	 * @since 0.3.0
	 */
	MultiSelect selectByVisibleText(String... texts);
	
	/**
	 * Selects some options of this select if not already selected.
	 * 
	 * @param options The options to be selected.
	 * @return This select instance in order to allow chain calls.
	 * @since 0.3.0
	 */
	MultiSelect selectOptions(Option... options);
	
}
