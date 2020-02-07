package com.plan.respository.acl;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ShiroFilerChainManager {
    @Autowired
    private ShiroFilterFactoryBean shiroFilter;
    @Resource
    private LoginService loginService;
    private Map<String, NamedFilterList> defaultFilterChains;
    public final Logger log = Logger.getLogger(this.getClass());

    public ShiroFilerChainManager() {
    }

    @PostConstruct
    public void init() {
        try {
            AbstractShiroFilter abstractShiroFilter = (AbstractShiroFilter)this.shiroFilter.getObject();
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver)abstractShiroFilter.getFilterChainResolver();
            DefaultFilterChainManager filterChainManager = (DefaultFilterChainManager)filterChainResolver.getFilterChainManager();
            this.defaultFilterChains = new HashMap(filterChainManager.getFilterChains());
            this.initFilterChains(this.loginService.getAuthorization("", ""));
        } catch (Exception var4) {
            this.log.error("context", var4);
        }

    }

    public void initFilterChains(List<Map<String, String>> urlFilters) {
        try {
            AbstractShiroFilter abstractShiroFilter = (AbstractShiroFilter)this.shiroFilter.getObject();
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver)abstractShiroFilter.getFilterChainResolver();
            DefaultFilterChainManager filterChainManager = (DefaultFilterChainManager)filterChainResolver.getFilterChainManager();
            filterChainManager.getFilterChains().clear();
            if (this.defaultFilterChains != null) {
                filterChainManager.getFilterChains().putAll(this.defaultFilterChains);
            }

            Iterator var5 = urlFilters.iterator();

            while(var5.hasNext()) {
                Map<String, String> m = (Map)var5.next();
                if (m != null) {
                    String url = (String)m.get("url");
                    if (!StringUtils.isEmpty(m.get("url"))) {
                        filterChainManager.addToChain(url, "perms", (String)m.get("name"));
                    }
                }
            }
        } catch (Exception var8) {
            this.log.error("context", var8);
        }

    }
}

