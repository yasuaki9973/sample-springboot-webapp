package mvc.controller;

import java.util.Arrays;
import java.util.List;
import mvc.domain.User;
import mvc.service.UserService;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.ui.ModelMap;

/**
 *
 * @author yasuaki
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private LoginHelper loginHelper;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(this.userController).build();
        when(this.loginHelper.getLoginUserName()).thenReturn("root");
    }

    @After
    public void tearDown() {
    }

    /**
     * 正常系の動作確認
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDisplayUsers01() throws Exception {

        String userName = "root";
        String encodedPassword = "sample-pass";
        String role = "root";

        User user = new User();
        user.setUserName(userName);
        user.setEncodedPassword(encodedPassword);
        user.setRole(role);

        List<User> users = Arrays.asList(user);

        when(this.userService.selectUser("root")).thenReturn(user);
        when(this.userService.selectUsers()).thenReturn(users);

        MvcResult result = mvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("editUser"))
                .andExpect(model().attributeExists("users"))
                .andReturn();

        ModelMap modelMap = result.getModelAndView().getModelMap();
        List<User> resultUsers = (List<User>) modelMap.get("users");

        assertThat(resultUsers.size(), is(1));

        User resultUser = resultUsers.get(0);

        assertThat(resultUser.getUserName(), is(userName));
        assertThat(resultUser.getEncodedPassword(), is(encodedPassword));
        assertThat(resultUser.getRole(), is(role));

    }

    /**
     * 正常系の動作確認
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDisplayProfile01() throws Exception {

        String userName = "root";
        String encodedPassword = "sample-pass";
        String role = "root";

        User user = new User();
        user.setUserName(userName);
        user.setEncodedPassword(encodedPassword);
        user.setRole(role);

        when(this.userService.selectUser("root")).thenReturn(user);

        MvcResult result = mvc.perform(get("/user/profile"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andReturn();

        ModelMap modelMap = result.getModelAndView().getModelMap();
        User resultUser = (User) modelMap.get("user");

        assertThat(resultUser.getUserName(), is(userName));
        assertThat(resultUser.getEncodedPassword(), is(encodedPassword));
        assertThat(resultUser.getRole(), is(role));

    }

    /**
     * 正常系の動作確認
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateUserRole01() throws Exception {

        mvc.perform(post("/user/update/role"))
                .andExpect(redirectedUrl("/user"))
                .andExpect(status().isFound())
                .andExpect(model().attributeExists("user"))
                .andReturn();

    }

    /**
     * 正常系の動作確認
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteUser01() throws Exception {

        mvc.perform(post("/user/delete"))
                .andExpect(redirectedUrl("/user"))
                .andExpect(status().isFound())
                .andExpect(model().attributeExists("user"))
                .andReturn();

    }

}
