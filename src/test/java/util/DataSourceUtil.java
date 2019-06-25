package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;


public class DataSourceUtil {
	public List<Map<String, String>> readSqlAsList(String query) {

		List<Map<String, String>> results = new ArrayList<Map<String, String>>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {

			conn = getDbConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);

			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columnSize = rsMetaData.getColumnCount();
			String[] columnNames = new String[columnSize];
			for (int i = 1; i <= columnSize; i++) {
				columnNames[i - 1] = rsMetaData.getColumnName(i);
			}

			while (rs.next()) {
				Map<String, String> result = new HashMap<String, String>();
				for (int i = 0; i < columnNames.length; i++) {
					result.put(columnNames[i], rs.getString(columnNames[i]));
				}

				results.add(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return results;
	}

	private Properties readProperies(String file) throws IOException {
		InputStream inputStream = getClass().getResourceAsStream(file);
		Properties props = new Properties();
		props.load(inputStream);

		inputStream.close();
		return props;
	}

	public Connection getDbConnection() {
		Connection conn = null;
		try {
			String file = "/db.properties";
			Properties prop = readProperies(file);
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://" + prop.getProperty("server") + ":"
							+ prop.getProperty("port") + "/"
							+ prop.getProperty("db") + "",
					prop.getProperty("user"), prop.getProperty("pass"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static List<String> sqlTextList(List<Map<String, String>> sqlList) {
		List<String> nameList = new ArrayList<String>();
		for (int i = 0; i < sqlList.size(); i++) {
			System.out.println((i + 1) + "-) " + sqlList.get(i).values());
			// System.out.println((i+1)+"-) "+sqlList.get(i).remove("NAME"));
			nameList.addAll(sqlList.get(i).values());
			System.out.println(nameList.get(i));
		}
		return nameList;
	}

	public static void printSQLList(List<Map<String, String>> sqlList) {
		for (Map<String, String> map : sqlList) {
			for (String key : map.keySet()) {
				System.out.println(key + " -> " + map.get(key));
			}
		}

	}

	/**
	 * Reads the properties of DB and connects to it. Sends the query and fills
	 * the map from results. Keys are equivalent for column names on DB. Values
	 * are the rows of the column in a List. Column names are not ordered but
	 * rows are ordered.
	 * 
	 * 
	 * @param query
	 *            command to DB for requesting data
	 * @return result of SQL Query as map
	 */
	public Map<String, List<String>> readSqlAsMap(String query) {

		Map<String, List<String>> results = new HashMap<String, List<String>>();

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = getDbConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query);

			ResultSetMetaData rsMetaData = rs.getMetaData();
			int columnSize = rsMetaData.getColumnCount();
			String[] columnNames = new String[columnSize];
			for (int i = 1; i <= columnSize; i++) {
				columnNames[i - 1] = rsMetaData.getColumnName(i);
				List<String> rows = new LinkedList<String>();
				results.put(rsMetaData.getColumnName(i), rows);
			}

			while (rs.next()) {
				for (int i = 0; i < columnNames.length; i++) {
					List<String> rows = results.get(columnNames[i]);
					rows.add(rs.getString(columnNames[i]));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return results;
	}

	/**
	 * Gets the result map as parameter and returns the rows of the column. Also
	 * prints every column and row for debugging.
	 * 
	 * @param sqlResult
	 *            the result of query as Map
	 * @param columnName
	 *            the name of the contents column
	 * @return column "NAME"s rows
	 */
	public static List<String> getContentOfColumn(
			Map<String, List<String>> sqlResult, String columnName) {
		List<String> names = new LinkedList<String>();
		for (String column : sqlResult.keySet()) {
			if (column.equals(columnName))
				names.addAll(sqlResult.get(column));
		}
		return names;
	}

	/**
	 * Gets all columns of a row. Returns all values in list.
	 * 
	 * @param sqlResult
	 *            the result of query as Map
	 * @param index
	 *            index of the row
	 * @return rows
	 */
	public static List<String> getRow(Map<String, List<String>> sqlResult,
			int index) {
		List<String> row = new LinkedList<String>();
		for (String column : sqlResult.keySet()) {
			row.add(sqlResult.get(column).get(index));
		}
		return row;
	}

	/**
	 * Gets all columns names, prints and returns them in a list.
	 * 
	 * @param sqlResult
	 *            the result of query as Map
	 * @return columns
	 */
	public static List<String> getColumns(Map<String, List<String>> sqlResult) {
		List<String> columns = new LinkedList<String>();
		for (String column : sqlResult.keySet()) {
			columns.add(column);
			System.out.print(column + ", ");
		}
		return columns;
	}
	public boolean execute(String query){
		PreparedStatement preparedStatement;
		boolean result = false;
		try {
			Connection connection = getDbConnection();
			preparedStatement = connection.prepareStatement(query);
			result = preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}