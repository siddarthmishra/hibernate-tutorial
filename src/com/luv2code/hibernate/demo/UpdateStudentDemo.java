package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = null;

		Transaction transaction = null;

		try {
			int studentId = 4;

			// create session
			session = factory.getCurrentSession();
			// start a transaction
			transaction = session.beginTransaction();

			// retrieve student based on primary key
			System.out.println("\nGetting student with id : " + studentId);
			Student myStudent = session.get(Student.class, studentId);

			System.out.println("Updating student...");
			myStudent.setFirstName("Scooby");
			myStudent.setLastName("Doo");
			myStudent.setEmail("scooby@luv2code.com");

			// commit the transaction
			transaction.commit();

			System.out.println("update on stuent id " + studentId + " successfull");

			// NEW CODE

			// create session
			session = factory.getCurrentSession();
			// start a transaction
			transaction = session.beginTransaction();

			System.out.println("Update email for all students");
			int i = session.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();
			if (i > 0)
				System.out.println(i + " records updated");
			else
				System.out.println("No records updated");

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
