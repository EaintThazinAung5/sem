
package com.napier.sem;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 1000);

        assertNotNull(app, "App should be created");
    }

    @Test
    void testGetEmployee()
    {
        Employee emp = app.getEmployee(255530);

        assertNotNull(emp, "Employee should exist in the database");
        assertEquals(255530, emp.emp_no);
        assertEquals("Ronghao", emp.first_name);
        assertEquals("Garigliano", emp.last_name);
    }

    @Test
    void testAddEmployee()
    {
        Employee emp = new Employee();
        emp.emp_no = (int) (System.currentTimeMillis() % 1000000) + 1000000;
        emp.first_name = "Kevin";
        emp.last_name = "Chalmers";

        app.addEmployee(emp);

        Employee result = app.getEmployee(emp.emp_no);

        assertNotNull(result, "New employee should exist in the database");
        assertEquals(emp.emp_no, result.emp_no);
        assertEquals("Kevin", result.first_name);
        assertEquals("Chalmers", result.last_name);
    }

    @AfterAll
    static void cleanup()
    {
        if (app != null)
        {
            app.disconnect();
        }
    }
}
