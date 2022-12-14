package com.findev.omo.config;

import com.findev.omo.common.security.EntryPointUnauthorizedHandler;
import com.findev.omo.common.security.Jwt;
import com.findev.omo.common.security.JwtAccessDeniedHandler;

import com.findev.omo.common.security.JwtAuthenticationTokenFilter;
import com.findev.omo.model.user.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

  private final Jwt jwt;

  private final JwtTokenConfigure jwtTokenConfigure;

  private final JwtAccessDeniedHandler accessDeniedHandler;

  private final EntryPointUnauthorizedHandler unauthorizedHandler;

  public WebSecurityConfigure(Jwt jwt, JwtTokenConfigure jwtTokenConfigure, JwtAccessDeniedHandler accessDeniedHandler, EntryPointUnauthorizedHandler unauthorizedHandler) {
    this.jwt = jwt;
    this.jwtTokenConfigure = jwtTokenConfigure;
    this.accessDeniedHandler = accessDeniedHandler;
    this.unauthorizedHandler = unauthorizedHandler;
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/swagger-resources", "/h2-console/**");
  }

  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
    return new JwtAuthenticationTokenFilter(jwtTokenConfigure.getHeader(), jwt);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AccessDecisionManager accessDecisionManager() {
    List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
    decisionVoters.add(new WebExpressionVoter());
    // voter ????????? connectionBasedVoter ??? ?????????
//    decisionVoters.add(connectionBasedVoter());
    // ?????? voter ???????????? ?????????
    return new UnanimousBased(decisionVoters);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
      .disable()
      .headers()
      .disable()
      .exceptionHandling()
      .accessDeniedHandler(accessDeniedHandler)
      .authenticationEntryPoint(unauthorizedHandler)
      .and()
      .sessionManagement()
      // JWT ????????? ??????????????? ?????????(STATELESS) ?????? ??????
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeRequests()
//      .antMatchers("/api/auth").permitAll()
//      .antMatchers("/api/user/join").permitAll()
//      .antMatchers("/api/user/exists").permitAll()
      .antMatchers("/api/**").permitAll()
      .antMatchers("/blockedapi/**").hasRole(Role.USER.name())
      .accessDecisionManager(accessDecisionManager())
      .anyRequest().permitAll()
      .and()
      // JWT ????????? ??????????????? form ????????? ???????????????
      .formLogin()
      .disable();
    http
      // ?????? ?????? ??????
      // UsernamePasswordAuthenticationFilter ?????? jwtAuthenticationTokenFilter ??? ????????????.
      .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}
