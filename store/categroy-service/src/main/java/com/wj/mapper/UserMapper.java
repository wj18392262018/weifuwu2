package com.wj.mapper;

import com.wj.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from tb_user where username=#{username}")
    public User getUser(String username);
    @Insert("insert into tb_user values(#{id},#{username},#{password},#{phone},#{created})")
    public Integer addUser(User user);
}
