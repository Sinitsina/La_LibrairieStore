create table paper_type (
                            id serial not null constraint paper_type_pkey primary key,
                            name varchar(30) not null ,
                            is_deleted boolean not null
);