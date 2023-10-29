/* Author:
File Name: table_data.sql
Last Modified: October 3rd, 2023
Description: Script to populate data.
*/

-- DELETE data from tables --
DELETE FROM T_users
DELETE FROM SubscriberProducer;
DELETE FROM T_messages;

-- Insertion of 4 user data into the T_users Table --
INSERT INTO T_users( uid, u_name, u_role ) VALUES( 101, 'Kamelia.P', 'Producer' );
INSERT INTO T_users( uid, u_name, u_role ) VALUES( 102, 'John.D', 'Producer' );
INSERT INTO T_users( uid, u_name, u_role ) VALUES( 103, 'Blake.L', 'Subscriber' );
INSERT INTO T_users( uid, u_name, u_role ) VALUES( 104, 'Ryan.R', 'Subscriber' );

-- Insertion of 4 subscribers to  producers into the SubscriberProducer Table --
INSERT INTO SubscriberProducer( SP_ID, subcriberId, producerId ) VALUES( 1, 103, 101 );
INSERT INTO SubscriberProducer( SP_ID, subcriberId, producerId ) VALUES( 2, 103, 102 );
INSERT INTO SubscriberProducer( SP_ID, subcriberId, producerId ) VALUES( 3, 104, 101 );
INSERT INTO SubscriberProducer( SP_ID, subcriberId, producerId ) VALUES( 4, 104, 102 );

-- Insertion of 5 messsages into the Messages Table --
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10001, 101, 'This is a message from producer 101, Kamelia');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10002, 102, 'This is a message from producer 102, John');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10003, 103, 'This is a message from subscriber 103, Blake');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10004, 104, 'This is a message from subscriber 104, Ryan');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10005, 101, 'This is a secomd message from producer 101, Kamelia');

INSERT INTO T_messages ( mid, uid, contents) VALUES( 10001, 101, 'This is a message from producer 101, Kamelia');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10002, 102, 'This is a message from producer 102, John');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10003, 103, 'This is a message from subscriber 103, Blake');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10004, 104, 'This is a message from subscriber 104, Ryan');
INSERT INTO T_messages ( mid, uid, contents) VALUES( 10005, 101, 'This is a secomd message from producer 101, Kamelia');
