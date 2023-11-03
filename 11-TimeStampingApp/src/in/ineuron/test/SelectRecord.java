package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.ineuron.model.BankAccount;
import in.ineuron.util.HibernateUtil;

public class SelectRecord {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Long id = 1L;
		BankAccount account = null;

		// Integer id = 25;
		try {

			session = HibernateUtil.getSession();
			account = new BankAccount();

			if (session != null) {

				account = session.get(BankAccount.class, id);
				System.out.println("Account before modification::" + account);

				if (account != null) {
					transaction = session.beginTransaction();

					account.setBalance(account.getBalance() + 1000);

					flag = true;

				} else {
					System.out.println("Record is not available with id:" + id);
					
					System.exit(0);
				}
			}

		}

		catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object updated...");
				System.out.println("Account opening date    :: " + account.getOpeningDate());
				System.out.println("Account lastly modified :: " + account.getLastUpdate());
				System.out.println("Account version count   :: " + account.getCount());
			} else {
				transaction.rollback();
				System.out.println("object not updated....");
			}
			HibernateUtil.closeUp(session, factory);
		}

	}

}
