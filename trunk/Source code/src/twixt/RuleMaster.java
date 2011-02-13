package twixt;
/**
 * Class RuleMaster, a class used to inquire whether or not certain moves are legal on the current board object.
 * @author Jack
 *
 */
public class RuleMaster {
	public Board mBoard;
	/**
	 * Returns true if a tower can be placed at the specified location
	 * @param x x position of tower
	 * @param y y position of tower
	 * @return a boolean, true if a tower can be placed here
	 */
	public boolean canPlaceTower(int x, int y){
		if(mBoard.isTower(x, y)){
			return false;
		}else{
		return true;
		}
	}
	/**
	 * Returns true if a bridge can be placed at the current position
	 * @param x1 x position of the start of the bridge
	 * @param y1 y position of the start of the bridge
	 * @param x2 x position of the end of the bridge
	 * @param y2 y position of the end of the bridge
	 * @return a boolean, true if a bridge can be placed here
	 */
	public boolean canPlaceBridge(int x1, int y1, int x2, int y2){//not working yet
		
		
		if(mBoard.isTower(x, y))
		return false;
	}
	/**
	 * 
	 * @return
	 */
	public boolean detectEnd(){//does nothing yet
		return false;
	}
}
