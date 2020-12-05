package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

//管理者アカウントのEntity
@Table(name = "authorities")
@Entity
public class Authorities {
	@Id
	@NotBlank
	private String username;
	@NotBlank
	private String authority;

	public Authorities(@NotBlank String username, @NotBlank String password) {
		super();
		this.username = username;
		this.authority = password;
	}

	public Authorities() {
	}

	public String getUser_name() {
		return username;
	}

	public void setUser_name(String username) {
		this.username = username;
	}

	public String getPassword() {
		return authority;
	}

	public void setPassword(String password) {
		this.authority = password;
	}

}
