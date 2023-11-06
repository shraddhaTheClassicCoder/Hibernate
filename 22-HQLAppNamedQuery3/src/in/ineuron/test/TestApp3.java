package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class TestApp3 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Integer id=1;

		try {
			session = HibernateUtil.getSession();

			// Prepare Query object to hold HQL
			Query<Product> query = session
					.createQuery(" FROM in.ineuron.model.Product WHERE id=:id");    
			
			// select
			// *
			// from
			// product where name in("TV","Laptop");

			
			// Set Value to Named Parameter
			query.setParameter("id",1);
		    

			// Execute the query
			Product product = query.uniqueResult();
			
			// process the List Object
			if(product!=null) {
				
				System.out.println(product);
			}
			else 
              System.out.println("Record not available....");
		

		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeUp(session, factory);

		}
	}

}
