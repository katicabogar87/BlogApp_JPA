package application.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter @Setter
@NoArgsConstructor
@Entity
public class BlogTemplate {

    @Id @GeneratedValue
    private long templateId;
    private String templateName;
    private String fontColor;
    @Enumerated(EnumType.STRING)
    private Category category;


}
