/*
 * @Project Name: security
 * @File Name: SwaggerConfiguration.java
 * @Package Name: com.atom.demo.security.conf
 * @Date: 2021年5月18日上午11:27:28
 * @Creator: xuchuandi
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.atom.demo.security.conf;


import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * TODO
 * @author xuchuandi
 * @date 2021年5月18日上午11:27:28
 * @see
 */
@EnableOpenApi
@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {
    @Bean
    public Docket createRestApi() {
        //返回文档摘要信息
    	//3.0 swagger-ui.html改成swagger-ui/index.html
    	//这里很关键，需要用SWAGGER_2，不能使用OAS_30，否则Authorize出不来
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(securitySchemes()))
                .securityContexts(Arrays.asList(securityContexts()))
                ;
    }

    //生成接口信息，包括标题、联系人等
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("如有疑问，请联系xxx")
                .contact(new Contact("xxx", "xxx", "xxx"))
                .version("1.0")
                .build();
    }

    private SecurityScheme securitySchemes() {
        //return new ApiKey("Authorization", "Authorization", "header");
    	//改成password 默认认证
    	  GrantType grant = new ResourceOwnerPasswordCredentialsGrant("http://localhost:8080/oauth/token");
          return new OAuthBuilder().name("OAuth2")
                  .grantTypes(Arrays.asList(grant))
                  .scopes(Arrays.asList(scopes()))
                  .build();
    }
    
    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("all", "all scope")
        };
    }

    @SuppressWarnings("deprecation")
	private SecurityContext securityContexts() {
    	/*
        return SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.any())
                        .build();
                        */
    	//改成password 默认认证
    	 return SecurityContext.builder()
                 .securityReferences(Arrays.asList(new SecurityReference("OAuth2", scopes())))
                 .forPaths(PathSelectors.any())
                 .build();
     }
    
    
}