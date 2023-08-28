package com.techinnovation.arrangingseats.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.techinnovation.arrangingseats.model.User;
import com.techinnovation.arrangingseats.model.UserRole;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails {
  private static final long serialVersionUID = 1L;

  private String loginId;
  private String username;
  private String password;
  private String department;
  private String position;
  private String project;

  // private Collection<? extends GrantedAuthority> authorities;

  public CustomUserDetails(String loginId, String username, String password, String department, String position, String project
  // Collection<? extends GrantedAuthority> authorities
  ) {
    this.loginId = loginId;
    this.username = loginId;
    this.password = password;
    this.department = department;
    this.position = position;
    this.project = project;
    // this.authorities = authorities;
  }

  public static CustomUserDetails build(User user) {
    // List<GrantedAuthority> authorities = user.getRoles().stream()
    // .map(role -> new SimpleGrantedAuthority(role.getName().name()))
    // .collect(Collectors.toList());

    return new CustomUserDetails(
        user.getLoginId(),
        user.getUsername(),
        user.getPassword(),
        user.getDepartment(),
        user.getProject(),
        user.getProject());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<UserRole>(Arrays.asList(UserRole.ROLE_CLIENT));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
