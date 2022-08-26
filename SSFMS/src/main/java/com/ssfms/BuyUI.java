package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.util.DBConn;

public class BuyUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BuyDAO buydao = new BuyDAOImpl();
	
	public void menu() {
		
		int ch;
		
		while(true) {
			try {
				System.out.println();
				System.out.println("----------------------------------");
				System.out.println("[1] 전표등록 [2] 원자재 발주 [3] 발주수정 [4] 발주현황조회"
						+ "\n[5] 발주취소 [6] 원자재 재고조회 [7] 반품등록"
						+ "\n\n[8] 매입처 추가 [9] 매입처 수정 [10] 매입처 삭제 ");
				System.out.println("-------------------------------------");
				System.out.println("=> ");
				
				ch = Integer.parseInt(br.readLine());
				
				if(ch == 7) {
					DBConn.close();
					return;
				}
				
				switch(ch) {
				case 1: accInsert(); break;
				case 2: buyInsert(); break;
				case 3: buyUpdate(); break;
				case 4: buyList(); break;
				case 5: buyDelete(); break;
				case 6: partList(); break;
				case 7: banpumInsert(); break;
				case 8: shopInsert(); break;
				case 9: shopUpdate(); break;
				case 10: shopDelete(); break;

				}
				
			} catch (Exception e) {
			}
		}
		
	}
	
	
	
	protected void accInsert() {


	}
	
	protected void buyInsert() {
	
	}

	
	protected void buyUpdate() {

		
	}
	
	protected void buyList() {

		
	}
	
	protected void buyDelete() {

		
	}
	
	protected void partList() {

		
	}
	
	protected void banpumInsert() {
	
	}
	
	protected void shopInsert() {
		System.out.println("\n[매입처 추가] 매입처 추가하기");
		BuyDTO buydto = new BuyDTO();
		System.out.print("매입처 코드[ 입력 : ");
		
		
		
	}
	
	protected void shopUpdate() {
		
	}
	
	protected void shopDelete() {
		
	}
	
	
	
	
	
	
}
