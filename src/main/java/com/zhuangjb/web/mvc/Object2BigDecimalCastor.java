package com.zhuangjb.web.mvc;

import java.math.BigDecimal;

import org.nutz.castor.Castor;
import org.nutz.castor.FailToCastObjectException;

/**
 * 用于nutz的json输入转换
 * 
 * @author fangw
 */
public class Object2BigDecimalCastor extends Castor<Object, BigDecimal> {

	@Override
	public BigDecimal cast(Object src, Class<?> toType, String... args) throws FailToCastObjectException {
		if (src == null || src.toString().length() == 0) {
			return null;
		}

		if (toType.equals(BigDecimal.class)) {
			return new BigDecimal(src.toString());
		}
		return null;
	}

	@Override
	public Class<?> getFromClass() {
		return Object.class;
	}

	@Override
	public Class<?> getToClass() {
		return BigDecimal.class;
	}

}
