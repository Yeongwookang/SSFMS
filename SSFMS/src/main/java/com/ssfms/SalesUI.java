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
				System.out.print("[영업부] 1.견적/주문처리 2.출고 3.반품 4.배송관리 5.영업이익조회 6.세금계산서 7.수금/채권관리 8.종료 =>");
				ch = Integer.parseInt(br.readLine());

				if (ch == 3) {
					return;
				}

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
			System.out.println("\n[견적/주문관리] 1.견적서 및 주문서 조회 2.견적서 및 주문서 관리 3.영업부로 돌아가기 => ");
			ch = Integer.parseInt(br.readLine());

			if (ch == 3) {
				DBConn.close();
				return;
			}

			switch (ch) {
			case 1:
				check();
				break;
			case 2:
				manage();
				break;
			case 3:
				SalesUI.this.menu();
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();

	}

	private void manage() throws NumberFormatException, IOException {
		System.out.println("\n[견적서 및 주문서 조회]");
		int stateNo;

		try {
			System.out.println("[일련번호를 입력해주세요] => ");
			stateNo = Integer.parseInt(br.readLine());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void check() throws NumberFormatException, IOException {
		System.out.println("\n[견적서 및 주문서 관리]");
		int stateNo;

		try {
			System.out.println("[취소할 전표의 일련번호를 입력해주세요] => ");
			stateNo = Integer.parseInt(br.readLine());

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
			System.out.println("\n[수금/채권조회] 1.수금관리 2.채권관리 3.영업부로 돌아가기 => ");
			ch = Integer.parseInt(br.readLine());

			if (ch == 3) {
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
			case 3:
				SalesUI.this.menu();
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
