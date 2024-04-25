INSERT INTO `user` (first_name, last_name, email)
VALUES ('Sangavi', 'Ravichandran', 'sangavi1021@pec.edu'),
       ('Cloudbees', 'test', 'email2@example.com'),
       ('Assessment', 'Cloudbees', 'email3@example.com');


INSERT INTO `journey` (from_location, to_location, price, date_of_journey)
VALUES ('Paris', 'London', 5.0, '2024-05-01'),
       ('London', 'Paris', 6.0, '2024-05-02');

INSERT INTO `seat` (id, journey_id, section, number, status)
VALUES ('1-A-1', 1, 'A', 1, 'BOOKED'),
       ('1-A-2', 1, 'A', 2, 'AVAILABLE'),
       ('1-A-3', 1, 'A', 3, 'AVAILABLE'),
       ('1-A-4', 1, 'A', 4, 'AVAILABLE'),
       ('1-A-5', 1, 'A', 5, 'AVAILABLE'),
       ('1-B-1', 1, 'B', 1, 'AVAILABLE'),
       ('1-B-2', 1, 'B', 2, 'AVAILABLE'),
       ('1-B-3', 1, 'B', 3, 'AVAILABLE'),
       ('1-B-4', 1, 'B', 4, 'AVAILABLE'),
       ('1-B-5', 1, 'B', 5, 'BOOKED');

INSERT INTO `seat` (id, journey_id, section, number, status)
VALUES ('2-A-1', 2, 'A', 1, 'BOOKED'),
       ('2-A-2', 2, 'A', 2, 'AVAILABLE'),
       ('2-A-3', 2, 'A', 3, 'AVAILABLE'),
       ('2-A-4', 2, 'A', 4, 'AVAILABLE'),
       ('2-A-5', 2, 'A', 5, 'AVAILABLE'),
       ('2-B-1', 2, 'B', 1, 'AVAILABLE'),
       ('2-B-2', 2, 'B', 2, 'AVAILABLE'),
       ('2-B-3', 2, 'B', 3, 'AVAILABLE'),
       ('2-B-4', 2, 'B', 4, 'AVAILABLE'),
       ('2-B-5', 2, 'B', 5, 'BOOKED');