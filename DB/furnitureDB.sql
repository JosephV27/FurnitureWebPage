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
    ordinary_hours   NUMBER NOT NULL,
    extra_hours      NUMBER NOT NULL,
    double_hours     NUMBER NOT NULL,
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

