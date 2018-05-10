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

    public final static String USERNAME_0 = "username-0";
    public final static String USERNAME_1 = "username-1";

    public final static String ROLE_ADMIN = "ROLE_ADMIN";
    public final static String ROLE_DBA = "ROLE_DBA";
}
