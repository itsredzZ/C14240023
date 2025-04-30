package org.example.pr_8.Commission;

import javafx.application.Application;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeCommissionApp extends Application {
    private final String url = "jdbc:postgresql://localhost:5432/oracle_hr";
    private final String user = "postgres";
    private final String password = "____";

    private TableView<EmployeeCommission> table = new TableView<>();

    @Override
    public void start(Stage stage) {
        TableColumn<EmployeeCommission, String> nameCol = new TableColumn<>("Nama Lengkap");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        TableColumn<EmployeeCommission, Double> salaryCol = new TableColumn<>("Salary");
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        TableColumn<EmployeeCommission, Double> commissionPctCol = new TableColumn<>("Percent Komisi");
        commissionPctCol.setCellValueFactory(new PropertyValueFactory<>("commissionPct"));

        TableColumn<EmployeeCommission, Double> commissionCol = new TableColumn<>("Komisi");
        commissionCol.setCellValueFactory(new PropertyValueFactory<>("commission"));

        TableColumn<EmployeeCommission, Double> totalSalaryCol = new TableColumn<>("Total Gaji");
        totalSalaryCol.setCellValueFactory(new PropertyValueFactory<>("totalSalary"));

        table.getColumns().addAll(nameCol, salaryCol, commissionPctCol, commissionCol, totalSalaryCol);
        table.setItems(getEmployeeCommissions());

        TableStyler.applyStyle(table, stage, "Employee Commission Report");

    }

    private ObservableList<EmployeeCommission> getEmployeeCommissions() {
        ObservableList<EmployeeCommission> employees = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT first_name, last_name, salary, COALESCE(commission_pct, 0) AS commission_pct FROM employees";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                double salary = rs.getDouble("salary");
                double commissionPct = rs.getDouble("commission_pct");
                double commission = salary * commissionPct;
                double totalSalary = salary + commission;

                employees.add(new EmployeeCommission(fullName, salary, commissionPct, commission, totalSalary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

