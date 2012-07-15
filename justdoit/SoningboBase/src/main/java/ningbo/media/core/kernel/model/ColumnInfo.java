package ningbo.media.core.kernel.model;

public class ColumnInfo {

	private String tableName;

	private String columnName;
	private boolean nullable;
	private String dataType;

	private String columnType;
	private String columnKey;
	private String columnComment;
	/** 是否为主键 */
	private boolean pk;
	/** 是否外健 */
	private boolean fk;

	public boolean isFk() {
		return fk;
	}

	public void setFk(boolean fk) {
		this.fk = fk;
	}

	public boolean isPk() {
		return pk;
	}

	public void setPri(boolean pk) {
		this.pk = pk;
	}

	@Override
	public String toString() {
		return "表:" + tableName + ":{" + "字段描述='" + columnComment + '\''
				+ ", 字段键值类型='" + columnKey + '\'' + ", 字段类型='" + columnType
				+ '\'' + ", 数据类型='" + dataType + '\'' + ", 是否可以为空=" + nullable
				+ ", 字段名称='" + columnName + '\'' + '}';
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullAble(boolean nullable) {
		this.nullable = nullable;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

}
