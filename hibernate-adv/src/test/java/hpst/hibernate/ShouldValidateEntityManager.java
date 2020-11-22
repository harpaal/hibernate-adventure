package hpst.hibernate;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import com.vladmihalcea.sql.SQLStatementCountValidator;


@DataJpaTest
@TestMethodOrder(Alphanumeric.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@Commit
class ShouldValidateEntityManager {
	
	@Autowired
	TestEntityManager entityManager;


	@Test()
	void test() {
		assertNotNull(entityManager);
	}

	@Test
	void insertPost() {
		PostDetails postDetail =new PostDetails();
		postDetail.setCreatedBy("Harpal  Singh");
		postDetail.setCreatedOn(new Date());
		
		Post post  = new Post();
		post.setId(1l);
		post.setDetails(postDetail);
		post.setTitle("My First Post");
		post=entityManager.persistAndFlush(post);
		

		Post post2  = new Post();
		post2.setId(2l);
		post2.setDetails(null);
		post2.setTitle("My Second Post");
		post2=entityManager.persistAndFlush(post2);
		
	
		assertAll(()->assertNotNull(entityManager.find(Post.class, 1l)),
				()-> entityManager.find(Post.class, 2l));
		}

	@Test
	void insertPostComments() {
		
		PostComment postComment=new PostComment();
		postComment.setId(1l);
		postComment.setPost(entityManager.find(Post.class, 1l));
		postComment.setReview("This is awesome post!!");
		assertNotNull(entityManager.persistAndFlush(postComment));
	}
	
	@Test
	void  sqlStatementCount() {
		SQLStatementCountValidator.reset();
	entityManager.getEntityManager()
				.createQuery("select p from PostComment p", PostComment.class).getResultList();
	SQLStatementCountValidator.assertSelectCount(1);
	}
	
}
