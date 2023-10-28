/* Author:
File Name: tables.sql
Last Modified: 3rd October, 2023
Description: Script to create tables.
*/

-- Drop Tables --
DROP TABLE IF EXISTS T_users;
DROP TABLE IF EXISTS SubscriberProducer;
DROP TABLE IF EXISTS T_messages;


-- Create the user table --
CREATE TABLE T_users (
                         uid INT NOT NULL,
                         u_name VARCHAR(255) NOT NULL,
                         u_role VARCHAR(20) NOT NULL,
                         CONSTRAINT PK_user PRIMARY KEY(uid)
);

-- Establish Subscriptions --
CREATE TABLE SubscriberProducer (
                                    SP_ID INT NOT NULL,
                                    subcriberId INT NOT NULL,
                                    producerId INT NOT NULL,
                                    CONSTRAINT PK_subscriber PRIMARY KEY(SP_ID),
                                    CONSTRAINT FK_Subscriber_ID FOREIGN KEY(subcriberId) REFERENCES T_users(uid),
                                    CONSTRAINT FK_Producer_ID FOREIGN KEY(producerId) REFERENCES T_users(uid)
);

-- Create the T_messages table --
CREATE TABLE T_messages (
                            mid INT NOT NULL,
                            uid INT NOT NULL,
                            contents TEXT NOT NULL,
                            CONSTRAINT PK_messages PRIMARY KEY(mid),
                            CONSTRAINT FK_uid FOREIGN KEY(uid) REFERENCES T_users(uid)
);

