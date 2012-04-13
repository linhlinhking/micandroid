/**
*	获取当前document的第一个table对象
*/
function f_getTblObj() {
	var objs = document.getElementsByTagName("TABLE");
	if (objs != null && objs.length > 0) {
		return objs[0];
	}

	return null;
}

/**
*	插入一个新的table
*/
function f_insertTable(tblContent ,tblobj) {
	var strTblId = f_createTblID();
	//alert(tblobj.width);

	var strContent = "<table id=\""+strTblId+"\" x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse:collapse;table-layout:fixed'>";
	strContent += tblContent + "</table>";

	document.writeln(strContent);

	var tblObj = document.getElementById(strTblId);

	//设置新table的style属性
	setTblStyle(tblobj ,tblObj);

	//alert(tblObj.style.height);

	return tblObj;
}

/**
*	设置新table的style属性
*/
function setTblStyle(oldTbl ,newTbl) {
	newTbl.style.width = oldTbl.style.width;
}

/**
*	获取随机数
*/
function f_random() {
	var tmpVal = Math.random() * 10000;
	var Val = new String(tmpVal).split(".");

	return Val[0];
}

/**
*	创建表格ID
*/
function f_createTblID() {
	var tblID = "rtbl_" + f_random();
	var tblObj = document.getElementById(tblID);

	var isHas = false;
	if (tblObj != null && tblObj.tagName == "TABLE") {
		isHas = true;
	}

	while (isHas == true) {
		tblID = "rtbl_" + f_random();
		tblObj = document.getElementById(tblID);

		if (tblObj != null && tblObj.tagName == "TABLE") {
			isHas = true;
		} else {
			isHas = false;
		}
	}

	return tblID;
}

/**
*	根据
*
*	@param	posi		当前行
*	@param	mutilNum	倍数
*	@param	stblObj		表格对象
*/
function f_setRowHeightByPos(posi ,mutilNum ,stblObj) {
	var trObj = f_getRow(stblObj ,posi);
	trObj.cells(0).height = trObj.cells(0).height * mutilNum;
}

/**
*/
function f_hiddenRowByPos(posi ,hiddenLineNum ,stblObj) {
	var trObj = null;
	for (var i = hiddenLineNum ;i > 0 ;i--)	{
		trObj = f_getRow(stblObj ,parseInt(posi) + i);
		trObj.style.display = "none";
	}
//	var trObj = f_getRow(stblObj ,posi);
//	trObj.cells(0).height = trObj.cells(0).height * mutilNum;
}

/**
*	根据当前字段填写的内容，设置当前行的行高。
*/
function f_setCellHeightByPos(posi ,cellVal ,colName ,stblObj) {
	if (columnDefine.length == null || columnDefine.length == 0) return ;

	var row = posi[0];
	var column = posi[1];
	var trObj = null;

	//alert("stblObj.id = " + stblObj.id);

	var ableCharNum = 0;			//每个单元格可以显示的字符数
	var charLength = cellVal.length;//
	var heightNum = 0;
	var firstTD = "" ,tdInnerHTML = "";
	var tdContent = null;
	for (var i = 0 ,n = columnDefine.length ;i < n  ;i++ ) {
		//alert("columnDefine[i][0] = " + columnDefine[i][0]);
		if (columnDefine[i][0] == colName) {
			ableCharNum = columnDefine[i][1] * columnDefine[i][2];

			//alert("ableCharNum = " + ableCharNum);
			//alert("charLength = " + charLength);

			if (ableCharNum < charLength) {
				//字符的长度超出单元格显示------------

				//计算要加多少行高
				heightNum = parseInt(charLength / ableCharNum) + 1;

				//alert("heigthNum = " + heightNum);

				trObj = f_getRow(stblObj ,row);
				if (trObj == null) {
					continue ;
				}
//				alert("trObj.cells(0).height = " + trObj.cells(0).height);
				//alert("trObj.outHTML = " + trObj.outerHTML);

				trObj.cells(0).height = trObj.cells(0).height * heightNum;
			}
		}
	}
}

/**
*	根据当前字段填写位置，将内容填写到相应的单元格中
*/
function f_setCellValueByPos(posi ,cellVal ,tblObj) {
	if (posi.length != 2) {
		return ;
	}

	if (tblObj == null || tblObj.tagName != "TABLE") {
		return ;
	}

	var row = posi[0];
	var column = posi[1];

	var trObj = f_getRow(tblObj ,row);
	if (trObj == null) {
		return ;
	}

	var tdObj = f_getCell(trObj ,column);

	if (tdObj == null) {
		return ;
	}

	//设置单元格的内容
	tdObj.innerText = cellVal;

	//alert(tblObj.style.width);
}

/**
*	获取当前操作行对象
*/
function f_getRow(tblObj ,rowIndex) {
	/*
	var trObjs = tblObj.rows;
	var rowspan = 0;
	var trobj = null ,retObj = null;
	for (var i = 0 ,n = trObjs.length ;i < n ;i++) {
		trobj = trObjs[i];
		rowspan += trobj.rowspan;
		if (rowspan >= rowIndex) {
			retObj = trobj;
			break;
		}
	}
	*/

	return tblObj.rows(rowIndex);
}

/**
*	获取当前操作单元格对象
*/
function f_getCell(trObj ,colIndex) {
	var tdObjs = trObj.cells;
	var colspan = 0;
	var tdobj = null ,retObj = null;
	for (var i = 0 ,n = tdObjs.length ;i < n ;i++) {
		tdobj = tdObjs[i];
		if (tdobj.tagName == "TD") {
			colspan = parseInt(colspan) + parseInt(tdobj.colSpan);

			if (colspan > colIndex) {
				retObj = tdobj;
				break;
			}
		}
	}

	return retObj;
}

/**
*	报表打印
*/
function f_window_print() {
	//alert(tblobj.style.height);
	//window.print();
	//alert('ok');
}