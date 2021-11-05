package application.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Comment {

    @Id @GeneratedValue
    private long commentId;

    @ManyToOne
    private BlogPost blogPost;

    @ManyToOne
    private BlogUser commenter;

    @ManyToOne
    private Comment preceding;
    private String commentText;
    private boolean isVisible = true;
    private LocalDateTime timestamp;

    //@OneToMany(mappedBy = "preceding", fetch = FetchType.EAGER)
    //private List<Comment> replies;


}
