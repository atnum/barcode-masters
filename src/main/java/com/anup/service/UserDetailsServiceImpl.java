package com.anup.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anup.entity.Role;
import com.anup.repository.UserRepository;
//UserDetailsService is an interface and UserDetailsServiceImpl is it's implementation
@Service("UserDetailsServiceImpl")
@Transactional(readOnly=true)
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository ;  
	
	public static com.anup.entity.User user = null;
             
	
	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
	
        System.out.println("username is:-" + username);
             user = userRepository.findByUsername(username);
              
                System.out.println("Password From Detailservice is:-" + user.getPassword().toString());
                 System.out.println(user.toString());
                if (user==null){throw new UsernameNotFoundException("No such user: " + username);
              }else if(user.getRoles().isEmpty()){
                 throw new UsernameNotFoundException("User" + username + "has no authorities");
              }
                
               
                System.out.println("password is:-" + user.getPassword().toString());
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

		return buildUserForAuthentication(user, authorities);
		
	}

	// Converts com.ray.springsecurity.pojos.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.anup.entity.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
        
      

}