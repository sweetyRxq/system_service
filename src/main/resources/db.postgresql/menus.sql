-- The default menus here
INSERT INTO auth_menu (code,name,parent_code, sort_index, function_code,icon, url, params) VALUES ('MN_BASE_MNG','基础信息',NULL,10000,'MN_BASE_MNG',NULL,'/sys',NULL);
INSERT INTO auth_menu (code,name,parent_code, sort_index, function_code,icon, url, params) VALUES ('MN_FUNC_MNG','功能管理','MN_BASE_MNG',12000,'MN_FUNC_MNG',NULL,'/sysfunction','views/sysfunction/index');
INSERT INTO auth_menu (code,name,parent_code, sort_index, function_code,icon, url, params) VALUES ('MN_MENU_MNG','菜单管理','MN_BASE_MNG',13000,'MN_MENU_MNG',NULL,'/sysmenu','views/sysmenu/index');
INSERT INTO auth_menu (code,name,parent_code, sort_index, function_code,icon, url, params) VALUES ('MN_ROLE_MNG','角色管理','MN_BASE_MNG',14000,'MN_ROLE_MNG',NULL,'/sysrole','views/sysrole/index');
INSERT INTO auth_menu (code,name,parent_code, sort_index, function_code,icon, url, params) VALUES ('MNG_ORG_MNG','部门管理','MN_BASE_MNG',15000,'MNG_ORG_MNG',NULL,'/sysorganization','views/sysorganization/index');
INSERT INTO auth_menu (code,name,parent_code, sort_index, function_code,icon, url, params) VALUES ('MN_USER_MNG','用户管理','MN_BASE_MNG',16000,'MN_USER_MNG',NULL,'/sysuser','views/sysuser/index');
INSERT INTO auth_menu (code,name,parent_code, sort_index, function_code,icon, url, params) VALUES ('MN_CCCFG_MNG','业务配置',NULL,20000,'MN_CCCFG_MNG',NULL,'/cfg',NULL);
INSERT INTO auth_menu (code,name,parent_code, sort_index, function_code,icon, url, params) VALUES ('MN_CHCD_MNG','智能合约管理','MN_CCCFG_MNG',21000,'MN_CHCD_MNG',NULL,'/cfgcc','views/cfgcc/index');
INSERT INTO auth_menu (code,name,parent_code, sort_index, function_code,icon, url, params) VALUES ('MN_CCENY_MNG','业务对象配置','MN_CCCFG_MNG',22000,'MN_CCENY_MNG',NULL,'/cfgentity','views/cfgentity/index');
-- The custom menus here