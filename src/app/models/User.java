package app.models;

public class User {
	private String name;
	private String username;
	private String email;
	private static  String tableName = "user";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static String getTableName() {
		return tableName;
	}
	public static void setTableName(String tableName) {
		User.tableName = tableName;
	}
	


}
