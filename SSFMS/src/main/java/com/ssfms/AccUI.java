package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class AccUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private AccDAO dao = new AccDAOImpl();
	private BuyDAO bdao = new BuyDAOImpl();

	public void menu() {

		int ch;

		while (true) {
			try {
				System.out.print("\t\t\t[회계] \n");
				do {
					System.out.print("1.전표관리 2. 승인관리 3. 전표조회  4. 매입매출 및 거래처 관리 5. 계좌관리 6.계정과목관리 7. [이전화면] => ");
					ch = Integer.parseInt(br.readLine());
					System.out.println();
				} while (ch < 1 || ch > 7);

				switch (ch) {
				case 1:
					new AccUI().menu1();
				case 2:
					new AccUI().menu2();
				case 3:
					new AccUI().menu3();
					// case 4: new AccUI().menu5();

					// case 5: new AccUI().menu6();
				case 7:
					App.main(null);
					break;
				}

				/*
				 * System.out.println("========================================================"
				 * ); System.out.print("1. 전표등록 \t   2. 전표수정 \t 3. 전표삭제 \n");
				 * System.out.println("--------------------------------------------------------"
				 * ); System.out.print("4. 전체 전표 조회 \t   5. 사원별 전표 조회 6. 계정과목 별 전표 조회 \n");
				 * System.out.println("--------------------------------------------------------"
				 * ); System.out.print("7. 월별 매입매출 조회  8. 거래처 관리 \n");
				 * System.out.println("--------------------------------------------------------"
				 * );
				 * System.out.print("9. 계좌 등록 \t    10. 계좌 수정 \t  11. 계좌 삭제 12. 전체 계좌 조회 \n");
				 * System.out.println("--------------------------------------------------------"
				 * ); System.out.print("13. 계정과목 등록 14. 계정과목 수정 15. 계정과목 삭제. 16. 계정과목 조회 \n");
				 * System.out.println("--------------------------------------------------------"
				 * ); System.out.print("17.[이전화면으로 돌아가기]");
				 * System.out.println("========================================================"
				 * ); System.out.print("[메뉴 버튼] : \n");
				 */

			} catch (Exception e) {
			}
		}

	}

	// 전표관리
	public void menu1() {
		int ch;
		while (true) {
			try {
				do {
					System.out.print("1. 전표등록 2. 전표수정 3. 전표삭제 4. [이전화면] \n => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 4);
				System.out.println();

				if (ch == 4) {
					new AccUI().menu();
				}

				switch (ch) {
				case 1:
					insert();
					break;
				case 2:
					update();
					break;
				case 3:
					delete();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void insert() {
		System.out.println("\n전표 등록 ");
		try {
			AccDTO dto = new AccDTO();

			System.out.print("사번 : ");
			dto.setEmpNo(br.readLine());

			System.out.print("계좌코드 : ");
			dto.setAccountNo(br.readLine());

			System.out.print("계정과목코드 : ");
			dto.setAccountSubNo(br.readLine());

			System.out.print("금액 : ");
			dto.setAmount(Integer.parseInt(br.readLine()));

			System.out.print("상세내용 : ");
			dto.setDetail(br.readLine());

			System.out.print("전표상태 : ");
			dto.setStateCon(br.readLine());

			dao.insertAccount(dto);

			System.out.println("전표가 등록 되었습니다.");
		} catch (Exception e) {
			System.out.println("등록 실패하였습니다.");
		}

	}

	protected void update() {
		System.out.println("\n전표 수정 ");

		try {
			AccDTO dto = new AccDTO();

			System.out.print("수정할 사원코드 : ");
			dto.setEmpNo(br.readLine());

			System.out.print("수정할 계좌코드 : ");
			dto.setAccountNo(br.readLine());

			System.out.print("수정할 계정과목코드 : ");
			dto.setAccountSubNo(br.readLine());

			System.out.print("수정할 금액 : ");
			dto.setAmount(Integer.parseInt(br.readLine()));

			System.out.print("수정할 상세내용 : ");
			dto.setDetail(br.readLine());

			System.out.print("수정할 취소여부 : ");
			dto.setCancellation(br.readLine());

			System.out.print("수정할 전표상태 : ");
			dto.setStateCon(br.readLine());

			dao.updateAccount(dto);

			System.out.println("전표가 수정 되었습니다.");

		} catch (Exception e) {
			System.out.println("수정 실패");
		}
	}

	protected void delete() {
		System.out.println("\n전표 삭제 ");
		int stateNo;

		try {
			System.out.print("삭제할 전표 번호 ? ");
			stateNo = Integer.parseInt(br.readLine());

			dao.deleteAccount(stateNo);

			System.out.println("전표가 삭제 되었습니다.");
		} catch (Exception e) {
			System.out.println("전표 삭제 실패 !!!");
		}
		System.out.println();

	}

	// 승인관리
	public void menu2() {
		int ch;
		while (true) {
			try {
				do {
					System.out.print("1. 미승인전표 출력 2. 승인처리 3. [이전화면] \n => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 3);
				System.out.println();

				if (ch == 3) {
					new AccUI().menu();
				}

				switch (ch) {
				case 1:
					listNapproval();
					break;
				case 2:
					update_approval();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void listNapproval() {
		System.out.println("\n미승인 전표 조회 ");

		try {

			List<AccDTO> listNapproval = dao.listNapproval();

			if (listNapproval.size() == 0) {
				System.out.println("미승인된 전표가 없습니다.\n");
				return;
			}

			System.out.println("전표번호\t차대\t계좌코드\t계정과목명\t금액\t\t취소\t전표상태\t승인일시\t\t사번\t부서\t직급\t이름");
			for (AccDTO dto : listNapproval) {
				System.out.print(dto.getStateNo() + "\t");
				System.out.print(dto.getT_account() + "\t");
				System.out.print(dto.getAccountNo() + "\t");
				System.out.print(dto.getAsub_name() + "\t");
				System.out.print(dto.getAmount() + "\t\t");
				System.out.print(dto.getCancellation() + "\t");
				System.out.print(dto.getStateCon() + "\t");
				System.out.print(dto.getStateDate() + "\t");
				System.out.print(dto.getEmpNo() + "\t");
				System.out.print(dto.getDep() + "\t");
				System.out.print(dto.getRank() + "\t");
				System.out.println(dto.getName());
			}

			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	protected void update_approval() {
		int ch;
		while (true) {
			try {
				do {
					System.out.println("1. 승인할 전표 2. 전체 승인 3. 승인된 전표 목록 4. [이전화면] \n =>");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 3);
				System.out.println();

				if (ch == 3) {
					new AccUI().menu2();
				}

				switch (ch) {
				case 1:
					update_approval_N();
					break;
				case 2:
					update_approval_All();
					break;
				case 3:
					listapproval();
					break;	
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	protected void update_approval_N() {
		
		
		try {
		
				System.out.println("\n승인할 전표번호?  ");
				AccDTO dto =  dao.readAccount(Integer.parseInt(br.readLine()));

			
			
			dto.setStateCon("승인");

			dao.updateAccount(dto);

			System.out.println("전표가 승인 되었습니다.");

		} catch (Exception e) {
			System.out.println("승인 실패");
		}
	}
	
	protected void update_approval_All() {
		
	}
	
	protected void listapproval() {
		System.out.println("\n승인 전표 목록 ");

		try {

			List<AccDTO> listapproval = dao.listAccount();

			if (listapproval.size() == 0) {
				System.out.println("미승인된 전표가 없습니다.\n");
				return;
			}

			System.out.println("전표번호\t차대\t계좌코드\t계정과목명\t금액\t\t취소\t전표상태\t승인일시\t\t사번\t부서\t직급\t이름");
			for (AccDTO dto : listapproval) {
				System.out.print(dto.getStateNo() + "\t");
				System.out.print(dto.getT_account() + "\t");
				System.out.print(dto.getAccountNo() + "\t");
				System.out.print(dto.getAsub_name() + "\t");
				System.out.print(dto.getAmount() + "\t\t");
				System.out.print(dto.getCancellation() + "\t");
				System.out.print(dto.getStateCon() + "\t");
				System.out.print(dto.getStateDate() + "\t");
				System.out.print(dto.getEmpNo() + "\t");
				System.out.print(dto.getDep() + "\t");
				System.out.print(dto.getRank() + "\t");
				System.out.println(dto.getName());
			}

			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	
	
	// 전표조회
	public void menu3() {
		int ch;
		while (true) {
			try {
				do {
					System.out.print("1. 전체 전표 조회 2. 사원별 전표 조회 3. 계정과목 별 전표 조회 4. [이전화면] \n => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 4);
				System.out.println();

				if (ch == 4) {
					new AccUI().menu();
				}

				switch (ch) {
				case 1:
					listAll();
					break;
				case 2:
					findByempNo();
					break;
				case 3:
					findByasub_name();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void listAll() {
		System.out.println("\n전체 전표 조회 ");
		try {
			List<AccDTO> list = new ArrayList<>();
			list = dao.listAccount();
			System.out.println("전표번호\t차대\t계좌코드\t계정과목명\t금액\t\t취소\t전표상태\t승인일시\t\t사번\t부서\t직급\t이름");
			for (AccDTO dto : list) {
				System.out.print(dto.getStateNo() + "\t");
				System.out.print(dto.getT_account() + "\t");
				System.out.print(dto.getAccountNo() + "\t");
				System.out.print(dto.getAsub_name() + "\t");
				System.out.print(dto.getAmount() + "\t\t");
				System.out.print(dto.getCancellation() + "\t");
				System.out.print(dto.getStateCon() + "\t");
				System.out.print(dto.getStateDate() + "\t");
				System.out.print(dto.getEmpNo() + "\t");
				System.out.print(dto.getDep() + "\t");
				System.out.print(dto.getRank() + "\t");
				System.out.println(dto.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void findByempNo() {
		System.out.println("\n사원별 전표 조회 ");
		String empNo;

		try {
			System.out.print("검색할 사원 ? ");
			empNo = br.readLine();

			List<AccDTO> findByempNo = dao.listAccount_emp(empNo);

			if (findByempNo.size() == 0) {
				System.out.println("등록된 사원이 아닙니다.\n");
				return;
			}

			System.out.println("전표번호\t차대\t계좌코드\t계정과목명\t금액\t\t취소\t전표상태\t승인일시\t\t사번\t부서\t직급\t이름");
			for (AccDTO dto : findByempNo) {
				System.out.print(dto.getStateNo() + "\t");
				System.out.print(dto.getT_account() + "\t");
				System.out.print(dto.getAccountNo() + "\t");
				System.out.print(dto.getAsub_name() + "\t");
				System.out.print(dto.getAmount() + "\t\t");
				System.out.print(dto.getCancellation() + "\t");
				System.out.print(dto.getStateCon() + "\t");
				System.out.print(dto.getStateDate() + "\t");
				System.out.print(dto.getEmpNo() + "\t");
				System.out.print(dto.getDep() + "\t");
				System.out.print(dto.getRank() + "\t");
				System.out.println(dto.getName());
			}

			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	protected void findByasub_name() {
		System.out.println("\n계정과목 별 전표 조회 ");
		String asub_name;

		try {
			System.out.print("계정과목명을 입력하세요. ");
			asub_name = br.readLine();

			List<AccDTO> findByasub_name = dao.listAccount_subNo(asub_name);
			if (findByasub_name.size() == 0) {
				System.out.println("해당 계정과목으로 등론된 전표가 존재하지 않습니다.\n");
				return;
			}

			System.out.println("전표번호\t차대\t계좌코드\t계정과목명\t금액\t\t취소\t전표상태\t승인일시\t\t사번\t부서\t직급\t이름");
			for (AccDTO dto : findByasub_name) {

				System.out.print(dto.getStateNo() + "\t");
				System.out.print(dto.getT_account() + "\t");
				System.out.print(dto.getAccountNo() + "\t");
				System.out.print(dto.getAsub_name() + "\t");
				System.out.print(dto.getAmount() + "\t\t");
				System.out.print(dto.getCancellation() + "\t");
				System.out.print(dto.getStateCon() + "\t");
				System.out.print(dto.getStateDate() + "\t");
				System.out.print(dto.getEmpNo() + "\t");
				System.out.print(dto.getDep() + "\t");
				System.out.print(dto.getRank() + "\t");
				System.out.println(dto.getName());
			}
			System.out.println();

		} catch (Exception e) {
		}
		System.out.println();
	}

	public void menu4() {
		int ch;
		while (true) {
			try {
				do {
					System.out.print("1. 월별 매입매출 조회 2. 거래처 관리 3.[이전화면] \n =>");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 3);
				System.out.println();

				if (ch == 3) {
					new AccUI().menu();
				}

				switch (ch) {
				case 1:
					findBycateg_Name();
					break;
				case 2:
					findBycustomer();
					break;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void findBycateg_Name() {
		System.out.println("\n월별 매입/매출 조회 ");
		try {
			BuyDAO bdao = new BuyDAOImpl();
			List<BuyDTO> list = bdao.listBuy();

			for (BuyDTO bdto : list) {

				System.out.print(bdto.getBuy_No() + "\t");
				System.out.print(bdto.getStateNo() + "\t");
				System.out.print(bdto.getPartNo() + "\t");
				System.out.print(bdto.getPart_name() + "\t");
				System.out.print(bdto.getBuy_Date() + "\t");
				System.out.print(bdto.getBuy_qty() + "\t");
				System.out.print(bdto.getBuy_price() + "\t");
				System.out.print(bdto.getShop_No() + "\t");

			}
			System.out.println();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	protected void findBycustomer() {

		System.out.println("\n 전체 거래처 조회 ");
		BuyDAO bdao = new BuyDAOImpl();
		List<BuyDTO> list = bdao.listShop();

		for (BuyDTO bdto : list) {

			System.out.print(bdto.getShop_No() + "\t");
			System.out.print(bdto.getShop_Num() + "\t");
			System.out.print(bdto.getShop_Name() + "\t");
			System.out.print(bdto.getShop_Boss() + "\t");
			System.out.print(bdto.getShop_Tel() + "\t");
			System.out.print(bdto.getShop_Post() + "\t");
			System.out.print(bdto.getShop_addr() + "\t");
			System.out.println(bdto.getShop_Reg() + "\t");

		}
		System.out.println();

	}

	/*
	 * protected void insert_accNo() { System.out.println("\nr계좌코드 등록 "); try {
	 * AccDTO dto = new AccDTO();
	 * 
	 * System.out.print("계좌코드 : "); dto.setaccountNo(br.readLine());
	 * 
	 * System.out.print("은행명 : "); dto.setbankName(br.readLine());
	 * 
	 * System.out.print("계좌번호 : "); dto.setaccountNum(br.readLine());
	 * 
	 * System.out.print("예금주성함 : "); dto.setaccName(br.readLine());
	 * 
	 * System.out.print("거래액 : ");
	 * dto.setbusAmount(Integer.parseInt(br.readLine()));
	 * 
	 * 
	 * System.out.print("잔액 : "); dto.setbalance(Integer.parseInt(br.readLine()));
	 * 
	 * 
	 * dao.insertAccount(dto);
	 * 
	 * System.out.println("계좌가 등록 되었습니다."); } catch (Exception e) {
	 * System.out.println("등록 실패하였습니다."); }
	 * 
	 * }
	 * 
	 * 
	 * protected void update_accNo() { System.out.println("\n전표 수정 ");
	 * 
	 * try { AccDTO dto = new AccDTO();
	 * 
	 * System.out.print("수정할 사원코드 : "); dto.setEmpNo(br.readLine());
	 * 
	 * System.out.print("수정할 계좌코드 : "); dto.setAccountNo(br.readLine());
	 * 
	 * System.out.print("수정할 계정과목코드 : "); dto.setAccountSubNo(br.readLine());
	 * 
	 * System.out.print("수정할 금액 : ");
	 * dto.setAmount(Integer.parseInt(br.readLine()));
	 * 
	 * System.out.print("수정할 상세내용 : "); dto.setDetail(br.readLine());
	 * 
	 * System.out.print("수정할 취소여부 : "); dto.setCancellation(br.readLine());
	 * 
	 * System.out.print("수정할 전표상태 : "); dto.setStateCon(br.readLine());
	 * 
	 * dao.updateAccount(dto);
	 * 
	 * System.out.println("전표가 수정 되었습니다.");
	 * 
	 * } catch (Exception e) { System.out.println("수정 실패"); } }
	 * 
	 * protected void delete_accNo() { System.out.println("\n전표 삭제 "); int stateNo;
	 * 
	 * try { System.out.print("삭제할 전표 번호 ? "); stateNo =
	 * Integer.parseInt(br.readLine());
	 * 
	 * dao.deleteAccount(stateNo);
	 * 
	 * System.out.println("전표가 삭제 되었습니다."); } catch (Exception e) {
	 * System.out.println("전표 삭제 실패 !!!"); } System.out.println();
	 * 
	 * }
	 */
}
