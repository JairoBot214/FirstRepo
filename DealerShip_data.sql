CREATE TABLE Dealerships(
	dealership_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    address VARCHAR(50),
    phone VARCHAR(12));
    
    
CREATE TABLE Vehicles(
    year INT,
    make VARCHAR(30),
    model VARCHAR(30),
    vehicleType VARCHAR(30),
    color VARCHAR(12),
    odometer INT,
    price float(15));
    
    
CREATE TABLE Inventory(
	dealership_id INT,
    vin INT,
    FOREIGN KEY (dealership_id) REFERENCES Dealerships (dealership_id),
     FOREIGN KEY (vin) REFERENCES Vehicles(vin));
    
    
    
    
CREATE TABLE Sales_contracts(
    dateOfContract DATETIME,
    customerName VARCHAR(50),
    customerEmail VARCHAR(100),
    isFinanced bit,
    vin INT,
    dealership_id INT,
    sales_id INT PRIMARY KEY AUTO_INCREMENT,
    FOREIGN KEY (dealership_id) REFERENCES Dealerships(dealership_id),
    FOREIGN KEY (vin) REFERENCES Vehicles(vin));
	
    
CREATE TABLE Lease_contracts(
	lease_id INT PRIMARY KEY AUTO_INCREMENT,
    dateOfContract DATETIME,
    customerName VARCHAR(50),
    customerEmail VARCHAR(100),
    vin INT,
    dealership_id INT,
    FOREIGN KEY (dealership_id) REFERENCES Dealerships(dealership_id),
    FOREIGN KEY (vin) REFERENCES Vehicles(vin));
    
    INSERT INTO Dealerships (name, address, phone) VALUES
   (' Reynolds auto','Address: 123 Maple Lane, Springville, CA 98765','Phone: (555) 555-1111'),
   (' Rodriguez shop','Address: 789 Oak Street, Rivertown, NY 54321','Phone: (555) 555-2222'),
(' Jenkins autoshop','Address: 456 Pine Avenue',' Lakeside, TX 67890','Phone: (555) 555-3333');

INSERT INTO Vehicles (vin, year, make, model, vehicleType, color, odometer, price) VALUES 
(223544, 2021, 'Hyundai', 'Santa Fe', 'SUV', 'Black', 23000, 30000.50),
(667588, 2018, 'Tesla', 'Model 3', 'Sedan', 'Blue', 12000, 50000.75),
(990051, 2017, 'BMW', 'X5', 'SUV', 'Black', 18000, 45000.00);

INSERT INTO inventories(DealershipID,vin)
VALUES
(1,07159),
(2, 901234),
(3,33564);

INSERT INTO Sales_contracts (dateOfContract, customerName, customerEmail, isFinanced, vin, dealership_id)
VALUES
('2023-05-17 11:20:00', 'Hardy Davis', 'hardy.davis@example.com', 1,07159 , 2),
('2023-06-18 15:10:00', 'John Wilson', 'BigJohnwilson@example.com', 0, 901234, 3),
('2023-07-19 08:45:00', 'David smith', 'davidSmith224e@example.com', 1, 33564, 1);



INSERT INTO Lease_contracts (dateOfContract, customerName, customerEmail, vin, dealership_id)
VALUES


('2023-05-25 11:50:00', 'Grace Wilson', 'grace.wilson@example.com',07159 , 2),
('2023-06-30 15:30:00', 'James Taylor', 'james.taylor@example.com', 901234, 3),
('2023-07-08 09:00:00', 'Emma Lee', 'emma.lee@example.com',33564, 1);


SELECT * 
FROM Dealerships;


SELECT *
FROM Vehicles v
JOIN Inventory i ON v.vin = i.vin
JOIN Dealerships d ON i.dealership_id = d.dealership_id;


SELECT *
FROM Vehicles
WHERE vin = '33564';

SELECT dealerships.name AS DealershipName
FROM dealerships
JOIN inventories ON inventories.DealershipID = dealerships.DealershipID
JOIN vehicles ON vehicles.vin = inventories.vin
WHERE vehicles.make = 'Ford'
GROUP BY dealerships.name;



SELECT dealerships.name, salescontracts.*
FROM salescontracts
JOIN vehicles ON vehicles.vin = salescontracts.vin
JOIN inventories ON inventories.vin	= salescontracts.vin
JOIN dealerships ON inventories.DealershipID = dealerships.DealershipID
WHERE YEAR (salescontracts.date) = '2023' AND dealerships.DealershipID = 1;



    