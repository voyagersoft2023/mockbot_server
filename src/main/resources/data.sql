insert into projects (id, name, context, access_authority, explanation, create_date, update_date, create_user_id)
values (1,'프로젝트', '/uri', 'A', '설명', '20231130131500', '20231130131500', 1);

insert into apis (id,project_id, api_name, group_yn, upper_id, "depth", "order", create_date, update_date, create_user_id)
values (1,1, 'API명', 'N', 0, 1, 1, '20231130131500', '20231130131500', 1);

insert into api_requests (id,api_id,"key","name",data_type, upper_id, "depth", "order", json_data, create_date, update_date, create_user_id)
values (3,1, 'KEY', 'REQUEST 명', 1, 0, 1, 1 , '빈값', '20231130131500', '20231130131500', 1);

insert into api_requests (id,api_id,"key","name",data_type, upper_id, "depth", "order", json_data, create_date, update_date, create_user_id)
values (4,1, 'KEY', 'REQUEST 명2', 1, 0, 1, 1 , '빈값2', '20231130131500', '20231130131500', 1);

insert into api_responses (id,api_id,"key","name",data_type, upper_id, "depth", "order", create_date, update_date, create_user_id)
values (1,1, 'KEY', 'RESPONSE 명', 1, 0, 1, 1, '20231130131500', '20231130131500', 1);