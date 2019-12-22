package com.site.blog.my.core.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * 判空工具类
 *
 * @author yuzy
 * @version 1.0
 * @date 2019/12/22 23:15
 */

public class BlogObjectUtils {

    /**
     * 判断字符串是否为空,取自apache的StringUtils,空格也会被认为是空
     * @param cs 字符串序列
     * @return result 是否为空
     */
    public static boolean isEmpty(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空
     * @param cs 字符串序列
     * @return result 是否不为空
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 判断集合是否为空
     * @param collection 指定泛型集合
     * @return result 是否为空
     */
    public static <T> boolean isNotEmpty(Collection<T> collection){

        if(collection != null && !collection.isEmpty()){
            Iterator<T> iterator = collection.iterator();
            if(iterator != null){
                while(iterator.hasNext()){
                    Object next = iterator.next();
                    if(next != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断map是否为空
     * @param map Map
     * @param <T> 泛型
     * @return result 是否为空
     */
    public static <T> boolean isEmpty(Map<T,T> map){
        return map == null || map.isEmpty();
    }
}
