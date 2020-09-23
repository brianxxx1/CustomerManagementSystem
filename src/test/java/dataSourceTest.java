import dao.IUserDao;
import domain.user;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.Impl.UserServiceImpl;
import service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class dataSourceTest {
    private SqlSession sqlSession;
    IUserDao mapper;
    @Before
    public void before(){
        try {
            BasicConfigurator.configure ();
            InputStream stream = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(stream);
            sqlSession = factory.openSession();

        } catch (IOException e) {
        }
    }
    @Test
    public void loginCheckTest(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        Boolean aBoolean = mapper.loginCheck("xuwenshuo123", "jicococ0319");
        System.out.println(aBoolean);
    }
    @Test
    public void userInsertTest(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        user user = new user("wanglaoer","男",25,"大连","12345123214","xhfoqr3@jfq.com");
        Boolean aBoolean = mapper.userInsert(user);
        System.out.println(aBoolean);
    }
    @Test
    public void findAllTest(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<user> all = mapper.findAll();
        for (user user : all) {
            System.out.println(user);
        }
    }
    @Test
    public void test1() throws IOException {
        UserService userService = new UserServiceImpl();
        Boolean aBoolean = userService.userDelete(12);
        System.out.println(aBoolean);
    }

    @Test
    public void deleteUserTest(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        Boolean aBoolean = mapper.userDelete(11);
        System.out.println(aBoolean);
    }

    @Test
    public void findByIdTest(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        user byId = mapper.findById(2);
        System.out.println(byId);
    }
    @Test
    public void userUpdateTest(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        user user = new user("帅哥","男",15,"广东","15123213","xhqwr@qq.com");
        user.setId(1);
        Boolean aBoolean = mapper.userUpdate(user);
        System.out.println(aBoolean);
    }
    @Test
    public void findTotalCountTest(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        Integer totalCount = mapper.findTotalCount();
        System.out.println(totalCount);
    }
    @Test
    public void findByPageTest(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<user> byPage = mapper.findByPage(0, 5);
        System.out.println(byPage);
    }
    @Test
    public void blurFindTest(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<user> users = mapper.blurFind("%2%", "%广%", "%1%",0,5);
        System.out.println(users);
    }
    @Test
    public void blurTotalCountFInd(){
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        Integer integer = mapper.blurTotalCountFind("%Wenshuo%", null, null);
        System.out.println(integer);
    }
    @After
    public void after(){
        sqlSession.commit();
    }
}
