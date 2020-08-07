package com.oee.util;
public final class ApiPaths {
	
	private static final String BASE_PATH = "/order";
	
	public static final class OrderInfoCtrl{
		public static final String CTRL = BASE_PATH + "/order-info";
	}
	
	public static final class OrderedMaterialCtrl{
		public static final String CTRL = BASE_PATH + "/ordered-material";
	}
	
	public static final class ConsumptionMaterialCtrl{
		public static final String CTRL = BASE_PATH + "/consumption-material";
	}
	
}