package com.crointech.backstage.common.aspect;

public enum OperType implements DbTypeEnumIfc {

	// 登录
	LOGIN(1),

	// 登出
	LOGOUT(2),

	// 新增
	ADD(3),

	// 修改
	UPDATE(4),

	// 删除
	DELETE(5);

	int id;

	OperType(int val) {
		this.id = val;
	}

	public int getId() {
		return this.id;
	}

	public static OperType valOf(int tu) {
		OperType[] values = OperType.values();
		for (OperType status : values) {
			if (status.id == tu) {
				return status;
			}
		}
		return null;
	}

	@Override
	public int getIndex() {
		return id;
	}
}
