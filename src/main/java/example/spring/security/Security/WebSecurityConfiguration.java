package example.spring.security.Security;

import example.spring.security.Constants;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

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

//    @Override
//    @SneakyThrows
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/resources/static/**");
//    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
