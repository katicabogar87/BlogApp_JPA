package application.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class BlogUser implements UserDetails {

    @Id @GeneratedValue
    private long userId;

    @Column(unique = true)
    private String loginName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role = Role.REG_USER;

    @CreationTimestamp
    private LocalDateTime regTime;
    private boolean isSuspended;

   /* @OneToMany(mappedBy = "blogOwner")
    private List<Blog> blogs;*/

   /* @OneToMany(mappedBy = "commenter")
    private List<Comment> comments;*/


    public BlogUser(String loginName, String password, Role role) {
        this.loginName = loginName;
        this.password = password;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return role.getAuths();
    }

    @Override
    public String getUsername() {
        return getLoginName();
    }



    @Override
    public boolean isAccountNonExpired() {
        return !isSuspended;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isSuspended;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isSuspended;
    }

    @Override
    public boolean isEnabled() {
        return !isSuspended;
    }
}
