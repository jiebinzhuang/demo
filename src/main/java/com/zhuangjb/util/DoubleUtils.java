package com.zhuangjb.util;

import java.math.BigDecimal;

public class DoubleUtils {
	public static Double format(Double a) {
		Double value = new BigDecimal(a).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return value;
	}

	public static Double format(Double a, int scale) {
		Double value = new BigDecimal(a).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		return value;
	}

	public static Double add(Double a, Double b) {
		Double value = new BigDecimal(a + b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return value;
	}

	public static Double subtract(Double a, Double b) {
		Double value = new BigDecimal(a - b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return value;
	}

	public static Double multiply(Double a, Double b) {
		Double value = new BigDecimal(a * b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return value;
	}

	public static Double divide(Double a, Double b) {
		Double value = new BigDecimal(a / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return value;
	}

	public static Double add(Double a, Double b, int scale) {
		Double value = new BigDecimal(a + b).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		return value;
	}

	public static Double subtract(Double a, Double b, int scale) {
		Double value = new BigDecimal(a - b).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		return value;
	}

	public static Double multiply(Double a, Double b, int scale) {
		Double value = new BigDecimal(a * b).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		return value;
	}

	public static Double divide(Double a, Double b, int scale) {
		Double value = new BigDecimal(a / b).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		return value;
	}

}
