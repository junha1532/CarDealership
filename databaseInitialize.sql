-- Running this script will DELETE the existing database and all data it contains.
-- Use with caution.

DROP DATABASE IF EXISTS CarDealerShip;

CREATE DATABASE CarDealerShip;

USE CarDealerShip;

CREATE TABLE User(
	userId INT NOT NULL AUTO_INCREMENT,
	userFirstName varchar(20) not null, 
    userLastName varchar(20) not null,
    userType varchar(10) NOT NULL, /*admin/sales/*/ 
	userEmail varchar(100) NOT NULL UNIQUE,
    Primary key (userId),
    password varchar(60) NOT NULL
);

CREATE TABLE MAKE(
	FOREIGN KEY (userId) references user(userId),
	MakeId INT NOT NULL AUTO_INCREMENT,
    MakeName varchar(20) NOT NULL UNIQUE, 
	userId INT NOT NULL,
	dateAdded date not null,
    primary key(makeId)
);

Create TABLE MODEL(
Primary key (modelId),
FOREIGN KEY (MakeId) references make(MakeId),
FOREIGN KEY (userId) references user(userId),
modelId INT NOT NULL AUTO_INCREMENT,
MakeId INT NOT NULL,
userId INT NOT NULL,
modelName varchar(20) NOT NULL,
dateAdded Date NOT NULL
);

CREATE TABLE Vehicle (
userId INT NOT NULL,
VehicleId INT NOT NULL AUTO_INCREMENT,
VIN varchar(20) NOT NULL UNIQUE,
Year INT NOT NULL,
MakeId INT NOT NULL,
ModelId INT NOT NULL,
Color varchar(20) NOT NULL,
Interior varchar(20) NOT NULL,
BodyStyle varchar(20) NOT NULL,
Transmission varchar(20) NOT NULL,
Mileage INT NOT NULL,
SalePrice INT NOT NULL,
MSRP INT NOT NULL, 
featured boolean,
dateAdded date not null,
DESCRIPTION varchar(200) NOT NULL,
PRIMARY KEY (VehicleId),
FOREIGN KEY (userId) references user(userId),
FOREIGN KEY (ModelId) references MODEL(ModelId)
);



CREATE TABLE Special(
	SpecialTitle varChar(20) NOT NULL,
    SpecialDescription varchar(200) NOT NULL,
    promotionAmount int,
    PRIMARY KEY (SpecialTitle)
);

CREATE TABLE Contact(
	ContactId INT NOT NULL AUTO_INCREMENT,
    ContactName varchar(20) NOT NULL,
    Message varchar(200) NOT NULL,
    
    ContactEmail varchar(100),
	ContactPhone varchar(20),
    PRIMARY KEY (ContactId)
);


Create TABLE SALE(
	FOREIGN KEY(SpecialTitle) references Special(SpecialTitle),
	FOREIGN KEY (SalesPersonId) references User(userId),
	SaleId INT NOT NULL AUTO_INCREMENT,
	Primary key (SaleId),
    SpecialTitle varchar(20),
    salespersonId int not null,
    customerName varchar(20) NOT NULL,
	customerEmail varchar(100) NOT NULL,
	customerAddress varchar(100) NOT NULL,
	customerAddress2 varchar(100),
	customerCity varchar(20) NOT NULL,
	customerZipCode varchar(10) NOT NULL,
    purchasePrice INT NOT NULL,
    purchaseType varChar(20) NOT NULL,
    purchaseDate date not null
);

INSERT INTO user(userFirstName, userLastName, userType, userEmail, password)
Values("Junha", "Park", "ADMIN", "junha.park@cardealership.com", "12345ABC");


INSERT INTO user(userFirstName, userLastName, userType, userEmail, password)
Values("Paul", "Botti", "USER", "pbotti4@gmail.com", "123456456456");

INSERT INTO Make(userId, makeName, dateAdded) 
Values(1, "Audi", "2021-09-01");


INSERT INTO Make(userId, makeName, dateAdded) 
Values(1, "Mercedes", "2021-09-10");

INSERT INTO Make(userId, makeName, dateAdded) 
Values(1, "Ferrarri", "2021-09-14");

INSERT INTO MODEL(MakeId, userId, modelName, dateAdded) 
Values(1, 1, "R8", "2021-09-14");

INSERT INTO MODEL(MakeId, userId, modelName, dateAdded) 
Values(1, 1, "A4", "2021-09-14");

INSERT INTO MODEL(MakeId, userId, modelName, dateAdded) 
Values(2, 1, "A-Class", "2021-09-14");

INSERT INTO MODEL(MakeId, userId, modelName, dateAdded) 
Values(3, 1, "SuperFast", "2021-09-14");

INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
Values(1, "3380800793", 2016, 1, 1, "Black", "Grey", "SUV", "Auto", 80000, 20000, 25000, true, "2021-09-14", "This is a  Featured USED Black 2016 Black Audi R8!");

INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
Values(1, "0417482285", 2021, 1, 1, "Red", "Grey", "Coupe", "Auto", 0, 170000, 200000, true, "2021-09-14", "This is a featured New 2021 Red Audi R8!");

INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
Values(1, "8159644154", 2021, 1, 1, "Red", "Grey", "Coupe", "Auto", 0, 170000, 200000, false, "2021-09-14", "This is a unfeatured New 2021 Red Audi R8!");

INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
Values(1, "4417559305", 2021, 1, 1, "Red", "Grey", "Coupe", "Auto", 0, 170000, 200000, false, "2021-09-14", "This is a unfeatured New 2021 Red Audi R8!");

INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
Values(1, "3059452769", 2021, 1, 2, "Silver", "Grey", "Coupe", "Auto", 0, 150000, 180000, true, "2021-09-14", "This is a featured Used 2021 Silver Audi A4!");

INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
Values(1, "0771076256 ", 2020, 2, 3, "Blue", "Grey", "SUV", "Auto", 20000, 35000, 40000, false, "2021-09-14", "This is a unfeatured Used 2021 Blue Mercedes A-Class!");

INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
Values(1, "7877093347 ", 1980, 3, 3, "Silver", "Grey", "Coupe", "Auto", 20000, 35000, 40000, false, "2021-09-14", "Silver unfeatured Ferrarri SuperFast ");

INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
Values(1, "01230AJKNDSD", 2020, 1, 1, "Mat Black", "Grey", "SUV", "Auto", 20000, 35000, 40000, false, "2021-09-14", "COOL CAR. MUST BUY!");

INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
Values(2, "01230AJKNDSJ", 2020, 1, 1, "Mat Black", "Grey", "SUV", "Auto", 20000, 35000, 40000, true, "2021-09-14", "COOL CAR. MUST BUY!");

-- INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
-- Values(3, "01230AJKNDGJ", 2020, 1, 1, "Mat Black", "Grey", "SUV", "Auto", 20000, 35000, 40000, true, "2021-09-14", "COOL CAR. MUST BUY!");

-- DELETE FROM vehicle WHERE vehicleId = 1; 

INSERT INTO sale(salesPersonId,customerName,customerEmail, customerAddress,customerAddress2,customerCity,customerZipCode,purchasePrice,purchaseType,purchaseDate )
Values(1, "Aubrey", "aubrey@abc.com", "Somewhere in US", "", "New York", "ZIP12", 35000, "Cash", "2021-09-09");

INSERT INTO sale(salesPersonId,customerName,customerEmail, customerAddress,customerAddress2,customerCity,customerZipCode,purchasePrice,purchaseType,purchaseDate )
Values(1, "Dan", "dan@abc.com", "Somewhere in Canada", "", "Waterloo", "ZIP12", 35000, "Bank Finance", "2021-07-09");

INSERT INTO sale(salesPersonId,customerName,customerEmail, customerAddress,customerAddress2,customerCity,customerZipCode,purchasePrice,purchaseType,purchaseDate )
Values(1, "Paul", "paul@abc.com", "Somewhere in US", "", "New York", "ZIP12", 35000, "Bank Finance", "2021-10-01");


INSERT INTO special(SpecialTitle, SpecialDescription, promotionAmount)
Values ("DEAL OF THE MONTH!", "Cash Purchase: $3,000 cash incentive on some models.", 100);
INSERT INTO special(SpecialTitle, SpecialDescription, promotionAmount)
Values ("FINANCING!", "Financing Purchase: $750 rebate plus 0.99% interest financing for 84 months. ", 100);
INSERT INTO special(SpecialTitle, SpecialDescription, promotionAmount)
Values ("NEW CARS IN STOCK!", "CHECK OUT NEW IN STOCK AUDIS1", 100);