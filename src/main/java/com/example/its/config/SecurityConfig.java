package com.example.its.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    //ログインは認証不要、ログインページを指定する
    public void configure(HttpSecurity http) throws Exception{

        //h2-console を対象外にする
        http
        	.authorizeRequests().antMatchers("/h2-console/**").permitAll()
        	.and()
        	.csrf().ignoringAntMatchers("/h2-console/**")
        	.and()
        	.headers().frameOptions().disable();

    	http
                .authorizeRequests()
//                アドミンユーザーのみ以下のパスwebリソースにアクセス可能とする
                .mvcMatchers("/users/**").hasAuthority("ADMIN")
//                認証不要（ログインページは誰でもログイン可能）
                .mvcMatchers("/login/**").permitAll()
//                それ以外は認証が必要
                .anyRequest().authenticated()
                .and()
                //ログインページの指定
                .formLogin()
//                .usernameParameter("aaaa") loginForm のパラメータ名を設定可能
                .loginPage("/login");
        
        
    }
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	// 実装したuserDetailsService を使用するため、設定
    	auth.userDetailsService(userDetailsService)
    		.passwordEncoder(passwordEncoder);
    }
}
