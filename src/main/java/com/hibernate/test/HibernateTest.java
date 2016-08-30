package com.hibernate.test;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateTest {

	public static void main(String[] args) throws Exception {
		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
//			User_info user_info = (User_info) session.load(User_info.class, "601653489");
//			session.save(new User_info("6016534891","wj8621174","0","哈哈", new Timestamp(System.currentTimeMillis())));
//			session.delete(user_info);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null){
				transaction.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

}
