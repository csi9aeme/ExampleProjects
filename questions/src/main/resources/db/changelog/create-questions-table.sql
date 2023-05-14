create table questions (
    id bigint not null auto_increment,
    answer_text varchar(255),
    answered bit not null,
    answered_at datetime(6),
    created_at datetime(6),
    question_text varchar(255),
    votes integer not null,
    member_id bigint, primary key (id));
