package com.hwua.ssm.dao;

import com.hwua.ssm.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleDao {

    public List<Map<String,Object>> queryRole(Role role);

    public int addRole(Role role);

    public int updateRole(Role role);

    public Role queryRoleByName(String rolename);

    public List<Map<String,Object>> queryValiAuth();

    public List<Integer> queryAuthByRoleId(int roleid);

    public int delAuthByRoleId(int id);

    public int addRoleAndAuth(List<Map<String, Object>> parmas);

    public List<Map<String,Object>> queryValidRole();

    public List<Integer> queryRoleByUserId(int userid);
}
