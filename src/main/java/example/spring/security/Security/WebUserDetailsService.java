package example.spring.security.Security;

import example.spring.security.Constants;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsService - Used for obtain user information.
 * UserDetails        - Store User information.
 *
 * security.core.userdetails.User is the default implementation for UserDetails.
 */

@Service
public class WebUserDetailsService implements UserDetailsService {

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(@NonNull String username) {
        final List<GrantedAuthority> roles = new ArrayList<>();

        if (Constants.USERNAME_0.equalsIgnoreCase(username)) {
            roles.add(new SimpleGrantedAuthority(Constants.ROLE_ADMIN));
        } else if (Constants.USERNAME_1.equalsIgnoreCase(username)) {
            roles.add(new SimpleGrantedAuthority(Constants.ROLE_ADMIN));
            roles.add(new SimpleGrantedAuthority(Constants.ROLE_DBA));
        } else {
            return null;
        }

        return new User(username, "password", roles);
    }
}
