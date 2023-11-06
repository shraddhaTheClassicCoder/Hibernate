package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class TestApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			// Prepare Query object to hold HQL
			Query<Integer> query = session
					.createQuery("SELECT price FROM in.ineuron.model.Product WHERE name IN(:prod1,:prod2)");// select
																												// *
																												// from
																												// product where name in("TV","Laptop");

			// Set Value to Named Parameter
			query.setParameter("prod1", "TV");
			query.setParameter("prod2", "Laptop");

			// Execute the query
			List<Integer> products = query.getResultList();

			
			System.out.println("PRICE");
			// process the List Object
			products.forEach(System.out::println);

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeUp(session, factory);

		}
	}

}
