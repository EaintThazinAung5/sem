# USE CASE: 6 View an Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to view an employee's details* so that *the employee's promotion request can be supported.*

### Scope

Company.

### Level

Primary task.

### Preconditions

The employee exists in the database.

### Success End Condition

The employee's current details are displayed to the HR advisor.

### Failed End Condition

The employee's details cannot be displayed.

### Primary Actor

HR Advisor.

### Trigger

An employee's details are required to support a promotion request.

## MAIN SUCCESS SCENARIO

1. HR advisor requests the details of an employee.
2. HR advisor enters the employee number.
3. System searches for the employee in the database.
4. System retrieves the employee's current details.
5. System displays the employee's details to the HR advisor.

## EXTENSIONS

3. **Employee does not exist**:
    1. System informs the HR advisor that the employee cannot be found.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0