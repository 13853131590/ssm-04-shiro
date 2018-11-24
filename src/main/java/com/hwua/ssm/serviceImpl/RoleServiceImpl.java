package com.hwua.ssm.serviceImpl;

import com.hwua.ssm.dao.RoleDao;
import com.hwua.ssm.entity.Role;
import com.hwua.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("/roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Map<String, Object>> queryRole(Role role) {
        return roleDao.queryRole(role);
    }

    @Override
    public int saveOrUpdateRole(Role role) {
        int result=0;
        if(role.getId()==null){
            Role rr=roleDao.queryRoleByName(role.getRolename());
            if(rr==null){
                result=roleDao.addRole(role);
            }else {
                result=2;
            }
        }else {
            Role rr=roleDao.queryRoleByName(role.getRolename());
            if(rr==null){
                result=roleDao.updateRole(role);
            }else if(rr.getId().intValue()==role.getId().intValue()){
                result=roleDao.updateRole(role);
            }else {
                result=2;
            }
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> queryAuthByRoleId(int roleid) {
        List<Map<String,Object>> validAuths=roleDao.queryValiAuth();
        List<Integer> auths=roleDao.queryAuthByRoleId(roleid);

        for(int i=0;i<validAuths.size();i++){
            int id=Integer.parseInt(validAuths.get(i).get("id").toString());
            if(auths.contains(id)){
                validAuths.get(i).put("checked",true);
            }
        }
        Map<String,Object> father=null;
        Map<String,Object> son=null;
        List<Map<String,Object>> children=null;
        for (int i=0;i<validAuths.size();i++){
            father=validAuths.get(i);
            children=new ArrayList<Map<String, Object>>();
            for (int j=0;j<validAuths.size();j++){
                son=validAuths.get(j);
                if(son.get("pid").toString().equals(father.get("id").toString())){
                    children.add(son);
                }
            }
            father.put("children",children);
        }
        List<Map<String,Object>> result=new ArrayList<Map<String, Object>>();
        result.add(validAuths.get(0));
        return result;
    }

    @Override
    @Transactional
    public int addRoleAndAuth(int roleid, List<Map<String, Object>> params) {
        int a=roleDao.delAuthByRoleId(roleid);
        int b=roleDao.addRoleAndAuth(params);
        return a+b;
    }

    @Override
    public List<Map<String, Object>> queryRoleByUserId(int userid) {
        List<Map<String,Object>> validRoles=roleDao.queryValidRole();
        List<Integer> roles=roleDao.queryRoleByUserId(userid);
        for (Map<String,Object> role:validRoles){
            int id=Integer.parseInt(role.get("id").toString());
            if(roles.contains(id)){
                role.put("checked",true);
            }
        }
        return validRoles;
    }
}
