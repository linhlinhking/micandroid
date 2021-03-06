/**
 * Copyright 2010 Nicholas Blair, Eric Dalquist
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.googlecode.ehcache.annotations.integration;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInvocation;

import com.googlecode.ehcache.annotations.key.CacheKeyGenerator;
import com.googlecode.ehcache.annotations.key.HashCodeCacheKeyGenerator;

/**
 * Custom {@link CacheKeyGenerator} that delegates to a {@link HashCodeCacheKeyGenerator}
 * and counts invocations; used in {@link OverrideDefaultCacheKeyGeneratorTest}.
 * @author Nicholas Blair
 * @version $Id: OverrideTestCustomCacheKeyGenerator.java 519 2010-06-29 21:02:24Z eric.dalquist@gmail.com $
 */
public class OverrideTestCustomCacheKeyGenerator implements CacheKeyGenerator<Serializable> {

    private int callCount = 0;
    private HashCodeCacheKeyGenerator delegate = new HashCodeCacheKeyGenerator();
    
    /* (non-Javadoc)
     * @see com.googlecode.ehcache.annotations.key.CacheKeyGenerator#generateKey(org.aopalliance.intercept.MethodInvocation)
     */
    public Serializable generateKey(MethodInvocation methodInvocation) {
        this.callCount++;
        return this.delegate.generateKey(methodInvocation);
    }
    
    /* (non-Javadoc)
     * @see com.googlecode.ehcache.annotations.key.CacheKeyGenerator#generateKey(java.lang.Object[])
     */
    public Serializable generateKey(Object... data) {
        this.callCount++;
        return this.delegate.generateKey(data);
    }

    public int getCallCount() {
        return this.callCount;
    }
}
