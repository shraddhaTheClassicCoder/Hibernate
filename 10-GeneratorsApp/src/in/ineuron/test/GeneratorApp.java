package in.ineuron.test;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class GeneratorApp {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		Integer id=null;
		// Integer id = 25;
		try {

			session = HibernateUtil.getSession();
			Student student = new Student();

			if (session != null) {
				tx = session.beginTransaction();
			}

			if (tx != null) {

				//tx = session.beginTransaction();
				student.setSname("shraddha");
				student.setSage(25);
				student.setSaddress("pune");

				id = (Integer) session.save(student);
				flag = true;

			}
		}

		catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (flag) {
				tx.commit();
				System.out.println("Record Saved in the Database with id::" + id);
			} else {

				System.out.println("Record is not Saved or Updated in the Database");
			}
			HibernateUtil.closeUp(session, factory);
		}

	}

}
