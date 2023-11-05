package in.ineuron.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtilDb1;
import in.ineuron.util.HibernateUtilDb2;

public class ITransferDaoImpl implements ITDao {

	@SuppressWarnings("finally")
	public String transferProductById(Integer id) {

		Session sessionDB1 = HibernateUtilDb1.getSession();
		Session sessionDB2 = HibernateUtilDb2.getSession();

		boolean flag = false;

		Transaction tx = null;
		Product product=null;
		
		Integer idValue = 0;

		if (sessionDB1 != null) 

			 product = sessionDB1.get(Product.class, id);

			if (product == null) 
				return "rexord not found";
			 else {
				try {
				tx = sessionDB2.beginTransaction();

				idValue = (Integer) sessionDB2.save(product);
				flag = true;

			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(flag) {
					tx.commit();
					return "Data copied from telusko to hibernate db with the id ::" + idValue;
				}else {
					tx.rollback();
					return "Data failed to copy telusko to hibernate db with the id ::" + idValue;
					
				}
			}

		}

		
	
		

	}}
