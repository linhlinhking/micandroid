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
package org.sempere.commons.proxy;

import org.junit.*;

import java.lang.reflect.*;

/**
 * Unit tests class for ChainingHandler.
 *
 * @author bsempere
 */
public class ChainingHandlerTest {

    private ChainingHandler<String> handler;

    @Test
    public void invoke() throws Throwable {
        this.handler = new ChainingHandler<String>("ToTo");
        this.handler.addHandler(new LowerCaseHandler("ToTo"));
        this.handler.addHandler(new UpperCaseHandler("ToTo"));

        Object result = this.handler.invoke(null, Object.class.getMethod("toString", new Class[]{}), new Object[]{});
        System.out.println(result);
    }

    // *************************************************************************
    //
    // Fixtures, Stubs, ...
    //
    // *************************************************************************

    private class UpperCaseHandler extends DelegateHandler<String> {

        private UpperCaseHandler(String realObject) {
            super(realObject);
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String result = ((String) method.invoke(getRealObject(), args)).toUpperCase();
            System.out.println("UpperCaseHandler " + result);
            return result;
        }
    }

    private class LowerCaseHandler extends DelegateHandler<String> {

        private LowerCaseHandler(String realObject) {
            super(realObject);
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String result = ((String) method.invoke(getRealObject(), args)).toLowerCase();
            System.out.println("LowerCaseHandler " + result);                        
            return result;
        }
    }
}
