package buu.mypizza.models;

/**
 *
 * @author nazar
 */
public class Admin extends User{
    
    public Admin(String email, String password) {
        super(email, password);
    }
    
    public Admin(String[] fields){
        super(fields);
    }
    
}
