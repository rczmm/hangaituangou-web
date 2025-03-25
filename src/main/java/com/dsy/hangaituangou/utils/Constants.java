package com.dsy.hangaituangou.utils;

/**
 * 常量接口
 */
public interface Constants {

    // appid
    String APPID = "wx4b953fe4ab946673";

    // 密钥
    String SECRET = "02ba32f4ee2444b9d18ae3525245712e";

    // 授权类型
    String GRANT_TYPE = "authorization_code";

    // code2Session
    String CODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=%s";

    // 获取插件用户
    String GET_PLUGIN_USER = "https://api.weixin.qq.com/wxa/getpluginopenpid?access_token=%s";

}
