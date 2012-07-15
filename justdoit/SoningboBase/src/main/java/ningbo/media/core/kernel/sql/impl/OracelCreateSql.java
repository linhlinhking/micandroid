package ningbo.media.core.kernel.sql.impl;

import ningbo.media.core.kernel.model.MybatisSqlModal;
import ningbo.media.core.kernel.model.SqlModal;
import ningbo.media.core.kernel.model.TableInfo;
import ningbo.media.core.kernel.sql.ICreateSqlFace;

public class OracelCreateSql extends CreateSql implements ICreateSqlFace {

	private OracelCreateSql() {
	}

	private static class Inner {
		private static ICreateSqlFace _oracle = new OracelCreateSql();
	}

	public static ICreateSqlFace getInstance() {
		return Inner._oracle;
	}

	@Override
	protected String pageParamSql() {
		return null;
	}

	@Override
	protected String getInsertPrimaryKey() {
		return null;
	}

	public SqlModal createSql(TableInfo tableInfo) {
		return null;
	}

	@Override
	protected String pageSelectSql(MybatisSqlModal mybatisSqlModal,
			String tableName) {
		return null;
	}
}
