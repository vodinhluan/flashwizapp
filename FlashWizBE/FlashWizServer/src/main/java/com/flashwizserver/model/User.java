package com.flashwizserver.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User implements UserDetails  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	@Column(length=128, nullable=false)
	public String name;
	@Column(length=128, nullable=false, unique=true)
	public String email;
	@Column(length=64, nullable=false)
	public String password;
	@Column(length=64, nullable=true)
	private String resetPasswordOTP;
	
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<Role> roles = new HashSet();
	private Set<Folder> folders = new HashSet<>();

	public User() {}

	public User(String email, String password, String name) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}
	





	public String getResetPasswordOTP() {
		return resetPasswordOTP;
	}

	public void setResetPasswordOTP(String resetpasswordOTP) {
		this.resetPasswordOTP = resetpasswordOTP;
	}

	@Override
	public String toString() {
		return "User [Id=" + id 
				+ ", Email=" + email 
				+ ", Name= "+name
				+ " , Roles=" + roles
				+ "]";
	}

	

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {

		// TODO Auto-generated method stub

		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {

		// TODO Auto-generated method stub

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		// TODO Auto-generated method stub

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		// TODO Auto-generated method stub

		return true;
	}

	@Override
	public boolean isEnabled() {

		// TODO Auto-generated method stub

		return true;
	}

	public Object orElseThrow(Object object) {
		return null;
	}

	public void addFolder(Folder folder) {
	    this.folders.add(folder);
	}
	


}
