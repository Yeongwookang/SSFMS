package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.util.DBConn;

public class ProdUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private ProdDAO dao = new ProdDAOImpl();
	private AccDAO adao = new AccDAOImpl();
	public void menu() {

		int ch;

		while (true) {
			try {
				System.out.println("1. 제품등록 2. 등록제품 삭제 3. 생산출고 4. 생산입고 5. 생산전표등록");
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
					Producing();
					break;
				case 5:
					insert_Prod_state();
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
		System.out.println("\n제품 등록");
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

	protected void using_part() {
		ProdDTO pdto= new ProdDTO();
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
				pdto.setPart_stock(Integer.parseInt(br.readLine()));
				dao.using_part(pdto);
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void Producing() {
		System.out.println("\n생산 입고");
		try {
			String ProductNo;
			ProdDTO pdto = new ProdDTO();
			System.out.println("승인된 전표번호를 입력해주세요.");
			int StateNo= Integer.parseInt(br.readLine());
			
			
			pdto.setStateNo(StateNo);
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

	protected void insert_Prod_state() {
		System.out.println("\n생산전표등록");
		AccUI aui=new AccUI();
		try {
			aui.insert();
			/*System.out.println("작성자의 사번을 입력해주세요.");
			adto.setEmpNo(br.readLine());
			System.out.println("계좌코드를 입력해주세요.");
			adto.setAccountNo(br.readLine());
			System.out.println("계정과목 코드를 입력해주세요.");
			adto.setAccountSubNo(br.readLine());
			System.out.println("금액을 입력해주세요.");
			adto.setAmount(br.readLine());
			System.out.println("비고사항을 입력해주세요.");
			adto.setDetail(br.readLine());
			adto.setCancellation("요청");
			adto.setStateCon("승인대기");
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			adto.setStateDate(sdf.format(cal.getTime()));
			
			adao.insertAccount(adto);*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	protected void listAll() {
		System.out.println("\n전체 리스트 !!!");

	}
}
