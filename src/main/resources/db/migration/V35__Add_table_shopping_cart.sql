create table shopping_card (
                               order_id  bigint references db_order(id)
                                   on update cascade on delete cascade,
                               product_id bigint  references product(id)
                                   on update cascade on delete cascade,
                                   constraint order_product_pkey primary key (order_id, product_id),
                               count int not null);