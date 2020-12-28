package ImageHoster.model;

import javax.persistence.*;
import java.time.LocalDate;

//@Entity annotation specifies that the corresponding class is a JPA entity.
@Entity

//@Table annotation provides options to customize the mapping of the class to the database table.
/*Here the name of the table to be created in the database is explicitly mentioned as 'comments'.
 This will create the table named 'comments' in the database with the columns mapped to all the
 attributes in 'Comment' class.
 */
@Table (name = "comments")
public class Comment {

    //@Id annotation specifies that the corresponding attribute is a primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column annotation specifies that the attribute will be mapped to the column in the database.
    //Here the column name is explicitly mentioned as 'id'
    @Column(name = "id")
    private Integer id;

    // Text is a Postgres specific column type that allows you to save text based data that is longer than 256 characters.
    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    /*The 'comments' table is mapped to the 'users' table with Many:One mapping.
     One comment can have only one user (owner) but one user can make multiple comments.
     FetchType is EAGER.
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    //Below annotation indicates that the name of the column in 'comments' table referring the primary key in 'users' table will be 'user_id'
    @JoinColumn(name = "user_id")
    private User user;

    /*The 'comments' table is mapped to the 'images' table with Many:One mapping.
     One comment can be mapped to only one image but one image can make multiple comments.
     FetchType is EAGER.
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
