package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter department's name: ");
		String departmentName = sc.next();

		System.out.println("\nEnter worker data: ");
		System.out.print("Name: ");
		String workerName = sc.next();
		System.out.print("Level: ");
		String workerLevel = sc.next();
		System.out.print("Base salary: ");
		double workerBaseSalary = sc.nextDouble();

		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), workerBaseSalary,
				new Department(departmentName));

		System.out.print("\nHow many contracts to this worker? ");
		int totalContracts = sc.nextInt();

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		for (int i = 1; i <= totalContracts; i++) {

			System.out.println("\nEnter contract #" + i + " data:");
			System.out.print("Date (DD/MM/YYYY): ");
			Date contractDate = formato.parse(sc.next());
			System.out.print("Value per hour: ");
			double value = sc.nextDouble();
			System.out.print("Duration (hours): ");
			int hours = sc.nextInt();

			// Intanciando contrato
			HourContract contract = new HourContract(contractDate, value, hours);
			// Associando contrato ao trabalhador
			worker.addContract(contract);
		}

		System.out.print("\nEnter month and year to calculate income (MM/YYYY): ");
		String monthAndYear = sc.next();
		int month = Integer.parseInt(monthAndYear.substring(0, 2));
		int year = Integer.parseInt(monthAndYear.substring(3));

		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));

		sc.close();
	}

}
