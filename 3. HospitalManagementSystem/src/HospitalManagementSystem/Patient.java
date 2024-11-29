package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient{
    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }
    public void addPatient(){
        System.out.print("Enter Patient Name : ");
        String name = scanner.next();
        System.out.print("Enter Patient Age : ");
        int age = scanner.nextInt();
        System.out.print("Enter Patient Gender : ");
        String gender = scanner.next();

        String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                System.out.println("Patient Added Successfully!!");
            }else {
                System.out.println("Failed To Add Patient!!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewPatients(){
        String query = "SELECT * from patients";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()){
            System.out.println("Patients : ");
            System.out.println("+------------+-----------------+--------+-----------+");
            System.out.println("| Patient Id | Name            | Age    | Gender    |");
            System.out.println("+------------+-----------------+--------+-----------+");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10s | %-15s | %-6s | %-9s |\n", id, name, age, gender);
                System.out.println("+------------+-----------------+--------+-----------+");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id){
        String query = "SELECT * FROM patients WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void deletePatientById(int id){
        String query = "DELETE FROM patients WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                System.out.println("Patient Deleted from patients list Successfully!!");
            }else {
                System.out.println("Failed To Delete Patient from patients list!!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
