package example.spring.security.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.spring.security.Constants;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsService - Used for obtain user information.
 * UserDetails        - Store User information.
 *
 * security.core.userdetails.User is the default implementation for UserDetails.
 */

@Service
@NoArgsConstructor
public class WebUserDetailsService implements UserDetailsService {

    private static final String PASSWORD_JSON_FILE = "password.data";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @SneakyThrows
    private WebUserPassword findWebUserPassword(@NonNull String username) {
        final String path = this.getClass().getClassLoader().getResource(PASSWORD_JSON_FILE).getFile();
        @Cleanup final BufferedReader reader = new BufferedReader(new FileReader(path));

        String record;
        WebUserPassword userPassword;

        while ((record = reader.readLine()) != null) {
            userPassword = MAPPER.readValue(record, WebUserPassword.class);

            if (userPassword.getUsername().equals(username)) {
                return userPassword;
            }
        }

        return null;
    }

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(@NonNull String username) {
        final List<GrantedAuthority> roles = new ArrayList<>();
        final WebUserPassword userPassword = this.findWebUserPassword(username);

        if (userPassword == null) {
            return null;
        }

        roles.add(new SimpleGrantedAuthority(Constants.ROLE_ADMIN));

        return new User(username, userPassword.getPassword(), roles);
    }
}
