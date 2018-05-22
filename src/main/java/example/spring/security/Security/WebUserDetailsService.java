package example.spring.security.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.spring.security.Constants;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
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

    private static final String PASSWORD_JSON_FILE = "userPassword.json";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private WebUserPassword userPassword;

    @Autowired
    private PasswordEncoder encoder;

    @SneakyThrows
    private WebUserPassword getWebUserPassword() {
        if (this.userPassword == null) {
            this.userPassword = MAPPER.readValue(new File(PASSWORD_JSON_FILE), WebUserPassword.class);
        }

        return this.userPassword;
    }

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(@NonNull String username) {
        final List<GrantedAuthority> roles = new ArrayList<>();
        final WebUserPassword webUserPassword = this.getWebUserPassword();
        final String password = webUserPassword.getMap().get(username);

        if (password == null) {
            return null;
        }

        roles.add(new SimpleGrantedAuthority(Constants.ROLE_ADMIN));

        return new User(username, password, roles);
    }
}
