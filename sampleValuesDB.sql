USE cardealership;

-- INSERT INTO user(userFirstName, userLastName, userType, userEmail, password)
-- Values("Junha", "Park", "ADMIN", "junha.park@cardealership.com", "12345ABC");

-- INSERT INTO Make(userId, makeName, dateAdded) 
-- Values(1, "Audi", "2021-09-01");

-- INSERT INTO Make(userId, makeName, dateAdded) 
-- Values(1, "Mercedes", "2021-09-10");

-- INSERT INTO Make(userId, makeName, dateAdded) 
-- Values(1, "Ferrarri", "2021-09-14");

-- INSERT INTO MODEL(MakeId, userId, modelName, dateAdded) 
-- Values(1, 1, "R8", "2021-09-14");

-- INSERT INTO MODEL(MakeId, userId, modelName, dateAdded) 
-- Values(1, 1, "A4", "2021-09-14");

-- INSERT INTO MODEL(MakeId, userId, modelName, dateAdded) 
-- Values(2, 1, "A-Class", "2021-09-14");

-- INSERT INTO MODEL(MakeId, userId, modelName, dateAdded) 
-- Values(3, 1, "SuperFast", "2021-09-14");

-- INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
-- Values(1, "76547654677", 2019, 1, 1, "Mat Black", "Grey", "SUV", "Auto", 20000, 40000, 40000, false, "2021-09-14", "This is a used Black Audi R8");

-- INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
-- Values(1, "0123498765", 2020, 1, 2, "Red", "Grey", "SUV", "Auto", 0, 35000, 40000, false, "2021-09-14", "This is a New Red Audi A4");

-- INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
-- Values(1, "8432901884", 2020, 2, 3, "White", "Grey", "SUV", "Auto", 0, 35000, 40000, false, "2021-09-14", "This is a new Mercedes A-Class");

-- INSERT INTO vehicle(userId, VIN, Year, MakeId, ModelID, Color, Interior, BodyStyle, Transmission, Mileage, SalePrice, MSRP, featured, dateAdded, DESCRIPTION)
-- Values(1, "45637463673", 2020, 3, 4, "Red", "Grey", "SUV", "Auto", 100000, 35000, 40000, true, "2021-09-14", "This is a used Ferrarri Superfast");

-- DELETE FROM vehicle WHERE vehicleId = 1; 

-- INSERT INTO sale(salesPersonId,customerName,customerEmail, customerAddress,customerAddress2,customerCity,customerZipCode,purchasePrice,purchaseType )
-- Values(1, "Aubrey", "aubrey@cool.com", "Somewhere in US", "", "New York", "ZIP12", 35000, "CASH");

-- SELECT * FROM vehicle;
-- SELECT v.* FROM VEHICLE v JOIN model m ON m.modelId = v.modelId JOIN make ma on ma.makeId = m.makeId
-- WHERE v.mileage >=0
-- -- AND ma.makeName LIKE "%i%";
-- AND YEAR LIKE "%2020%"
-- LIMIT 4;
-- AND SALEPRICE = 35000;


-- SELECT ma.makeName, m.modelName, v.vehicleId , count(*) FROM VEHICLE v JOIN make ma on ma.makeid = v.makeId join model m on m.modelId = v.modelId where v.mileage = 0  group by modelName ;

-- SELECT v.year Year, ma.makeName Make, m.modelName Model, count(*) Count, sum(v.SalePrice) Stock_Value FROM VEHICLE v JOIN make ma on ma.makeid = v.makeId join model m on m.modelId = v.modelId WHERE v.mileage > 0 AND ma.makeName = "Audi" and m.modelName = "R8";
-- SELECT v.year Year, ma.makeName Make, m.modelName Model, count(*) Count, sum(v.SalePrice) Stock_Value FROM VEHICLE v JOIN make ma on ma.makeid = v.makeId join model m on m.modelId = v.modelId WHERE v.mileage > 0  group by v.year,ma.MakeId;

-- SELECT concat(u.userFirstName," ", u.userLastName) User, sum(s.purchasePrice) Total_Sales, count(*) Total_Vehicles FROM user u join sale s on s.salesPersonId = u.userId group by u.userId;
-- user and sale sum(sale..) count(how many sales) group by userid

-- insert into special (SpecialTitle, SpecialDescription, promotionAmount) values ("Cool promotion", "Hello", 1000);

-- SELECT * FROM SPECIAL
-- SELECT * FROM special;
-- insert into make(makeName,userId,dateAdded) values("audi",1,"2021-09-14")
-- SELECT * FROM make;
-- SELECT v.* FROM VEHICLE v JOIN model m ON m.modelId = v.modelId JOIN make ma on ma.makeId = m.makeId WHERE v.mileage = 0 AND ma.makeName LIKE '% %' AND m.modelName LIKE %' '% AND v.year LIKE %' '% LIMIT 2

-- SELECT * FROM CONTACT

-- SELECT m.* FROM  make m JOIN vehicle v ON v.makeId = m.makeId;


-- SELECT * FROM VEHICLE;
-- SELECT v.* FROM VEHICLE v JOIN model m ON m.modelId = v.modelId JOIN make ma on ma.makeId = m.makeId WHERE v.mileage > 0 AND ma.makeName LIKE '%%' and m.modelName LIKE '%%' and v.year LIKE '%%'  AND v.salePrice BETWEEN 0 AND 9999999999999 AND v.year BETWEEN 0 AND 9999 LIMIT 20


-- INSERT INTO sale(SpecialTitle,salesPersonId,customerName,customerEmail,customerAddress,customerAddress2,customerCity,customerZipCode,purchasePrice,purchaseType 
-- VALUES(1,'Dan Huynh','danh8953@gmail.com','492 Wismer St.','','Waterloo','N2K');

-- INSERT INTO sale(salesPersonId,customerName,customerEmail, customerAddress,customerAddress2,customerCity,customerZipCode,purchasePrice,purchaseType )
-- Values(1, "Aubrey", "aubrey@cool.com", "Somewhere in US", "", "New York", "ZIP12", 35000, "CASH");

-- INSERT INTO user(userFirstName, userLastName, userType, userEmail, password)
-- Values("Dan", "H", "SALES", "dan@sales.com", "123");

SELECT * FROM SPECIAL;

-- SELECT * FROM SALE;