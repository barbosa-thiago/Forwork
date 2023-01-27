create table fwk_task (
    id serial constraint fwk_task_pk primary key,
    title varchar (80),
    description varchar (150),
    deadline timestamptz,
    time_spent_in_seconds bigint,
    finished boolean,
    created_at timestamptz,
    updated_at timestamptz
);