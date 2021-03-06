/*
 * =============================================================================
 * Copyright by Benjamin Sempere,
 * All rights reserved.
 * =============================================================================
 * Author  : Benjamin Sempere
 * E-mail  : benjamin@sempere.org
 * Homepage: www.sempere.org
 * =============================================================================
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.sempere.commons.checker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests class for NotNullChecker.
 * 
 * @author bsempere
 */
public class NotNullCheckerTest {

	private NotNullChecker<String> checker;

	@Before
	public void before() throws Exception {
		this.checker = new NotNullChecker<String>();
	}

	@Test
	public void checkWhenObjectIsNotNullShouldReturnTrue() {
		assertTrue("Checker should return true.", this.checker.check("MyObject"));
	}

	@Test
	public void checkWhenObjectIsNullShouldReturnFalse() {
		assertFalse("Checker should return false.", this.checker.check(null));
	}
}
