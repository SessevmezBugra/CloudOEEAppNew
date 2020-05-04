package com.oee.util;
public final class ApiPaths {
	
	private static final String BASE_PATH = "/rest/auth";
	
	public static final class UserCtrl{
		public static final String CTRL = BASE_PATH + "/user";
	}
	
	public static final class UserInfoCtrl{
		public static final String CTRL = BASE_PATH + "/userinfo";
	}
	
	public static final class UserInvitationInfoCtrl{
		public static final String CTRL = BASE_PATH + "/userinvitationinfo";
	}
	
	public static final class UserCareerInfoCtrl{
		public static final String CTRL = BASE_PATH + "/usercareerinfo";
	}
	
	public static final class AuthorityCtrl{
		public static final String CTRL = BASE_PATH + "/authority";
	}
	
}