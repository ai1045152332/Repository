CREATE DATABASE IF NOT EXISTS dmanager DEFAULT  CHARACTER SET utf8 COLLATE utf8_general_ci;
USE dmanager;
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `applog`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `applog` (
  `log_id` VARCHAR(50) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_msgid` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_destip` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_destserialno` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_request` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_requesttime` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_response` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_responsetime` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_result` VARCHAR(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  KEY `user_id` (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of applog
-- ----------------------------

-- ----------------------------
-- Table structure for `classtime`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `classtime` (
  `classtime_id` INT(11) NOT NULL AUTO_INCREMENT,
  `week_id` INT(11) DEFAULT NULL COMMENT '星期几： 1-星期一 ，2-星期二，3-星期三，4-星期四，5-星期五，6-星期六，7-星期日',
  `classtime_section` INT(11) DEFAULT NULL COMMENT '第几节',
  `classtime_start` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上课开始时间',
  `classtime_end` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上课结束时间',
  `classtime_ploy` INT(11) DEFAULT NULL COMMENT '对应时间策略',
  PRIMARY KEY (`classtime_id`),
  KEY `ploy_id` (`classtime_ploy`) USING BTREE,
  CONSTRAINT `ploy_id` FOREIGN KEY (`classtime_ploy`) REFERENCES `classtime_ploy` (`ploy_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for `classtimeploy2group`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `classtimeploy2group` (
  `p2g_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `ploy_id` INT(11) DEFAULT NULL COMMENT '时间策略id',
  `group_id` INT(11) DEFAULT NULL COMMENT '班级分组id',
  PRIMARY KEY (`p2g_id`),
  KEY `ploy` (`ploy_id`),
  CONSTRAINT `ploy` FOREIGN KEY (`ploy_id`) REFERENCES `classtime_ploy` (`ploy_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classtimeploy2group
-- ----------------------------

-- ----------------------------
-- Table structure for `classtime_ploy`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `classtime_ploy` (
  `ploy_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '时间策略id，自增长',
  `ploy_name` VARCHAR(255) DEFAULT NULL COMMENT '时间策略名称',
  PRIMARY KEY (`ploy_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `command`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `command` (
  `cmd_id` INT(11) NOT NULL AUTO_INCREMENT,
  `cmd_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cmd_group` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cmd_image` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cmd_default` BIT(1) DEFAULT b'0',
  `cmd_flag` VARCHAR(2) COLLATE utf8_unicode_ci DEFAULT '10' COMMENT '命令标识：10-host;01-device;11-both;',
  `cmd_hex` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`cmd_id`)
) ENGINE=INNODB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of command
-- ----------------------------
INSERT INTO `command` VALUES ('1', '开机', '电源管理', NULL, '', '10', '0x001001');
INSERT INTO `command` VALUES ('2', '关机', '电源管理', NULL, '', '10', '0x001002');
INSERT INTO `command` VALUES ('3', '复位', '电源管理', NULL, '', '10', '0x001003');
INSERT INTO `command` VALUES ('4', '预置点设置', 'PTZ控制', NULL, '', '10', '0x002001');
INSERT INTO `command` VALUES ('5', '预置点定位', 'PTZ控制', NULL, '', '10', '0x002002');
INSERT INTO `command` VALUES ('6', '水平转动', 'PTZ控制', NULL, '', '10', '0x002003');
INSERT INTO `command` VALUES ('7', '垂直转动', 'PTZ控制', NULL, '', '10', '0x002004');
INSERT INTO `command` VALUES ('8', '巡航', 'PTZ控制', NULL, '', '10', '0x002005');
INSERT INTO `command` VALUES ('9', '辅助灯光控制', 'PTZ控制', NULL, '', '10', '0x002006');
INSERT INTO `command` VALUES ('10', '镜头聚焦', '镜头控制', NULL, '', '10', '0x003001');
INSERT INTO `command` VALUES ('11', '镜头变倍', '镜头控制', NULL, '', '10', '0x003002');
INSERT INTO `command` VALUES ('12', '光圈设置', '镜头控制', NULL, '', '10', '0x003003');
INSERT INTO `command` VALUES ('13', '创建直播', '直播管理', NULL, '', '10', '0x004001');
INSERT INTO `command` VALUES ('14', '删除直播', '直播管理', NULL, '', '10', '0x004002');
INSERT INTO `command` VALUES ('15', '修改直播', '直播管理', NULL, '', '10', '0x004003');
INSERT INTO `command` VALUES ('16', '开始直播', '直播管理', NULL, '', '10', '0x004004');
INSERT INTO `command` VALUES ('17', '暂停直播', '直播管理', NULL, '', '10', '0x004005');
INSERT INTO `command` VALUES ('18', '停止直播', '直播管理', NULL, '', '10', '0x004006');
INSERT INTO `command` VALUES ('19', '创建录播', '录播管理', NULL, '', '10', '0x005001');
INSERT INTO `command` VALUES ('20', '删除录播', '录播管理', NULL, '', '10', '0x005002');
INSERT INTO `command` VALUES ('21', '修改录播', '录播管理', NULL, '', '10', '0x005003');
INSERT INTO `command` VALUES ('22', '开始录播', '录播管理', NULL, '', '10', '0x005004');
INSERT INTO `command` VALUES ('23', '暂停录播', '录播管理', NULL, '', '10', '0x005005');
INSERT INTO `command` VALUES ('24', '停止录播', '录播管理', NULL, '', '10', '0x005006');
INSERT INTO `command` VALUES ('25', '声音+', '音量调节', '', '', '10', '0x014001');
INSERT INTO `command` VALUES ('26', '声音-', '音量调节', '', '', '10', '0x014002');
INSERT INTO `command` VALUES ('27', '模拟电视ATV', '信号源切换', '', '', '10', '0x012001');
INSERT INTO `command` VALUES ('28', '数字电视DTV', '信号源切换', '', '', '10', '0x012002');
INSERT INTO `command` VALUES ('29', '视频1', '信号源切换', '', '', '10', '0x012003');
INSERT INTO `command` VALUES ('30', '视频2', '信号源切换', '', '', '10', '0x012004');
INSERT INTO `command` VALUES ('31', 'S-端子', '信号源切换', '', '', '10', '0x012005');
INSERT INTO `command` VALUES ('32', '分量', '信号源切换', '', '', '10', '0x012006');
INSERT INTO `command` VALUES ('33', 'HDMI1', '信号源切换', '', '', '10', '0x012007');
INSERT INTO `command` VALUES ('34', 'HDMI2', '信号源切换', '', '', '10', '0x012008');
INSERT INTO `command` VALUES ('35', '电脑1', '信号源切换', '', '', '10', '0x012009');
INSERT INTO `command` VALUES ('36', '电脑2', '信号源切换', '', '', '10', '0x012010');
INSERT INTO `command` VALUES ('37', '内置电脑', '信号源切换', '', '', '10', '0x012011');
INSERT INTO `command` VALUES ('38', '电脑VGA', '信号源切换', '', '', '10', '0x012012');
INSERT INTO `command` VALUES ('39', '室内', '音响模式调节', '', '', '10', '0x013001');
INSERT INTO `command` VALUES ('40', '室外', '音响模式调节', '', '', '10', '0x013002');
INSERT INTO `command` VALUES ('41', '混合', '音响模式调节', '', '', '10', '0x013003');
INSERT INTO `command` VALUES ('43', '启用', '触摸功能', '', '', '10', '0x015001');
INSERT INTO `command` VALUES ('44', '禁用', '触摸功能', '', '', '10', '0x015002');
INSERT INTO `command` VALUES ('45', '启用', '远程节能', '', '', '10', '0x016001');
INSERT INTO `command` VALUES ('46', '禁用', '远程节能', '', '', '10', '0x016002');
INSERT INTO `command` VALUES ('47', '重启', '电源管理', '', '', '10', '0x001003');
INSERT INTO `command` VALUES ('48', '其他', '电源管理', '', '', '10', '0x011004');
INSERT INTO `command` VALUES ('49', '临场2', '音响模式调节', NULL, '', '10', '0x013010');
INSERT INTO `command` VALUES ('50', '临场1', '音响模式调节', NULL, '', '10', '0x013009');
INSERT INTO `command` VALUES ('51', '用户', '音响模式调节', NULL, '', '10', '0x013008');
INSERT INTO `command` VALUES ('52', '新闻', '音响模式调节', NULL, '', '10', '0x013007');
INSERT INTO `command` VALUES ('53', '运动', '音响模式调节', NULL, '', '10', '0x013006');
INSERT INTO `command` VALUES ('54', '音乐', '音响模式调节', NULL, '', '10', '0x013005');
INSERT INTO `command` VALUES ('55', '标准', '音响模式调节', NULL, '', '10', '0x013004');
INSERT INTO `command` VALUES ('56', '标准', '远程节能', NULL, '', '10', '0x016003');
INSERT INTO `command` VALUES ('57', '节能', '远程节能', NULL, '', '10', '0x016004');
INSERT INTO `command` VALUES ('58', '自动', '远程节能', NULL, '', '10', '0x016005');
INSERT INTO `command` VALUES ('59', 'android模式', '触摸功能', NULL, '', '10', '0x015004');
INSERT INTO `command` VALUES ('60', '电脑模式', '触摸功能', NULL, '', '10', '0x015003');
INSERT INTO `command` VALUES ('64', '主页', '信号源切换', NULL, '', '10', '0x012016');
INSERT INTO `command` VALUES ('65', 'HDMI3', '信号源切换', NULL, '', '10', '0x012017');
INSERT INTO `command` VALUES ('66', '影院', '音响模式调节', NULL, '', '10', '0x013011');
INSERT INTO `command` VALUES ('67', '电脑3', '信号源切换', NULL, '', '10', '0x012018');
INSERT INTO `command` VALUES ('68', '多媒体', '信号源切换', NULL, '', '10', '0x012019');
INSERT INTO `command` VALUES ('70', '视屏', '信号源切换', NULL, '', '10', '0x012021');
INSERT INTO `command` VALUES ('71', '电影', '音响模式调节', NULL, '', '10', '0x013012');
INSERT INTO `command` VALUES ('72', '解锁', '触摸功能', NULL, '', '10', '0x015006');
INSERT INTO `command` VALUES ('73', '锁定', '触摸功能', NULL, '', '10', '0x015005');
INSERT INTO `command` VALUES ('74', '会议', '音响模式调节', NULL, '', '10', '0x013014');
INSERT INTO `command` VALUES ('75', '教室', '音响模式调节', NULL, '', '10', '0x013013');
INSERT INTO `command` VALUES ('76', '开机', '投影仪开关', NULL, '', '10', '0x017001');
INSERT INTO `command` VALUES ('77', '关机', '投影仪开关', NULL, '', '10', '0x017002');

-- ----------------------------
-- Table structure for `commandgroup`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `commandgroup` (
  `code_group` VARCHAR(20) DEFAULT NULL,
  `cmd_group` VARCHAR(255) DEFAULT NULL
) ENGINE=INNODB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of commandgroup
-- ----------------------------
-- ----------------------------
-- Table structure for `command_code`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `command_code` (
  `code_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '命令编码id',
  `code_name` VARCHAR(26) COLLATE utf8_unicode_ci NOT NULL COMMENT '命令编码',
  `code_type` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对应的通用命令id',
  `code_result` VARCHAR(25) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '命令返回码',
  `code_remark` VARCHAR(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '命令编码备注',
  `code_instruction` VARCHAR(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '命令编码说明',
  `dspec_id` INT(11) DEFAULT '6' COMMENT '设备类型',
  `code_code` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '通信接口解释标准码',
  `code_flag` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '命令返回数值',
  `code_group` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '命令所属组',
  PRIMARY KEY (`code_id`),
  KEY `desp_code` (`dspec_id`),
  CONSTRAINT `command_code_ibfk_1` FOREIGN KEY (`dspec_id`) REFERENCES `dspecification` (`dspec_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=333 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of command_code
-- ----------------------------
INSERT INTO `command_code` VALUES ('1', '7F0899A2B3C402ff0100CF', '0x001001', '7E0999A2B3C402ff010001CF', '（00 ～2F）无返回参数,TV收到串口命令后将返回码写入串口，返回给上位机', '电源管理：01：开机', '6', 'Boot', NULL, NULL);
INSERT INTO `command_code` VALUES ('2', '7F0899A2B3C402ff0101CF', '0x001002', '7F0999A2B3C402ff010101CF', '（00 ～2F）无返回参数,TV收到串口命令后将返回码写入串口，返回给上位机', '电源管理：关机', '6', 'ShutDown', NULL, NULL);
INSERT INTO `command_code` VALUES ('3', '7F0899A2B3C402ff0102CF', NULL, '7F0999A2B3C402ff010201CF', '静音', NULL, '6', 'Mute', NULL, NULL);
INSERT INTO `command_code` VALUES ('4', '7F0899A2B3C402ff0105CF', NULL, '7F0999A2B3C402ff010501CF', '音响模式（设置）XX=00：标准', NULL, '6', 'AudioMode', NULL, NULL);
INSERT INTO `command_code` VALUES ('5', '7F0899A2B3C402ff0103CF', NULL, '7F0999A2B3C402ff010301CF', '触控状态', NULL, '6', 'Touch', NULL, NULL);
INSERT INTO `command_code` VALUES ('6', '7F0899A2B3C402ff0104CF', NULL, '7F0999A2B3C402ff010401CF', 'WIFI', NULL, '6', '', NULL, NULL);
INSERT INTO `command_code` VALUES ('7', '7F0899A2B3C402ff0106CF', NULL, '7F0999A2B3C402ff010601CF', '信号源', NULL, '6', '', NULL, NULL);
INSERT INTO `command_code` VALUES ('8', '7F0899A2B3C402ff0109CF', NULL, '7F0999A2B3C402ff010901CF', '显示状态', NULL, '6', '', NULL, NULL);
INSERT INTO `command_code` VALUES ('9', '7F0899A2B3C402ff010ACF', '0x012007', '7F0999A2B3C402ff010A01CF', '17:高清1(HDMI1)', '信号源切换：XX=17:高清1(HDMI1)', '6', 'HDMI1', '17', 'Channel');
INSERT INTO `command_code` VALUES ('10', '7F0899A2B3C402ff010BCF', '0x012008', '7F0999A2B3C402ff010B01CF', '18:高清2(HDMI2)', '信号源切换：XX=18:高清2(HDMI2)', '6', 'HDMI2', '18', 'Channel');
INSERT INTO `command_code` VALUES ('11', '7F0899A2B3C402ff010CCF', '0x012011', '7F0999A2B3C402ff010C01CF', '19:OPS电脑(内置电脑)', '信号源切换：XX=19:OPS电脑(内置电脑)', '6', 'OPS', '19', 'Channel');
INSERT INTO `command_code` VALUES ('12', '7F0899A2B3C402ff010DCF', '0x012009', '7F0999A2B3C402ff010D01CF', '00:VGA 电脑1', '信号源切换：XX=00:VGA电脑1', '6', 'VGA1', '00', 'Channel');
INSERT INTO `command_code` VALUES ('13', '7F0899A2B3C402ff010ECF', '0x012010', '7F0999A2B3C402ff010E01CF', '11: 电脑2 ', '信号源切换：XX=11:电脑2 ', '6', 'VGA2', '11', 'Channel');
INSERT INTO `command_code` VALUES ('14', '7F0899A2B3C402ff0111CF', '0x012003', '7F0999A2B3C402ff011101CF', '02：视频1', '信号源切换：XX=02:视频1', '6', 'VIDEO1', '02', 'Channel');
INSERT INTO `command_code` VALUES ('15', '7F0899A2B3C402ff0112CF', '0x012004', '7F0999A2B3C402ff011201CF', '03：视频2', '信号源切换：XX=03:视频2', '6', 'VIDEO2', '03', 'Channel');
INSERT INTO `command_code` VALUES ('16', '7F0899A2B3C402ff0110CF', '0x012006', '7F0999A2B3C402ff011001CF', '10:分量', '信号源切换：XX=10:分量', '6', 'Weight', '10', 'Channel');
INSERT INTO `command_code` VALUES ('17', '7F0899A2B3C402ff0118CF', '0x014001', '7F0999A2B3C402ff011801CF', '声音+', NULL, '6', 'Volume+', NULL, NULL);
INSERT INTO `command_code` VALUES ('18', '7F0899A2B3C402ff0117CF', '0x014002', '7F0999A2B3C402ff011701CF', '声音-', NULL, '6', 'Volume-', NULL, NULL);
INSERT INTO `command_code` VALUES ('19', '7F0899A2B3C402ff011FCF', NULL, '7F0999A2B3C402ff011F01CF', '截屏', NULL, '6', 'ScreenShot', NULL, NULL);
INSERT INTO `command_code` VALUES ('20', '7F0899A2B3C402ff0131CF', NULL, '7F0999A2B3C402ff0131XXCF', '设备型号', NULL, '6', 'Model', NULL, NULL);
INSERT INTO `command_code` VALUES ('21', '7F0899A2B3C402ff0132CF', NULL, '7F0999A2B3C402ff0132XXCF', 'DSPLAYSTATE显示状态', 'XX指的当前通道号：XX=01：模拟电视，=1C：数字电视，=02：视频1，=03：视频2，=0B：S-端子，=10：分量，=17：高清1，（HDMI1）=18：高清2(HDMI2)，=19(内置电脑ops)=00：VGA(电脑1)=11(电脑2)', '6', 'DisplayState', NULL, NULL);
INSERT INTO `command_code` VALUES ('22', '7F0899A2B3C402ff0133CF', NULL, '7F0999A2B3C402ff0133XXCF', 'VOLUME声音（如静音则返回0）', 'XX表示当前音量值（XX为十六进制数，范围为：00～64）。如：XX=20：当前音量值为32（十进制），=00：当前为静音。', '6', 'Volume', NULL, NULL);
INSERT INTO `command_code` VALUES ('23', '7F0899A2B3C402ff0134CF', NULL, '7F0999A2B3C402ff0134XXCF', '音响模式', 'XX指的音响模式：XX=00：标准，=01：音乐，=02：运动，=03：新闻，=04：用户，=05：临场1，=06：临场2', '6', 'AudioState', NULL, NULL);
INSERT INTO `command_code` VALUES ('24', '7F0899A2B3C402ff0135CF', NULL, '7F0999A2B3C402ff0135XXCF', 'ECOMODE节能模式（31～37）有返回参数用返回码中的第11位保存需要返回的参数，返回给上位机', 'XX指的节能模式：XX=00：标准，=01：节能，=02：自动', '6', 'EnergyState', NULL, NULL);
INSERT INTO `command_code` VALUES ('25', '7F0899A2B3C402ff0136CF', NULL, '7F0999A2B3C402ff0136XXCF', 'TOUCHSTATE触控状态', 'XX指的触摸模式：XX=00：电脑模式，=01：android模式(电脑模式下)', '6', 'TouchState', NULL, NULL);
INSERT INTO `command_code` VALUES ('26', '7F0899A2B3C402ff0137CF', NULL, '7F0999A2B3C402ff0137XXCF', '开关机状态', 'XX指的开关机状态：XX=01：开机状态', '6', 'SwitchState', NULL, NULL);
INSERT INTO `command_code` VALUES ('27', '7F0899A2B3C402ff0105CF', '0x013004', '7F0999A2B3C402ff010501CF', '音响模式-标准', '音响模式：XX=00：标准', '6', 'Standard', '00', 'Audio');
INSERT INTO `command_code` VALUES ('28', '7F0899A2B3C402ff0105CF', '0x013005', '7F0999A2B3C402ff010501CF', '音响模式-音乐', '音响模式：XX=01：音乐', '6', 'Music', '01', 'Audio');
INSERT INTO `command_code` VALUES ('29', '7F0899A2B3C402ff0105CF', '0x013006', '7F0999A2B3C402ff010501CF', '音响模式-运动', '音响模式：XX=02：运动', '6', 'Sport', '02', 'Audio');
INSERT INTO `command_code` VALUES ('30', '7F0899A2B3C402ff0105CF', '0x013007', '7F0999A2B3C402ff010501CF', '音响模式-新闻', '音响模式：XX=03：新闻', '6', 'News', '03', 'Audio');
INSERT INTO `command_code` VALUES ('31', '7F0899A2B3C402ff0105CF', '0x013008', '7F0999A2B3C402ff010501CF', '音响模式-用户', '音响模式：XX=04：用户', '6', 'Custom', '04', 'Audio');
INSERT INTO `command_code` VALUES ('32', '7F0899A2B3C402ff0105CF', '0x013009', '7F0999A2B3C402ff010501CF', '音响模式-临场1', '音响模式：XX=05：临场1', '6', 'Spot1', '05', 'Audio');
INSERT INTO `command_code` VALUES ('33', '7F0899A2B3C402ff0105CF', '0x013010', '7F0999A2B3C402ff010501CF', '音响模式-临场2', '音响模式：XX=06：临场2', '6', 'Spot2', '06', 'Audio');
INSERT INTO `command_code` VALUES ('34', '7F0899A2B3C402ff0116CF', '0x016003', '7F0999A2B3C402ff011601CF', '远程节能-标准', '节能模式：XX=00：标准', '6', 'Normal', '00', 'Energy');
INSERT INTO `command_code` VALUES ('35', '7F0899A2B3C402ff0116CF', '0x016004', '7F0999A2B3C402ff011601CF', '远程节能-节能', '节能模式：XX=01：节能', '6', 'PowerSaving', '01', 'Energy');
INSERT INTO `command_code` VALUES ('36', '7F0899A2B3C402ff0116CF', '0x016005', '7F0999A2B3C402ff011601CF', '远程节能-自动', '节能模式：XX=02：自动', '6', 'Auto', '02', 'Energy');
INSERT INTO `command_code` VALUES ('37', '7F0899A2B3C402ff0136CF', '', '7F0999A2B3C402ff0136XXCF', '触控状态-电脑模式', '触摸功能：XX=00：电脑模式', '6', 'computer', '00', 'Touch');
INSERT INTO `command_code` VALUES ('38', '7F0899A2B3C402ff0136CF', '', '7F0999A2B3C402ff0136XXCF', '触控状态-android模式', '触摸功能：XX=01：android模式', '6', 'android', '01', 'Touch');
INSERT INTO `command_code` VALUES ('40', '7F0899A2B3C402ff0108CF', '0x012001', '7F0999A2B3C402ff010B01CF', '01:模拟电视', '信号源切换：XX=01:模拟电视', '6', 'ATV', '01', 'Channel');
INSERT INTO `command_code` VALUES ('41', '7F0899A2B3C402ff010FCF', '0x012002', '7F0999A2B3C402ff010B01CF', '1C:数字电视', '信号源切换：XX=1C:数字电视', '6', 'DTV', '1C', 'Channel');
INSERT INTO `command_code` VALUES ('48', '7F0899A2B3C402ff010BCF', '0x012005', '7F0999A2B3C402ff010B01CF', '信号源切换：XX=0B:S端子', '信号源切换：XX=0B:S端子', '6', 'STerminal', '0B', 'Channel');
INSERT INTO `command_code` VALUES ('73', '7F0899A2B3C402ff011CCF', '0x012016', '7F0999A2B3C402ff011C01CF', '主页', '信号源切换：XX = 22：主页', '6', 'Home', '22', 'Channel');
INSERT INTO `command_code` VALUES ('80', '7F0899A2B3C402ff0100CF', '0x001001', '7E0999A2B3C402ff010001CF', '开机', NULL, '7', 'Boot', NULL, NULL);
INSERT INTO `command_code` VALUES ('81', '7F0899A2B3C402ff0101CF', '0x001002', '7E0999A2B3C402ff010101CF', '关机', NULL, '7', 'ShutDown', NULL, NULL);
INSERT INTO `command_code` VALUES ('82', '7F0899A2B3C402ff0102CF', ' ', '7E0999A2B3C402ff010201CF', '静音', '  ', '7', 'Mute', ' ', ' ');
INSERT INTO `command_code` VALUES ('83', '7F0899A2B3C402ff0103CF', '0x012001', '7E0999A2B3C402ff010301CF', 'TV', '模拟电视XX=00', '7', 'ATV', '00', 'Channel');
INSERT INTO `command_code` VALUES ('84', '7F0899A2B3C402ff0104CF', '0x012012', '7E0999A2B3C402ff010401CF', '电脑VGA', '电脑VGA XX= 06：电脑', '7', 'VGA', '06', 'Channel');
INSERT INTO `command_code` VALUES ('86', '7F0899A2B3C402ff0106CF', '0x012021', '7E0999A2B3C402ff010601CF', 'AV', '视频XX= 08：视频', '7', 'VIDEO', '08', 'Channel');
INSERT INTO `command_code` VALUES ('87', '7F0899A2B3C402ff0107CF', '0x012007', '7E0999A2B3C402ff010701CF', 'HDMI1', ' XX = 03', '7', 'HDMI1', '03', 'Channel');
INSERT INTO `command_code` VALUES ('88', '7F0899A2B3C402ff0122CF', '0x012008', '7E0999A2B3C402ff012201CF', 'HDMI2', 'XX= 04：HDMI2', '7', 'HDMI2', '04', 'Channel');
INSERT INTO `command_code` VALUES ('89', '7F0899A2B3C402ff0123CF', '0x012017', '7E0999A2B3C402ff012301CF', 'HDMI3', 'XX= 05：HDMI3', '7', 'HDMI3', '05', 'Channel');
INSERT INTO `command_code` VALUES ('90', '7F0899A2B3C402ff0124CF', '0x012011', '7E0999A2B3C402ff012401CF', 'OPS电脑(内置电脑)', '02：OPS电脑', '7', 'OPS', '02', 'Channel');
INSERT INTO `command_code` VALUES ('92', '7F0899A2B3C402ff0109CF', '0x012019', '7E0999A2B3C402ff010901CF', 'Multi-media', 'XX=09多媒体', '7', 'Media', '09', 'Channel');
INSERT INTO `command_code` VALUES ('95', '7F0899A2B3C402ff010CCF', '0x014002', '7E0999A2B3C402ff010C01CF', 'V-', '声音-', '7', 'Volume-', ' ', ' ');
INSERT INTO `command_code` VALUES ('96', '7F0899A2B3C402ff010DCF', '0x014001', '7E0999A2B3C402ff010D01CF', 'V+', '声音+', '7', 'Volume+', ' ', '    ');
INSERT INTO `command_code` VALUES ('107', '7F0899A2B3C402ff0125CF', '0x012002', '7E0999A2B3C402ff012501CF', 'DTV', '数字电视xx=01', '7', 'DTV', '01', 'Channel');
INSERT INTO `command_code` VALUES ('108', '7F0899A2B3C402ff0136CF', NULL, '7E0999A2B3C402ff0136XXCF', '开关机状态', 'XX指的开关机状态：XX=00关机状态\r\nXX = 01：开机状态', '7', 'SwitchState', '  ', ' ');
INSERT INTO `command_code` VALUES ('112', '7F0899A2B3C402ff0134CF', '0x013004', '7E0999A2B3C402ff0134XXCF', '音响模式', '标准', '7', 'Standard', '00', 'Audio');
INSERT INTO `command_code` VALUES ('113', '7F0899A2B3C402ff0134CF', '0x013005', '7E0999A2B3C402ff0134XXCF', '音响模式', '音乐', '7', 'Music', '01', 'Audio');
INSERT INTO `command_code` VALUES ('114', '7F0899A2B3C402ff0134CF', '0x013011', '7E0999A2B3C402ff0134XXCF', '音响模式', '电影', '7', 'Movie', '02', 'Audio');
INSERT INTO `command_code` VALUES ('115', '7F0899A2B3C402ff0134CF', '0x013006', '7E0999A2B3C402ff0134XXCF', '音响模式', '运动', '7', 'Sport', '03', 'Audio');
INSERT INTO `command_code` VALUES ('116', '7F0899A2B3C402ff0134CF', '0x013008', '7E0999A2B3C402ff0134XXCF', '音响模式', '用户', '7', 'Custom', '04', 'Audio');
INSERT INTO `command_code` VALUES ('117', '7F0899A2B3C402ff0135CF', ' ', '7E0999A2B3C402ff0135XXCF', 'ECOMODE  节能模式', 'XX指的节能模式：\r\nXX = 00：标准，\r\n      = 01：节能，\r\n      = 02：自动  ', '7', 'EnergyState', ' ', ' ');
INSERT INTO `command_code` VALUES ('118', '7F0899A2B3C402ff0134CF', ' ', '7E0999A2B3C402ff0134XXCF', '音响模式', 'XX=00:标准,XX=01:音乐,XX=02:电影,XX=03:运动XX=04:用户.', '7', 'AudioState', ' ', ' ');
INSERT INTO `command_code` VALUES ('119', '7F0899A2B3C402ff0133CF', ' ', '7E0999A2B3C402ff0133XXCF', 'VOLUME声音（如静音则返回0）', 'XX 表示当前音量值（XX为十六进制数，范围为：00～64）。如：\r\nXX = 20：当前音量值为32（十进制），\r\n      = 00：当前为静音。', '7', 'Volume', ' ', '  ');
INSERT INTO `command_code` VALUES ('120', '7F0899A2B3C402ff0132CF', '  ', '7E0999A2B3C402ff0132XXCF', 'DSPLAYSTATE显示状态', 'XX = 00：模拟电视， XX= 01：数字电视，\r\nXX= 02：OPS电脑，   XX = 03：HDMI1\r\nXX= 04：HDMI2，     XX= 05：HDMI3，\r\nXX= 06：电脑，     XX= 07：分量，\r\nXX= 08：视频,     XX =09:多媒体', '7', 'DisplayState', '', ' ');
INSERT INTO `command_code` VALUES ('131', '7F0899A2B3C402ff0131CF', ' ', '7E0999A2B3C402ff0131XXCF', '设备型号', 'XX指的主IC的型号：\r\nXX = 01：801; XX= 02：901;XX =03:V69', '7', 'Mosel', '03', ' ');
INSERT INTO `command_code` VALUES ('132', '7F0899A2B3C402ff0126CF', ' ', '7E0999A2B3C402ff012601CF', '节能', '节能设置命令', '7', 'Energy', ' ', ' ');
INSERT INTO `command_code` VALUES ('133', '7F0899A2B3C402ff0135CF', '0x016003', '7E0999A2B3C402ff0135XXCF', 'ECOMODE节能模式', '标准', '7', 'Normal', '00', 'Audio');
INSERT INTO `command_code` VALUES ('134', '7F0899A2B3C402ff0135CF', '0x016004', '7E0999A2B3C402ff0135XXCF', 'ECOMODE节能模式', '节能', '7', 'PowerSaving', '01', 'Audio');
INSERT INTO `command_code` VALUES ('135', '7F0899A2B3C402ff0135CF', '0x016005', '7E0999A2B3C402ff0135XXCF', 'ECOMODE节能模式', '自动', '7', 'Auto', '02', 'Audio');
INSERT INTO `command_code` VALUES ('136', '7F0899A2B3C402FF0100CF', '0x001001', '7F0999A2B3C402FF010001CF0', '开机', ' ', '8', 'Boot', ' ', ' ');
INSERT INTO `command_code` VALUES ('137', '7F0899A2B3C402FF0101CF', '0x001002', '7F0999A2B3C402FF010101CF', '关机', '', '8', 'ShutDown', '', '');
INSERT INTO `command_code` VALUES ('138', '7F0899A2B3C402FF0102CF', '', '7F0999A2B3C402FF010201CF', '静音', '', '8', 'Mute', '', '');
INSERT INTO `command_code` VALUES ('139', '7F0899A2B3C402FF0108CF', '0x012001', '7F0999A2B3C402FF010801CF', 'ATV', '模拟电视XX = 01：模拟电视', '8', 'ATV', '01', 'Channel');
INSERT INTO `command_code` VALUES ('140', '7F0899A2B3C402FF010ACF', '0x012007', '7F0999A2B3C402FF010A01CF', 'HDMI1', 'HDMI117：高清1', '8', 'HDMI1', '17', 'Channel');
INSERT INTO `command_code` VALUES ('141', '7F0899A2B3C402FF010BCF', '0x012008', '7F0999A2B3C402FF010B01CF', 'HDMI2', 'HDMI2= 18：高清2', '8', 'HDMI2', '18', 'Channel');
INSERT INTO `command_code` VALUES ('142', '7F0899A2B3C402FF010CCF', '0x012017', '7F0999A2B3C402FF010C01CF', 'HDMI3', 'HDMI320：高清3', '8', 'HDMI3', '20', 'Channel');
INSERT INTO `command_code` VALUES ('143', '7F0899A2B3C402FF0138CF', '0x012011', '7F0999A2B3C402FF013801CF', 'HDMI4', 'HDMI4（ops电脑）= 19：OPS', '8', 'OPS', '19', 'Channel');
INSERT INTO `command_code` VALUES ('144', '7F0899A2B3C402FF010DCF', '0x012009', '7F0999A2B3C402FF010D01CF', '电脑1', '电脑1 00：VGA1', '8', 'VGA1', '00', 'Channel');
INSERT INTO `command_code` VALUES ('145', '7F0899A2B3C402FF010FCF', '0x012002', '7F0999A2B3C402FF010F01CF', 'DTV', '数字电视1C：数字电视', '8', 'DTV', '1C', 'Channel');
INSERT INTO `command_code` VALUES ('146', '7F0899A2B3C402FF0110CF', '0x012006', '7F0999A2B3C402FF011001CF', '分量', '分量 = 10：分量', '8', 'Weight', '10', 'Channel');
INSERT INTO `command_code` VALUES ('147', '7F0899A2B3C402FF0111CF', '0x012003', '7F0999A2B3C402FF011101CF', '视频1', '视频1 02：视频1', '8', 'VIDEO1', '02', 'Channel');
INSERT INTO `command_code` VALUES ('148', '7F0899A2B3C402FF0117CF', '0x014002', '7F0999A2B3C402FF011701CF', '声音-', '', '8', 'Volume-', '', '');
INSERT INTO `command_code` VALUES ('149', '7F0899A2B3C402FF0118CF', '0x014001', '7F0999A2B3C402FF011801CF', '声音+', '', '8', 'Volume+', '', '');
INSERT INTO `command_code` VALUES ('150', '7F0899A2B3C402FF0119CF', '', '7F0999A2B3C402FF011901CF', '频道-', '', '8', '', '', '');
INSERT INTO `command_code` VALUES ('151', '7F0899A2B3C402FF011ACF', '', '7F0999A2B3C402FF011A01CF', '频道+', '', '8', '', '', '');
INSERT INTO `command_code` VALUES ('152', '7F0899A2B3C402FF011CCF', '0x012016', '7F0999A2B3C402FF011C01CF', '主页', '', '8', 'Home', '', 'Channel');
INSERT INTO `command_code` VALUES ('153', '7F0899A2B3C402FF011FCF', '', '7F0999A2B3C402FF011F01CF', '截屏', '', '8', 'ScreenShot', '', '');
INSERT INTO `command_code` VALUES ('154', '7F0899A2B3C402FF0300CF', '0x013004', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：00##标准', '8', 'Standard', '00', 'Audio');
INSERT INTO `command_code` VALUES ('155', '7F0899A2B3C402FF0301CF', '0x013005', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：01##音乐', '8', 'Music', '01', 'Audio');
INSERT INTO `command_code` VALUES ('156', '7F0899A2B3C402FF0302CF', '0x013006', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：02##运动', '8', 'Sport', '02', 'Audio');
INSERT INTO `command_code` VALUES ('157', '7F0899A2B3C402FF0303CF', '0x013007', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：03##新闻', '8', 'News', '03', 'Audio');
INSERT INTO `command_code` VALUES ('158', '7F0899A2B3C402FF0304CF', '0x013008', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：04##用户', '8', 'Custom', '04', 'Audio');
INSERT INTO `command_code` VALUES ('161', '7F0899A2B3C402FF05XXCF', '', '7F0999A2B3C402FF05XX01CF', '设置音量', 'XX表示音量值（0-100），对应十六进制：00-64', '8', '', '', '');
INSERT INTO `command_code` VALUES ('162', '7F0899A2B3C402FF0600CF', '0x016003', '7F0999A2B3C402FF06XX01CF', '设置节能模式', 'XX表示各种模式：00##标准', '8', 'Normal', '00', 'Energy');
INSERT INTO `command_code` VALUES ('163', '7F0899A2B3C402FF0601CF', '0x016004', '7F0999A2B3C402FF06XX01CF', '设置节能模式', 'XX表示各种模式：01##节能', '8', 'PowerSaving', '01', 'Energy');
INSERT INTO `command_code` VALUES ('164', '7F0899A2B3C402FF0602CF', '0x016005', '7F0999A2B3C402FF06XX01CF', '设置节能模式', 'XX表示各种模式：02##自动', '8', 'Auto', '02', 'Energy');
INSERT INTO `command_code` VALUES ('165', '7F0899A2B3C402FF013DCF', '', '7F0999A2B3C402FF013D01CF', '查询固件版本号', '', '8', '', '', '');
INSERT INTO `command_code` VALUES ('166', '7F0899A2B3C402FF013ECF', '', '7F0999A2B3C402FF013E01CF', '一键ATV+DTV自动搜台', '', '8', '', '', '');
INSERT INTO `command_code` VALUES ('167', '7F0899A2B3C402FF0131CF', '', '7F0999A2B3C402FF0131XXCF', '设备型号', 'XX = 01：801 , = 02：901.1  , =03：V69.5，=04：918，=05：901.6，=06：V69.8', '8', 'Model', '', '');
INSERT INTO `command_code` VALUES ('168', '7F0899A2B3C402FF0132CF', '', '7F0999A2B3C402FF0132XXCF', 'DSPLAYSTATE显示状态XX = 01：模拟电视， = 1C：数字电视，= 02：视频1，= 03：视频2，= 0B：S视频，= 10：分量， = 17：高清1， = 18：高清2，= 00：V', '', '8', 'DisplayState', '', '');
INSERT INTO `command_code` VALUES ('169', '7F0899A2B3C402FF0133CF', '', '7F0999A2B3C402FF0133XXCF', 'VOLUME声音（如静音则返回0）', '', '8', 'Volume', '', '');
INSERT INTO `command_code` VALUES ('170', '7F0899A2B3C402FF0134CF', '', '7F0999A2B3C402FF0134XXCF', '音响模式（状态）', '', '8', 'AudioState', '', '');
INSERT INTO `command_code` VALUES ('171', '7F0899A2B3C402FF0135CF', '', '7F0999A2B3C402FF0135XXCF', 'ECOMODE  节能模式', '', '8', 'EnergyState', '', '');
INSERT INTO `command_code` VALUES ('172', '7F0899A2B3C402FF0137CF', '', '7F0999A2B3C402FF0137XXCF', '开关机状态', '', '8', 'SwitchState', '', '');
INSERT INTO `command_code` VALUES ('173', '7F0899A2B3C402FF013CCF', '', '7F0999A2B3C402FF013CXXCF', '查询ATV/DTV频道号', '', '8', '', '', '');
INSERT INTO `command_code` VALUES ('174', '7F0899A2B3C402FF04XXCF', '', '7F0999A2B3C402FF04XX01CF', 'ATV/设置ATV/DTV频道号XX表示台号（1-255），对应十六进制：01-FF', '', '8', '', '', '');
INSERT INTO `command_code` VALUES ('175', '7F0899A2B3C402FF0102CF', '', '7F0999A2B3C402FF010201CF', '静音', '', '10', 'Mute', '', '');
INSERT INTO `command_code` VALUES ('176', '7F0899A2B3C402FF0108CF', '0x012001', '7F0999A2B3C402FF010801CF', 'ATV', '模拟电视X = 01：模拟电视', '10', 'ATV', '01', 'Channel');
INSERT INTO `command_code` VALUES ('177', '7F0899A2B3C402FF010ACF', '0x012007', '7F0999A2B3C402FF010A01CF', 'HDMI1', 'HDMI1 xx=17：高清1', '10', 'HDMI1', '17', 'Channel');
INSERT INTO `command_code` VALUES ('178', '7F0899A2B3C402FF010BCF', '0x012008', '7F0999A2B3C402FF010B01CF', 'HDMI2', 'HDMI2 xx=18', '10', 'HDMI2', '18', 'Channel');
INSERT INTO `command_code` VALUES ('179', '7F0899A2B3C402FF010CCF', '0x012017', '7F0999A2B3C402FF010C01CF', 'HDMI3', 'HDMI3 xx=20', '10', 'HDMI3', '20', 'Channel');
INSERT INTO `command_code` VALUES ('180', '7F0899A2B3C402FF0138CF', '0x012011', '7F0999A2B3C402FF013801CF', 'HDMI4', 'HDMI4（ops电脑） xx = 19：OPS', '10', 'OPS', '19', 'Channel');
INSERT INTO `command_code` VALUES ('181', '7F0899A2B3C402FF010DCF', '0x012009', '7F0999A2B3C402FF010D01CF', '电脑1', '电脑1   00：VGA1', '10', 'VGA1', '00', 'Channel');
INSERT INTO `command_code` VALUES ('182', '7F0899A2B3C402FF010FCF', '0x012002', '7F0999A2B3C402FF010F01CF', 'DTV', '数字电视= 1C：数字电视', '10', 'DTV', '1C', 'Channel');
INSERT INTO `command_code` VALUES ('183', '7F0899A2B3C402FF0110CF', '0x012006', '7F0999A2B3C402FF011001CF', '分量', '分量  = 10：分量', '10', 'Weight', '10', 'Channel');
INSERT INTO `command_code` VALUES ('184', '7F0899A2B3C402FF0111CF', '0x012003', '7F0999A2B3C402FF011101CF', '视频1', '视频1 = 02：视频1', '10', 'VIDEO1', '02', 'Channel');
INSERT INTO `command_code` VALUES ('185', '7F0899A2B3C402FF0117CF', '0x014002', '7F0999A2B3C402FF011701CF', '声音-', '', '10', 'Volume-', '', '');
INSERT INTO `command_code` VALUES ('186', '7F0899A2B3C402FF0118CF', '0x014001', '7F0999A2B3C402FF011801CF', '声音+', '', '10', 'Volume+', '', '');
INSERT INTO `command_code` VALUES ('187', '7F0899A2B3C402FF0119CF', '', '7F0999A2B3C402FF011901CF', '频道-', '', '10', '', '', '');
INSERT INTO `command_code` VALUES ('188', '7F0899A2B3C402FF011ACF', '', '7F0999A2B3C402FF011A01CF', '频道+', '', '10', '', '', '');
INSERT INTO `command_code` VALUES ('189', '7F0899A2B3C402FF011CCF', '0x012016', '7F0999A2B3C402FF011C01CF', '主页', '', '10', 'Home', '', 'Channel');
INSERT INTO `command_code` VALUES ('190', '7F0899A2B3C402FF011FCF', '', '7F0999A2B3C402FF011F01CF', '截屏', '', '10', 'ScreenShot', '', '');
INSERT INTO `command_code` VALUES ('191', '7F0899A2B3C402FF0300CF', '0x013004', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：00##标准', '10', 'Standard', '00', 'Audio');
INSERT INTO `command_code` VALUES ('192', '7F0899A2B3C402FF0301CF', '0x013005', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：01##音乐', '10', 'Music', '01', 'Audio');
INSERT INTO `command_code` VALUES ('193', '7F0899A2B3C402FF0302CF', '0x013006', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：02##运动', '10', 'Sport', '02', 'Audio');
INSERT INTO `command_code` VALUES ('194', '7F0899A2B3C402FF0303CF', '0x013007', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：03##新闻', '10', 'News', '03', 'Audio');
INSERT INTO `command_code` VALUES ('195', '7F0899A2B3C402FF0304CF', '0x013008', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：04##用户', '10', 'Custom', '04', 'Audio');
INSERT INTO `command_code` VALUES ('196', '7F0899A2B3C402FF0305CF', '0x013009', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：05##临场1', '10', 'Spot1', '05', 'Audio');
INSERT INTO `command_code` VALUES ('197', '7F0899A2B3C402FF0306CF', '0x013010', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：06##临场2', '10', 'Spot2', '06', 'Audio');
INSERT INTO `command_code` VALUES ('198', '7F0899A2B3C402FF05XXCF', '', '7F0999A2B3C402FF05XX01CF', '设置音量', 'XX表示音量值（0-100），对应十六进制：00-64', '10', '', '', '');
INSERT INTO `command_code` VALUES ('199', '7F0899A2B3C402FF0600CF', '0x016003', '7F0999A2B3C402FF06XX01CF', '设置节能模式', 'XX表示各种模式：00##标准', '10', 'Normal', '00', 'Energy');
INSERT INTO `command_code` VALUES ('200', '7F0899A2B3C402FF0601CF', '0x016004', '7F0999A2B3C402FF06XX01CF', '设置节能模式', 'XX表示各种模式：01##节能', '10', 'PowerSaving', '01', 'Energy');
INSERT INTO `command_code` VALUES ('201', '7F0899A2B3C402FF0602CF', '0x016005', '7F0999A2B3C402FF06XX01CF', '设置节能模式', 'XX表示各种模式：02##自动', '10', 'Auto', '02', 'Energy');
INSERT INTO `command_code` VALUES ('202', '7F0899A2B3C402FF013DCF', '', '7F0999A2B3C402FF013D01CF', '查询固件版本号', '', '10', '', '', '');
INSERT INTO `command_code` VALUES ('203', '7F0899A2B3C402FF013ECF', '', '7F0999A2B3C402FF013E01CF', '一键ATV+DTV自动搜台', '', '10', '', '', '');
INSERT INTO `command_code` VALUES ('204', '7F0899A2B3C402FF0131CF', '', '7F0999A2B3C402FF0131XXCF', '设备型号', 'XX = 01：801 , = 02：901.1  , =03：V69.5，=04：918，=05：901.6，=06：V69.8', '10', 'Model', '', '');
INSERT INTO `command_code` VALUES ('205', '7F0899A2B3C402FF0132CF', '', '7F0999A2B3C402FF0132XXCF', 'DSPLAYSTATE显示状态XX = 01：模拟电视， = 1C：数字电视，= 02：视频1，= 03：视频2，= 0B：S视频，= 10：分量， = 17：高清1， = 18：高清2，= 00：V', '', '10', 'DisplayState', '', '');
INSERT INTO `command_code` VALUES ('206', '7F0899A2B3C402FF0133CF', '', '7F0999A2B3C402FF0133XXCF', 'VOLUME声音（如静音则返回0）', '', '10', 'Volume', '', '');
INSERT INTO `command_code` VALUES ('207', '7F0899A2B3C402FF0134CF', '', '7F0999A2B3C402FF0134XXCF', '音响模式（状态）', '', '10', 'AudioState', '', '');
INSERT INTO `command_code` VALUES ('208', '7F0899A2B3C402FF0135CF', '', '7F0999A2B3C402FF0135XXCF', 'ECOMODE  节能模式', '', '10', 'EnergyState', '', '');
INSERT INTO `command_code` VALUES ('209', '7F0899A2B3C402FF0137CF', '', '7F0999A2B3C402FF0137XXCF', '开关机状态', '', '10', 'SwitchState', '', '');
INSERT INTO `command_code` VALUES ('210', '7F0899A2B3C402FF013CCF', '', '7F0999A2B3C402FF013CXXCF', '查询ATV/DTV频道号', '', '10', '', '', '');
INSERT INTO `command_code` VALUES ('211', '7F0899A2B3C402FF04XXCF', '', '7F0999A2B3C402FF04XX01CF', 'ATV/设置ATV/DTV频道号XX表示台号（1-255），对应十六进制：01-FF', '', '10', '', '', '');
INSERT INTO `command_code` VALUES ('212', '7F0899A2B3C402FF0102CF', '', '7F0999A2B3C402FF010201CF', '静音', '', '9', 'Mute', '', '');
INSERT INTO `command_code` VALUES ('213', '7F0899A2B3C402FF0108CF', '0x012001', '7F0999A2B3C402FF010801CF', 'ATV', '模拟电视 01：模拟电视', '9', 'ATV', '01', 'Channel');
INSERT INTO `command_code` VALUES ('214', '7F0899A2B3C402FF010ACF', '0x012007', '7F0999A2B3C402FF010A01CF', 'HDMI1', 'HDMI1  = 17：高清1', '9', 'HDMI1', '17', 'Channel');
INSERT INTO `command_code` VALUES ('215', '7F0899A2B3C402FF010BCF', '0x012008', '7F0999A2B3C402FF010B01CF', 'HDMI2', 'HDMI2   = 18：高清2', '9', 'HDMI2', '18', 'Channel');
INSERT INTO `command_code` VALUES ('216', '7F0899A2B3C402FF010CCF', '0x012017', '7F0999A2B3C402FF010C01CF', 'HDMI3', 'HDMI3  =20：高清3', '9', 'HDMI3', '20', 'Channel');
INSERT INTO `command_code` VALUES ('217', '7F0899A2B3C402FF0138CF', '0x012011', '7F0999A2B3C402FF013801CF', 'HDMI4', 'HDMI4（ops电脑）    = 19：OPS', '9', 'OPS', '19', 'Channel');
INSERT INTO `command_code` VALUES ('218', '7F0899A2B3C402FF010DCF', '0x012009', '7F0999A2B3C402FF010D01CF', '电脑1', '电脑1  = 00：VGA1', '9', 'VGA1', '00', 'Channel');
INSERT INTO `command_code` VALUES ('219', '7F0899A2B3C402FF010FCF', '0x012002', '7F0999A2B3C402FF010F01CF', 'DTV', '数字电视  = 1C：数字电视', '9', 'DTV', '1C', 'Channel');
INSERT INTO `command_code` VALUES ('220', '7F0899A2B3C402FF0110CF', '0x012006', '7F0999A2B3C402FF011001CF', '分量', '分量  = 10：分量', '9', 'Weight', '10', 'Channel');
INSERT INTO `command_code` VALUES ('221', '7F0899A2B3C402FF0111CF', '0x012003', '7F0999A2B3C402FF011101CF', '视频1', '视频1 = 02：视频1', '9', 'VIDEO1', '02', 'Channel');
INSERT INTO `command_code` VALUES ('222', '7F0899A2B3C402FF0117CF', '0x014002', '7F0999A2B3C402FF011701CF', '声音-', '', '9', 'Volume-', '', '');
INSERT INTO `command_code` VALUES ('223', '7F0899A2B3C402FF0118CF', '0x014001', '7F0999A2B3C402FF011801CF', '声音+', '', '9', 'Volume+', '', '');
INSERT INTO `command_code` VALUES ('224', '7F0899A2B3C402FF0119CF', '', '7F0999A2B3C402FF011901CF', '频道-', '', '9', '', '', '');
INSERT INTO `command_code` VALUES ('225', '7F0899A2B3C402FF011ACF', '', '7F0999A2B3C402FF011A01CF', '频道+', '', '9', '', '', '');
INSERT INTO `command_code` VALUES ('226', '7F0899A2B3C402FF011CCF', '0x012016', '7F0999A2B3C402FF011C01CF', '主页', '', '9', 'Home', '', 'Channel');
INSERT INTO `command_code` VALUES ('227', '7F0899A2B3C402FF011FCF', '', '7F0999A2B3C402FF011F01CF', '截屏', '', '9', 'ScreenShot', '', '');
INSERT INTO `command_code` VALUES ('228', '7F0899A2B3C402FF0300CF', '0x013004', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：00##标准', '9', 'Standard', '00', 'Audio');
INSERT INTO `command_code` VALUES ('229', '7F0899A2B3C402FF0301CF', '0x013005', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：01##音乐', '9', 'Music', '01', 'Audio');
INSERT INTO `command_code` VALUES ('230', '7F0899A2B3C402FF0302CF', '0x013006', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：02##运动', '9', 'Sport', '02', 'Audio');
INSERT INTO `command_code` VALUES ('231', '7F0899A2B3C402FF0303CF', '0x013007', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：03##新闻', '9', 'News', '03', 'Audio');
INSERT INTO `command_code` VALUES ('232', '7F0899A2B3C402FF0304CF', '0x013008', '7F0899A2B3C402FF03XX01CF', '设置音响模式', 'XX表示各种模式：04##用户', '9', 'Custom', '04', 'Audio');
INSERT INTO `command_code` VALUES ('235', '7F0899A2B3C402FF05XXCF', '', '7F0999A2B3C402FF05XX01CF', '设置音量', 'XX表示音量值（0-100），对应十六进制：00-64', '9', '', '', '');
INSERT INTO `command_code` VALUES ('236', '7F0899A2B3C402FF0600CF', '0x016003', '7F0999A2B3C402FF06XX01CF', '设置节能模式', 'XX表示各种模式：00##标准', '9', 'Normal', '00', 'Energy');
INSERT INTO `command_code` VALUES ('237', '7F0899A2B3C402FF0601CF', '0x016004', '7F0999A2B3C402FF06XX01CF', '设置节能模式', 'XX表示各种模式：01##节能', '9', 'PowerSaving', '01', 'Energy');
INSERT INTO `command_code` VALUES ('238', '7F0899A2B3C402FF0602CF', '0x016005', '7F0999A2B3C402FF06XX01CF', '设置节能模式', 'XX表示各种模式：02##自动', '9', 'Auto', '02', 'Energy');
INSERT INTO `command_code` VALUES ('239', '7F0899A2B3C402FF013DCF', '', '7F0999A2B3C402FF013D01CF', '查询固件版本号', '', '9', '', '', '');
INSERT INTO `command_code` VALUES ('240', '7F0899A2B3C402FF013ECF', '', '7F0999A2B3C402FF013E01CF', '一键ATV+DTV自动搜台', '', '9', '', '', '');
INSERT INTO `command_code` VALUES ('241', '7F0899A2B3C402FF0131CF', '', '7F0999A2B3C402FF0131XXCF', '设备型号', 'XX = 01：801 , = 02：901.1  , =03：V69.5，=04：918，=05：901.6，=06：V69.8', '9', 'Model', '', '');
INSERT INTO `command_code` VALUES ('242', '7F0899A2B3C402FF0132CF', '', '7F0999A2B3C402FF0132XXCF', 'DSPLAYSTATE显示状态XX = 01：模拟电视， = 1C：数字电视，= 02：视频1，= 03：视频2，= 0B：S视频，= 10：分量， = 17：高清1， = 18：高清2，= 00：V', '', '9', 'DisplayState', '', '');
INSERT INTO `command_code` VALUES ('243', '7F0899A2B3C402FF0133CF', '', '7F0999A2B3C402FF0133XXCF', 'VOLUME声音（如静音则返回0）', '', '9', 'Volume', '', '');
INSERT INTO `command_code` VALUES ('244', '7F0899A2B3C402FF0134CF', '', '7F0999A2B3C402FF0134XXCF', '音响模式（状态）', '', '9', 'AudioState', '', '');
INSERT INTO `command_code` VALUES ('245', '7F0899A2B3C402FF0135CF', '', '7F0999A2B3C402FF0135XXCF', 'ECOMODE  节能模式', '', '9', 'EnergyState', '', '');
INSERT INTO `command_code` VALUES ('246', '7F0899A2B3C402FF0137CF', '', '7F0999A2B3C402FF0137XXCF', '开关机状态', '', '9', 'SwitchState', '', '');
INSERT INTO `command_code` VALUES ('247', '7F0899A2B3C402FF013CCF', '', '7F0999A2B3C402FF013CXXCF', '查询ATV/DTV频道号', '', '9', '', '', '');
INSERT INTO `command_code` VALUES ('248', '7F0899A2B3C402FF04XXCF', '', '7F0999A2B3C402FF04XX01CF', 'ATV/设置ATV/DTV频道号XX表示台号（1-255），对应十六进制：01-FF', '', '9', '', '', '');
INSERT INTO `command_code` VALUES ('251', '7F0899A2B3C402FF0101CF', '0x001002', '7F0999A2B3C402FF010101CF', '关机', '', '9', 'ShutDown', '', '');
INSERT INTO `command_code` VALUES ('252', '7F0899A2B3C402FF0101CF', '0x001002', '7F0999A2B3C402FF010101CF', '关机', '', '8', 'ShutDown', '', '');
INSERT INTO `command_code` VALUES ('253', '7F0899A2B3C402FF0121CF', '', '7F0999A2B3C402FF012101CF', '数字1', '', '6', 'Number1', '', '');
INSERT INTO `command_code` VALUES ('254', '7F0899A2B3C402FF0122CF', '', '7F0999A2B3C402FF012201CF', '数字2', '', '6', 'Number2', '', '');
INSERT INTO `command_code` VALUES ('255', '7F0899A2B3C402FF0123CF', '', '7F0999A2B3C402FF012301CF', '数字3', '', '6', 'Number3', '', '');
INSERT INTO `command_code` VALUES ('256', '7F0899A2B3C402FF0124CF', '', '7F0999A2B3C402FF012401CF', '数字4', '', '6', 'Number4', '', '');
INSERT INTO `command_code` VALUES ('257', '7F0899A2B3C402FF0125CF', '', '7F0999A2B3C402FF012501CF', '数字5', '', '6', 'Number5', '', '');
INSERT INTO `command_code` VALUES ('258', '7F0899A2B3C402FF0126CF', '', '7F0999A2B3C402FF012601CF', '数字6', '', '6', 'Number6', '', '');
INSERT INTO `command_code` VALUES ('259', '7F0899A2B3C402FF0127CF', '', '7F0999A2B3C402FF012701CF', '数字7', '', '6', 'Number7', '', '');
INSERT INTO `command_code` VALUES ('260', '7F0899A2B3C402FF0128CF', '', '7F0999A2B3C402FF012801CF', '数字8', '', '6', 'Number8', '', '');
INSERT INTO `command_code` VALUES ('261', '7F0899A2B3C402FF0129CF', '', '7F0999A2B3C402FF012901CF', '数字9', '', '6', 'Number9', '', '');
INSERT INTO `command_code` VALUES ('262', '7F0899A2B3C402FF012ACF', '', '7F0999A2B3C402FF012A01CF', '数字0', '', '6', 'Number0', '', '');
INSERT INTO `command_code` VALUES ('263', '7F0899A2B3C402FF0121CF', '', '7F0999A2B3C402FF012101CF', '数字1', '', '8', 'Number1', '', '');
INSERT INTO `command_code` VALUES ('264', '7F0899A2B3C402FF0122CF', '', '7F0999A2B3C402FF012201CF', '数字2', '', '8', 'Number2', '', '');
INSERT INTO `command_code` VALUES ('265', '7F0899A2B3C402FF0123CF', '', '7F0999A2B3C402FF012301CF', '数字3', '', '8', 'Number3', '', '');
INSERT INTO `command_code` VALUES ('266', '7F0899A2B3C402FF0124CF', '', '7F0999A2B3C402FF012401CF', '数字4', '', '8', 'Number4', '', '');
INSERT INTO `command_code` VALUES ('267', '7F0899A2B3C402FF0125CF', '', '7F0999A2B3C402FF012501CF', '数字5', '', '8', 'Number5', '', '');
INSERT INTO `command_code` VALUES ('268', '7F0899A2B3C402FF0126CF', '', '7F0999A2B3C402FF012601CF', '数字6', '', '8', 'Number6', '', '');
INSERT INTO `command_code` VALUES ('269', '7F0899A2B3C402FF0127CF', '', '7F0999A2B3C402FF012701CF', '数字7', '', '8', 'Number7', '', '');
INSERT INTO `command_code` VALUES ('270', '7F0899A2B3C402FF0128CF', '', '7F0999A2B3C402FF012801CF', '数字8', '', '8', 'Number8', '', '');
INSERT INTO `command_code` VALUES ('271', '7F0899A2B3C402FF0129CF', '', '7F0999A2B3C402FF012901CF', '数字9', '', '8', 'Number9', '', '');
INSERT INTO `command_code` VALUES ('272', '7F0899A2B3C402FF012ACF', '', '7F0999A2B3C402FF012A01CF', '数字0', '', '8', 'Number0', '', '');
INSERT INTO `command_code` VALUES ('273', '7F0899A2B3C402FF0121CF', '', '7F0999A2B3C402FF012101CF', '数字1', '', '9', 'Number1', '', '');
INSERT INTO `command_code` VALUES ('274', '7F0899A2B3C402FF0122CF', '', '7F0999A2B3C402FF012201CF', '数字2', '', '9', 'Number2', '', '');
INSERT INTO `command_code` VALUES ('275', '7F0899A2B3C402FF0123CF', '', '7F0999A2B3C402FF012301CF', '数字3', '', '9', 'Number3', '', '');
INSERT INTO `command_code` VALUES ('276', '7F0899A2B3C402FF0124CF', '', '7F0999A2B3C402FF012401CF', '数字4', '', '9', 'Number4', '', '');
INSERT INTO `command_code` VALUES ('277', '7F0899A2B3C402FF0125CF', '', '7F0999A2B3C402FF012501CF', '数字5', '', '9', 'Number5', '', '');
INSERT INTO `command_code` VALUES ('278', '7F0899A2B3C402FF0126CF', '', '7F0999A2B3C402FF012601CF', '数字6', '', '9', 'Number6', '', '');
INSERT INTO `command_code` VALUES ('279', '7F0899A2B3C402FF0127CF', '', '7F0999A2B3C402FF012701CF', '数字7', '', '9', 'Number7', '', '');
INSERT INTO `command_code` VALUES ('280', '7F0899A2B3C402FF0128CF', '', '7F0999A2B3C402FF012801CF', '数字8', '', '9', 'Number8', '', '');
INSERT INTO `command_code` VALUES ('281', '7F0899A2B3C402FF0129CF', '', '7F0999A2B3C402FF012901CF', '数字9', '', '9', 'Number9', '', '');
INSERT INTO `command_code` VALUES ('282', '7F0899A2B3C402FF012ACF', '', '7F0999A2B3C402FF012A01CF', '数字0', '', '9', 'Number0', '', '');
INSERT INTO `command_code` VALUES ('283', '7F0899A2B3C402FF0121CF', '', '7F0999A2B3C402FF012101CF', '数字1', '', '10', 'Number1', '', '');
INSERT INTO `command_code` VALUES ('284', '7F0899A2B3C402FF0122CF', '', '7F0999A2B3C402FF012201CF', '数字2', '', '10', 'Number2', '', '');
INSERT INTO `command_code` VALUES ('285', '7F0899A2B3C402FF0123CF', '', '7F0999A2B3C402FF012301CF', '数字3', '', '10', 'Number3', '', '');
INSERT INTO `command_code` VALUES ('286', '7F0899A2B3C402FF0124CF', '', '7F0999A2B3C402FF012401CF', '数字4', '', '10', 'Number4', '', '');
INSERT INTO `command_code` VALUES ('287', '7F0899A2B3C402FF0125CF', '', '7F0999A2B3C402FF012501CF', '数字5', '', '10', 'Number5', '', '');
INSERT INTO `command_code` VALUES ('288', '7F0899A2B3C402FF0126CF', '', '7F0999A2B3C402FF012601CF', '数字6', '', '10', 'Number6', '', '');
INSERT INTO `command_code` VALUES ('289', '7F0899A2B3C402FF0127CF', '', '7F0999A2B3C402FF012701CF', '数字7', '', '10', 'Number7', '', '');
INSERT INTO `command_code` VALUES ('290', '7F0899A2B3C402FF0128CF', '', '7F0999A2B3C402FF012801CF', '数字8', '', '10', 'Number8', '', '');
INSERT INTO `command_code` VALUES ('291', '7F0899A2B3C402FF0129CF', '', '7F0999A2B3C402FF012901CF', '数字9', '', '10', 'Number9', '', '');
INSERT INTO `command_code` VALUES ('292', '7F0899A2B3C402FF012ACF', '', '7F0999A2B3C402FF012A01CF', '数字0', '', '10', 'Number0', '', '');
INSERT INTO `command_code` VALUES ('293', '7F0899A2B3C402FF0136CF', '', '7F0999A2B3C402FF0136XXCF', '触控状态-电脑模式', '触摸功能：XX=00：电脑模式', '8', 'computer', '00', 'Touch');
INSERT INTO `command_code` VALUES ('294', '7F0899A2B3C402FF0136CF', '', '7F0999A2B3C402FF0136XXCF', '触控状态-android模式', '触摸功能：XX=01：android模式', '8', 'android', '01', 'Touch');
INSERT INTO `command_code` VALUES ('295', '7F0899A2B3C402FF0146CF', '', '7F0999A2B3XXXXXXXXXXXXCF', '获得MCU地址', '返回值从第六个开始11个止', '6', 'MCUAddress', '', '');
INSERT INTO `command_code` VALUES ('296', '7F0899A2B3C402FF0146CF', '', '7F0999A2B3XXXXXXXXXXXXCF', '获得MCU地址', '返回值从第六个开始11个止', '7', 'MCUAddress', '', '');
INSERT INTO `command_code` VALUES ('297', '7F0899A2B3C402FF0146CF', '', '7F0999A2B3XXXXXXXXXXXXCF', '获得MCU地址', '返回值从第六个开始11个止', '6', 'MCUAddress', '', '');
INSERT INTO `command_code` VALUES ('298', '7F0899A2B3C402FF0146CF', '', '7F0999A2B3XXXXXXXXXXXXCF', '获得MCU地址', '返回值从第六个开始11个止', '8', 'MCUAddress', '', '');
INSERT INTO `command_code` VALUES ('299', '7F0899A2B3C402FF0146CF', '', '7F0999A2B3XXXXXXXXXXXXCF', '获得MCU地址', '返回值从第六个开始11个止', '9', 'MCUAddress', '', '');
INSERT INTO `command_code` VALUES ('300', '7F0899A2B3C402FF0146CF', '', '7F0999A2B3XXXXXXXXXXXXCF', '获得MCU地址', '返回值从第六个开始11个止', '10', 'MCUAddress', '', '');
INSERT INTO `command_code` VALUES ('301', 'BEEF0306002AD3010000600000', '0x017002', '06', '关机', ' ', '12', 'TurnOff', '1d0000', 'Turn');
INSERT INTO `command_code` VALUES ('302', 'BEEF030600BAD2010000600100', '0x017001', '06', '开机', '', '12', 'TurnOn', '1d0100', 'Turn');
INSERT INTO `command_code` VALUES ('303', 'BEEF03060019D3020000600000', '', '1DXX00', '获得开关机状态', '1D0000:TurnOff;1D0100:TurnOn:1D0200:CoolDown', '12', 'TurnState', '', '');
INSERT INTO `command_code` VALUES ('304', 'BEEF030600FED2010000200000', '0x012009', '06', 'Computer1', '', '12', 'Computer1', '1d0000', 'Channel');
INSERT INTO `command_code` VALUES ('305', 'BEEF0306003ED0010000200400', '0x012010', '06', 'Computer2', '', '12', 'Computer2', '1d0400', 'Channel');
INSERT INTO `command_code` VALUES ('306', 'BEEF030600AED1010000200500', '0x012006', '06', 'Component', '', '12', 'Component', '1d0500', 'Channel');
INSERT INTO `command_code` VALUES ('307', 'BEEF0306009ED3010000200200', '0x012005', '06', 'S-Video', '', '12', 'S-Video', '1d0200', 'Channel');
INSERT INTO `command_code` VALUES ('308', 'BEEF0306006ED3010000200100', '0x012003', '06', 'Video', '', '12', 'Video', '1d0100', 'Channel');
INSERT INTO `command_code` VALUES ('309', 'BEEF030600CDD2020000200000', '', '1DXX00', '获得信号源状态', '1D0100:Video;', '12', 'ChannelState', '', '');
INSERT INTO `command_code` VALUES ('310', '', '', '', '冷却', '', '12', 'CoolDown', '1d0200', 'Turn');
INSERT INTO `command_code` VALUES ('311', '7F0899A2B3C402FF0157CF', '0x015005', '7F0999A2B3C402FF015701CF', '大屏锁定', NULL, '6', 'ScreenLock', NULL, '');
INSERT INTO `command_code` VALUES ('312', '7F0899A2B3C402FF0157CF', '0x015006', '7F0999A2B3C402FF015701CF', '解锁屏幕', NULL, '6', 'ScreenUnlock', NULL, '');
INSERT INTO `command_code` VALUES ('313', '7F0899A2B3C402FF0158CF', NULL, '7F0999A2B3C402FF015801CF', '获得大屏屏锁状态', NULL, '6', 'ScreenLockMode', NULL, '');
INSERT INTO `command_code` VALUES ('314', '7F0899A2B3C402FF0157CF', '0x015005', '7F0999A2B3C402FF015701CF', '大屏锁定', NULL, '7', 'ScreenLock', NULL, '');
INSERT INTO `command_code` VALUES ('315', '7F0899A2B3C402FF0157CF', '0x015006', '7F0999A2B3C402FF015701CF', '解锁屏幕', NULL, '7', 'ScreenUnlock', NULL, '');
INSERT INTO `command_code` VALUES ('316', '7F0899A2B3C402FF0158CF', NULL, '7F0999A2B3C402FF015801CF', '获得大屏屏锁状态', NULL, '7', 'ScreenLockMode', NULL, '');
INSERT INTO `command_code` VALUES ('317', '7F0899A2B3C402FF0157CF', '0x015005', '7F0999A2B3C402FF015701CF', '大屏锁定', NULL, '8', 'ScreenLock', NULL, '');
INSERT INTO `command_code` VALUES ('318', '7F0899A2B3C402FF0157CF', '0x015006', '7F0999A2B3C402FF015701CF', '解锁屏幕', NULL, '8', 'ScreenUnlock', NULL, '');
INSERT INTO `command_code` VALUES ('319', '7F0899A2B3C402FF0158CF', NULL, '7F0999A2B3C402FF015801CF', '获得大屏屏锁状态', NULL, '8', 'ScreenLockMode', NULL, '');
INSERT INTO `command_code` VALUES ('320', '7F0899A2B3C402FF0157CF', '0x015005', '7F0999A2B3C402FF015701CF', '大屏锁定', NULL, '9', 'ScreenLock', NULL, '');
INSERT INTO `command_code` VALUES ('321', '7F0899A2B3C402FF0157CF', '0x015006', '7F0999A2B3C402FF015701CF', '解锁屏幕', NULL, '9', 'ScreenUnlock', NULL, '');
INSERT INTO `command_code` VALUES ('322', '7F0899A2B3C402FF0158CF', NULL, '7F0999A2B3C402FF015801CF', '获得大屏屏锁状态', NULL, '9', 'ScreenLockMode', NULL, '');
INSERT INTO `command_code` VALUES ('323', '7F0899A2B3C402FF0157CF', '0x015005', '7F0999A2B3C402FF015701CF', '大屏锁定', NULL, '10', 'ScreenLock', NULL, '');
INSERT INTO `command_code` VALUES ('324', '7F0899A2B3C402FF0157CF', '0x015006', '7F0999A2B3C402FF015701CF', '解锁屏幕', NULL, '10', 'ScreenUnlock', NULL, '');
INSERT INTO `command_code` VALUES ('325', '7F0899A2B3C402FF0158CF', NULL, '7F0999A2B3C402FF015801CF', '获得大屏屏锁状态', NULL, '10', 'ScreenLockMode', NULL, '');
INSERT INTO `command_code` VALUES ('331', 'BEEF0306002AD3010000600000', '0x001002', '06', '关机', NULL, '12', 'TurnOff', '1d0000', '');
INSERT INTO `command_code` VALUES ('332', 'BEEF030600BAD2010000600100', '0x001001', '06', '开机', NULL, '12', 'boot', '1d0000', '');

-- ----------------------------
-- Table structure for `curriculum`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `curriculum` (
  `cur_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '课表自增id',
  `cur_date` DATETIME DEFAULT NULL COMMENT '日期',
  `cur_week` INT(11) DEFAULT NULL COMMENT '星期几',
  `cur_section` INT(11) DEFAULT NULL COMMENT '节次',
  `cur_host` INT(11) DEFAULT NULL COMMENT '上课教室',
  `cur_class` VARCHAR(255) DEFAULT NULL COMMENT '上课行政班级',
  `cur_teacher` VARCHAR(255) DEFAULT NULL COMMENT '教师',
  `cur_subject` VARCHAR(255) DEFAULT NULL COMMENT '科目',
  `cur_unit` VARCHAR(255) DEFAULT NULL COMMENT '教学单位',
  PRIMARY KEY (`cur_id`),
  KEY `classtime` (`cur_section`)
) ENGINE=MYISAM DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `device`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `device` (
  `device_id` INT(11) NOT NULL AUTO_INCREMENT,
  `device_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_maintoken` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_subtoken` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `host_id` INT(11) DEFAULT NULL,
  `device_mainstream` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `device_substream` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`device_id`),
  KEY `FK_ipi2a5qoc7yc1xn1l9hkwon1n` (`host_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- ----------------------------
-- Table structure for `deviceconfig`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `deviceconfig` (
  `id` INT(11) NOT NULL,
  `connect_param` VARCHAR(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` VARCHAR(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `main` VARCHAR(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sub` VARCHAR(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_ptz` INT(11) DEFAULT NULL COMMENT '是否支持云台操作 0 不支持 1 支持',
  PRIMARY KEY (`id`)
) ENGINE=MYISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of deviceconfig
-- ----------------------------
INSERT INTO `deviceconfig` VALUES ('1', 'C6F0SjZ3N0P0L0', '教师全景', 'MainProfileToken', 'SubProfileToken', '0');
INSERT INTO `deviceconfig` VALUES ('2', 'HL-ZF0400', '教师全景', 'FrontToken', 'FrontSubToken', '1');
INSERT INTO `deviceconfig` VALUES ('3', 'HL-ZF0400', '学生全景', 'StudentsMainToken', 'StudentsSubToken', '1');
INSERT INTO `deviceconfig` VALUES ('4', 'HL-ZF0400', '教师特写', 'TearcherToken', 'TearcherSubToken', '1');
INSERT INTO `deviceconfig` VALUES ('5', 'HL-ZF0400', 'VGA', 'ComputerToken', 'ComputerSubToken', '0');
INSERT INTO `deviceconfig` VALUES ('6', 'HL-ZF0400', '电影模式', 'MovieToken', 'MovieSubToken', '0');
INSERT INTO `deviceconfig` VALUES ('7', '12345678', '教师全景', 'platform', 'platform', '0');
INSERT INTO `deviceconfig` VALUES ('8', '12345678', '学生全景', 'students', 'students', '0');
INSERT INTO `deviceconfig` VALUES ('9', '12345678', '学生特写', 'student', 'student', '1');
INSERT INTO `deviceconfig` VALUES ('10', '12345678', '电影模式', 'movie', 'movie', '0');
INSERT INTO `deviceconfig` VALUES ('11', '12345678', '教师特写', 'teacher', 'teacher', '1');
INSERT INTO `deviceconfig` VALUES ('12', '12345678', '板书', 'blackboard', 'blackboard', '1');
INSERT INTO `deviceconfig` VALUES ('13', '12345678', 'VGA ', 'computer', 'computer', '0');
INSERT INTO `deviceconfig` VALUES ('14', '12345678', '通道一', 'channel1', 'channel1', '1');
INSERT INTO `deviceconfig` VALUES ('15', '12345678', '通道二', 'channel2', 'channel2', '1');
INSERT INTO `deviceconfig` VALUES ('16', '12345678', '通道三', 'channel3', 'channel3', '1');
INSERT INTO `deviceconfig` VALUES ('17', '12345678', '通道四', 'channel4', 'channel4', '1');
INSERT INTO `deviceconfig` VALUES ('18', '12345678', '通道五', 'channel5', 'channel5', '1');
INSERT INTO `deviceconfig` VALUES ('19', '12345678', '通道六', 'channel6', 'channel6', '1');
INSERT INTO `deviceconfig` VALUES ('20', '12345678', '通道七', 'channel7', 'channel7', '1');
INSERT INTO `deviceconfig` VALUES ('21', '12345678', '通道八', 'channel8', 'channel8', '1');
INSERT INTO `deviceconfig` VALUES ('22', 'HL-ZJ0500', '电影模式', 'movie', 'movie', '0');
INSERT INTO `deviceconfig` VALUES ('23', 'HL-ZJ0500', '教师全景', 'platform', 'platform', '0');
INSERT INTO `deviceconfig` VALUES ('24', 'HL-ZJ0500', '学生全景', 'students', 'students', '0');
INSERT INTO `deviceconfig` VALUES ('25', 'HL-ZJ0500', '学生特写', 'student', 'student', '0');
INSERT INTO `deviceconfig` VALUES ('26', 'HL-ZJ0500', '教师特写', 'teacher', 'teacher', '0');
INSERT INTO `deviceconfig` VALUES ('27', 'HL-ZJ0500', '板书', 'blackboard', 'blackboard', '0');
INSERT INTO `deviceconfig` VALUES ('28', 'HL-ZJ0500', 'VGA', 'computer', 'computer', '0');
INSERT INTO `deviceconfig` VALUES ('29', 'TBOX-1234', '教师全景', 'platform', 'platform_sub', '0');
INSERT INTO `deviceconfig` VALUES ('30', 'TBOX-1234', '教师特写', 'teacher', 'teacher_sub', '1');
INSERT INTO `deviceconfig` VALUES ('31', 'TBOX-1234', '学生全景', 'students', 'students_sub', '0');
INSERT INTO `deviceconfig` VALUES ('32', 'TBOX-1234', '学生特写', 'student', 'student_sub', '1');
INSERT INTO `deviceconfig` VALUES ('33', 'TBOX-1234', '板书-1', 'blackboard', 'blackboard_sub', '1');
INSERT INTO `deviceconfig` VALUES ('34', 'TBOX-1234', '电影模式', 'movie', 'movie_sub', '0');
INSERT INTO `deviceconfig` VALUES ('35', 'TBOX-1234', 'HDMI/VGA', 'computer', 'computer_sub', '0');
INSERT INTO `deviceconfig` VALUES ('36', 'TBOX-1234', '测试一', '11', '11_1', '0');
INSERT INTO `deviceconfig` VALUES ('37', 'TBOX-1234', '测试二', '12', '12_1', '0');
INSERT INTO `deviceconfig` VALUES ('38', 'HL-ZF0500', '电影模式', 'MovieToken', 'MovieToken', '0');
INSERT INTO `deviceconfig` VALUES ('39', 'HL-ZF0500', '教师全景', 'FrontToken', 'FrontSubToken', '1');
INSERT INTO `deviceconfig` VALUES ('40', 'HL-ZF0500', '学生全景', 'StudentsToken', 'StudentsSubToken', '1');
INSERT INTO `deviceconfig` VALUES ('41', 'HL-ZF0500', '教师特写', 'TearcherToken', 'TearcherSubToken', '1');
INSERT INTO `deviceconfig` VALUES ('42', 'HL-ZF0500', '学生特写', 'StudentToken', 'StudentSubToken', '1');
INSERT INTO `deviceconfig` VALUES ('43', 'HL-ZF0500', 'VGA', 'ComputerSubToken', 'ComputerSubToken', '0');

-- ----------------------------
-- Table structure for `deviceuse`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `deviceuse` (
  `d_id` INT(11) NOT NULL AUTO_INCREMENT,
  `d_mac` VARCHAR(255) NOT NULL,
  `d_hostname` VARCHAR(255) DEFAULT NULL,
  `d_usertime` INT(11) DEFAULT NULL,
  `d_createtime` DATE DEFAULT NULL,
  PRIMARY KEY (`d_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of deviceuse
-- ----------------------------

-- ----------------------------
-- Table structure for `dspecification`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `dspecification` (
  `dspec_id` INT(11) NOT NULL AUTO_INCREMENT,
  `dspec_name` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dtype_id` INT(11) NOT NULL,
  `connect_param` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `record_type` INT(11) NOT NULL COMMENT '0 代表简易录播，1代表精品录播',
  PRIMARY KEY (`dspec_id`),
  KEY `dtype_id` (`dtype_id`),
  CONSTRAINT `dtype` FOREIGN KEY (`dtype_id`) REFERENCES `dtype` (`dtype_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of dspecification
-- ----------------------------
INSERT INTO `dspecification` VALUES ('1', '嵌入式录播主机', '1', 'sdsfs', '0');
INSERT INTO `dspecification` VALUES ('2', '精品录播主机', '1', '12345678', '1');
INSERT INTO `dspecification` VALUES ('3', '爱录客录播主机', '1', 'HL-ZJ0500', '1');
INSERT INTO `dspecification` VALUES ('4', '未知', '3', 'unknown', '0');
INSERT INTO `dspecification` VALUES ('5', '简易录播', '1', 'HL-ZF0400', '0');
INSERT INTO `dspecification` VALUES ('6', '鸿合教育大屏901', '4', '901.1', '2');
INSERT INTO `dspecification` VALUES ('7', '鸿合v69(7025EL)', '4', 'V69.5', '2');
INSERT INTO `dspecification` VALUES ('8', '鸿合2015901大屏', '4', '901.6', '2');
INSERT INTO `dspecification` VALUES ('9', '鸿合v69.8', '4', 'V69.8', '2');
INSERT INTO `dspecification` VALUES ('10', '鸿合918', '4', '918', '2');
INSERT INTO `dspecification` VALUES ('11', 'TBOX录播主机', '1', 'TBOX-1234', '0');
INSERT INTO `dspecification` VALUES ('12', '投影机', '5', 'HCP-A7', '3');
INSERT INTO `dspecification` VALUES ('13', '鸿合大屏828', '4', '828', '2');
INSERT INTO `dspecification` VALUES ('14', '鸿合大屏638', '4', '638', '2');
INSERT INTO `dspecification` VALUES ('15', '鸿合大屏V56', '4', 'V56', '2');
INSERT INTO `dspecification` VALUES ('16', '简易录播四机位', '1', 'HL-ZF0500', '0');
INSERT INTO `dspecification` VALUES ('17', '虚拟录播主机', '1', 'HHTVD-IPCAM', '1');
INSERT INTO `dspecification` VALUES ('18', '数字班牌', '6', 'DC-001', '4');
INSERT INTO `dspecification` VALUES ('19', '白板一体机', '7', 'HV-MC96', '5');
INSERT INTO `dspecification` VALUES ('20', '定位天线', '8', 'PA-001', '6');

-- ----------------------------
-- Table structure for `dspcauto`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `dspcauto` (
  `host_id` INT(10) NOT NULL AUTO_INCREMENT,
  `host_ip` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`host_id`)
) ENGINE=MYISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of dspcauto
-- ----------------------------

-- ----------------------------
-- Table structure for `dtype`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `dtype` (
  `dtype_id` INT(11) NOT NULL AUTO_INCREMENT,
  `dtype_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dtype_desc` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`dtype_id`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of dtype
-- ----------------------------
INSERT INTO `dtype` VALUES ('1', 'NVR', 'nvr');
INSERT INTO `dtype` VALUES ('2', 'IPC', 'ipc');
INSERT INTO `dtype` VALUES ('3', 'UNKNOWN', 'UNKNOWN');
INSERT INTO `dtype` VALUES ('4', 'HHTC', 'hht');
INSERT INTO `dtype` VALUES ('5', 'HTPR', 'htpr');
INSERT INTO `dtype` VALUES ('6', 'HHTWB', 'hhtwb');

-- ----------------------------
-- Table structure for `d_mode`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `d_mode` (
  `m_id` INT(11) NOT NULL AUTO_INCREMENT,
  `m_name` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '镜头场景',
  PRIMARY KEY (`m_id`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='场景模式表';

-- ----------------------------
-- Records of d_mode
-- ----------------------------
INSERT INTO `d_mode` VALUES ('1', '教师学生');
INSERT INTO `d_mode` VALUES ('2', '教师全景');
INSERT INTO `d_mode` VALUES ('3', '学生全景');
INSERT INTO `d_mode` VALUES ('4', '学生特写');
INSERT INTO `d_mode` VALUES ('5', '教师特写');
INSERT INTO `d_mode` VALUES ('6', '电影模式');

-- ----------------------------
-- Table structure for `eventfield`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `eventfield` (
  `field_id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '事件字段id',
  `field_belong` INT(11) NOT NULL COMMENT '字段所属，一般为对应event的id；如果字段公用的话，则\r\n\r\n为自定义值',
  `field_name` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '字段名称',
  `field_content` VARCHAR(50) DEFAULT NULL,
  `field_remark` VARCHAR(200) DEFAULT NULL COMMENT '字段备注',
  PRIMARY KEY (`field_id`)
) ENGINE=INNODB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eventfield
-- ----------------------------
INSERT INTO `eventfield` VALUES ('1', '29', 'Omode', '原模式', NULL);
INSERT INTO `eventfield` VALUES ('2', '29', 'Nmode', '新模式', NULL);
INSERT INTO `eventfield` VALUES ('3', '30', 'Recording', 'Recording', '0或1');
INSERT INTO `eventfield` VALUES ('4', '30', 'Streaming', 'Streaming', '0或1');
INSERT INTO `eventfield` VALUES ('5', '6', 'Duration', '当前录制或直播时间，如果同时启动时已录\r\n\r\n制时间为准', '');
INSERT INTO `eventfield` VALUES ('6', '7', 'avaliable space', '剩余存储空间', NULL);
INSERT INTO `eventfield` VALUES ('7', '7', 'Percentage', '剩余存储空间百分比', NULL);
INSERT INTO `eventfield` VALUES ('8', '8', 'Time', '剩余录制时间', '秒');
INSERT INTO `eventfield` VALUES ('9', '3', 'Recording', 'Recording', '0或1');
INSERT INTO `eventfield` VALUES ('10', '3', 'Streaming', 'Streaming', '0或1');
INSERT INTO `eventfield` VALUES ('11', '3', 'Duration', '当前录制或直播时间，如果同时启动时已\r\n\r\n录制时间为准', NULL);
INSERT INTO `eventfield` VALUES ('12', '4', 'Recording', 'Recording', '0或1');
INSERT INTO `eventfield` VALUES ('13', '4', 'Streaming', 'Streaming', '0或1');
INSERT INTO `eventfield` VALUES ('14', '4', 'Duration', '当前录制或直播时间，如果同时启动时已\r\n\r\n录制时间为准', NULL);
INSERT INTO `eventfield` VALUES ('15', '31', 'Duration', '当前录制或直播时间，如果同时启动时已\r\n\r\n录制时间为准', NULL);
INSERT INTO `eventfield` VALUES ('16', '31', 'Recording', 'Recording', '0或1');
INSERT INTO `eventfield` VALUES ('17', '31', 'Streaming', 'Streaming', '0或1');
INSERT INTO `eventfield` VALUES ('18', '16', 'Path', 'NAS路径', NULL);
INSERT INTO `eventfield` VALUES ('19', '16', 'User', 'NAS用户', NULL);
INSERT INTO `eventfield` VALUES ('20', '16', 'PW', 'NAS密码', NULL);
INSERT INTO `eventfield` VALUES ('21', '17', 'Path', 'NAS路径', NULL);
INSERT INTO `eventfield` VALUES ('22', '17', 'User', 'NAS用户', NULL);
INSERT INTO `eventfield` VALUES ('23', '17', 'PW', 'NAS密码', NULL);
INSERT INTO `eventfield` VALUES ('24', '18', 'Path', 'NAS路径', NULL);
INSERT INTO `eventfield` VALUES ('25', '18', 'User', 'NAS用户', NULL);
INSERT INTO `eventfield` VALUES ('26', '18', 'PW', 'NAS密码', NULL);
INSERT INTO `eventfield` VALUES ('27', '19', 'Path', 'NAS路径', NULL);
INSERT INTO `eventfield` VALUES ('28', '19', 'User', 'NAS用户', NULL);
INSERT INTO `eventfield` VALUES ('29', '19', 'PW', 'NAS密码', NULL);
INSERT INTO `eventfield` VALUES ('30', '21', 'Ouser', '原用户名', NULL);
INSERT INTO `eventfield` VALUES ('31', '21', 'OPW', '原密码', NULL);
INSERT INTO `eventfield` VALUES ('32', '21', 'Nuser', '新用户名', NULL);
INSERT INTO `eventfield` VALUES ('33', '21', 'NPW', '新密码', NULL);
INSERT INTO `eventfield` VALUES ('34', '20', 'OIP', '原IP', NULL);
INSERT INTO `eventfield` VALUES ('35', '20', 'Onetmask', '原子网掩码', NULL);
INSERT INTO `eventfield` VALUES ('36', '20', 'Ogateway', '原网关', NULL);
INSERT INTO `eventfield` VALUES ('37', '20', 'NIP', '新IP', NULL);
INSERT INTO `eventfield` VALUES ('38', '20', 'Nnetmask', '新子网掩码', NULL);
INSERT INTO `eventfield` VALUES ('39', '20', 'Ngeteway', '新网关', NULL);
INSERT INTO `eventfield` VALUES ('40', '22', 'Otime', '原时间', NULL);
INSERT INTO `eventfield` VALUES ('41', '22', 'Ntime', '新时间', NULL);
INSERT INTO `eventfield` VALUES ('42', '25', 'Oversion', '原版本号', NULL);
INSERT INTO `eventfield` VALUES ('43', '25', 'Nversion', '新版本号', NULL);
INSERT INTO `eventfield` VALUES ('44', '28', 'stop record', '停止录像', NULL);
INSERT INTO `eventfield` VALUES ('45', '28', 'stop live', '停止直播', NULL);
INSERT INTO `eventfield` VALUES ('46', '28', 'movie mode', '电影模式', NULL);
INSERT INTO `eventfield` VALUES ('47', '33', 'NASPath', 'NAS上文件夹存储路径', '');

-- ----------------------------
-- Table structure for `ftpsetting`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `ftpsetting` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ftp_addr` VARCHAR(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ftp地址',
  `ftp_port` INT(4) DEFAULT NULL COMMENT 'ftp端口号',
  `ftp_user` VARCHAR(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ftp连接用户名',
  `ftp_pass` VARCHAR(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ftp密码',
  `ftp_remark` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of ftpsetting
-- ----------------------------

-- ----------------------------
-- Table structure for `group2host`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `group2host` (
  `g2h_id` INT(11) NOT NULL AUTO_INCREMENT,
  `group_id` INT(11) DEFAULT NULL,
  `host_id` INT(11) DEFAULT NULL,
  PRIMARY KEY (`g2h_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `group2host_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `hostgroup` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for `group2user`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `group2user` (
  `u2g` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `group_id` INT(11) NOT NULL,
  PRIMARY KEY (`u2g`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `group2user_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `hostgroup` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- ----------------------------
-- Table structure for `host2news`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `host2news` (
  `h2n_id` INT(11) NOT NULL AUTO_INCREMENT,
  `host_id` INT(11) DEFAULT NULL COMMENT '主机id',
  `n_id` INT(11) DEFAULT NULL COMMENT '消息id',
  `host_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` INT(11) DEFAULT NULL COMMENT '发送状态，0失败，1成功，2删除，3删除未发送或者失败',
  PRIMARY KEY (`h2n_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- ----------------------------
-- Table structure for `hostevent`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `hostevent` (
  `event_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '事件id',
  `event_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '事件触发key',
  `event_content` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '事件触发',
  `event_type` VARCHAR(20) DEFAULT NULL COMMENT '事件类别',
  `event_topic` VARCHAR(20) DEFAULT 'HREC' COMMENT 'Topic',
  `event_desc` VARCHAR(200) DEFAULT NULL COMMENT '事件描述',
  `event_remark` VARCHAR(200) DEFAULT NULL COMMENT '字段定义',
  PRIMARY KEY (`event_id`)
) ENGINE=INNODB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hostevent
-- ----------------------------
INSERT INTO `hostevent` VALUES ('9', 'error_ipc_notwork', 'IPC未启动', 'ERROR', 'HREC', '给IPC\r\n\r\n上电后，60秒内未搜索到IPC。', '录 播主机内部该设备的ID');
INSERT INTO `hostevent` VALUES ('10', 'error_ipc_new', '新添加IPC', 'ERROR', 'HREC', '给IPC上\r\n\r\n电后，60秒内搜索到未配置过的IPC。', '录播主机内部该设备的ID');
INSERT INTO `hostevent` VALUES ('11', 'error_ipc_config', 'IPC配置错误', 'ERROR', 'HREC', '查\r\n\r\n询到的IPC配置参数不符合录播要求。', '录播主机内部该设备的ID');
INSERT INTO `hostevent` VALUES ('12', 'error_ipc_lost', 'IPC连接失败', 'ERROR', 'HREC', '无法\r\n\r\n打开IPC的视频流。', '录播主机内部该 设备的ID');
INSERT INTO `hostevent` VALUES ('13', 'error_ipc_nodata', 'IPC断线', 'ERROR', 'HREC', '传输IPC\r\n\r\n视频流工程中，有1秒未收到数据。', '录播主机内部该设备的ID');
INSERT INTO `hostevent` VALUES ('14', 'error_disk_detect', '硬盘无法识别', 'ERROR', 'HREC', '\r\n\r\n无法检测到硬盘设备。', '录播主机内 部该设备的ID');
INSERT INTO `hostevent` VALUES ('15', 'error_disk_write', '硬盘写入错误', 'ERROR', 'HREC', '无\r\n\r\n法向硬盘写入数据。', '录播主机内部 该设备的ID');
INSERT INTO `hostevent` VALUES ('16', 'error_nas_connect', 'NAS连接错误', 'ERROR', 'HREC', '无\r\n\r\n法连接NAS服务器。', 'Path：NAS路径 User：NAS用户 PW：NAS密码');
INSERT INTO `hostevent` VALUES ('17', 'error_nas_dir', 'NAS创建目录错误', 'ERROR', 'HREC', '无\r\n\r\n法在NAS服务器上创建目录。', 'Path ：NAS路径 User：NAS用户 PW：NAS密码');
INSERT INTO `hostevent` VALUES ('18', 'error_nas_file', 'NAS创建文件错误', 'ERROR', 'HREC', '\r\n\r\n无法在NAS服务器上创建文件。', 'Path ：NAS路径 User：NAS用户 PW：NAS密码');
INSERT INTO `hostevent` VALUES ('19', 'error_nas_write', 'NAS写入文件错误', 'ERROR', 'HREC', '\r\n\r\n无法在NAS服务器上写入数据。', 'Path：NAS路径 User：NAS用户 PW：NAS密码');
INSERT INTO `hostevent` VALUES ('20', 'operation_net_change', '修改网络参数', 'OPERATION', 'HREC', '修改了录播主机的网络参数。', 'OIP： 原IP Onetmask：原子网掩码 Ogateway：原网关 \r\nNIP\r\n\r\n：新IP Nnetmask：新子网掩码 Ngeteway：新网关');
INSERT INTO `hostevent` VALUES ('21', 'operation_user_modify', '修改或添加用户名密码', 'OPERATION', 'HREC', NULL, 'Ouser：原用户名 OPW： 原密码 Nuser：新用户名 NPW：新密码;如果新建用\r\n\r\n户则Ouser、OPW为空');
INSERT INTO `hostevent` VALUES ('22', 'operation_time_change', '修改系统时间', 'NOTICE', 'HREC', '修改了录播主机的系统时间。', 'Otime： 原时间 Ntime：新时间');
INSERT INTO `hostevent` VALUES ('23', 'operation_restore_factory', '恢复默认参数', 'ERROR', 'HREC', '进行了恢复出厂设置操作。', NULL);
INSERT INTO `hostevent` VALUES ('24', 'operation_restart_system', '系统重启动', 'NOTICE', 'HREC', '进行了系统重启动操作。', NULL);
INSERT INTO `hostevent` VALUES ('25', 'operation_update_fireware', '固件升级', 'OPERATION', 'HREC', '进行了固件升级操作。', 'Oversion： 原版本号 Nversion：新版本号');
INSERT INTO `hostevent` VALUES ('26', 'operation_update_ipcconfig', '重新配置IPC', 'OPERATION', 'HREC', '对IPC进行了重新配置。', NULL);
INSERT INTO `hostevent` VALUES ('27', 'operation_delete_log', '删除日志', 'OPERATION', 'HREC', '删除了日志。', NULL);
INSERT INTO `hostevent` VALUES ('28', 'CurrentState', '测试设备状态', 'OPERATION', 'HREC', '测\r\n\r\n试设备状态', NULL);
INSERT INTO `hostevent` VALUES ('29', 'event_direct_modechange', '导播模式切换', NULL, 'HREC', '在自动，后台，手动之间切换导播模式。', 'Omode：原模式 Nmode：新模式');
INSERT INTO `hostevent` VALUES ('30', 'event_direct_start', '录制/直播开始', NULL, 'HREC', NULL, 'Recording：0或1 Streaming：0或1 ');
INSERT INTO `hostevent` VALUES ('31', 'event_direct_stop', '', NULL, 'HREC', NULL, 'Recording\r\n\r\n：0或1 Streaming：0或1 Duration：当 前录制或直播时间，如果同时启动时已录制时间为准');
INSERT INTO `hostevent` VALUES ('32', 'event_disk_avaliable', '硬盘剩余空间', NULL, 'HREC', NULL, NULL);
INSERT INTO `hostevent` VALUES ('33', 'event_recording_finished', '视频存储数据回传', '', 'HREC', '一次录制完成之后，不管是电影模式还是 电影加资源模式，产生几个视频，只发送一条事件通\r\n\r\n知', 'NASPath:NAS路径 Duration:录制时长');

-- ----------------------------
-- Table structure for `hostgroup`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `hostgroup` (
  `group_id` INT(11) NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for `layoutwindow`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `layoutwindow` (
  `name` VARCHAR(45) NOT NULL,
  `layoutId` VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=MYISAM DEFAULT CHARSET=ucs2 COMMENT='板式配置';

-- ----------------------------
-- Records of layoutwindow
-- ----------------------------
INSERT INTO `layoutwindow` VALUES ('multiScreenTeacherStudent0', 'HRECLayout1');
INSERT INTO `layoutwindow` VALUES ('multiScreenTeacherStudent1', 'HRECLayout2');
INSERT INTO `layoutwindow` VALUES ('multiScreenTeacherStudentVGA1', 'HRECLayout4');
INSERT INTO `layoutwindow` VALUES ('multiScreenTeacherStudentVGA2', 'HRECLayout5');
INSERT INTO `layoutwindow` VALUES ('multiScreenTeacherStudent2', 'HRECLayout3');

-- ----------------------------
-- Table structure for `liveplan`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `liveplan` (
  `liveplan_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '直播id',
  `liveplan_section` INT(11) DEFAULT NULL COMMENT '上课节次',
  `liveplan_host` INT(11) DEFAULT NULL COMMENT '主机id',
  `liveplan_date` DATE DEFAULT NULL COMMENT '日期',
  `liveplan_week` TINYINT(4) DEFAULT NULL COMMENT '星期几 1-7为星期一至星期日',
  `liveplan_channel` VARCHAR(255) DEFAULT NULL COMMENT '直播资源，电影及各通道，以逗号分隔',
  `liveplan_sessionid` VARCHAR(255) DEFAULT NULL COMMENT '媒体服务器唯一标识',
  `liveplan_roomid` VARCHAR(255) DEFAULT NULL COMMENT '对应资源平台直播间',
  `liveplan_autoclose` TINYINT(4) DEFAULT NULL COMMENT '直播结束是否自动关闭直播间 0-否  1-是',
  `liveplan_ext` VARCHAR(255) DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`liveplan_id`),
  UNIQUE KEY `unique_index` (`liveplan_section`,`liveplan_host`,`liveplan_date`,`liveplan_week`) USING BTREE
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of liveplan
-- ----------------------------


-- ----------------------------
-- Table structure for `logtype`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `logtype` (
  `logtype_id` INT(11) NOT NULL AUTO_INCREMENT,
  `log_type` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_name` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`logtype_id`)
) ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of logtype
-- ----------------------------
INSERT INTO `logtype` VALUES ('1', 'SYSTEM', '系统日志');
INSERT INTO `logtype` VALUES ('2', 'MESSAGE', '消息日志');
INSERT INTO `logtype` VALUES ('3', 'PLAN', '定时计划日志');
INSERT INTO `logtype` VALUES ('4', 'DCONTROL', '设备控制日志');
INSERT INTO `logtype` VALUES ('5', 'LOGIN', '登录日志');
INSERT INTO `logtype` VALUES ('6', 'ERROR', '严重告警');
INSERT INTO `logtype` VALUES ('7', 'OPERATION', '次要告警');
INSERT INTO `logtype` VALUES ('8', 'ALARM', '告警信息');
INSERT INTO `logtype` VALUES ('9', 'NOTICE', '一般告警');

-- ----------------------------
-- Table structure for `monitor`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `monitor` (
  `m_id` INT(11) NOT NULL AUTO_INCREMENT,
  `m_mac` VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  `m_devicename` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_softname` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `m_usetime` INT(11) DEFAULT NULL,
  `m_createtime` DATE DEFAULT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for `nas2host`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `nas2host` (
  `n2h_id` INT(11) NOT NULL AUTO_INCREMENT,
  `host_id` INT(11) DEFAULT NULL,
  `nas_id` INT(11) DEFAULT NULL,
  PRIMARY KEY (`n2h_id`),
  KEY `nas2host_host_idx` (`host_id`),
  KEY `nas2host_nas_idx` (`nas_id`),
  CONSTRAINT `nas2host_nas` FOREIGN KEY (`nas_id`) REFERENCES `setting_nas` (`nas_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `news` (
  `n_id` INT(11) NOT NULL AUTO_INCREMENT,
  `n_cont` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `n_begintime` DATE DEFAULT NULL,
  `n_endtime` DATE DEFAULT NULL,
  `uid` INT(11) DEFAULT NULL,
  `username` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `n_showtype` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '消息显示方式',
  `n_fontsize` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '字号',
  `n_fontcolor` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '字体颜色',
  `n_timeline` DATE DEFAULT NULL COMMENT '发布时间',
  `n_loop` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '信息循环类型；day-每天,week-周,month-月',
  `n_start` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '开始循环时间',
  `n_finish` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '循环结束时间',
  `n_week` INT(2) DEFAULT NULL COMMENT '固定周几执行',
  `n_month` INT(2) DEFAULT NULL COMMENT '按月循环，固定几号',
  `n_font` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '字体（SONG：宋体；BLACK：黑体）',
  `n_type` INT(11) DEFAULT NULL COMMENT '0为普通消息 ，1为富文 本消息 ',
  `n_status` INT(11) DEFAULT NULL COMMENT '-1为删除1为发布0为未发布',
  `n_devicetype` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for `onlinetime`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `onlinetime` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `ip` VARCHAR(255) DEFAULT NULL,
  `mac` VARCHAR(255) DEFAULT NULL,
  `opentime` INT(15) DEFAULT NULL,
  `closetime` INT(15) DEFAULT NULL,
  `type` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of onlinetime
-- ----------------------------

-- ----------------------------
-- Table structure for `policy`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `policy` (
  `p_id` INT(11) NOT NULL AUTO_INCREMENT,
  `p_type` INT(11) DEFAULT NULL COMMENT '策略类型，0关机，1开机',
  `p_loop` INT(11) DEFAULT NULL COMMENT '是否循环 0单次，1循环',
  `p_looptype` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '循环类别 week,day,month',
  `p_date` INT(11) DEFAULT NULL COMMENT '每月几号 ',
  `p_week` INT(11) DEFAULT NULL COMMENT '每周几 1-7 7为周日',
  `p_time` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '时间点 08:00',
  `p_singletime` DATETIME DEFAULT NULL COMMENT '单次计划的日期及时间',
  `p_uid` INT(11) DEFAULT NULL,
  `p_createtime` DATETIME DEFAULT NULL,
  `p_username` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `p_devicetype` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of policy
-- ----------------------------

-- ----------------------------
-- Table structure for `policy2host`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `policy2host` (
  `p2h_id` INT(11) NOT NULL AUTO_INCREMENT,
  `host_id` INT(11) DEFAULT NULL,
  `p_id` INT(11) DEFAULT NULL,
  `host_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` INT(11) DEFAULT NULL COMMENT '0失败，1成功',
  PRIMARY KEY (`p2h_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of policy2host
-- ----------------------------

-- ----------------------------
-- Table structure for `programme`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `programme` (
  `p_id` INT(11) NOT NULL AUTO_INCREMENT,
  `p_singnal` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `p_type` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '策略类型，0关机，1开机',
  `p_loop` INT(11) DEFAULT NULL COMMENT '是否循环 0单次，1循环',
  `p_looptype` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '循环类别 week,day,month',
  `p_date` INT(11) DEFAULT NULL COMMENT '每月几号 ',
  `p_week` INT(11) DEFAULT NULL COMMENT '每周几 1-7 7为周日',
  `p_time` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '时间点 08:00',
  `p_singletime` DATETIME DEFAULT NULL COMMENT '单次计划的日期及时间',
  `p_uid` INT(11) DEFAULT NULL,
  `p_username` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `p_createtime` DATETIME DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of programme
-- ----------------------------

-- ----------------------------
-- Table structure for `programme2host`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `programme2host` (
  `p2h_id` INT(11) NOT NULL AUTO_INCREMENT,
  `host_id` INT(11) DEFAULT NULL,
  `p_id` INT(11) DEFAULT NULL,
  `host_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` INT(11) DEFAULT NULL COMMENT '0失败，1成功',
  PRIMARY KEY (`p2h_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of programme2host
-- ----------------------------

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `resource` (
  `res_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `res_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资源名称(通常为时间)',
  `host_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '班级名称(nvr名称)',
  `host_ip` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '设备Ip',
  `res_title` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题',
  `res_url` VARCHAR(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资源http地址',
  `res_path` VARCHAR(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资源路径',
  `res_resolution` VARCHAR(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '分辨率',
  `res_class` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '第几节课',
  `res_grade` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '年级',
  `res_subject` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '科目',
  `res_course` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '主讲课程',
  `res_speaker` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `res_school` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学校',
  `res_size` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文件大小',
  `res_updatetime` DATETIME DEFAULT NULL COMMENT '最后一次修改时间',
  `res_status` INT(2) DEFAULT '0' COMMENT '资源状态，默认为0（正常使用）',
  `res_downloads` INT(8) DEFAULT '0' COMMENT '下载次数',
  `res_visits` INT(8) DEFAULT '0' COMMENT '浏览次数（访问量）',
  `res_thumb` VARCHAR(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '缩略图地址',
  PRIMARY KEY (`res_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of resource
-- ----------------------------

-- ----------------------------
-- Table structure for `ringbell`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `ringbell` (
  `r_id` INT(11) NOT NULL AUTO_INCREMENT,
  `r_loop` INT(11) DEFAULT NULL COMMENT '是否循环 0单次，1循环',
  `r_looptype` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '循环类别 week,day,month',
  `r_date` INT(11) DEFAULT NULL COMMENT '每月几号 ',
  `r_week` INT(11) DEFAULT NULL COMMENT '每周几 1-7 7为周日',
  `r_time` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '时间点 08:00',
  `r_singletime` DATETIME DEFAULT NULL COMMENT '单次计划的日期及时间',
  `r_uid` INT(11) DEFAULT NULL,
  `r_username` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT '',
  `r_createtime` DATETIME DEFAULT NULL,
  `r_type` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='定时打铃计划';

-- ----------------------------
-- Records of ringbell
-- ----------------------------

-- ----------------------------
-- Table structure for `ringbell2host`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `ringbell2host` (
  `r2hid` INT(11) NOT NULL AUTO_INCREMENT,
  `r_id` INT(11) DEFAULT NULL,
  `h_id` INT(11) DEFAULT NULL,
  `h_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `r_status` INT(11) DEFAULT NULL COMMENT '0失败，1成功',
  PRIMARY KEY (`r2hid`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of ringbell2host
-- ----------------------------

-- ----------------------------
-- Table structure for `setting`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `setting` (
  `id` INT(11) NOT NULL,
  `recordinfo_resolution` INT(11) DEFAULT NULL COMMENT '录像信息分辨率',
  `recordinfo_bit_rate` INT(11) DEFAULT NULL,
  `nvr_username` VARCHAR(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nvr_password` VARCHAR(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `live_addr` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `curriculum_type` INT(11) DEFAULT NULL COMMENT '课表类型 1-课程总表，2-以班为单位的周课表',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for `setting_logo`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `setting_logo` (
  `logo_id` INT(11) NOT NULL AUTO_INCREMENT,
  `logo_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `logo_using` INT(11) DEFAULT NULL COMMENT '0-不再使用；1-正在使用',
  `logo_site` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`logo_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for `setting_nas`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `setting_nas` (
  `nas_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nas_rootpath` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'nas根目录',
  `nas_username` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `nas_password` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`nas_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for `signalplan`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `signalplan` (
  `s_id` INT(11) NOT NULL AUTO_INCREMENT,
  `s_signal` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '信号源',
  `s_signal_code` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '信号源命令码',
  `s_loop` INT(11) DEFAULT NULL COMMENT '是否循环 0单次，1循环',
  `s_looptype` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '循环类别 week,day,month',
  `s_date` INT(11) DEFAULT NULL COMMENT '每月几号 ',
  `s_week` INT(11) DEFAULT NULL COMMENT '每周几 1-7 7为周日',
  `s_time` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '时间点 08:00',
  `s_singletime` DATETIME DEFAULT NULL COMMENT '单次计划的日期及时间',
  `s_uid` INT(11) NOT NULL,
  `s_username` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT '',
  `s_createtime` DATETIME DEFAULT NULL,
  `s_type` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='定时切换信号源计划';

-- ----------------------------
-- Table structure for `signalplan2host`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `signalplan2host` (
  `s2hid` INT(11) NOT NULL AUTO_INCREMENT,
  `s_id` INT(11) DEFAULT NULL,
  `h_id` INT(11) DEFAULT NULL,
  `h_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `s_status` INT(11) DEFAULT NULL COMMENT '0失败，1成功',
  PRIMARY KEY (`s2hid`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for `spec2command`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `spec2command` (
  `t2c_id` INT(11) NOT NULL AUTO_INCREMENT,
  `dspec_id` INT(11) DEFAULT NULL,
  `cmd_id` INT(11) DEFAULT NULL,
  `cmd_word` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cmd_param` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cmd_return` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cmd_flag` VARCHAR(2) COLLATE utf8_unicode_ci DEFAULT '10' COMMENT '命令标识：10-host;01-device;11-both;',
  PRIMARY KEY (`t2c_id`),
  KEY `cmd_id` (`cmd_id`),
  KEY `dspec_id` (`dspec_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of spec2command
-- ----------------------------

-- ----------------------------
-- Table structure for `status`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `status` (
  `status_id` INT(11) NOT NULL,
  `status_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cn_desc` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `en_desc` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES ('1', '', '成功', 'Success');
INSERT INTO `status` VALUES ('2', 'Config Conflict', '分组已经存在', 'Group already exist');
INSERT INTO `status` VALUES ('3', 'Config Conflict', '主机已经存在', 'Host already exist');
INSERT INTO `status` VALUES ('4', 'Config Conflict', '设备已经存在', 'Device already exist');
INSERT INTO `status` VALUES ('5', 'Config Conflict', '用户已经存在', 'User already exist');
INSERT INTO `status` VALUES ('6', 'Config Conflict', '直播已经存在', 'LiveVideo already exist');
INSERT INTO `status` VALUES ('7', 'Config Conflict', '录播已经存在', 'Recording already exist');
INSERT INTO `status` VALUES ('8', 'Config Conflict', '关系已经存在', 'Relation already exist');
INSERT INTO `status` VALUES ('9', 'Login Confilct', '用户已经登录', 'User already login');
INSERT INTO `status` VALUES ('10', 'Login Confilct', '主机已经登录', 'Host already login');
INSERT INTO `status` VALUES ('11', 'Not Exist', '分组不存在', 'Group not exist');
INSERT INTO `status` VALUES ('12', 'Not Exist', '主机不存在', 'Host not exist');
INSERT INTO `status` VALUES ('13', 'Not Exist', '用户不存在', 'User not exist');
INSERT INTO `status` VALUES ('14', 'Not Exist', '设备不存在', 'Device not exist');
INSERT INTO `status` VALUES ('15', 'Not Exist', '直播不存在', 'LiveVideo not exist');
INSERT INTO `status` VALUES ('16', 'Not Exist', '录播不存在', 'Recording not exist');
INSERT INTO `status` VALUES ('17', 'Not Exist', '文件不存在', 'File not exist');
INSERT INTO `status` VALUES ('18', 'Not Allowed', '超越授权', 'Beyond Authority');
INSERT INTO `status` VALUES ('19', 'Not Allowed', '支持该命令', 'Not Supported');
INSERT INTO `status` VALUES ('20', 'Not Allowed', '系统权限不足', 'System not permite');

-- ----------------------------
-- Table structure for `syslog`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `syslog` (
  `log_id` INT(11) NOT NULL AUTO_INCREMENT,
  `log_user` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_content` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_host` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `log_type` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '日志类型',
  `log_time` INT(11) DEFAULT NULL COMMENT '日志时间',
  `log_ip` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '设备ip',
  PRIMARY KEY (`log_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of syslog
-- ----------------------------


-- ----------------------------
-- Table structure for `timeplan`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `timeplan` (
   `timeplan_id` INT(11) NOT NULL AUTO_INCREMENT,
  `section_id` INT(11) DEFAULT NULL COMMENT '上课节次id',
  `host_id` INT(11) DEFAULT NULL COMMENT '主机id',
  `timeplan_date` DATE DEFAULT NULL COMMENT '日期',
  `timeplan_week` TINYINT(4) DEFAULT NULL COMMENT '星期几 1-7为星期至周日',
  `timeplan_turnoff` TINYINT(4) DEFAULT NULL COMMENT '多长时间后关机',
  `timeplan_autooff` TINYINT(4) DEFAULT NULL COMMENT '是否自动关机',
  `timeplan_turnon` TINYINT(4) DEFAULT NULL COMMENT '计划时间开始前多长时间开机',
  `timeplan_channel` VARCHAR(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '录像计划录制资源通道',
  PRIMARY KEY (`timeplan_id`),
  KEY `unique_index` (`section_id`,`host_id`,`timeplan_date`,`timeplan_week`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for `touchscreen`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `touchscreen` (
  `t_id` INT(11) NOT NULL AUTO_INCREMENT,
  `t_type` INT(11) DEFAULT NULL,
  `t_loop` INT(255) DEFAULT NULL,
  `t_looptype` VARCHAR(255) DEFAULT NULL,
  `t_date` INT(255) DEFAULT NULL,
  `t_week` INT(255) DEFAULT NULL,
  `t_time` VARCHAR(255) DEFAULT NULL,
  `t_singletime` DATETIME DEFAULT NULL,
  `t_uid` INT(11) DEFAULT NULL,
  `t_createtime` DATETIME DEFAULT NULL,
  `t_uname` VARCHAR(255) DEFAULT NULL,
  `t_devicetype` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of touchscreen
-- ----------------------------

-- ----------------------------
-- Table structure for `touchscreen2host`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `touchscreen2host` (
  `t2h_id` INT(11) NOT NULL AUTO_INCREMENT,
  `host_id` INT(11) DEFAULT NULL,
  `t_id` INT(11) DEFAULT NULL,
  `host_name` VARCHAR(255) DEFAULT NULL,
  `status` INT(11) DEFAULT NULL,
  PRIMARY KEY (`t2h_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of touchscreen2host
-- ----------------------------

-- ----------------------------
-- Table structure for `t_resource`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_resource` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `length` INT(11) NOT NULL,
  `name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `size` INT(11) DEFAULT NULL,
  `path` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `duration` INT(11) DEFAULT NULL,
  `format` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `resourceIndex` TINYBLOB,
  `width` INT(11) NOT NULL,
  `creator` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `updator` VARCHAR(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `creatorName` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上传的用户名',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `t_resource_catalog`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_resource_catalog` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type` INT(11) NOT NULL COMMENT '0为文件夹，1video 2doc，3xls，4img，5pdf 6ppt 7swf',
  `duration` INT(11) DEFAULT NULL,
  `format` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` INT(11) DEFAULT NULL,
  `virtual` VARCHAR(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `fid` INT(11) DEFAULT NULL,
  `resourceIndex_id` INT(11) DEFAULT NULL,
  `resourceCatalog_id` INT(11) DEFAULT NULL,
  `folder` BIT(1) DEFAULT NULL,
  `deep` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `edit` BIT(1) DEFAULT NULL,
  `subitems` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `layer` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `islock` BIT(1) DEFAULT NULL,
  `md5` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `delFlag` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4038B553F58F16C` (`resourceCatalog_id`),
  KEY `FK4038B55355F98D93` (`resourceIndex_id`),
  CONSTRAINT `t_resource_catalog_ibfk_1` FOREIGN KEY (`resourceIndex_id`) REFERENCES `t_resource_index` (`id`),
  CONSTRAINT `t_resource_catalog_ibfk_2` FOREIGN KEY (`resourceCatalog_id`) REFERENCES `t_resource_index` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resource_catalog
-- ----------------------------
INSERT INTO `t_resource_catalog` VALUES ('1', '公共资源', '0', NULL, NULL, NULL, '/', '1', NULL, NULL, NULL, '', NULL, '', '', NULL, '', '0');
INSERT INTO `t_resource_catalog` VALUES ('2', '节目导入相关资源', '0', NULL, NULL, '2', '/root/', '1', NULL, NULL, NULL, '', NULL, '', '', NULL, '', '0');
INSERT INTO `t_resource_catalog` VALUES ('3', '我的资源', '0', NULL, NULL, '2', '/root/', '1', NULL, NULL, NULL, '', NULL, '', '', NULL, '', '0');

-- ----------------------------
-- Table structure for `setting_initialization`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `setting_initialization` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `channel_name` VARCHAR(255) DEFAULT NULL,
  `lock_status` VARCHAR(255) DEFAULT NULL,
  `devicetype` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of setting_initialization
-- ----------------------------
INSERT INTO `setting_initialization` VALUES ('1', '内置电脑', 'ScreenUnlock', 'hhtc');

-- ----------------------------
-- Table structure for `t_resource_index`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_resource_index` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `t_name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t_type` INT(11) DEFAULT NULL,
  `t_path` VARCHAR(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t_result` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t_desc` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t_version` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t_status` INT(11) DEFAULT NULL,
  `resouceStatus_id` INT(11) DEFAULT NULL,
  `t_convert` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `t_situation` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `resource_id` INT(11) DEFAULT NULL,
  `t_md5` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9FFC848C3158BCE1` (`resource_id`),
  KEY `FK9FFC848CD5919A67` (`resouceStatus_id`),
  CONSTRAINT `t_resource_index_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`),
  CONSTRAINT `t_resource_index_ibfk_2` FOREIGN KEY (`resouceStatus_id`) REFERENCES `t_resource_status` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resource_index
-- ----------------------------

-- ----------------------------
-- Table structure for `t_resource_status`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `t_resource_status` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `resourceIndex` TINYBLOB,
  `uploadtime` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `uploader` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `uploadstatus` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '1上传完成2正在转换3转换完成4转换错误5非法格式',
  `updater` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `checker` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `checktime` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `formatstatus` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `checkstatus` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `updatetime` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resource_status
-- ----------------------------

-- ----------------------------
-- Table structure for `record_name_setting`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `record_name_setting` (
  `record_name_id` INT(11) NOT NULL AUTO_INCREMENT,
  `classroom_name` BIT(1) DEFAULT NULL,
  `subject_name` BIT(1) DEFAULT NULL,
  `teacher_name` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`record_name_id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*Table structure for table `temp2host` */

-- ----------------------------
-- Table structure for `temp2host`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `temp2host` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `temp_id` INT(11) NOT NULL,
  `host_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8f9hgpngjltct9q6k94vm1qvk` (`host_id`),
  KEY `id` (`id`),
  KEY `FK_69wxxnb3xl8sgh1p9jnbu4e8j` (`temp_id`),
  CONSTRAINT `FK_69wxxnb3xl8sgh1p9jnbu4e8j` FOREIGN KEY (`temp_id`) REFERENCES `temporaryplan` (`temporaryplan_id`)
) ENGINE=INNODB AUTO_INCREMENT=290 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `temporaryplan`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `temporaryplan` (
  `temporaryplan_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `time_end` VARCHAR(255) DEFAULT NULL,
  `time_start` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`temporaryplan_id`)
) ENGINE=INNODB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `video`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `video` (
  `video_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '视频id',
  `video_name` VARCHAR(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '视频名称',
  `video_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `video_duration` INT(11) DEFAULT NULL COMMENT '视频时长，以秒为单位',
  `video_downloads` INT(8) DEFAULT '0' COMMENT '下载次数',
  `video_visits` INT(8) DEFAULT '0' COMMENT '预览次数',
  `video_size` VARCHAR(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `video_status` INT(2) DEFAULT '0' COMMENT '视频文件状态（0有效，1无效）',
  `video_upload` INT(2) DEFAULT '0' COMMENT '是否上传，0 未上传，1上传中，9上传完成',
  `video_code` VARCHAR(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '编码方式',
  `video_resolution` VARCHAR(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '视频文件分辨率',
  `video_url` VARCHAR(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '视频网络地址',
  `video_path` VARCHAR(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '视频地址',
  `video_thumb` VARCHAR(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '缩略图地址',
  `res_id` INT(11) NOT NULL COMMENT '所属文件夹id',
  PRIMARY KEY (`video_id`),
  KEY `所属文件夹id` (`res_id`),
  CONSTRAINT `video_ibfk_1` FOREIGN KEY (`res_id`) REFERENCES `resource` (`res_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DELIMITER ;;
