package org.example.pr_8.Commission;

public class EmployeeCommission {
    private String fullName;
    private double salary;
    private double commissionPct;
    private double commission;
    private double totalSalary;

    public EmployeeCommission(String fullName, double salary, double commissionPct, double commission, double totalSalary) {
        this.fullName = fullName;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.commission = commission;
        this.totalSalary = totalSalary;
    }

    public String getFullName() {
        return fullName;
    }

    public double getSalary() {
        return salary;
    }

    public double getCommissionPct() {
        return commissionPct;
    }

    public double getCommission() {
        return commission;
    }

    public double getTotalSalary() {
        return totalSalary;
    }
}
