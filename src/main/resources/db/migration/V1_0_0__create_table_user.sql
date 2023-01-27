create table fwk_user (
    id            serial
        constraint fwk_user_pk primary key,
    name varchar (80),
    created_at timestamptz,
    updated_at timestamptz
);