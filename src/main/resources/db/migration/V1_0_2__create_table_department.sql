create table fwk_department (
    id serial constraint fwk_department_pk primary key,
    name varchar (20),
    created_at timestamptz,
    updated_at timestamptz
);

alter table fwk_user
    add department_id int constraint fwk_user_department_fk references fwk_department;

alter table fwk_task
    add department_id int constraint fwk_task_department_fk references fwk_department;