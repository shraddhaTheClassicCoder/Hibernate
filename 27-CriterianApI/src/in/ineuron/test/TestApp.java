package in.ineuron.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class TestApp {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			// Prepare Query object to hold HQL
			Criteria criteria = session.createCriteria(Product.class); // It act as HQL Query i.e. FROM
																		// in.ineuron.model.product

			// Execute the query
			List<Product> products = criteria.list();

			// process the List Object
			products.forEach(System.out::println);

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeUp(session, factory);

		}
	}

}
