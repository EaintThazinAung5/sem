# USE CASE: 5 Add a New Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to add a new employee's details* so that *I can ensure the new employee is paid.*

### Scope

Company.

### Level

Primary task.

### Preconditions

The new employee's details are available.

### Success End Condition

The new employee's details are stored in the database.

### Failed End Condition

The new employee's details are not added.

### Primary Actor

HR Advisor.

### Trigger

A new employee joins the company and their details need to be recorded.

## MAIN SUCCESS SCENARIO

1. HR advisor requests to add a new employee.
2. HR advisor enters the new employee's details.
3. System validates the employee's details.
4. System adds the new employee's details to the database.
5. System confirms that the employee has been added.

## EXTENSIONS

3. **Employee details are invalid**:
    1. System informs the HR advisor that the details are invalid.
    2. HR advisor corrects the details.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0