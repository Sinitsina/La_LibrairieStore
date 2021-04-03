alter table shopping_card
    add column order_id  bigint references db_order(id)
        on update cascade on delete cascade;

alter table shopping_card
    add column product_id bigint  references product(id)
        on update cascade on delete cascade;

