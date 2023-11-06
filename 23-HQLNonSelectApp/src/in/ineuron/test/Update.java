package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.ineuron.util.HibernateUtil;

public class Update {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Integer count = 0;
		boolean flag = false;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			// Prepare Query object to hold HQL
			Query query = session
					.createQuery("UPDATE in.ineuron.model.Product SET qty=qty + :newQty WHERE name like :initialLetter");// select
																														// *
																														// from
																														// product;

			// Set The Parameter values
			query.setParameter("newQty", 5);
			query.setParameter("initialLetter", "T%");

			// Execute the query
			count = query.executeUpdate();

			// process the List Object
			flag = true;

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
                System.out.println("Record is updated with count"+count);
			} else {
				transaction.rollback();
				System.out.println("Record is not available");

			}
			HibernateUtil.closeUp(session, factory);

		}
	}

}
