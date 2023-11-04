package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.ineuron.model.InsurancePolicy;
import in.ineuron.util.HibernateUtil;

public class SelectRecord {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		Long id = 1L;
		InsurancePolicy account = null;

		// Integer id = 25;
		try {
			session = HibernateUtil.getSession();

			InsurancePolicy policy1 = session.get(InsurancePolicy.class, 1L);
			System.out.println("1:: "+policy1);
			System.out.println("------------------------");//gets from DB put in L1-cache
			
			InsurancePolicy policy2 = session.get(InsurancePolicy.class, 1L);
			System.out.println("2:: "+policy2);
			System.out.println("------------------------");//gets from L1-cache
			
			session.evict(policy1);//Remove policy object from L1-cache
			
			InsurancePolicy policy3 = session.get(InsurancePolicy.class, 1L);
			System.out.println("3:: "+policy3);
			System.out.println("------------------------");//gets from DB put in L1-cache
			
			InsurancePolicy policy4 = session.get(InsurancePolicy.class, 1L);
			System.out.println("4:: "+policy4);
			System.out.println("------------------------");//gets from L1-cache
			
			InsurancePolicy policy5 = session.get(InsurancePolicy.class, 2L);
			System.out.println("5:: "+policy5);
			System.out.println("------------------------");//gets from DB put in L1-cache
			
			session.clear();//remove all objects from the cache
			
			InsurancePolicy policy6 = session.get(InsurancePolicy.class, 1L);
			System.out.println("6:: "+policy6);
			System.out.println("------------------------");//gets from DB and put it in L1-cache
			
			InsurancePolicy policy7 = session.get(InsurancePolicy.class, 2L);
			System.out.println("7:: "+policy7);
			System.out.println("------------------------");//gets from DB put it in L1-cache
		}
		catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			
			HibernateUtil.closeUp(session, factory);
		}

	}

}
