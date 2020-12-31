package com.guodf.owner.util.SmsUtils;

import com.guodf.owner.common.util.ConfigUtil;
import com.guodf.owner.util.HttpOutApi.HttpClient;
import lombok.extern.slf4j.Slf4j;

/**
 * 短信下发
 * 验证码校验
 */
@Slf4j
public class SmsUtil {

    /**
     * @param phone
     * @param str
     * @return
     * @throws Exception
     */
    public static String sendSms(String phone, String str) throws Exception {
        String url = ConfigUtil.getValue("url");
        String para = "?phone="+phone+"&str"+str;
        String a = HttpClient.sendGet(url,para);
        return a;
    }
}
