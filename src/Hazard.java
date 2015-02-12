/**
 * @author James DeVeuve & Tanner Koch
 * TA: Mike Collins & Ben Whitely
 * Hunt The Wumpus Iteration 2
 * 
 * The intent of this interface is to provide a method (causeHazard) that all board objects
 * which our hunter will encounter will need. Any board object besides the hunter himself 
 * will have a message it provides to visitors (except in the case of an empty room!)
 * which is implemented via this interface.
 */


public interface Hazard {
	
	public String causeHazard() ;

}
