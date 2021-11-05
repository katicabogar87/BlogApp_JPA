package testHelpers;

import application.models.BlogUser;
import application.models.Role;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;


@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {

        BlogUser blogUser = new BlogUser("user", "user", Role.REG_USER);
        BlogUser moderator = new BlogUser("moderator", "moderator", Role.REG_USER);
        BlogUser admin = new BlogUser("admin", "admin", Role.ADMIN);

        return new InMemoryUserDetailsManager(Arrays.asList(blogUser, moderator, admin));
    }
}
