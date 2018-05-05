package example.spring.security.Security;

import example.spring.security.Constants;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    @SneakyThrows
    protected void configure(@NonNull HttpSecurity http) {
        http.authorizeRequests()
                .antMatchers(Constants.PATH_LOGIN).permitAll()
                .antMatchers("/resources/**").permitAll()
                .and()
                // Set login Page and allow everyone access this path
                .formLogin().loginPage(Constants.PATH_LOGIN).permitAll()
                .and()
                .csrf().disable();
    }

//    @Override
//    @SneakyThrows
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/resources/static/**");
//    }
}
