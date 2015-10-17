package com.mediabox.findpro.form;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartForm {
	@NotNull
	private int menuid;
	
	@NotNull
	private int count;
}
