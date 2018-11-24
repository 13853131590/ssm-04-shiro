package com.hwua.ssm.realm;

import com.hwua.ssm.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomerRealm extends AuthorizingRealm {
    // 重写setName方法，给我的自定义realm起个名字,名字一般是类名首字母小写。
    //重写setName方法的作用，就是为了范围AuthenticationInfo的时候，需要自定义realm的名字
    @Override
    public void setName(String name) {
        super.setName("customerRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //authenticationToken对象就是在subject对象调用login方法的时候传入的那个令牌
        //我们传入的是他的子类UsernamePasswordToken，其中的Principal就是用户名，然后Credential就是密码
        String username=(String) authenticationToken.getPrincipal();
        //拿着输入的用户名，去数据库里找对应的记录，如果找不到，返回一个null，则说明用户名不存在
        //下面假装从数据库中获取数据，在数据库中获取的数据，有两种情况1、null，表示用户名不存在
        //2、返回一个user对象，这个对象带有从数据库那里存储的密码。
        //User user = userService.queryUserByName(username);
        User user=null;
        user = new User();
        //假设在数据库中存储的密码是经过加盐qwerty，并且64次加密之后的值
        //123456-->qwerty-->64=b8f2dfd636853396dc5dd27d46649779
        user.setUsername("zhangsan").setPassword("123456");
        //当从数据库中查询的user为null的时候，说明用户不存在
        //如果user为null，则可以直接return
        if(user==null){
            return null;
        }else{
            //当从数据库中查询的user不为null的时候，说明用户存在，需要返回一个AuthenticationInfo对象
            //我们返回的是AuthenticationInfo对象的子类：SimpleAuthenticationInfo
            //在SimpleAuthenticationInfo这里面，输入你查询到的用户名和密码
            SimpleAuthenticationInfo info=
                    new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),this.getName());
            //按照正常的业务逻辑，下一步应该是拿着输入的密码（即参数中authenticationToken对象中的Credential）
            //和查询出来的user中的密码进行对比。但是这个工作不用我们自己做，封装成AuthenticationInfo对象
            //交给shiro框架去做就可以了。
            return info;
        }


    }
}
