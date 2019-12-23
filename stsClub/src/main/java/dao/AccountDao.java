package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.AccountBean;
import model.FiscalBean;

@Repository
public class AccountDao {
	@Autowired
	private SqlSession sqlSession;// mybatis 쿼리문 실행 변수

	public List<AccountBean> getBasket(List<String> nos) {
		// TODO Auto-generated method stub
		List<AccountBean> accounts = new ArrayList<>();
			accounts = sqlSession.selectList("accounting.basketout", nos);
		
		return accounts;
	}

	public void deleteAccounts(List<String> nos) {
		// TODO Auto-generated method stub
		sqlSession.delete("accounting.delete", nos);
	}

	public void accountUpload(AccountBean dto) {
		// TODO Auto-generated method stub
		sqlSession.insert("accounting.upload", dto);
	}

	public void addYear(int year) {
		// TODO Auto-generated method stub
		sqlSession.insert("accounting.insert", year);
	}

	public List<AccountBean> getAccountsByMonth(Map dats) {
		// TODO Auto-generated method stub

		List<AccountBean> accounts = null;
		accounts = sqlSession.selectList("accounting.byMonth", dats);

		return accounts;
	}

	public List<AccountBean> getAccountsByYear(String yearStr) {
		// TODO Auto-generated method stub
		
		List<AccountBean> accounts = null;
		accounts = sqlSession.selectList("accounting.byYear", yearStr);
		return accounts;
	}

	public List<FiscalBean> getYears() {
		// TODO Auto-generated method stub
		List<FiscalBean> years = new ArrayList<>();
			years = sqlSession.selectList("accounting.selectlist");
		return years;
	}

}
