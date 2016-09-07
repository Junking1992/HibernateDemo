package com.hibernate.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.entity.User_info;

public class HibernateTest {

	public static void main(String[] args) throws Exception {
		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			User_info user_info = (User_info) session.get(User_info.class, "601653489");
//			session.save(new User_info("122382438","wj8621174","冰", (byte)0, null, new Timestamp(System.currentTimeMillis())));
//			session.delete(user_info);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			System.out.println(user_info);
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
