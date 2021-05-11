DROP TABLE IF EXISTS car CASCADE;
DROP TABLE IF EXISTS ride CASCADE;

CREATE TABLE public.car (
	plate_number varchar(255) NOT NULL,
	model varchar(255) NULL,
	CONSTRAINT car_pkey PRIMARY KEY (plate_number)
);

CREATE TABLE public.ride (
	ride_id int8 NOT NULL,
	company_name varchar(255) NULL,
	ride_cost float8 NULL,
	ride_date timestamp NULL,
	car_id varchar(255) NULL,
	CONSTRAINT ride_pkey PRIMARY KEY (ride_id)
);

ALTER TABLE public.ride ADD CONSTRAINT fksw6vk9tlt62y7xk9yj8yej2jw FOREIGN KEY (car_id) REFERENCES public.car(plate_number);

INSERT INTO public.car (plate_number, model) VALUES
('E123KO178', 'Nissan'),
('A456MC777', 'BMW'),
('P789ET178', 'Renault');

INSERT INTO public.ride (ride_id, company_name, ride_cost, ride_date, car_id) VALUES
(2, 'YANDEX_DRIVE', 123.76, '1992-03-25T15:15:15', 'E123KO178'),
(3, 'YANDEX_DRIVE', 32.40, '1992-03-26T06:15:15', 'E123KO178'),
(4, 'CITY_DRIVE', 78.31, '2010-09-01T21:00:15', 'A456MC777'),
(5, 'CITY_DRIVE', 923.10, '2014-06-21T10:00:00', 'A456MC777'),
(6, 'DELIMOBIL', 4098.00, '2016-05-25T19:18:10', 'P789ET178'),
(7, 'DELIMOBIL', 54.2, '2019-02-12T13:18:10', 'P789ET178')
;