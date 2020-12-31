package com.guodf.owner.util.CastUtil;

import java.util.ArrayList;
import java.util.List;

public class CastUtil {
    /**
     * 对象强转，Obj转List
     *
     * @param obj
     * @param clazz
     * @return
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}
