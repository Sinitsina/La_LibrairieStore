alter table db_order
    add column region varchar (100),
    add column city varchar(100),
    add column district varchar(100),
    add column street varchar (100),
    add column house integer,
    add column apartment integer,
    add column index integer;
