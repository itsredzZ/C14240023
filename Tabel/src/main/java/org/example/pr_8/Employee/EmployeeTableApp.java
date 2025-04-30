package org.example.pr_8.Employee;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeTableApp extends Application {

    private final String url = "jdbc:postgresql://localhost:5432/oracle_hr";
    private final String user = "postgres";
    private final String password = "____";

    private TableView<Employees> table = new TableView<>();

    @Override
    public void start(Stage stage) {
        TableColumn<Employees, Integer> idCol = new TableColumn<>("Employee ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

        TableColumn<Employees, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Employees, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Employees, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Employees, String> phoneCol = new TableColumn<>("Phone Number");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Employees, String> hireDateCol = new TableColumn<>("Hire Date");
        hireDateCol.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        TableColumn<Employees, String> jobIdCol = new TableColumn<>("Job ID");
        jobIdCol.setCellValueFactory(new PropertyValueFactory<>("jobId"));

        TableColumn<Employees, Double> salaryCol = new TableColumn<>("Salary");
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<Employees, Double> commissionCol = new TableColumn<>("Commission %");
        commissionCol.setCellValueFactory(new PropertyValueFactory<>("commissionPct"));

        TableColumn<Employees, Integer> managerIdCol = new TableColumn<>("Manager ID");
        managerIdCol.setCellValueFactory(new PropertyValueFactory<>("managerId"));

        TableColumn<Employees, Integer> departmentIdCol = new TableColumn<>("Department ID");
        departmentIdCol.setCellValueFactory(new PropertyValueFactory<>("departmentId"));

        table.getColumns().addAll(idCol, firstNameCol, lastNameCol, emailCol, phoneCol,
                hireDateCol, jobIdCol, salaryCol, commissionCol, managerIdCol, departmentIdCol);

        table.setItems(getEmployees());

        TableStyle.applyStyle(table, stage, "Employee Report");

    }

    private ObservableList<Employees> getEmployees() {
        ObservableList<Employees> employees = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM employees";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int employeeId = rs.getInt("employee_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String hireDate = rs.getString("hire_date");
                String jobId = rs.getString("job_id");
                double salary = rs.getDouble("salary");
                double commissionPct = rs.getDouble("commission_pct");
                int managerId = rs.getInt("manager_id");
                int departmentId = rs.getInt("department_id");

                employees.add(new Employees(
                        employeeId,
                        safeString(firstName),
                        safeString(lastName),
                        safeString(email),
                        safeString(phoneNumber),
                        safeString(hireDate),
                        safeString(jobId),
                        salary,
                        commissionPct,
                        managerId,
                        departmentId
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private String safeString(String input) {
        return input == null ? "-" : input;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
