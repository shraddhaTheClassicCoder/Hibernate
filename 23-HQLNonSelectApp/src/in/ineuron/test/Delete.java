package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.ineuron.util.HibernateUtil;

public class Delete {

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
					.createQuery("DELETE FROM in.ineuron.model.Product WHERE id= :id");// select
																														// *
																														// from
																														// product;

			// Set The Parameter values
			query.setParameter("id", 10);
			
			// Execute the query
			count = query.executeUpdate();

			// process the List Object
			flag = true;

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
                System.out.println("Record is Deleted with id"+count);
			} else {
				transaction.rollback();
				System.out.println("Record is not available for deletion with id"+count);

			}
			HibernateUtil.closeUp(session, factory);

		}
	}

}
