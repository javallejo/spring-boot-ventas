package com.cuasatar.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cuasatar.ventas.service.UsuarioDetallesServiceImpl;

//Indica que esta clase es de configuracion y necesita ser cargada durante el inicio del server
@Configuration

//Indica que esta clase sobreescribira la implmentacion de seguridad web
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

  String[] resources = new String[]{
          "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
  };
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
  	http
      .authorizeRequests()
      .antMatchers(resources).permitAll()  
      .antMatchers("/","/index").permitAll()
          .anyRequest().authenticated()
          .and()
      .formLogin()
          .loginPage("/login")
          .permitAll()
          .defaultSuccessUrl("/userForm")
          .failureUrl("/login?error=true")
          .usernameParameter("nombreusuario")
          .passwordParameter("contrasena")
          .and()
          .csrf().disable()
      .logout()
          .permitAll()
          .logoutSuccessUrl("/login?logout");
  }
  
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
      return bCryptPasswordEncoder;
  }
  
  @Autowired
  UsuarioDetallesServiceImpl userDetailsService;
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
  	//Especificar el encargado del login y encriptacion del password
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }
}
