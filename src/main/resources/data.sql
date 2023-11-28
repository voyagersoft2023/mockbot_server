insert into apis (id,project_id, api_name, group_yn, upper_id, "depth", "order")
values (1,1, 'API명', 'N', 0, 1, 1);

insert into api_requests (id,api_id,"key","name",data_type, upper_id, "depth", "order", json_data)
values (1,1, 'KEY', 'RE 명', 1, 0, 1, 1 , '빈값');