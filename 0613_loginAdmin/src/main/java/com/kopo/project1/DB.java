package com.kopo.project1;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteConfig;

public class DB {
	private Connection connection;
	
	private String databaseUrl; 

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void open() {
		try {
			String dbFileName = "c:/kopo/project1.sqlite";
			SQLiteConfig config = new SQLiteConfig();
			databaseUrl = "jdbc:sqlite:/" + dbFileName;
			this.connection = DriverManager.getConnection(databaseUrl, config.toProperties());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isTableExists(String tableName) {
		this.open();
		boolean exists = false;
		String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "';";
		try (Statement statement = this.connection.createStatement();
			 ResultSet rs = statement.executeQuery(query)) {
			if (rs.next()) {
				exists = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return exists;
	}

	public void createTable() {
		this.open();
		String query = "CREATE TABLE user (idx INTEGER PRIMARY KEY AUTOINCREMENT, user_type TEXT, id TEXT, pwd TEXT, name TEXT, phone TEXT, address TEXT, created TEXT, last_updated TEXT);";
		try {
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}
	
    public static String sha512(String input) {
        try {
            // Using SHA-512 hash algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert byte array to hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm is not available.", e);
        }
    }
	
	public void insertData(User user) {
		this.open();
		String query = "INSERT INTO user (user_type, id, pwd, name, phone, address, created, last_updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, "guest");
			statement.setString(2, user.id);
			user.pwd = sha512(user.pwd);
			statement.setString(3, user.pwd);
			statement.setString(4, user.name);
			statement.setString(5, user.phone);
			statement.setString(6, user.address);
			String now = (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new java.util.Date());
			statement.setString(7, now);
			statement.setString(8, now);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}
	
	public ArrayList<User> selectAll() {
		this.open();
		ArrayList<User> data = new ArrayList<>();
		try {
			String query = "SELECT * FROM user";
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				int idx = result.getInt("idx");
				String userType = result.getString("user_type");
				String id = result.getString("id");
				String pwd = result.getString("pwd");
				String name = result.getString("name");
				String phone = result.getString("phone");
				String address = result.getString("address");
				String created = result.getString("created");
				String lastUpdated = result.getString("last_updated");
				data.add(new User(idx, userType, id, pwd, name, phone, address, created, lastUpdated));
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return data;
	}

	public User login(User user) {
		this.open();
		User returnData = new User();
		try {
			String query = "SELECT * FROM user WHERE id=? AND pwd=?";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, user.id);
			user.pwd = sha512(user.pwd);
			statement.setString(2, user.pwd);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				int idx = result.getInt("idx");
				String userType = result.getString("user_type");
				String id = result.getString("id");
				String pwd = result.getString("pwd");
				String name = result.getString("name");
				String phone = result.getString("phone");
				String address = result.getString("address");
				String created = result.getString("created");
				String lastUpdated = result.getString("last_updated");
				returnData = new User(idx, userType, id, pwd, name, phone, address, created, lastUpdated);
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return returnData;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			this.open();
			String query = "SELECT * FROM user";
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				try {
					int idx = result.getInt("idx");
					String userType = result.getString("user_type");
					String id = result.getString("id");
					String pwd = result.getString("pwd");
					String name = result.getString("name");
					String phone = result.getString("phone");
					String address = result.getString("address");
					String created = result.getString("created");
					String lastUpdated = result.getString("last_updated");
					users.add(new User(idx, userType, id, pwd, name, phone, address, created, lastUpdated));
				} catch (Exception e) {
					System.out.println("Error processing user data: " + e.getMessage());
					continue;
				}
			}
			statement.close();
		} catch (Exception e) {
			System.out.println("Error getting all users: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				this.close();
			} catch (Exception e) {
				System.out.println("Error closing connection: " + e.getMessage());
			}
		}
		return users;
	}

	public void createAdminAccount() {
		try {
			this.open();
			String query = "INSERT INTO user (user_type, id, pwd, name, phone, address, created, last_updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, "admin");
			statement.setString(2, "admin");
			statement.setString(3, sha512("admin1234"));
			statement.setString(4, "관리자");
			statement.setString(5, "010-0000-0000");
			statement.setString(6, "서울시");
			String now = (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new java.util.Date());
			statement.setString(7, now);
			statement.setString(8, now);
			statement.execute();
			statement.close();
			System.out.println("Admin account created successfully");
		} catch (Exception e) {
			System.out.println("Error creating admin account: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				this.close();
			} catch (Exception e) {
				System.out.println("Error closing connection: " + e.getMessage());
			}
		}
	}

}
