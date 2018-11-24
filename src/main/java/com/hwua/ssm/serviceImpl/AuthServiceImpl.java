package com.hwua.ssm.serviceImpl;

import com.hwua.ssm.dao.AuthDao;
import com.hwua.ssm.entity.Auth;
import com.hwua.ssm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthDao authDao;

    @Override
    public List<Map<String, Object>> queryAllAuth() {
        List<Map<String,Object>> auths=authDao.queryAllAuth();
        auths.get(0).remove("_parentId");
        return auths;
    }

    @Override
    public int saveOrUpdateAuth(Auth auth) {
        int result=0;
        if(auth.getId()==null){
            Auth au=authDao.queryAuthByName(auth.getAuthname());
            if (au==null) {
                result=authDao.addAuth(auth);
            }else {
                result=2;
            }
        }else {
            Auth au=authDao.queryAuthByName(auth.getAuthname());
            if(au==null){
               result= authDao.updateAuth(auth);
            }else {
                if(au.getId().intValue()==auth.getId().intValue()){
                    result=authDao.updateAuth(auth);
                }else {
                    result=2;
                }
            }
        }
        return result;
    }
}
