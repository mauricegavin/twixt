package twixtPackage;

import java.util.Vector;

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
		if(mBoard.getTower(x, y)){
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
	public boolean canPlaceBridge(int x1, int y1, int x2, int y2, int playerNumber){//not working yet
		Tower tower1=mBoard.getTower(x1,y1);
		Tower tower2=mBoard.getTower(x2,y2);
		if((tower1!=null)&&(tower2!=null)&&(tower1.getPlayerId==playerNumber)&&(tower1.getTower(x2,y2).getPlayerId==playerNumber)&&(mBoard.getBridge(x1,y1,x2,y2)==null))//checks to make sure the towers exist and are of the correct player, and that a bridge does not already exist at this position
		{
			int dx = x1-x2;
			int dy = y1-y2;
			//Use dx and dy to work out the orientation of the bridge
			if(Math.abs(dx)==2&&Math.abs(dy)==1)//Bridge is horizontally oriented, ie is two spaces across and one high
			{
				dy=dy/2;//use dy/2 for computation purposes of co-ordinates
				//now check for collisions in all the possible locations
				if(mBoard.getBridge(x2,y1+dy,x1,y1-dy)==null){//1
					if(mBoard.getBridge(x2-dx,y1,x1,y1-dy)==null){//2
						if(mBoard.getBridge(x2,y1,x1+dx,y1-dy)==null){//3
							if(mBoard.getBridge(x2,y1,x1,y2)==null){//4
								if(mBoard.getBridge(x2-dx,x1,y2)==null){//5
									if(mBoard.getBridge(x2,y2+dy,x1+dx,y1)==null){//6
										if(mBoard.getBridge(x2,y2+dy,x1,y2-dy)==null){//7
											if(mBoard.getBridge(x2,y2+dy,x1,y2-dy)==null){//8
												if(mBoard.getBridge(x2-dx,y2,x1,y2+dy)==null){//9
													return true;//no bridges are colliding with the proposed bridge and return true
												}	
											}	
										}	
									}	
								}	
							}	
						}	
					}
				}
			}else if(Math.abs(dx)==1&&Math.abs(dy)==2)//Bridge is vertically oriented, ie two spaces high and one across
			{
				dx=dx/2;//use dx/2 for computation purposes of co-ordinates
				//now check for collisions in all the possible locations
				if(mBoard.getBridge(x2,y1+dy,x2+dx,y2)==null){//1
					if(mBoard.getBridge(x2+dx,y1+dy,x1,y2)==null){//2
						if(mBoard.getBridge(x1,y1+dy,x1,y2)==null){//3
							if(mBoard.getBridge(x2-dx,y1,x2+dx,y2)==null){//4
								if(mBoard.getBridge(x2,y1,x2+dx,y2)==null){//5
									if(mBoard.getBridge(x2,y1,x1,y2)==null){//6
										if(mBoard.getBridge(x2+dx,y1,x2,y2-dy)==null){//7
											if(mBoard.getBridge(x2+dx,y1,x1,y2-dy)==null){//8
												if(mBoard.getBridge(x2+dx,y1,x1+dx,y2)==null){//9
													return true;//no bridges are colliding with the proposed bridge and return true
												}	
											}	
										}	
									}	
								}	
							}	
						}	
					}
				}
			}
		}
		return false;
	}
	/**
	 * Checks to see if the game is over. Returns 0 if it is not, 1 if player 1 has won and 2 if player 2 has won.
	 * @return an integer
	 */
	public int detectEnd(){//does nothing yet
		Vector verticalTowers = new Vector();
		Vector horizontalTowers = new Vector();
		Vector checkedVerticalBridges = new Vector();
		Vector checkedHorizontalBridges = new Vector();
		Tower tempTower;
		for(int i=0;i<24;i++){
			tempTower=mBoard.getTower(i,0);
			if(tempTower!=null)
			{
				
			}
			tempTower=mBoard.getTower(i,24)
			if(tempTower!=null)
			{
			
			}
		}
		return 0;
	}
}
