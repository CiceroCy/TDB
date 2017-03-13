package com.nttdata.tdb.web.core.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nttdata.tdb.domain.user.Role;
import com.nttdata.tdb.domain.user.User;
import com.nttdata.tdb.services.user.UserService;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOG = Logger.getLogger(UserDetailsServiceImpl.class.getName());

	@Autowired
	UserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String idUser) throws UsernameNotFoundException, DataAccessException {

		LOG.log(Level.INFO, "Executing UserDetailsServiceImpl - loadUserByUsername method");

		User user = userService.findUserByMatriculation(idUser);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		checkGrantAuthorities(user, authorities);
		UserDetails userDetails = validateUser(authorities, user);

			
		
		LOG.log(Level.INFO, "Finishing UserDetailsServiceImpl - loadUserByUsername method");

		return userDetails;
	}

	private void checkGrantAuthorities(User user,
			List<GrantedAuthority> listGrantAuthority) {
		if (user != null && user.getRoles() != null) {
			for (Role userRole : user.getRoles()) {
				final String PREFIX = "ROLE_";
				String role = PREFIX + userRole.getDescription();
				listGrantAuthority.add(new SimpleGrantedAuthority(role));
			}
		}
	}

	private UserDetails validateUser(List<GrantedAuthority> listGrantAuthority, User user) {

		CustomUser userDetails = null;

		if (user != null) {
			boolean accountNonLocked = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			
			userDetails = new CustomUser(user.getUsername(), user.getPassword(), user.getId(),
					user.getActive(), accountNonExpired,
			        credentialsNonExpired, accountNonLocked, listGrantAuthority);
		}
		
		return userDetails;
	}

}
