package com.woniuxy.entities;

import com.woniuxy.annotations.ColumnNameAnn;

public class User {
	@ColumnNameAnn("userId")
	private int userId;
	@ColumnNameAnn("userName")
	private String userName;
	@ColumnNameAnn("userPwd")
	private String userPwd;
	@ColumnNameAnn("userRole")
	private String userRole;
	@ColumnNameAnn("userStatus")
	private String userStatus;

	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param userId
	 * @param userName
	 * @param userPwd
	 * @param userRole
	 * @param userStatu
	 */
	public User(int userId, String userName, String userPwd, String userRole, String userStatus) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userRole = userRole;
		this.userStatus = userStatus;
	}
	
	/**
	 * @param userName
	 * @param userPwd
	 */
	public User(String userName, String userPwd) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userPwd
	 */
	public String getUserPwd() {
		return userPwd;
	}
	/**
	 * @param userPwd the userPwd to set
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}
	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	/**
	 * @return the userStatus
	 */
	public String getUserStatus() {
		return userStatus;
	}
	/**
	 * @param userStatu the userStatus to set
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userRole=" + userRole
				+ ", userStatus=" + userStatus + "]";
	}
	
	
	
}
