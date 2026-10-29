package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    private Connection con = null;

    /**
     * Connects to the MySQL database.
     *
     * @param location Database hostname and port
     * @param delay Delay in milliseconds before each connection attempt
     */
    public void connect(String location, int delay)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            return;
        }

        int retries = 10;

        for (int i = 0; i < retries; i++)
        {
            System.out.println("Connecting to database...");

            try
            {
                Thread.sleep(delay);

                con = DriverManager.getConnection(
                        "jdbc:mysql://" + location
                                + "/employees?allowPublicKeyRetrieval=true&useSSL=false",
                        "root",
                        "example"
                );

                System.out.println("Successfully connected");
                return;
            }
            catch (SQLException e)
            {
                System.out.println(
                        "Failed to connect to database attempt " + (i + 1)
                );
                System.out.println(e.getMessage());
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                System.out.println("Database connection interrupted.");
                return;
            }
        }

        System.out.println("Could not connect after " + retries + " attempts.");
    }

    /**
     * Disconnects from the database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                con.close();
            }
            catch (SQLException e)
            {
                System.out.println("Error closing database connection");
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Gets a department by its name, including its manager.
     *
     * @param dept_name Department name.
     * @return Department details, or null if not found or an error occurs.
     */
    public Department getDepartment(String dept_name)
    {
        String sql =
                "SELECT d.dept_no, d.dept_name, " +
                        "m.emp_no AS manager_emp_no, " +
                        "m.first_name AS manager_first_name, " +
                        "m.last_name AS manager_last_name " +
                        "FROM departments d " +
                        "LEFT JOIN dept_manager dm ON d.dept_no = dm.dept_no " +
                        "AND dm.to_date = '9999-01-01' " +
                        "LEFT JOIN employees m ON dm.emp_no = m.emp_no " +
                        "WHERE d.dept_name = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, dept_name);

            try (ResultSet rset = pstmt.executeQuery())
            {
                if (rset.next())
                {
                    Department dept = new Department();
                    dept.dept_no = rset.getString("dept_no");
                    dept.dept_name = rset.getString("dept_name");

                    if (rset.getObject("manager_emp_no") != null)
                    {
                        Employee manager = new Employee();
                        manager.emp_no = rset.getInt("manager_emp_no");
                        manager.first_name =
                                rset.getString("manager_first_name");
                        manager.last_name =
                                rset.getString("manager_last_name");

                        dept.manager = manager;
                    }

                    return dept;
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get department details");
        }

        return null;
    }

    /**
     * Gets an employee by employee number.
     *
     * @param ID Employee number.
     * @return Employee details, or null if not found or an error occurs.
     */
    public Employee getEmployee(int ID)
    {
        String sql =
                "SELECT e.emp_no, e.first_name, e.last_name, " +
                        "t.title, s.salary, d.dept_no, d.dept_name, " +
                        "m.emp_no AS manager_emp_no, " +
                        "m.first_name AS manager_first_name, " +
                        "m.last_name AS manager_last_name " +
                        "FROM employees e " +
                        "LEFT JOIN titles t ON e.emp_no = t.emp_no " +
                        "AND t.to_date = '9999-01-01' " +
                        "LEFT JOIN salaries s ON e.emp_no = s.emp_no " +
                        "AND s.to_date = '9999-01-01' " +
                        "LEFT JOIN dept_emp de ON e.emp_no = de.emp_no " +
                        "AND de.to_date = '9999-01-01' " +
                        "LEFT JOIN departments d ON de.dept_no = d.dept_no " +
                        "LEFT JOIN dept_manager dm ON de.dept_no = dm.dept_no " +
                        "AND dm.to_date = '9999-01-01' " +
                        "LEFT JOIN employees m ON dm.emp_no = m.emp_no " +
                        "WHERE e.emp_no = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setInt(1, ID);

            try (ResultSet rset = pstmt.executeQuery())
            {
                if (rset.next())
                {
                    Employee emp = new Employee();
                    emp.emp_no = rset.getInt("emp_no");
                    emp.first_name = rset.getString("first_name");
                    emp.last_name = rset.getString("last_name");
                    emp.title = rset.getString("title");
                    emp.salary = rset.getInt("salary");

                    if (rset.getString("dept_no") != null)
                    {
                        Department dept = new Department();
                        dept.dept_no = rset.getString("dept_no");
                        dept.dept_name = rset.getString("dept_name");

                        if (rset.getObject("manager_emp_no") != null)
                        {
                            Employee manager = new Employee();
                            manager.emp_no =
                                    rset.getInt("manager_emp_no");
                            manager.first_name =
                                    rset.getString("manager_first_name");
                            manager.last_name =
                                    rset.getString("manager_last_name");

                            dept.manager = manager;
                        }

                        emp.dept = dept;
                    }

                    if (rset.getObject("manager_emp_no") != null)
                    {
                        Employee manager = new Employee();
                        manager.emp_no = rset.getInt("manager_emp_no");
                        manager.first_name =
                                rset.getString("manager_first_name");
                        manager.last_name =
                                rset.getString("manager_last_name");

                        emp.manager = manager;
                    }

                    return emp;
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
        }

        return null;
    }

    /**
     * Gets an employee by first and last name.
     *
     * @param first_name Employee's first name.
     * @param last_name Employee's last name.
     * @return Employee details, or null if not found or an error occurs.
     */
    public Employee getEmployee(String first_name, String last_name)
    {
        String sql =
                "SELECT emp_no FROM employees " +
                        "WHERE first_name = ? AND last_name = ? " +
                        "ORDER BY emp_no ASC LIMIT 1";

        try (PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);

            try (ResultSet rset = pstmt.executeQuery())
            {
                if (rset.next())
                {
                    int employeeId = rset.getInt("emp_no");
                    return getEmployee(employeeId);
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to find employee by name");
        }

        return null;
    }



    /**
     * Adds a new employee to the database.
     *
     * @param emp Employee to add.
     */
    public void addEmployee(Employee emp)
    {
        if (emp == null)
        {
            System.out.println("Employee cannot be null");
            return;
        }

        String sql =
                "INSERT INTO employees " +
                        "(emp_no, birth_date, first_name, last_name, gender, hire_date) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setInt(1, emp.emp_no);
            pstmt.setDate(2, java.sql.Date.valueOf("1990-01-01"));
            pstmt.setString(3, emp.first_name);
            pstmt.setString(4, emp.last_name);
            pstmt.setString(5, "M");
            pstmt.setDate(6, java.sql.Date.valueOf("2026-01-01"));

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0)
            {
                System.out.println("Employee added successfully");
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Failed to add employee", e);
        }
    }



    /**
     * Displays employee details.
     *
     * @param emp Employee to display.
     */
    public void displayEmployee(Employee emp)
    {
        if (emp != null)
        {
            System.out.println(
                    emp.emp_no + " " +
                            emp.first_name + " " +
                            emp.last_name + "\n" +
                            "Title: " + emp.title + "\n" +
                            "Salary: " + emp.salary + "\n" +
                            "Department: " +
                            (emp.dept != null
                                    ? emp.dept.dept_name
                                    : "No department") + "\n" +
                            "Manager: " +
                            (emp.manager != null
                                    ? emp.manager.first_name + " " +
                                    emp.manager.last_name
                                    : "No manager") + "\n"
            );
        }
    }

    /**
     * Gets current employees and their salaries in a department.
     *
     * @param dept Department to report on.
     * @return Employees and salaries, or null if an error occurs.
     */
    public ArrayList<Employee> getSalariesByDepartment(Department dept)
    {
        ArrayList<Employee> employees = new ArrayList<>();

        if (dept == null)
        {
            return employees;
        }

        String sql =
                "SELECT e.emp_no, e.first_name, e.last_name, s.salary " +
                        "FROM employees e " +
                        "JOIN salaries s ON e.emp_no = s.emp_no " +
                        "JOIN dept_emp de ON e.emp_no = de.emp_no " +
                        "JOIN departments d ON de.dept_no = d.dept_no " +
                        "WHERE s.to_date = '9999-01-01' " +
                        "AND de.to_date = '9999-01-01' " +
                        "AND d.dept_no = ? " +
                        "ORDER BY e.emp_no ASC";

        try (PreparedStatement pstmt = con.prepareStatement(sql))
        {
            pstmt.setString(1, dept.dept_no);

            try (ResultSet rset = pstmt.executeQuery())
            {
                while (rset.next())
                {
                    Employee emp = new Employee();
                    emp.emp_no = rset.getInt("emp_no");
                    emp.first_name = rset.getString("first_name");
                    emp.last_name = rset.getString("last_name");
                    emp.salary = rset.getInt("salary");
                    emp.dept = dept;

                    employees.add(emp);
                }
            }

            return employees;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get department salary details");
            return null;
        }
    }

    /**
     * Gets all current employees and their salaries.
     *
     * @return Employees and salaries, or null if an error occurs.
     */
    public ArrayList<Employee> getAllSalaries()
    {
        String sql =
                "SELECT e.emp_no, e.first_name, e.last_name, s.salary " +
                        "FROM employees e " +
                        "JOIN salaries s ON e.emp_no = s.emp_no " +
                        "WHERE s.to_date = '9999-01-01' " +
                        "ORDER BY e.emp_no ASC";

        ArrayList<Employee> employees = new ArrayList<>();

        try (Statement stmt = con.createStatement();
             ResultSet rset = stmt.executeQuery(sql))
        {
            while (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.salary = rset.getInt("salary");

                employees.add(emp);
            }

            return employees;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * Prints a list of employees and their salaries.
     *
     * @param employees Employees to print.
     */
    public void printSalaries(ArrayList<Employee> employees)
    {
        // Check employees is not null
        if (employees == null)
        {
            System.out.println("No employees");
            return;
        }

        // Print header
        System.out.println(String.format(
                "%-10s %-15s %-20s %-10s",
                "Emp No", "First Name", "Last Name", "Salary"));

        // Loop over all employees in the list
        for (Employee emp : employees)
        {
            // Skip if the employee is null
            if (emp == null)
                continue;

            System.out.println(String.format(
                    "%-10d %-15s %-20s %-10d",
                    emp.emp_no, emp.first_name, emp.last_name, emp.salary));
        }
    }

    public static void main(String[] args)
    {
        App app = new App();

        String location = "localhost:33060";
        int delay = 1000;

        if (args.length >= 2)
        {
            location = args[0];

            try
            {
                delay = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid delay value.");
                return;
            }
        }
        else if (args.length == 1)
        {
            location = args[0];
        }

        app.connect(location, delay);

        if (app.con == null)
        {
            System.out.println("Could not connect to the database.");
            return;
        }

        Department dept = app.getDepartment("Sales");

        if (dept != null)
        {
            System.out.println("Department: " + dept.dept_name);

            if (dept.manager != null)
            {
                System.out.println(
                        "Manager: " + dept.manager.first_name + " "
                                + dept.manager.last_name
                );
            }

            ArrayList<Employee> employees =
                    app.getSalariesByDepartment(dept);

            app.printSalaries(employees);
        }
        else
        {
            System.out.println("Department not found.");
        }

        app.disconnect();
    }
}