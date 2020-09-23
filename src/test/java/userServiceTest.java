import domain.pageBean;
import domain.user;
import org.junit.Test;
import service.Impl.UserServiceImpl;
import service.UserService;

import java.io.IOException;

public class userServiceTest {
    @Test
    public  void findByPageTest() throws IOException {
        UserService userService = new UserServiceImpl();
        pageBean<user> byPage = userService.findByPage(1, 5);
        System.out.println(byPage);
    }
    @Test
    public void blurFindTest() throws IOException {
        UserService userService = new UserServiceImpl();
        pageBean<user> pageBean = userService.blurFind(1, 5, "%wanglaoer%", null, null);
        System.out.println(pageBean);
    }
}
