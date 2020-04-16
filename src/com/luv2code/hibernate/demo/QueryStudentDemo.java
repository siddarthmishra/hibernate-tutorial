package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create sesssion
		Session session = factory.getCurrentSession();

		Transaction transaction = null;

		try {

			// start a transaction
			transaction = session.beginTransaction();

			List<Student> theStudents = null;
			String query;

			// query students
			query = "from Student";
			theStudents = queryStudentList(session, query);

			// display students
			displayStudents(theStudents);

			// query students: lastName='Nikki'
			query = "from Student s where s.lastName = 'Nikki'";
			theStudents = queryStudentList(session, query);

			// display students
			displayStudents(theStudents);

			// query students: lastName='Nikki' OR firstName='Nikhita'
			query = "from Student s where s.lastName = 'Nikki' OR s.firstName = 'Nikhita'";
			theStudents = queryStudentList(session, query);

			// display students
			displayStudents(theStudents);

			// query students where email LIKE '%luv2coe.com'
			query = "from Student s where s.email LIKE '%luv2code.com'";
			theStudents = queryStudentList(session, query);

			// display students
			displayStudents(theStudents);

			// query students where email LIKE '%luv2coe.com'
			query = "from Student s where s.email LIKE '%gmail.com'";
			theStudents = queryStudentList(session, query);

			// display students
			displayStudents(theStudents);

			System.out.println();
			String firstName = (String) session.createNativeQuery("select first_name from student where id = 1")
					.uniqueResult();
			System.out.println(firstName);

			System.out.println();
			session.createNativeQuery("select last_name from student where id = 1").uniqueResultOptional()
			.ifPresentOrElse(System.out::println, () -> System.out.println("No Such Id Exist"));

			transaction.commit();

			System.out.println("\nDone...");

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			System.out.println("Finally...");
			factory.close();
		}
	}

	private static List<Student> queryStudentList(Session session, String query) {
		System.out.println("\n" + query);
		return session.createQuery(query).getResultList();
	}

	private static void displayStudents(List<Student> theStudents) {
		theStudents.forEach(System.out::println);
	}
}
