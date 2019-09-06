CREATE TABLE MY_DATABASE.autobus(
    number_of_autobus INT NOT NULL,
    bus_brand VARCHAR(40) NOT NULL,
    capacity INT NOT NULL,
    year_of_issue INT(4) NOT NULL,
    PRIMARY KEY(number_of_autobus)
);
CREATE TABLE MY_DATABASE.routes(
    number_of_route INT NOT NULL,
    start_station VARCHAR(40) NOT NULL,
    end_station VARCHAR(40) NOT NULL,
    stops VARCHAR(200) NOT NULL,
    distance INT NOT NULL,
    PRIMARY KEY(number_of_route)
);
CREATE TABLE MY_DATABASE.drivers(
    license INT(20) NOT NULL,
    fio VARCHAR(40) NOT NULL,
    salary INT NOT NULL,
    adress VARCHAR(60) NOT NULL,
    date_of_birth DATE not null,
    PRIMARY KEY(license)
);
CREATE TABLE MY_DATABASE.schedule(
    id INT NOT NULL,
    departure_time DATE NOT NULL,
    arrival_time DATE NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE MY_DATABASE.stops(
    name_of_stop VARCHAR(30) NOT NULL,
    direction VARCHAR(50) NOT NULL,
    arrival_time_on_stop DATE NOT NULL,
    PRIMARY KEY(name_of_stop)
);