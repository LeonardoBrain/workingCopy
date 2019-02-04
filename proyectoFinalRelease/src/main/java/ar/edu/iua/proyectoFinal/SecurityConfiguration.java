package ar.edu.iua.proyectoFinal;

import javax.sql.DataSource;

import ar.edu.iua.proyectoFinal.controller.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("persistenceUserDetailService")
    @Autowired
    private UserDetailsService userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Bean
    public PasswordEncoder pwdEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Value("${recursos.estaticos}")
    private String recursosEstaticos;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] resources = recursosEstaticos.split(",");

        http.authorizeRequests().antMatchers(resources).permitAll().anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/dologin").permitAll().anyRequest()
                .authenticated();

        http.formLogin().loginPage(Constants.URL_DENY).defaultSuccessUrl(Constants.URL_LOGINOK)
                .loginProcessingUrl("/dologin").permitAll().failureForwardUrl(Constants.URL_DENY);

        http.logout().logoutSuccessUrl(Constants.URL_LOGOUTOK).deleteCookies("SESSION", "rmiw3")
                .clearAuthentication(true);

        http.csrf().disable();

    }

    @Autowired
    private DataSource ds;

    public PersistentTokenRepository rmRepository() {
        JdbcTokenRepositoryImpl r = new JdbcTokenRepositoryImpl();
        r.setDataSource(ds);
        return r;
    }
}