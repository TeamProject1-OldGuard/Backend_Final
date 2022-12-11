package com.oldguard.common.config;

import com.oldguard.auth.support.JwtAuthenticationFilter;
import com.oldguard.auth.support.JwtTokenProvider;
import com.oldguard.auth.support.TokenAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                .exceptionHandling()
                //.authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(tokenAccessDeniedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/code","/test","/login","/api/v1/guest","/api/v1/member","/auth/**", "/oauth2/**","/login/oauth2/code/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), OAuth2AuthorizationRequestRedirectFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/configuration/**","/webjars/**","/swagger*","/swagger*/**","/v2/api-docs");
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
