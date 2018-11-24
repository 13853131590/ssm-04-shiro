package com.hwua.ssm.dao;

import com.hwua.ssm.entity.Role;
import com.hwua.ssm.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
//按名字查
    public User queryUserByName(String name);

    public User queryUserByNameAndPwd(String username, String password);

    public  int updatePwd(String username, String newpwd);
// 查询全部
    public List<Map<String,Object>> queryUser(User user);
//增加
    public int addUser(User user);
//修改
    public int updateUser(User user);
    //按名字
    public Role queryuserByName(String username);

    public int deleteRoleByUserId(int userid);

    public int saveUserAndRole(List<Map<String, Object>> params);

    public List<Map<String,Object>> queryMenuByUserId(int userid);

    public List<String> queryUrlByUserId(int userid);

    public List<String> queryCodeByUserId(int userid);
}
