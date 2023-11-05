package in.ineuron.test;

import in.ineuron.dao.ITransferDaoImpl;
import in.ineuron.util.HibernateUtilDb1;
import in.ineuron.util.HibernateUtilDb2;

import org.hibernate.SessionFactory;

import in.ineuron.dao.ITDao;

public class InterActWithMultipleDb {

	public static void main(String[] args) {

		ITDao it = new ITransferDaoImpl();

		System.out.println(it.transferProductById(2));

	}
}
