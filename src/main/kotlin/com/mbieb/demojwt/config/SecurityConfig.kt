package com.mbieb.demojwt.config

import com.mbieb.demojwt.filter.JwtRequestFilters
import com.mbieb.demojwt.service.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
  @Autowired
  private val myUserDetailService: MyUserDetailsService? = null

  @Autowired
  private val jwtRequestFilter: JwtRequestFilters? = null

  @Throws(Exception::class)
  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService<UserDetailsService>(myUserDetailService)
  }

  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    http.csrf().disable()
      .authorizeRequests().antMatchers("/token").permitAll()
      .anyRequest().authenticated()
      .and().sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .cors()

    http.addFilterBefore(jwtRequestFilter!!, UsernamePasswordAuthenticationFilter::class.java)
  }

  @Bean
  @Throws(Exception::class)
  override fun authenticationManagerBean(): AuthenticationManager {
    return super.authenticationManagerBean()
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return NoOpPasswordEncoder.getInstance()
  }

}
