package com.newcodebbs.config;

import com.newcodebbs.interceptor.AutoInterceptor;
import com.newcodebbs.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;

/**
 * <p>
 * 路由拦截器配置类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //不屏蔽的路径
        String[] excludePatterns = new String[]{
                "/api/post/**",
                "/api/user/**",
                "/swagger-resources/**",
                "/webjars/**", "/v2/**",
                "/swagger-ui.html/**",
                "/api",
                "/api-docs",
                "/api-docs/**",
                "/doc.html/**",
                "/api/captcha/**"
        };
        // 登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        excludePatterns
                ).order(1);
//         token刷新的拦截器
        registry.addInterceptor(new AutoInterceptor(stringRedisTemplate)).addPathPatterns("/**").excludePathPatterns(
                excludePatterns
        ).order(0);
    }
    
    /**
     * 设置静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/home/**").addResourceLocations("classpath:/web/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    /**
     * 通过knife4j生成接口文档
     * @return
     */
    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("newcodebbs(新代码论坛)api接口文档")
                .version("1.0")
                .description("newcodebbs(新代码论坛)api接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.newcodebbs.controller"))
                .paths(PathSelectors.any())
                .build().useDefaultResponseMessages(false);
        return docket;
    }
}
