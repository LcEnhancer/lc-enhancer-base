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

package io.github.lcehancer.base.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

/**
 * <p>ParameterAcceptStrategyExceptionTracer is an exception class
 * used to track exceptions during the matching process
 * between the leetcode invoker and leetcode input.</p>
 *
 * <p>When all leetcode invokers and inputs cannot match,
 * detailed error matching information will be output
 * based on ParameterAcceptStrategyExceptionTracer to
 * troubleshoot parameter matching issues.
 * </p>
 *
 * @author Jidcoo
 * @since 1.0.0
 */
public final class ParameterAcceptStrategyExceptionTracer extends EnhancerException {

    /**
     * The ParameterAcceptStrategy name for throws.
     */
    private final String parameterAcceptStrategy;

    /**
     * Constructs a new enhancer exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * (which typically contains the class and detail message of
     * <tt>cause</tt>).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwable.
     *
     * @param parameterAcceptStrategy the ParameterAcceptStrategy name for throws.
     * @param cause                   the cause (which is saved for later retrieval by the
     *                                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                                permitted, and indicates that the cause is nonexistent or
     *                                unknown.)
     */
    public ParameterAcceptStrategyExceptionTracer(String parameterAcceptStrategy, Throwable cause) {
        super(cause);
        this.parameterAcceptStrategy = parameterAcceptStrategy;
    }

    /**
     * Get cur tracer detail.
     *
     * @return the string tracer detail.
     */
    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        getCause().printStackTrace(printWriter);
        String stringBuilder = "<ParameterAcceptStrategyExceptionTracer> - " +
                (Objects.isNull(parameterAcceptStrategy) ? null : parameterAcceptStrategy.substring(parameterAcceptStrategy.lastIndexOf('.') + 1)) +
                "\n -- ParameterAcceptStrategy: \n" +
                parameterAcceptStrategy +
                "\n -- Error: \n" +
                getMessage() +
                "\n -- Detail: \n" +
                stringWriter;
        try {stringWriter.close();} catch (IOException ignored) {}
        return stringBuilder;
    }
}
