package com.plan.newyear.util.time;

public class NewTes {

}
/*
    /**
     * 防止js，jsp，html访问外部网站
     */
//    public class ServerNameFilter implements Filter {
//        public FilterConfig config;
//        public void init(FilterConfig filterConfig) throws ServletException {
//            config = filterConfig;
//        }
//        static String serverName="localhost,192.168.20.33";
//        public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//            HttpServletRequest servletrequest = (HttpServletRequest) request;
//            HttpServletResponse servletresponse = (HttpServletResponse) response;
//            //只接受来自本域的请求，过滤js,html,jsp等
//            if(!serverName.contains(servletrequest.getServerName())){
//                return ;
//            }
//            filterChain.doFilter(servletrequest, servletresponse);
//        }
//
//        public void destroy() {
//            this.config = null;
//        }
//    }
//
//    //request请求参数不可修改，覆盖父类方法可修改请求参数
//    public class ParameterRequestWrapper extends HttpServletRequestWrapper{
//        private Map params;
//        public ParameterRequestWrapper(HttpServletRequest request,Map newParams) {
//            super(request);
//            this.params=newParams;
//        }
//        @Override
//        public Map getParameterMap() {
//            return params;
//        }
//        public String getParameter(String name) {
//            Object v = params.get(name);
//            if(v==null){
//                return null;
//            }else if(v instanceof String[]){
//                String []strArr=(String[]) v;
//                if(strArr.length>0){
//                    return strArr[0];
//                }else{
//                    return null;
//                }
//            }else if(v instanceof String){
//                return (String) v;
//            }else{
//                return v.toString();
//            }
//        }
//    }
//
//
//    /**
//     * 过滤非法字符，参数规则验证
//     */
//    public class IllegalCharacterFilter implements Filter {
//        public FilterConfig config;
//        String temp = "";//不需要过滤的方法 "/logon/logon!logonStandard.action"
//
//        public static boolean isContains(String container, String[] regx) {
//            boolean result = false;
//            for (int i = 0; i < regx.length; i++) {
//                if (container.indexOf(regx[i]) != -1) {
//                    return true;
//                }
//            }
//            return result;
//        }
//        String key = "timstamp";
//        public void init(FilterConfig filterConfig) throws ServletException {
//            config = filterConfig;
//        }
//        private Map<String, Object> processParamsters(Map<String, Object> m) {
//            for(String key : m.keySet()) {
//                Object paramValue = m.get(key);
//                if(paramValue!=null){
//                    String value = String.valueOf(paramValue);
//                    value = value.replaceAll("<", "《");
//                    value = value.replaceAll(">", "》");
//                    value = value.replaceAll("'", "");
//                    value = value.replaceAll("\\'", "");
//                    value = value.replaceAll("%", "％");
//                    value = value.replaceAll("\\%", "％");
//                    value = value.replaceAll("\\.", "。");
//                    value = value.replaceAll("\\_", "－");
//                    value = value.replaceAll("\\@", "＠");
//                    m.put(key, value);
//                }
//            }
//            return m ;
//        }
//        public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//            HttpServletRequest servletrequest = (HttpServletRequest) request;
//            HttpServletResponse servletresponse = (HttpServletResponse) response;
//            servletresponse.setHeader("Cache-Control","no-store");
//            servletresponse.setHeader("Pragrma","no-cache");
//            servletresponse.setDateHeader("Expires",0);
//            String timstamp  =request.getParameter(key);
//            if(!temp.contains(servletrequest.getServletPath())){
//                Cookie[] cookies = servletrequest.getCookies();
//                if(cookies==null||cookies.length==1){
//                    return ;
//                }else if(cookies.length>1){
//                    //验证规则,必须有含有key的cookie
//                    boolean a=true;
//                    for(Cookie cookie : cookies) {
//                        if(cookie.getName().equals(key)){
//                            a= false;
//                            String aa =cookie.getValue();
//                            if(aa.length()!=32){
//                                return ;
//                            }
//                            break;
//                        }
//                    }
//                    if (a)
//                        return;
//                }
//                if (timstamp != null){
//                    if(timstamp.length() != 32) {
//                        return;
//                    }
//                }
//            }else{
//                if(timstamp==null||timstamp.length() != 32) {
//                    return;
//                }
//            }
//            String a = UUIDGenerator.generate32();//自定义规则
//            Cookie cookie = new Cookie(key,a);
//            cookie.setPath("/");
//            servletresponse.addCookie(cookie);
//            Map<String, Object> m = InMap2Map.getMapFormInMap(request.getParameterMap());
//            ParameterRequestWrapper wrapRequest=new ParameterRequestWrapper(servletrequest,processParamsters(m));
//            filterChain.doFilter(wrapRequest, servletresponse);
//        }
//
//        public void destroy() {
//            this.config = null;
//        }
//
//        /**
//         * 将页面的获得的参数集合inmap放到map<String,Object>中。
//         * @date 2011-12-15
//         * @author Oba
//         */
//        public class InMap2Map {
//            /**
//             * 由于页面传入的map是<String,Object[]>类型，所以要将其转化为Map<String,Object>类型
//             * @param inMap 页面传入的map
//             * @return 正确类型的map
//             */
//            public static Map<String,Object> getMapFormInMap(Map<String,Object> inMap){
//                Map<String,Object> map = new HashMap<String,Object>();
//                Iterator<String> it = inMap.keySet().iterator();
//                while (it.hasNext()) {
//                    String key = (String) it.next();
//                    Object value = ((Object[])inMap.get(key))[0];
//                    map.put(key, value);
//                }
//                return map;
//            }
//        }
//        配置js文件
//Ext.Ajax.on('beforerequest',function(conn,options){
//            var security_key =  getSercurity();
//            Ext.Ajax.extraParams={'timstamp':security_key};
//        });
//
//        function getSercurity(){
//            var arrStr = document.cookie.split("; ");
//            for(var i = 0;i < arrStr.length;i ++){
//                var temp = arrStr[i].split("=");
//                if(temp[0] == "timstamp")
//                    return temp[1];
//            }
//        };*/
//        //同步请求方法
//        function SynReq (url){
//            var obj=null;
//            if (window.ActiveXObject) {
//                obj = new ActiveXObject('Microsoft.XMLHTTP');
//            }else if (window.XMLHttpRequest) {
//                obj = new XMLHttpRequest();
//            }
//            obj.open('POST', url, false);
//            obj.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//            obj.send('timstamp='+getSercurity());
//            return obj;	  }
//
//
//            service.xml配置SSL
//                <Connector connectionTimeout="20000" port="80" protocol="HTTP/1.1" redirectPort="443"  URIEncoding="UTF-8"/>
//  <Connector port="443" protocol="HTTP/1.1" SSLEnabled="true"
//        scheme="https" secure="true"
//        clientAuth="false"
//        sslProtocol="TLS"
//        keystoreFile="C:/Java/jdk1.6.0_31/bin/webspeed.keystore"
//        keystorePass="111111" />
//    <Connector port="8009" protocol="AJP/1.3" redirectPort="443"/>
//
//*/
//
//        修改web.xml，修改如下配置:
//<!--禁用 WebDAV,或者禁止不需要的 HTTP 方法 -->
//<security-constraint>
//	<web-resource-collection>
//		<url-pattern>/*</url-pattern>
//		<http-method>PUT</http-method>
//		<http-method>DELETE</http-method>
//		<http-method>HEAD</http-method>
//		<http-method>OPTIONS</http-method>
//		<http-method>TRACE</http-method>
//	</web-resource-collection>
//	<auth-constraint>
//	</auth-constraint>
//</security-constraint>
//<login-config>
//	<!-- Authorization setting for SSL -->
//	<auth-method>CLIENT-CERT</auth-method>
//	<realm-name>Client Cert Users-only Area</realm-name>
//	<auth-method>BASIC</auth-method>
//</login-config>
//<security-constraint>
//	<!-- Authorization setting for SSL -->
//	<web-resource-collection>
//		<web-resource-name>SSL</web-resource-name>
//		<url-pattern>/logon/login.jsp</url-pattern>
//	</web-resource-collection>
//	<user-data-constraint>
//		<transport-guarantee>CONFIDENTIAL</transport-guarantee>
//	</user-data-constraint>
//</security-constraint>
//	此为从https转到http时的跳转值，为应用部署服务器IP地址为端口
//登录方法添加
//HttpServletRequest request = Struts2Utils.getRequest();
//result =  "http://"+request.getServerName()+":80/petition/petition.jsp";
//Struts2Utils.getResponse().getWriter().print(result);
//
//
//    }
//public void home(HttpServletResponse response) throws Exception{
//    String state = String.valueOf(new Random(10000).nextInt());
//    // http://www.baidu.com?code=djsfisdjfids
//    String redirectUrl = URLEncoder.encode(this.redirectUrl,"UTF-8");
//    StringBuilder stringBuilder = new StringBuilder(authorize).append("/idp/oauth2/authorize");
//    stringBuilder.append("?client_id=").append(clientId)
//            .append("&redirect_uri=").append(redirectUrl)
//            .append("&response_type=").append(code)
//            .append("&state=").append(state);
//    response.sendRedirect(stringBuilder.toString());
//}
//
//
//e用户信息
//public String token(String code, HttpServletRequest request) {
//    try {
//        Map<String,String> param = Maps.newHashMap();
//        param.put("client_id",clientId);
//        param.put("client_secret",CLIENT_SECRET);
//        param.put("code",code);
//        param.put("grant_type","authorization_code");
//        String postURL = authorize + "/idp/oauth2/getToken";
//        String res = OkHttpUtils.post(postURL,param);
//        // {"access_token":"a16e5f2993548643834ef498570718cb","refresh_token":"e1a515ac3e70d5f82120ebc3a995c32d","uid":"2","expires_in":45580}
//        JSONObject jsonObject = JSONObject.parseObject(res);
//        String accessToken = jsonObject.getString("access_token");
//        StringBuilder userUrl = new StringBuilder(authorize + "/idp/oauth2/getUserInfo");
//        userUrl.append("?access_token=").append(accessToken).append("&client_id=").append(clientId);
//        String userRes = OkHttpUtils.get(userUrl.toString());
//        //{"spRoleList":["heliang01"],"uid":"clare"}
//        JSONObject userResJsonObject = JSONObject.parseObject(userRes);
//        JSONArray jsonArray = userResJsonObject.getJSONArray("spRoleList");
//        String loginName = "";
//        if (jsonArray.size() == 1) {
//            loginName = jsonArray.getString(0);
//        }
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(loginName,"123456");
//        subject.login(token);
//        return jsonArray.toJSONString();
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    return null;
//}
//*/
