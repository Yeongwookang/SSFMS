package com.ssfms;

import java.io.BufferedReader;

import java.io.InputStreamReader;

 import com.util.DBConn;

public class App {
	private static EmpDTO LOGIN_EMP;
	public static EmpDTO loginEmp() {
		return LOGIN_EMP;
	}
	
	public void logout() {
		LOGIN_EMP = null;
	}
	
	public static void main(String[] args) {

		int ch = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		EmpDAO edao = new EmpDAOImpl();
		try {
			do {

				System.out.println();
				System.out.println("\t   ･ﾟ✧ 로그인이 필요한 서비스입니다! ･ﾟ✧\t     ");
				System.out.println("=================================================");
				String n = "      _____  _____  ______ ___  ___ _____ \r\n"

						+ "     /  ___|/  ___| |  ___||  \\/  |/  ___|\r\n"
						+ "     \\ `--. \\ `--.  | |_   | .  . |\\ `--. \r\n"
						+ "      `--. \\ `--. \\ |  _|  | |\\/| | `--. \\\r\n"
						+ "     /\\__/ //\\__/ / | |    | |  | |/\\__/ /\r\n"
						+ "     \\____/ \\____/  \\_|    \\_|  |_/\\____/ ";

				System.out.println(n);
				System.out.println("\t\t\t  대한민국 재무관리 No.1 System");
				System.out.println();
				System.out.println("=================================================");
				System.out.print("[1] 로그인\t\t\t[2] 로그아웃 \n");
				System.out.println("=================================================");
				System.out.print("[메뉴 버튼] : ");
				ch = Integer.parseInt(br.readLine());

			} while (ch < 1 || ch > 2);
			EmpDTO edto = new EmpDTO();
			if (ch == 2) {
				System.exit(0);
			}
			if (ch == 1) {
				try {
					
					System.out.print("⚜ ID: ");
					String id = br.readLine();
					System.out.print("⚜ PW: ");
					String pwd = br.readLine();
					edto = edao.readEmp(id);
					
					if (edto.getPwd().equals(pwd)) {
						System.out.println("\t     ･ﾟ✧ " + edto.getName() + "님, 환영합니다! ･ﾟ✧\t     ");
						System.out.println("\t      ⚜ 로그인 되었습니다. ⚜");
						edto = edao.readMember(id);
						LOGIN_EMP= edto;
					} else {
						System.out.println("\t     ⚜ 로그인에 실패 했습니다. ⚜");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (edto.getRank().equals("관리자")) {
				new Administrator().menu();
			} else if (edto.getDep().equals("회계")) {
				new AccUI().menu();
			} else if (edto.getDep().equals("생산부")) {
				new ProdUI().menu();
			} else if (edto.getDep().equals("구매부")) {
				new BuyUI().menu();
			} else if (edto.getDep().equals("영업부")) {
				new SalesUI().menu();
			} else if (edto.getDep().equals("인사부")) {
				new EmpUI().menu();
			} else {
				System.out.println("⚜ EMP 설정 오류입니다. 경력사항을 확인해 주십시오. ⚜");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class Administrator {
	EmpDAO edao = new EmpDAOImpl();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void menu() {
		int ch;
		try {
			System.out.println("\t     ･ﾟ✧ [관리자]님, 환영합니다! ･ﾟ✧\t     ");

			do {
				System.out.println("=================================================");
				System.out.print("[1] 회계 [2] 구매 [3] 인사 [4] 생산 [5] 영업 [6] 로그아웃 \n");
				System.out.println("=================================================");
				ch = Integer.parseInt(br.readLine());
			} while (ch < 1 || ch > 6);

			switch (ch) {
			case 1:
				new AccUI().menu();
			case 2:
				new BuyUI().menu();
			case 3:
				new EmpUI().menu();
			case 4:
				new ProdUI().menu();
			case 5:
				new SalesUI().menu();
			case 6:
				App.main(null);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		}
	}

