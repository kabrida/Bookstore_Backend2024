package syksy2024.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import syksy2024.bookstore.model.AppUser;
import syksy2024.bookstore.model.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    
    @Autowired
    AppUserRepository AUrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curUser = AUrepo.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curUser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curUser.getRole()));
        return user;
    }

}
