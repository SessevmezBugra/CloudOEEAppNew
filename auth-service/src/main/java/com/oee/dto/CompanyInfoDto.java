package com.oee.dto;

public class CompanyInfoDto {

	private Integer companyId;

	private String companyName;

	private String username;

//	private List<ClientInfo> clients;

	public CompanyInfoDto() {
		// TODO Auto-generated constructor stub
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

//	public List<ClientInfo> getClients() {
//		return clients;
//	}
//
//
//	public void setClients(List<ClientInfo> clients) {
//		this.clients = clients;
//	}
//
//	public void addClient(ClientInfo client) {
//		if(this.clients == null) {
//			this.clients = new ArrayList<ClientInfo>();
//		}
//		this.clients.add(client);
//	}

}