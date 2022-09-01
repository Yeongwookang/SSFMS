package com.ssfms;

import java.io.BufferedReader;

import java.io.InputStreamReader;

// import com.util.DBConn;

public class App2 {
	public static void main(String[] args) {
		// MainUI ui = new MainUI();
		// ui.startmenu();

		int ch = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		EmpDAO edao = new EmpDAOImpl();
		try {
			do {

				System.out.println();
				System.out.println("\t     ･ﾟ✧ 관리자님, 환영합니다! ･ﾟ✧\t     ");

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
						System.out.println("⚜ 로그인 되었습니다. ⚜");
						edto = edao.readMember(id);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (edto.getDep().equals("회계")) {
				new AccUI().menu();
			} else if (edto.getDep().equals("생산부")) {
				new ProdUI().menu();
			} else if (edto.getDep().equals("구매부")) {
				new BuyUI().menu();
			} else if (edto.getDep().equals("영업부")) {
				new SalesUI().menu();
			} else if (edto.getDep().equals("인사부")) {
				new EmpUI().menu();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
