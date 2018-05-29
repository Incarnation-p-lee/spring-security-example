package example.spring.security.Security;

import example.spring.security.Constants;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebUserDetailsService userService;

    @Autowired
    private WebAuthenticationProvider provider;

    /**
     * Overriding default blocker to protect http request
     * @param http
     */
    @Override
    @SneakyThrows
    protected void configure(@NonNull HttpSecurity http) {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers(Constants.PATH_LOGIN).permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/vendor/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(Constants.PATH_LOGIN).defaultSuccessUrl(Constants.PATH_INDEX).permitAll()
                .and()
                .logout().permitAll();

        http.logout().logoutSuccessUrl(Constants.PATH_LOGIN);
    }

    /**
     * Overriding default filter chain
     * @param web
     */
    @Override
    @SneakyThrows
    public void configure(WebSecurity web) {
        super.configure(web);
    }

    /**
     * Overriding default user-details service
     * @param auth
     */
    @Override
    @SneakyThrows
    public void configure(AuthenticationManagerBuilder auth) {
        auth.userDetailsService(this.userService);
        auth.authenticationProvider(this.provider);
    }
}
