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

package io.github.lce.base.utils;

import java.util.*;

/**
 * Common java data container util.
 *
 * @author Jidcoo
 * @since 1.0.0
 */
public class ContainerUtil {

    /**
     * Check if the map is empty.
     *
     * @param map the map.
     * @return true if the map is null or empty.
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return Objects.isNull(map) || map.isEmpty();
    }

    /**
     * Check if the map is not empty.
     *
     * @param map the map.
     * @return true if the map is not null and not empty.
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * Check if the collection is empty.
     *
     * @param collection the collection.
     * @return true if the collection is null or empty.
     */
    public static boolean isEmpty(Collection<?> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    /**
     * Check if the collection is empty.
     *
     * @param collection the collection.
     * @return true if the collection is null or empty.
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}
