package com.nttdata.tdb.web.core.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * Class custom user for Authentication
 *
 * @author jean.lorenzini
 *
 */
public class CustomUser implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = 188805201634885301L;
	private String password;
	private final String username;
	private final String idUser;
	private final Set<GrantedAuthority> authorities;
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;

	/**
	 * @param username
	 * @param password
	 * @param authorities
	 * @param role
	 */
	public CustomUser(String username, String password,
	        Collection<? extends GrantedAuthority> authorities, String idUser) {
		this(username, password, idUser, true, true, true, true, authorities);
	}

	/**
	 * @param username
	 * @param password
	 * @param role
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public CustomUser(String username, String password, String idUser, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
	        boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {

		if (((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException(
			        "Cannot pass null or empty values to constructor");
		}

		this.username = username;
		this.password = password;
		this.idUser = idUser;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.authorities = Collections
		        .unmodifiableSet(sortAuthorities(authorities));
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the authorities
	 */
	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * @return the accountNonExpired
	 */
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @return the accountNonLocked
	 */
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @return the credentialsNonExpired
	 */
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param authorities
	 * @return SortedSet<GrantedAuthority>
	 */
	private static SortedSet<GrantedAuthority> sortAuthorities(
	        Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities,
		        "Cannot pass a null GrantedAuthority collection");
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(
		        new AuthorityComparator());

		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority,
			        "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}

	/**
	 * @author hudson.brito
	 *
	 */
	private static class AuthorityComparator implements
	        Comparator<GrantedAuthority>, Serializable {
		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

		/*
		 * (non-Javadoc)
		 *
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			if (g2.getAuthority() == null) {
				return -1;
			}

			if (g1.getAuthority() == null) {
				return 1;
			}

			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof CustomUser) {
			return username.equals(((CustomUser) rhs).getUsername());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return username.hashCode();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("Username: ").append(this.username).append("; ");
		sb.append("Password: [PROTECTED]; ");
		sb.append("Enabled: ").append(this.enabled).append("; ");
		sb.append("AccountNonExpired: ").append(this.accountNonExpired)
		        .append("; ");
		sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired)
		        .append("; ");
		sb.append("AccountNonLocked: ").append(this.accountNonLocked)
		        .append("; ");

		if (!authorities.isEmpty()) {
			sb.append("Granted Authorities: ");

			boolean first = true;
			for (GrantedAuthority auth : authorities) {
				if (!first) {
					sb.append(",");
				}
				first = false;

				sb.append(auth);
			}
		} else {
			sb.append("Not granted any authorities");
		}

		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.core.CredentialsContainer#eraseCredentials()
	 */
	public void eraseCredentials() {
		password = null;
	}

    /**
     * @return the idUser
     */
    public String getIdUser() {
        return idUser;
    }
    
    /**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

}
