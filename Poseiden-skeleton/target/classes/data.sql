
USE demo;


INSERT INTO bidlist (account,type,bid_quantity)
VALUES
('101','type1','1'),('102','type2','2'),('103','type3','3');



INSERT INTO Users (fullname,username,password,role)
VALUES
('Administrator','admin','$2a$12$yaPVzh/YEegzYbLNG/2csOhBOzxYJ6Gz7CRuQdsxwETJ3QKdijVDS','ADMIN'),
('User','user','$2a$12$QAMweolG6RTtkWuU0FaycOZik88Iy6rg61ymDSh6TJzC4MZzHo7Ga','USER');