package application.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public enum Role {

    REG_USER (UserAuthority.CREATE_BLOG),
    MODERATOR(UserAuthority.CREATE_BLOG, UserAuthority.DELETE),
    ADMIN (UserAuthority.CREATE_BLOG, UserAuthority.MANAGE_USERS, UserAuthority.DELETE);

    private final UserAuthority[] AUTHS;

    Role(UserAuthority... auths) {
        AUTHS = auths;
    }

    public List<SimpleGrantedAuthority> getAuths() {
        List<SimpleGrantedAuthority> auths = new ArrayList<>();

        for (UserAuthority auth : AUTHS) {
            auths.add(new SimpleGrantedAuthority(auth.toString()));
        }

        return auths;
    }

}