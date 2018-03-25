package mvc.service.auth;

import mvc.domain.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 * @author yasuaki
 */
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public LoginUserDetails(User user) {
        super(user.getUserName(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = user;
    }

}
