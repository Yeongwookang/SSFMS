package com.ssfms;

public class Login {
	
	
	// emp 사람들이 로그인 되어야하니까
	private EmpDTO loginEmp = null;
	
	
	//로그인 멤버 변수 리턴 (생성자...)
	public EmpDTO loginEmp() {
		return loginEmp;
	}
	
	//로그인 하는 메소드 - 로그인emp 변수 dto 를 받음. 
	public void login(EmpDTO loginEmp) {
		this.loginEmp = loginEmp;
	}
	
	public void logout() {
		loginEmp = null;
	}
	
}
