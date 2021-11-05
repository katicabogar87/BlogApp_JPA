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
public class BlogPost {
    @Id @GeneratedValue
    private long blogPostId;
    private String blogPostTitle;
    private String text;
    @Enumerated(EnumType.STRING)
    private BlogPostStatus blogPostStatus;
    private LocalDateTime pubTime;
    @Transient
    private long reads;
    @ManyToOne
    private  Blog blog;

    @OneToMany(mappedBy = "blogPost")
    private List<Comment> comments;


    public void incrementReads (){
        reads++;
    }
}
