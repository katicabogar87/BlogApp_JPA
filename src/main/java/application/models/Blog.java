package application.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter @Setter
@NoArgsConstructor
@Entity
public class Blog {
    @Id @GeneratedValue
    private long blogId;
    private String blogTitle;

    @ManyToOne
    private BlogUser blogOwner;

    @ManyToOne
    private BlogTemplate blogTemplate;
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "blog")
    private List<BlogPost> blogPosts;

}


