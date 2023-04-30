
create sequence bm_id_seq start with 1 increment by 50;

CREATE TABLE kb_column (
  id bigint default nextval('bm_id_seq') not null,
  title VARCHAR(255),
  description VARCHAR(255),
  assignee VARCHAR(255),
  priority VARCHAR(255),
  status VARCHAR(255),
  date_due timestamp(6) with time zone,
  tags VARCHAR(255),
  time_estimate BIGINT
);