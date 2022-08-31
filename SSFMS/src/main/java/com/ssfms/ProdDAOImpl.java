package com.ssfms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class ProdDAOImpl implements ProdDAO {
	private Connection conn = DBConn.getConnection();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	@Override
	public void reg_product(ProductDTO pdto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "INSERT INTO product(productNo, product_name, cost, price, stock) VALUES (?,?,?,?,?)";
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, pdto.getProductNo());
			pstmt.setString(2, pdto.getProduct_name());
			pstmt.setInt(3, pdto.getCost());
			pstmt.setInt(4, pdto.getPrice());
			pstmt.setInt(5, pdto.getStock());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}
		}
	}

	@Override
	public void del_product(String productNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "DELETE FROM PRODUCT WHERE productNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productNo);
			pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}
		}
	}

	@Override
	public List<ProdDTO> list_product() throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		ResultSet rs=null;
		List<ProdDTO>list=new ArrayList<>();
		try {
			sql = "SELECT * FROM PRODUCT";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				ProdDTO pdto = new ProdDTO();
				pdto.setProductNo(rs.getString("productNo"));
				pdto.setProduct_name(rs.getString("product_name"));
				pdto.setCost(rs.getInt("cost"));
				pdto.setPrice(rs.getInt("price"));
				pdto.setStock(rs.getInt("stock"));

				list.add(pdto);
			}

		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	
	
	@Override
	public ProdDTO readPart(String partNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		ProdDTO pdto = new ProdDTO();

		try {
			sql = "SELECT partNo, part_name, part_price, part_stock FROM PART WHERE partNo =? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, partNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pdto.setPartNo(partNo);
				pdto.setPart_name(rs.getString("part_name"));
				pdto.setPart_price(rs.getInt("part_price"));
				pdto.setPart_stock(rs.getInt("part_stock"));
			}

		}catch (Exception e) {
			throw e;
		}
		return pdto;
	}

	@Override
	public List<AccDTO> listAccount_prod() throws SQLException {
		List<AccDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT stateNo, t_account, empNo, accountNo, asub_name, amount, detail, cancellation, stateCon, "
					+ " TO_char(stateDate,'YYYY-MM-DD')stateDate, dep, rank, name FROM State_prod_view";

			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				AccDTO dto = new AccDTO();

				dto.setStateNo(rs.getInt("stateNo"));
				dto.setT_account(rs.getString("t_account"));
				dto.setEmpNo(rs.getString("empNo"));
				dto.setAccountNo(rs.getString("accountNo"));
				dto.setAsub_name(rs.getString("asub_name"));
				dto.setAmount(rs.getInt("amount"));
				dto.setDetail(rs.getString("detail"));
				dto.setCancellation(rs.getString("cancellation"));
				dto.setStateCon(rs.getString("stateCon"));
				dto.setStateDate(rs.getString("stateDate"));
				dto.setDep(rs.getString("dep"));
				dto.setRank(rs.getString("rank"));
				dto.setName(rs.getString("name"));

				list.add(dto);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}

		}

		return list;
	}

	@Override
	public List<BuyDTO> listPart() throws SQLException {
		List<BuyDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT partNo,part_name,part_price,part_stock FROM part";

			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BuyDTO bdto = new BuyDTO();

				bdto.setPartNo(rs.getString("partNo"));
				bdto.setPart_name(rs.getString("part_name"));
				bdto.setPart_price(rs.getInt("part_price"));
				bdto.setPart_stock(rs.getInt("part_stock"));

				list.add(bdto);
			}

		} catch (Exception e) {
			throw e;
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}

		}

		return list;
	}

	@Override
	public void buyList_write(List<ProdDTO> list) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		try {
			conn.setAutoCommit(false);
			for (ProdDTO pdto : list) {
				sql = "INSERT INTO PartOffer(partOfferNo,partNo,part_name,qty,offer_date) "
						+ "VALUES(partOffer_seq.NEXTVAL,?,?,?,SYSDATE)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pdto.getPartNo());
				pstmt.setString(2, pdto.getPart_name());
				pstmt.setInt(3, pdto.getQty());
				pstmt.executeUpdate();
			}
			conn.commit();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void buy_offer_cancel(List<ProdDTO> list) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;
		conn.setAutoCommit(false);
		try {
			for (ProdDTO pdto : list) {
				sql = "DELETE FROM PartOffer WHERE partOfferNo=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pdto.getPartOfferNo());
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			throw e;
		}

		conn.commit();
		conn.setAutoCommit(true);

	}

	@Override
	public List<ProdDTO> list_Buy_offer() throws SQLException {
		List<ProdDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT partOfferNo, partNo, part_name, qty, TO_CHAR(offer_date,'YYYY-MM-DD') offer_date FROM partOffer";

			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProdDTO pdto = new ProdDTO();

				pdto.setPartOfferNo(rs.getInt("partOfferNo"));
				pdto.setPartNo(rs.getString("partNo"));
				pdto.setPart_name(rs.getString("part_name"));
				pdto.setQty(rs.getInt("qty"));
				pdto.setOffer_date(rs.getString("offer_date"));

				list.add(pdto);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}

		}

		return list;
	}


@Override
public void producing(List<ProdDTO>plist, List<ProdDTO>ulist) throws SQLException {
	PreparedStatement pstmt = null;
	String sql;
	conn.setAutoCommit(false);
	try {
		sql= "INSERT INTO PRODUCTION (prodNo, stateNo,productNo,qty,cost,prod_Date) "
				+ " VALUES(PRODUCTION_seq.nextval, ?,?,?,?, SYSDATE)";
		pstmt=conn.prepareStatement(sql);
		for(ProdDTO pdto:plist) {
			pstmt.setInt(1,pdto.getStateNo()); // 전표번호
			pstmt.setString(2, pdto.getProductNo()); // 제품코드
			pstmt.setInt(3, pdto.getQty()); // 생산량
			pstmt.setInt(4, pdto.getCost()); // 비용
			pstmt.executeUpdate();
			pstmt.close();
			sql = "UPDATE product SET stock = stock + ? WHERE productNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,pdto.getQty());
			pstmt.setString(2, pdto.getProductNo());
			pstmt.executeUpdate();
		}
		pstmt.close();
		
		sql = "INSERT INTO STOCK(StockNo, prodNo, partNo, pStock, use, nStock, date)"
				+ "VALUES(STOCK_seq.nextval, ?, ?, ?, ?, ?, SYSDATE )";
		pstmt = conn.prepareStatement(sql);
		for(ProdDTO pdto:ulist) {
		pstmt.setString(1, pdto.getProdNo()); // 생산번호
		pstmt.setString(2, pdto.getPartNo()); // 부품코드
		int stock = readPart(pdto.getPartNo()).getPart_stock();
		pstmt.setInt(3, stock); // 기존 재고량
		pstmt.setInt(4, pdto.getQty()); // 사용량
		pstmt.setInt(5, stock - pdto.getQty()); // 현재 재고량
		if (stock - pdto.getQty() < 0) {
			System.out.println("현재 재고량보다 많이 사용할수 없습니다.");
			return;
		}
		pstmt.executeUpdate();

		pstmt.close();

		sql = "UPDATE part SET part_stock = part_stock - ? WHERE partNo = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pdto.getQty());
		pstmt.setString(2, pdto.getPartNo());
		pstmt.executeUpdate();
		}
		
		conn.commit();
		
	} catch (Exception e) {
		throw e;
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		}
		try {
			conn.setAutoCommit(true);
		} catch (Exception e2) {
			throw e2;
		}
	}
}
}
