package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = null;
		Transaction transaction = null;

		try {
			int studentId = 1;

			// create session
			session = factory.getCurrentSession();
			// start a transaction
			transaction = session.beginTransaction();

			System.out.println("\nGetting Student with Id : " + studentId);
			Student myStudent = session.get(Student.class, studentId);

			if (myStudent != null) {
				System.out.println("Deleting student : " + myStudent);
				session.delete(myStudent);
				System.out.println("Student with Id " + studentId + " deleted successfully");
			} else
				System.out.println("Student with Id : " + studentId + " not found");

			// delete student id = 2
			System.out.println("Deleting student id = 2");
			session.createQuery("delete from Student where id = 2").executeUpdate();
			System.out.println("Student with Id 2 deleted successfully");

			// commit the transaction
			transaction.commit();

			System.out.println("Done!!!");
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			System.out.println("Finally...");
			factory.close();
		}
	}
}
