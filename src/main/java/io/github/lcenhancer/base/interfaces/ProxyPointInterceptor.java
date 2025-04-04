/*
 * Copyright (C) 2025-2030 LcEnhancer(https://github.com/lcenhancer).
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

package io.github.lcenhancer.base.interfaces;

import io.github.lcenhancer.base.proxy.ProxyPointParameterView;

/**
 * <p>ProxyPointInterceptor is an interceptor used to
 * enhance a specified proxy point before or after
 * invoking.
 *
 * <p>ProxyPointInterceptor inherits from the {@link Order}
 * interface and is used to handle the priority of
 * interceptors.
 *
 * @author Jidcoo
 * @since 1.0.0
 */
public interface ProxyPointInterceptor<PointResult> extends Order {

    /**
     * Return the proxy point name that needs to
     * be intercepted.
     *
     * @return the proxy point name.
     */
    String interceptPoint();

    /**
     * Intercept and process parameters before
     * invoking proxy point.
     *
     * @param leetcodeJavaDebugEnhancer the LeetcodeJavaDebugEnhancer instance.
     * @param parameterView             the proxy point parameter view.
     */
    default void onBefore(LeetcodeJavaDebugEnhancer leetcodeJavaDebugEnhancer, ProxyPointParameterView parameterView) {}

    /**
     * Intercept and process proxy point result
     * after invoking proxy point.
     *
     * @param leetcodeJavaDebugEnhancer the LeetcodeJavaDebugEnhancer instance.
     * @param pointResult               the proxy point result.
     * @return the processed proxy point result.
     */
    default PointResult onAfter(LeetcodeJavaDebugEnhancer leetcodeJavaDebugEnhancer, PointResult pointResult) {
        return pointResult;
    }
}
