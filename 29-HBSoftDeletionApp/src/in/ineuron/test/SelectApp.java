package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.ineuron.model.BankAccount;
import in.ineuron.util.HibernateUtil;

public class SelectApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction transaction = null;
		Boolean flag = false;

		try {
			session = HibernateUtil.getSession();

			Query<BankAccount> query = session.createQuery("from in.ineuron.model.BankAccount");
			List<BankAccount> account = query.getResultList();
			account.forEach(System.out::println);

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			
			HibernateUtil.closeUp(session, factory);

		}
	}

}
