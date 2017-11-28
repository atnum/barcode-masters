package com.anup.entity;
// Generated May 31, 2017 3:23:15 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Email;

/**
 * @RRay
 */
@Entity
@Table(name = "users",uniqueConstraints = @UniqueConstraint(columnNames = { "user_name",
		"password" }))

public class User implements java.io.Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Column(name = "USER_ID")
	private int userId;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Email(message="Not a Valid Email Address!")
	private String email;
	
//	@Size(min = 6, message = "Username must be minimum of 6 chracters, Try again!")
	private String username;
	
//    @Size(min = 6, message = "Invalid Password, Password must be greater than or equal to 6 chracters, Try again!")
	//@Pattern(regexp="^(?=.*\\d)(?=.*[A-Z]).{6,8}$", message="Password must have, Minimum 6 and Maximum 8 Character, Atleast 1 number, Atleast 1 alphabet in capitals, No Special char allowed")
	private String password;

	@SuppressWarnings("unused")
	private Boolean enabled;

	private Set<Role> roles = new HashSet<Role>(0);

	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "first_name", nullable = false, length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", nullable = false, length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "user_name", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "enabled", nullable = false, columnDefinition = "NUMBER(1)")
	public Boolean getEnabled() {
		return true;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void reset() {
		this.userId = 0;
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.password = "";
		this.username = "";
		this.roles = null;
		this.enabled = true;

	}

	// This method writes the values of contact object with
	// System.out.println(user.toString()) code
	@Override
	public String toString() {
		return "User is :-" + "\n\t FirstName:- " + this.firstName + "\n\t LastName:- " + this.lastName
				+ "\n\t UserName:- " + this.username + "\n\t Email:- " + this.email + "\n\t Password:- " + this.password
				+ "\n\t Authority:- " + this.getRoles();
	}

}
