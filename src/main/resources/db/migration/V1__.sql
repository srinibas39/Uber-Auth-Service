CREATE TABLE booking
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    created_at     datetime NOT NULL,
    updated_at     datetime NOT NULL,
    booking_status ENUM( 'SCHEDULED',
    'CANCELLED',
    'CAR_DRIVER',
    'ASSIGNING_DRIVER',
    'IN_RIDE',
    'COMPLETED') NULL,
    end_time       datetime NULL,
    start_time     datetime NULL,
    total_distance BIGINT NULL,
    driver_id      BIGINT NULL,
    passenger_id   BIGINT NULL,
    review_id      BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE booking_review
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime     NOT NULL,
    updated_at datetime     NOT NULL,
    rating DOUBLE NULL,
    content    VARCHAR(255) NOT NULL,
    booking_id BIGINT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE course
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime NOT NULL,
    updated_at    datetime NOT NULL,
    `description` VARCHAR(255) NULL,
    name          VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE course_student_list
(
    course_id       BIGINT NOT NULL,
    student_list_id BIGINT NOT NULL
);

CREATE TABLE driver
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime NOT NULL,
    updated_at  datetime NOT NULL,
    car_lisence VARCHAR(255) NULL,
    name        VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE driver_review
(
    driver_comment VARCHAR(255) NOT NULL,
    driver_rating  VARCHAR(255) NOT NULL,
    review_id      BIGINT       NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (review_id)
);

CREATE TABLE passenger
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NOT NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE passenger_review
(
    passenger_comment VARCHAR(255) NOT NULL,
    passenger_rating  VARCHAR(255) NOT NULL,
    passenger_id      BIGINT       NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (passenger_id)
);

CREATE TABLE student
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NOT NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE student_courses
(
    student_id BIGINT NOT NULL,
    course_id  BIGINT NOT NULL
);

ALTER TABLE booking
    ADD CONSTRAINT UK_2c57floc70nhp4ehcsn9ctr71 UNIQUE (review_id);

ALTER TABLE driver
    ADD CONSTRAINT UK_om9p8lqjh2lu02t1haph5r7hh UNIQUE (car_lisence);

ALTER TABLE booking_review
    ADD CONSTRAINT UK_p4ps590fybk1a6a07kydp51h UNIQUE (booking_id);

ALTER TABLE course_student_list
    ADD CONSTRAINT FK1wk5ao47fo8f0ed91xniw22bs FOREIGN KEY (student_list_id) REFERENCES student (id) ON DELETE NO ACTION;

CREATE INDEX FK1wk5ao47fo8f0ed91xniw22bs ON course_student_list (student_list_id);

ALTER TABLE passenger_review
    ADD CONSTRAINT FK3ac7ywfrp0s1vnv92yplej0f9 FOREIGN KEY (passenger_id) REFERENCES booking_review (id) ON DELETE NO ACTION;

ALTER TABLE course_student_list
    ADD CONSTRAINT FK7gt3758vmi37fjvdp7thkj61q FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE NO ACTION;

CREATE INDEX FK7gt3758vmi37fjvdp7thkj61q ON course_student_list (course_id);

ALTER TABLE booking
    ADD CONSTRAINT FKabxd6qpdfkp11yan46jj1td90 FOREIGN KEY (passenger_id) REFERENCES passenger (id) ON DELETE NO ACTION;

CREATE INDEX FKabxd6qpdfkp11yan46jj1td90 ON booking (passenger_id);

ALTER TABLE student_courses
    ADD CONSTRAINT FKc614in0kdhj9sih7vw304qxgj FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE NO ACTION;

CREATE INDEX FKc614in0kdhj9sih7vw304qxgj ON student_courses (course_id);

ALTER TABLE booking
    ADD CONSTRAINT FKd3n9h18mu017cxfopghwkri7s FOREIGN KEY (driver_id) REFERENCES driver (id) ON DELETE NO ACTION;

CREATE INDEX FKd3n9h18mu017cxfopghwkri7s ON booking (driver_id);

ALTER TABLE booking
    ADD CONSTRAINT FKh1stionm0jgsyfg7fv98trhjj FOREIGN KEY (review_id) REFERENCES booking_review (id) ON DELETE NO ACTION;

ALTER TABLE student_courses
    ADD CONSTRAINT FKiqufqwgb6im4n8xslhjvxmt0n FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE NO ACTION;

CREATE INDEX FKiqufqwgb6im4n8xslhjvxmt0n ON student_courses (student_id);

ALTER TABLE booking_review
    ADD CONSTRAINT FKjkq7o59og4g3162nfq8kehrep FOREIGN KEY (booking_id) REFERENCES booking (id) ON DELETE NO ACTION;

ALTER TABLE driver_review
    ADD CONSTRAINT FKl1al8jhswckq8stpr5s6t0i51 FOREIGN KEY (review_id) REFERENCES booking_review (id) ON DELETE NO ACTION;