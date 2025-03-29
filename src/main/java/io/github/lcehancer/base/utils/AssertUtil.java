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

package io.github.lcehancer.base.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Simple assert util.
 *
 * @author Jidcoo
 * @since 1.0.0
 */
public class AssertUtil {

    /**
     * Assert the flag is true.
     *
     * @param flag boolean flag.
     * @param msg  the message you want to show if the flag is false.
     */
    public static void isTrue(boolean flag, String msg) {
        if (!flag) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * Assert the object is non-null object.
     *
     * @param object the object.
     * @param msg    the message you want to show if the object is null.
     */
    public static void nonNull(Object object, String msg) {
        isTrue(Objects.nonNull(object), msg);
    }

    /**
     * Assert the collection is not empty.
     *
     * @param collection the collection.
     * @param msg        the message you want to show if the collection is empty.
     */
    public static void notEmpty(Collection<?> collection, String msg) {
        isTrue(Objects.nonNull(collection) && !collection.isEmpty(), msg);
    }

    /**
     * Assert the map is not empty.
     *
     * @param map the map.
     * @param msg the message you want to show if the map is empty.
     */
    public static void notEmpty(Map<?, ?> map, String msg) {
        isTrue(Objects.nonNull(map) && !map.isEmpty(), msg);
    }

    /**
     * Assert the set is not empty.
     *
     * @param set the set.
     * @param msg the message you want to show if the set is empty.
     */
    public static void notEmpty(Set<?> set, String msg) {
        isTrue(Objects.nonNull(set) && !set.isEmpty(), msg);
    }

    /**
     * Assert the string is not blank.
     *
     * @param str the string.
     * @param msg the message you want to show if the string is blank.
     */
    public static void notBlank(String str, String msg) {
        isTrue(Objects.nonNull(str) && !StringUtil.isBlank(str), msg);
    }

    /**
     * Assert the arguments are equal to each other
     *
     * @param o1  the object.
     * @param o2  the object.
     * @param msg the message you want to show if the objects is not equals.
     */
    public static void equals(Object o1, Object o2, String msg) {
        isTrue(Objects.equals(o1, o2), msg);
    }
}
