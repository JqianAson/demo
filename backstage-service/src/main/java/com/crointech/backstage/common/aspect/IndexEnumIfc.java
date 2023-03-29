package com.crointech.backstage.common.aspect;

/**
 * ENUM顶级父类
 */
public interface IndexEnumIfc {

	/**
	 * ENUM元素标识，当一个ENUM需要用一个int值来作为唯一标识时，建议实现该接口，方便调用统一轮训。
	 * 
	 * @see EnumManager#getIndexEnumByIndex(int, Class)
	 * @see DbTypeEnumIfc
	 * @return
	 */
	int getIndex();

}
