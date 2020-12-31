package com.guodf.owner.util.HttpUtil;

import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

@Slf4j
public class HttpUtil {
    
    //设置超时时长300秒
    private static int conAndSoTimeOut = 300000;

    public static JSONObject postData4Json(String url, String msg) throws Exception {
        return JSONObject.fromObject(postData(url, msg));
    }

    public static String postData(String url, String msg) throws Exception {
        String result = null;
        PostMethod method = null;
        try {
            assertMsgNotNull(msg);
            long begin = System.currentTimeMillis();
            HttpClient httpClient = new HttpClient();
            //设置超时时长
            iniTimeOut(httpClient);
            method = new PostMethod(url);
            StringRequestEntity entity = new StringRequestEntity(msg, "application/json", "utf-8");
            method.setRequestEntity(entity);
            httpClient.executeMethod(method);
            log.info("准备发送目标URL为:" + method.getURI());

            InputStream in = method.getResponseBodyAsStream();
            result = IOUtils.toString(in, "utf-8");
            IOUtils.closeQuietly(in);

            //获取请求返回码
            int statu_code = method.getStatusLine().getStatusCode();

            log.info("响应报文为：" + result);
            log.info("发送请求,完成,耗时:" + (System.currentTimeMillis() - begin));

            assertResultSuccess(statu_code);

        } catch (Exception e) {
            log.error("对端接口发送失败！", e);
            throw e;
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
        return result;
    }

    public static JSONObject getData4Json(String url) throws Exception {
        JSONObject json =  JSONObject.fromObject(getData(url));
		/*if (!"0".equals(json.get("retCode"))) {
			throw new RuntimeException("rest接口返回异常，不处理。"+json.get("retCode"));
		}*/
        return json;
    }

    public static String getData(String url) throws Exception {
        String result = null;
        GetMethod method = null;
        try {
            long begin = System.currentTimeMillis();
            HttpClient httpClient = new HttpClient();
            //设置超时时长
            iniTimeOut(httpClient);
            method = new GetMethod(url);
            //设置请求头
            method.addRequestHeader("Content-type", "application/json; charset=utf-8");
            httpClient.executeMethod(method);
            log.debug("准备发送目标URL为:" + method.getURI());
            InputStream in = method.getResponseBodyAsStream();
            result = IOUtils.toString(in, "utf-8");
            IOUtils.closeQuietly(in);
            //获取请求返回码
            int statu_code = method.getStatusLine().getStatusCode();

            log.debug("响应报文为：" + result);
            log.debug("发送请求,完成,耗时:" + (System.currentTimeMillis() - begin));
            //处理返回值
            assertResultSuccess(statu_code);
        } catch (Exception e) {
            log.error("对端接口发送失败！"+e.getMessage());
            throw e;
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
        return result;
    }

    /**
     * 增加超时时间限制
     */
    private static void iniTimeOut(HttpClient httpClient) {
        log.debug("超时设定为：" + conAndSoTimeOut);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(conAndSoTimeOut);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(conAndSoTimeOut);
        httpClient.getParams().setSoTimeout(conAndSoTimeOut);
    }

    private static void assertMsgNotNull(String msg) throws Exception {
        if (StringUtils.isBlank(msg)) {
            log.warn("待发送的报文为空，请核实！");
            throw new Exception("待发送的报文为空，请核实！");
        }
    }

    private static void assertResultSuccess(int statu_code) throws Exception {
        if (statu_code == 200 || statu_code == 204) {
        } else {
            log.error("调用对端接口响应错误,响应码为" + statu_code);
            throw new Exception("调用对端接口响应错误,响应码为" + statu_code);
        }
    }

    /**
     * 测试方法
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String tmp = HttpUtil.getData("http://dm.10086.cn.cn/res/rank/queryRank?no=2&rank_type=1502");
        JSONObject json = JSONObject.fromObject(tmp);
        if ("0".equals(json.get("retCode"))) {
            JSONArray array= (JSONArray)json.get("object");
            for (int i = 0; i < array.size(); i++) {
                JSONObject detail=(JSONObject)array.get(i);
                log.info(detail.get("contentId").toString());
            }
        }
        log.info(json.toString());
    }

}
