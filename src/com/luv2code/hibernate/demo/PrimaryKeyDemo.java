package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create sesssion
		Session session = factory.getCurrentSession();

		Transaction transaction = null;

		try {
			// create 3 student object
			System.out.println("Creating 3 Student Object...");
			Student tempStudent1 = new Student("Siddarth", "Mishra", "siddarth@luv2code.com");
			Student tempStudent2 = new Student("Nikhita", "Sharma", "nikhita@luv2code.com");

			// start a transaction
			transaction = session.beginTransaction();

			// save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent1);
			session.save(tempStudent2);

			// commit transaction
			transaction.commit();

			System.out.println("Done...");

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			System.out.println("Finally...");
			factory.close();
		}
	}
}
