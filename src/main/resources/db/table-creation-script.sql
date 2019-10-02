CREATE TABLE autopark.public_transport
(
    transport_number INT,
    bus_brand        VARCHAR(40) NOT NULL,
    capacity         INT         NOT NULL,
    issue_year       INT(4),
    PRIMARY KEY (transport_number)
);
CREATE TABLE autopark.routes
(
    route_number  INT,
    start_station VARCHAR(40)  NOT NULL,
    end_station   VARCHAR(40)  NOT NULL,
    stops         VARCHAR(200) NOT NULL,
    distance      INT,
    PRIMARY KEY (route_number)
);
CREATE TABLE autopark.drivers
(
    license    INT(20)     NOT NULL,
    fio        VARCHAR(40) NOT NULL,
    salary     INT         NOT NULL,
    address    VARCHAR(60) NOT NULL,
    birth_date date        NOT NULL,
    PRIMARY KEY (license)
);
CREATE TABLE autopark.schedule
(
    id             INT,
    departure_time DATE NOT NULL,
    arrival_time   DATE NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE autopark.stops
(
    stop_name            VARCHAR(30) NOT NULL,
    direction            VARCHAR(50) NOT NULL,
    arrival_time_on_stop DATE        NOT NULL,
    PRIMARY KEY (stop_name)
);