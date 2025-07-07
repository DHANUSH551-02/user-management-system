package com.cg.UserManagementSpringSecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.UserManagementSpringSecurity.entity.User;
import com.cg.UserManagementSpringSecurity.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=repo.findByUsername(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("User not found");
		}
			return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),AuthorityUtils.createAuthorityList(user.getRole()));
		
	}
	
	public void saveUser(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
	}
	public List<User>getAllUsers(){
		return repo.findAll();
		
	}
	
	public void deleteUser(Long id) {
		repo.deleteById(id);
	}
	
	
	
}