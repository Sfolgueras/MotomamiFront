CREATE DATABASE IF NOT exists motomamiFront;
use motomamiFront;
create table if not exists mm_user (
	id int primary key auto_increment,
    nombre varchar(50),
    apellidos varchar(50),
    fecha_nacimiento date,	
    direccion varchar(100),
    telefono int,
    dni varchar(9),
    carnet_conducir varchar(9),
    email varchar(100),
    tipo_vehiculo varchar(100),
    matricula varchar(100),
    marca varchar(100),
    modelo varchar(100),
    contrasenia varchar(100),
    genero varchar(100),
    tipo_usuario varchar(100) default 'customer'
);

CREATE TABLE IF NOT EXISTS mm_invoice(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE,
    descripcion VARCHAR(2000),
    importe DECIMAL(10, 2),
    idUser INT,
    FOREIGN KEY (idUser) REFERENCES mm_user(id)
);

create table if not exists mm_part(
id int primary key auto_increment,
fecha date,
descripcion varchar (2000),
daños varchar (500),
idUser int,
foreign key (idUser) references mm_user(id)
);

create table if not exists mm_state(
id int primary key,
descripcion varchar (2000)
)

SELECT COUNT(*) AS numCliente FROM mm_user WHERE email = 'juan@motomami.com' AND contrasenia = 'contrasenia123';


-- Insert trabajador 1
INSERT INTO mm_user (nombre, apellidos, fecha_nacimiento, direccion, telefono, dni, email, contrasenia, tipo_usuario) 
VALUES ('Juan', 'Perez Garcia', '1990-05-15', 'Calle Principal 123', 123456789, '12345678A', 'juan@motomami.com', 'contrasenia123', 'worker');

-- Insert trabajador 2
INSERT INTO mm_user (nombre, apellidos, fecha_nacimiento, direccion, telefono, dni, email, contrasenia, tipo_usuario) 
VALUES ('Maria', 'Lopez Fernandez', '1985-08-20', 'Avenida Central 456', 987654321, '98765432B', 'maria@motomami.com', 'password456', 'worker');

-- Insert trabajador 3
INSERT INTO mm_user (nombre, apellidos, fecha_nacimiento, direccion, telefono, dni, email, contrasenia, tipo_usuario) 
VALUES ('Pedro', 'Gomez Martinez', '1982-11-10', 'Plaza Principal 789', 555444333, '55544433C', 'pedro@motomami.com', 'securepass789', 'worker');

