package com.coroma.springdemomongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoMongodbApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoMongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();

		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));

		System.out.println("-------------------------------");
		System.out.println("Customers found with findAll():");

		for (Customer customer : repository.findAll()) {
			System.out.println(customer.getFirstName() + " " +customer.getLastName());
		}

		// fetch an individual customer
		System.out.println("-------------------------------");
		System.out.println("Customer found with findByFirstName('Alice'):");
		Customer aliceCustomer = repository.findByFirstName("Alice");
		System.out.println(aliceCustomer.getFirstName() + " " + aliceCustomer.getLastName());

		System.out.println("--------------------------------");
		System.out.println("Customers found with findByLastName('Smith'):");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer.getFirstName() + " " +customer.getLastName());
		}

		System.out.println("--------------------------------");
		System.out.println("Customer found with findByFirstName('Bob'):");
		Customer bobCustomer = repository.findByFirstName("Bob");
		System.out.println(bobCustomer.getId() + ": " + bobCustomer.getFirstName() + " " + bobCustomer.getLastName());
		bobCustomer.setFirstName("Hector");
		repository.save(bobCustomer);
		System.out.println("Customers after update with Hector:");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer.getId() + ": " + customer.getFirstName() + " " +customer.getLastName());
		}
	}
}
