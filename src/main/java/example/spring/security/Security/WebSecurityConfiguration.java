package example.spring.security.Security;

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
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll() // allow everyone access this path
                .and()
                .csrf().disable();
    }

//    @Override
//    @SneakyThrows
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/resources/static/**");
//    }
}
