package com.hibernate.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.entity.Role_info;

public class HibernateTest {

//	public static void main(String[] args) throws Exception {
//		Session session = HibernateUtil.getSession();
//		Transaction transaction = null;
//		try {
//			transaction = session.beginTransaction();
//			String hql = " select pk_jar,code from mtws_jar where pk_jar='1001A4100000D721013 '";   
//	        Query query = session.createSQLQuery(hql); 
//	        //默认查询出来的list里存放的是一个Object数组   
//	        List<Object[]> list = query.list();  
//	        for(Object[] arr : list){
//	        	System.out.println(arr[0]);
//	        	System.out.println(arr[1]);
//	        }
//			transaction.commit();
//		} catch (Exception e) {
////			if (transaction != null){
////				transaction.rollback();
////			}
//			throw e;
//		} finally {
//			session.close();
//			HibernateUtil.shutdown();
//		}
//	}
	
	public static void main(String[] args){
		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
//			Mo_Type type = new Mo_Type();
//			type.setUuid(UUID.randomUUID().toString());
//			type.setTs(new Date());
//			
//			session.save(type);
//			Role_info ro = new Role_info();
//			session.save(ro);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}
	}

}
