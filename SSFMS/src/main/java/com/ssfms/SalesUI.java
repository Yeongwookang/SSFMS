package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.util.DBConn;

public class SalesUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private SalesDAO dao = new SalesDAOImpl();

	public void menu() {
		int ch;

		while (true) {
			try {
				System.out.print("[영업부] 1.견적/주문처리 2.출고 3.반품 4.배송관리 5.영업이익조회 6.세금계산서 7.수금/채권관리 8.메인으로 돌아가기 =>");
				ch = Integer.parseInt(br.readLine());

				switch (ch) {
				case 1:
					orderProcessing();
					break;
				case 2:
					release();
					break;
				case 3:
					banpum();
					break;
				case 4:
					shippingManagement();
					break;
				case 5:
					operatingProfit();
					break;
				case 6:
					taxBill();
					break;
				case 7:
					moneyBondManage();
					break;
				case 8:
					App.main(null);
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	protected void orderProcessing() {
		int ch;
		while (true) {
			try {
				System.out.println("\n[견적/주문관리] 1.견적서입력 2.견적서조회 3.주문서조회 4.주문서관리 5.영업부 메뉴로 돌아가기=> ");
				ch = Integer.parseInt(br.readLine());

				if (ch == 5) {
					SalesUI.this.menu();
				}

				switch (ch) {
				case 1:
					estimateInsert();
					break;
				case 2:
					estimateCheck();
					break;
				case 3:
					orderCheck();
					break;
				case 4:
					manage();
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println();
		}
	}

	private void estimateInsert() {
		System.out.println("[견적서 입력하세요]");
		// 견적서는 입력과 조회만 가능. 입력후 30일까지만 보관하고 30일이 지나면 삭제

		try {
			SalesDTO dto = new SalesDTO();

			System.out.println("견적서일련번호 : ");

			System.out.println("사업자등록번호 : ");

			System.out.println("회사명 : ");

			System.out.println("회사연락처 : ");

			System.out.println("주문처 : ");

			System.out.println("담당자 : ");

			System.out.println("주문처연락처 : ");

			for (int i = 0; i < 10; i++) {
				System.out.println("제품명 : ");

				System.out.println("수량 : ");

				/*
				 * 
				 * System.out.println("단가 : ");
				 * 
				 * System.out.println("공급가액");
				 * 
				 * System.out.println("세액 : ");
				 * 
				 * System.out.println("비고 : ");
				 */
			}

			System.out.println("결제방식 : ");

			dao.estimateInsertSales(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void estimateCheck() {
		System.out.println("[견적서 조회]");

		try {
			System.out.println("조회할 견적서의 일련번호 입력 : ");
		} catch (Exception e) {

		}
	}

	private void orderCheck() {

	}

	private void manage() {
		System.out.println("\n[주문서 관리]");

		try {
			System.out.println("[취소할 전표의 일련번호를 입력해주세요] => ");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void release() {
		int ch;

		try {
			System.out.println("\n[출고] 1.출고등록 2.출고조회 3.영업부메뉴로 돌아가기 => ");
			ch = Integer.parseInt(br.readLine());

			if (ch == 3) {
				SalesUI.this.menu();
			}

			switch (ch) {
			case 1:
				releaseInsert();
				break;
			case 2:
				releaseCheck();
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void releaseCheck() {
		System.out.println("[출고등록]");

		try {

			System.out.println("주문서 일련번호 입력 > ");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void releaseInsert() {
		System.out.println("[출고조회]");

		System.out.println("주문서 일련번호 입력 > ");

	}

	protected void banpum() {
		int ch;

		try {
			System.out.println("\n[반품] 1.반품등록 2.반품조회 3.영업부 메뉴로 돌아가기 => ");
			ch = Integer.parseInt(br.readLine());

			if (ch == 3) {
				SalesUI.this.menu();
			}

			switch (ch) {
			case 1:
				banpumInsert();
				break;
			case 2:
				banpumChenk();
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void banpumInsert() {
		System.out.println("[반품등록]");

	}

	private void banpumChenk() {
		System.out.println("[반품조회]");
	}

	protected void shippingManagement() {
		int ch;
		while (true) {
			try {
				System.out.println("\n[배송관리] 1.배송입력 2.배송조회 3.영업부 메뉴로 돌아가기 =>");
				ch = Integer.parseInt(br.readLine());

				if (ch == 3) {
					SalesUI.this.menu();
				}

				switch (ch) {
				case 1:
					shipInsert();
					break;
				case 2:
					shipChenk();
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void shipInsert() {
		// TODO Auto-generated method stub

	}

	private void shipChenk() {
		// TODO Auto-generated method stub

	}

	protected void operatingProfit() {
		int ch;

		// 매출데이터 입력후 전표에 차변, 대변 같이 넣기
		// 영업이익조회 = 매출-매출원가-판매비와 관리비
		while (true) {
			try {
				System.out.println("[영업이익조회] 1.매출입력 2.매출조회 3.매출전표입력(차변/대변) 4.매출전표조회 5.영업이익조회 6.영업부 메뉴로 돌아가기 => ");

				ch = Integer.parseInt(br.readLine());

				if (ch == 6) {
					SalesUI.this.menu();
				}

				switch (ch) {
				case 1:
					salesInsert();
					break;
				case 2:
					salesCheck();
					break;
				case 3:
					salesAccountInsert();
					break;
				case 4:
					salesAccountCheck();
				case 5:
					operatingProfitCheck();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void salesInsert() {
		System.out.println("[매출입력(※전표번호가 있어야 입력이 가능합니다※)]");

		try {
			SalesDTO dto = new SalesDTO();
			ProductDTO pdto = new ProductDTO();

			System.out.println("[전표일련번호]");
			dto.setStateNo(Integer.parseInt(br.readLine()));

			System.out.println("[제품코드]");
			pdto.setProductNo(br.readLine());

			System.out.println("[거래처]");
			dto.setCustomer(br.readLine());

			System.out.println("[매출액]");
			pdto.setPrice(Integer.parseInt(br.readLine()));

			System.out.println("[수량]");
			pdto.setStock(Integer.parseInt(br.readLine()));

			System.out.println("[거래일시]");
			dto.setDealDate(br.readLine());

			dao.salesInsert(dto, pdto);

			System.out.println("[매출이 입력되었습니다.]");

		} catch (NumberFormatException e) {
			System.out.println("금액은 숫자만 입력 가능합니다.");
		} catch (Exception e) {
			System.out.println("등록에 실패했습니다. 다시 입력해주세요.");
		}
		System.out.println();

	}

	private void salesCheck() {
		System.out.println("[매출입력조회]");

		System.out.println("매출번호\t전표일련번호\t제품코드\t거래처\t매출액\t수량\t거래일시");
		System.out.println("--------------------------------------------------------------------------------");

		List<SalesDTO> list = dao.listSales();
		for (SalesDTO dto : list) {
			System.out.print(dto.getSalesNo() + "\t");
			System.out.print(dto.getStateNo() + "\t");
			System.out.print(dto.getProductNo()+ "\t");
			System.out.print(dto.getCustomer() + "\t");
			System.out.print(dto.getSales() + "\t");
			System.out.print(dto.getSalesQty() + "\t");
			System.out.println(dto.getDealDate() + "\t");
		}

		System.out.println();

	}

	private void salesAccountInsert() {
		System.out.println("[매출전표입력(차변/대변)]");

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

			dao.salesAccountInsert(empdto, accdto);

			System.out.println("매입전표 등록이 완료되었습니다.");

		} catch (NumberFormatException e) {
			System.out.println("금액은 숫자만 입력 가능합니다.");
		} catch (Exception e) {
			System.out.println("데이터 등록이 실패했습니다. ");
		}
		System.out.println();

	}

	private void salesAccountCheck() {
		System.out.println("[매출전표조회]");

		try {
			List<AccDTO> list = dao.listSalesAccountInsert();

			if (list.size() == 0) {
				System.out.println("등록된 전표 내역이 없습니다. ");
				return;
			}

			System.out.println("전표일련번호\t차대\t계좌코드\t계정과목명\t금액\t취소\t전표상태\t작성일자\t\t사번");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");

			for (AccDTO accdto : list) {
				System.out.print(accdto.getStateNo() + "\t");
				System.out.print(accdto.getT_account() + "\t");
				System.out.print(accdto.getAccountNo() + "\t");
				System.out.print(accdto.getAsub_name() + "\t");
				System.out.print(accdto.getAmount() + "\t");
				System.out.print(accdto.getCancellation() + "\t");
				System.out.print(accdto.getStateCon() + "\t");
				System.out.print(accdto.getStateDate() + "\t");
				System.out.println(accdto.getEmpNo());

			}
		} catch (Exception e) {
			System.out.println("등록전표 조회에 실패했습니다. ");
		}
		System.out.println();

	}

	private void operatingProfitCheck() {
		System.out.println("[영업이익조회]");

	}

	protected void taxBill() {
		int ch;

		while (true) {
			try {
				System.out.println("\n[세금계산서] 1.세금계산서 입력 2.세금계산서 조회 3.영업부 메뉴로 돌아가기 =>");
				ch = Integer.parseInt(br.readLine());

				if (ch == 3) {
					SalesUI.this.menu();
				}

				switch (ch) {
				case 1:
					taxBillInsert();
					break;
				case 2:
					taxBillCheck();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void taxBillInsert() {
		System.out.println("[세금계산서 입력]");

		try {
			SalesDTO dto = new SalesDTO();

			System.out.println("[매출번호]");
			dto.setStateNo(Integer.parseInt(br.readLine()));

			System.out.println("[상호]");
			dto.setCompanyName(br.readLine());

			System.out.println("[대표자성명]");
			dto.setName(br.readLine());

			System.out.println("[주소]");
			dto.setAddress(br.readLine());

			System.out.println("[업태]");
			dto.setBusStatue(br.readLine());

			System.out.println("[공급가액]");
			dto.setValueSupply(Integer.parseInt(br.readLine()));

			System.out.println("[세액]");
			dto.setTaxAmount(Integer.parseInt(br.readLine()));

			System.out.println("[품목]");
			dto.setItem(br.readLine());

			System.out.println("[수량]");
			dto.setNum(Integer.parseInt(br.readLine()));

			System.out.println("[단가]");
			dto.setUnitPrice(Integer.parseInt(br.readLine()));

			System.out.println("[미수금]");
			dto.setOutAmount(Integer.parseInt(br.readLine()));

			System.out.println("[구분]");
			dto.setNote(br.readLine());

			dao.taxBillInsert(dto);

			System.out.println("[세금계산서 입력이 완료되었습니다.]");

		} catch (NumberFormatException e) {
			System.out.println("금액은 숫자만 입력 가능합니다.");
		} catch (Exception e) {
			System.out.println("등록에 실패하였습니다. 다시 시도해주세요.");
		}
		System.out.println();

	}

	private void taxBillCheck() {
		System.out.println("[세금계산서 조회]");

		System.out.println("등록번호\t매출번호\t상호\t성명\t주소\t업태\t작성일\t공급가액\t세액\t품목\t수량\t단가\t합계금액\t미수금\t구분");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------");

		List<SalesDTO> list = dao.listTaxBill();
		for (SalesDTO dto : list) {
			System.out.print(dto.getTaxBillNum() + "\t");
			System.out.print(dto.getSalesNo() + "\t");
			System.out.print(dto.getCompanyName() + "\t");
			System.out.print(dto.getName() + "\t");
			System.out.print(dto.getAddress() + "\t");
			System.out.print(dto.getBusStatue() + "\t");
			System.out.print(dto.getCurrDate() + "\t");
			System.out.print(dto.getValueSupply() + "\t");
			System.out.print(dto.getTaxAmount() + "\t");
			System.out.print(dto.getItem() + "\t");
			System.out.print(dto.getNum() + "\t");
			System.out.print(dto.getUnitPrice() + "\t");
			System.out.print(dto.getTotal() + "\t");
			System.out.print(dto.getOutAmount() + "\t");
			System.out.println(dto.getNote());

		}
		System.out.println();

	}

	protected void moneyBondManage() {
		int ch;
		while (true) {
			try {
				System.out.println("\n[수금/채권조회] 1.수금/채권 조회 2.영업부 메뉴로 나가기 => ");
				ch = Integer.parseInt(br.readLine());

				if (ch == 2) {
					DBConn.close();
					return;
				}

				switch (ch) {
				case 1:
					money();
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println();
		}
	}

	private void money() {
		System.out.println("[수금/채권 조회]");
		
		try {
			List<AccDTO> list = dao.listMoney();

			if (list.size() == 0) {
				System.out.println("등록된 전표 내역이 없습니다. ");
				return;
			}

			System.out.println("전표일련번호\t차대\t계좌코드\t계정과목명\t금액\t취소\t전표상태\t작성일자\t\t사번");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");

			for (AccDTO accdto : list) {
				System.out.print(accdto.getStateNo() + "\t");
				System.out.print(accdto.getT_account() + "\t");
				System.out.print(accdto.getAccountNo() + "\t");
				System.out.print(accdto.getAsub_name() + "\t");
				System.out.print(accdto.getAmount() + "\t");
				System.out.print(accdto.getCancellation() + "\t");
				System.out.print(accdto.getStateCon() + "\t");
				System.out.print(accdto.getStateDate() + "\t");
				System.out.println(accdto.getEmpNo());

			}
		} catch (Exception e) {
			System.out.println("등록전표 조회에 실패했습니다. ");
		}
		System.out.println();

	}

}
