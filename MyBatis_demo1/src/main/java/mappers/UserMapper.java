package mappers;

import myClass.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface UserMapper {
    int insertUser();

    int deleteUser();

    int updateUser();

    User getUserByID(int id);

    User getUserByIdAndName1(int id,String name);

    int insertByUser(User user);

    //使用@Param注解 并用map集合接收查询结果--以查询到的字段名为key，字段值为value
    Map<String,Object> getUserByIdAndName2(@Param("myId") int id, @Param("myName") String name);

    List<User> getAllUser();

    int getCount();   //查询表中记录数量

    @MapKey("id")      //查询多条记录,用map来接收--指定id为key,以记录对应的实体类对象为value
    Map<String,Object> getAllUserInMap(@Param("id1") int id1,@Param("id2") int id2,@Param("id3") int id3);

    @MapKey("id")
    Map<String,Object> getInMapByName(String name);

    int getAutoKey(User user);

}
