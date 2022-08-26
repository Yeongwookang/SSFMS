package com.ssfms;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.List;
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
						+ "\n\n[8] 매입처 추가 [9] 매입처 수정 [10] 매입처 삭제 [11] 매입처 조회 [12] 뒤로가기");
				System.out.println("-------------------------------------");
				System.out.print("=> ");
				
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
				case 11: shopList(); break;
				case 12: App.main(null); break; //뒤로가기
				

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
		System.out.println("\n[매입처 추가] 새로운 매입처 추가하기");
		
		try {
			
			BuyDTO buydto = new BuyDTO();
			
			System.out.print("매입처 코드[숫자 4자]: ");
			buydto.setShop_No("SH" + br.readLine());
			
			System.out.print("사업자등록번호[10자]: ");
			buydto.setShop_Num(br.readLine());
			
			System.out.print("매입처 상호명: ");
			buydto.setShop_Name("(주)"+ br.readLine());
			
			System.out.print("매입처 대표자 성명: ");
			buydto.setShop_Boss(br.readLine());
			
			System.out.print("매입처 전화번호 입력: ");
			buydto.setShop_Tel(br.readLine());
			
			System.out.print("매입처 우편번호[5자] 입력: ");
			buydto.setShop_Post(br.readLine());
			
			System.out.print("매입처 주소 입력: ");
			buydto.setShop_addr(br.readLine());
			
			System.out.print("매입처 등록일 입력: ");
			buydto.setShop_Reg(br.readLine());
			
			
			buydao.insertShop(buydto);
			
			System.out.println("[등록완료] 새로운 매입처가 등록되었습니다.");
			
			
		} catch (Exception e) {
			System.out.println("데이터 등록이 실패했습니다.");
		}
		System.out.println();

	}
	
	
	protected void shopUpdate() {
		System.out.println("\n[매입처 수정] 기존 매입처 수정하기");
		
		try {
			
			BuyDTO buydto = new BuyDTO();
			
			System.out.print("수정할 매입처 코드는?[숫자 4자]: ");
			buydto.setShop_No("SH" + br.readLine());
			
			System.out.print("수정된 사업자등록번호[10자]: ");
			buydto.setShop_Num(br.readLine());
			
			System.out.print("수정된 매입처 상호명: ");
			buydto.setShop_Name("(주)" + br.readLine());
			
			System.out.print("수정된 매입처 대표자 성명: ");
			buydto.setShop_Boss(br.readLine());
			
			System.out.print("수정된 매입처 전화번호 입력: ");
			buydto.setShop_Tel(br.readLine());
			
			System.out.print("수정된 매입처 우편번호[5자] 입력: ");
			buydto.setShop_Post(br.readLine());
			
			System.out.print("수정된 매입처 주소 입력: ");
			buydto.setShop_addr(br.readLine());
			
			System.out.print("수정된 매입처 등록일 입력: ");
			buydto.setShop_Reg(br.readLine());
			
			int result = buydao.updateShop(buydto);
			
			if(result==0) {
				System.out.println("등록된 자료가 아닙니다.");
			}else {
				System.out.println("데이터가 수정되었습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("자료 수정에 실패했습니다.");
		}
		System.out.println();
	}

	
	
	protected void shopDelete() {
		System.out.println("\n[매입처 삭제] 기존 매입처 삭제하기");
		
		
		try {
			
			BuyDTO buydto = new BuyDTO();
			
			System.out.print("삭제할 매입처 코드는?[숫자 4자]: ");
			 buydto.setShop_No("SH" + br.readLine());
			
			int result = buydao.deleteShop(buydto);
			
			if(result == 0) {
				System.out.println("등록된 매입처가 없습니다. ");
			}else {
				System.out.println("삭제가 완료되었습니다!");
				System.out.println("감사합니다.");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();

	}
	
	
	
	protected void shopList() {
		System.out.println("\n[매입처 조회] 현재 계약중인 매입처 리스트");
		
		System.out.println("매입처코드\t사업자등록번호\t상호명\t\t대표명\t전화번호\t\t우편번호\t주소\t\t\t등록일자\t");
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		
		List<BuyDTO> list = buydao.listShop();
		for(BuyDTO buydto : list) {
			System.out.print(buydto.getShop_No()+ "\t");
			System.out.print(buydto.getShop_Num()+ "\t");
			System.out.print(buydto.getShop_Name()+ "\t");
			System.out.print(buydto.getShop_Boss()+ "\t");
			System.out.print(buydto.getShop_Tel()+ "\t");
			System.out.print(buydto.getShop_Post()+ "\t");
			System.out.print(buydto.getShop_addr()+ "\t");
			System.out.println(buydto.getShop_Reg()+ "\t");

		}
		System.out.println();
		
	}

	
}
