package com.instacart.challenge.model;

public class ShopperApplication {
	
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	private String referralCode;
	
	public ShopperApplication(String lastName, String firstName, String email, String phoneNumber,
			String referralCode) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.referralCode = referralCode;
	}
	
	public String getEmail() {
		return email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
