# SprinigCRUD

create table dbo.clientes(
      cod_cli           int not null AUTO_INCREMENT,
      nome_cli    varchar(80),
      data_nasc   datetime,
      cidade_nasc varchar(100),
      pai_cli           varchar(80),
      mae_cli           varchar(80),
      cpf_cli           varchar(14),
      rg_cli            varchar(20),
      data_cad    datetime default now(),
      primary key (cod_cli)
);

create table dbo.tipos_contato (
      cod_tipo    int not null AUTO_INCREMENT,
      desc_tipo   varchar(35),
      data_cad    datetime default now(),
      primary key (cod_tipo)
);

create table dbo.telefones_clientes (
      cod_fone    int not null AUTO_INCREMENT,
      cod_cli           int,
      ddd_fone    int,
      num_fone    int,
      cod_tipo    int,
      data_cad    datetime default now(),
      primary key (cod_fone),
      CONSTRAINT FK_tel_cli FOREIGN KEY (cod_cli) REFERENCES clientes(cod_cli),
      CONSTRAINT FK_tel_tipo FOREIGN KEY (cod_tipo) REFERENCES tipos_contato(cod_tipo)
);

create table dbo.enderecos_clientes ( 
      cod_ende    int not null AUTO_INCREMENT, 
      cod_cli           int, 
      cod_tipo    int, 
      logradouro  varchar(80), 
      bairro            varchar(80), 
      complemento varchar(80),       
      cidade            varchar(80), 
      cep               varchar(8), 
      data_cad    datetime default now(), 
      primary key (cod_ende), 
      CONSTRAINT FK_ende_cli FOREIGN KEY (cod_cli) REFERENCES clientes(cod_cli), 
      CONSTRAINT FK_ende_tipo FOREIGN KEY (cod_tipo) REFERENCES tipos_contato(cod_tipo) 
); 
insert dbo.tipos_contato (desc_tipo) VALUES ('RESIDENCIAL'); 
insert dbo.tipos_contato (desc_tipo) VALUES ('COMERCIAL'); 
insert dbo.tipos_contato (desc_tipo) VALUES ('OUTROS');

