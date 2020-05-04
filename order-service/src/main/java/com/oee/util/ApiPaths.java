package com.oee.util;
public final class ApiPaths {
	
	private static final String BASE_PATH = "/rest/order";
	
	public static final class OrderInfoCtrl{
		public static final String CTRL = BASE_PATH + "/orderinfo";
	}
	
	public static final class OrderedMaterialCtrl{
		public static final String CTRL = BASE_PATH + "/orderedmaterial";
	}
	
	public static final class ConsumptionMaterialCtrl{
		public static final String CTRL = BASE_PATH + "/consumptionmaterial";
	}
	
}