# USE CASE: 8 Delete an Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to delete an employee's details* so that *the company is compliant with data retention legislation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

The employee exists in the database and the employee's details are eligible for deletion.

### Success End Condition

The employee's details are deleted from the database.

### Failed End Condition

The employee's details are not deleted.

### Primary Actor

HR Advisor.

### Trigger

An employee's details are identified for deletion in accordance with data retention requirements.

## MAIN SUCCESS SCENARIO

1. HR advisor requests to delete an employee's details.
2. HR advisor enters the employee number.
3. System searches for the employee in the database.
4. System displays the employee's current details.
5. HR advisor confirms that the employee's details should be deleted.
6. System deletes the employee's details from the database.
7. System confirms that the employee's details have been deleted.

## EXTENSIONS

3. **Employee does not exist**:
    1. System informs the HR advisor that the employee cannot be found.

5. **Deletion is not permitted**:
    1. System informs the HR advisor that the employee's details cannot be deleted.
    2. HR advisor does not delete the employee's details.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0