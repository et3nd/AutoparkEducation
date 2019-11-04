CREATE TABLE autopark.public_transport
(
    transport_number INT,
    bus_brand        VARCHAR(255) NOT NULL,
    capacity         INT,
    issue_year       INT,
    PRIMARY KEY (transport_number)
);
CREATE TABLE autopark.route
(
    route_number  INT,
    start_station VARCHAR(255)  NOT NULL,
    end_station   VARCHAR(255)  NOT NULL,
    stops         VARCHAR(255) NOT NULL,
    distance      INT,
    PRIMARY KEY (route_number)
);
CREATE TABLE autopark.driver
(
    license    INT,
    fio        VARCHAR(255) NOT NULL,
    salary     INT,
    address    VARCHAR(255) NOT NULL,
    birth_date date        NOT NULL,
    PRIMARY KEY (license)
);
CREATE TABLE autopark.schedule
(
    id             INT,
    departure_time TIME NOT NULL,
    arrival_time   TIME NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE autopark.stop
(
    stop_name            VARCHAR(255) NOT NULL,
    direction            VARCHAR(255) NOT NULL,
    arrival_time_on_stop TIME        NOT NULL,
    PRIMARY KEY (stop_name)
);