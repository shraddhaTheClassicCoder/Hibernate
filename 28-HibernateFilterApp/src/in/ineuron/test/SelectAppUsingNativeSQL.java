package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.Filter;

import in.ineuron.model.BankAccount;
import in.ineuron.util.HibernateUtil;

public class SelectAppUsingNativeSQL {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			Filter filter = session.enableFilter("FILTER_BANK_ACCOUNT_STATUS");
			filter.setParameter("accType1", "blocked");
			filter.setParameter("accType2", "closed");

			NativeQuery<BankAccount> nQquery = session.createSQLQuery("select * from bankaccount where balance>=:amt");

			nQquery.setParameter("amt", 22000.0f);

			nQquery.addEntity(BankAccount.class);
			List<BankAccount> account = nQquery.list();

			account.forEach(System.out::println);
			
					
			System.out.println();

			session.disableFilter("FILTER_BANK_ACCOUNT_STATUS");
			NativeQuery<BankAccount> nQuery1 = session.createSQLQuery("select * from bankaccount where balance>=:amt");
			nQuery1.setParameter("amt", 25000.0f);
			nQuery1.addEntity(BankAccount.class);

			List<BankAccount> account1 = nQuery1.list();
			account1.forEach(System.out::println);


		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeUp(session, factory);

		}
	}

}
