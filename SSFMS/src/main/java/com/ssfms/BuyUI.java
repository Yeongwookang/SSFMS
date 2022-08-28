package com.ssfms;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.List;
import com.util.DBConn;


public class BuyUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BuyDAO buydao = new BuyDAOImpl();

	
	// 구매 관리 전체 메뉴
	public void menu() {
		
		int ch;
		
		while(true) {
			try {
				System.out.println();
				System.out.println("-----------------------------------------------------------------");
				System.out.println("[1] 매입전표관리 [2] 원자재관리 [3] 반품관리 [4] 매입처관리 [5] 뒤로가기");
				System.out.println("-----------------------------------------------------------------");
				System.out.print("=> ");
				
				ch = Integer.parseInt(br.readLine());
				
				switch(ch) {
				case 1: new BuyUI().menu1(); break;
				case 2: new BuyUI().menu2(); break;
				case 3: new BuyUI().menu3(); break;
				case 4: new BuyUI().menu4(); break;
				case 5: App.main(null); break; //뒤로가기
				

				}
				
			} catch (Exception e) {
			}
		}
		
	}
	
	
	//매입전표관리 메뉴
	public void menu1() {
		
		int ch;
		
		while(true) {
			try {
				
				System.out.println();
				System.out.println("-----------------------------------------------------------------");
				System.out.println("[1] 전표등록 [2] 전표취소 [3] 등록전표조회 [4] 뒤로가기 ");
				System.out.println("-----------------------------------------------------------------");
				System.out.print("=> ");
				
				ch = Integer.parseInt(br.readLine());
				
				if(ch==4) {
					new BuyUI().menu();
				}
				
				switch(ch) {
				case 1: accInsert(); break; 
				case 2: accDelete(); break;
				case 3: accList(); break;
				}
					
			} catch (Exception e) {
			}
			
		}
		
	}
	
	
	
	
	protected void accInsert() {


	}
	
	
	protected void accDelete() {
		
	}
	
	
	protected void accList() {
		
	}
	
	
	
	
	
	
	// 원자재관리
	public void menu2() {
		
		int ch;
		
		while(true) {
			try {
				
				System.out.println();
				System.out.println("-----------------------------------------------------------------");
				System.out.println("[1] 원자재 발주 [2] 발주수정 [3] 발주현황조회 [4] 발주취소 [5] 원자재 재고조회 [6] 뒤로가기 ");
				System.out.println("-----------------------------------------------------------------");
				System.out.print("=> ");
				
				ch = Integer.parseInt(br.readLine());
				
				if(ch==6) {
					new BuyUI().menu();
				}
				
				switch(ch) {
				case 1: buyInsert(); break;
				case 2: buyUpdate(); break;
				case 3: buyList(); break;
				case 4: buyDelete(); break;
				case 5: partList(); break;
				
				}
					
			} catch (Exception e) {
			}
			
		}
		
	}
	
	
	protected void buyInsert() {
		
	}

	
	protected void buyUpdate() {

		
	}
	
	protected void buyList() {
		System.out.println("\n[발주현황조회] 발주현황 조회하기");

		System.out.println("매입번호\t전표일련번호\t재료코드\t재료명\t매입일자\t매입수량\t매입금액\t매입처코드");
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		
		List<BuyDTO> list = buydao.listBuy();
		for(BuyDTO buydto : list) {
			System.out.print(buydto.getBuy_No()+"\t");
			System.out.print(buydto.getStateNo()+"\t");
			System.out.print(buydto.getPartNo()+"\t");
			System.out.print(buydto.getPart_name()+"\t");
			System.out.print(buydto.getBuy_Date()+"\t");
			System.out.print(buydto.getBuy_qty()+"\t");
			System.out.print(buydto.getBuy_price()+"\t");
			System.out.print(buydto.getShop_No()+"\t");
			
		}
		System.out.println();
		
	}
	
	
	
	protected void buyDelete() {

		
	}
	
	protected void partList() {
		System.out.println("\n[원자재 재고조회] 원자재 재고 조회하기");
		
		String partNo;
		
		try {
			System.out.print("재고 조회할 재료코드는?: ");
			partNo = br.readLine();
			
			List<BuyDTO> list = buydao.partlistAll(partNo);
			
			if(list==null) {
				System.out.println("유효하지 않은 재료코드입니다. ");
				return;
			}
			
			for(BuyDTO buydto : list) {
				System.out.print(buydto.getPartNo()+"\t");
				System.out.print(buydto.getPart_name()+"\t");
				System.out.print(buydto.getPart_price()+"\t");
				System.out.print(buydto.getPart_stock()+"\t");
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	

	

	
	// 반품관리
	public void menu3() {
		
		int ch;
		
		while(true) {
			try {
				
				System.out.println();
				System.out.println("-----------------------------------------------------------------");
				System.out.println("[1] 반품등록 [2] 반품취소 [3] 뒤로가기 ");
				System.out.println("-----------------------------------------------------------------");
				System.out.print("=> ");
				
				ch = Integer.parseInt(br.readLine());
				
				if(ch==3) {
					new BuyUI().menu();
				}
				
				switch(ch) {
				case 1: ; break;
				case 2: ; break;
				
				}
					
			} catch (Exception e) {
			}
			
		}
		
	}
	
	
	protected void banpumInsert() {
		
	}
	
	
	protected void banpumDelete() {
		
	}
	
	

	
	
	// 매입처관리
	public void menu4() {
		
		int ch;
		
		while(true) {
			try {
				
				System.out.println();
				System.out.println("-----------------------------------------------------------------");
				System.out.println("[1] 매입처등록 [2] 매입처수정 [3] 매입처삭제 [4] 매입처조회 [5] 뒤로가기 ");
				System.out.println("-----------------------------------------------------------------");
				System.out.print("=> ");
				
				ch = Integer.parseInt(br.readLine());
				
				if(ch==5) {
					new BuyUI().menu();
				}
				
				switch(ch) {
				case 1: shopInsert(); break;
				case 2: shopUpdate(); break;
				case 3: shopDelete(); break;
				case 4: shopList(); break;
				
				}
					
			} catch (Exception e) {
			}
			
		}
		
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
