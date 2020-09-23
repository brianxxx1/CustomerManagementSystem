package service.Impl;

import dao.IUserDao;
import domain.pageBean;
import domain.user;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public Boolean loginCheck(String username, String password) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        SqlSession sqlSession = factory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        final Boolean bool = mapper.loginCheck(username, password);
        sqlSession.commit();
        return bool;
    }

    @Override
    public Boolean userDelete(Integer id) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        SqlSession sqlSession = factory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        Boolean aBoolean = mapper.userDelete(id);
        sqlSession.commit();
        return aBoolean;
    }

    @Override
    public Boolean userInsert(user user) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        SqlSession sqlSession = factory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        Boolean bool = mapper.userInsert(user);
        sqlSession.commit();
        return bool;

    }

    @Override
    public Boolean userUpdate(user user) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        SqlSession sqlSession = factory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        Boolean aBoolean = mapper.userUpdate(user);
        sqlSession.commit();
        return aBoolean;
    }


    @Override
    public user findById(Integer id) throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        SqlSession sqlSession = factory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        user byId = mapper.findById(id);
        return byId;
    }

    @Override
    public List<user> findAll() throws IOException {
        InputStream stream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        SqlSession sqlSession = factory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<user> all = mapper.findAll();
        return all;
    }

    @Override
    public pageBean<user> findByPage(Integer currentPage, Integer rows) throws IOException {
        pageBean<user> pageBean = new pageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        Integer start = (currentPage-1)*rows;
        InputStream stream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        SqlSession sqlSession = factory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<user> byPage = mapper.findByPage(start, rows);
        pageBean.setList(byPage);
        Integer totalCount = mapper.findTotalCount();
        pageBean.setTotalCount(totalCount);
        Integer totalPage = totalCount/rows;
        if(totalCount%rows!=0){
            totalPage+=1;
        }
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public pageBean<user> blurFind(Integer currentPage,Integer rows, String name, String address, String email) throws IOException {
        pageBean<user> pageBean = new pageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
        Integer start = (currentPage-1)*rows;
        InputStream stream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        SqlSession sqlSession = factory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        List<user> users = mapper.blurFind(name, address, email, start, rows);
        pageBean.setList(users);
        Integer totalCount = mapper.blurTotalCountFind(name,address,email);
        pageBean.setTotalCount(totalCount);
        Integer totalPage = totalCount/rows;
        if(totalCount%rows!=0){
            totalPage+=1;
        }
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
}
