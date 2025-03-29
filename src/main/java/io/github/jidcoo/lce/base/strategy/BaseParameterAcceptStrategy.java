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

import io.github.jidcoo.lce.base.exception.ParameterAcceptStrategyExceptionTracer;
import io.github.jidcoo.lce.base.intf.Strategizable;
import io.github.jidcoo.lce.base.utils.TypeUtil;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * <p>BaseParameterAcceptStrategy is an abstract class
 * for parameter accept strategies. </p>
 *
 * <p>When performing dynamic matching between
 * the leetcode invoker and leetcode input, it is necessary
 * to use BaseParameterAcceptStrategy to specialize
 * certain parameters and parameter types in order to
 * meet the matching rules of the leetcode invoker and
 * leetcode input.
 * </p>
 *
 * @author Jidcoo
 * @see Strategizable
 * @since 1.0.0
 */
public abstract class BaseParameterAcceptStrategy<Parameter> implements Strategizable<Parameter,
        BaseParameterAcceptStrategy<?>, Parameter> {

    /**
     * Accept the object.
     *
     * @param object        the object.
     * @param type          the parameter type.
     * @param strategiesMap the strategies map that can be used during this accepting process.
     *                      <p>The key is the output object class to which this BaseParameterAcceptStrategy
     *                      applies. The value is a set of strategy with the same accepted type.
     *                      And the set is sorted the priority of {@link Strategizable} based on
     *                      {@code getOrder()}.
     *                      </p>
     * @return the accepted parameter.
     */
    protected abstract Parameter acceptParameter(Object object, Type type,
                                                 Map<Class<?>, Set<BaseParameterAcceptStrategy<?>>> strategiesMap);

    /**
     * Common accepting parameter function.
     *
     * @param strategies    the strategy set for parameter accepting.
     * @param parameterType the parameter type.
     * @param object        the parameter object.
     * @return {@link ParameterAcceptResult}
     */
    protected ParameterAcceptResult commonAcceptingFunction(Map<Class<?>, Set<BaseParameterAcceptStrategy<?>>> strategies,
                                                            Type parameterType,
                                                            Object object) {
        // Create a tracer stack for tracking the acceptance process.
        Stack<ParameterAcceptStrategyExceptionTracer> tracerStack = new Stack<>();

        try {
            // Find the strategy set for the parameter acceptance.
            Set<BaseParameterAcceptStrategy<?>> strategySet = findStrategySet(
                    TypeUtil.obtainRawTypeOfType(parameterType),
                    strategies
            );
            for (BaseParameterAcceptStrategy<?> acceptStrategy : strategySet) {
                try {
                    // Try to accept the parameter and return the accepted result.
                    return ParameterAcceptResult.accept(acceptStrategy.accept(parameterType, object, strategies));
                } catch (Throwable e) {
                    // Push the throwable with the object tracer into stack.
                    tracerStack.push(new ParameterAcceptStrategyExceptionTracer(acceptStrategy.getClass().getName(),
                            e));
                }
            }
        } catch (Throwable throwable) {
            // Push the throwable with the object tracer into stack.
            tracerStack.push(new ParameterAcceptStrategyExceptionTracer(null, throwable));
        }

        // Return the rejected result.
        return ParameterAcceptResult.reject(object, tracerStack);
    }

    /**
     * Accept object.
     *
     * @param type       the object type.
     * @param object     the object.
     * @param strategies the strategies that can be used during the acceptance process.
     * @return the accepted output.
     */
    @Override
    public final Parameter accept(Type type, Object object,
                                  Map<Class<?>, Set<BaseParameterAcceptStrategy<?>>> strategies) {
        return acceptParameter(object, type, strategies);
    }

    /**
     * <p>ParameterAcceptResults is a result class
     * that represents whether a parameter object
     * is accepted.</p>
     *
     * <p>When the result get from {@link ParameterAcceptResult#isAccepted()}
     * is true, it indicates that the parameter object
     * has been accepted, and you can obtain the
     * accepted object by {@link ParameterAcceptResult#getObject()}.
     * </p>
     *
     * <p>When the result get from {@link ParameterAcceptResult#isAccepted()}
     * is false, it indicates that the parameter object
     * cannot be accepted. You can obtain the details of
     * the exception that occurred during the parameter
     * acceptance process by {@link ParameterAcceptResult#getTracer()}}.
     * </p>
     */
    public static final class ParameterAcceptResult {

        /**
         * The accepted object.
         * <p>When the current result {@link Code} is {@link Code#PASS},
         * this object must be assigned a value of accepted data.</p>
         */
        private final Object object;

        /**
         * Parameter object acceptance exception tracker.
         */
        private final Stack<ParameterAcceptStrategyExceptionTracer> tracer;

        /**
         * Parameter accept result code.
         */
        private final Code resultCode;

        /**
         * Create a ParameterAcceptResult instance.
         *
         * @param object the accepted object.
         * @param code   the result code.
         * @param tracer the parameter object acceptance exception tracker.
         */
        private ParameterAcceptResult(Object object, Code code, Stack<ParameterAcceptStrategyExceptionTracer> tracer) {
            this.object = object;
            this.resultCode = code;
            this.tracer = tracer;
        }

        /**
         * Create a accepted result.
         *
         * @param object the accepted object.
         * @return the accepted result.
         */
        public static ParameterAcceptResult accept(Object object) {
            return new ParameterAcceptResult(object, Code.PASS, null);
        }

        /**
         * Create a rejected result.
         *
         * @param object      the object.
         * @param tracerStack the parameter object acceptance exception tracker stack.
         * @return the rejected result.
         */
        public static ParameterAcceptResult reject(Object object,
                                                   Stack<ParameterAcceptStrategyExceptionTracer> tracerStack) {
            return new ParameterAcceptResult(object, Code.REJECT, tracerStack);
        }

        /**
         * Get the accepted object.
         *
         * @return the accepted object.
         */
        @SuppressWarnings("unchecked")
        public <T> T getObject() {
            return (T) object;
        }

        /**
         * Get the result state.
         *
         * @return true if the result code is {@link Code#PASS}.
         */
        public boolean isAccepted() {
            return Code.PASS == this.resultCode;
        }

        /**
         * Get the parameter object acceptance exception tracker.
         *
         * @return the tracker stack.
         */
        public Stack<ParameterAcceptStrategyExceptionTracer> getTracer() {
            return tracer;
        }

        /**
         * Result to string.
         *
         * @return result string.
         */
        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("ParameterAcceptResult: ");
            if (isAccepted()) {
                stringBuilder.append("parameter has been accepted, accepted object: ");
                stringBuilder.append(object);
            } else {
                stringBuilder.append("parameter cannot be accepted, parameter: ");
                stringBuilder.append(object);
                stringBuilder.append(", accepting tracer stack: ");
                for (int i = tracer.size() - 1; i >= 0; i--) {
                    stringBuilder.append("\n");
                    stringBuilder.append(tracer.elementAt(i).toString());
                }
            }
            return stringBuilder.toString();
        }

        private enum Code {
            PASS,
            REJECT
        }
    }
}
