/*
Navicat PGSQL Data Transfer

Source Server         : postgresql
Source Server Version : 90302
Source Host           : localhost:5432
Source Database       : postgres
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90302
File Encoding         : 65001

Date: 2015-03-28 09:52:16
*/


-- ----------------------------
-- Table structure for leave
-- ----------------------------
DROP TABLE IF EXISTS "public"."leave";
CREATE TABLE "public"."leave" (
"id" varchar(64) COLLATE "default" NOT NULL,
"process_instance_id" varchar(64) COLLATE "default",
"user_id" varchar(64) COLLATE "default" NOT NULL,
"start_time" timestamp(6),
"end_time" timestamp(6),
"leave_type" varchar(100) COLLATE "default",
"reason" varchar(1000) COLLATE "default",
"apply_time" timestamp(6),
"reality_start_time" timestamp(6),
"reality_end_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."leave"."id" IS '请假表主键';
COMMENT ON COLUMN "public"."leave"."process_instance_id" IS '流程id';
COMMENT ON COLUMN "public"."leave"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."leave"."start_time" IS '开始时间';
COMMENT ON COLUMN "public"."leave"."end_time" IS '结束时间';
COMMENT ON COLUMN "public"."leave"."leave_type" IS '请假类型';
COMMENT ON COLUMN "public"."leave"."reason" IS '请假理由';
COMMENT ON COLUMN "public"."leave"."apply_time" IS '申请时间';
COMMENT ON COLUMN "public"."leave"."reality_start_time" IS '实际请假开始时间';
COMMENT ON COLUMN "public"."leave"."reality_end_time" IS '实际请假结束时间';

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table leave
-- ----------------------------
ALTER TABLE "public"."leave" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Init Data
-- ----------------------------
INSERT INTO "act_id_user" VALUES ('491983787@qq.com', 1, 'li', 'xiaoliang', '491983787@qq.com', '123456', NULL);
INSERT INTO "act_id_user" VALUES ('826205392@qq.com', 1, 'liu', 'ya', '826205392@qq.com', '123456', NULL);
INSERT INTO "act_id_user" VALUES ('comeonhd@sina.com', 1, 'he', 'dong', 'comeonhd@sina.com', '123456', NULL);

INSERT INTO "act_id_group" VALUES ('1', 1, 'deptLeader', NULL);
INSERT INTO "act_id_group" VALUES ('2', 1, 'personnel', NULL);
INSERT INTO "act_id_group" VALUES ('3', 1, 'employee', NULL);

INSERT INTO "act_id_membership" VALUES ('491983787@qq.com', '2');
INSERT INTO "act_id_membership" VALUES ('826205392@qq.com', '3');
INSERT INTO "act_id_membership" VALUES ('comeonhd@sina.com', '1');