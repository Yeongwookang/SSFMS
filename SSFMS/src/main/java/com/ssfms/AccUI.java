package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.util.DBConn;

public class AccUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private AccDAO dao = new AccDAOImpl();
	
	public void menu() {
		
		int ch;
		
		while(true) {
			try {
				
				System.out.print("회계 => 1. 전표등록 2. 전표수정 3. 전표삭제 4. 전체 전표 조회 5. 사원별 전표 조회 6. 계정과목 별 전표 조회 7. 월별 총 매입매출 조회 8. 거래처 관리 ");
				ch = Integer.parseInt(br.readLine());
				
				if(ch == 10) {
					DBConn.close();
					return;
				}
				
				switch(ch) {
				case 1: insert(); break;
				case 2: update(); break;
				case 3: delete(); break;
				case 4: listAll(); break;
				case 5: findByempNo(); break;
				case 6: findByname(); break;
				case 7: findBycateg_Name(); break;
				case 8: findBycustomer(); break;
				case 9: App.main(null); break;
				}
				
			} catch (Exception e) {
			}
		}
		
	}
	
	protected void insert() {
		System.out.println("\n전표 등록 ");
		try {
			AccDTO dto = new AccDTO(); 
			
			System.out.print("사번");
			dto.setEmpNo(br.readLine());
			
			System.out.print("계좌코드");
			dto.setAccountNo(br.readLine());
			
			System.out.print("계정과목코드");
			dto.setAccountSubNo(br.readLine());
			
			System.out.print("분류코드");
			dto.setCategNo(br.readLine());
			
			System.out.print("금액");
			dto.setAmount(br.readLine());
			
			System.out.print("상세내용");
			dto.setDetail(br.readLine());
			
			System.out.print("전표상태");
			dto.setStateCon(br.readLine());
			
			dao.insertAccount(dto);
			
			System.out.println("전표가 등록 되었습니다.");
		} catch (Exception e) {
			
		}
		
	}
	
	protected void update() {
		System.out.println("\n전표 수정 ");
	
	}
	
	protected void delete() {
		System.out.println("\n전표 삭제 ");
	
		
	}
	
	protected void listAll() {
		System.out.println("\n전체 전표 조회 ");
	
	}
	
	protected void findByempNo() {
		System.out.println("\n사원별 전표 조회 ");

		
	}
	
	protected void findByname() {
		System.out.println("\n계정과목 별 전표 조회 ");

		
	}
	
	protected void findBycateg_Name() {
		System.out.println("\n월별 총 매입/매출액 ");

		
	}
	
	protected void findBycustomer() {
		System.out.println("\n거래처 관리 ");

	}
	
}
