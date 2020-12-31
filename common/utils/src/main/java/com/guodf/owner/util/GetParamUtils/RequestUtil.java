package com.guodf.owner.util.GetParamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @description:对get请求参数调整的方法封装
 * @author: Administrator
 * @date: Created in 2020/8/13 0:24
 * @modified By :  guodf
 */
public class RequestUtil extends HttpServletRequestWrapper {
    private Map params;

    public RequestUtil(HttpServletRequest request, Map newParams) {
        super(request);
        this.params = newParams;
    }

    public Map getParameterMap() {
        return params;
    }

    public Enumeration getParameterNames() {
        Vector l = new Vector(params.keySet());
        return l.elements();
    }

    public String[] getParameterValues(String name) {
        Object v = params.get(name);
        if (v == null) {
            return null;
        } else if (v instanceof String[]) {
            return (String[]) v;
        } else if (v instanceof String) {
            return new String[] { (String) v };
        } else {
            return new String[] { v.toString() };
        }
    }

    public String getParameter(String name) {
        Object v = params.get(name);
        if (v == null) {
            return null;
        } else if (v instanceof String[]) {
            String[] strArr = (String[]) v;
            if (strArr.length > 0) {
                return strArr[0];
            } else {
                return null;
            }
        } else if (v instanceof String) {
            return (String) v;
        } else {
            return v.toString();
        }
    }

    /**
     * 根据request获取所有入参封装方法
     *      1、解决多线程request只能获取一次的问题
     *      2、封装request取参数代码
     * @param request
     * @return
     */
    public static Map<String,String> getParameter(HttpServletRequest request) {

        Map<String,String> outMap = new HashMap<>();
        HashMap<String,Object> params=new HashMap(request.getParameterMap());
        for (String key : params.keySet()) {
            Object v = params.get(key);
            if (v == null) {
                return null;
            } else if (v instanceof String[]) {
                String[] strArr = (String[]) v;
                if (strArr.length > 0) {
                    outMap.put(key, strArr[0]);
                } else {
                    outMap.put(key, "");
                }
            } else if (v instanceof String) {
                outMap.put(key, (String) v);
            } else {
                outMap.put(key, v.toString());
            }
        }
        return outMap;
    }
}