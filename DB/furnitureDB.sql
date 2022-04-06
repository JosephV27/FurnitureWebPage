SET SERVEROUTPUT ON;


CREATE TABLE user_credentials
(
    id_user  NUMBER,
    username VARCHAR2(30) NOT NULL,
    password VARCHAR2(50) NOT NULL,
    roles    VARCHAR2(50) NOT NULL,
    active   NUMBER       NOT NULL,
    CONSTRAINT user_id_pk PRIMARY KEY (id_user)
);

CREATE TABLE employee
(
    num_employee    NUMBER,
    identification  VARCHAR2(15) NOT NULL,
    name            VARCHAR2(30) NOT NULL,
    first_lastname  VARCHAR2(30) NOT NULL,
    second_lastname VARCHAR2(30) NOT NULL,
    salary          NUMBER       NOT NULL,
    status          VARCHAR2(10) NOT NULL,
    date_admission  DATE         NOT NULL,
    CONSTRAINT employee_id_pk PRIMARY KEY (num_employee)
);

CREATE SEQUENCE seq_employee_id
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 10000
    CYCLE;


CREATE TABLE department
(
    id_department NUMBER,
    name          VARCHAR2(30) NOT NULL,

    CONSTRAINT department_pk PRIMARY KEY (id_department)

);

CREATE SEQUENCE seq_department_id
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 10000
    CYCLE;

CREATE TABLE employee_department
(
    num_employee  NUMBER,
    id_department NUMBER,

    CONSTRAINT employee_department_pk
        PRIMARY KEY (num_employee, id_department),

    CONSTRAINT fk_num_employee_department
        FOREIGN KEY (num_employee)
            REFERENCES employee (num_employee),

    CONSTRAINT fk_employee_id_department
        FOREIGN KEY (id_department)
            REFERENCES department (id_department)

);


CREATE TABLE category
(
    id_category NUMBER,
    name        VARCHAR2(30) NOT NULL,
    description VARCHAR(500) NOT NULL,

    CONSTRAINT category_pk PRIMARY KEY (id_category)

);


CREATE SEQUENCE seq_category_id
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 10000
    CYCLE;



CREATE TABLE product
(
    id_product  NUMBER,
    id_category NUMBER,
    name        VARCHAR2(30) NOT NULL,
    description VARCHAR(500) NOT NULL,
    price       FLOAT        NOT NULL,

    CONSTRAINT product_pk PRIMARY KEY (id_product),

    CONSTRAINT fk_id_category_product
        FOREIGN KEY (id_category)
            REFERENCES category (id_category)

);


CREATE SEQUENCE seq_product_id
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 10000
    CYCLE;


insert into category values (1, 'Mesas', 'Mesas de madera');
insert into category values (2, 'Muebles de Oficina', 'Muebles para oficina');
insert into category values (3, 'Muebles de Jardin', 'Muebles para jardin');
insert into category values (4, 'Sillas', 'Sillas de madera');
insert into category values (5, 'Muebles para interior', 'Muebles para interior de casas');
insert into category values (6, 'Muebles lujosos', 'Muebles de lujo');


CREATE TABLE time_logger
(
    num_employee     NUMBER,
    id_product       NUMBER,
    ordinary_hours   NUMBER DEFAULT 0,
    extra_hours      NUMBER DEFAULT 0,
    double_hours     NUMBER DEFAULT 0,
    date_time_logger DATE   NOT NULL,

    CONSTRAINT fk_num_employee_time_logger
        FOREIGN KEY (num_employee)
            REFERENCES employee (num_employee),

    CONSTRAINT fk_id_product_time_logger
        FOREIGN KEY (id_product)
            REFERENCES product (id_product)

);



CREATE TABLE customer (
    id_customer    NUMBER,
    identification VARCHAR2(15) NOT NULL,
    name           VARCHAR2(30) NOT NULL,
    lastname       VARCHAR2(30) NOT NULL,
    address        VARCHAR2(100) NOT NULL,
    phone          VARCHAR2(30) NOT NULL,
    CONSTRAINT customer_id_pk PRIMARY KEY ( id_customer )
);

CREATE SEQUENCE seq_customer_id
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 10000
    CYCLE;


CREATE TABLE receipt
(
    id_customer NUMBER,
    id_product  NUMBER,

    CONSTRAINT receipt_pk
        PRIMARY KEY (id_customer, id_product),

    CONSTRAINT fk_id_customer_receipt
        FOREIGN KEY (id_customer)
            REFERENCES customer (id_customer),

    CONSTRAINT fk_id_product_receipt
        FOREIGN KEY (id_product)
            REFERENCES product (id_product)

);


insert into user_credentials
values (1, 'furnitureAdmin', 'pass', 'ROLE_USER', 1);

-- MODIFICATION PACKAGE
CREATE OR REPLACE PACKAGE time_logger_management AS
    FUNCTION insert_hours_time_logger (
        v_num_employee   NUMBER,
        v_id_product     NUMBER,
        v_ordinay_hours  NUMBER,
        v_extra_hours    NUMBER,
        v_double_hours   NUMBER,
        v_date_time_logger DATE
    ) RETURN VARCHAR2;
    
        PROCEDURE delete_hours_time_logger (
        v_num_employee   NUMBER,
        v_date_time_logger DATE
    );

END time_logger_management;
/

CREATE OR REPLACE PACKAGE BODY time_logger_management AS

    FUNCTION insert_hours_time_logger (
        v_num_employee   NUMBER,
        v_id_product     NUMBER,
        v_ordinay_hours  NUMBER,
        v_extra_hours    NUMBER,
        v_double_hours   NUMBER,
        v_date_time_logger DATE
    ) RETURN VARCHAR2 IS
         PRAGMA AUTONOMOUS_TRANSACTION;
        
    BEGIN
    
        INSERT INTO time_logger VALUES (
            v_num_employee,
            v_id_product,
            v_ordinay_hours,
            v_extra_hours,
            v_double_hours,
            v_date_time_logger
        );
        
        COMMIT;
        RETURN v_num_employee || ' INSERTED'  ;
    END insert_hours_time_logger;
    
    
        PROCEDURE delete_hours_time_logger (
         v_num_employee   NUMBER,
        v_date_time_logger DATE
    ) IS
    BEGIN

            DELETE FROM time_logger
        WHERE
                num_employee = v_num_employee
            AND date_time_logger = v_date_time_logger;

    END;
    
END time_logger_management;
/

-- CONSULTATION PACKAGE
CREATE OR REPLACE PACKAGE consult_time_logger AS
    FUNCTION get_employee_info (
        v_num_employee NUMBER
    ) RETURN VARCHAR2;

    PROCEDURE get_hours_worked_by_date (
        v_initial_date IN DATE,
        v_ending_date  IN DATE,
        hours_worked_cursor OUT SYS_REFCURSOR
    );

END consult_time_logger;
/

CREATE OR REPLACE PACKAGE BODY consult_time_logger AS

     FUNCTION get_employee_info (
        v_num_employee   NUMBER
    ) RETURN VARCHAR2 AS
    employee_cursor SYS_REFCURSOR;
    employee_info VARCHAR2(100);
    BEGIN 
    
       FOR employee_cursor IN (
      select * from
    employee 
    where num_employee = v_num_employee
    ) LOOP
        employee_info := employee_cursor.num_employee || ' ' || employee_cursor.identification || ' ' || 
        employee_cursor.name || ' ' || employee_cursor.first_lastname || ' ' || employee_cursor.second_lastname
        || ' ' || employee_cursor.salary || ' ' || employee_cursor.status || ' ' || employee_cursor.date_admission;
    END LOOP;
        
    RETURN employee_info;
    
    END get_employee_info;
    
    
    PROCEDURE get_hours_worked_by_date (
        v_initial_date DATE,
        v_ending_date  DATE,
        hours_worked_cursor OUT SYS_REFCURSOR
    ) AS
    BEGIN
        OPEN hours_worked_cursor FOR SELECT
                                       e.num_employee,
                                       t.id_product,
                                       t.ordinary_hours,
                                       t.extra_hours,
                                       t.double_hours,
                                       t.date_time_logger
                                   FROM
                                            employee e
                                       INNER JOIN time_logger t ON e.num_employee = t.num_employee
                                   WHERE
                                       t.date_time_logger BETWEEN v_initial_date AND v_ending_date;
       
    END;
    
END consult_time_logger; 
/

-- total hours select
select e.num_employee, e.name, e.first_lastname, e.second_lastname, sum(t.ordinary_hours) total_ordinary_hours, sum(t.extra_hours) total_extra_hours, sum(t.double_hours) total_double_hours from 
        employee e 
        inner join time_logger t
        on e.num_employee = t.num_employee 
        where t.date_time_logger BETWEEN v_initial_date AND v_ending_date
        group by e.num_employee, e.name, e.first_lastname, e.second_lastname;

CREATE TABLE employeeLogger (
    employee_logger_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    transaction_name VARCHAR2(10),
    by_user          VARCHAR2(30),
    value_modified   VARCHAR2(50),
    transaction_date DATE
);

select * from employeeLogger;

CREATE OR REPLACE TRIGGER employees_logger_trg AFTER
    UPDATE OR INSERT OR DELETE ON employee
    FOR EACH ROW
DECLARE
    l_transaction    VARCHAR2(10);
    l_value_modified VARCHAR2(50);
BEGIN
   -- determine the transaction type
    l_transaction :=
        CASE
            WHEN updating THEN
                'UPDATE'
            WHEN deleting THEN
                'DELETE'
            WHEN inserting THEN
                'INSERT'
        END;
   
   -- determine the value modified
    l_value_modified :=
        CASE
            WHEN
                updating
                AND ( :old.identification != :new.identification )
            THEN
                'identification value updated'
            WHEN
                updating
                AND ( :old.name != :new.name )
            THEN
                'name value updated'
            WHEN
                updating
                AND ( :old.first_lastname != :new.first_lastname )
            THEN
                'first_lastname value updated'
            WHEN
                updating
                AND ( :old.second_lastname != :new.second_lastname )
            THEN
                'second_lastname value updated'
            WHEN
                updating
                AND ( :old.salary != :new.salary )
            THEN
                'salary value updated'
            WHEN
                updating
                AND ( :old.status != :new.status )
            THEN
                'status value updated'
            WHEN
                updating
                AND ( :old.date_admission != :new.date_admission )
            THEN
                'date_admission value updated'
            WHEN deleting THEN
               CONCAT(trim(:old.name), CONCAT(trim(:old.first_lastname), ' employee deleted'))
            WHEN inserting THEN
                CONCAT(trim(:new.name), CONCAT(trim(:new.first_lastname), ' employee inserting'))
        END;

    INSERT INTO employeelogger (
        transaction_name,
        by_user,
        value_modified,
        transaction_date
    ) VALUES (
        l_transaction,
        user,
        l_value_modified,
        sysdate
    );

END;
/

-- TRANSACTIONS SUMARY

SELECT COUNT(*) FROM employee WHERE status = 'activo';

SELECT COUNT(*) FROM employee WHERE status = 'inactivo';

SELECT SUM(salary) FROM employee WHERE status = 'activo';

SELECT SUM(salary) * 12 FROM employee WHERE status = 'activo';

SELECT SUM(ordinary_hours) + SUM(extra_hours) + SUM(double_hours) FROM time_logger WHERE EXTRACT(MONTH FROM date_time_logger) = EXTRACT(MONTH FROM SYSDATE);



