package ningbo.media.core.kernel.db;

import java.util.List;

import ningbo.media.core.kernel.db.impl.TableInfoDao;
import ningbo.media.core.kernel.db.impl.TableInfoFactory;
import ningbo.media.core.kernel.model.TableInfo;

/**
 * 数据库调用对象单例，获取各种数据库类型参数获取具体的表以及列的信息.
 * <br/>
 *
 */
public enum TableInfoClient implements ITableInfoDao {
    instance;

    public List<TableInfo> showTables() {
        TableInfoDao infoDao = TableInfoFactory
                .builderTableInfoDao(TableInfoDao.DB_POOL.getDbms());
        return infoDao.showTables();
    }

    public TableInfo showColumns(String tableName) {
        TableInfoDao infoDao = TableInfoFactory
                .builderTableInfoDao(TableInfoDao.DB_POOL.getDbms());
        return infoDao.showColumns(tableName);
    }


}