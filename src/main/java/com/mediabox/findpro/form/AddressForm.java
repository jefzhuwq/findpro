package com.mediabox.findpro.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressForm {
	@NotNull
	@Size(min=2, max=30)
	private String firstname;
	
	@NotNull
	@Size(min=2, max=30)
	private String lastname;
	
	@NotNull
	@Size(min=2, max=30)
	private String street;
	
	@NotNull
	@Size(min=2, max=30)
	private String city;
	
	@NotNull
	@Size(min=2, max=30)
	private String state;
	
	@NotNull
	@Size(min=2, max=30)
	private String zipcode;
}
