package com.jshop.service.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jshop.dao.impl.TableTDaoImpl;
import com.jshop.entity.TableT;
import com.jshop.service.TableTService;
@Service("tableTServiceImpl")
@Scope("prototype")
public class TableTServiceImpl implements TableTService {

	private TableTDaoImpl tableTDaoImpl;
	
	
	public TableTDaoImpl getTableTDaoImpl() {
		return tableTDaoImpl;
	}

	public void setTableTDaoImpl(TableTDaoImpl tableTDaoImpl) {
		this.tableTDaoImpl = tableTDaoImpl;
	}

	public int addTableT(TableT t) {
		return this.getTableTDaoImpl().addTableT(t);
	}

	public int countfindAllTableT() {
		return this.getTableTDaoImpl().countfindAllTableT();
	}

	public int delTableT(String[] strs) {
		return this.getTableTDaoImpl().delTableT(strs);
	}

	public List<TableT> findAllTableT() {
		return this.getTableTDaoImpl().findAllTableT();
	}

	public TableT findTableBytableid(String tableid) {
		return this.getTableTDaoImpl().findTableBytableid(tableid);
	}

	public List<TableT> sortAllTableT(int currentPage, int lineSize, String queryString) {
		return this.getTableTDaoImpl().sortAllTableT(currentPage, lineSize, queryString);
	}

	public void updateTableT(TableT t) {
		this.getTableTDaoImpl().updateTableT(t);
	}

	public int updateTableTtablestateBytableNo(String tableNumber, String tablestate) {
		return this.getTableTDaoImpl().updateTableTtablestateBytableNo(tableNumber, tablestate);
	}

}
