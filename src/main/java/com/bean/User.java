package com.bean;

import java.util.Date;

public class User {
	private int userID;
	private String userName;
	private String password;
	private String email;
	private int sex;
	private int age;
	private Date birth;
	private String constellation;
	private String work;
	private String hometown;
	private String district;
	private String school;
	private String company;
	private String phone;
	private String personalizedSignature;
	private String photo;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPersonalizedSignature() {
		return personalizedSignature;
	}
	public void setPersonalizedSignature(String personalizedSignature) {
		this.personalizedSignature = personalizedSignature;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", password=" + password + ", sex=" + sex
				+ ", birth=" + birth + ", constellation=" + constellation + ", work=" + work + ", hometown=" + hometown
				+ ", district=" + district + ", school=" + school + ", company=" + company + ", phone=" + phone
				+ ", email=" + email + ", personalizedSignature=" + personalizedSignature + ", photo=" + photo + "]";
	}

	

}
