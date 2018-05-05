package mvc.service;

import mvc.domain.User;
import mvc.repository.UserRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author yasuaki
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
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
        userService.selectUser("root");
        verify(userRepository, times(1)).selectUser(anyString());
    }

    /**
     * 正常系の動作確認
     *
     */
    @Test
    public void testSelectUsers01() {
        userService.selectUsers();
        verify(userRepository, times(1)).selectUsers();
    }

    /**
     * 正常系の動作確認
     *
     */
    @Test
    public void testUpdateUser01() {

        User user = new User();

        userService.updateUser(user);
        verify(userRepository, times(1)).updateUser(user);
    }

    /**
     * 正常系の動作確認
     *
     */
    @Test
    public void testDeleteUser01() {

        User user = new User();

        userService.deleteUser(user);
        verify(userRepository, times(1)).deleteUser(user);
    }

}
