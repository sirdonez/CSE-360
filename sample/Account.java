package sample;


public class Account {

	private String username;
    private String password;
    private String email;
    private boolean isAdmin;
    private int creditCardNumber;

    //Constructor
    public Account(String username, String password, String email, boolean isAdmin) {

    	this.username = username;
    	this.password = password;
    	this.email = email;
    	this.isAdmin = isAdmin;

    }
    
    public void setCreditCardNumber(int creditCardNumber) {
    	
    	this.creditCardNumber = creditCardNumber;
    	
    }
    
    public void setUserName(String username) {
    	
    	this.username = username;
    	
    }

	public void setPassword(String password) {

		this.password = password;

	}

	public void setEmail(String email) {

		this.email = email;

	}
	
	public void setIsAdmin(boolean isAdmin) {

		this.isAdmin = isAdmin;

	}

    public int getCreditCardNumber() {
    	
    	return this.creditCardNumber;
    	
    }
    
    public boolean getAccountType() {
    	
    	return this.isAdmin;
    	
    }
    
    public String getUsername() {
    	
    	return this.username;
    	
    }
    
    public String getPassword() {
    	
    	return this.password;
    	
    }
    
    public String getEmail() {
    	
    	return this.email;
    	
    }
    
}
