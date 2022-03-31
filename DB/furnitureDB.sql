SET SERVEROUTPUT ON;

CREATE TABLE  user_credentials(
    id_user NUMBER,
    username VARCHAR2(30) NOT NULL,
    password VARCHAR2(50) NOT NULL,
    roles VARCHAR2(50) NOT NULL,  
    active NUMBER NOT NULL,
    CONSTRAINT user_id_pk PRIMARY KEY (id_user) 
);

CREATE TABLE employee (
    num_employee NUMBER, 
    identification VARCHAR2(15) NOT NULL,
    name VARCHAR2(30) NOT NULL,
    first_lastname VARCHAR2(30) NOT NULL,
    second_lastname VARCHAR2(30) NOT NULL,
    salary NUMBER NOT NULL, 
    status VARCHAR2(10) NOT NULL,
    date_admission DATE NOT NULL,
    CONSTRAINT employee_id_pk PRIMARY KEY (num_employee)
);

CREATE SEQUENCE seq_employee_id
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 10000
    CYCLE;

insert into user_credentials values (1, 'furnitureAdmin', 'pass', 'ROLE_USER', 1);


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

