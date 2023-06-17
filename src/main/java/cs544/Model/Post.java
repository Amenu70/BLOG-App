package cs544.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Post {
    @Id
    private Integer id;
    @Lob
    private String content;
    @ManyToOne
    private User user;

}
