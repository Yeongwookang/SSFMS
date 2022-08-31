package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;





public class EmpUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private EmpDAO dao = new EmpDAOImpl();

	public void menu() { 
		
		int ch;
		
		while(true) {
			try {
				do {
				System.out.print("1.사원관리 2.경력관리 3.연봉관리 4.급여관리 5.근태관리 6.급여전표관리 7.돌아가기 => ");
				ch = Integer.parseInt(br.readLine());
				System.out.println();
				}while(ch<1||ch>7);
				
				switch(ch) {
				case 1: new EmpUI().menu1();
				case 2: new EmpUI().menu2();
				case 3: new EmpUI().menu3();
				case 4: new EmpUI().menu4();
				case 5: new EmpUI().menu5();
				case 6: new EmpUI().menu6();
				// 메인 메뉴로 돌아가기 
				case 7: App.main(null); break;
				}
				
			} catch (Exception e) {
			}
		}
		
	}
	
	// 사원리스트 관리
	public void menu1() { 
    	int ch;
    	while(true) {
    		try {
    			do {
    			System.out.print("1.사원등록 2.사원수정 3.사원리스트 4.사번검색 5.돌아가기 => ");
    			ch = Integer.parseInt(br.readLine());
    			}while(ch<1||ch>5);
    			System.out.println();
    			
    			if(ch==5) {
    				new EmpUI().menu();
    			}
    			
    			switch (ch) {
    			case 1: insert(); break;
				case 2: update(); break;
				case 3: listAll(); break;
				case 4: findByEmp(); break;
				}
			} catch (Exception e) {
			}
    	}
    }
	
	protected void insert() {
		System.out.println("\n사원 등록하기 !!!");
		
		try {
			EmpDTO dto = new EmpDTO();
			
			System.out.print("성명 ? ");
			dto.setName(br.readLine());

			System.out.print("비밀번호 ? ");
			dto.setPwd(br.readLine());

			System.out.print("학력사항 ? ");
			dto.setEdu(br.readLine());

			System.out.print("연락처 ? ");
			dto.setTel(br.readLine());
			
			System.out.print("이메일 ? ");
			dto.setEmail(br.readLine());
			
			System.out.print("주소 ? ");
			dto.setAddr(br.readLine());
			
			System.out.print("통장번호 ? ");
			dto.setAccount(br.readLine());
			
			System.out.print("고용형태 ? ");
			dto.setHire_class(br.readLine());
			
			System.out.print("주민번호 ? ");
			dto.setRrn(br.readLine());
			// 경력사항
			System.out.print("비고 ? ");
			dto.setcNote(br.readLine());

			System.out.print("부서코드 ? ");
			dto.setDepNo(br.readLine());

			dao.insertEmp(dto);
			
			System.out.println("사원등록에 성공 했습니다.");
			System.out.println("[ 등록된 사번 : "+dto.getEmpNo()+" ]");
		} catch (Exception e) {
			System.out.println("사원등록에 실패 했습니다.");
		}
		
		System.out.println();

	}
	
	protected void update() {
		System.out.println("\n사원 정보 수정 !!!");
		
		try {
			EmpDTO dto = new EmpDTO();
			
			System.out.print("수정할 사원번호 ? ");
			dto.setEmpNo(br.readLine());
			
			System.out.print("성명 ? ");
			dto.setName(br.readLine());

			System.out.print("비밀번호 ? ");
			dto.setPwd(br.readLine());

			System.out.print("학력사항 ? ");
			dto.setEdu(br.readLine());

			System.out.print("연락처 ? ");
			dto.setTel(br.readLine());
			
			System.out.print("이메일 ? ");
			dto.setEmail(br.readLine());
			
			System.out.print("주소 ? ");
			dto.setAddr(br.readLine());
			
			System.out.print("통장번호 ? ");
			dto.setAccount(br.readLine());
			
			System.out.print("고용형태 ? ");
			dto.setHire_class(br.readLine());
			
			System.out.print("주민번호 ? ");
			dto.setRrn(br.readLine());
			
			dao.updateEmp(dto);
			
			System.out.println("사원 수정에 성공 했습니다.");
		} catch (Exception e) {
			System.out.println("사원 수정에 실패 했습니다.");
		}
		
		System.out.println();
	
	}
	
	protected void listAll() {
		System.out.println("\n사원전체 리스트 !!!");
		
		List<EmpDTO> list = dao.listEmp();
		
		System.out.println("사번\t이름\t전화번호\t\t이메일\t\t\t주소\t\t학력사항\t급여통장\t\t고용형태");
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		
		for(EmpDTO dto : list) {
			System.out.print(dto.getEmpNo() +"\t");
			System.out.print(dto.getName() +"\t");
			System.out.print(dto.getTel() +"\t");
			System.out.print(dto.getEmail() +"\t");
			System.out.print(dto.getAddr() +"\t");
			System.out.print(dto.getEdu() +"\t");
			System.out.print(dto.getAccount()+"\t");
			System.out.println(dto.getHire_class());
		}
		System.out.println();
	
	}
	
	protected void findByEmp() {
		System.out.println("\n사번 검색 !!!");
		String id;
		
		try {
			System.out.print("검색할 사번 ? ");
			id = br.readLine();
			
			EmpDTO dto = dao.readEmp(id);
			if(dto == null) {
				System.out.println("등록된 사번이 아닙니다.\n");
				return;
			}
			System.out.print(dto.getEmpNo() +"\t");
			System.out.print(dto.getName() +"\t");
			System.out.print(dto.getTel() +"\t");
			System.out.print(dto.getEmail() +"\t");
			System.out.print(dto.getAddr() +"\t");
			System.out.print(dto.getEdu() +"\t");
			System.out.print(dto.getAccount()+"\t");
			System.out.println(dto.getHire_class());
		} catch (Exception e) {
		}
		
		System.out.println();
	}
	
	// 경력사항 관리
	public void menu2() { 
    	int ch;
    	while(true) {
    		try {
    			do {
    			System.out.print("1.경력사항 입력 2.경력사항 수정 3.경력사항 리스트 4.직급&경력검색 5.돌아가기 => ");
    			ch = Integer.parseInt(br.readLine());
    			}while(ch<1||ch>5);
    			System.out.println();
    			
    			if(ch==5) {
    				new EmpUI().menu();
    			}
    			
    			switch (ch) {
    			case 1: cinsert(); break;
				case 2: cupdate(); break;
				case 3: clistAll(); break;
				case 4: findByEmpNo(); break;
				}
			} catch (Exception e) {
			}
    	}
    }
	
	protected void cinsert() {
        System.out.println("\n경력사항 등록하기 !!!");
		
		try {
			EmpDTO dto = new EmpDTO();
			
			System.out.print("구분(퇴사/입사) ? ");
			dto.setcDiv(br.readLine());

			System.out.print("일시(퇴사/입사) ? ");
			dto.setCar_date(br.readLine());

			System.out.print("비고 ? ");
			dto.setcNote(br.readLine());

			System.out.print("부서코드 ? ");
			dto.setDepNo(br.readLine());
			
			System.out.print("직급코드 ? ");
			dto.setRankNo(br.readLine());
			
			System.out.print("사번 ? ");
			dto.setEmpNo(br.readLine());
			
			dao.insertCare(dto);
			
			System.out.println("경력등록에 성공 했습니다.");
			System.out.println("[ 등록된 경력코드 : "+dto.getCarNo()+" ]");
		} catch (Exception e) {
			System.out.println("경력등록에 실패 했습니다.");
		}
		
		System.out.println();
		
	}
	
    protected void cupdate() {
        System.out.println("\n경력사항 수정 !!!");
		
		try {
			EmpDTO dto = new EmpDTO();
			
			System.out.print("수정할 경력번호 ? ");
			dto.setCarNo(br.readLine());
			
			System.out.print("구분 ? ");
			dto.setcDiv(br.readLine());

			System.out.print("일시(퇴사/입사) ? ");
			dto.setCar_date(br.readLine());

			System.out.print("비고 ? ");
			dto.setcNote(br.readLine());

			System.out.print("부서코드 ? ");
			dto.setDepNo(br.readLine());
			
			System.out.print("직급코드 ? ");
			dto.setRankNo(br.readLine());
			
			System.out.print("사번 ? ");
			dto.setEmpNo(br.readLine());
			
			dao.updateCare(dto);
			
			System.out.println("경력사항 수정에 성공 했습니다.");
		} catch (Exception e) {
			System.out.println("경력사항 수정에 실패 했습니다.");
		}
		
		System.out.println();
		
	}
    
    protected void clistAll() {
        System.out.println("\n경력사항 리스트 !!!");
		
		List<EmpDTO> list = dao.listCare();
		System.out.println("경력번호\t구분\t일시\t\t\t부서코드\t직급코드\t사번");
		System.out.println("--------------------------------------------------------------------------------");
		for(EmpDTO dto : list) {

			System.out.print(dto.getCarNo() +"\t");
			System.out.print(dto.getcDiv() +"\t");
			System.out.print(dto.getCar_date() +"\t");
			System.out.print(dto.getDepNo() +"\t");
			System.out.print(dto.getRankNo() +"\t");
			System.out.println(dto.getEmpNo());

		}
		System.out.println();
	}
    // 사번으로 최신부서, 직급 검색
    protected void findByEmpNo() {
		System.out.println("\n최신부서&직급 검색 !!!");
		String id;
		
		try {
			System.out.print("검색할 사번 ? ");
			id = br.readLine();
			
			EmpDTO dto = dao.readMember(id);
			if(dto == null) {
				System.out.println("등록된 사번이 아닙니다.\n");
				return;
			}
			System.out.print(dto.getCarNo()+"\t");
			System.out.print(dto.getEmpNo()+"\t");
			System.out.print(dto.getcDiv()+"\t");
			System.out.print(dto.getCar_date()+"\t");
			System.out.print(dto.getcNote()+"\t");
			System.out.print(dto.getDep()+"\t");
			System.out.println(dto.getRank());
			
		} catch (Exception e) {
		}
		
		System.out.println();
	}
	
    
    // 연봉 관리
    public void menu3() { 
    	int ch;
    	while(true) {
    		try {
    			do {
    			System.out.print("1.연봉 입력 2.연봉 수정 3.연봉 리스트 4.돌아가기 => ");
    			ch = Integer.parseInt(br.readLine());
    			}while(ch<1||ch>4);
    			System.out.println();
    			
    			if(ch==4) {
    				new EmpUI().menu();
    			}
    			
    			switch (ch) {
    			case 1: ainsert(); break;
				case 2: aupdate(); break;
				case 3: alistAll(); break;
				}
			} catch (Exception e) {
			}
    	}
    }
    
	protected void ainsert() {
		System.out.println("연봉 입력하기 !!!");
		
		try {
			EmpDTO adto = new EmpDTO();

			System.out.print("계약연봉 ? ");
			adto.setAsal(Integer.parseInt(br.readLine()));

			System.out.print("사번 ? ");
			adto.setEmpNo(br.readLine());
			
			dao.insertAsal(adto);
			
			System.out.println("연봉등록에 성공 했습니다.");
			System.out.println("[ 등록된 연봉코드 : "+adto.getAccountNo()+" ]");
		} catch (Exception e) {
			System.out.println("연봉등록에 실패 했습니다.");
		}
		
		System.out.println();
	}

    protected void aupdate() {
        System.out.println("\n연봉 수정 !!!");
		
		try {
			EmpDTO adto = new EmpDTO();
			
			System.out.print("수정할 연봉코드 ? ");
			adto.setAsalNo(br.readLine());
			
			System.out.print("계약일자 ? ");
			adto.setSal_date(br.readLine());

			System.out.print("계약연봉 ? ");
			adto.setAsal(Integer.parseInt(br.readLine()));

			System.out.print("사번 ? ");
			adto.setEmpNo(br.readLine());
			
			dao.updateAsal(adto);
			
			System.out.println("연봉 수정에 성공 했습니다.");
		} catch (Exception e) {
			System.out.println("연봉 수정에 실패 했습니다.");
		}
		
		System.out.println();
	}
    
    protected void alistAll() {
        System.out.println("\n연봉 리스트 !!!");
		
		List<EmpDTO> list = dao.listAsal();
		System.out.println("연봉코드\t계약일자\t\t\t계약연봉\t사번");
		System.out.println("----------------------------------------------------------------");
		for(EmpDTO dto : list) {
			System.out.print(dto.getAsalNo() +"\t");
			System.out.print(dto.getSal_date() +"\t");
			System.out.print(dto.getAsal() +"\t");
			System.out.println(dto.getEmpNo());

		}
		System.out.println();
	}
    
    // 급여 관리
    public void menu4() { 
    	int ch;
    	while(true) {
    		try {
    			do {
    			System.out.print("1.급여 등록 2.급여 수정 3.급여 리스트 4.돌아가기 => ");
    			ch = Integer.parseInt(br.readLine());
    			}while(ch<1||ch>4);
    			System.out.println();
    			
    			if(ch==4) {
    				new EmpUI().menu();
    			}
    			
    			switch (ch) {
    			case 1: sinsert(); break;
				case 2: supdate(); break;
				case 3: slistAll(); break;
				}
			} catch (Exception e) {
			}
    	}
    }
    
    protected void sinsert() {
        System.out.println("\n급여 등록하기 !!!");
		
		try {
			AccDTO accdto = new AccDTO();
    		EmpDTO empdto = new EmpDTO();
    		
			System.out.print("급여 신청자 사번: ");
			empdto.setEmpNo(br.readLine());
			
			System.out.print("월급 ? ");
			empdto.setSal(Integer.parseInt(br.readLine()));
			
			int tax = 0;
			if(empdto.getSal()>=3000000) {
				tax = (int) (empdto.getSal()*0.03);
			}else if (empdto.getSal()>=2000000) {
				tax = (int) (empdto.getSal()*0.02);
			}else {
				tax=0;
			}

			empdto.setTax(tax);

			System.out.print("보너스 ? ");
			empdto.setBonus(Integer.parseInt(br.readLine()));
			
			int rSal = empdto.getSal()- empdto.getTax();
			empdto.setPay(rSal);
			
			System.out.print("급여 신청 계좌코드: ");
			accdto.setAccountNo(br.readLine());
			
			int kSal = rSal + empdto.getBonus();
			accdto.setAmount(kSal);
			
			// System.out.print("상세 내용: ");
			// accdto.setDetail(br.readLine());
			
			// System.out.print("급여 신청 일자: ");
			// accdto.setStateDate(br.readLine());
			
			dao.insertSett(accdto, empdto);
			
			
			System.out.println();
			System.out.println("[등록완료] 매입전표 등록이 완료되었습니다.");
			System.out.println("관리자 승인까지 1~2일의 기간이 소요됩니다. ");
			
			System.out.println("급여등록에 성공 했습니다.");
			System.out.println("등록된 급여코드 : "+empdto.getSettleNo());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("급여등록에 실패 했습니다.");
		}
		
		System.out.println();
	}
	
    protected void supdate() {
        System.out.println("\n급여 수정 !!!");
		
		try {
			AccDTO accdto = new AccDTO();
    		EmpDTO empdto = new EmpDTO();
			// 정산코드
			System.out.print("수정할 정산코드 ? ");
			empdto.setSettleNo(br.readLine());
			// 전표일련번호
			System.out.print("수정할 전표일련번호 ? ");
			accdto.setStateNo(Integer.parseInt(br.readLine()));
			// 사번
			System.out.print("사번 ? ");
			empdto.setEmpNo(br.readLine());
			// 월급(세금전)
			System.out.print("월급 ? ");
			empdto.setSal(Integer.parseInt(br.readLine()));

			int tax = 0;
			if(empdto.getSal()>=3000000) {
				tax = (int) (empdto.getSal()*0.03);
			}else if (empdto.getSal()>=2000000) {
				tax = (int) (empdto.getSal()*0.02);
			}else {
				tax=0;
			}
			// 세금
			empdto.setTax(tax);
			// 보너스
			System.out.print("보너스 ? ");
			empdto.setBonus(Integer.parseInt(br.readLine()));
			// 실급여
			int rSal = empdto.getSal()- empdto.getTax();
			empdto.setPay(rSal);
			// 계좌코드
			System.out.print("급여 신청 계좌코드: ");
			accdto.setAccountNo(br.readLine());
			// 실급여
			int kSal = rSal + empdto.getBonus();
			accdto.setAmount(kSal);
			
			int result = dao.updateSett(accdto, empdto);
			

			if(result == 0) {
				System.out.println("등록된 자료가 아닙니다.");
			} else {
				System.out.println("회원정보가 수정 되었습니다.");
			}
			
			System.out.println("급여 수정에 성공 했습니다.");
		} catch (Exception e) {
			System.out.println("급여 수정에 실패 했습니다.");
		}
		
		System.out.println();
	}
    
    protected void slistAll() {
        System.out.println("\n급여 리스트 !!!");
		
		List<EmpDTO> list = dao.listSett();
		System.out.println("정산코드\t사번\t월급\t세금\t보너스\t실수령액\t정산일자");
		System.out.println("---------------------------------------------------------------------------");
		for(EmpDTO dto : list) {

			System.out.print(dto.getSettleNo() +"\t");
			System.out.print(dto.getEmpNo() +"\t");
			System.out.print(dto.getSal() +"\t");
			System.out.print(dto.getTax() +"\t");
			System.out.print(dto.getBonus() +"\t");
			System.out.print(dto.getPay()+"\t");
			System.out.println(dto.getPay_date());

		}
		System.out.println();
	}
    
    // 근태 관리
    public void menu5() { 
    	int ch;
    	while(true) {
    		try {
    			do {
    			System.out.print("1.출근 2.퇴근 3.근태 리스트 4.돌아가기 => ");
    			ch = Integer.parseInt(br.readLine());
    			}while(ch<1||ch>4);
    			System.out.println();
    			
    			if(ch==4) {
    				new EmpUI().menu();
    			}
    			
    			switch (ch) {
    			case 1: atinsert(); break;
				case 2: atupdate(); break;
				case 3: atlistAll(); break;
				}
			} catch (Exception e) {
			}
    	}
    }
    
    protected void atinsert() {
        System.out.println("\n출근 !!!");
		
		try {
			EmpDTO dto = new EmpDTO();
			
			System.out.print("사번 ? ");
			dto.setEmpNo(br.readLine());
			
			System.out.print("비고 ? ");
			dto.setaNote(br.readLine());

			dao.insertAtt(dto);
			
			System.out.println("출근에 성공 했습니다.");
			System.out.println("[ 등록된 근태코드 : "+dto.getAttNo()+" ]");
		} catch (Exception e) {
			System.out.println("출근에 실패 했습니다.");
		}
		
		System.out.println();
	}
	
	
    protected void atupdate() {
        System.out.println("\n퇴근 !!!");
		
		try {
			EmpDTO dto = new EmpDTO();
			
			System.out.print("금일 근태코드 ? ");
			dto.setAttNo(br.readLine());
			
			System.out.print("사번 ? ");
			dto.setEmpNo(br.readLine());
			
			System.out.print("비고 ? ");
			dto.setaNote(br.readLine());
			
			dao.updateAtt(dto);
			
			System.out.println("퇴근에 성공 했습니다.");
		} catch (Exception e) {
			System.out.println("퇴근에 실패 했습니다.");
		}
		
		System.out.println();
	}
    
    protected void atlistAll() {
    	 System.out.println("\n근태 리스트 !!!");
 		
 		List<EmpDTO> list = dao.listAtt();
 		System.out.println("사번\t근태코드\t출근\t\t\t퇴근\t\t\t비고");
		System.out.println("----------------------------------------------------------------------------");
 		for(EmpDTO dto : list) {

 			System.out.print(dto.getEmpNo()+"\t");
 			System.out.print(dto.getAttNo() +"\t");
 			System.out.print(dto.getsTime() +"\t");
 			System.out.print(dto.geteTime() +"\t");
 			System.out.println(dto.getaNote());

 		}
 		System.out.println();
	}
    
    // 전표 관리
    public void menu6() { 
    	int ch;
    	while(true) {
    		try {
    			do {
    			System.out.print("1.전표등록 2.전표수정 3.전표리스트 4.돌아가기 => ");
    			ch = Integer.parseInt(br.readLine());
    			}while(ch<1||ch>4);
    			System.out.println();
    			
    			if(ch==4) {
    				new EmpUI().menu();
    			}
    			
    			switch (ch) {
    			case 1: acInsert(); break;
				case 2: acUpdate(); break;
				case 3: acListAll(); break;
				}
			} catch (Exception e) {
			}
    	}
    }
    
    protected void acInsert() {
    	System.out.println("\n전표등록!!!");
    	
    	try {
    		AccDTO accdto = new AccDTO();
    		EmpDTO empdto = new EmpDTO();
    		
			System.out.print("신청자 사번: ");
			empdto.setEmpNo(br.readLine());
			
			System.out.print("신청 계좌코드: ");
			accdto.setAccountNo(br.readLine());
			
			System.out.print("신청 계정과목코드: ");
			accdto.setAccountSubNo(br.readLine());
			
			System.out.print("신청 금액: ");
			accdto.setAmount(Integer.parseInt(br.readLine()));
			
			System.out.print("상세 내용: ");
			accdto.setDetail(br.readLine());

			dao.insertAccSett(accdto, empdto);
			
			System.out.println();
			System.out.println("[등록완료] 매입전표 등록이 완료되었습니다.");
			System.out.println("관리자 승인까지 1~2일의 기간이 소요됩니다. ");
			
			
		} catch (NumberFormatException e) {
			System.out.println("금액은 숫자만 입력 가능합니다.");
		} catch (Exception e) {
			System.out.println("데이터 등록이 실패했습니다. ");
		}
		System.out.println();
			
    }
    
    protected void acUpdate() {
        System.out.println("\n[전표취소] 등록된 전표 취소하기 ");
		
		try {
			
			AccDTO accdto = new AccDTO();
			
			System.out.print("취소할 전표일련번호: ");
			accdto.setStateNo(Integer.parseInt(br.readLine()));
			
			int result = dao.updateAccSett(accdto);
			
			if(result ==0) {
				System.out.println("등록된 전표가 아니거나 승인된 전표입니다.");
				System.out.println("(취소조건 : 미승인된 구매 전표만 취소 가능 ");
			}else {
				System.out.println();
				System.out.println("[취소완료] 매입전표가 취소되었습니다.");
				System.out.println("관리자 승인까지 1~2일의 기간이 소요됩니다. ");
			}

			
		} catch (Exception e) {
			System.out.println("[취소실패] 전표 취소가 실패했습니다. ");
		}
		
		System.out.println();
    }
    
    protected void acListAll() {
        System.out.println("\n[등록전표조회] 등록된 매입전표 리스트");
		
		// String accountSubNo;
		
		try {
			// System.out.print("급여전표 조회 [급여 코드는 503, 세금 코드는 517]: ");
			// accountSubNo = br.readLine();
			
			List<AccDTO> list = dao.listAccSett();
			
			if(list.size()==0) {
				System.out.println("등록된 전표 내역이 없습니다. ");
				return;
			}
			
			System.out.println("전표일련번호\t차대\t계좌코드\t계정과목명\t\t금액\t취소상태\t전표상태\t전표날짜\t\t사번");
			System.out.println("------------------------------------------------------------------------------------------------------------------");
			
			for(AccDTO accdto : list) {
				System.out.print(accdto.getStateNo()+"\t\t");
				System.out.print(accdto.getT_account()+"\t");
				System.out.print(accdto.getAccountNo()+"\t");
				System.out.print(accdto.getAsub_name()+"\t\t");
				System.out.print(accdto.getAmount()+"\t");
				System.out.print(accdto.getCancellation()+"\t");
				System.out.print(accdto.getStateCon()+"\t");
				System.out.print(accdto.getStateDate()+"\t");
				System.out.println(accdto.getEmpNo()+"\t");

			}

			
		} catch (Exception e) {
			System.out.println("등록전표 조회에 실패했습니다. ");
		}
		System.out.println();
    }
    
    
}
