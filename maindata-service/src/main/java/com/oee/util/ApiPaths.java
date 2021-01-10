package com.oee.util;
public final class ApiPaths {
	
	private static final String BASE_PATH = "/main-data";
	
	public static final class ClientInfoCtrl{
		public static final String CTRL = BASE_PATH + "/client";
	}
	
	public static final class CompanyInfoCtrl{
		public static final String CTRL = BASE_PATH + "/company";
	}
	
	public static final class PlantInfoCtrl{
		public static final String CTRL = BASE_PATH + "/plant";
	}
	
	public static final class MaterialInfoCtrl{
		public static final String CTRL = BASE_PATH + "/material";
	}
	
	public static final class WarehouseInfoCtrl{
		public static final String CTRL = BASE_PATH + "/warehouse";
	}
	
	public static final class UOMCtrl{
		public static final String CTRL = BASE_PATH + "/uom";
	}

	public static final class StatusCtrl{
		public static final String CTRL = BASE_PATH + "/status";
	}

	public static final class ReasonCodeCtrl {
		public static final String CTRL = BASE_PATH + "/reason-code";
	}

	public static final class QualityTypeCtrl {
		public static final String CTRL = BASE_PATH + "/quality-type";
	}

	public static final class MachineCtrl {
		public static final String CTRL = BASE_PATH + "/machine";
	}
	
}