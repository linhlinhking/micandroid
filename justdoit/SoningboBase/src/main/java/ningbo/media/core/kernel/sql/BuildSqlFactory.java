package ningbo.media.core.kernel.sql;

import ningbo.media.core.kernel.db.DBType;
import ningbo.media.core.kernel.sql.impl.MysqlCreateSql;
import ningbo.media.core.kernel.sql.impl.OracelCreateSql;

public class BuildSqlFactory {
	private BuildSqlFactory() {
	}

	/**
	 * 根据数据库类型取得对应产生sql脚本的实现
	 * 
	 * @param dbms
	 *            数据库类型
	 * @return 数据库对应类型的sql脚本
	 */
	public static ICreateSqlFace getDBMSBuilderSql(DBType dbms) {
		switch (dbms) {
		case MSSQL:
			break;
		case MYSQL:
			return MysqlCreateSql.getInstance();
		case ORACLE:
			return OracelCreateSql.getInstance();
		case DB2:
			break;
		default:
			break;
		}
		return null;
	}

}
