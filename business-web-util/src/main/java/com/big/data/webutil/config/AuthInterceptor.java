package com.big.data.webutil.config;

import com.alibaba.fastjson.JSON;
import com.big.data.util.HttpClientUtil;
import io.jsonwebtoken.impl.Base64UrlCodec;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getParameter("newToken");
        if (token != null){
            CookieUtil.setCookie(request,response,"token",token,WebConst.COOKIE_MAXAGE,false);
        }
        if (token == null){
            token = CookieUtil.getCookieValue(request,"token",false);
        }
        if (token != null){
            //获取nickName
            Map map = getUserMapByToken(token);
            String nickName = (String) map.get("nickName");
            request.setAttribute("nickName",nickName);
        }
        //判断当前控制器上是否有注解Loginrequire
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequire methodAnnotation = handlerMethod.getMethodAnnotation(LoginRequire.class);
        if (methodAnnotation != null){
            String salt = request.getHeader("X-forwarded-for");
            String result = HttpClientUtil.doGet(WebConst.VERIFY_ADDRESS+"?token="+token+"&salt="+salt);
            if ("success".equals(result)){
                Map map = getUserMapByToken(token);
                String userId = (String) map.get("userId");
                request.setAttribute("userId",userId);
                return true;
            }else {
                if (methodAnnotation.autoRedirect()){
                    String requestUrl = request.getRequestURL().toString();
                    String encodeURL = URLEncoder.encode(requestUrl, "utf8");
                    response.sendRedirect(WebConst.LOGIN_ADDRESS+"?originalUrl="+encodeURL);
                    return false;
                }
            }
        }
        return true;
    }

    // 获取map集合
    private Map getUserMapByToken(String token) {
        // eyJhbGciOiJIUzI1NiJ9.eyJuaWNrTmFtZSI6IkF0Z3VpZ3UiLCJ1c2VySWQiOiIxIn0.XzRrXwDhYywUAFn-ICLJ9t3Xwz7RHo1VVwZZGNdKaaQ
        // map属于第二部分 可以使用JWTUtil 工具类 ，base64编码
        // 获取token 中第二部分 数据
        String tokenUserInfo  = StringUtils.substringBetween(token, ".");
        // 创建base64 对象
        Base64UrlCodec base64UrlCodec = new Base64UrlCodec();
        // 返回字节数组
        byte[] decode = base64UrlCodec.decode(tokenUserInfo);
        // 将字节数组转化为String
        String tokenJson =null;
        try {
            tokenJson = new String(decode,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return JSON.parseObject(tokenJson,Map.class);

    }
    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterConcurrentHandlingStarted(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    }

}
