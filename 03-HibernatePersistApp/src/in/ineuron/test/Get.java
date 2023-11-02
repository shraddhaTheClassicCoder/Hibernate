package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class Get {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		Integer id = 21;
		try {

			session = HibernateUtil.getSession();
			Student student = new Student();

			if (session != null) {

				student = session.get(Student.class, id);

				if (student != null) {
					System.out.println("student Id:: " + student.getSid());
					System.out.println("student Name::" + student.getSname());
					System.out.println("student Age::" + student.getSage());
					System.out.println("student Address::" + student.getSaddress());
				} else {
					System.out.println("Student with Given id not available" + id);
				}
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		HibernateUtil.closeUp(session, factory);
	}

}
