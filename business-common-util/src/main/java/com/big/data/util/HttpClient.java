package com.big.data.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HttpClient {

    private String url;
    private Map<String,String> param;
    private int statusCode;
    private String content;
    private String xmlParam;
    private boolean isHttps;

    public boolean isHttps() {
        return isHttps;
    }

    public void setHttps(boolean isHttps){
        this.isHttps = isHttps;
    }

    public String getXmlParam(){
        return xmlParam;
    }

    public void setXmlParam(String xmlParam){
        this.xmlParam = xmlParam;
    }

    public HttpClient (String url,Map<String,String> param){
        this.url = url;
        this.param = param;
    }

    public HttpClient(String url){
        this.url = url;
    }

    public void setParameter(Map<String,String> map){
        param = map;
    }

    public void addParameter(String key,String value){
        if (param == null)
            param = new HashMap<>();
        param.put(key,value);
    }

    public void post(){
        HttpPost httpPost = new HttpPost(url);
        setEnity(httpPost);
        execute(httpPost);
    }

    public void put(){
        HttpPut httpPut = new HttpPut(url);
        setEnity(httpPut);
        execute(httpPut);
    }

    private void execute(HttpUriRequest httpPut) {
        CloseableHttpClient httpClient = null;
        try {
            if (isHttps){
                SSLContext ssl = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                    @Override
                    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        return false;
                    }
                }).build();
                SSLConnectionSocketFactory scf = new SSLConnectionSocketFactory(ssl);
                httpClient = HttpClients.custom().setSSLSocketFactory(scf).build();
            }else {
                httpClient = HttpClients.createDefault();
            }
            CloseableHttpResponse response = httpClient.execute(httpPut);
            try {
                if (response != null){
                    if (response.getStatusLine() != null){
                        statusCode = response.getStatusLine().getStatusCode();
                        HttpEntity entity = response.getEntity();
                        content = EntityUtils.toString(entity,Consts.UTF_8);
                    }
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setEnity(HttpEntityEnclosingRequestBase http) {
        if (param != null){
            LinkedList<NameValuePair> pairs = new LinkedList<>();
            for (String key : param.keySet())
                pairs.add(new BasicNameValuePair(key,param.get(key)));
            http.setEntity(new UrlEncodedFormEntity(pairs, Consts.UTF_8));
        }
        if (xmlParam != null){
            http.setEntity(new StringEntity(xmlParam,Consts.UTF_8));
        }
    }

    public void get(){
        if(param !=null){
            StringBuilder url = new StringBuilder(this.url);
            boolean isFirst = true;
            for (String key :param.keySet()) {
                if (isFirst)
                    url.append("?");
                else
                    url.append("&");
                url.append(key).append("=").append(param.get(key));
            }
            this.url = url.toString();
        }
        HttpGet httpGet = new HttpGet(url);
        execute(httpGet);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getContent(){
        return content;
    }
}
