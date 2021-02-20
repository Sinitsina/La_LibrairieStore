create table db_user (
                         id serial not null constraint db_user_pkey primary key ,
                         name varchar(20) not null ,
                         lastname varchar(40) not null ,
                         email varchar(40) not null ,
                         phone_number varchar(20) not null ,
                         login varchar(20) not null ,
                         password varchar(20) not null ,
                         role varchar(20) not null ,
                         is_deleted boolean not null
);

create table cover (
                       id serial not null constraint cover_pkey primary key,
                       name varchar(30) not null ,
                       is_deleted boolean not null
);

create table format (
                        id serial not null constraint format_pkey primary key,
                        name varchar(30) not null ,
                        is_deleted boolean not null
);

create table sheets_type (
                             id serial not null constraint sheets_type_pkey primary key,
                             name varchar(30) not null ,
                             is_deleted boolean not null
);

create table manufacturer (
                              id serial not null constraint manufacturer_pkey primary key,
                              name varchar(30) not null ,
                              is_deleted boolean not null
);

create table db_order (
                          id serial not null constraint db_order_pkey primary key,
                          order_date date not null,
                          total_cost decimal not null,
                          users_id bigint constraint db_order_user_customer_id_fkey
                              references db_user,
                          address varchar(100) not null,
                          phone_number varchar(20) not null,
                          order_status varchar(20) not null
);

create table product (
                         id serial not null constraint product_pkey primary key,
                         title varchar(100) not null,
                         description varchar(1000) not null,
                         discount_percent int,
                         price decimal not null,
                         cover_id bigint not null constraint product_cover_id_fkey references cover,
                         format_id bigint not null constraint product_format_id_fkey references format,
                         sheets_type_id bigint not null constraint product_sheets_id_type_fkey references sheets_type,
                         manufacturer_id bigint not null constraint product_manufacturer_id_fkey references manufacturer,
                         is_deleted boolean not null
);

create table favourites (
                            product_id bigint
                                constraint favourites_product_id_fkey
                                    references product,
                            user_id  bigint
                                constraint favourites_user_id_fkey
                                    references db_user
);

create table shopping_card (
                               product_id bigint
                                   constraint shopping_card_product_id_fkey
                                       references product,
                               count int not null,
                               order_id  bigint
                                   constraint shopping_card_user_id_fkey
                                       references db_order
);

create table book (
                      id serial not null primary key,
                      product_id bigint constraint book_product_id_fkey
                          references product,
                      author varchar(30) not null,
                      genre varchar(20) not null,
                      year SMALLINT not null,
                      pages int not null

);

create table copybook (
                          id serial not null constraint copybook_pkey primary key,
                          product_id bigint constraint copybook_product_id_fkey
                              references product
);

create table datebook (
                          id serial not null constraint datebook_pkey primary key,
                          product_id bigint constraint datebook_product_id_fkey
                              references product,
                          is_dated boolean not null
);

create table notebook (
                          id serial not null constraint notebook_pkey primary key,
                          product_id bigint constraint notebook_product_id_fkey
                              references product
);

create table sketchbook (
                            id serial not null constraint sketchbook_pkey primary key,
                            product_id bigint constraint sketchbook_product_id_fkey
                                references product
);

create table diary (
                       id serial not null constraint diary_pkey primary key,
                       product_id bigint constraint diary_product_id_fkey
                           references product
);

create table office_paper (
                              id serial not null constraint office_paper_pkey primary key,
                              product_id bigint constraint office_paper_product_id_fkey
                                  references product
);

create table postcard (
                          id serial not null constraint postcard_pkey primary key,
                          product_id bigint constraint postcard_product_id_fkey
                              references product
);
