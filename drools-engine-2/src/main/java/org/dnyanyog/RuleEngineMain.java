package org.dnyanyog;

import java.util.Scanner;

import org.dnyanyog.config.DroolsBeanFactory;
import org.dnyanyog.dto.Customer;
import org.kie.api.runtime.KieSession;

public class RuleEngineMain {

	public static void main(String[] args) {

		// Knowledge is everything => kie
		
		KieSession kSession = new DroolsBeanFactory().getKieSession(); // Get build kie container (package)

		if (kSession != null) {
			
			Scanner sc = new Scanner(System.in);
			// Now you can use kSession to insert objects and fire rules
			
			Customer customer = new Customer();
			
			System.out.println("age :");
			int age = sc.nextInt();
			
			System.out.println("gender :");
			String gender = sc.next();
			
			customer.setAge(age);
			customer.setGender(gender);
			
			System.out.println("befor rule fire customer discount: " + customer.getDiscount());

	//		System.out.println("before rule fire customer discount: " + customer.getDiscount());

			kSession.insert(customer); // 

			// Fire the rules
			kSession.fireAllRules();

			// Close the session
			kSession.dispose();

			// Print the result

			System.out.println("customer discount: " + customer.getDiscount());
			
		} else {
			System.err.println("Failed to create KieSession.");
		}
	}
}
