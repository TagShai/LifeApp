CREATE DATABASE `life_app` DEFAULT CHARACTER SET utf8;

-- authorities for mannaya_kasha
GRANT CREATE, SELECT, INSERT, UPDATE, DELETE, ALTER
ON `life_app`.*
TO mannaya_kasha@localhost
IDENTIFIED BY 'mannaya_kasha_password';

GRANT CREATE, SELECT, INSERT, UPDATE, DELETE, ALTER
ON `life_app`.*
TO mannaya_kasha@'%'
IDENTIFIED BY 'mannaya_kasha_password';