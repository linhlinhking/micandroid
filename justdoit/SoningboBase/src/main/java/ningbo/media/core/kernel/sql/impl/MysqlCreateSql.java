package ningbo.media.core.kernel.sql.impl;

import ningbo.media.core.kernel.common.Constants;
import ningbo.media.core.kernel.model.MybatisSqlModal;
import ningbo.media.core.kernel.model.SqlModal;
import ningbo.media.core.kernel.model.TableInfo;
import ningbo.media.core.kernel.sql.ICreateSqlFace;

public class MysqlCreateSql extends CreateSql implements ICreateSqlFace {

	private MysqlCreateSql() {
	}

	/**
	 * 内部类，用来产生内部单例
	 */
	private static class Inner {
		private static ICreateSqlFace _mysql = new MysqlCreateSql();
	}

	/**
	 * 获取创建Mysql脚本的单例对象
	 * 
	 * @return 创建Mysql脚本的单例对象
	 */
	public static ICreateSqlFace getInstance() {
		return Inner._mysql;
	}

	public SqlModal createSql(TableInfo tableInfo) {
		SqlModal sql = new SqlModal();
		String tableName = tableInfo.getTableName().toUpperCase();
		MybatisSqlModal mybatisSqlModal = gengerateSql(tableInfo);
		sql.setInsertSql(generateInsertSql(mybatisSqlModal, tableName));
		sql.setDeleteSql(generateDeleteSql(mybatisSqlModal, tableName));
		sql.setSelectAllSql(pageSelectSql(mybatisSqlModal, tableName));
		sql.setSelectSql(generateSelectOneSql(mybatisSqlModal, tableName));
		sql.setUpdateSql(generateUpdateSql(mybatisSqlModal, tableName));
		sql.setCountQuerySql(generateCountSql(mybatisSqlModal, tableName));
		sql.setPageWhereSql(pageParamSql());
		return sql;
	}

	@Override
	protected String pageParamSql() {
		return "LIMIT #{start},#{end}";
	}

	@Override
	protected String pageSelectSql(MybatisSqlModal mybatisSqlModal,
			String tableName) {
		StringBuilder selectAllSql = new StringBuilder();
		selectAllSql.append(Constants.SELECT_KEY).append(Constants.WRAP_CHAR)
				.append(mybatisSqlModal.getSelectColumn())
				.append(Constants.WRAP_CHAR).append(Constants.BLANK_SIGN)
				.append(Constants.FROM_KEY).append(tableName);
		return selectAllSql.toString();
	}

	@Override
	protected String getInsertPrimaryKey() {
		return null;
	}
}
