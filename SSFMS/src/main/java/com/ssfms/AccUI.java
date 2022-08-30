package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
				System.out.println("========================================================");
				System.out.print("1. 전표등록 \t   2. 전표수정 \t 3. 전표삭제 \n");
				System.out.println("--------------------------------------------------------");
				System.out.print("4. 전체 전표 조회 \t   5. 사원별 전표 조회 6. 계정과목 별 전표 조회 \n"); 
				System.out.println("--------------------------------------------------------");
				System.out.print("7. 월별 매입매출 조회  8. 거래처 관리  9. 뒤로가기 \n ");		
				System.out.println("========================================================");
				ch = Integer.parseInt(br.readLine());

				if (ch == 10) {
					DBConn.close();
					return;
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
				case 4:
					listAll();
					break;
				case 5:
					findByempNo();
					break;
				case 6:
					findByname();
					break;
				case 7:
					findBycateg_Name();
					break;
				case 8:
					findBycustomer();
					break;
				case 9:
					App.main(null);
					break;
				}

			} catch (Exception e) {
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

	protected void listAll() {
		System.out.println("\n전체 전표 조회 ");
		List<AccDTO> listAll = dao.listAccount();
		for (AccDTO dto : listAll) {
			
			System.out.print(dto.getStateNo() + "\t");
			System.out.print(dto.getEmpNo() + "\t");
			System.out.print(dto.getAccountNo() + "\t");
			System.out.print(dto.getAccountSubNo() + "\t");
			System.out.print(dto.getAmount() + "\t");
			System.out.print(dto.getDetail() + "\t");
			System.out.print(dto.getCancellation() + "\t");
			System.out.print(dto.getStateCon() + "\t");
			System.out.println(dto.getStateDate());

		}
		System.out.println();
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
			for (AccDTO dto : findByempNo) {
				
				System.out.print(dto.getStateNo() + "\t");
				System.out.print(dto.getEmpNo() + "\t");
				System.out.print(dto.getAccountNo() + "\t");
				System.out.print(dto.getAccountSubNo() + "\t");
				System.out.print(dto.getAmount() + "\t");
				System.out.print(dto.getDetail() + "\t");
				System.out.print(dto.getCancellation() + "\t");
				System.out.print(dto.getStateCon() + "\t");
				System.out.println(dto.getStateDate());

			}
			System.out.println();

		} catch (Exception e) {
		}
		System.out.println();
	}

	protected void findByname() {
		System.out.println("\n계정과목코드 별 전표 조회 ");
		String categName;

		try {
			System.out.print("계정과목코드를 입력하세요. ");
			categName = br.readLine();

			List<AccDTO> findByname = dao.listAccount_subNo(categName);
			if (findByname.size() == 0) {
				System.out.println("해당 계정과목으로 등론된 전표가 존재하지 않습니다.\n");
				return;
			}

			for (AccDTO dto : findByname) {
				
				System.out.print(dto.getStateNo() + "\t");
				System.out.print(dto.getEmpNo() + "\t");
				System.out.print(dto.getAccountNo() + "\t");
				System.out.print(dto.getAccountSubNo() + "\t");
				System.out.print(dto.getAmount() + "\t");
				System.out.print(dto.getDetail() + "\t");
				System.out.print(dto.getCancellation() + "\t");
				System.out.print(dto.getStateCon() + "\t");
				System.out.println(dto.getStateDate());

			}
			System.out.println();

		} catch (Exception e) {
		}
		System.out.println();
	}

	protected void findBycateg_Name() {
		System.out.println("\n월별 매입/매출 조회 ");
		try {
			BuyDAO bdao = new BuyDAOImpl();
			List<BuyDTO>list = bdao.listBuy();
			
			
			for(BuyDTO bdto : list) {
				
				System.out.print(bdto.getBuy_No()+ "\t");	
				System.out.print(bdto.getStateNo()+ "\t");
				System.out.print(bdto.getPartNo()+ "\t");
				System.out.print(bdto.getPart_name()+ "\t");
				System.out.print(bdto.getBuy_Date()+ "\t");
				System.out.print(bdto.getBuy_qty()+ "\t");
				System.out.print(bdto.getBuy_price()+ "\t");
				System.out.print(bdto.getShop_No()+ "\t");
				
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
			
			System.out.print(bdto.getShop_No()+ "\t");
			System.out.print(bdto.getShop_Num()+ "\t");
			System.out.print(bdto.getShop_Name()+ "\t");
			System.out.print(bdto.getShop_Boss()+ "\t");
			System.out.print(bdto.getShop_Tel()+ "\t");
			System.out.print(bdto.getShop_Post()+ "\t");
			System.out.print(bdto.getShop_addr()+ "\t");
			System.out.println(bdto.getShop_Reg()+ "\t");
			
		}
		System.out.println();
		
	}

	
}
