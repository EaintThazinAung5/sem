# USE CASE: 3 Produce a Report on the Salary of Employees in My Department

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *department manager* I want *to produce a report on the salary of employees in my department* so that *I can support financial reporting for my department.*

### Scope

Company.

### Level

Primary task.

### Preconditions

The department manager is known. Database contains current employee salary data.

### Success End Condition

A report is available for the department manager.

### Failed End Condition

No report is produced.

### Primary Actor

Department Manager.

### Trigger

A request for salary information is made by the department manager.

## MAIN SUCCESS SCENARIO

1. Department manager requests salary information for their department.
2. Department manager requests the salary information of employees in their department.
3. System extracts the current salary information of all employees in the department.
4. System provides the report to the department manager.

## EXTENSIONS

3. **No employees are found in the department**:
    1. System informs the department manager that no employee salary information is available.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0