package gui;

import service.EmployeeManager;
import service.Manager;
import vo.Employee;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeUI implements UI{
    Scanner scanner;
    Manager manager;

    public EmployeeUI(){
        scanner = new Scanner(System.in);
        manager = new EmployeeManager();

        String select;

        while(true){
            showMainMenu();

            select = scanner.nextLine();

            switch (select){
                case "1":
                    insertEmployee();
                    break;
                case "2":
                    findEmployee();
                    break;
                case "3":
                    deleteEmplyee();
                    break;
                case "4":
                    manager.showAll();
                    break;
                case "9":
                    System.out.println("exit application.");
                    System.out.println("data will be saved the place");
                    manager.saveFile();
                    System.exit(0);
                    break;
            }
        }
    }

    @Override
    public void showMainMenu() {
        System.out.println("========================================");
        System.out.println("===[KITA employee management app]===");
        System.out.println("========================================");
        System.out.println("1. add new employee");
        System.out.println("2. find employee");
        System.out.println("3. delete employee");
        System.out.println("4. show all employee");
        System.out.println("9. exit");
        System.out.println("========================================");
        System.out.println("select manu : ");
    }

    @Override
    public void insertEmployee() {
        System.out.println("========================================");
        System.out.println("=====[add new employee]=====");
        System.out.println("1. employee ID number : " + (Employee.serial + 1));
        System.out.println("2. employee name : ");
        String name = scanner.nextLine();
        System.out.println("3. employee salary : ");
        int salary = scanner.nextInt();

        ArrayList<String> license = new ArrayList<String>();

        while(true){
            Scanner forLicense = new Scanner(System.in);
            System.out.println("4. License : ");
            String temp = forLicense.nextLine();

            if(temp.length() == 0){
                break;
            }
            license.add(temp);
        }//while

        Employee employee = new Employee(name,salary,license);
        manager.insertEmployee(employee);
    }

    @Override
    public void findEmployee() {
        System.out.println("input employee ID want you find : ");
        String eid = scanner.nextLine();

        if(manager.findEmployee(eid) == null){
            System.out.println("there is no employee who you find.");
        }
        else{
            System.out.println(manager.findEmployee(eid).toString());
        }
    }

    @Override
    public void deleteEmplyee() {
        System.out.println("input employee id want you delete : ");
        String eid = scanner.nextLine();
        Employee emp = manager.findEmployee(eid);

        if(emp == null){
            System.out.println("there is no employee who you want to delete.");
            return;
        }

        System.out.println("Are you sure delete " + emp.getName() + "?(Y/N)");
        String answer = scanner.nextLine();

        if(answer.equalsIgnoreCase("y")){
            manager.deleteEmployee(eid);
            return;
        }
        else{
            System.out.println("delete process are canceled.");
        }
    }
}
