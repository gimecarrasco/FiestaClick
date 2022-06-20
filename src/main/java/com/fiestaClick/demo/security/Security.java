
package com.fiestaClick.demo.security;

import com.fiestaClick.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


   @Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserService userService;

    @Autowired
    public void globalConfigure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());// para encriptar la contraseña
    }
       
    protected void configure(HttpSecurity http) throws Exception { //estamos especificando determinadas configuracione de security
        http
                .authorizeRequests()
                .antMatchers("/admin/*").hasRole("ADMINISTRADOR") // Puedo dar acceso a un controlador completo, con rol especifoc
                .antMatchers("/css/*", "/js/*", "/img/*",
                        "/**").permitAll()
                .and().
                formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/logincheck") //controlador que checkea user y password
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/index") //controlador que deriva a la pagina luego de un logueo exitoso
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index") // controlador que redirecciona al html cuando se desloguea de manera exitosa
                .permitAll().
                and().csrf().disable();
    }
}

