# USE CASE: 1 Produce a Report on the Salary of All Employees

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to produce a report on the salary of all employees* so that *I can support financial reporting of the organisation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

Database contains current employee salary data.

### Success End Condition

A report is available for HR to provide to finance.

### Failed End Condition

No report is produced.

### Primary Actor

HR Advisor.

### Trigger

A request for financial information is sent to HR.

## MAIN SUCCESS SCENARIO

1. Finance requests salary information for all employees.
2. HR advisor requests the salary information of all employees.
3. HR advisor extracts the current salary information of all employees.
4. HR advisor provides the report to finance.

## EXTENSIONS

3. **No current salary information is available**:
    1. HR advisor informs finance that salary information is unavailable.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0