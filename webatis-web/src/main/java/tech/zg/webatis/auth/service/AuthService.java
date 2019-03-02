package tech.zg.webatis.auth.service;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tech.zg.webatis.entity.RoleEntity;
import tech.zg.webatis.entity.UserEntity;
import tech.zg.webatis.entity.UserRoleEntity;
import tech.zg.webatis.exception.BaseException;
import tech.zg.webatis.service.RoleService;
import tech.zg.webatis.service.UserRoleService;
import tech.zg.webatis.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张弓
 * @create 2019-02-23
 */
@Service("authService")
public class AuthService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<>();

        UserEntity userEntity = userService.findByName(name);
        List<UserRoleEntity> userRoleEntities = null;
        try {
            userRoleEntities = userRoleService.findByUserId(userEntity.getId());
        }catch (Exception e){
            return null;
        }
        if (CollectionUtils.isNotEmpty(userRoleEntities)) {
            for (UserRoleEntity userRole : userRoleEntities) {
                try {
                    RoleEntity role = roleService.get(userRole.getRoleId());
                    authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
                } catch (BaseException e) {
                    e.printStackTrace();
                }
            }
        }

        return new User(userEntity.getUserName(), userEntity.getPassword(), authorities);
    }
}
