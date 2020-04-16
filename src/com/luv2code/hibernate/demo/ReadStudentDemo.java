package com.luv2code.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			String theDateOfBirthStr = "16/04/1998";
			Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
			Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com", theDateOfBirth);

			// start a transaction
			transaction = session.beginTransaction();

			// save the student object
			System.out.println("Saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);

			// commit transaction
			transaction.commit();

			// MY NEW CODE

			// find out the stuent's id : primary key
			System.out.println("Saved student. Generated Id : " + tempStudent.getId());

			// now get a new session and start transaction
			session = factory.getCurrentSession();
			transaction = session.beginTransaction();

			// retrieve student based on the id : primary key
			System.out.println("\nGetting student with id : " + tempStudent.getId());
			Student myStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get Complete : " + myStudent);

			// commit the transaction
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
