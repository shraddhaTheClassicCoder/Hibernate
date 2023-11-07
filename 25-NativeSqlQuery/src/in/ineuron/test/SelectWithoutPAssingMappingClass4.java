package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.build.AllowSysOut;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import in.ineuron.model.Student;
import in.ineuron.util.HibernateUtil;

public class SelectWithoutPAssingMappingClass4 {
	public static void main(String[] args) {

		Session session = null;
		SessionFactory factory = null;

		try {
			session = HibernateUtil.getSession();

			@SuppressWarnings("unchecked")
			NativeQuery<Object[]> nativeQuery = session
					.createSQLQuery("SELECT SID,SNAME,SAGE,SADDRESS FROM STUDENT WHERE sname=:name AND sage=:age AND saddress=:addr AND sid=:id");

			// setting parameter
			nativeQuery.setParameter("id",5);
			nativeQuery.setParameter("name","kirti");
			nativeQuery.setParameter("age",28);
			nativeQuery.setParameter("addr","Kalyan");
			
			//Binding the datatypes of output paramater
			
			nativeQuery.addScalar("SID",StandardBasicTypes.INTEGER);
			nativeQuery.addScalar("SNAME",StandardBasicTypes.STRING);
			nativeQuery.addScalar("SGAGE",StandardBasicTypes.INTEGER);
			nativeQuery.addScalar("SADDRESS",StandardBasicTypes.STRING);
			
			
			// Mapping with Entity Class
			nativeQuery.addEntity(Student.class);

			// Executing Result
			List<Object[]> policies = nativeQuery.getResultList();

			// processing result
            System.out.println("SNAME\tSAGE");
            policies.forEach(row -> {
				for (Object obj : row) {
					System.out.print(obj+"\t");
				}
				System.out.println();
			});

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}
}
