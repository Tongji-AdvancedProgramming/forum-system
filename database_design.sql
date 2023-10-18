/* �����ַ��� */
set names gbk;

/* ���ݿ����Ƹ�Ϊ forum */
drop database if exists forum_dev;
create database forum_dev;
use forum_dev;


/* =================== homework ���ݿ� =================== */

/* ����һ���ı�(���ƿ��Բ�ͬ) */
drop table if exists student_raw;
create table student_raw
(
    stu_term                  char(11)               not null comment 'ѧ��(��term���������ϵ)',
    stu_grade                 char(4)                not null comment '�꼶',
    stu_no                    char(8)                not null primary key comment 'ѧ��(����)',
    stu_name                  char(32)               not null comment '����',
    stu_sex                   enum ('��','Ů','/')   not null default '/' comment '�Ա�',
    stu_password              char(32)               not null comment '����(md5)',
    stu_class_fname           char(32)               not null comment 'רҵ/�༶ȫ��',
    stu_class_sname           char(16)               not null comment 'רҵ/�༶���',
    stu_wtype                 enum ('0','1')         not null default '0',
    stu_userlevel             char(1)                not null default '0' comment 'ѧ���û��ȼ�(0:��ͨ�û� 1:���� 5:����Ա 9:�����û�)',
    stu_enable                enum ('0','1')         not null default '1' comment '�˺��Ƿ�����(''0'':��ֹ��¼ ''1'':�����¼ ע��:enum��Ҫ��int����)',
    stu_add_date              datetime               not null comment 'ϵͳע��ʱ��',
    stu_cno_1                 char(16) comment 'ѧ��ѡ�޵Ŀγ�1�Ŀκ�(��course���������ϵ)',
    stu_cno_1_is_special      enum ('0','1')         not null default '0',
    stu_cno_1_is_del          enum ('0','1')         not null default '0' comment 'ѧ��ѡ�޵Ŀγ�1�Ƿ��˿�(''0'':���� ''1'':���˿� ע��:enum��Ҫ��int����,�˿�ѧ����Ӧ�γ̵���ҵ��Ϣ��Ҫ��ʾ����)',
    stu_cno_1_lastop_date     datetime,
    stu_cno_1_lab             char(16),
    stu_cno_1_lab_is_del      enum ('0','1')         not null default '0',
    stu_cno_1_lab_lastop_date datetime,
    stu_cno_1_flag            enum ('B','M','L','/') not null default '/',
    stu_cno_2                 char(16) comment 'ѧ��ѡ�޵Ŀγ�2�Ŀκ�(��course���������ϵ)',
    stu_cno_2_is_special      enum ('0','1')         not null default '0',
    stu_cno_2_is_del          enum ('0','1')         not null default '0' comment 'ѧ��ѡ�޵Ŀγ�2�Ƿ��˿�(''0'':���� ''1'':���˿� ע��:enum��Ҫ��int����,�˿�ѧ����Ӧ�γ̵���ҵ��Ϣ��Ҫ��ʾ����)',
    stu_cno_2_lastop_date     datetime,
    stu_cno_2_lab             char(16),
    stu_cno_2_lab_is_del      enum ('0','1')         not null default '0',
    stu_cno_2_lab_lastop_date datetime,
    stu_cno_2_flag            enum ('B','M','L','/') not null default '/',
    stu_cno_3                 char(16) comment 'ѧ��ѡ�޵Ŀγ�3�Ŀκ�(��course���������ϵ)',
    stu_cno_3_is_special      enum ('0','1')         not null default '0',
    stu_cno_3_is_del          enum ('0','1')         not null default '0' comment 'ѧ��ѡ�޵Ŀγ�3�Ƿ��˿�(''0'':���� ''1'':���˿� ע��:enum��Ҫ��int����,�˿�ѧ����Ӧ�γ̵���ҵ��Ϣ��Ҫ��ʾ����)',
    stu_cno_3_lastop_date     datetime,
    stu_cno_3_lab             char(16),
    stu_cno_3_lab_is_del      enum ('0','1')         not null default '0',
    stu_cno_3_lab_lastop_date datetime,
    stu_cno_3_flag            enum ('B','M','L','/') not null default '/',
    stu_is_del                enum ('0','1')         not null default '0' comment '��ѧ���Ƿ�ɾ��(''0'':���� ''1'':��ɾ�� ע��:��ɾ��������stu_enbale�ú�ֵ���������¼)',
    stu_comment               text comment '��ע��Ϣ'
) ENGINE = FEDERATED
  CONNECTION = 'mysql://forum_federated:FoRum#feDeRatEd$oCt.2023@10.80.42.245:3306/homework/student'
  DEFAULT CHARSET = gbk;

drop table if exists homework_raw;
create table homework_raw
(
    hw_term                char(11)               not null comment 'ѧ��(����+���)',
    hw_cno                 char(16)               not null,
    hw_id                  smallint               not null comment '��ҵ���(����)',
    hw_week                tinyint                not null comment '������',
    hw_chapter             tinyint                not null comment '�½�(0-20: ��0-20�� 90:����ҵ 98:�ĵ���ҵ 99:������ҵ)',
    hw_filename            char(64)               not null comment '����ҵ��վ���ύ�ļ���',
    hw_description         char(64)               not null comment '��ҵ����',
    hw_type                char(1)                not null default '0',
    hw_filesize_min        int                    not null default 0,
    hw_filesize_max        int                    not null default 65536,
    hw_bdate               datetime               not null comment '��ҵ�ύ��ʼʱ��',
    hw_edate               datetime               not null comment '��ҵ�ύ����ʱ��',
    hw_decrease            smallint               not null default 5,
    hw_score               decimal(5, 1)          not null default 0 comment '����ҵ����',
    hw_compile_vs          enum ('Y','/')         not null default 'Y',
    hw_compile_dev         enum ('Y','/')         not null default 'Y',
    hw_compile_cb          enum ('Y','/')         not null default 'Y',
    hw_compile_linux       enum ('Y','/')         not null default 'Y',
    hw_compile_type        enum ('S','M','B','/') not null default 'S',
    hw_assistant_need      enum ('Y','/')         not null default '/',
    hw_fullscore_check_num tinyint                not null default 0,
    hw_total_check_num     tinyint                not null default 0,
    hw_add_date            datetime               not null comment '����ҵ������̳��ʱ��',
    primary key (hw_term, hw_cno, hw_id)
) ENGINE = FEDERATED
  CONNECTION = 'mysql://forum_federated:FoRum#feDeRatEd$oCt.2023@10.80.42.245:3306/homework/homework'
    COMMENT = '��ҵ��Ϣ��'
  DEFAULT CHARSET = gbk;

/* ѧ����Ϣ��ͼ */
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

/* ��ҵ��Ϣ��ͼ */
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
where hw_chapter not in (89, 99) /* �ų��ϻ���ҵ������ӷ���ҵ���˴����԰����޸� */
group by left(hw_cno, length(hw_cno) - 2), hw_id;

/* ѧ�� */
drop table if exists term;
create table term
(
    term_no char(11) not null primary key comment '"2022/2023/2"��ʽ��ѧ�ڱ�ʾ'
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='ѧ����Ϣ��';

insert into term (term_no)
values ('2023/2024/1');
insert into term (term_no)
values ('2023/2024/2');


/* �γ� */
drop table if exists course;
create table course
(
    course_term  char(11) not null comment 'ѧ��(����+���)',
    course_code  char(16) not null comment '�γ̴���',
    course_no    char(16) not null comment '�γ����(����,Ŀǰͬ�õĹ����Ǵ���+��λ���)',
    course_fname char(64) not null comment '����ϵͳ�е�ȫ��',
    course_sname char(32) not null comment '�γ̼��',
    course_type  char(1)  not null comment '�γ����(1-���� 2-רҵ����ʱ���ã�δ����ѧУ�Ŀγ̱���ƥ��)',
    primary key (course_term, course_no),
    foreign key (course_term) references term (term_no) on delete no action on update cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='�γ���Ϣ��';

insert into course
values ('2023/2024/2', '50002440016', '5000244001601', '�߼����Գ������', '�߳�01��', '1');
insert into course
values ('2023/2024/2', '50002440016', '5000244001602', '�߼����Գ������', '�߳�02��', '1');
insert into course
values ('2023/2024/2', '50002440016', '5000244001603', '�߼����Գ������', '�߳�03��', '1');
insert into course
values ('2023/2024/2', '50002440016', '5000244001604', '�߼����Գ������', '�߳�04��', '1');
insert into course
values ('2023/2024/2', '50002440016', '5000244001605', '�߼����Գ������', '�߳�05��', '1');
insert into course
values ('2023/2024/1', '101080', '10108002', '�������������', '�������(��)', '1');
insert into course
values ('2023/2024/1', '100692', '10069201', '�߼����Գ������', '�߳�(��)', '1');
insert into course
values ('2023/2024/1', '50002440016', '5000244001601', '�߼����Գ������', '�߳�(����)', '1');


/* ��¼��־ */
drop table if exists log_login;
create table log_login
(
    log_login_id        int auto_increment not null primary key comment '���(����,�Զ�����)',
    log_login_no        char(8)            not null comment 'ѧ��',
    log_login_ipaddr    char(15) comment '��¼IP',
    log_login_date      datetime comment '��¼ʱ��',
    log_login_useragent text comment '��¼����(�������agent)',
    log_login_comment   text comment '��ע'
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='�û���¼��־��';

/* д��¼��־�Ĺ��� */
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

/* �û���֤�Ĺ��� */
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


/* ���ϴ�����ҵ */
drop table if exists homework_uploaded;
create table homework_uploaded
(
    hwup_term        char(11)       not null comment 'ѧ��(����+���)',
    hwup_ccode       char(16)       not null comment '�γ����(����,��Ӧcourse�е�course_code ע��:���������ϵ,��Ҫ����Ƿ����)',
    hwup_id          char(20)       not null comment '�ļ����(��: 22232-030101-W0102)',
    hwup_week        tinyint        not null comment '������',
    hwup_chapter     tinyint        not null comment '�½�(0-20: ��0-20�� 90:����ҵ 98:�ĵ���ҵ 99:������ҵ)',
    hwup_filename    char(64)       not null comment '�ϴ����ļ���',
    hwup_filemd5     char(32)       not null comment '�ϴ����ļ���MD5',
    hwup_description char(64)       not null comment '��ҵ����',
    hwup_date_add    datetime       not null comment '�ļ����뱾��̳��ʱ��',
    hwup_is_del      enum ('0','1') not null default '0' comment '�ļ��Ƿ���ɾ��(''0'':��������ʾ/���� ''1'':����ʾ/���ṩ���� ע��:enum��Ҫ��int����)',
    hwup_comment     text comment '��ע',
    primary key (hwup_term, hwup_ccode, hwup_id),
    foreign key (hwup_term) references term (term_no) on delete no action on update cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='��ҵ�ϴ���Ϣ��';


/* �ϴ���ҵ����־ */
drop table if exists log_homework_uploaded;
create table log_homework_uploaded
(
    log_hwup_id      int auto_increment not null primary key comment '���(����,�Զ�����)',
    log_hwup_opno    char(8)            not null comment '������ѧ��',
    log_hwup_ipaddr  char(15) comment '��¼IP',
    log_hwup_date    datetime comment '��¼ʱ��',
    log_hwup_comment text comment '��ע'
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='��ҵ�ϴ���־��';


/* д�ϴ���ҵ��־�Ĺ��� */
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

/* ��ǩ���������� */
drop table if exists tag;
create table tag
(
    tag_fieldname char(16) not null primary key comment 'post����tag���ֶ���',
    tag_name      char(32) not null comment 'tag�����Ľ���',
    tag_fgcolor   char(6)  not null comment '��Ӧtar��ǰ��ɫ(FF0000 - RGB��ʽ��ʾ����ɫ,ÿ��λ��ʾһ��16���Ƶ���ɫ)',
    tag_bgcolor   char(6)  not null comment '��Ӧtar�ı���ɫ(00FF00 - RGB��ʽ��ʾ����ɫ,ÿ��λ��ʾһ��16���Ƶ���ɫ)'
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='��ǩ����������';

insert tag
values ('post_tag_01', '����', 'FF0000', '0000FF');
insert tag
values ('post_tag_02', '�ö�', 'FF0000', '0000FF');
insert tag
values ('post_tag_03', '��Ʒ', 'FF0000', '0000FF');
insert tag
values ('post_tag_04', '�ظ�', 'FF0000', '0000FF');
insert tag
values ('post_tag_05', '����', 'FF0000', '0000FF');
insert tag
values ('post_tag_06', 'Ԥ��', 'FF0000', '0000FF');
insert tag
values ('post_tag_07', 'Ԥ��', 'FF0000', '0000FF');
insert tag
values ('post_tag_08', 'Ԥ��', 'FF0000', '0000FF');
insert tag
values ('post_tag_09', 'Ԥ��', 'FF0000', '0000FF');

/* ������Ϣ�� */
drop table if exists post;
create table post
(
    post_id            int primary key auto_increment comment 'ID(����,�Զ�����)',
    post_term          char(11)                                                      not null comment 'ѧ��(���)',
    post_ccode         char(16)                                                      not null comment '�γ����(��Ӧcourse�е�course_code ע��:���������ϵ,��Ҫ����Ƿ����)',
    post_hwup_or_hw_id char(20)                                                      not null comment '��Ӧ���ϴ��ļ�/������ҵ�����\n
                            ������ڵ�x�ܵ��������⴦����,���ֶ�ֵΪ�ϴ��ļ����(''22232-000001-W0101'' - ������homework_uploaded�д���)\n
                            ������ڵ�x�ܵ�ĳ������ҵ������,���ֶ�ֵΪ������ҵ���(''0401'' - ������homework�д���)\n
                            ������ڿγ̵��������⴦����(������Ա�������û�����),���ֶ�ֵΪ"ѧ��-G5λ���-W4λ�ܴ�"(''22232-G00001-W0101'')',
    post_week          tinyint                                                       not null comment '������(�γ̵������������ܴ�Ϊ-1)',
    post_chapter       tinyint                                                       not null comment '�½�(�γ̵������������½�Ϊ-1)',
    post_answer_id     int comment '��Ӧ���ӵ�id(��post_id�������ϵ)\n
                            ����Ƿ���,��ΪNULL\n
                            ����ǻ���,��Ϊ��Ӧ���ӵ�post_id(�Դ�Ϊ���ݹ����������������νṹ)',
    post_type          enum ('Question','QuestionsAdditional','Answer','Other', '/') not null default '/' comment '��������(''Question'':�׷����� ''QuestionsAdditional'':׷�� ''Answer'':���� ''Other'':���� ''/'':Ԥ��)\n
                            �� post_term + post_ccode + post_hwup_or_hw_id Ϊ��׼���,�����������?\n
                            ���ֶ��Ƿ����?',
    post_sno           char(8)                                                       not null comment '������ѧ��',
    post_priority      char(1)                                                       not null default '0' comment '���ȼ�(��''0''~''9'' ���ε���,������ʾ�ǰ����ȼ�˳��,��ͬ���ȼ�������ʱ��,���ɹ���Ա�ֹ���λ���е���)',
    post_tag_01        enum ('0','1')                                                not null default '0' comment 'Լ����tag 1���(0:�˱��δ��λ 1:�˱������λ)',
    post_tag_02        enum ('0','1')                                                not null default '0' comment 'Լ����tag 2���(0:�˱��δ��λ 1:�˱������λ)',
    post_tag_03        enum ('0','1')                                                not null default '0' comment 'Լ����tag 3���(0:�˱��δ��λ 1:�˱������λ)',
    post_tag_04        enum ('0','1')                                                not null default '0' comment 'Լ����tag 4���(0:�˱��δ��λ 1:�˱������λ)',
    post_tag_05        enum ('0','1')                                                not null default '0' comment 'Լ����tag 5���(0:�˱��δ��λ 1:�˱������λ)',
    post_tag_06        enum ('0','1')                                                not null default '0' comment 'Լ����tag 6���(0:�˱��δ��λ 1:�˱������λ)',
    post_tag_07        enum ('0','1')                                                not null default '0' comment 'Լ����tag 7���(0:�˱��δ��λ 1:�˱������λ)',
    post_tag_08        enum ('0','1')                                                not null default '0' comment 'Լ����tag 8���(0:�˱��δ��λ 1:�˱������λ)',
    post_tag_09        enum ('0','1')                                                not null default '0' comment 'Լ����tag 9���(0:�˱��δ��λ 1:�˱������λ)',
    post_tag_10        enum ('0','1')                                                not null default '0' comment 'Լ����tag 10���(0:�˱��δ��λ 1:�˱������λ)',
    post_content       text comment '������������(������ͼ,Richtext?)',
    post_date          datetime                                                      not null comment '����ʱ��',
    post_is_del        enum ('0','1')                                                not null default '0' comment '�����Ƿ���ɾ��(''0'':������ʾ ''1'':����ʾ,�������еĻ��� ע��:enum��Ҫ��int����)',
    post_comment       text comment '��ע(Ԥ��)',
    foreign key (post_term) references term (term_no) on delete no action on update cascade,
    foreign key (post_answer_id) references post (post_id) on delete no action on update cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='������Ϣ��';

CREATE INDEX `post index post_sno` ON `post` (`post_sno`);
CREATE INDEX `post index post_hwup_or_hw_id` ON `post` (`post_hwup_or_hw_id`);

/* ������־�� */
drop table if exists log_post;
create table log_post
(
    log_post_id      int auto_increment not null primary key comment '���(����,�Զ�����)',
    log_post_postid  int comment '����id',
    log_post_opno    char(8)            not null comment '������ѧ��',
    log_post_ipaddr  char(15) comment '��¼IP',
    log_post_date    datetime comment '��¼ʱ��',
    log_post_comment text comment '��ע��û���Ǻ���ô������',
    foreign key (log_post_postid) references post (post_id) on delete no action on update cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='������־��';


/* д������־�Ĺ��� */
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

/*��������̳���е���������*/

/* �����û����ݱ� */
CREATE TABLE `student_info`
(
    `stu_no`   char(8) COLLATE gbk_bin     NOT NULL COMMENT 'ѧ��ѧ��',
    `avatar`   varchar(80) COLLATE gbk_bin NOT NULL COMMENT 'ͷ���ַ',
    `nickname` varchar(20) COLLATE gbk_bin NOT NULL COMMENT '�ǳƣ����У���ʵ��ͬʱ��ʾ��'
) ENGINE = InnoDB
  DEFAULT CHARSET = gbk
  COLLATE = gbk_bin COMMENT ='�����û����ݱ�';

CREATE INDEX `student_info index stu_no` ON `student_info` (`stu_no`);
