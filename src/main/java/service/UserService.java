package service;

import domain.pageBean;
import domain.user;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<user> findAll() throws IOException;
    Boolean loginCheck(String username, String password) throws IOException;
    Boolean userInsert(user user) throws IOException;
    Boolean userDelete(Integer id) throws IOException;
    user findById(Integer id) throws IOException;
    Boolean userUpdate(user user) throws IOException;
    pageBean<user> findByPage(Integer currentPage, Integer rows) throws IOException;
    pageBean<user> blurFind(Integer currentPage,Integer rows, String name, String address, String email) throws IOException;
}
