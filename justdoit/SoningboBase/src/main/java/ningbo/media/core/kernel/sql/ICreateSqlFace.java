package ningbo.media.core.kernel.sql;

import ningbo.media.core.kernel.model.SqlModal;
import ningbo.media.core.kernel.model.TableInfo;

public interface ICreateSqlFace {

	/**
	 * 创建SQL脚本
	 * 
	 * @param tableInfo
	 *            数据库表信息。
	 * @return SQL对象信息
	 */
	SqlModal createSql(TableInfo tableInfo);
}
