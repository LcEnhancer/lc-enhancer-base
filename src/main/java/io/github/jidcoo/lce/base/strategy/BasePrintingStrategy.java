/*
 * Copyright (C) 2025-2030 Jidcoo(https://github.com/jidcoo).
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

package io.github.jidcoo.lce.base.strategy;

import io.github.jidcoo.lce.base.interfaces.Strategizable;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

/**
 * <p>BasePrintingStrategy is an abstract class
 * for object printing strategies. </p>
 * <p>When printing output objects, we will
 * use appropriate printing strategies to
 * elegantly print the output results.
 * </p>
 *
 * @author Jidcoo
 * @see Strategizable
 * @since 1.0.0
 */
public abstract class BasePrintingStrategy<Output> implements Strategizable<Output, BasePrintingStrategy<?>, String> {

    /**
     * Print the output.
     *
     * @param output        the output.
     * @param strategiesMap the strategies map that can be used during this printing process.
     *                      <p>The key is the output object class to which this BasePrintingStrategy
     *                      applies. The value is a set of strategy with the same accepted type.
     *                      And the set is sorted the priority of {@link Strategizable} based on
     *                      {@code getOrder()}.
     *                      </p>
     * @return the printed string content.
     */
    protected abstract String printOutput(Output output, Map<Class<?>, Set<BasePrintingStrategy<?>>> strategiesMap);

    /**
     * Accept object.
     *
     * @param type       the object type.
     * @param object     the object.
     * @param strategies the strategies that can be used during the acceptance process.
     * @return the accepted output.
     */
    @SuppressWarnings("unchecked")
    @Override
    public final String accept(Type type, Object object, Map<Class<?>, Set<BasePrintingStrategy<?>>> strategies) {
        return printOutput((Output) object, strategies);
    }
}