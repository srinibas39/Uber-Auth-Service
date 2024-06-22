ALTER TABLE driver
    ADD phone_number BLOB NULL;

ALTER TABLE booking
    DROP COLUMN booking_status;

ALTER TABLE booking
    ADD booking_status VARCHAR(255) NULL;