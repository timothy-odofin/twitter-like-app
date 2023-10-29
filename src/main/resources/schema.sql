/* Author:
File Name: tables.sql
Last Modified: 3rd October, 2023
Description: Script to create tables.
*/

-- Drop Tables --

DROP TABLE IF EXISTS SubscriberProducer;
DROP TABLE IF EXISTS T_messages;
DROP TABLE IF EXISTS T_users;

-- Create the user table --
CREATE TABLE T_users (
                         uid INT NOT NULL AUTO_INCREMENT,  -- Added AUTO_INCREMENT attribute
                         u_name VARCHAR(255) NOT NULL,
                         u_role VARCHAR(20) NOT NULL,
                         CONSTRAINT PK_user PRIMARY KEY(uid)
);

-- Establish Subscriptions --
CREATE TABLE SubscriberProducer (
                                    SP_ID INT NOT NULL AUTO_INCREMENT,
                                    subscriberId INT NOT NULL,
                                    producerId INT NOT NULL,
                                    CONSTRAINT PK_subscriber PRIMARY KEY(SP_ID),
                                    CONSTRAINT FK_Subscriber_ID FOREIGN KEY(subscriberId) REFERENCES T_users(uid),
                                    CONSTRAINT FK_Producer_ID FOREIGN KEY(producerId) REFERENCES T_users(uid)
);

-- Create the T_messages table --
CREATE TABLE T_messages (
                            mid INT NOT NULL AUTO_INCREMENT,
                            uid INT NOT NULL,
                            contents TEXT NOT NULL,
                            CONSTRAINT PK_messages PRIMARY KEY(mid),
                            CONSTRAINT FK_uid FOREIGN KEY(uid) REFERENCES T_users(uid)
);


DELETE FROM SubscriberProducer where SP_ID>0;
DELETE FROM T_messages where mid>0;
DELETE FROM T_users where uid>0;

INSERT INTO T_users( uid, u_name, u_role ) VALUES( 101, 'Kamelia.P', 'Producer' );
INSERT INTO T_users( uid, u_name, u_role ) VALUES( 102, 'John.D', 'Producer' );
INSERT INTO T_users( uid, u_name, u_role ) VALUES( 103, 'Blake.L', 'Subscriber' );
INSERT INTO T_users( uid, u_name, u_role ) VALUES( 104, 'Ryan.R', 'Subscriber' );

INSERT INTO SubscriberProducer( SP_ID, subscriberId, producerId ) VALUES( 1, 103, 101 );
INSERT INTO SubscriberProducer( SP_ID, subscriberId, producerId ) VALUES( 2, 103, 102 );
INSERT INTO SubscriberProducer( SP_ID, subscriberId, producerId ) VALUES( 3, 104, 101 );
INSERT INTO SubscriberProducer( SP_ID, subscriberId, producerId ) VALUES( 4, 104, 102 );

INSERT INTO T_messages ( mid, uid, contents) VALUES( 10001, 101, 'This is a message from producer 101, Kamelia');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10002, 102, 'This is a message from producer 102, John');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10003, 103, 'This is a message from subscriber 103, Blake');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10004, 104, 'This is a message from subscriber 104, Ryan');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10005, 101, 'This is a secomd message from producer 101, Kamelia');

INSERT INTO T_messages ( mid, uid, contents) VALUES( 10006, 101, 'This is a message from producer 101, Kamelia');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10011, 102, 'This is a message from producer 102, John');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10010, 103, 'This is a message from subscriber 103, Blake');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10009, 104, 'This is a message from subscriber 104, Ryan');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10008, 101, 'This is a secomd message from producer 101, Kamelia');
