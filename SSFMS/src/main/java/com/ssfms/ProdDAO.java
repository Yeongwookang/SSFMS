package com.ssfms;

import java.sql.SQLException;
import java.util.List;

public interface ProdDAO {
	public void reg_product (ProductDTO pdto) throws SQLException; // 제품등록
	public void del_product (String prodNo) throws SQLException; // 제품삭제
	public void using_part(ProdDTO pdto ) throws SQLException;// 부품 사용
	public ProdDTO producing (ProdDTO pdto) throws SQLException; // 생산 입고
	public int productStock (String productNo) throws SQLException; //제품 재고 확인
	public BuyDTO readPart(String partNo) throws SQLException; //부품재고
	public List<AccDTO> listAccount_prod() throws SQLException; // 생산부서 전표리스트
}
