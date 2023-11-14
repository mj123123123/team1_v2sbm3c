/**********************************/
/* Table Name: Routes */
/**********************************/
CREATE TABLE Routes (
    routesno INT PRIMARY KEY,
    routesname VARCHAR2(255),
    start_location VARCHAR2(100),
    end_location VARCHAR2(100),
    departure_time TIMESTAMP,
    arrival_time TIMESTAMP
);

/**********************************/
/* Table Name: Seats */
/**********************************/
CREATE TABLE Seats (
    seatno INT PRIMARY KEY,
    routesno INT,
    seat_number INT,
    available NUMBER(1),
    FOREIGN KEY (routesno) REFERENCES Routes(routesno)
);

/**********************************/
/* Table Name: Customer */
/**********************************/
CREATE TABLE Customer (
    Customerno INT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(15)
);

/**********************************/
/* Table Name: Reserve */
/**********************************/
CREATE TABLE Reserve (
    Reserveno INT PRIMARY KEY,
    routesno INT,
    seatno INT,
    customerno INT,
    reserve_time TIMESTAMP,
    FOREIGN KEY (routesno) REFERENCES Routes(routesno),
    FOREIGN KEY (seatno) REFERENCES Seats(seatno),
    FOREIGN KEY (customerno) REFERENCES Customer(customerno)
);
