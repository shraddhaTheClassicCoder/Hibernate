package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import in.ineuron.util.HibernateUtil;

public class DeleteAppUsingHQL {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction transaction = null;
		Boolean flag = false;
		int rowCount=0;

		try {
			session = HibernateUtil.getSession();

			transaction = session.beginTransaction();

			Query query=session.createQuery("update in.ineuron.model.BankAccount set status='closed' where accno=:no");
			
			query.setParameter("no",6);

			rowCount =query.executeUpdate();			
			
			flag=true;

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Object status changed to closed/blocked==> soft deletion" + rowCount);
			} else {
				transaction.rollback();
				System.out.println("object status not changed..." + rowCount);
			}
			HibernateUtil.closeUp(session, factory);

		}
	}

}
