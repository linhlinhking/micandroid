package ningbo.media.core.kernel.db.impl;

import ningbo.media.core.kernel.db.DBType;

public class TableInfoFactory {

	private TableInfoFactory() {

    }

    public static TableInfoDao builderTableInfoDao(DBType dbms) {
        switch (dbms) {
            case MYSQL:
                return Inner.MYSQLI_TABLE_INFO_DAO;
            case ORACLE:
                break;
        }
        return null;
    }


    private static final class Inner {
        private static final TableInfoDao MYSQLI_TABLE_INFO_DAO = new MysqlTableInfoDao();
    }
}
