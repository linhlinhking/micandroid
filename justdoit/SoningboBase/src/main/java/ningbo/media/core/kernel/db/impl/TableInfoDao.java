package ningbo.media.core.kernel.db.impl;

import java.util.List;

import ningbo.media.core.kernel.db.pool.ConnectionPool;
import ningbo.media.core.kernel.model.TableInfo;
import ningbo.media.core.kernel.util.ResourceUtils;

public abstract class TableInfoDao {
    /**
     * 数据库连接池
     */
    public static final ConnectionPool DB_POOL = new ConnectionPool(ResourceUtils.getInstance().getJdbc());


    /**
     * 查询当前连接的库中，包含哪些表的信息。
     *
     * @return 数据库中的表 <br/>
     *         如果返回<code>null</code>表示查询失败
     */
    public abstract List<TableInfo> showTables();

    /**
     * 查询给定表的所有的字段信息.
     *
     * @param tableName 数据库表名称
     * @return 给定的表的所有字段属性 <br/>
     *         如果范围<code>null</code>表示查询失败
     */
    public abstract TableInfo showColumns(String tableName);
}