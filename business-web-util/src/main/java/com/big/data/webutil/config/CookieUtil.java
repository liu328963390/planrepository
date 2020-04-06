package com.big.data.webutil.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtil {

    public static String getCookieValue(HttpServletRequest request,String cookieName,boolean isDecorder){
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookieName == null){
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieName)){
                    if (isDecorder){
                        retValue = URLDecoder.decode(cookies[i].getValue(),"utf8");
                    }else {
                        retValue = cookies[i].getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    public static void setCookie(HttpServletRequest request, HttpServletResponse response,String cookieName,String cookieValue,int cookieMaxage,boolean isEncode){
        try {
            if (cookieValue == null){
                cookieValue = "";
            }else if (isEncode){
                cookieValue = URLEncoder.encode(cookieValue,"utf8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage>=0){
                cookie.setMaxAge(cookieMaxage);
            }
            if (null != request){
                cookie.setDomain(getDomainName(request));
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static String getDomainName(HttpServletRequest request) {
        String domainName = null;
        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")){
            domainName = "";
        }else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0,end);
            final String[] split = serverName.split("\\.");
            int len = split.length;
            if (len >3){
                domainName = split[len-3]+"."+split[len-2]+"."+split[len-1];
            }else if (len<=3 && len>1){
                domainName = split[len-2]+"."+split[len-1];
            }else {
                domainName = serverName;
            }
        }
        if (domainName != null && domainName.indexOf(":")>0){
            String[] splits = domainName.split("\\:");
            domainName = splits[0];
        }
        return domainName;
    }

    public static void deleteCookie(HttpServletResponse response,HttpServletRequest request,String cookieName){
        setCookie(request,response,cookieName,null,0,false);
    }
}
