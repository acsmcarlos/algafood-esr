create table item_pedido (
	id bigint not null auto_increment,
	quantidade Integer not null,
	preco_unitario decimal(10,2) not null,
	preco_total decimal(10,2) not null,
	observacao varchar(255) null,
	pedido_id bigint not null,
	produto_id bigint not null,
	
	primary key (id),
	
	constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido (id),
	constraint fk_item_pedido_produto foreign key (produto_id) references produto (id)
) engine=InnoDB default charset=utf8;