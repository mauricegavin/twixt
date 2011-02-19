package twixtPackage;

/**
 * The Test class can be used in any other class for testing purposes
 * <p>
 * Use it in places where you do not want to the messages to be printed in the final game.<br>
 * Example of use:<br>
 * <code>
 * 	//<br>
 *	// Initialise Test Module<br>
 *	//<br>
 *	Test test = new Test(true);<br>
 *	...<br>
 *	...<br>
 *	...<br>
 * 	if(test.getDebugModeOn() == true){<br>
 * 		...<br>
 *		// Some messages<br>
 *		...<br>
 *	}<br>
 * </code>
 * See more: Used in Board class<br>
 * @author Maurice
 */
public class Test {

	private Boolean debugmode;
	
	Test()
	{
		debugmode = false;
	}
	
	Test(Boolean state)
	{
		debugmode = state;
	}
	
	public Boolean getDebugModeOn()
	{
		return debugmode;
	}
}
