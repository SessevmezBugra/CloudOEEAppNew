package com.oee.util;
public final class ApiPaths {
	
	private static final String BASE_PATH = "/rest/maindata";
	
	public static final class ClientInfoCtrl{
		public static final String CTRL = BASE_PATH + "/clientinfo";
	}
	
	public static final class CompanyInfoCtrl{
		public static final String CTRL = BASE_PATH + "/companyinfo";
	}
	
	public static final class PlantInfoCtrl{
		public static final String CTRL = BASE_PATH + "/plantinfo";
	}
	
	public static final class MaterialInfoCtrl{
		public static final String CTRL = BASE_PATH + "/materialinfo";
	}
	
	public static final class WarehouseInfoCtrl{
		public static final String CTRL = BASE_PATH + "/warehouseinfo";
	}
	
	public static final class UOMCtrl{
		public static final String CTRL = BASE_PATH + "/uom";
	}
	
}