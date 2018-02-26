package com.example.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;


/**
 * 自定义error页面
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/2/26 14:12
 * @Version 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "server.error.whitelabel", name = "enabled", matchIfMissing = true)
//@Conditional(ErrorTemplateMissingCondition.class)
public class WhitelabelErrorViewConfiguration {

//    private final View defaultErrorView = new SpelView(
//            "<html><body><h1>Whitelabel Error Page</h1>"
//                    + "<p>This application has no explicit mapping for /error, so you are seeing this as a fallback.</p>"
//                    + "<div id='created'>${timestamp}</div>"
//                    + "<div>There was an unexpected error (type=${error}, status=${status}).</div>"
//                    + "<div>${message}</div></body></html>");
//
//    @Bean(name = "error")
//    @ConditionalOnMissingBean(name = "error")
//    public View defaultErrorView() {
//        return this.defaultErrorView;
//    }
}
