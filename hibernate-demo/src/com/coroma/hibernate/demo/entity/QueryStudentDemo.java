package com.coroma.hibernate.demo.entity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastName='Huang'
			theStudents = session.createQuery("from Student s where s.lastName='Huang'").getResultList();
			
			// display the students
			System.out.println("\nStudents who have last name of Huang");
			displayStudents(theStudents);
			
			// query students: lastName='Huang' OR firstName='Corey'
			theStudents = session.createQuery("from Student s where s.lastName='Huang' OR s.firstName='Corey'").getResultList();
			
			// display the students
			System.out.println("\nStudents who have last name of Huang OR first name Corey");
			displayStudents(theStudents);
			
			// query students where email LIKE '%coroma.com.au'
			theStudents = session.createQuery("from Student s where s.email LIKE '%coroma.com.au'").getResultList();
						
			// display the students
			System.out.println("\nStudents who have email LIKE '%coroma.com.au'");
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
