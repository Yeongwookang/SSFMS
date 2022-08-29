package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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

	private void estimateInsert() {
		System.out.println("[견적서 입력하세요]");
		// 견적서는 입력과 조회만 가능. 입력후 30일까지만 보관하고 30일이 지나면 삭제

		try {
			SalesDTO dto = new SalesDTO();

			System.out.println("견적서일련번호 : ");

			// System.out.println("입력날짜(현재날짜) : ");

			System.out.println("사업자등록번호 : ");

			System.out.println("회사명 : ");

			System.out.println("연락처 : ");

			System.out.println("주문처 : ");

			System.out.println("담당자 : ");

			System.out.println("전화번호 : ");

			for (int i = 0; i < 10; i++) {
				System.out.println("제품명 : ");

				System.out.println("수량 : ");

				/*
				 * 
				 * registNo(사업자등록번호) System.out.println("단가 : ");
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

	/*
	 * private void orderInsert() { System.out.println("[주문서 입력하세요]"); // 주문서는 입력,
	 * 조회, 관리가 가능 => 주문서는 외부에서 입력하는 것 테이블생성해서 넣어놓기
	 * 
	 * try { SalesDTO dto = new SalesDTO();
	 * 
	 * System.out.println("주문서일련번호 (orderNo): ");
	 * 
	 * System.out.println("입력날짜(현재날짜) currDate: ");
	 * 
	 * System.out.println("주문처 orderPlace: ");
	 * 
	 * System.out.println("담당자 manager: ");
	 * 
	 * System.out.println("전화번호 tel : ");
	 * 
	 * System.out.println("남품예정일 deliveryDate : ");
	 * 
	 * System.out.println("회사명 companyName: ");
	 * 
	 * System.out.println("회사전화번호 companyTel: ");
	 * 
	 * System.out.println("회사사업자번호 companyNo: ");
	 * 
	 * "회사주소 companyAddress: ");
	 * 
	 * for (int i = 0; i < 10; i++) { System.out.println("제품명 productName : ");
	 * 
	 * System.out.println("수량 num : ");
	 * 
	 * System.out.println("단가 price : ");
	 * 
	 * System.out.println("공급가액  supplyValue ");
	 * 
	 * System.out.println("세액 tax : ");
	 * 
	 * System.out.println("비고 note : ");
	 * 
	 * //이거 수량 제외 다 적어놓고 수량만 적게끔하 }
	 * 
	 * System.out.println("합계 sum : ");
	 * 
	 * System.out.println("부가세 surtax : ");
	 * 
	 * System.out.println("총계 tot : ");
	 * 
	 */

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

	private void banpumChenk() {
		System.out.println("[반품등록]");

	}

	private void banpumInsert() {
		System.out.println("[반품조회]");

	}

	protected void shippingManagement() {
		int ch;

		try {
			System.out.println("\n[배송관리] 1.배송입력 2.배송조회 3.영업부 메뉴로 돌아가기 =>");
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

	protected void operatingProfit() {
		int ch;

		// 매출데이터 입력후 전표에 차변, 대변 같이 넣기
		// 영업이익조회 = 매출-매출원가-판매비와 관리비
		try {
			System.out.println("[영업이익조회] 1.매출입력 2.매출전표입력(차변/대변) 3.영업이익조회 4.영업부 메뉴로 돌아가기 => ");

			ch = Integer.parseInt(br.readLine());

			if (ch == 4) {
				SalesUI.this.menu();
			}

			switch (ch) {
			case 1:
				salesInsert();
				break;
			case 2:
				salesAccountInsert();
				break;
			case 3:
				operatingProfitCheck();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void salesInsert() {
		System.out.println("[매출입력]");

		try {
			SalesDTO dto = new SalesDTO();
			
			System.out.println("[매출번호]");
			dto.setSalesNo(br.readLine());
			
			System.out.println("[전표일련번호]");
			dto.setStateNo(Integer.parseInt(br.readLine()));
			
			System.out.println("[제품코드]");
			dto.setProductCode(br.readLine());
			
			System.out.println("[거래처]");
			dto.setCustomer(br.readLine());
			
			System.out.println("[매출액]");
			dto.setSales(Integer.parseInt(br.readLine()));
			
			System.out.println("[수량]");
			dto.setSalesQty(Integer.parseInt(br.readLine()));
			
			System.out.println("[거래일시]");
			dto.setDealDate(br.readLine());
			
			dao.salesInsert(dto);
			
			System.out.println("[매출이 입력되었습니다.]");

		} catch (NumberFormatException e) {
			System.out.println("금액은 숫자만 입력 가능합니다.");
		} catch (Exception e) {
			System.out.println("등록에 실패했습니다. 다시 입력해주세요.");
		}
		System.out.println();

	}

	private void salesAccountInsert() {
		System.out.println("[매출전표입력(차변/대변)]");

	}

	private void operatingProfitCheck() {
		System.out.println("[영업이익조회]");

	}

	protected void taxBill() {
		int ch;

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

	private void taxBillInsert() {
		System.out.println("[세금계산서 입력]");

		try {
			SalesDTO dto = new SalesDTO();

			System.out.println("[등록번호]");
			dto.setTaxBillNum(Integer.parseInt(br.readLine()));

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

			System.out.println("[합계금액]");
			dto.setTotal(Integer.parseInt(br.readLine()));

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

	}

	protected void moneyBondManage() {
		int ch;

		try {
			System.out.println("\n[수금/채권조회] 1.수금/채권 관리 2.나가기 => ");
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

	private void money() {
		System.out.println("[수금/채권 관리]");

		try {
			System.out.println("수금/채권 조회");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
