
package com.anup.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Raichand
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public void login() throws ServletException, IOException {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		username = context.getRequestParameterMap().get("username");
		System.out.println("Login controller username2 is :-" + username);
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/login");
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
	}

	public String logout() throws IOException, ServletException

	{
		System.out.println("Login controller password is :-" + password);
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/logout");
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();

		username = null;
		password = null;

		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
