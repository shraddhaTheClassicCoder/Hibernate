package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.ineuron.model.BankAccount;
import in.ineuron.util.HibernateUtil;

public class InsertRecord {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Long id = null;

		// Integer id = 25;
		try {

			session = HibernateUtil.getSession();
			BankAccount account = new BankAccount();
			System.out.println("Before modification :: " + account);
			if (session != null) {
				transaction = session.beginTransaction();
			}

			if (transaction != null) {
				account.setHolderName("manya");
				account.setBalance(1000.20);
				account.setAccNo((long) 3339876.5);
				account.setType("saving");

				id = (Long) session.save(account);
				flag = true;

			}
		}

		catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Record Saved in the Database with id::" + id);
			} else {
				transaction.rollback();
				System.out.println("Record is not Saved or Updated in the Database");
			}
			HibernateUtil.closeUp(session, factory);
		}

	}

}
