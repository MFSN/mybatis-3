package org.apache.ibatis.mybatistest.model;


import org.apache.ibatis.mybatistest.enums.UserSexEnum;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String userName;
	private String passWord;
	private UserSexEnum userSex;
	private StringWrapper nickName;

	public User() {
		super();
	}

	public User(String userName, String passWord, UserSexEnum userSex, StringWrapper nickName) {
		super();
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
		this.nickName = nickName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public UserSexEnum getUserSex() {
		return userSex;
	}

	public void setUserSex(UserSexEnum userSex) {
		this.userSex = userSex;
	}

	public StringWrapper getNickName() {
		return nickName;
	}

	public void setNickName(StringWrapper nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "userName " + this.userName + ", pasword " + this.passWord + "sex " + userSex.name();
	}

}