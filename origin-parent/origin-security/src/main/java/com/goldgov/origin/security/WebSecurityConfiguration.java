package com.goldgov.origin.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configurable
//@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements ApplicationListener<ContextRefreshedEvent>{

//	@Value("${discovery.client.discovery-server}")
//	private String discoveryServer;
//	
//	@Value("${discovery.client.update-path}")
//	private String updatePath;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired(required=false)
	private AccessDecisionManager accessDecisionManager;
	
	@Value("${security.http.default-success-url:/main}")
	private String defaultSuccessUrl;
	
	@Value("${security.http.failure-url:/login?error}")
	private String failureUrl;
	
	@Value("${security.http.default-success-url.always-use:true}")
	private boolean alwaysUse;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(new CustomUserDetailsService());
        auth.authenticationProvider(authenticationProvider);
    }
    
    @Bean
    @ConditionalOnMissingBean(AuthenticationProvider.class)
    public AuthenticationProvider authenticationProvider(){
    	return new DefaultAuthenticationProvider();
    }
    
    
//    @Bean
//    @ConditionalOnMissingBean(AccessDecisionManager.class)
//    public AccessDecisionManager accessDecisionManager(){
//    	return new DefaultAccessDecisionManager();
//    }
    
//	public UserDetailsService getUserDetailsService() throws Exception {
//		Collection<UserDetails> users = new ArrayList<>();
//		List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();
//		grantedAuthoritys.add(new SimpleGrantedAuthority("USER"));
//		User user = new User("user","password",grantedAuthoritys);
//		users.add(user);
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(users);
//		return manager;
//	}
    
    @Override  
    public void configure(WebSecurity web) throws Exception {  
//        web.ignoring().antMatchers("/static/**");
    	web.ignoring().requestMatchers(new InternalRequestMatcher());
    }  
    
    @Override  
    protected void configure(HttpSecurity http) throws Exception {  
//    	http.csrf().disable()
//    	@formatter:off
    	http.authorizeRequests().anyRequest().authenticated()
//    			.accessDecisionManager(accessDecisionManager)
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl(defaultSuccessUrl,alwaysUse)
                .failureUrl(failureUrl)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))//因为启用了scrf，因此默认必须post提交才可以登出，本行设置是以GET方式也可以实现登出
//                .logoutUrl("/logout") //这行与上一行不能同时存在，否则以本行设置为准。
                .permitAll();
//    	@formatter:on
    	
//    	http.authorizeRequests().antMatchers("/**").access("ROLE_ANONYMOUS");
//    	http.requestMatcher(new InternalRequestMatcher());

//        http.authorizeRequests().antMatchers("/").permitAll();
        if(accessDecisionManager != null){
        	http.authorizeRequests().accessDecisionManager(accessDecisionManager);
        }
    }

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext context = event.getApplicationContext();
		FreeMarkerConfigurer bean = context.getBean(FreeMarkerConfigurer.class);
    	List<String> tldPaths = new ArrayList<>();
    	tldPaths.add("/META-INF/security.tld");
    	bean.getTaglibFactory().setClasspathTlds(tldPaths);
    	
	}
	
	@Bean
	public ErrorPageFilter errorPageFilter() {
	    return new ErrorPageFilter();
	}

	@Bean
	public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	    filterRegistrationBean.setFilter(filter);
	    filterRegistrationBean.setEnabled(false);
	    return filterRegistrationBean;
	}

}
