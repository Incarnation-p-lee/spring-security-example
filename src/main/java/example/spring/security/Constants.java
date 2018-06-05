package example.spring.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public final static String PATH_LOGIN = "/login";
    public final static String PAGE_LOGIN = "login";

    public final static String PATH_ROOT = "/";
    public final static String PAGE_ROOT = "index";

    public final static String PATH_INDEX = "/index";
    public final static String PAGE_INDEX = "index";

    public final static String PATH_HOME = "/home";
    public final static String PAGE_HOME = "home";

    public final static String ROLE_ADMIN = "ROLE_ADMIN";
}

/**
 * UserDetailsService: obtain user identify information
 * UserDetails:        save obtained user information
 *                     default implementation -> org.springframework.security.core.userdetails.User
 *
 * AuthenticationManager covers all auth process, it defines many AuthenticationProvider. If no-related object
 * specific by provider, it will use DaoAuthenticationProvider.
 * The provider need UserDetailsService to obtain UserDetails.
 *
 * Authentication: the interface for hold user information.
 * User defined filter: obtain authentication information from user, like username, password.
 *                      then do filter to verify, fill SecurityContextHolder Authentication field.
 *
 * AccessDecisionManager: for manager resources specified. The FilterSecurityInterceptor will invoke that MANAGER.
 */
