
#场景:查询用户上传的文件。
SELECT * FROM tb_module_file as m where 1=1 AND m.toolId IN(SELECT toolId FROM tb_tools as t INNER JOIN users as u WHERE u.id = 27 AND t.toolId = 2) AND m.typeId = 1

