package com.mediabox.findpro.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
	@NotNull
	@Size(min=2, max=30)
	private String username;
	
	@NotNull
	@Size(min=2, max=30)
	private String password;
}
