package twixtPackage;

import java.util.Vector;

/**
 * Class RuleMaster, a class used to inquire whether or not certain moves are legal on the current board object.
 * @author Jack
 *
 */
public class RuleMaster {
	/**
	 * Contructor
	 * @param iBoard board to be tested on
	 */
	public Board mBoard;
	public RuleMaster(Board iBoard){
		mBoard = iBoard;
	}
	/**
	 * Returns true if a tower can be placed at the specified location
	 * @param x x position of tower
	 * @param y y position of tower
	 * @return a boolean, true if a tower can be placed here
	 */
	public boolean canPlaceTower(int x, int y){
		if(mBoard.getTower(x, y)!=null){
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
		System.out.println("Testing bridge move...");
		if((tower1!=null)&&(tower2!=null)&&(tower1.getPlayerId()==playerNumber)&&(tower2.getPlayerId()==playerNumber)&&(mBoard.getBridge(x1,y1,x2,y2)==null))//checks to make sure the towers exist and are of the correct player, and that a bridge does not already exist at this position
		{
			System.out.println("the towers exist and are of the correct player, and that a bridge does not already exist at this position");
			int dx = x1-x2;
			int dy = y1-y2;
			//Use dx and dy to work out the orientation of the bridge
			if(Math.abs(dx)==1&&Math.abs(dy)==2)//Bridge is vertically oriented, ie two spaces high and one across
			{
				System.out.println("Bridge is vertical");
				dy=dy/2;//use dy/2 for computation purposes of co-ordinates
				//now check for collisions in all the possible locations
				if(mBoard.getBridge(x2,y1+dy,x1,y1-dy)==null){//1
					//System.out.println("Test 1 failed -- Blocked by: "+(x2)+" "+(y1+dy)+" "+(x1)+" "+(y1-dy));
					if(mBoard.getBridge(x2-dx,y1,x1,y1-dy)==null){//2
						//System.out.println("Test 2 failed -- Blocked by: "+(x2-dx)+" "+(y1)+" "+(x1)+" "+(y1-dy));
						if(mBoard.getBridge(x2,y1,x1+dx,y1-dy)==null){//3
							//System.out.println("Test 3 failed -- Blocked by: "+(x2)+" "+(y1)+" "+(x1+dx)+" "+(y1-dy));
							if(mBoard.getBridge(x2,y1,x1,y2)==null){//4
								//System.out.println("Test 4 failed -- Blocked by: "+(x2)+" "+(y1)+" "+(x1)+" "+(y2));
								if(mBoard.getBridge(x2-dx,y2+dy,x1,y2)==null){//5
									//System.out.println("Test 5 failed -- Blocked by: "+(x2-dx)+" "+(y2+dy)+" "+(x1)+" "+(y2));
									if(mBoard.getBridge(x2,y2+dy,x1+dx,y1)==null){//6
										//System.out.println("Test 6 failed -- Blocked by: "+(x2)+" "+(y2+dy)+" "+(x1+dx)+" "+(y1));
										if(mBoard.getBridge(x2,y2+dy,x1+dx,y2)==null){//7
											//System.out.println("Test 7 failed -- Blocked by: "+(x2)+" "+(y2+dy)+" "+(x1+dx)+" "+(y2));
											if(mBoard.getBridge(x2,y2+dy,x1,y2-dy)==null){//8
												//System.out.println("Test 8 failed -- Blocked by: "+(x2)+" "+(y2+dy)+" "+(x1)+" "+(y2-dy));
												if(mBoard.getBridge(x2-dx,y2,x1,y2+dy)==null){//9
													//System.out.println("Test 9 failed -- Blocked by: "+(x2-dx)+" "+y2+" "+x1+" "+(y2+dy));
													return true;//no bridges are colliding with the proposed bridge and return true
												}else{
													System.out.println("Test 9 failed -- Blocked by: "+(x2-dx)+" "+y2+" "+x1+" "+(y2+dy));
												}
											}else{
												System.out.println("Test 8 failed -- Blocked by: "+(x2)+" "+(y2+dy)+" "+(x1)+" "+(y2-dy));
											}	
										}else{
											System.out.println("Test 7 failed -- Blocked by: "+(x2)+" "+(y2+dy)+" "+(x1+dx)+" "+(y2));
										}	
									}else{
										System.out.println("Test 6 failed -- Blocked by: "+(x2)+" "+(y2+dy)+" "+(x1+dx)+" "+(y1));
									}	
								}else{
									System.out.println("Test 5 failed -- Blocked by: "+(x2-dx)+" "+(y2+dy)+" "+(x1)+" "+(y2));
								}		
							}else{
								System.out.println("Test 4 failed -- Blocked by: "+(x2)+" "+(y1)+" "+(x1)+" "+(y2));
							}	
						}else{
							System.out.println("Test 3 failed -- Blocked by: "+(x2)+" "+(y1)+" "+(x1+dx)+" "+(y1-dy));
						}	
					}else{
						System.out.println("Test 2 failed -- Blocked by: "+(x2-dx)+" "+(y1)+" "+(x1)+" "+(y1-dy));
					}
				}else{
					System.out.println("Test 1 failed -- Blocked by: "+(x2)+" "+(y1+dy)+" "+(x1)+" "+(y1-dy));
				}
			}else if(Math.abs(dx)==2&&Math.abs(dy)==1)//Bridge is horizontally oriented, ie is two spaces across and one high
			{
				System.out.println("Bridge is horizontal");
				dx=dx/2;//use dx/2 for computation purposes of co-ordinates
				//now check for collisions in all the possible locations
				if(mBoard.getBridge(x2,y1+dy,x2+dx,y2)==null){//1
					if(mBoard.getBridge(x2+dx,y1+dy,x1,y2)==null){//2
						if(mBoard.getBridge(x1,y1+dy,x2+dx,y2)==null){//3
							if(mBoard.getBridge(x2-dx,y1,x2+dx,y2)==null){//4
								if(mBoard.getBridge(x2,y1,x2+dx,y2-dy)==null){//5
									if(mBoard.getBridge(x2,y1,x1,y2)==null){//6
										if(mBoard.getBridge(x2+dx,y1,x2,y2-dy)==null){//7
											if(mBoard.getBridge(x2+dx,y1,x1,y2-dy)==null){//8
												if(mBoard.getBridge(x2+dx,y1,x1+dx,y2)==null){//9	
													return true;//no bridges are colliding with the proposed bridge and return true
												}else{
													System.out.println("Test 9 failed:"+(x2+dx)+" "+(y1)+" "+(x1+dx)+" "+(y2));
												}
											}else{
												System.out.println("Test 8 failed :"+(x2+dx)+" "+(y1)+" "+(x1)+" "+(y2-dy));
											}
										}else{
											System.out.println("Test 7 failed :"+(x2+dx)+" "+(y1)+" "+(x2)+" "+(y2-dy));
										}
									}else{
										System.out.println("Test 6 failed :"+(x2)+" "+(y1)+" "+(x1)+" "+(y2));
									}
								}else{
									System.out.println("Test 5 failed :"+(x2)+" "+(y1)+" "+(x2+dx)+" "+(y2-dy));
								}
							}else{
								System.out.println("Test 4 failed :"+(x2-dx)+" "+(y1)+" "+(x2+dx)+" "+(y2));
							}
						}else{
							System.out.println("Test 3 failed :"+(x1)+" "+(y1+dy)+" "+(x2+dx)+" "+(y2));
						}
					}else{
						System.out.println("Test 2 failed:"+(x2+dx)+" "+(y1+dy)+" "+(x1)+" "+(y2));
					}
					System.out.println("Test 1 failed :"+(x2)+" "+(y1+dy)+" "+(x2+dx)+" "+(y2));
				}
			}//if neither of the above statements are true then the bridge is not a knights move across, and is not a legal move.
		}else{
			System.out.println("Bridge failed due to");
			if(tower1==null){
				System.out.println("tower 1 does not exist");
			}else if(tower1.getPlayerId()!=playerNumber){
				System.out.println("tower one is wrong colour");
			}
			if(tower2==null){
				System.out.println("tower 2 does not exist");
			}else if(tower2.getPlayerId()!=playerNumber){
				System.out.println("tower 2 is wrong colour");
			}
			if(mBoard.getBridge(x1,y1,x2,y2)!=null){
				System.out.println("a bridge is already placed here");
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
			tempTower=mBoard.getTower(i,24);
			if(tempTower!=null)
			{
			
			}
		}
		return 0;
	}
}
