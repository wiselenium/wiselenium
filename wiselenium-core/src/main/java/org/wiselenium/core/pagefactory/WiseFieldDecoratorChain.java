/**
 * Copyright (c) 2013 Andre Ricardo Schimport static org.wiselenium.core.util.AnnotationUtil.isAnnotationPresent;
import static org.wiselenium.core.util.ClasspathUtil.findImplementationClass;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
h, distribute, sublicense, and/or sell
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

package org.wiselenium.core.pagefactory;

import static org.wiselenium.core.util.AnnotationUtil.isAnnotationPresent;
import static org.wiselenium.core.util.ClasspathUtil.findImplementationClass;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * Class responsible for decorating WebElements into Fields.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
class WiseFieldDecoratorChain extends ExtendedSeleniumDecoratorChainTemplate {
	
	WiseFieldDecoratorChain(ElementLocatorFactory factory) {
		super(factory);
	}
	
	@Override
	protected <E> E decorateWebElement(Class<E> clazz, WebElement webElement) {
		Class<? extends E> implementationClass = findImplementationClass(clazz);
		return WiseFieldProxy.getInstance(implementationClass, webElement);
	}
	
	@Override
	protected <E> List<E> decorateWebElements(Class<E> clazz, List<WebElement> webElements) {
		return WiseElementListProxy.getInstance(clazz, webElements, this.factory);
	}
	
	@Override
	protected <E> boolean shouldDecorate(Class<E> clazz) {
		return isAnnotationPresent(clazz, org.wiselenium.core.annotation.Field.class);
	}
	
}
