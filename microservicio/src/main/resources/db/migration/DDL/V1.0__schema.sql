create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);
create table cita (
 id int(11) not null auto_increment,
 nombre varchar(80) not null,
 id_persona int(10) not null,
 tipo_servicio int(2) not null,
 costo_servicio int(6) not null,
 fecha_cita datetime not null,
 primary key (id)
);