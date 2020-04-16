package com.luv2code.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create sesssion
		Session session = factory.getCurrentSession();

		Transaction transaction = null;

		try {
			// create student object
			System.out.println("Creating new Student Object...");
			String theDateOfBirthStr = "31/12/1998";
			Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
			Student tempStudent = new Student("Sid", "Nikki", "sn@luv2code.com", theDateOfBirth);

			// start a transaction
			// session.beginTransaction();
			// OR
			transaction = session.beginTransaction();

			// save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent);

			// commit transaction
			// session.getTransaction().commit();
			// OR
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
