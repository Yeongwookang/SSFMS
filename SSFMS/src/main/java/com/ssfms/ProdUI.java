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
	List<ProdDTO> ulist = new ArrayList<>();
	List<ProdDTO> plist = new ArrayList<>();

	public void menu() {

		int ch;

		while (true) {
			try {
				System.out.println("\n⚜ [생산 메인] ⚜");
				System.out.println("----------------------------------------------------------");
				System.out.println("⚜ [1] 제품관리 [2] 부품재고관리 [3] 전표관리 [4] 생산관리 [5] 돌아가기  ");
				System.out.println("----------------------------------------------------------");
				System.out.print("⚜ => ");
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
					if(App.loginEmp().getRank().equals("관리자")) {
						new Administrator().menu();
						break;
					}App.main(null);
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
				System.out.println("\n⚜ [제품 등록/삭제] ⚜");
				System.out.println("---------------------------------------------");
				System.out.println("⚜ [1] 제품등록 [2] 등록제품삭제 [3]제품목록 [4] 돌아가기 ");
				System.out.println("---------------------------------------------");
				System.out.print("⚜ => ");
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
		System.out.println("\n⚜ 제품 등록 ⚜");
		ProductDTO pdto = new ProductDTO();
		try {

			System.out.print("⚜ 제품의 등록번호를 입력해주세요.  ");
			pdto.setProductNo(br.readLine());
			System.out.print("⚜ 제품의 이름을 입력해주세요.  ");
			pdto.setProduct_name(br.readLine());
			System.out.print("⚜ 제품의 생산 가격을 입력해주세요.  ");
			pdto.setCost(Integer.parseInt(br.readLine()));
			System.out.print("⚜ 제품의 판매 가격을 입력해주세요.  ");
			pdto.setPrice(Integer.parseInt(br.readLine()));
			pdto.setStock(0);
			dao.reg_product(pdto);
			System.out.println("\n⚜ 제품 등록이 완료되었습니다. ⚜");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("⚜ 중복된 제품번호입니다. ⚜");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("⚜ 필수 입력사항을 입력하지 않았습니다. ⚜");
			}
		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void del_product() {
		System.out.println("\n⚜ 등록제품 삭제 ⚜");
		try {
			System.out.print("⚜ 삭제할 제품의 등록번호를 입력해주세요.  ");
			String productNo = br.readLine();
			dao.del_product(productNo);
			System.out.println("\n⚜ 등록된 제품이 삭제되었습니다.  ");

		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("⚜ 중복된 제품번호입니다. ⚜");
			}
			if (e.getErrorCode() == 2292) {
				System.out.println("⚜ 하위테이블에 값이 있습니다. ⚜");
			}
		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void list_product() {
		System.out.println("\n⚜ 등록제품 목록 ⚜");
		try {
			List<ProdDTO> list = new ArrayList<>();
			list = dao.list_product();

			System.out.println("⚜ 제품번호\t제품이름\t\t\t단가\t제품가격\t재고량");
			for (ProdDTO pdto : list) {
				System.out.print("⚜ " + pdto.getProductNo() + "\t");
				System.out.printf("%.20s\t", pdto.getProduct_name());
				System.out.print(pdto.getCost() + "\t");
				System.out.print(pdto.getPrice() + "\t");
				System.out.println(pdto.getStock() + "\t");
			}

		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("⚜ 중복된 제품번호입니다. ⚜");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("⚜ 필수 입력사항을 입력하지 않았습니다. ⚜");
			}
		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void partStockMenu() {
		int ch;

		while (true) {
			try {
				System.out.println("\n⚜ [부품 재고/구매요청] ⚜");
				System.out.println("-------------------------------------------------------------------");
				System.out.println("⚜ [1]부품재고확인 [2] 부품구매요청 [3] 부품구매취소 [4] 부품구매요청목록 [5] 돌아가기 ");
				System.out.println("-------------------------------------------------------------------");
				System.out.print("⚜ => ");

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
		System.out.println("\n⚜ 부품재고 확인 ⚜");
		List<BuyDTO> list = new ArrayList<>();
		try {
			list = dao.listPart();
			System.out.println("⚜ 부품번호\t부품이름\t\t\t부품가격\t재고량\t⚜");
			for (BuyDTO bdto : list) {
				System.out.print("⚜ " + bdto.getPartNo() + "\t");
				System.out.printf("%.20s\t", bdto.getPart_name());
				System.out.print(bdto.getPart_price() + "\t");
				System.out.println(bdto.getPart_stock() + "\t");
			}
			System.out.println();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("⚜ 중복된 제품번호입니다. ⚜");
			}
		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void buy_offer() {
		System.out.println("\n⚜ 부품구매 요청");
		ProdDTO pdto = new ProdDTO();
		List<ProdDTO> list = new ArrayList<>();
		try {
			String partNo;

			while (true) {
				System.out.print("⚜ 부품번호를 입력해주세요. [0을 누르면 입력종료]  ");
				partNo = br.readLine();
				pdto = dao.readPart(partNo);
				if (partNo.equals("0")) {
					break;
				}
				if (pdto == null) {
					System.out.println("\n⚜ 없는 부품입니다.");
					return;
				}
				System.out.print("⚜ 필요 갯수를 입력해주세요.  ");
				pdto.setQty(Integer.parseInt(br.readLine()));
				list.add(pdto);
			}

			dao.buyList_write(list);
			System.out.println("⚜ 부품구매 요청이 완료되었습니다. ⚜");

		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("⚜ 중복된 제품번호입니다. ⚜");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("⚜ 필수 입력사항을 입력하지 않았습니다. ⚜");
			}
		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void buy_offer_cancel() {
		System.out.println("⚜ 부품 구매요청 취소 ⚜");
		List<ProdDTO> list = new ArrayList<>();
		try {
			while (true) {
				ProdDTO pdto = new ProdDTO();
				System.out.print("⚜ 취소할 요청번호를 입력해주세요. [ 입력종료 : 0 ]  ");
				int partOfferNo = Integer.parseInt(br.readLine());
				if (partOfferNo == 0) {
					break;
				}
				pdto.setPartOfferNo(partOfferNo);
				list.add(pdto);

			}
			dao.buy_offer_cancel(list);
			System.out.println("⚜ 부품 구매요청이 취소되었습니다. ⚜");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("⚜ 중복된 제품번호입니다. ⚜");
			} else if (e.getErrorCode() == 2292) {
				System.out.println("⚜ 하위테이블에 값이 있습니다. ⚜");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("⚜ 필수 입력사항을 입력하지 않았습니다. ⚜");
			}
		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void list_buy_offer() {
		System.out.println("⚜ 부품 구매요청 목록 ⚜");
		List<ProdDTO> list = new ArrayList<>();
		try {
			list = dao.list_Buy_offer();
			System.out.println("⚜ 요청번호\t재료코드\t부품이름\t\t\t갯수\t요청일자\t⚜");
			for (ProdDTO pdto : list) {
				System.out.print("⚜ " + pdto.getPartOfferNo() + "\t");
				System.out.print(pdto.getPartNo() + "\t");
				System.out.printf("%.20s\t", pdto.getPart_name());
				System.out.print(pdto.getQty() + "\t");
				System.out.println(pdto.getOffer_date() + "\t");
			}
		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("⚜ 중복된 제품번호입니다. ⚜");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("⚜ 필수 입력사항을 입력하지 않았습니다. ⚜");
			}
		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void State() {
		int ch;

		while (true) {
			try {
				System.out.println("\n⚜ [전표등록 / 취소] ⚜");
				System.out.println("-------------------------------------------------------");
				System.out.println("⚜ [1] 생산전표등록 [2] 전표취소신청 [3] 생산부서 전표목록 [4] 돌아가기 ");
				System.out.println("-------------------------------------------------------");
				System.out.print("⚜ =>  ");
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
		System.out.println("\n⚜ 생산전표등록(차변) [제품생산]");
		try {
			AccDTO adto = new AccDTO();

			System.out.print("⚜ 사번 : ");
			adto.setEmpNo(br.readLine());
			adto.setAccountNo("60001");
			adto.setAccountSubNo("150");

			System.out.print("⚜ 금액 : ");
			adto.setAmount(Integer.parseInt(br.readLine()));

			System.out.print("⚜ 상세내용 : ");
			adto.setDetail(br.readLine());
			adto.setStateCon("미승인");

			adao.insertAccount(adto);

			System.out.println("\n⚜ 생산전표등록(대변) [부품사용]");
			adto.setAccountSubNo("1000");

			System.out.print("⚜ 상세내용 : ");
			adto.setDetail(br.readLine());
			adao.insertAccount(adto);

			System.out.println("⚜ 전표가 등록 되었습니다. ⚜");
		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("⚜ 중복된 제품번호입니다. ⚜");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("⚜ 필수 입력사항을 입력하지 않았습니다. ⚜");
			}
		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void cancel_prod_state() {
		System.out.println("\n⚜ 생산전표취소 ⚜");
		try {
			AccDTO adto = new AccDTO();

			System.out.print("⚜ 취소할 전표 번호 : ");
			int stateNo = Integer.parseInt(br.readLine());
			adto = adao.readAccount(stateNo);
			if (adto == null) {
				System.out.println("⚜ 없는 전표입니다. ⚜");
			}
			if (adto.getStateCon().equals("승인") || adto.getStateCon().equals("처리")) {
				System.out.println("⚜ [ 승인, 처리 ] 상태인 전표는 취소할수 없습니다.  ⚜");
				return;
			}
			int result = adao.deleteAccount(stateNo);
			if (result == 1) {
				System.out.println("⚜ 전표취소가 완료되었습니다. ⚜");
			} else {
				System.out.println("⚜ 전표가 취소되지 않았습니다. ⚜");
			}

		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("⚜ 중복된 제품번호입니다. ⚜");
			} else if (e.getErrorCode() == 2292) {
				System.out.println("⚜ 하위테이블에 값이 있습니다. ⚜");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("⚜ 필수 입력사항을 입력하지 않았습니다. ⚜");
			}
		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void prod_state_list() {
		System.out.println("\n⚜ 생산부서 전표리스트 ⚜");
		try {
			List<AccDTO> list = new ArrayList<>();
			list = dao.listAccount_prod();
			System.out.println("⚜ 전표번호 차대\t계좌코드\t계정과목명\t\t금액\t\t취소\t전표상태\t승인일시\t\t사번\t부서\t직급\t이름\t ⚜");
			for (AccDTO adto : list) {
				System.out.print("⚜ " + adto.getStateNo() + "\t ");
				System.out.print(adto.getT_account() + "\t");
				System.out.print(adto.getAccountNo() + "\t");
				System.out.print(adto.getAsub_name() + "\t");
				System.out.printf("%(15d ￦\t", adto.getAmount());
				System.out.print(adto.getCancellation() + "\t");
				System.out.print(adto.getStateCon() + "\t");
				System.out.print(adto.getStateDate() + "\t");
				System.out.print(adto.getEmpNo() + "\t");
				System.out.print(adto.getDep() + "\t");
				System.out.print(adto.getRank() + "\t");
				System.out.println(adto.getName());
			}
			System.out.println();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("⚜ 중복된 제품번호입니다. ⚜");
			}
		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void productionMenu() {
		int ch;

		while (true) {
			try {
				System.out.println("\n⚜ [생산관리] ⚜");
				System.out.println("\n ❗❗ 생산표와 재고사용표에는 프로그램 실행시 임시로 저장되며, ([7],[8])을 통해 기록해야 DB에 저장됩니다. ❗❗");
				System.out.println("-----------------------------------------------");
				System.out.println("⚜ [1] 생산제품입력\t[2] 생산표보기\t[3] 생산표초기화");
				System.out.println("⚜ [4] 부품사용입력\t[5] 부품사용표보기\t[6] 부품사용표초기화");
				System.out.println("⚜ [7] 생산기록\t[8] 부품사용기록\t[9] 돌아가기");
				System.out.println("-----------------------------------------------");
				System.out.print("⚜ => ");

				ch = Integer.parseInt(br.readLine());
				if (ch == 9) {
					return;
				}
				switch (ch) {
				case 1:
					producing();
					break;
				case 2:
					list_producing();
					break;
				case 3:
					plist_purge();
					break;
				case 4:
					using_part();
					break;
				case 5:
					list_usingpart();
					break;
				case 6:
					ulist_purge();
					break;
				case 7:
					production();
					break;
				case 8:
					stock_insert();
					break;
				}
			} catch (Exception e) {
			}
		}
	}

	protected void producing() {
		System.out.println("\n⚜ 생산제품 입력 (4.생산기록을 통해 기록해야 DB에 제출됩니다.) ⚜");

		try {
			String ProductNo;
			ProdDTO pdto = new ProdDTO();
			System.out.print("⚜ 승인된 전표번호를 입력해주세요.  ");
			AccDTO adto = adao.readAccount(Integer.parseInt(br.readLine()));
			if (adto == null) {
				System.out.println("⚜ 없는 전표입니다. ⚜");
				return;
			}
			if (!adto.getStateCon().equals("승인")) {
				System.out.println("⚜ 승인되지 않은 전표입니다. ⚜");
				return;
			}
			pdto.setStateNo(adto.getStateNo());

			while (true) {
				System.out.println("⚜ 생산한 제품의 코드를 입력하세요. [입력 종료: 0]  ");
				ProductNo = br.readLine();
				if (ProductNo.equals("0")) {
					break;
				}
				pdto.setProductNo(ProductNo);
				System.out.println("⚜ 생산한 갯수를 입력하세요.  ");
				pdto.setQty(Integer.parseInt(br.readLine()));
				System.out.println("⚜ 생산 단가를 입력해주세요.  ");
				pdto.setCost(Integer.parseInt(br.readLine()));
				plist.add(pdto);
			}

		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	protected void list_producing() {
		System.out.println("\n⚜ 생산표 보기 (임시저장) ⚜");
		System.out.println("⚜ 전표번호 제품번호\t수량\t비용\t ⚜");
		for (ProdDTO pdto : plist) {
			System.out.printf("⚜ %5d\t", pdto.getStateNo());
			System.out.print(pdto.getProductNo() + "\t");
			System.out.print(pdto.getQty() + "\t");
			System.out.println(pdto.getCost() + "\t");
		}
		System.out.println();
	}

	protected void plist_purge() {
		plist = new ArrayList<>();
		System.out.println("⚜ 초기화 되었습니다 ⚜");
	}

	protected void using_part() {
		ProdDTO pdto = new ProdDTO();

		System.out.println("\n⚜ 부품사용입력([8]부품사용기록을 통해 기록해야 DB에 제출됩니다.) ⚜");
		try {
			System.out.println("⚜ 가장 최근 생산번호 " + dao.readProdNo() + " ⚜");
			String partNo;
			System.out.print("⚜ 전표번호를 입력해주세요.  ");
			pdto.setStateNo(Integer.parseInt(br.readLine()));
			System.out.print("⚜ 생산번호를 입력해주세요.  ");
			pdto.setProdNo(Integer.parseInt(br.readLine()));
			while (true) {
				System.out.print("⚜ 사용한 재료의 코드를 입력해주세요. [입력 종료: 0]  ");
				partNo = br.readLine();
				if (partNo.equals("0")) {
					break;
				}
				pdto.setPartNo(partNo);
				System.out.print("⚜ 사용한 재료의 갯수를 입력해주세요.  ");
				pdto.setQty(Integer.parseInt(br.readLine()));
				ulist.add(pdto);
			}

		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();

	}

	protected void list_usingpart() {
		System.out.println("\n⚜ 생산표 보기 (임시저장) ⚜");
		System.out.println("⚜ 전표번호\t부품번호\t수량\t⚜");
		for (ProdDTO pdto : ulist) {
			System.out.print("⚜ " + pdto.getStateNo() + "\t ");
			System.out.print(pdto.getProdNo() + "\t ");
			System.out.print(pdto.getPartNo() + "\t");
			System.out.println(pdto.getQty() + "\t");
		}
		System.out.println();
	}

	protected void ulist_purge() {
		ulist = new ArrayList<>();
		System.out.println("⚜  초기화 되었습니다 ⚜");
	}

	protected void production() {
		System.out.println("\n⚜ 생산등록 (DB등록) ⚜");
		try {
			dao.producing(plist);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("⚜ 중복된 제품번호입니다. ⚜");
			} else if (e.getErrorCode() == 2292) {
				System.out.println("⚜ 하위테이블에 값이 있습니다. ⚜");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("⚜ 필수 입력사항을 입력하지 않았습니다. ⚜");
			}
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void stock_insert() {
		System.out.println("\n⚜ 생산등록 (DB등록) ⚜");
		try {
			dao.UsePart(ulist);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("⚜ 중복된 제품번호입니다. ⚜");
			} else if (e.getErrorCode() == 2292) {
				System.out.println("⚜ 하위테이블에 값이 있습니다. ⚜");
			} else if (e.getErrorCode() == 1400) {
				System.out.println("⚜ 필수 입력사항을 입력하지 않았습니다. ⚜");
			}
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("⚜ 해당 필드에는 숫자만 입력 가능합니다. ⚜");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
