
package com.cmc.training.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cmc.training.entity.RolePermission;
import com.cmc.training.entity.User;
import com.cmc.training.repository.UserRepository;
import com.cmc.training.service.UserService;

/**
 * 
 * @author 
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User account = null;
		account = userRepository.findByUserName(username);

		if (account == null) {
			throw new UsernameNotFoundException("User " + username + " not found.");
		}
		
		User user = findByUserName(account.getUserName());
		
		if(user == null) {
			throw new UsernameNotFoundException("User " + username + " not found.");
		}
		Collection<GrantedAuthority> grantedAuthorities = getAuthorities(user);
		
		org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
				account.getUserName(), buildPasswordWithSalt(account.getHashedPassword(), account.getSalt()), true, true,
				true, true, grantedAuthorities);

		return userDetails;
	}

	/**
	 * Cộng chuỗi salt vào cùng với pwd truyền sang
	 *
	 * @param pwd
	 * @param salt
	 * @return
	 */
	private String buildPasswordWithSalt(String pwd, String salt) {
		return pwd + " " + salt;
	}

	private final Collection<GrantedAuthority> getAuthorities(User user) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<RolePermission> lstPermission = new ArrayList<>();
		    //user.getGroup().getRole().getRolePermissions();
		if (lstPermission != null)
			for (RolePermission item : lstPermission) {
				if (item.getEnable() == 1) {
					authorities.add(new SimpleGrantedAuthority(item.getPermission().getPermissionName()));
				}
			}
		return authorities;
	}

	/**
	 * Get user info from training
	 * 
	 * @param userName
	 * @return
	 */
	private User findByUserName(String userName) {
		User user = userService.findByUserName(userName);
		return user;
	}

}
