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

package io.github.jidcoo.lce.base.intf;

import io.github.jidcoo.lce.base.strategy.BaseParameterAcceptStrategy;
import io.github.jidcoo.lce.base.strategy.BasePrintingStrategy;

import java.util.List;
import java.util.logging.Level;

/**
 * <p>Let's start our dreams here! [↖（^ω^）↗]</p>
 *
 * <p>LeetcodeJavaDebugEnhancer is a debugging enhanced startup interface class.</p>
 * <p>A public algorithm class is called <tt>Algorithm-Target(AT)</tt>.</p>
 * <p>Notice: All <tt>AT</tt> that require debugging must implement this interface!!!</p>
 *
 * @author Jidcoo
 * @since 1.0.0
 */
public interface LeetcodeJavaDebugEnhancer {

    /**
     * <p>If you need to customize an input provider, please return a valid instance of
     * the input provider. For example, you can provide the input sources from
     * files or networks.</p>
     *
     * <p>If the method returns null, the {@link LeetcodeJavaDebugEnhancer} will use
     * the console as the input provider</p>
     *
     * @return the input provider.
     */
    default InputProvider getInputProvider() {
        return null;
    }

    /**
     * <p>If you need to customize an output consumer, please return a valid instance of
     * the output consumer. For example, you can consume the output sources to files
     * or networks.</p>
     *
     * <p>If the method returns null, the {@link LeetcodeJavaDebugEnhancer} will use
     * the console as the output consumer</p>
     *
     * @return the output consumer.
     */
    default OutputConsumer getOutputConsumer() {
        return null;
    }

    /**
     * <p>If you need to customize the printing of the output object, please return
     * a list of printing strategies, and the {@link LeetcodeJavaDebugEnhancer} will
     * try to find the appropriate strategy from this list to print the output object.
     * </p>
     *
     * @return a list of printing strategies
     */
    default List<BasePrintingStrategy<?>> getOutputPrintStrategies() {
        return null;
    }

    /**
     * Return the LeetcodeJavaDebugEnhancer log level.
     * By default, logging is turned off.
     * <p>The only available log levels are {@link Level#OFF},
     * {@link Level#SEVERE}, {@link Level#WARNING}
     * and {@link Level#INFO}. </p>
     *
     * @return the log level.
     * @see Level#OFF
     * @see Level#SEVERE
     * @see Level#WARNING
     * @see Level#INFO
     */
    default Level getEnhancerLogLevel() {
        return Level.OFF;
    }

    /**
     * Return the custom enhancer payload.
     *
     * <p>For example, if you want to debug an outer algorithm solution
     * instead of an inner algorithm solution, you can use this api to
     * specify the outer algorithm solution class.
     *
     * @return the Enhancer payload class.
     */
    default Class<?> getEnhancerPayload() {
        return null;
    }

    /**
     * <p>If you need to customize the accepting of the input parameter, please return
     * a list of parameter accepting strategies, and the {@link LeetcodeJavaDebugEnhancer} will
     * try to find the appropriate strategy from this list to accept the input parameter.
     *
     * @return a list of parameter accepting strategies
     */
    default List<BaseParameterAcceptStrategy<?>> getParameterAcceptStrategies() {
        return null;
    }
}
