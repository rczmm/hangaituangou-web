package com.dsy.hangaituangou.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.dsy.hangaituangou.utils.SecurityUtils;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MybatisPlusConfig {

    /**
     * 定义需要忽略的表（不进行多租户处理）
     */
    private static final List<String> IGNORE_TENANT_TABLES = new ArrayList<>();
    static {
        IGNORE_TENANT_TABLES.add("sys_tenant"); // 租户表
        IGNORE_TENANT_TABLES.add("sys_user");   // 用户表
        IGNORE_TENANT_TABLES.add("sys_role");   // 角色表
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        
        // 添加租户拦截器
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                // 从当前登录用户中获取租户ID
                Long tenantId = SecurityUtils.getCurrentTenantId();
                if (tenantId == null) {
                    throw new RuntimeException("获取租户信息失败");
                }
                return new LongValue(tenantId);
            }

            @Override
            public String getTenantIdColumn() {
                // 租户ID的字段名
                return "tenant_id";
            }

            @Override
            public boolean ignoreTable(String tableName) {
                // 返回true表示不进行租户隔离
                return IGNORE_TENANT_TABLES.contains(tableName);
            }
        }));

        return interceptor;
    }
}