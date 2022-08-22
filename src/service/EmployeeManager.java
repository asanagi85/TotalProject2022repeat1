package service;

import vo.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager implements Manager {
    BufferedReader br;
    BufferedWriter bw;
    FileReader fr;
    FileWriter fw;
    File file;
    ArrayList<Employee> eList;

    public EmployeeManager(){
        file = new File("D:\\test text\\employee_22repeat1.txt");

        if(file.exists()){
            eList = new ArrayList<Employee>();
            getFile();
        }
        else{
            try{
                file.createNewFile();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getFile(){
        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String temp;
            while(true){
                temp = br.readLine();

                if(temp == null){
                    break;
                }

                List<String> license = new ArrayList<>();
                String[] data = temp.split(",");
                Employee emp = new Employee();

                emp.setEid(data[0]);
                Employee.serial = Integer.parseInt((data[0]));

                emp.setName(data[1]);
                emp.setSalary(Integer.parseInt(data[2]));

                for(int i = 3; i < data.length; ++i){
                    license.add(data[i]);
                }

                if(license.size() != 0){
                    emp.setLicense(license);
                }

                eList.add(emp);
            }//while
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(fr != null){
                    fr.close();
                }
                if(br != null){
                    br.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveFile() {
        try{
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            for(Employee e : eList){
                bw.write(e.toString() + "\n");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(bw != null){
                    bw.close();
                }
                if(fw != null){
                    fw.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean insertEmployee(Employee employee) {
        return eList.add(employee);
    }

    @Override
    public boolean deleteEmployee(String eid) {
        Employee e = findEmployee(eid);
        return eList.remove(e);
    }

    @Override
    public Employee findEmployee(String eid) {
        for(Employee e : eList){
            if(e.getEid().equals(eid)){
                return e;
            }
        }
        return null;
    }

    @Override
    public void showAll() {
        for(Employee e: eList){
            System.out.println(e.toString());
        }
    }
}
