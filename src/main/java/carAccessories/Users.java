package carAccessories;


public class Users {
	
	public final String email;
	public final String password;
	public final String role;
	protected boolean isSignedIn;

	public Users(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.isSignedIn = false;
	}
	
	public boolean checkEmail(String email) {
		return this.email.equals(email);
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);		
	}
	

	public boolean signIn(String email, String password) {
		this.isSignedIn = checkEmail(email) && checkPassword(password);
		return this.isSignedIn;
	}
	
	public boolean checkRole(String role) {
		return this.role.equals(role);		
	}
	

	public boolean signOut() {
		this.isSignedIn = false;
		return this.isSignedIn;
	}
	
	public boolean isSignedIn() {
		return this.isSignedIn;
	}
	
	@Override
    public boolean equals(Object o) {
        if  (!(o instanceof Users))
            return false;
        Users other = (Users) o;
        return (this.email.equals(other.email) && this.password.equals(other.password) && this.role.equals(other.role));
    }
	
    @Override
  public int hashCode() {
   return 0;
    }


}
