package com.junolabs.usm.persistence.dao.mysql.support;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class MySQLUtils {

	//---------------------------------------------------------------------------------------------------------------------------
	
	/*
	 * Genera linea de insert. 
	 * Por ejemplo: "insert into users (FIRST_NAME, LAST_NAME, EMAIL, BIRTH_DATE) values (?,?,?,?)"
	 */
	public static String prepareInsert(String tableName, String... columnNames) {
		
		StringBuffer strInsert = new StringBuffer("insert into " + tableName + " (");
		StringBuffer strValues = new StringBuffer(" values (");
				
		for (int i = 0; i < columnNames.length; i++) {
			if (i == (columnNames.length - 1)){
				strInsert.append(columnNames[i] + ")");
				strValues.append("?)");
			}
			else{
				strInsert.append(columnNames[i] + ", ");
				strValues.append("?,");
			}
		}

		strInsert.append(strValues);
		return strInsert.toString();
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	
	/*
	 * Genera linea de update. 
	 * Por ejemplo: "update table_name set FIRST_NAME='Lea', LAST_NAME='Car', EMAIL='gmail', BIRTH_DATE='19820221' where ID=1"
	 */
	public static String prepareUpdate(String tableName, long id, String... columnNames) {
		
		StringBuffer strUpdate = new StringBuffer("update " + tableName + " set ");
				
		for (int i = 0; i < columnNames.length; i++) {
			if (i == (columnNames.length - 1)){
				strUpdate.append(columnNames[i] + "='?'");
			}
			else{
				strUpdate.append(columnNames[i] + "='?', ");
			}
		}

		strUpdate.append("where ID=" + id);
		return strUpdate.toString();
	}
	
	
//	public static String prepareUpdate(String tableName, HashMap<String, Object> mapColumnValue, String condition) {
//		
//		StringBuffer strUpdate = new StringBuffer("update " + tableName + " set ");
//		
//		boolean initial = true;
//		for (String key : mapColumnValue.keySet()) {
//			if (!initial){
//				initial = false;
//				strUpdate.append(", ");
//			}
//			
//			strUpdate.append(key + "='" + mapColumnValue.get(key).toString() + "'");
//		}
//		
//		strUpdate.append(" where " + condition);
//		
//		return strUpdate.toString();
//	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	
	/*
	 * Genera linea de delete. 
	 * Por ejemplo: "delete from table_name where ID=1"
	 */
	public static String prepareDelete(String tableName) {
		
		StringBuffer strDelete = new StringBuffer("delete from " + tableName + " where id=?");
		return strDelete.toString();
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------
	
	public static String getStringDate(Date date){
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		String s = df.format(date);
		return s;
	}
}
