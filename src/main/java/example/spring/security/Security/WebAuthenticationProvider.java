package example.spring.security.Security;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService detailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @SneakyThrows
    public Authentication authenticate(@NonNull Authentication auth) {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, auth, "should be auth type");

        final String username = auth.getName();
        final String password = auth.getCredentials().toString();
        final List<SimpleGrantedAuthority> roles = new ArrayList<>();
        final UserDetails details = this.detailsService.loadUserByUsername(username);

        if (details == null) {
            throw new UsernameNotFoundException("Invalid username/password");
        }

        final UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(username, password, roles);
        token.setDetails(details);

        return token;
    }

    @Override
    public boolean supports(@NonNull Class<?> auth) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(auth);
    }
}
