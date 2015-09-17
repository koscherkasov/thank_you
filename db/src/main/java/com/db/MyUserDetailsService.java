package com.db;

import com.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

/**
 * Created by Konstantin on 03.09.2015.
 */
public class MyUserDetailsService implements UserDetailsService {

    private DaoUserInt userDao;

    public DaoUserInt getUserDao() {
        return userDao;
    }

    public void setUserDao(DaoUserInt userDao) {
        this.userDao = userDao;
    }

    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserDetails ud = new UserDetails() {
            public Collection<? extends GrantedAuthority> getAuthorities() {
                Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
                setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                return setAuths;
            }

            public String getPassword() {
                List<User> listUser= userDao.getListUser();
                for (User user: listUser) {
                    if (username.equals(user.getEmail())) return user.getHashPass();
                }
                return null;
            }

            public String getUsername() {
                return username;
            }

            public boolean isAccountNonExpired() {
                return true;
            }

            public boolean isAccountNonLocked() {
                return true;
            }

            public boolean isCredentialsNonExpired() {
                return true;
            }

            public boolean isEnabled() {
                return true;
            }
        };
        return ud;

    }
}