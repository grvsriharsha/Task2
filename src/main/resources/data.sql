DROP TABLE IF EXISTS Item;

CREATE TABLE Item (
  id INT PRIMARY KEY,
  parentid INT,
  name VARCHAR(250) DEFAULT NULL,
  color VARCHAR(250) DEFAULT NULL
);

INSERT INTO Item (id,parentid,name,color) VALUES
  (1,0,'Warrior','red'),
   (2,0,'Warrior','green'),
    (3,0,'Priest','white'),
     (4,0,'Rogue','yellow'),
      (5,1,'Fighter','blue'),
       (6,1,'Paladin','lighblue'),
        (7,1,'Ranger','lighgreen'),
         (8,2,'Mage','grey'),
          (9,2,'Specialist wizard','lightgrey'),
           (10,3,'Cleric','red'),
            (11,3,'Druid','green'),
            (12,3,'Priest of specific mythos','white'),
            (13,4,'Thief','yellow'),
            (14,4,'Bard','blue'),
            (15,13,'Assassin','lighblue');
