package com.oee.util;
public final class ApiPaths {
	
	private static final String BASE_PATH = "/rest/stock";
	
	public static final class StockInfoCtrl{
		public static final String CTRL = BASE_PATH + "/stockinfo";
	}
	
	public static final class StockMovementCtrl{
		public static final String CTRL = BASE_PATH + "/stockmovement";
	}
	
}