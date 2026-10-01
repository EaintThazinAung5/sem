# USE CASE: 7 Update an Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to update an employee's details* so that *employee's details are kept up-to-date.*

### Scope

Company.

### Level

Primary task.

### Preconditions

The employee exists in the database.

### Success End Condition

The employee's updated details are stored in the database.

### Failed End Condition

The employee's details are not updated.

### Primary Actor

HR Advisor.

### Trigger

An employee's details need to be changed.

## MAIN SUCCESS SCENARIO

1. HR advisor requests to update an employee's details.
2. HR advisor enters the employee number.
3. System searches for the employee in the database.
4. System displays the employee's current details.
5. HR advisor changes the required details.
6. System validates the updated details.
7. System saves the updated details in the database.
8. System confirms that the employee's details have been updated.

## EXTENSIONS

3. **Employee does not exist**:
    1. System informs the HR advisor that the employee cannot be found.

6. **Updated details are invalid**:
    1. System informs the HR advisor that the details are invalid.
    2. HR advisor corrects the details.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0