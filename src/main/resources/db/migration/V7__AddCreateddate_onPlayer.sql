ALTER TABLE PLAYER ADD COLUMN CREATEDDATE long;

update PLAYER set createddate = 0;

commit;
