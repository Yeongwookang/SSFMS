package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
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
				System.out.println("[생산 메인]");
				System.out.println("1. 제품관리 2. 부품재고관리 3. 전표관리 4. 생산관리 5. 돌아가기");
				ch = Integer.parseInt(br.readLine());
				switch (ch) {
				case 1:
					productMenu();
					break;
				case 2:
					partStockMenu();
					break;
				case 3:
					State();
					break;
				case 4:
					productionMenu();
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
				System.out.println("1. 제품등록 2. 등록제품삭제 3.제품목록 4. 돌아가기 ");
				ch = Integer.parseInt(br.readLine());
				if (ch == 4) {
					return;
				}
				switch (ch) {
				case 1:
					reg_product();
					break;
				case 2:
					del_product();
					break;
				case 3:
					list_product();
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

	protected void list_product() {
		System.out.println("\n등록제품 목록");
		try {
			List<ProdDTO> list = new ArrayList<>();
			list = dao.list_product();
			System.out.println("제품번호\t제품이름\t\t\t단가\t제품가격\t재고량");
			for (ProdDTO pdto : list) {
				System.out.print(pdto.getProductNo() + "\t");
				System.out.printf("%.20s\t", pdto.getProduct_name());
				System.out.print(pdto.getCost() + "\t");
				System.out.print(pdto.getPrice() + "\t");
				System.out.println(pdto.getStock() + "\t");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void partStockMenu() {
		int ch;

		while (true) {
			try {
				System.out.println("1.부품재고확인 2. 부품구매요청 3. 부품구매취소 4.부품구매요청목록 5. 돌아가기 ");
				ch = Integer.parseInt(br.readLine());
				if (ch == 5) {
					return;
				}
				switch (ch) {
				case 1:
					stock();
					break;
				case 2:
					buy_offer();
					break;
				case 3:
					buy_offer_cancel();
					break;
				case 4:
					list_buy_offer();
					break;

				}
			} catch (Exception e) {
			}
		}
	}

	protected void stock() {
		System.out.println("\n 부품재고 확인");
		List<BuyDTO> list = new ArrayList<>();
		try {
			list = dao.listPart();
			System.out.println("부품번호\t부품이름\t\t\t부품가격\t재고량");
			for (BuyDTO bdto : list) {
				System.out.print(bdto.getPartNo() + "\t");
				System.out.printf("%.20s\t", bdto.getPart_name());
				System.out.print(bdto.getPart_price() + "\t");
				System.out.println(bdto.getPart_stock() + "\t");
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void buy_offer() {
		System.out.println("\n 부품구매 요청");
		ProdDTO pdto = new ProdDTO();
		List<ProdDTO> list = new ArrayList<>();
		try {
			String partNo;

			while (true) {
				System.out.println("부품번호를 입력해주세요. [0을 누르면 입력종료]");
				partNo = br.readLine();
				pdto = dao.readPart(partNo);
				if (partNo.equals("0")) {
					break;
				}
				if (pdto == null) {
					System.out.println("없는 부품입니다.");
					return;
				}
				System.out.println("필요 갯수를 입력해주세요.");
				pdto.setQty(Integer.parseInt(br.readLine()));
				list.add(pdto);
			}

			dao.buyList_write(list);
			System.out.println("부품구매 요청이 완료되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void buy_offer_cancel() {
		System.out.println("부품 구매요청 취소");
		List<ProdDTO> list = new ArrayList<>();
		try {
			while (true) {
				ProdDTO pdto = new ProdDTO();
				System.out.println("취소할 요청번호를 입력해주세요. [ 입력종료 : 0 ]");
				int partOfferNo = Integer.parseInt(br.readLine());
				if (partOfferNo == 0) {
					break;
				}
				pdto.setPartOfferNo(partOfferNo);
				list.add(pdto);

			}
			dao.buy_offer_cancel(list);
			System.out.println("부품 구매요청이 취소되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void list_buy_offer() {
		System.out.println("부품 구매요청 목록");
		List<ProdDTO> list = new ArrayList<>();
		try {
			list = dao.list_Buy_offer();
			System.out.println("요청번호\t재료코드\t부품이름\t\t\t갯수\t요청일자");
			for (ProdDTO pdto : list) {
				System.out.print(pdto.getPartOfferNo() + "\t");
				System.out.print(pdto.getPartNo() + "\t");
				System.out.printf("%.20s\t", pdto.getPart_name());
				System.out.print(pdto.getQty() + "\t");
				System.out.println(pdto.getOffer_date() + "\t");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void State() {
		int ch;

		while (true) {
			try {
				System.out.println("1. 생산전표등록 2. 전표취소신청 3. 생산부서 전표목록 4. 돌아가기 ");
				ch = Integer.parseInt(br.readLine());
				if (ch == 4) {
					return;
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
			int stateNo = Integer.parseInt(br.readLine());
			adto = adao.readAccount(stateNo);
			if (adto.getStateCon().equals("승인") || adto.getStateCon().equals("처리")) {
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
			list = dao.listAccount_prod();
			System.out.println("전표번호\t차대\t계좌코드\t계정과목명\t금액\t\t취소\t전표상태\t승인일시\t\t\t사번\t부서\t직급\t이름");
			for (AccDTO adto : list) {
				System.out.print(adto.getStateNo() + "\t");
				System.out.print(adto.getT_account() + "\t");
				System.out.print(adto.getAccountNo() + "\t");
				System.out.print(adto.getAsub_name() + "\t");
				System.out.print(adto.getAmount() + "\t\t");
				System.out.print(adto.getCancellation() + "\t");
				System.out.print(adto.getStateCon() + "\t");
				System.out.print(adto.getStateDate() + "\t");
				System.out.print(adto.getEmpNo() + "\t");
				System.out.print(adto.getDep() + "\t");
				System.out.print(adto.getRank() + "\t");
				System.out.println(adto.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void productionMenu() {
		int ch;

		while (true) {
			try {
				System.out.println("1. 생산 2. 재고사용표 3. 돌아가기 ");
				ch = Integer.parseInt(br.readLine());
				if (ch == 3) {
					return;
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
