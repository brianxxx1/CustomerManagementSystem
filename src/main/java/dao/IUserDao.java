package dao;

import domain.pageBean;
import domain.user;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserDao {
    List<user> findAll();
    Boolean loginCheck(String username, String password);
    Boolean userInsert(user user);
    Boolean userDelete(Integer id);
    user findById(Integer id);
    Boolean userUpdate(user user);
    Integer findTotalCount();
    List<user> findByPage(Integer start, Integer rows);
    List<user> blurFind(@Param("name") String name,@Param("address") String address, @Param("email")String email, @Param("start") Integer start, @Param("rows")Integer rows);
    Integer blurTotalCountFind(String name, String address, String email);
}
