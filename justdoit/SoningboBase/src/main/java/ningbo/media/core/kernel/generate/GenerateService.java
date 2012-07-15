package ningbo.media.core.kernel.generate;


import java.util.List;

import ningbo.media.core.kernel.db.TableInfoClient;
import ningbo.media.core.kernel.model.TableInfo;

/**
 * 实体生成工具类.
 * <br/>
 *
 */
public class GenerateService implements IGenerateService {


    public boolean generateFile(String path) {
        List<TableInfo> tableInfoList = TableInfoClient.instance.showTables();
        for (TableInfo tableInfo : tableInfoList) {
            if (!BuilderEntity.generateCode(tableInfo, path)) {
                throw new RuntimeException("创建实体类出错");
            }
            if (!BuildMapper.createMybatis(tableInfo, path)) {
                throw new RuntimeException("创建Mybatis配置文件出错");
            }
        }
        return true;
    }


    public boolean generateEntityCode(String codeFilePath) {
        List<TableInfo> tableInfoList = TableInfoClient.instance.showTables();
        for (TableInfo tableInfo : tableInfoList) {
            if (!BuilderEntity.generateCode(tableInfo, codeFilePath)) {
                throw new RuntimeException("创建实体类出错");
            }
        }
        return true;
    }

    public boolean generateEntityCode(String tableName, String codeFilePath) {
        TableInfo tableInfo = TableInfoClient.instance.showColumns(tableName);
        return BuilderEntity.generateCode(tableInfo, codeFilePath);
    }

    public boolean generateMybatisXml(String xmlFilePath) {
        List<TableInfo> tableInfoList = TableInfoClient.instance.showTables();
        for (TableInfo tableInfo : tableInfoList) {
            if (!BuildMapper.createMybatis(tableInfo, xmlFilePath)) {
                throw new RuntimeException("创建Mybatis配置文件出错");
            }
        }
        return true;
    }


}
