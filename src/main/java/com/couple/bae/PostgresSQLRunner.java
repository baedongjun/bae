package com.couple.bae;

import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PostgresSQLRunner implements ApplicationRunner {
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try (Connection connection = dataSource.getConnection()){
			System.out.println(dataSource.getClass());
			System.out.println(connection.getMetaData().getURL());
			System.out.println(connection.getMetaData().getUserName());

			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE member(id VARCHAR(5) NOT NULL, mbrNo INTEGER NULL, name VARCHAR(100), PRIMARY KEY (id))";
			statement.executeUpdate(sql);
		}
		jdbcTemplate.execute("INSERT INTO member VALUES ('1', 123456, '배동준')");
	}
}
