package com.ssfms;

import java.sql.SQLException;
import java.util.List;

public interface BuyDAO {
	
	//매입처
	public int insertShop (BuyDTO buydto)throws SQLException;
	public int updateShop (BuyDTO buydto)throws SQLException;
	public int deleteShop (BuyDTO buydto)throws SQLException;
	public List<BuyDTO> listShop();
	
	
	//구매 + 원자재
	public int insertBuy (BuyDTO buydto) throws SQLException; //원자재 발주
	public List<BuyDTO> listBuy(); //구매내역 조회
	public List<BuyDTO> partlistAll(String partNo); //원자재 재고조회
	public int insertPart (BuyDTO buydto) throws SQLException; //원자재 등록
	public int searchBuyState (BuyDTO buydto) throws SQLException;
	
	
	//전표등록
	public int insertAccBuy (AccDTO accdto, EmpDTO empdto) throws SQLException;
	public int deleteAccBuy (AccDTO accdto) throws SQLException; ///전표취소-상태변경
	public List<AccDTO> listAccBuy();
	
	//반품등록
	public int insertBanpum (BuyDTO buydto) throws SQLException;
	public List<BuyDTO> listBanpum();
	
	//원자재요청 조회
	public List<BuyDTO> applyList();
	public int deleteApply (BuyDTO buydto) throws SQLException;
	
	
	//세금계산서 출력하기
	public List<BuyDTO> listBuyTaxBill();
	
	
	
	
}
