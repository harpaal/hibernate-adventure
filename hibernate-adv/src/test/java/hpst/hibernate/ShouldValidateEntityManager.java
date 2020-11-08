package hpst.hibernate;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class ShouldValidateEntityManager {
	
	@Autowired
	TestEntityManager entityManager;


	@Test
	void test() {
		assertNotNull(entityManager);
	}

	@Test
	
	void InsertPost() {
		PostDetails postDetail =new PostDetails();
		postDetail.setCreatedBy("Harpal  Singh");
		postDetail.setCreatedOn(new Date());
		
		Post post  = new Post();
		post.setId(1l);
		post.setDetails(postDetail);
		post.setTitle("My First Post");
		post=entityManager.persistAndFlush(post);
		assertNotNull(post);
	}

	
}
