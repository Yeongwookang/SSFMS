package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private EmpDAO dao = new EmpDAOImpl();
	private Login login = new Login();

	public void startmenu() {
		while (true) {
			if (login.loginEmp() == null) {
				menuGuest();
			} else if (login.loginEmp().getEmpNo().equals("1002")) {
				menuAccountant();
			} else {
				menuUser();
			}
		}
	}

	private void menuAccountant() {
		int ch;

		try {
			System.out.println("\n[관리자] 님");

			do {
				System.out.print("[1] 회계 [2] 구매 [3] 인사 [4] 생산 [5] 영업 [6] 로그아웃 \n");
				ch = Integer.parseInt(br.readLine());
			} while (ch < 1 || ch > 6);

			switch (ch) {
			case 1: new AccUI().menu();
			case 2: new BuyUI().menu();
			case 3: new EmpUI().menu();
			case 4: new ProdUI().menu();
			case 5: new SalesUI().menu();
			case 6: login.logout();
				System.out.println();
				break;
			}
		} catch (Exception e) {

		}
	}

	private void menuUser() {
		int ch;

		try {
			System.out.println("\n[" + login.loginEmp().getName() + "] 님");

			do {
				System.out.print("1.상품검색 2.상품구매 3.정보수정 4.로그아웃  => ");
				ch = Integer.parseInt(br.readLine());
			} while (ch < 1 || ch > 4);

			switch (ch) {
			case 1:
				System.out.println("[상품검색]\n");
				break;
			case 2:
				System.out.println("[상품구매]\n");
				break;
			// case 3: memberUI.update(); break;
			case 4:
				login.logout();
				System.out.println();
				break;
			}
		} catch (Exception e) {
		}

	}

	private void menuGuest() {
		int ch;

		do {
			ch = 0;
			try {
				System.out.println();

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
				System.out.print("1.로그인 2.종료 => ");
				ch = Integer.parseInt(br.readLine());
			} catch (Exception e) {
			}
		} while (ch < 1 || ch > 3);

		if (ch == 3) {
			System.exit(0);
		}

		switch (ch) {
		case 1:
			login();
			break;
		}
	}

	public void login() {
		System.out.println("\n[로그인]");

		String empNo, pwd;

		try {
			System.out.print("사번 ? ");
			empNo = br.readLine();
			System.out.print("패스워드 ? ");
			pwd = br.readLine();

			EmpDTO dto = dao.readEmp(empNo);
			if (dto == null || !dto.getPwd().equals(pwd)) {
				System.out.println("아이디 또는 패스워드가 일치하지 않습니다.");
				return;
			}

			login.login(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

}
