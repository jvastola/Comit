SELECT COUNT(DISTINCT d_name) 
  FROM developer,
       groups
 WHERE g_gid = d_gid AND 
       d_gid = 10;
       
-- Number of Commits by joebob

SELECT COUNT(c_cid) 
  FROM comit,
       developer
 WHERE c_cid = d_did AND 
       d_name = 'joebob';
       
-- Project Names not in Groups

SELECT p_name
  FROM project
 WHERE p_did > 0 OR 
       p_did < 0;
       
-- FIND USERS WHO MADE EDITS

SELECT u_name
  FROM edit,
       comit,
       user
 WHERE e_cid = c_cid AND 
       u_uid = c_uid;
