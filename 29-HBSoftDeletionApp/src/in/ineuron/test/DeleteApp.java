package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import in.ineuron.model.BankAccount;
import in.ineuron.util.HibernateUtil;

public class DeleteApp {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction transaction = null;
		Boolean flag = false;

		try {
			session = HibernateUtil.getSession();

			transaction = session.beginTransaction();

			BankAccount account = new BankAccount();

			account.setAccno(7);

			session.delete(account);

			flag = true;

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			if(flag) {
				transaction.commit();
				System.out.println("Object status changed to closed/blocked==> soft deletion");
			}else {
				transaction.rollback();
				System.out.println("object status not changed");
			}
			HibernateUtil.closeUp(session, factory);

		}
	}

}
