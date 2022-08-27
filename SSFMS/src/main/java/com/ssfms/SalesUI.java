package com.ssfms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.util.DBConn;

public class SalesUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private SalesDAO dao = new SalesDAOImpl();
	private ProductDTO pdto = new ProductDTO();

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
					operatingProfitCheck();
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
			System.out.println("\n[견적/주문관리] 1.견적서입력 2.주문서입력 3.견적서조회 4.주문서조회 5.주문서관리 6.영업부 메뉴로 돌아가기=> ");
			ch = Integer.parseInt(br.readLine());

			if (ch == 6) {
				SalesUI.this.menu();
			}

			switch (ch) {
			case 1:
				estimateInsert();
				break;
			case 2:
				orderInsert();
				break;
			case 3:
				estimateCheck();
				break;
			case 4:
				orderCheck();
				break;
			case 5:
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
			System.out.println("견적서일련번호 : ");

			System.out.println("입력날짜(현재날짜) : ");

			System.out.println("사업자등록번호 : ");

			System.out.println("상호 : ");

			System.out.println("업태 : ");

			System.out.println("연락처 : ");

			System.out.println("주문처 : ");

			System.out.println("담당자 : ");

			System.out.println("전화번호 : ");

			for (int i = 0; i < 10; i++) {
				System.out.println("제품명 : ");

				System.out.println("수량 : ");

				System.out.println("단가 : ");

				System.out.println("공급가액");

				System.out.println("세액 : ");

				System.out.println("바고 : ");
			}

			System.out.println("결제방식 : ");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void orderInsert() {
		System.out.println("[주문서 입력하세요]");
		// 주문서는 입력, 조회, 관리가 가능		=>  주문서는 외부에서 입력하는 것 주문서 테이블을 따로만들어 데이터 입력하는 것 물어보기

		try {
			System.out.println("주문서일련번호 : ");

			System.out.println("입력날짜(현재날짜) : ");

			System.out.println("주문처 : ");

			System.out.println("담당자 : ");

			System.out.println("전화번호 : ");

			System.out.println("남품예정일 : ");

			System.out.println("결재조건 : ");

			System.out.println("회사명 : ");

			System.out.println("회사전화번호 : ");
			
			System.out.println("회사사업자번호 : ");
			
			System.out.println("업태 : ");

			for (int i = 0; i < 10; i++) {
				System.out.println("제품명 : ");

				System.out.println("수량 : ");

				System.out.println("단가 : ");

				System.out.println("공급가액");

				System.out.println("세액 : ");

				System.out.println("바고 : ");
			}

			System.out.println("합계 : ");
			
			System.out.println("부가세 : ");
			
			System.out.println("총계 : ");

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

	protected void release() throws NumberFormatException, IOException {
		System.out.println("\n[출고]");

		int stateNo;

		try {
			System.out.println("[출고시킬 제품의 주문번호를 입력하세요] => ");
			stateNo = Integer.parseInt(br.readLine());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void banpum() throws NumberFormatException, IOException {
		System.out.println("\n[반품]");
		int stateNo;

		try {
			System.out.println("[반품처리할 제품의 주문번호를 입력하세요] => ");
			stateNo = Integer.parseInt(br.readLine());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void shippingManagement() throws NumberFormatException, IOException {
		System.out.println("\n[배송관리]");

		int stateNo;

		try {
			System.out.println("[배송정보를 출력할 배송번호를 입력하세요] => ");
			stateNo = Integer.parseInt(br.readLine());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void operatingProfitCheck() {
		System.out.println("\n[영업이익조회]");

		try {
			System.out.println("[영업이익]");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void taxBill() {
		System.out.println("\n[세금계산서]");

		try {
			System.out.println("\n[세금계산서 조회]");

			System.out.println("[등록번호]");
			System.out.println("[상호]");
			System.out.println("[사업자번호]");
			System.out.println("[대표자성명]");
			System.out.println("[주소]");
			System.out.println("[종목]");
			System.out.println("[작성일자]");
			System.out.println("[품목]");
			System.out.println("[수량]");
			System.out.println("[단가]");
			System.out.println("[공급가액]");
			System.out.println("[부가세]");
			System.out.println("[발행금액]");
			System.out.println("[미수금]");
			System.out.println("[구분]");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void moneyBondManage() {
		int ch;

		try {
			System.out.println("\n[수금/채권조회] 1.수금관리 2.채권관리 => ");
			ch = Integer.parseInt(br.readLine());

			if (ch == 2) {
				DBConn.close();
				return;
			}

			switch (ch) {
			case 1:
				money();
				break;
			case 2:
				bond();
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();

	}

	private void money() {
		System.out.println("[수금관리]");

		try {
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void bond() {
		System.out.println("[채권관리]");

		try {
			System.out.println("[발행일자]");
			System.out.println("[채무자명]");
			System.out.println("[연락처]");
			System.out.println("[상황조건]");
			System.out.println("[채권금액]");
			System.out.println("[원금/이자]");
			System.out.println("[금액]");
			System.out.println("[원금잔액]");
			System.out.println("[납입일자]");
			System.out.println("[비고]");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
