package com.ssfms;

public class EmpDTO {
	// 사원리스트
	private String empNo;
	private String pwd;
	private String name;
	private String tel;
	private String rrn;
	private String email;
	private String addr;
	private String edu;
	private String account;
	private String hire_class;
	// 부서
	private String depNo;
	private String dep;
	// 직급
	private String rankNo;
	private String rank;
	// 경력사항
	private String carNo;
	private String cDiv;
	private String car_date;
	private String cNote;
	// 연봉
	private String asalNo;
	private String sal_date;
	private int asal;
	// 급여관리
	private String settleNo;
	private int sal;
	private int tax;
	private int bonus;
	private int pay;
	private String pay_date;
	// 근태관리
	private String attNo;
	private String aDiv;
	private String sTime;
	private String eTime;
	private int wTime;
	private String aNote;
	// 전표관리
	
	private String asub_name;
	private String t_account;
	private int amount;
	private String detail;
	private String cancellation;
	private String accountNo;
	private String accountSubNo;
	private String stateNo;

	
	public String getStateNo() {
		return stateNo;
	}
	public void setStateNo(String stateNo) {
		this.stateNo = stateNo;
	}
	public String getAsub_name() {
		return asub_name;
	}
	public void setAsub_name(String asub_name) {
		this.asub_name = asub_name;
	}
	public String getT_account() {
		return t_account;
	}
	public void setT_account(String t_account) {
		this.t_account = t_account;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCancellation() {
		return cancellation;
	}
	public void setCancellation(String cancellation) {
		this.cancellation = cancellation;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountSubNo() {
		return accountSubNo;
	}
	public void setAccountSubNo(String accountSubNo) {
		this.accountSubNo = accountSubNo;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getHire_class() {
		return hire_class;
	}
	public void setHire_class(String hire_class) {
		this.hire_class = hire_class;
	}
	public String getDepNo() {
		return depNo;
	}
	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getRankNo() {
		return rankNo;
	}
	public void setRankNo(String rankNo) {
		this.rankNo = rankNo;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getcDiv() {
		return cDiv;
	}
	public void setcDiv(String cDiv) {
		this.cDiv = cDiv;
	}
	public String getCar_date() {
		return car_date;
	}
	public void setCar_date(String car_date) {
		this.car_date = car_date;
	}
	public String getcNote() {
		return cNote;
	}
	public void setcNote(String cNote) {
		this.cNote = cNote;
	}
	public String getAsalNo() {
		return asalNo;
	}
	public void setAsalNo(String asalNo) {
		this.asalNo = asalNo;
	}
	public String getSal_date() {
		return sal_date;
	}
	public void setSal_date(String sal_date) {
		this.sal_date = sal_date;
	}
	public int getAsal() {
		return asal;
	}
	public void setAsal(int asal) {
		this.asal = asal;
	}
	public String getSettleNo() {
		return settleNo;
	}
	public void setSettleNo(String settleNo) {
		this.settleNo = settleNo;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getAttNo() {
		return attNo;
	}
	public void setAttNo(String attNo) {
		this.attNo = attNo;
	}
	public String getaDiv() {
		return aDiv;
	}
	public void setaDiv(String aDiv) {
		this.aDiv = aDiv;
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	public String geteTime() {
		return eTime;
	}
	public void seteTime(String eTime) {
		this.eTime = eTime;
	}
	public int getwTime() {
		return wTime;
	}
	public void setwTime(int wTime) {
		this.wTime = wTime;
	}
	public String getaNote() {
		return aNote;
	}
	public void setaNote(String aNote) {
		this.aNote = aNote;
	}
	
	
	
	
	
	
	
	
}
