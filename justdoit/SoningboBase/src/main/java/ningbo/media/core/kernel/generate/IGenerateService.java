package ningbo.media.core.kernel.generate;

/**
 * 代码生成操作接口.
 * <br/>
 *
 */
public interface IGenerateService {
	
    boolean generateFile(String path);
    /**
     * 根据当前的数据库创建代码。
     *
     * @param codeFilePath 代码文件存放文件夹地址
     * @return 操作成功返回<code>true</code>
     */
    boolean generateEntityCode(String codeFilePath);

    /**
     * 根据当前数据库连接，生成某个表的代码文件.
     *
     * @param tableName    数据库表名称
     * @param codeFilePath 代码文件存放文件夹地址。
     * @return 操作成功返回<code>true</code>
     */
    boolean generateEntityCode(String tableName, String codeFilePath);

    /**
     * 创建Mybatis的XML文件.
     *
     * @param xmlFilePath 存放地址
     * @return 操作成功返回<code>true</code>
     */
    boolean generateMybatisXml(String xmlFilePath);
}
