package com.util.bean;

import java.util.List;

public class MenuTree {
	private String		menuId;
	private String		menuParId;
	private String 		menuName;
	private String  	menuUrl;
	private Integer		menuStatus;
	private Integer		menuType;
	private String		menuCode;
	private String		sysId;
	private String		authUrl;
	
	private List<MenuTree> children;
	
	
	public List<MenuTree> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuParId() {
		return menuParId;
	}
	public void setMenuParId(String menuParId) {
		this.menuParId = menuParId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getAuthUrl() {
		return authUrl;
	}
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
	
	public Integer getMenuStatus() {
		return menuStatus;
	}
	public void setMenuStatus(Integer menuStatus) {
		this.menuStatus = menuStatus;
	}
	public Integer getMenuType() {
		return menuType;
	}
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}
	@Override
	public String toString() {
		return "MenuTree [menuId=" + menuId + ", menuParId=" + menuParId
				+ ", menuName=" + menuName + ", menuUrl=" + menuUrl
				+ ", menuStatus=" + menuStatus + ", menuType=" + menuType
				+ ", menuCode=" + menuCode + ", sysId=" + sysId + ", authUrl="
				+ authUrl + ", children=" + children + "]";
	}
	
	
}
