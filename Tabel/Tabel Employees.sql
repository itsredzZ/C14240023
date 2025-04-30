SELECT 
    CONCAT(first_name, ' ', last_name) AS "Nama Lengkap", 
    TO_CHAR(salary, 'Rp99,999') AS "Salary",
    TO_CHAR(COALESCE(commission_pct, 0) * 100, 'FM99') || '%' AS "Percent Komisi",
    TO_CHAR(salary * COALESCE(commission_pct, 0), 'Rp99,999') AS "Komisi",
    TO_CHAR(salary + (salary * COALESCE(commission_pct, 0)), 'Rp99,999') AS "Total Salary"
FROM employees;