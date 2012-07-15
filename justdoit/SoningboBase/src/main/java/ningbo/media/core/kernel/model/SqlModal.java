package ningbo.media.core.kernel.model;

public class SqlModal {

	private String insertSql;
	private String updateSql;
	private String deleteSql;
	private String selectAllSql;

	private String pageWhereSql;
	private String selectSql;
	private String countQuerySql;

	public String getPageWhereSql() {
		return pageWhereSql;
	}

	public void setPageWhereSql(String pageWhereSql) {
		this.pageWhereSql = pageWhereSql;
	}

	public String getCountQuerySql() {
		return countQuerySql;
	}

	public void setCountQuerySql(String countQuerySql) {
		this.countQuerySql = countQuerySql;
	}

	public String getInsertSql() {
		return insertSql;
	}

	public void setInsertSql(String insertSql) {
		this.insertSql = insertSql;
	}

	public String getUpdateSql() {
		return updateSql;
	}

	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}

	public String getDeleteSql() {
		return deleteSql;
	}

	public void setDeleteSql(String deleteSql) {
		this.deleteSql = deleteSql;
	}

	public String getSelectAllSql() {
		return selectAllSql;
	}

	public void setSelectAllSql(String selectAllSql) {
		this.selectAllSql = selectAllSql;
	}

	public String getSelectSql() {
		return selectSql;
	}

	public void setSelectSql(String selectSql) {
		this.selectSql = selectSql;
	}

}
