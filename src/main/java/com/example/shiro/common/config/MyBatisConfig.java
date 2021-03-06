package com.example.shiro.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tianhao
 * @2019/12/9 0009 20:08
 */

@Configuration
public class MyBatisConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求 默认false
        interceptor.setOverflow(true);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        interceptor.setLimit(500);
        return interceptor;
    }
}
