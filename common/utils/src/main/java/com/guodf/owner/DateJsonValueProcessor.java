package com.guodf.owner;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 当date 转json时，格式化
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    //目标格式
    private String format = "yyyy-MM-dd HH:mm:ss";

    public DateJsonValueProcessor(){

    }

    public DateJsonValueProcessor(String format){
        this.format = format;
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        String[] obj = {};
        if (o instanceof Date[]){
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date[] dates = (Date[]) o;
            obj = new String[dates.length];
            for (int i = 0; i < dates.length; i++){
                obj[i] = sf.format(dates[i]);
            }
        }
        return obj;
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        if (o instanceof Date){
            String str = new SimpleDateFormat(format).format((Date) o);
            return str;
        }
        return o;
    }

}
