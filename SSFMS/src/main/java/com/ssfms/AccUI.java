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
	private AccDAO adao = new AccDAOImpl();
	private AccDAO sdao = new AccDAOImpl();

	public void menu() {

		int ch;

		while (true) {
			try {
				System.out.println("⚜ [회계 메인] ⚜");
				do {
					System.out.println("----------------------------------------------------------");
					System.out.println("⚜ [1] 전표관리 [2] 승인관리 [3] 전표조회 [4] 월 매입/매출 조회   ");
					System.out.println("⚜ [5] 계좌관리 [6] 계정과목관리 [7] 돌아가기 ");
					System.out.println("----------------------------------------------------------");
					System.out.print("⚜ => ");
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
				case 4:
					new AccUI().menu4();
				case 5:
					new AccUI().menu5();
				case 6:
					new AccUI().menu6();
				case 7:
					if(App.loginEmp().getRank().equals("관리자")) {
						new Administrator().menu();
						break;
					}
					App.main(null);
					break;
				}

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
					System.out.println("\n⚜ 전표관리 ⚜");
					System.out.println("---------------------------------------------");
					System.out.println("⚜ [1] 전표등록 [2] 전표수정 [3] 전표삭제 [4] 돌아가기 ");
					System.out.println("---------------------------------------------");
					System.out.print("⚜ => ");
					
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
		System.out.println("\n⚜ 전표 등록 ⚜");
		
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
		System.out.println("\n⚜ 전표 수정 ⚜");

		try {
			AccDTO dto = new AccDTO();

			System.out.println("⚜ 수정할 전표 번호 ? ⚜");
			System.out.println("⚜ => ");
			
			dto.setStateNo(Integer.parseInt(br.readLine()));

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
			
			System.out.println("⚜ 삭제할 전표 번호 ? ⚜");
			System.out.println("⚜ => ");
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
					System.out.println("\n⚜ 승인관리 ⚜");
					System.out.println("---------------------------------------------------------");
					System.out.println("⚜ [1] 미승인전표 조회 [2] 승인처리 [3] 승인된전표 조회 [4] 돌아가기 ⚜");
					System.out.println("---------------------------------------------------------");
					System.out.print("⚜ => ");
					
				
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 4);
				System.out.println();

				if (ch == 4) {
					new AccUI().menu();
				}

				switch (ch) {
				case 1:
					listNapproval();
					break;
				case 2: 
					update_approval();
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

	protected void listNapproval() {
		System.out.println("\n⚜ 미승인 전표 조회 ⚜");

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
				System.out.printf("%10d\t", dto.getAmount());
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

	//승인하기
	protected void update_approval() {
		List<AccDTO>list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				AccDTO dto = new AccDTO();
				
				while(true) {
					System.out.println("\n⚜ 승인할 전표 번호 ?  [0 : 종료] ⚜");
					System.out.println("⚜ => ");
					int stateNo = Integer.parseInt(br.readLine());
					
					if(stateNo == 0) {
						break;
					}
				
					dto = dao.readAccount(stateNo);
					list.add(dto);
				}	
				
				dao.ListApproval(list);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
	}
	

// 한번에 승인

	protected void listapproval() {
		System.out.println("\n⚜ 승인 전표 목록 ⚜");

		try {

			List<AccDTO> listapproval = dao.listapproval();

			if (listapproval.size() == 0) {
				System.out.println("승인된 전표가 없습니다.\n");
				return;
			}

			System.out.println("전표번호\t차대\t계좌코드\t계정과목명\t금액\t\t취소\t전표상태\t승인일시\t\t사번\t부서\t직급\t이름");
			System.out.println("=============================================================================");
			for (AccDTO dto : listapproval) {
				System.out.print(dto.getStateNo() + "\t");
				System.out.print(dto.getT_account() + "\t");
				System.out.print(dto.getAccountNo() + "\t");
				System.out.print(dto.getAsub_name() + "\t");
				System.out.printf("%10d\t", dto.getAmount());
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
					System.out.println("\n⚜ 전표조회 ⚜");
					System.out.println("------------------------------------------------------");
					System.out.println("⚜ [1] 전체 전표 [2] 사원별 조회 [3] 계정과목별 조회 [4] 돌아가기 ");
					System.out.println("------------------------------------------------------");
					System.out.print("⚜ => ");

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
		System.out.println("\n⚜ 전체 전표 조회 ⚜");
		
		try {
			List<AccDTO> list = new ArrayList<>();
			list = dao.listAccount();
			System.out.println("전표번호\t차대\t계좌코드\t계정과목명\t금액\t\t취소\t전표상태\t비고\t승인일시\t\t사번\t부서\t직급\t이름");
			for (AccDTO dto : list) {
				System.out.print(dto.getStateNo() + "\t");
				System.out.print(dto.getT_account() + "\t");
				System.out.print(dto.getAccountNo() + "\t");
				System.out.print(dto.getAsub_name() + "\t");
				System.out.printf("%10d\t", dto.getAmount());
				System.out.print(dto.getDetail() + "\t");
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
		System.out.println("\n⚜ 사원별 전표 조회 ⚜");
		
		String empNo;

		try {
			System.out.println("\n⚜ 검색할 사원 코드 ?  ⚜");
			System.out.print("⚜ => ");
			
			empNo = br.readLine();

			List<AccDTO> findByempNo = dao.listAccount_emp(empNo);

			if (findByempNo.size() == 0) {
				System.out.println("등록된 사원이 아닙니다.\n");
				return;
			}

			System.out.println("전표번호\t차대\t계좌코드\t계정과목명\t금액\t\t취소\t전표상태\t비고\t승인일시\t\t사번\t부서\t직급\t이름");
			for (AccDTO dto : findByempNo) {
				System.out.print(dto.getStateNo() + "\t");
				System.out.print(dto.getT_account() + "\t");
				System.out.print(dto.getAccountNo() + "\t");
				System.out.print(dto.getAsub_name() + "\t");
				System.out.printf("%10d\t", dto.getAmount());
				System.out.print(dto.getDetail() + "\t");
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
	
		String asub_name;

		try {
			System.out.println("\n⚜ 조회할 계정과목명 ?  ⚜");
			System.out.print("⚜ => ");
			
			asub_name = br.readLine();

			List<AccDTO> findByasub_name = dao.listAccount_subNo(asub_name);
			if (findByasub_name.size() == 0) {
				System.out.println("해당 계정과목으로 등론된 전표가 존재하지 않습니다.\n");
				return;
			}

			System.out.println("전표번호\t차대\t계좌코드\t계정과목명\t금액\t\t취소\t전표상태\t비고\t승인일시\t\t사번\t부서\t직급\t이름");
			for (AccDTO dto : findByasub_name) {

				System.out.print(dto.getStateNo() + "\t");
				System.out.print(dto.getT_account() + "\t");
				System.out.print(dto.getAccountNo() + "\t");
				System.out.print(dto.getAsub_name() + "\t");
				System.out.printf("%10d\t", dto.getAmount());
				System.out.print(dto.getDetail() + "\t");
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

	// 매입매출 및 거래처 관리
	public void menu4() {
		int ch;
		while (true) {
			try {
				do {
					System.out.println("\n⚜ 기간별 매입/매출 조회 및 거래처 관리 ⚜");
					System.out.println("---------------------------------------------");
					System.out.println("⚜ [1] 월별 매입매출 조회 [2] 거래처 관리 [3] 돌아가기 ");
					System.out.println("---------------------------------------------");
					System.out.print("⚜ => ");

		

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
		System.out.println("\n⚜ 월별 매입/매출 조회 ⚜");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.println("\n⚜ 조회할 월 ?  ⚜");
			System.out.print("⚜ => ");
			int month = Integer.parseInt(br.readLine());
			
			List<AccDTO> list = dao.sblist(month);
			
			System.out.println("전표번호\t차대\t계좌코드\t계정과목명\t금액\t\t취소\t전표상태\t비고\t승인일시\t\t사번\t부서\t직급\t이름");
			System.out.println("=============================================================================");

			for (AccDTO dto : list) {

				System.out.print(dto.getStateNo() + "\t");
				System.out.print(dto.getT_account() + "\t");
				System.out.print(dto.getAccountNo() + "\t");
				System.out.print(dto.getAsub_name() + "\t");
				System.out.print(dto.getAmount() + "\t");
				System.out.print(dto.getDetail() + "\t");
				System.out.print(dto.getCancellation() + "\t");
				System.out.print(dto.getStateCon() + "\t");
				System.out.print(dto.getStateDate() + "\t");
				System.out.print(dto.getEmpNo() + "\t");
				System.out.print(dto.getDep() + "\t");
				System.out.print(dto.getRank() + "\t");
				System.out.println(dto.getName() + "\t");
				
			}
			System.out.println();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	protected void findBycustomer() {

		System.out.println("\n⚜ 전체 거래처 조회 ⚜");
		
		BuyDAO bdao = new BuyDAOImpl();
		List<BuyDTO> list = bdao.listShop();

		System.out.println("매입처코드\t사업자등록번호\t상호명\t\t대표명\t전화번호\t\t우편번호\t주소\t\t\t등록일자\t");
		
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

	// 계좌관리
	public void menu5() {
		int ch;
		while (true) {
			try {
				do {
					System.out.println("\n⚜ 은행계좌관리 ⚜");
					System.out.println("---------------------------------------------------------");
					System.out.println("⚜ [1] 계좌등록 [2] 계좌수정 [3] 계좌삭제 [4] 계좌리스트 [5] 돌아가기 ");
					System.out.println("---------------------------------------------------------");
					System.out.print("⚜ => ");

					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 5);
				System.out.println();

				if (ch == 5) {
					new AccUI().menu();
				}

				switch (ch) {
				case 1:
					insertAccountNo();
					break;
				case 2:
					updateAccountNo();
					break;

				case 3:
					deleteAccountNo();
					break;

				case 4:
					listaccountNo();
					break;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void insertAccountNo() {
		System.out.println("\n⚜ 계좌 등록 ⚜");

		try {
			AccDTO adto = new AccDTO();

			System.out.print("계좌코드 : ");
			adto.setAccountNo(br.readLine());

			System.out.print("은행명 : ");
			adto.setBankName(br.readLine());

			System.out.print("계좌번호 : ");
			adto.setAccountNum(br.readLine());

			System.out.print("예금주 : ");
			adto.setName(br.readLine());

			System.out.print("거래액 : ");
			adto.setBusAmount(Integer.parseInt(br.readLine()));

			System.out.print("잔액 : ");
			adto.setBalance(Integer.parseInt(br.readLine()));

			dao.insertAccountNo(adto);

			System.out.println("계좌정보가 등록 되었습니다.");
		} catch (Exception e) {
			System.out.println("등록 실패하였습니다.");
		}

	}

	protected void updateAccountNo() {
		System.out.println("\n⚜ 계좌 수정 ⚜");
		
		try {
			AccDTO adto = new AccDTO();

			System.out.println("\n⚜ 수정할 계좌코드 ?  ⚜");
			System.out.print("⚜ => ");
			adto.setAccountNo((br.readLine()));

			System.out.print("은행명 : ");
			adto.setBankName(br.readLine());

			System.out.print("계좌번호 : ");
			adto.setAccountNum(br.readLine());

			System.out.print("예금주 : ");
			adto.setName(br.readLine());

			System.out.print("거래액 : ");
			adto.setBusAmount(Integer.parseInt(br.readLine()));

			System.out.print("잔액 : ");
			adto.setBalance(Integer.parseInt(br.readLine()));

			dao.updateAccountNo(adto);

			System.out.println("계좌정보가 수정 되었습니다.");

		} catch (Exception e) {
			System.out.println("수정 실패");
		}
	}

	protected void deleteAccountNo() {
		System.out.println("\n⚜ 계좌 삭제 ⚜");
		int accountNo;

		try {
			System.out.println("\n⚜ 삭제할 계좌코드 ?  ⚜");
			System.out.print("⚜ => ");
			accountNo = Integer.parseInt(br.readLine());

			sdao.deleteAccountNo(accountNo);

			System.out.println("계좌정보가 삭제 되었습니다.");
		} catch (Exception e) {
			System.out.println("계좌정보 삭제 실패 !!!");
		}
		System.out.println();

	}

	protected void listaccountNo() {
		System.out.println("\n⚜ 전체 계좌 조회 ⚜");
		
		try {
			List<AccDTO> list = new ArrayList<>();
			list = sdao.listAccountNo();
			System.out.println("계좌코드\t은행명\t\t 계좌번호 \t\t예금주\t거래액\t잔액");
			for (AccDTO adto : list) {
				System.out.print(adto.getAccountNo() + "\t");
				System.out.print(adto.getBankName() + "\t");
				System.out.printf("%20s\t",adto.getAccountNum());
				System.out.print(adto.getName() + "\t");
				System.out.print(adto.getBusAmount() + "\t");
				System.out.println(adto.getBalance() + "\t");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 계정과목관리
	public void menu6() {

		int ch;
		while (true) {
			try {
				do {
					System.out.println("\n⚜ 계정과목관리 ⚜");
					System.out.println("----------------------------------------------------------------------");
					System.out.println("⚜ [1] 계정과목 등록 [2] 계정과목 수정 [3] 계정과목 삭제 [4] 계정과목 조회 [5] 돌아가기 ");
					System.out.println("----------------------------------------------------------------------");
					System.out.print("⚜ => ");
					
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 5);
				System.out.println();

				if (ch == 5) {
					new AccUI().menu();
				}

				switch (ch) {
				case 1:
					insertAccSub();
					break;
				case 2:
					updateAccSub();
					break;

				case 3:
					deleteAccSub();
					break;

				case 4:
					listAccSub();
					break;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void insertAccSub() {
		System.out.println("\n⚜ 계정과목 등록 ⚜");

		try {
			AccDTO sdto = new AccDTO();

			System.out.print("계정과목코드 : ");
			sdto.setAccountSubNo(br.readLine());

			System.out.print("계정과목명 : ");
			sdto.setAsub_name(br.readLine());

			System.out.print("분류코드 : ");
			sdto.setCategNo(br.readLine());

			dao.insertAccSub(sdto);

			System.out.println("계좌정보가 등록 되었습니다.");
		} catch (Exception e) {
			System.out.println("등록 실패하였습니다.");
		}

	}

	protected void updateAccSub() {
		
		System.out.println("\n⚜ 계정과목 수정 ⚜");

		try {
			AccDTO sdto = new AccDTO();

			System.out.println("\n⚜ 수정할 계정코드 ?  ⚜");
			System.out.print("⚜ => ");
			sdto.setAccountSubNo(br.readLine());

			System.out.print("계정과목명 : ");
			sdto.setAsub_name(br.readLine());

			System.out.print("분류코드 : ");
			sdto.setCategNo(br.readLine());

			dao.updateAccSub(sdto);

			System.out.println("계정과목이 수정 되었습니다.");

		} catch (Exception e) {
			System.out.println("수정 실패");
		}
	}

	protected void deleteAccSub() {
		System.out.println("\n⚜ 계정과목 삭제 ⚜");
		int accountNo;

		try {
			System.out.println("\n⚜ 삭제할 계좌코드 ?  ⚜");
			System.out.print("⚜ => ");
			accountNo = Integer.parseInt(br.readLine());

			sdao.deleteAccSub(accountNo);

			System.out.println("계정과목이 삭제 되었습니다.");
		} catch (Exception e) {
			System.out.println("계정과목 삭제 실패 !!!");
		}
		System.out.println();

	}

	protected void listAccSub() {
		System.out.println("\n⚜ 전체 계정과목 조회 ⚜");
		try {
			List<AccDTO> list = new ArrayList<>();
			list = sdao.listAccSub();
			System.out.println("계정코드\t계정과목명\t 분류코드");
			for (AccDTO sdto : list) {
				System.out.print(sdto.getAccountSubNo() + "\t");
				System.out.print(sdto.getAsub_name() + "\t");
				System.out.println(sdto.getCategNo() + " \t");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
