package com.ssfms;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

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
				System.out.println("[1] 매입전표관리 [2] 매입관리 [3] 반품관리 [4] 매입요청관리 [5] 매입처관리 [6] 뒤로가기");
				System.out.println("-----------------------------------------------------------------");
				System.out.print(" => ");
				
				ch = Integer.parseInt(br.readLine());
				
				switch(ch) {
				case 1: new BuyUI().menu1(); break;
				case 2: new BuyUI().menu2(); break;
				case 3: new BuyUI().menu3(); break;
				case 4: new BuyUI().menu4(); break;
				case 5: new BuyUI().menu5(); break;
				case 6: App.main(null); break; //뒤로가기
				

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
		System.out.println("\n[매입전표등록] 매입전표 등록하기 ");
		
		try {
			EmpDTO empdto = new EmpDTO();
			AccDTO accdto = new AccDTO();

			
			System.out.print("매입 신청자 사번: ");
			empdto.setEmpNo(br.readLine());
			
			System.out.print("매입 신청 계좌코드: ");
			accdto.setAccountNo(br.readLine());
			
			System.out.print("매입 신청 금액: ");
			accdto.setAmount(Integer.parseInt(br.readLine()));
			
			System.out.print("상세 내용: ");
			accdto.setDetail(br.readLine());
			
			System.out.print("매입 신청 일자: ");
			accdto.setStateDate(br.readLine());
			
			buydao.insertAccBuy(accdto, empdto);
			
			System.out.println();
			System.out.println("[등록완료] 매입전표 등록이 완료되었습니다.");
			System.out.println("관리자 승인까지 1~2일의 기간이 소요됩니다. ");
			System.out.println("감사합니다.");
			
			
		} catch (NumberFormatException e) {
			System.out.println("금액은 숫자만 입력 가능합니다.");
		} catch (Exception e) {
			System.out.println("데이터 등록이 실패했습니다. ");
		}
		System.out.println();


	}
	
	
	
	protected void accDelete() {
		System.out.println("\n[전표취소] 등록된 전표 취소하기 ");
		
		try {
			
			AccDTO accdto = new AccDTO();
			
			System.out.print("취소할 전표일련번호: ");
			accdto.setStateNo(Integer.parseInt(br.readLine()));
			
			int result = buydao.deleteAccBuy(accdto);
			
			if(result ==0) {
				System.out.println("등록된 전표가 아니거나 승인된 전표입니다.");
				System.out.println("(취소조건 : 미승인된 구매 전표만 취소 가능) ");
			}else {
				System.out.println();
				System.out.println("[취소완료] 매입전표가 취소되었습니다.");
				System.out.println("(원자재/외상매입금 전표 모두 삭제 부탁드립니다.)");
				System.out.println("감사합니다.");
			}

			
		} catch (Exception e) {
			System.out.println("[취소실패] 전표 취소가 실패했습니다. ");
		}
		
		System.out.println();
		
		
		
	}
	
	

	
	
	protected void accList() {
		System.out.println("\n[등록전표조회] 등록된 매입전표 리스트");
		
		String accountSubNo;
		
		try {

			List<AccDTO> list = buydao.listAccBuy();
			
			if(list.size()==0) {
				System.out.println("등록된 전표 내역이 없습니다. ");
				return;
			}
			
			System.out.println("전표일련번호\t차대\t계좌코드\t계정과목명\t금액\t취소\t전표상태\t작성일자\t\t사번");
			System.out.println("------------------------------------------------------------------------------------------------------------------");
			
			for(AccDTO accdto : list) {
				System.out.print(accdto.getStateNo()+"\t");
				System.out.print(accdto.getT_account()+"\t");
				System.out.print(accdto.getAccountNo()+"\t");
				System.out.print(accdto.getAsub_name()+"\t");
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

	
	
	
	// 원자재관리
	public void menu2() {
		
		int ch;
		
		while(true) {
			try {
				
				System.out.println();
				System.out.println("-----------------------------------------------------------------");
				System.out.println("[1] 원자재 발주 [2] 발주내역조회 [3] 원자재 재고조회 [4] 신규 원자재 등록 [5] 뒤로가기 ");
				System.out.println("-----------------------------------------------------------------");
				System.out.print("=> ");
				
				ch = Integer.parseInt(br.readLine());
				
				if(ch==5) {
					new BuyUI().menu();
				}
				
				switch(ch) {
				case 1: buyInsert(); break;
				case 2: buyList(); break;
				case 3: partList(); break;
				case 4: insertPart(); break;
				
				
				}
					
			} catch (Exception e) {
			}
			
		}
		
	}
	



	protected void buyInsert() {
		System.out.println("\n[원자재 발주] 원자재 발주하기");
		System.out.println("[조건 : 전표 상태 '승인' / 계정과목 코드 '153'만 발주가능");
		
		try {
			BuyDTO buydto = new BuyDTO();

			System.out.print("발주할 전표일련번호: ");
			buydto.setStateNo(Integer.parseInt(br.readLine()));
			
			System.out.print("발주할 원자재 코드: ");
			buydto.setPartNo(br.readLine());
			
			System.out.print("발주 일시: ");
			buydto.setBuy_Date(br.readLine());
			
			System.out.print("발주 수량: ");
			buydto.setBuy_qty(Integer.parseInt(br.readLine()));
			
			System.out.print("발주 신청할 매입처코드: ");
			buydto.setShop_No(br.readLine());
			
			
			buydao.insertBuy(buydto);
			
			System.out.println();
			System.out.println("[발주완료] 원자재 발주가 완료되었습니다.");
			System.out.println("초고속 배송으로 발주 완료되었습니다. ");
			System.out.println("감사합니다.");
			
			
		} catch (NumberFormatException e) {
			System.out.println("금액 및 수량은 숫자만 입력 가능합니다.");
		} catch (Exception e) {
			System.out.println("데이터 등록이 실패했습니다. ");
		}
		System.out.println();


	}

	
	
	
	protected void buyList() {
		System.out.println("\n[발주현황조회] 발주현황 조회하기");

		System.out.println("매입번호\t전표일련번호\t재료코드\t재료명\t\t\t\t\t\t매입일자\t\t매입수량\t매입금액\t매입처코드");
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
			System.out.println(buydto.getShop_No()+"\t");
			
		}
		System.out.println();
		
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
			
			System.out.println();
			System.out.println("재료코드\t재료명\t\t\t\t\t\t재료가격\t수량");
			System.out.println("-------------------------------------------------------------------");
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

	
	
	
	private void insertPart() {
		System.out.println("\n[신규 원자재] 새로운 원자재 정보 추가하기");
		
		try {
			
			BuyDTO buydto = new BuyDTO();
			
			
			System.out.print("원자재 코드[숫자 3자리]: ");
			buydto.setPartNo("G_" + br.readLine());
			
			System.out.print("원자재명: ");
			buydto.setPart_name(br.readLine());
			
			System.out.print("원자재 단가: ");
			buydto.setPart_price(Integer.parseInt(br.readLine()));
		
				
			buydao.insertPart(buydto);
			
			System.out.println("[등록완료] 새로운 원자재가 등록되었습니다.");
			System.out.println("감사합니다.");
			
			
		} catch (Exception e) {
			System.out.println("[등록실패] 데이터 등록이 실패했습니다.");
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
				System.out.println("[1] 반품등록 [2] 반품내역조회 [3] 뒤로가기 ");
				System.out.println("-----------------------------------------------------------------");
				System.out.print("=> ");
				
				ch = Integer.parseInt(br.readLine());
				
				if(ch==3) {
					new BuyUI().menu();
				}
				
				switch(ch) {
				case 1: banpumInsert(); break;
				case 2: banpumList(); break;
				
				}
					
			} catch (Exception e) {
			}
			
		}
		
	}
	
	
	protected void banpumInsert() {
		System.out.println("\n[반품등록] 반품 원자재 등록하기 ");
		
		try {
			BuyDTO buydto = new BuyDTO();

			
			System.out.print("반품 등록할 매입 코드: ");
			buydto.setBuy_No(br.readLine());
			
			System.out.print("반품 등록일: ");
			buydto.setBan_Date(br.readLine());
			
			System.out.print("반품 신청자 사번: ");
			buydto.setEmpNo(br.readLine());
			
			System.out.print("상세 내용: ");
			buydto.setBan_Memo(br.readLine());
			
			int result = buydao.insertBanpum(buydto);
			
			
			if(result ==0) {
				System.out.println("[등록실패] 반품 신청할 매입 내역이 없습니다.");
			}else {
				System.out.println();
				System.out.println("[등록완료] 반품등록이 완료되었습니다.");
				System.out.println("감사합니다.");
			}
			
			
		} catch (Exception e) {
			System.out.println("데이터 등록이 실패했습니다. ");
		}
		System.out.println();


	}
	
	
	protected void banpumList() {
		System.out.println("\n[반품내역조회] 반품 신청 리스트 조회하기 ");
		
		System.out.println("매입코드\t반품신청일\t\t반품수량\t반품처리일\t\t상세내용\t");
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		
		List<BuyDTO> list = buydao.listBanpum();
		for(BuyDTO buydto : list) {
			System.out.print(buydto.getBan_No()+ "\t");
			System.out.print(buydto.getBan_Date()+ "\t");
			System.out.print(buydto.getBan_qty()+ "\t");
			System.out.print(buydto.getBan_Finish()+ "\t");
			System.out.println(buydto.getBan_Memo()+ "\t");

		}
		System.out.println();
		
	}
	
	
	
	//매입요청 관리
	public void menu4() {
		
		int ch;
		
		while(true) {
			try {
				
				System.out.println();
				System.out.println("-----------------------------------------------------------------");
				System.out.println("[1] 매입요청 조회 [2] 매입요청삭제 [3] 뒤로가기 ");
				System.out.println("-----------------------------------------------------------------");
				System.out.print("=> ");
				
				ch = Integer.parseInt(br.readLine());
				
				if(ch==3) {
					new BuyUI().menu();
				}
				
				switch(ch) {
				case 1: applylist(); break;
				case 2: applyDelete(); break;
				
				}
					
			} catch (Exception e) {
			}
			
		}
		
	}

	
	protected void applylist() {
	System.out.println("\n[매입요청 조회] 현재 조회되는 매입요청 조회하기 ");
		
		System.out.println("부품요청번호\t재료코드\t재료이름\t\t\t\t수량\t요청일자\t");
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		
		List<BuyDTO> list = buydao.applyList();
		for(BuyDTO buydto : list) {
			System.out.print(buydto.getPartOfferNo()+ "\t");
			System.out.print(buydto.getPartNo()+ "\t");
			System.out.print(buydto.getPart_name()+ "\t");
			System.out.print(buydto.getQty()+ "\t");
			System.out.println(buydto.getOffer_date()+ "\t");

		}
		System.out.println();
		
	}
	


	protected void applyDelete() {
		System.out.println("\n[매입요청 삭제] 처리 완료한 매입요청 삭제하기 ");
		System.out.println("(꼭 처리 완료 후 삭제해주세요.) ");
		
		try {
			
			BuyDTO buydto = new BuyDTO();
			
			System.out.print("취소할 부품요청코드: ");
			buydto.setPartOfferNo(Integer.parseInt(br.readLine()));
			
			int result = buydao.deleteApply(buydto);
			
			
			if(result ==0) {
				System.out.println("등록된 매입요청이 아니거나 이미 삭제된 요청입니다.");
			}else {
				System.out.println();
				System.out.println("[삭제완료] 매입요청이 삭제되었습니다.");
				System.out.println("감사합니다.");
			}

			
		} catch (Exception e) {
			System.out.println("[삭제실패] 매입요청 삭제가 실패했습니다. ");
		}
		
		System.out.println();
	}
	


	// 매입처관리
	public void menu5() {
		
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
			System.out.println("[등록실패] 데이터 등록이 실패했습니다.");
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
				System.out.println("[수정완료] 새로운 정보로 수정되었습니다.");
				System.out.println("감사합니다.");
			}
			
		} catch (Exception e) {
			System.out.println("[수정실패] 자료 수정에 실패했습니다.");
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
				System.out.println("[삭제완료] 삭제가 완료되었습니다!");
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
