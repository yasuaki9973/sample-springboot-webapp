package mvc.repository;

import java.util.List;
import mvc.domain.User;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author yasuaki
 */
@RunWith(SpringRunner.class)
@MybatisTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * 正常系の動作確認
     *
     */
    @Test
    public void testSelectUser01() {

        User result = userRepository.selectUser("root");
        assertThat(result.getRole(), is("admin"));
    }

    /**
     * 正常系の動作確認
     *
     */
    @Test
    public void testSelectUsers01() {

        List<User> results = userRepository.selectUsers();

        // 1件以上取得出来ること
        assertThat(results.size(), is(greaterThanOrEqualTo(1)));

        User user = results.get(0);

        // 期待するカラムが取得出来ていること
        assertNotNull(user.getUserName());
        assertNotNull(user.getRole());

    }

    /**
     * 正常系の動作確認
     *
     */
    @Test
    public void testUpdateUser01() {

        User before = userRepository.selectUser("root");

        User user = new User();
        user.setUserName("root");
        user.setRole("normal");

        userRepository.updateUser(user);

        User after = userRepository.selectUser("root");

        assertThat(after.getRole(), not(before.getRole()));
        assertThat(after.getRole(), is("normal"));
    }

    /**
     * 正常系の動作確認
     *
     */
    @Test
    public void testDeleteUser01() {

        User before = userRepository.selectUser("root");

        assertNotNull(before);

        User user = new User();
        user.setUserName("root");

        userRepository.deleteUser(user);

        User after = userRepository.selectUser("root");

        assertNull(after);
    }

}
