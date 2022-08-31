package com.ssfms;

import java.sql.SQLException;
import java.util.List;

public interface ProdDAO {
	public void reg_product (ProductDTO pdto) throws SQLException; // 제품등록
	public void del_product (String prodNo) throws SQLException; // 제품삭제
	public List<ProdDTO> list_product() throws SQLException; // 제품 목록 보기
	
	public void producing (List<ProdDTO>plist, List<ProdDTO>ulist) throws SQLException; // 생산(재료사용 + 제품 생산)
	
	public ProdDTO readPart(String partNo) throws SQLException; //부품재고
	public List<BuyDTO> listPart() throws SQLException;// 부품 리스트 불러오기
	
	public void buyList_write(List<ProdDTO> list) throws SQLException; // 부품구매 요청리스트 작성
	public void buy_offer_cancel(List<ProdDTO> list) throws SQLException; // 부품구매 요청리스트 삭제
	public List<ProdDTO> list_Buy_offer() throws SQLException; //구매요청 리스트 불러오기
	public List<AccDTO> listAccount_prod() throws SQLException; // 생산부서 전표리스트
}
