/* 设置字符集 */
set names gbk;

/* 数据库名称改为 forum */
drop database if exists forum_dev;
create database forum_dev;
use forum_dev;


/* =================== homework 数据库 =================== */

/* 创建一样的表(名称可以不同) */
drop table if exists student_raw;
create table student_raw
(
    stu_term                  char(11)               not null comment '学期(与term表有外键关系)',
    stu_grade                 char(4)                not null comment '年级',
    stu_no                    char(8)                not null primary key comment '学号(主键)',
    stu_name                  char(32)               not null comment '姓名',
    stu_sex                   enum ('男','女','/')   not null default '/' comment '性别',
    stu_password              char(32)               not null comment '密码(md5)',
    stu_class_fname           char(32)               not null comment '专业/班级全称',
    stu_class_sname           char(16)               not null comment '专业/班级简称',
    stu_wtype                 enum ('0','1')         not null default '0',
    stu_userlevel             char(1)                not null default '0' comment '学生用户等级(0:普通用户 1:助教 5:管理员 9:超级用户)',
    stu_enable                enum ('0','1')         not null default '1' comment '账号是否启用(''0'':禁止登录 ''1'':允许登录 注意:enum不要当int处理)',
    stu_add_date              datetime               not null comment '系统注册时间',
    stu_cno_1                 char(16) comment '学生选修的课程1的课号(与course表有外键关系)',
    stu_cno_1_is_special      enum ('0','1')         not null default '0',
    stu_cno_1_is_del          enum ('0','1')         not null default '0' comment '学生选修的课程1是否退课(''0'':正常 ''1'':已退课 注意:enum不要当int处理,退课学生对应课程的作业信息不要显示出来)',
    stu_cno_1_lastop_date     datetime,
    stu_cno_1_lab             char(16),
    stu_cno_1_lab_is_del      enum ('0','1')         not null default '0',
    stu_cno_1_lab_lastop_date datetime,
    stu_cno_1_flag            enum ('B','M','L','/') not null default '/',
    stu_cno_2                 char(16) comment '学生选修的课程2的课号(与course表有外键关系)',
    stu_cno_2_is_special      enum ('0','1')         not null default '0',
    stu_cno_2_is_del          enum ('0','1')         not null default '0' comment '学生选修的课程2是否退课(''0'':正常 ''1'':已退课 注意:enum不要当int处理,退课学生对应课程的作业信息不要显示出来)',
    stu_cno_2_lastop_date     datetime,
    stu_cno_2_lab             char(16),
    stu_cno_2_lab_is_del      enum ('0','1')         not null default '0',
    stu_cno_2_lab_lastop_date datetime,
    stu_cno_2_flag            enum ('B','M','L','/') not null default '/',
    stu_cno_3                 char(16) comment '学生选修的课程3的课号(与course表有外键关系)',
    stu_cno_3_is_special      enum ('0','1')         not null default '0',
    stu_cno_3_is_del          enum ('0','1')         not null default '0' comment '学生选修的课程3是否退课(''0'':正常 ''1'':已退课 注意:enum不要当int处理,退课学生对应课程的作业信息不要显示出来)',
    stu_cno_3_lastop_date     datetime,
    stu_cno_3_lab             char(16),
    stu_cno_3_lab_is_del      enum ('0','1')         not null default '0',
    stu_cno_3_lab_lastop_date datetime,
    stu_cno_3_flag            enum ('B','M','L','/') not null default '/',
    stu_is_del                enum ('0','1')         not null default '0' comment '该学生是否被删除(''0'':正常 ''1'':已删除 注意:被删除则无论stu_enbale置何值均不允许登录)',
    stu_comment               text comment '备注信息'
) ENGINE = FEDERATED
  CONNECTION = 'mysql://forum_federated:FoRum#feDeRatEd$oCt.2023@10.80.42.245:3306/homework/student'
  DEFAULT CHARSET = gbk;

drop table if exists homework_raw;
create table homework_raw
(
    hw_term                char(11)               not null comment '学期(主键+外键)',
    hw_cno                 char(16)               not null,
    hw_id                  smallint               not null comment '作业序号(主键)',
    hw_week                tinyint                not null comment '布置周',
    hw_chapter             tinyint                not null comment '章节(0-20: 第0-20章 90:大作业 98:文档作业 99:其它作业)',
    hw_filename            char(64)               not null comment '交作业网站的提交文件名',
    hw_description         char(64)               not null comment '作业描述',
    hw_type                char(1)                not null default '0',
    hw_filesize_min        int                    not null default 0,
    hw_filesize_max        int                    not null default 65536,
    hw_bdate               datetime               not null comment '作业提交开始时间',
    hw_edate               datetime               not null comment '作业提交结束时间',
    hw_decrease            smallint               not null default 5,
    hw_score               decimal(5, 1)          not null default 0 comment '本作业分数',
    hw_compile_vs          enum ('Y','/')         not null default 'Y',
    hw_compile_dev         enum ('Y','/')         not null default 'Y',
    hw_compile_cb          enum ('Y','/')         not null default 'Y',
    hw_compile_linux       enum ('Y','/')         not null default 'Y',
    hw_compile_type        enum ('S','M','B','/') not null default 'S',
    hw_assistant_need      enum ('Y','/')         not null default '/',
    hw_fullscore_check_num tinyint                not null default 0,
    hw_total_check_num     tinyint                not null default 0,
    hw_add_date            datetime               not null comment '本作业加入论坛的时间',
    primary key (hw_term, hw_cno, hw_id)
) ENGINE = FEDERATED
  CONNECTION = 'mysql://forum_federated:FoRum#feDeRatEd$oCt.2023@10.80.42.245:3306/homework/homework'
    COMMENT = '作业信息表'
  DEFAULT CHARSET = gbk;

/* 学生信息视图 */
drop view if exists student;
create view student as
select `stu_term`,
       `stu_grade`,
       `stu_no`,
       `stu_name`,
       `stu_sex`,
       `stu_password`,
       `stu_class_fname`,
       `stu_class_sname`,
       `stu_userlevel`,
       `stu_enable`,
       `stu_add_date`,
       `stu_cno_1`,
       `stu_cno_1_is_del`,
       `stu_cno_2`,
       `stu_cno_2_is_del`,
       `stu_cno_3`,
       `stu_cno_3_is_del`,
       `stu_is_del`,
       `stu_comment`
from student_raw;

/* 作业信息视图 */
drop view if exists homework;
create view homework
as
select hw_term,
       left(hw_cno, length(hw_cno) - 2) as hw_ccode,
       hw_id,
       hw_week,
       hw_chapter,
       hw_filename,
       hw_description,
       hw_bdate,
       hw_edate,
       hw_add_date
from homework_raw
where hw_chapter not in (89, 99) /* 排除上机作业、额外加分作业，此处可以按需修改 */
group by left(hw_cno, length(hw_cno) - 2), hw_id;

/* 学期 */
drop table if exists term;
create table term
(
    term_no char(11) not null primary key comment '"2022/2023/2"形式的学期表示'
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='学期信息表';

insert into term (term_no)
values ('2023/2024/1');
insert into term (term_no)
values ('2023/2024/2');


/* 课程 */
drop table if exists course;
create table course
(
    course_term  char(11) not null comment '学期(主键+外键)',
    course_code  char(16) not null comment '课程代码',
    course_no    char(16) not null comment '课程序号(主键,目前同济的规则是代码+两位序号)',
    course_fname char(64) not null comment '教务系统中的全名',
    course_sname char(32) not null comment '课程简称',
    course_type  char(1)  not null comment '课程类别(1-基础 2-专业，暂时无用，未来和学校的课程编码匹配)',
    primary key (course_term, course_no),
    foreign key (course_term) references term (term_no) on delete no action on update cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='课程信息表';

insert into course
values ('2023/2024/2', '50002440016', '5000244001601', '高级语言程序设计', '高程01班', '1');
insert into course
values ('2023/2024/2', '50002440016', '5000244001602', '高级语言程序设计', '高程02班', '1');
insert into course
values ('2023/2024/2', '50002440016', '5000244001603', '高级语言程序设计', '高程03班', '1');
insert into course
values ('2023/2024/2', '50002440016', '5000244001604', '高级语言程序设计', '高程04班', '1');
insert into course
values ('2023/2024/2', '50002440016', '5000244001605', '高级语言程序设计', '高程05班', '1');
insert into course
values ('2023/2024/1', '101080', '10108002', '面向对象程序设计', '面向对象(嘉)', '1');
insert into course
values ('2023/2024/1', '100692', '10069201', '高级语言程序设计', '高程(嘉)', '1');
insert into course
values ('2023/2024/1', '50002440016', '5000244001601', '高级语言程序设计', '高程(国豪)', '1');


/* 登录日志 */
drop table if exists log_login;
create table log_login
(
    log_login_id        int auto_increment not null primary key comment '序号(主键,自动增长)',
    log_login_no        char(8)            not null comment '学号',
    log_login_ipaddr    char(15) comment '登录IP',
    log_login_date      datetime comment '登录时间',
    log_login_useragent text comment '登录环境(浏览器的agent)',
    log_login_comment   text comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='用户登录日志表';

/* 写登录日志的过程 */
drop procedure if exists proc_writelog_login;
delimiter //
create procedure proc_writelog_login(in in_sno char(8), in in_ipaddr char(15), in in_useragent text, in_comment text)
begin
    set @sqlcmd =
            concat('insert into log_login values (NULL, ''', in_sno, ''', ''', in_ipaddr, ''', now(), ''', in_useragent,
                   ''', ''', in_comment, ''')');
    prepare stmt from @sqlcmd;
    execute stmt;
    set @sqlcmd = 'bye';
end//
delimiter ;

/* 用户验证的过程 */
drop procedure if exists proc_user_auth;
delimiter //
create procedure proc_user_auth(in in_stu_no char(8), in in_stu_password varchar(64))
begin
    set @sqlcmd =
            concat('select count(*) as enable, stu_term as term, stu_grade as grade, stu_no as sno, stu_name as sname, stu_sex as sex, stu_class_fname as fname, stu_class_sname as sname, ');
    set @sqlcmd = concat(@sqlcmd, 'stu_cno_1 as cno1, stu_cno_2 as cno2, stu_cno_3 as cno3, stu_userlevel as level ');
    set @sqlcmd = concat(@sqlcmd, 'from student ');
    set @sqlcmd = concat(@sqlcmd, 'where stu_no = ''', in_stu_no, ''' and stu_password = ''', in_stu_password,
                         ''' and stu_enable = ''1'' and stu_is_del = ''0''');
    prepare stmt from @sqlcmd;
    execute stmt;
    set @sqlcmd = 'bye';
end//
delimiter ;


/* 已上传的作业 */
drop table if exists homework_uploaded;
create table homework_uploaded
(
    hwup_term        char(11)       not null comment '学期(主键+外键)',
    hwup_ccode       char(16)       not null comment '课程序号(主键,对应course中的course_code 注意:不是外键关系,但要检查是否存在)',
    hwup_id          char(20)       not null comment '文件编号(例: 22232-030101-W0102)',
    hwup_week        tinyint        not null comment '布置周',
    hwup_chapter     tinyint        not null comment '章节(0-20: 第0-20章 90:大作业 98:文档作业 99:其它作业)',
    hwup_filename    char(64)       not null comment '上传的文件名',
    hwup_filemd5     char(32)       not null comment '上传的文件的MD5',
    hwup_description char(64)       not null comment '作业描述',
    hwup_date_add    datetime       not null comment '文件导入本论坛的时间',
    hwup_is_del      enum ('0','1') not null default '0' comment '文件是否已删除(''0'':可正常显示/下载 ''1'':不显示/不提供下载 注意:enum不要当int处理)',
    hwup_comment     text comment '备注',
    primary key (hwup_term, hwup_ccode, hwup_id),
    foreign key (hwup_term) references term (term_no) on delete no action on update cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='作业上传信息表';


/* 上传作业的日志 */
drop table if exists log_homework_uploaded;
create table log_homework_uploaded
(
    log_hwup_id      int auto_increment not null primary key comment '序号(主键,自动增长)',
    log_hwup_opno    char(8)            not null comment '操作人学号',
    log_hwup_ipaddr  char(15) comment '登录IP',
    log_hwup_date    datetime comment '登录时间',
    log_hwup_comment text comment '备注'
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='作业上传日志表';


/* 写上传作业日志的过程 */
drop procedure if exists proc_writelog_homework_uploaded;
delimiter //
create procedure proc_writelog_homework_uploaded(in in_opno char(8), in in_ipaddr char(15), in_comment text)
begin
    set @sqlcmd =
            concat('insert into log_homework_uploaded values (NULL, ''', in_opno, ''', ''', in_ipaddr, ''', now(), ''',
                   in_comment, ''')');
    prepare stmt from @sqlcmd;
    execute stmt;
    set @sqlcmd = 'bye';
end//
delimiter ;

/* 标签名称索引表 */
drop table if exists tag;
create table tag
(
    tag_fieldname char(16) not null primary key comment 'post表中tag的字段名',
    tag_name      char(32) not null comment 'tag的中文解释',
    tag_fgcolor   char(6)  not null comment '对应tar的前景色(FF0000 - RGB方式表示的颜色,每两位表示一个16进制的颜色)',
    tag_bgcolor   char(6)  not null comment '对应tar的背景色(00FF00 - RGB方式表示的颜色,每两位表示一个16进制的颜色)'
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='标签名称索引表';

insert tag
values ('post_tag_01', '正常', 'FF0000', '0000FF');
insert tag
values ('post_tag_02', '置顶', 'FF0000', '0000FF');
insert tag
values ('post_tag_03', '精品', 'FF0000', '0000FF');
insert tag
values ('post_tag_04', '重复', 'FF0000', '0000FF');
insert tag
values ('post_tag_05', '低质', 'FF0000', '0000FF');
insert tag
values ('post_tag_06', '预留', 'FF0000', '0000FF');
insert tag
values ('post_tag_07', '预留', 'FF0000', '0000FF');
insert tag
values ('post_tag_08', '预留', 'FF0000', '0000FF');
insert tag
values ('post_tag_09', '预留', 'FF0000', '0000FF');

/* 发帖信息表 */
drop table if exists post;
create table post
(
    post_id            int primary key auto_increment comment 'ID(主键,自动增长)',
    post_term          char(11)                                                      not null comment '学期(外键)',
    post_ccode         char(16)                                                      not null comment '课程序号(对应course中的course_code 注意:不是外键关系,但要检查是否存在)',
    post_hwup_or_hw_id char(20)                                                      not null comment '对应的上传文件/具体作业的序号\n
                            如果是在第x周的整体问题处发帖,则本字段值为上传文件序号(''22232-000001-W0101'' - 必须在homework_uploaded中存在)\n
                            如果是在第x周的某具体作业处发帖,则本字段值为具体作业序号(''0401'' - 必须在homework中存在)\n
                            如果是在课程的整体问题处发帖(仅管理员及超级用户允许),则本字段值为"学期-G5位序号-W4位周次"(''22232-G00001-W0101'')',
    post_week          tinyint                                                       not null comment '布置周(课程的整体问题则周次为-1)',
    post_chapter       tinyint                                                       not null comment '章节(课程的整体问题则章节为-1)',
    post_answer_id     int comment '对应帖子的id(与post_id是外键关系)\n
                            如果是发帖,则为NULL\n
                            如果是回帖,则为对应帖子的post_id(以此为依据构建发帖回帖的树形结构)',
    post_type          enum ('Question','QuestionsAdditional','Answer','Other', '/') not null default '/' comment '帖子类型(''Question'':首发问题 ''QuestionsAdditional'':追问 ''Answer'':回帖 ''Other'':其它 ''/'':预留)\n
                            以 post_term + post_ccode + post_hwup_or_hw_id 为基准汇聚,具体排序规则?\n
                            本字段是否多余?',
    post_sno           char(8)                                                       not null comment '发帖人学号',
    post_priority      char(1)                                                       not null default '0' comment '优先级(从''0''~''9'' 依次递增,帖子显示是按优先级顺序,相同优先级按发帖时间,可由管理员手工置位进行调整)',
    post_tag_01        enum ('0','1')                                                not null default '0' comment '约定的tag 1标记(0:此标记未置位 1:此标记已置位)',
    post_tag_02        enum ('0','1')                                                not null default '0' comment '约定的tag 2标记(0:此标记未置位 1:此标记已置位)',
    post_tag_03        enum ('0','1')                                                not null default '0' comment '约定的tag 3标记(0:此标记未置位 1:此标记已置位)',
    post_tag_04        enum ('0','1')                                                not null default '0' comment '约定的tag 4标记(0:此标记未置位 1:此标记已置位)',
    post_tag_05        enum ('0','1')                                                not null default '0' comment '约定的tag 5标记(0:此标记未置位 1:此标记已置位)',
    post_tag_06        enum ('0','1')                                                not null default '0' comment '约定的tag 6标记(0:此标记未置位 1:此标记已置位)',
    post_tag_07        enum ('0','1')                                                not null default '0' comment '约定的tag 7标记(0:此标记未置位 1:此标记已置位)',
    post_tag_08        enum ('0','1')                                                not null default '0' comment '约定的tag 8标记(0:此标记未置位 1:此标记已置位)',
    post_tag_09        enum ('0','1')                                                not null default '0' comment '约定的tag 9标记(0:此标记未置位 1:此标记已置位)',
    post_tag_10        enum ('0','1')                                                not null default '0' comment '约定的tag 10标记(0:此标记未置位 1:此标记已置位)',
    post_content       text comment '发帖具体内容(允许贴图,Richtext?)',
    post_date          datetime                                                      not null comment '发帖时间',
    post_is_del        enum ('0','1')                                                not null default '0' comment '帖子是否已删除(''0'':正常显示 ''1'':不显示,包括所有的回帖 注意:enum不要当int处理)',
    post_comment       text comment '备注(预留)',
    foreign key (post_term) references term (term_no) on delete no action on update cascade,
    foreign key (post_answer_id) references post (post_id) on delete no action on update cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='发帖信息表';

CREATE INDEX `post index post_sno` ON `post` (`post_sno`);
CREATE INDEX `post index post_hwup_or_hw_id` ON `post` (`post_hwup_or_hw_id`);

/* 发帖日志表 */
drop table if exists log_post;
create table log_post
(
    log_post_id      int auto_increment not null primary key comment '序号(主键,自动增长)',
    log_post_postid  int comment '帖子id',
    log_post_opno    char(8)            not null comment '操作人学号',
    log_post_ipaddr  char(15) comment '登录IP',
    log_post_date    datetime comment '登录时间',
    log_post_comment text comment '备注（没考虑好怎么更合理）',
    foreign key (log_post_postid) references post (post_id) on delete no action on update cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='发帖日志表';


/* 写发帖日志的过程 */
drop procedure if exists proc_writelog_post;
delimiter //
create procedure proc_writelog_post(in in_postid int, in in_opno char(8), in in_ipaddr char(15), in_comment text)
begin
    set @sqlcmd =
            concat('insert into log_post values (NULL, ''', in_postid, ''', ''', in_opno, ''', ''', in_ipaddr,
                   ''', now(), ''',
                   in_comment, ''')');
    prepare stmt from @sqlcmd;
    execute stmt;
    set @sqlcmd = 'bye';
end//
delimiter ;

/*以下是论坛运行的其他数据*/

/* 其他用户数据表 */
CREATE TABLE `student_info`
(
    `stu_no`   char(8) COLLATE gbk_bin     NOT NULL COMMENT '学生学号',
    `avatar`   varchar(80) COLLATE gbk_bin NOT NULL COMMENT '头像地址',
    `nickname` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '昵称（如有，和实名同时显示）'
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='其他用户数据表';

CREATE INDEX `student_info index stu_no` ON `student_info` (`stu_no`);
