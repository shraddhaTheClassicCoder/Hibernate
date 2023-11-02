package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.build.AllowSysOut;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class SyncUpdate {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		Integer id = 25;
		try {

			session = HibernateUtil.getSession();
			Student student = new Student();
			student = session.load(Student.class, id);

			System.out.println("Student object before::"+student);
			if (student != null) {
				System.out.println(student);

			}
			System.in.read();//go to db and make the change
			
			session.refresh(student);
			System.out.println("Student object after::"+student);
			
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (flag) {
				tx.commit();
				System.out.println("Record is updated with Given Id" + id);
			} else {
				System.out.println("Record is not updated with Given Id" + id);

			}
			HibernateUtil.closeUp(session, factory);
		}
	}

}
