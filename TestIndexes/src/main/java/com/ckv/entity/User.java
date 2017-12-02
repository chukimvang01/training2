package com.ckv.entity;

import java.util.Date;

public class User {
	private enum Sex{MALE,FEMALE}
	private int id;
	private String firstName;
	private String lastName;
	private Sex sex;
	private Date dob;
	private int age;
	private String country;
	
	@SuppressWarnings("deprecation")
	public User(String firstName, String lastName, Sex sex,Date dob, String country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.dob = dob;
		this.country = country;
		
		Date current=new Date();
		this.age=(current.getYear()-dob.getYear());
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	@Override
	public String toString() {
		return id+" "+firstName+" "+lastName+" "+sex+" "+age+" "+country;
	}
}
