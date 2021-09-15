package Application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import Entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the full file path: ");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
		
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			while(line != null) {
				String [] vect = line.split(",");
				String name = vect[0];
				String email = vect[1];
				double salary = Double.parseDouble(vect[2]);
				list.add(new Employee(name, email, salary));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double comSalary = sc.nextDouble();
			
			List<String> overSalary = list.stream()
					.filter(s -> s.getSalary()>comSalary)
					.map(s -> s.getEmail())
					.sorted()
					.collect(Collectors.toList());
			System.out.println("Email of people whose salary is more than 2000.00: ");
			overSalary.forEach(System.out::println);
			
			double sumSalary = list.stream()
					.filter(s -> s.getName().charAt(0) == 'M')
					.map(s -> s.getSalary())
					.reduce(0.0, (x,y) ->x+y);
					
					//.reduce(0.0, (x,y) -> x+y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + sumSalary);
		}
		
		catch(IOException e) {
			System.out.println("Error :" + e.getMessage());
		}
	
		sc.close();
	}

}
