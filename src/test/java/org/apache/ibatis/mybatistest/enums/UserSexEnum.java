package org.apache.ibatis.mybatistest.enums;

public enum UserSexEnum {
	MAN(1, "男"),
	WOMAN(2, "女");

	private int id;
	private String name;

	UserSexEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static UserSexEnum getUserSex(int id) {
		if (1 == id) {
			return MAN;
		} else if (2 == id) {
			return WOMAN;
		}
		return null;
	}
}
