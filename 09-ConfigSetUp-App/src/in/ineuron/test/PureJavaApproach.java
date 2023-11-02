package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import in.ineuron.model.Student;

public class PureJavaApproach {

	public static void main(String[] args) {

		SessionFactory factory = null;
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		int id = 25;

		try {

			Configuration configuartion = new Configuration();

			configuartion.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
			configuartion.setProperty("hibernate.connection.url", "jdbc:mysql:///telusko");
			configuartion.setProperty("hibernate.connection.username", "root");
			configuartion.setProperty("hibernate.connection.password", "root");

			configuartion.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
			configuartion.setProperty("hibernate.show_sql", "true");
			configuartion.setProperty("hibernate.format_sql", "true");
			configuartion.setProperty("hibernate.hbm2ddl.auto", "update");

			configuartion.addAnnotatedClass(Student.class);
			
			factory=configuartion.buildSessionFactory();

			session = factory.openSession();

			if (session != null) {
				Student student = session.get(Student.class, id);

				if (student != null) {
					System.out.println(student);

				} else {
					System.out.println("Student with give record is not available::" + id);
				}
			}
		} catch (

		HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
