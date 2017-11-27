package com.anup.entity;
// Generated May 31, 2017 3:23:15 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @RRay
 */
@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "role" }))
public class Role implements java.io.Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Column(name = "ROLE_ID")
	private int roleId;
	private String role;
	private User user;

	public Role() {
	}

	public Role(int roleId, User user, String role) {
		this.roleId = roleId;
		this.user = user;
		this.role = role;
	}

	@Id

	@Column(name = "role_id", unique = true, nullable = false)
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "role", nullable = false, length = 45)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role is:-" + "\n\t RoleId:- " + this.getRoleId() + "\n\t UserId:- " + this.getUser().getUserId()
				+ "\n\t Role:- " + this.role;
	}

}
