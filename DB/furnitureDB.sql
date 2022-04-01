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


-- *************revisar los not NULLS en esta tabla************
CREATE TABLE time_logger
(
    num_employee     NUMBER,
    id_product       NUMBER,
    ordinary_hours   NUMBER,
    extra_hours      NUMBER,
    double_hours     NUMBER,
    date_time_logger DATE   NOT NULL,

    CONSTRAINT time_logger_pk
        PRIMARY KEY (num_employee),

    CONSTRAINT fk_num_employee_time_logger
        FOREIGN KEY (num_employee)
            REFERENCES employee (num_employee),

    CONSTRAINT fk_id_product_time_logger
        FOREIGN KEY (id_product)
            REFERENCES product (id_product)

);


CREATE TABLE customer
(
    id_customer     NUMBER,
    identification  VARCHAR2(15)  NOT NULL,
    name            VARCHAR2(30)  NOT NULL,
    first_lastname  VARCHAR2(30)  NOT NULL,
    second_lastname VARCHAR2(30)  NOT NULL,
    address         VARCHAR2(100) NOT NULL,
    city            VARCHAR2(30)  NOT NULL,
    phone           VARCHAR2(30)  NOT NULL,

    CONSTRAINT customer_id_pk PRIMARY KEY (id_customer)
);

CREATE SEQUENCE seq_customer_id
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 10000
    CYCLE;


-- cambiar el nombre de tabla
CREATE TABLE order
(
    id_customer NUMBER,
    id_product  NUMBER,

    CONSTRAINT order_pk
        PRIMARY KEY (id_customer, id_product),

    CONSTRAINT fk_id_customer_order
        FOREIGN KEY (id_customer)
            REFERENCES customer (id_customer),

    CONSTRAINT fk_id_product_order
        FOREIGN KEY (id_product)
            REFERENCES product (id_product)

);



insert into user_credentials
values (1, 'furnitureAdmin', 'pass', 'ROLE_USER', 1);


-- test
CREATE OR REPLACE PROCEDURE sos
AS
    c1 SYS_REFCURSOR;
BEGIN

    open c1 for
        SELECT * from employee;
    DBMS_SQL.RETURN_RESULT(c1);

END;
/

exec sos;


select * from employee;

declare
 id_temp NUMBER;
begin 
    id_temp := employee_management.insert_employee('1-1813-02118794', 'Marco', 'Valenciano', 'Herrera', 50000, 'active', TO_DATE('30/03/2022', 'dd/MM/yyyy'));
    DBMS_OUTPUT.put_line(id_temp);
end;
/

-- package example
CREATE OR REPLACE PACKAGE employee_management AS
    FUNCTION insert_employee (
        v_identification  VARCHAR2,
        v_name            VARCHAR2,
        v_first_lastname  VARCHAR2,
        v_second_lastname VARCHAR2,
        v_salary          NUMBER,
        v_status          VARCHAR2,
        v_date_admission  DATE
    ) RETURN NUMBER;

END employee_management;
/

CREATE OR REPLACE PACKAGE BODY employee_management AS
--INSERT THE EMPLOYEE
    FUNCTION insert_employee (
        v_identification  VARCHAR2,
        v_name            VARCHAR2,
        v_first_lastname  VARCHAR2,
        v_second_lastname VARCHAR2,
        v_salary          NUMBER,
        v_status          VARCHAR2,
        v_date_admission  DATE
    ) RETURN NUMBER IS
        id_temp NUMBER;
    BEGIN
        SELECT
            num_employee
        INTO id_temp
        FROM
            employee
        WHERE
            ( identification = v_identification );

        RETURN ( id_temp );
    EXCEPTION
        WHEN no_data_found THEN
            INSERT INTO employee VALUES (
                seq_employee_id.NEXTVAL,
                v_identification,
                v_name,
                v_first_lastname,
                v_second_lastname,
                v_salary,
                v_status,
                v_date_admission
            );

            COMMIT;
            SELECT
                num_employee
            INTO id_temp
            FROM
                employee
            WHERE
                ( identification = v_identification );

            RETURN ( id_temp );
    END insert_employee;

END employee_management;
/


CREATE TABLE employeeLogger (
    employee_logger_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    transaction_name VARCHAR2(10),
    by_user          VARCHAR2(30),
    value_modified   VARCHAR2(50),
    transaction_date DATE
);

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


