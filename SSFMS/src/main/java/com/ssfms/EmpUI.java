package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class EmpUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private EmpDAO dao = new EmpDAOImpl();
	
	/*
	Date date = new Date();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
	// 안에 문자에도 대소문자 구별 필수
	String s = sdf.format(date);
	System.out.println(s);
	*/
	

	public void menu() { 
		
		int ch;
		
		while(true) {
			try {
				do {
				System.out.print("1.사원관리 2.경력관리 3.연봉관리 4.급여관리 5.근태관리 6.돌아가기 => ");
				ch = Integer.parseInt(br.readLine());
				System.out.println();
				}while(ch<1||ch>6);
				
				switch(ch) {
				case 1: new EmpUI().menu1();
				case 2: new EmpUI().menu2();
				case 3: new EmpUI().menu3();
				case 4: new EmpUI().menu4();
				case 5: new EmpUI().menu5();				
				// 메인 메뉴로 돌아가기 
				case 6: App.main(null); break;
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
    			System.out.print("1.사원등록 2.사원수정 3.사원리스트 4.돌아가기 => ");
    			ch = Integer.parseInt(br.readLine());
    			}while(ch<1||ch>4);
    			System.out.println();
    			
    			if(ch==4) {
    				new EmpUI().menu();
    			}
    			
    			switch (ch) {
    			case 1: insert(); break;
				case 2: update(); break;
				case 3: listAll(); break;
				}
			} catch (Exception e) {
			}
    	}
    }
	
	protected void insert() {
		System.out.println("\n사원 등록하기 !!!");
		
		try {
			EmpDTO dto = new EmpDTO();
			
			/*
			System.out.print("사원번호 ? ");
			dto.setEmpNo(br.readLine());
			*/
			
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
			
			dao.insertEmp(dto);
			
			System.out.println("사원등록에 성공 했습니다.");
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
	
	// 경력사항 관리
	public void menu2() { 
    	int ch;
    	while(true) {
    		try {
    			do {
    			System.out.print("1.경력사항 입력 2.경력사항 수정 3.경력사항 리스트 4.돌아가기 => ");
    			ch = Integer.parseInt(br.readLine());
    			}while(ch<1||ch>4);
    			System.out.println();
    			
    			if(ch==4) {
    				new EmpUI().menu();
    			}
    			
    			switch (ch) {
    			case 1: cinsert(); break;
				case 2: cupdate(); break;
				case 3: clistAll(); break;
				}
			} catch (Exception e) {
			}
    	}
    }
	
	protected void cinsert() {
		
	}
	
    protected void cupdate() {
		
	}
    
    protected void clistAll() {
		
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
			
			System.out.print("연봉코드 ? ");
			adto.setAsalNo(br.readLine());
			
			System.out.print("계약일자 ? ");
			adto.setSal_date(br.readLine());

			System.out.print("계약연봉 ? ");
			adto.setAsal(Integer.parseInt(br.readLine()));

			System.out.print("사번 ? ");
			adto.setEmpNo(br.readLine());
			
			dao.insertAsal(adto);
			
			System.out.println("연봉등록에 성공 했습니다.");
		} catch (Exception e) {
			System.out.println("연봉등록에 실패 했습니다.");
		}
		
		System.out.println();
	}

    protected void aupdate() {
		
	}
    
    protected void alistAll() {
		
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
		
	}
	
    protected void supdate() {
		
	}
    
    protected void slistAll() {
		
	}
    
    // 근태 관리
    public void menu5() { 
    	int ch;
    	while(true) {
    		try {
    			do {
    			System.out.print("1.근태 등록 2.근태 수정 3.근태 리스트 4.돌아가기 => ");
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
		
	}
	
    protected void atupdate() {
		
	}
    
    protected void atlistAll() {
		
	}
    
    
}
