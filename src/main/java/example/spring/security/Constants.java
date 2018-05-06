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
}
