package example.spring.security.Security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class WebUserPassword {

    private Map<String, String> map = new HashMap<>();
}
