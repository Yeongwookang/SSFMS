package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.util.DBConn;

public class ProdUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private ProdDAO dao = new ProdDAOImpl();

	public void menu() {

		int ch;

		while (true) {
			try {
				System.out.println("1. 제품등록 2. 등록제품 삭제 3. ");
				ch = Integer.parseInt(br.readLine());

				if (ch == 7) {
					DBConn.close();
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
					using_part();
					break;
				case 4:
					findById();
					break;
				case 5:
					findByName();
					break;
				case 6:
					listAll();
					break;
				}

			} catch (Exception e) {
			}
		}

	}

	protected void reg_product() {
		System.out.println("제품 등록");
		ProductDTO pdto = new ProductDTO();
		try {
			System.out.println("제품의 등록번호를 입력해주세요.");
			pdto.setProductNo(br.readLine());
			System.out.println("제품의 이름을 입력해주세요.");
			pdto.setProduct_name(br.readLine());
			System.out.println("제품의 생산 가격을 입력해주세요.");
			pdto.setCost(Integer.parseInt(br.readLine()));
			System.out.println("제품의 판매 가격을 입혁해주세요.");
			pdto.setPrice(Integer.parseInt(br.readLine()));
			pdto.setStock(0);
			dao.reg_product(pdto);

			System.out.println("\n제품 등록이 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void del_product() {
		System.out.println("등록제품 삭제");
		try {
			System.out.println("삭제할 제품의 등록번호를 입력해주세요.");
			String productNo = br.readLine();
			dao.del_product(productNo);
			System.out.println("\n등록된 제품이 삭제되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void using_part() {
		BuyDTO bdto = new BuyDTO();
		System.out.println("생산출고");
		try {
			String partNo;
			while (true) {
				System.out.println("사용한 재료의 코드를 입력해주세요. [입력 종료: 0]");
				partNo = br.readLine();
				if (partNo.equals("0")) {
					break;
				}
				bdto.setPartNo(partNo);
				System.out.println("사용한 재료의 갯수를 입력해주세요.");
				bdto.setPart_stock(Integer.parseInt(br.readLine()));
				dao.using_part(bdto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void findByName() {
		System.out.println("\n이름 검색 !!!");

	}

	protected void findById() {
		System.out.println("\n아이디 검색 !!!");

	}

	protected void listAll() {
		System.out.println("\n전체 리스트 !!!");

	}
}
