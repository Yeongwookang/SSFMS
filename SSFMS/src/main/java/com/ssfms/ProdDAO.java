package com.ssfms;

import java.sql.SQLException;

public interface ProdDAO {
	public void reg_product (ProductDTO pdto) throws SQLException; // 제품등록
	public void del_product (String prodNo) throws SQLException; // 제품삭제
	public void using_part(ProdDTO pdto ) throws SQLException;// 부품 사용
	public ProdDTO producing (ProdDTO pdto) throws SQLException; // 생산 입고
	public int productStock (String productNo) throws SQLException; //제품 재고 확인
}
