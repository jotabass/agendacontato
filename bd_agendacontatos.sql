--script 21/09/22
create table usuario(
	idusuario		serial			primary key,
	nome			varchar(150)	not null,
	email			varchar(100)	not null unique,
	senha			varchar(40)		not null);
	
create table contato(
	idcontato		serial			primary key,
	nome			varchar(150)	not null,
	telefone		varchar(20)		not null,
	email			varchar(100)	not null,
	datanasc		date			not null,
	idusuario		integer			not null,
	foreign key(idusuario)
		references usuario(idusuario));