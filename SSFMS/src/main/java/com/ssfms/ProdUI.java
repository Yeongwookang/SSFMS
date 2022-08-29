package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProdUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private ProdDAO dao = new ProdDAOImpl();
	private AccDAO adao = new AccDAOImpl();


	public void menu() {

		int ch;

		while (true) {
			try {
				System.out.println("1. 제품관리 2. 부품재고관리 3. 제품재고관리 4. 전표관리 5. 돌아가기");
				ch = Integer.parseInt(br.readLine());
				switch (ch) {
				case 1:
					productMenu();
					break;
				case 2:
					partStockMenu();
					break;
				case 3:
					productStockMenu();
					break;
				case 4:
					State();
					break;
				case 5:
					App.main(null);
					break;

				}
			} catch (Exception e) {
			}
		}

	}

	public void productMenu() {
		int ch;

		while (true) {
			try {
				System.out.println("1. 제품등록 2. 등록제품삭제 3. 돌아가기 ");
				ch = Integer.parseInt(br.readLine());
				if (ch == 3) {
					new ProdUI().menu();
				}
				switch (ch) {
				case 1:
					reg_product();
					break;
				case 2:
					del_product();
					break;
				}
			} catch (Exception e) {
			}
		}
	}

	protected void reg_product() {
		System.out.println("\n제품 등록");
		ProductDTO pdto = new ProductDTO();
		try {
			System.out.println("제품의 등록번호를 입력해주세요.");
			pdto.setProductNo(br.readLine());
			System.out.println("제품의 이름을 입력해주세요.");
			pdto.setProduct_name(br.readLine());
			System.out.println("제품의 생산 가격을 입력해주세요.");
			pdto.setCost(Integer.parseInt(br.readLine()));
			System.out.println("제품의 판매 가격을 입력해주세요.");
			pdto.setPrice(Integer.parseInt(br.readLine()));
			pdto.setStock(0);
			dao.reg_product(pdto);
			System.out.println("\n제품 등록이 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void del_product() {
		System.out.println("\n등록제품 삭제");
		try {
			System.out.println("삭제할 제품의 등록번호를 입력해주세요.");
			String productNo = br.readLine();
			dao.del_product(productNo);
			System.out.println("\n등록된 제품이 삭제되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void partStockMenu() {
		int ch;

		while (true) {
			try {
				System.out.println("1.부품재고확인 2. 부품구매요청 3. 돌아가기 ");
				ch = Integer.parseInt(br.readLine());
				if (ch == 3) {
					new ProdUI().menu();
				}
				switch (ch) {
				case 1:
					stock();
					break;
				case 2:
					buy_offer();
					break;
				}
			} catch (Exception e) {
			}
		}
	}
	protected void stock() {
		System.out.println("\n 부품재고 확인");
		
		
	}
	protected void buy_offer() {
		System.out.println("\n 부품구매 요청");
		
	} 


	public void State() {
		int ch;

		while (true) {
			try {
				System.out.println("1. 생산전표등록 2. 전표취소신청 3. 생산부서 전표목록 4. 돌아가기 ");
				ch = Integer.parseInt(br.readLine());
				if (ch == 4) {
					new ProdUI().menu();
				}
				switch (ch) {
				case 1:
					insert_Prod_state();
					break;
				case 2:
					cancel_prod_state();
					break;
				case 3:
					prod_state_list();
					break;
				
				}
			} catch (Exception e) {
			}
		}
	}
	protected void insert_Prod_state() {
		System.out.println("\n생산전표등록");
		try {
			AccDTO adto = new AccDTO(); 
			
			System.out.print("사번 : ");
			adto.setEmpNo(br.readLine());
			
			System.out.print("계좌코드 : [기본 계좌 60001]");
			adto.setAccountNo(br.readLine());
			
			System.out.print("계정과목코드 : [ 기본 101 ]");
			adto.setAccountSubNo(br.readLine());
			
			System.out.print("금액 : ");
			adto.setAmount(Integer.parseInt(br.readLine()));
			
			System.out.print("상세내용 : ");
			adto.setDetail(br.readLine());
			
			adao.insertAccount(adto);
			
			System.out.println("전표가 등록 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected void cancel_prod_state() {
		System.out.println("\n생산전표취소");
		try {
			AccDTO adto = new AccDTO(); 
			
			System.out.print("취소할 전표 번호 : ");
			int stateNo=Integer.parseInt(br.readLine());
			adto=adao.readAccount(stateNo);
			if(adto.getStateCon().equals("승인")||adto.getStateCon().equals("처리")) {
				System.out.println("[ 승인, 처리 ] 상태인 전표는 취소할수 없습니다.");
				return;
			}
			adao.deleteAccount(stateNo);
			System.out.println("전표취소가 완료되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	protected void prod_state_list() {
		System.out.println("\n생산부서 전표리스트");
		try {
			List<AccDTO> list = new ArrayList<>();
			list=dao.listAccount_prod();
			System.out.println("전표번호\t차대\t계좌코드\t계정과목명\t금액\t\t취소\t전표상태\t승인일시\t\t\t사번\t부서\t직급\t이름");
			for(AccDTO adto : list) {
				System.out.print(adto.getStateNo() + "\t");
				System.out.print(adto.getT_account()+"\t");
				System.out.print(adto.getAccountNo() + "\t");
				System.out.print(adto.getAsub_name() + "\t");
				System.out.print(adto.getAmount() + "\t\t");
				System.out.print(adto.getCancellation() + "\t");
				System.out.print(adto.getStateCon() + "\t");
				System.out.print(adto.getStateDate()+ "\t" );
				System.out.print(adto.getEmpNo() + "\t");
				System.out.print(adto.getDep()+ "\t");
				System.out.print(adto.getRank()+ "\t");
				System.out.println(adto.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void productStockMenu() {
		int ch;

		while (true) {
			try {
				System.out.println("1. 제품재고리스트 2. 재고사용표 3. 돌아가기 ");
				ch = Integer.parseInt(br.readLine());
				if (ch == 3) {
					new ProdUI().menu();
				}
				switch (ch) {
				case 1:
					reg_product();
					break;
				case 2:
					del_product();
					break;
				}
			} catch (Exception e) {
			}
		}
	}




	protected void using_part() {
		ProdDTO pdto = new ProdDTO();
		System.out.println("\n생산 출고");
		try {
			String partNo;
			System.out.println("생산코드를 입력해주세요.");
			pdto.setProdNo(br.readLine());
			while (true) {
				System.out.println("사용한 재료의 코드를 입력해주세요. [입력 종료: 0]");
				partNo = br.readLine();
				if (partNo.equals("0")) {
					break;
				}
				pdto.setPartNo(partNo);
				System.out.println("사용한 재료의 갯수를 입력해주세요.");
				pdto.setQty(Integer.parseInt(br.readLine()));
				dao.using_part(pdto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void Producing() {
		System.out.println("\n부품관리");
		try {
			String ProductNo;
			ProdDTO pdto = new ProdDTO();
			System.out.println("승인된 전표번호를 입력해주세요.");
			AccDTO adto = adao.readAccount(Integer.parseInt(br.readLine()));
			pdto.setStateNo(adto.getStateNo());

			while (true) {
				System.out.println("생산한 제품의 코드를 입력하세요. [입력 종료: 0]");
				ProductNo = br.readLine();
				if (ProductNo.equals("0")) {
					break;
				}
				pdto.setProductNo(ProductNo);
				System.out.println("생산한 갯수를 입력하세요.");
				pdto.setQty(Integer.parseInt(br.readLine()));
				System.out.println("생산 단가를 입력해주세요.");
				pdto.setCost(Integer.parseInt(br.readLine()));
				dao.producing(pdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	protected void listAll() {
		System.out.println("\n");

	}
}
