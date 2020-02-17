/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.tooling.internal.provider.events;

import org.gradle.tooling.internal.protocol.events.InternalOperationDescriptor;
import org.gradle.tooling.internal.protocol.events.InternalTestOutputEvent;
import org.gradle.tooling.internal.protocol.events.InternalTestOutputResult;

public class DefaultTestOutputEvent extends AbstractProgressEvent<InternalOperationDescriptor> implements InternalTestOutputEvent {

    private final InternalOperationDescriptor descriptor;
    private final InternalTestOutputResult result;

    public DefaultTestOutputEvent(long startTime, InternalOperationDescriptor descriptor, InternalTestOutputResult result) {
        super(startTime, descriptor);
        this.descriptor = descriptor;
        this.result = result;
    }

    @Override
    public String getDisplayName() {
        return "output";
    }

    @Override
    public InternalOperationDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public InternalTestOutputResult getResult() {
        return result;
    }
}
