/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.glowroot.local.ui;

import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.immutables.value.Json;
import org.immutables.value.Value;

import org.glowroot.common.Marshaling2;

@Value.Immutable
@Json.Marshaled
abstract class Layout {

    abstract boolean jvmHeapDump();
    abstract String footerMessage();
    abstract boolean passwordEnabled();
    abstract List<LayoutPlugin> plugins();
    abstract List<String> transactionTypes();
    abstract String defaultTransactionType();
    abstract List<String> transactionCustomAttributes();
    abstract long fixedAggregateIntervalSeconds();
    abstract long fixedGaugeIntervalSeconds();

    @Value.Derived
    public String version() {
        return Hashing.sha1().hashString(Marshaling2.toJson(this), Charsets.UTF_8).toString();
    }

    @Value.Immutable
    @Json.Marshaled
    abstract static class LayoutPlugin {
        abstract String id();
        abstract String name();
    }
}