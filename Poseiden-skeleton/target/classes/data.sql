
USE demo;


INSERT INTO bidlist (account,type,bid_quantity)
VALUES
('101','type1','1'),('102','type2','2'),('103','type3','3');

INSERT INTO trade (trade_id,account,type,buy_quantity)
VALUES
('1','111','type1','100'),('2','222','type2','200'),('3','333','type3','300');

INSERT INTO curvepoint (curve_id,term,value)
VALUES
('1','10','11'),('2','20','22'),('3','30','33');

INSERT INTO rating (id,moodys_rating,sand_p_rating,fitch_rating,order_number)
VALUES
('1','A1','Aa1','Aa1',1),('2','A2','Aa2','Aa2',2),('3','A3','Aa3','Aa3',3);

INSERT INTO rulename (id,name,description,json,template,sql_str,sql_part)
VALUES
('1','Nom1','des1','json1','temp1','str1','part1'),
('2','Nom2','des2','json2','temp2','str2','part2'),
('3','Nom3','des3','json3','temp3','str3','part3');

INSERT INTO Users (fullname,username,password,role)
VALUES
('Administrator','admin','$2a$12$yaPVzh/YEegzYbLNG/2csOhBOzxYJ6Gz7CRuQdsxwETJ3QKdijVDS','ADMIN'),
('User','user','$2a$12$QAMweolG6RTtkWuU0FaycOZik88Iy6rg61ymDSh6TJzC4MZzHo7Ga','USER');