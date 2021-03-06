package hpst.hibernate;

import javax.persistence.*;

/**
 * @author Vlad Mihalcea
 */
@Entity
@Table(name = "post_comment")
public class PostComment {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    private String review;

    public Long getId() {
        return id;
    }

    public PostComment setId(Long id) {
        this.id = id;
        return this;
    }

    public Post getPost() {
        return post;
    }

    public PostComment setPost(Post post) {
        this.post = post;
        return this;
    }

    public String getReview() {
        return review;
    }

    public PostComment setReview(String review) {
        this.review = review;
        return this;
    }
}
