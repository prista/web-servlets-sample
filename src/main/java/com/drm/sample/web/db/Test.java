package com.drm.sample.web.db;

import java.sql.*;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection c = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/pristadb", "postgres", "postgres");

		Statement statement = c.createStatement();
		ResultSet resultSet = printCurrentTable(statement);

		// execute update
		int updated = statement.executeUpdate("update car set year=2018 where year<2000");
		System.out.print("updated rows:" + updated);
		resultSet = printCurrentTable(statement);
		
		// execute delete
		int deleted = statement.executeUpdate("delete from car where year<2017");
		System.out.print("deleted rows:" + deleted);
		resultSet = printCurrentTable(statement);
		
		statement.executeUpdate("insert into car (model_id, engine_type, year) values(3,'gasoline', 2015)");
		resultSet = printCurrentTable(statement);
		
		resultSet.close();
		statement.close();
		c.close();

	}

	private static ResultSet printCurrentTable(Statement statement) throws SQLException {
		statement.executeQuery("select * from car");

		ResultSet resultSet = statement.getResultSet();

		boolean hasNext = resultSet.next();
		while (hasNext) {

			System.out.print(resultSet.getInt("id") + "|");
			System.out.print(resultSet.getInt("year") + "|");
			System.out.print(resultSet.getString("engine_type") + "|");
			System.out.print((Integer) resultSet.getObject("model_id") + "|");
			System.out.println();

			hasNext = resultSet.next();
		}
		return resultSet;
	}

}
