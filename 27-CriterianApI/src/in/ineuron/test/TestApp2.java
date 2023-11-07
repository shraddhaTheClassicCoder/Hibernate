package in.ineuron.test;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;


public class TestApp2 {

	@SuppressWarnings({ "unchecked", "deprecation", "static-access" })
	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;

		try {
			session = HibernateUtil.getSession();

			// Prepare Query object to hold HQL
			Criteria criteria = session.createCriteria(Product.class); // It act as HQL Query i.e. FROM
																		// in.ineuron.model.product

			//Apply some conditions 
		
			Criterion cond1= Restrictions.ge("price", 15000);
			Criterion cond2=Restrictions.lt("price", 28000);
			
			
			criteria.add(cond1);//HQL=> from in.ineuron.model.Product where price>=1000 and price<=2500
			criteria.add(cond2);
			
			Order order=Order.asc("name");
			criteria.addOrder(order);//HQL=> from in.ineuron.model.Product where price>=1000 and price<=2500 orderby asc(pname)
			
			
			
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
