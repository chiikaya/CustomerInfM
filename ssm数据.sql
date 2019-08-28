 用户角色权限

用户：人
角色：身份
权限：可以做什么事

先把权限根据类型组织起来，分配一个类型.
然后把某些角色组织起来，分配给某一个用户

#部门表   ：部门ID，部门名称，部门位置(location)   D department
用户表    ：用户ID，用户名，密码，手机号，邮箱，是否再用   sys_user
中间表1      DBID(主键)，用户ID，角色ID
角色表    ：角色ID，角色名称，描述，排序，是否再用   sys_role
中间表2      DBID ,角色ID，权限ID
权限表    ：权限ID ，权限名称，描述，权限层级，权限URL，排序，父权限，权限类型，是否再用  sys_auth



权限分为两类：
 1.访问页面的权限，对URL的访问成为模块权限
 2. 能访问页面的前提下，对页面控件的访问权限 ，资源权限


一个用户对应多个角色   1 ：多
一个角色对应多个用户   1 ：多
用户 ：角色   多 ：多


一个角色对应多个权限   1 ：多
一个权限对应多个角色   1 ：多
权限 ： 角色    多 ：多

一个部门对应多个用户   1 ：多


---------------------------------------------------------------------------------


#1.用户表 
create table sys_user(
 user_id int(5) primary key auto_increment,
 username varchar(10)  not null,
 password varchar(10)  not null,
 tel  varchar(11)  not null,
 email varchar(20)  not null,
 inuse int(1) default 0
);
insert into sys_user(username,password,tel,email)
VALUES
('admin','admin','15763782603','2476810979@qq.com'),
('boss','boss','15053795859','1476810979@qq.com'),
('manager','manager','17863782603','2345610979@qq.com'),
('low','low','15763786666','2222684385@qq.com');


#2.角色表
create table sys_role(
role_id int(5) primary key auto_increment,
role_name varchar(20)  not null,
role_desc varchar(20),
role_order int(3)  not null,
inuse int(1) default 0

);
insert into sys_role(role_name,role_desc,role_order)
VALUES
('系统管理员','系统内部维护,具有最高权限',10),
('总经理','总经理,具有此系统绝大多数权限',20),
('财务经理','财务经理,复制财务,具有部分权限',30),
('普通员工','具有少部分权限',40);


#3. 用户，角色中间表
create table sys_user_role(
dbid int(5) primary key auto_increment,
user_id int(5) REFERENCES sys_user(user_id),
role_id int(5)  REFERENCES sys_role(role_id)
);

insert into sys_user_role(user_id,role_id)
VALUES
(1,1),(1,2),(1,3),(1,4),
(2,2),
(3,3),(3,4),
(4,4);



#4. 权限表
create table sys_auth(
auth_id  int(5) primary key auto_increment,
auth_name varchar(20)  not null,
auth_desc varchar(50),
auth_layer int(1) not null,
auth_url varchar(30),
auth_order int(3) not null,
parent_id int(5) REFERENCES sys_auth(auth_id),
auth_type  int(1) default 0,
inuse int(1) default 0

);

insert into sys_auth(auth_name,auth_desc,auth_layer,auth_url,auth_order,parent_id)
VALUES
('仓库管理','仓库的日常维护',1,'',10,-1),
('财务管理','财务报表及凭证的制作',1,'',20,-1),
('系统管理','部门、用户、角色、权限管理',1,'',30,-1),

('用户管理','管理用户',2,'',10,3),
('角色管理','管理角色',2,'',20,3),
('权限管理','管理权限',2,'',30,3),
('部门管理','管理部门',3,'',50,3);

insert into sys_auth(auth_name,auth_desc,auth_layer,auth_url,auth_order,parent_id)
VALUES
('客户信息','客户的基本信息',2,'',10,1);
('客户分配','客户分配',2,'',20,1),
('客户关怀','客户关怀',2,'',30,1),
('客户类型','客户类型',3,'',40,1),
('客户状态','客户状态',3,'',50,1),
('客户来源','客户来源',3,'',60,1),
('联系记录','联系记录',3,'',70,1),
('联系人','联系人',3,'',80,1);


#5. 角色和权限的中间表

create table sys_role_auth(
dbid int(5) primary key auto_increment,
role_id int(5)  REFERENCES sys_role(role_id),
auth_id int(5) REFERENCES sys_auth(auth_id)
);
insert into sys_role_auth(role_id,auth_id)
VALUES
(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),
(2,1),(2,3),(2,3),(2,7),
(3,1),(3,2),(3,3),
(4,1);


#6.部门表
 sys_department

create table sys_dept(
dept_id int(5) primary key auto_increment,
dept_name varchar(30)  not null,
dept_desc varchar(20),
dept_location varchar(50)  not null,
inuse int(1) default 0
);
 

#7. 部门和用户的中间表
create table sys_dept_user(
dbid int(5) primary key auto_increment,
dept_id int(5)  REFERENCES sys_role(dept_id),
user_id int(5) REFERENCES sys_user(user_id)
);

insert into sys_dept_user(dept_id,user_id)
VALUES
(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),
(2,1),(2,3),(2,3),(2,7),
(3,1),(3,2),(3,3),
(4,1);





-------------------客户--------------------------------


#8. 创建客户信息表 
#编号，姓名，客户状态，客户来源，所属员工，客户类型，性别，手机，QQ，邮箱，职位，公司
create table sys_client(
client_id int(5) primary key auto_increment,
clientname varchar(30)  not null,
status_id int(5) REFERENCES sys_status(status_id),  #客户状态表的id
from_id  int(5) REFERENCES sys_from(from_id),  #客户来源表的id
staff_id int(5)  REFERENCES staff_desc(staff_id), #员工信息表的id
mold_id int(5) REFERENCES sys_mold(mold_id),  #客户类型表的id
sex  int(10) default 0,
tel varchar(11)  not null,
QQ  varchar(20)  not null,
email varchar(20)  not null,
post varchar(20)  not null,
company varchar(30)  not null
);
0.查询条数
select count(sys_client.client_id)
        from sys_client left outer join sys_status on sys_client.status_id=sys_status.status_id
        left outer join sys_from on sys_client.from_id=sys_from.from_id
        left outer join staff_desc on sys_client.staff_id=staff_desc.staff_id
        left outer join sys_mold on sys_client.mold_id=sys_mold.mold_id


1.查询数据
select sys_client.client_id as id ,sys_client.clientname as text,sys_status.status_inuse,sys_status.status_id,
        sys_from.from_name,sys_from.from_id,staff_desc.staff_name,staff_desc.staff_id,sys_mold.mold_name,sys_mold.mold_id,sys_client.sex,sys_client.tel,sys_client.QQ,sys_client.email,
        sys_client.post,sys_client.company
        from sys_client left outer join sys_status on sys_client.status_id=sys_status.status_id
        left outer join sys_from on sys_client.from_id=sys_from.from_id
        left outer join staff_desc on sys_client.staff_id=staff_desc.staff_id
        left outer join sys_mold on sys_client.mold_id=sys_mold.mold_id

2.插入
 <insert id="addClient" parameterType="map">
        insert into sys_client (clientname,status_id,from_id,staff_id,mold_id,sex,tel,QQ,email,post,company)
        values(#{clientname},#{status_id},#{from_id},#{staff_id},#{mold_id},#{sex},#{tel},#{QQ},#{email},#{post},#{company})
 </insert>

3.修改
<update id="updateClient" parameterType="map">
        update sys_client set
        clientname=#{clientname},
        status_id= #{status_id},
        from_id=#{from_id},
        staff_id=#{staff_id},
        mold_id=#{mold_id},
        sex=#{sex},
        tel=#{tel},
        QQ=#{QQ},
        email=#{email},
        post=#{post},
        company=#{company}
        where client_id = #{client_id}
    </update>

4.假删客户
    <update id="deleteClient" parameterType="int">
        update sys_client
        set inuse = 1
        where client_id = #{client_id}
    </update>

 真删除
    <delete id="deleteClient" parameterType="int">
        delete from sys_client where client_id = #{client_id}
    </delete>



#10. 客户状态
# 客户编号，客户状态，客户描述
create table sys_status(
status_id int(5) primary key auto_increment,
status_inuse  varchar(30)  not null,
status_desc varchar(30)
);

 select status_id as id ,status_inuse as text,status_desc
        from sys_status


#11. 客户来源
# 来源编号 ，来源名称 from_id  from_name
create table sys_from(
from_id int(5) primary key auto_increment,
from_name varchar(30)  not null
);

 select from_id as id ,from_name as text
        from sys_from


#12. 客户类型
#  类型编号 ，客户类型
create table sys_mold(
mold_id int(5) primary key auto_increment,
mold_name varchar(30)  not null
);

select mold_id as id ,mold_name as text
        from sys_mold


#13. 客户分配
# 分配编号，姓名，状态，来源，类型，创建时间，公司
create table sys_allot(
allot_id int(5) primary key auto_increment,
allot_name varchar(30)  not null,
allot_inuse int(10) default 0,
allot_source int(10) default 0,
allot_type int(10) default 0,
create_time timestamp,
company varchar(30)  not null
);




#14. 客户关怀
#编号，客户，主题，方式，关怀时间，下次关怀时间，备注
create table sys_thought(
thought_id int(5) primary key auto_increment, 
client_id int(5) REFERENCES sys_client(client_id),  #客户信息表的id
thought_text varchar(30)  not null,
thought_way varchar(30)  not null,
create_time timestamp,
reply_time timestamp,
thought_desc varchar(30),
person varchar(30)
);

select sys_thought.thought_id as id,sys_client.clientname as text,sys_client.client_id,sys_thought.thought_text,
        sys_thought.thought_way,sys_thought.create_time,sys_thought.reply_time,
        sys_thought.thought_desc,sys_thought.person,sys_client.clientname
        from sys_thought left outer join sys_client
        on sys_thought.client_id = sys_client.client_id




#15. 联系记录  contact_ record
#编号,客户姓名,联系时间,下次联系时间,联系类型,是谁联系,联系主题,联系备注

create table contact_record(
record_id int(5) primary key auto_increment,
client_id int(5) REFERENCES sys_client(client_id),  #客户信息表的id
create_time timestamp,
reply_time timestamp,
record_type varchar(20)  not null,
record_who int(5) REFERENCES contcat_person(person_id),  #联系人表的id
record_text varchar(30)  not null,
record_desc varchar(30),
inuse int(5) default 0
);
select contact_record.record_id as id,sys_client.clientname as text,sys_client.client_id,
        contact_record.create_time,contact_record.reply_time,contact_record.record_type,
        p.person_name,p.person_id,contact_record.record_text,contact_record.record_desc
        from contact_record left outer join sys_client on contact_record.client_id=sys_client.client_id
        left outer join contcat_person as p on contact_record.record_who=p.person_id


#16. 联系人  contcat_person
#编号，属于的客户，联系人姓名，性别，年龄，职位，电话，与客户关系

create table contcat_person(
person_id int(5) primary key auto_increment,
person_customer int(5) REFERENCES sys_client(client_id),  #客户信息表的id
person_name varchar(30)  not null,
person_sex int(10) default 0,
person_age int(10),
person_post varchar(20),
person_tel varchar(11),
customer_nexus varchar(30),
inuse int(5) default 0
);


select  p.person_id as id,c.clientname as text,p.person_name,p.person_sex,p.person_age,p.person_post,p.person_tel,p.customer_nexus
        from contcat_person as p left outer join sys_client as c on p.person_customer=c.client_id




--------------------员工-----------------------
#员工信息
#姓名 ，性别，年龄，民族，部门，角色，学历婚姻，家庭地址，手机，电话，邮箱

CREATE TABLE staff_desc (
  staff_id int(10) primary key auto_increment,
  staff_name varchar(50) not null,
  staff_age varchar(10)  not null,
  staff_sex int(10) default 0,
  staff_nation varchar(10) not null,
  staff_dept int(10) REFERENCES sys_dept(dept_id), #部门表的id
  staff_role  int(10) REFERENCES sys_role(role_id), #角色表的id
  staff_degree varchar(10) ,
  staff_marital int(10) default 0,
  staff_address varchar(10) ,
  staff_phone varchar(20),
  staff_tel varchar(10) ,
  staff_email varchar(50),
  inuse int(5) DEFAULT 0
 );

select d.staff_id as id,d.staff_name as text,d.staff_age,d.staff_sex,
        d.staff_nation,e.dept_name,e.dept_id,r.role_name,r.role_id, d.staff_degree,d.staff_marital,
        d.staff_address, d.staff_phone,d.staff_tel,d.staff_email
        from staff_desc as d left outer join sys_dept as e on d.staff_dept=e.dept_id
        left outer join sys_role as r on d.staff_role=r.role_id


#房屋类型  
#类型编号，房屋类型名称
create table house_type(
type_id int(5) primary key auto_increment,
type_name varchar(30)  not null,
inuse int(5) DEFAULT 0
);

select type_id as id,type_name as text
        from house_type

 
#房屋信息
#编号，户型，管理员工，房屋地址，房屋价格
create table house_info(
info_id int(5) primary key auto_increment,
type_id int(10) REFERENCES house_type(type_id),   #房屋类型表的id
staff_id int(5) REFERENCES staff_desc(staff_id),  #员工信息表的id
info_adress varchar(10),
info_price double(10,0),
inuse int(5) DEFAULT 0
);

select h.info_id as id,t.type_name as text ,t.type_id,s.staff_name,s.staff_id,h.info_adress,h.info_price
        from house_info as h left outer join house_type as t on h.type_id=t.type_id
        left outer join staff_desc as s on h.staff_id=s.staff_id


#公告
#编号 ，公告人，公告主题，公告内容，公告时间，公告截止时间
create table notice(
notice_id int(5) primary key auto_increment,
notice_person int(10) REFERENCES staff_desc(staff_id),#员工表的id
notice_text varchar(30),
notice_content varchar(50),
create_time timestamp,
reply_time timestamp,
inuse int(5) DEFAULT 0
);
notice_id,notice_person,notice_text,notice_content,create_time,reply_time

select n.notice_id,s.staff_name,s.staff_id,n.notice_text,n.notice_content,n.create_time,n.reply_time
        from notice as n left outer join staff_desc as s on n.notice_person=s.staff_id

--------------------常用查询语句----------------------

#1. 根据用户id查询用户具有的权限
select auth_id as id ,auth_name as text,auth_desc,auth_layer,auth_url,auth_order,parent_id,auth_type,inuse
 from sys_auth
    where auth_id in(select auth_id from sys_role_auth where role_id in(
    select role_id from sys_user_role where role_id=1
    )
    )order by auth_layer,auth_order


#2. 查询所有的权限
select auth_id as id ,auth_name as text,auth_desc,auth_layer,auth_url,auth_order,parent_id,auth_type,inuse
 from sys_auth order by auth_layer,auth_order
    

#3. 添加权限
 insert into(auth_name,auth_desc,auth_layer,auth_url,auth_order,parent_id,auth_type,inuse)
 values(#{auth_name},#{auth_desc},#{auth_layer},#{auth_url},#{auth_order},#{parent_id},#{auth_type},#{inuse});


#4. 修改权限
update sys_auth set
        auth_name=#{auth_name},
        auth_desc=#{auth_desc},
        auth_url=#{auth_url},
        auth_order=#{auth_order},
        auth_type=#{auth_type},
        inuse=#{inuse}
        where auth_id = #{auth_id}

# 查询所有的用户
select user_id as id ,username as text,password,tel,email,inuse
 from sys_user
    
# 查询条数
    <select id="findRoleCount" parameterType="map"
            resultType="int">
        select count(role_id)
        from sys_role
        <where>
            <if test="role_name!=null and role_name!=''">
                and role_name like concat(concat('%',#{role_name}),'%')
            </if>
            <if test="role_desc!=null and role_desc!=''">
                and role_desc like concat(concat('%',#{role_desc}),'%')
            </if>
        </where>
    </select>

# 2.查询数据 
    <select id="findAllRole" parameterType="map"
            resultType="map">
        select role_id as id ,role_name as text,role_desc,role_order,inuse
        from sys_role
        <where>
            <if test="role_name!=null and role_name!=''">
                and role_name like concat(concat('%',#{role_name}),'%')
            </if>
            <if test="role_desc!=null and role_desc!=''">
                and role_desc like concat(concat('%',#{role_desc}),'%')
            </if>
        </where>
        limit #{startIndex},#{pageSize}
    </select>

    
#查询员工所在的部门
select sys_user.user_id,sys_user.username,sys_user.password,
sys_user.tel,sys_user.email,sys_dept.dept_name,sys_dept.inuse

from sys_user  left outer join sys_dept
on sys_user.dept_id = sys_dept.dept_id
 
where sys_user.inuse = 0;

#查询出每个用户的编号,姓名,密码,电话,email,inuse,部门名称

select u.user_id as id ,u.username as text,u.password,u.tel,u.email,u.inuse, d.dept_name
from sys_user u,sys_dept d
where user_id=dept_id


select * from sys_client where clientname="张三" or staff="张三" or post="张三"


