/**********************************/
/* Table Name: MEMBER */
/**********************************/
CREATE TABLE MEMBER (
    memberno NUMERIC(10) PRIMARY KEY,
    name VARCHAR(50),
    id VARCHAR(50),
    password VARCHAR(250),
    birthdate VARCHAR(15),
	email VARCHAR(50),
    address1 VARCHAR(50),
    address2 VARCHAR(50),
    joindate DATETIME
);

/**********************************/
/* Table Name: ADMIN */
/**********************************/
CREATE TABLE ADMIN (
    adminno NUMERIC(5) PRIMARY KEY,
    name VARCHAR(50),
    id VARCHAR(50),
    password VARCHAR(250),
    joindate DATETIME,
    grade NUMERIC(2)
);

/**********************************/
/* Table Name: TERMS */
/**********************************/
CREATE TABLE TERMS (
    termsno NUMERIC(10) PRIMARY KEY,
    title VARCHAR(50),
    content VARCHAR(250),
    termsdate VARCHAR(15)
);

/**********************************/
/* Table Name: TERMSAGREE */
/**********************************/
CREATE TABLE TERMSAGREE (
    termsagreeno NUMERIC(10) PRIMARY KEY,
    memberno NUMERIC(10),
    termsno NUMERIC(10),
    agreedate VARCHAR(15)
);

/**********************************/
/* Table Name: LOG */
/**********************************/
CREATE TABLE LOG (
    memberno NUMERIC(20),
    id VARCHAR(50),
    password VARCHAR(250),
    joindate DATETIME,
	passwordchangedate DATETIME,
    lastlogindate DATETIME,
    lastloginno INT
);
