package com.example.demo.security.services;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.mapper.RoleDAO;
import com.example.demo.mapper.UserDAO;
import com.example.demo.mapper.UserRoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserDAO userDAO;
  @Autowired
  UserRoleDAO userRoleDAO;
  @Autowired
  RoleDAO roleDAO;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDAO.getUserByUserName(username)
        .orElseThrow(() -> new InternalAuthenticationServiceException("User Not Found with username: " + username));
    List<UserRole> roleList = userRoleDAO.getUserRoleByUserId(user.getUserId());
    List<Role> roles = new ArrayList<>();
    if (roleList.size() > 0) {
      List<Long> roleIds = roleList.stream().map((UserRole::getRoleId)).toList();
      roles = roleDAO.findByIdList(roleIds);
    }
    return UserDetailsImpl.build(user,roles);
  }

  @Transactional
  public UserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException {
    User user = userDAO.getUserById(userId)
            .orElseThrow(() -> new InternalAuthenticationServiceException("User Not Found with userId: " + userId));
    List<UserRole> roleList = userRoleDAO.getUserRoleByUserId(userId);
    List<Role> roles = new ArrayList<>();
    if (roleList.size() > 0) {
      List<Long> roleIds = roleList.stream().map((UserRole::getRoleId)).toList();
      roles = roleDAO.findByIdList(roleIds);
    }
    return UserDetailsImpl.build(user,roles);
  }

}
