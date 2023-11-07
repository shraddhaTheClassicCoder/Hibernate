package in.ineuron.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class TestApp4 {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;

		try {
			session = HibernateUtil.getSession();
			// Prepare Query object to hold HQL
			Criteria criteria = session.createCriteria(Product.class); // It act as HQL Query i.e. FROM
																		// in.ineuron.model.product

			ProjectionList list = Projections.projectionList();
			list.add(Projections.property("name"));
			list.add(Projections.property("qty"));

			criteria.setProjection(list);// HQL=> SELECT pname,qty FROM in.ineuron.model.Product

			// Apply some conditions

			Criterion cond1 = Restrictions.ge("price", 15000);
			Criterion cond2 = Restrictions.lt("price", 28000);

			criteria.add(cond1);
			criteria.add(cond2);

			Order order = Order.asc("name");

			criteria.addOrder(order);
			// Execute the query
			List<Object[]> products = criteria.list();

			// process the List Object
			System.out.println("PNAME\tQTY");
			products.forEach(row -> {
				for (Object obj : row) {
					System.out.print(obj + "\t");
				}
				System.out.println();
			});

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeUp(session, factory);

		}
	}

}
