insert into graph values(1, 'First Graph');

insert into vertex values (1, 1, 'ME');
insert into vertex values (2, 1, 'Stefan');
insert into vertex values (3, 1, 'Amir');
insert into vertex values (4, 1, 'Martin');
insert into vertex values (5, 1, 'Adam');
insert into vertex values (6, 1, 'Philipp');
insert into vertex values (7, 1, 'Diana');

insert into edge values (1, 1, 2, 100); 
insert into edge values (2, 1, 3, 1042); 
insert into edge values (3, 1, 4, 595); 
insert into edge values (4, 1, 5, 10); 
insert into edge values (5, 1, 6, 128); 
insert into edge values (6, 2, 3, 850); 
insert into edge values (7, 2, 5, 85); 
insert into edge values (8, 5, 6, 7); 
insert into edge values (9, 5, 4, 400); 
insert into edge values (10, 5, 7, 33);
insert into edge values (11, 7, 3, 57); 
insert into edge values (12, 7, 4, 3); 

insert into test_case values (1, 'Philipp', 1, 1, 1, 400, 2.06, 1);
insert into test_case values (2, 'Martin', 20, 47, 12, 1890, 16.96, 1);
insert into test_case values (3, 'Stefan', 10, 10, 2, 800, 10.00, 1);