-- The custom functions here
INSERT INTO auth_menu (code,name,parent_code, sort_index, function_code,icon, url, params) VALUES ('MN_BUSSINESS_MNG','业务类管理',NULL,30000,'MN_BUSSINESS_MNG',NULL,'/bussiness',NULL);
INSERT INTO auth_menu (code,name,parent_code, sort_index, function_code,icon, url, params) VALUES ('MN_COMMENTLOG_MNG','业务评论详细日志管理','MN_BUSSINESS_MNG',31000,'MN_COMMENTLOG_MNG',NULL,'/commentlog','views/commentlog/index');
INSERT INTO auth_menu (code,name,parent_code, sort_index, function_code,icon, url, params) VALUES ('MN_TOPIC_MNG','业务论坛主题管理','MN_BUSSINESS_MNG',32000,'MN_TOPIC_MNG',NULL,'/topic','views/topic/index');