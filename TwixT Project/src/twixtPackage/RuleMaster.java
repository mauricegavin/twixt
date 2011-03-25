package twixtPackage;

import java.util.Vector;

/**
 * Class RuleMaster, a class used to inquire whether or not certain moves are legal on the current board object.
 * @author Jack
 *
 */
public class RuleMaster {
	Test test = new Test(false);
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
	//For now this method assumes p1 is always playing left to right, and p2 bottom to top//now attempting to switch so that p1 is up/down p2 is left right
	public boolean canPlaceTower(int x, int y, int playerID){
		if((playerID==2&&(y==0||y==23))||(playerID==1&&(x==0||x==23)))//the very top and bottom rows are reserved for the respective player, if the wrong player is trying to place a tower here return false; 
		{
			return false;
		}
		if((mBoard.getTower(x, y)!=null))
		{
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
	public boolean canPlaceBridge(int x1, int y1, int x2, int y2, int playerNumber){
		Tower tower1=mBoard.getTower(x1,y1);
		Tower tower2=mBoard.getTower(x2,y2);
		if(test.getDebugModeOn())System.out.println("Testing bridge move...");
		if((tower1!=null)&&(tower2!=null)&&(tower1.getPlayerId()==playerNumber)&&(tower2.getPlayerId()==playerNumber)&&(mBoard.getBridge(x1,y1,x2,y2)==null))//checks to make sure the towers exist and are of the correct player, and that a bridge does not already exist at this position
		{
			if(test.getDebugModeOn())System.out.println("the towers exist and are of the correct player, and that a bridge does not already exist at this position");
			int dx = x1-x2;
			int dy = y1-y2;
			//Use dx and dy to work out the orientation of the bridge
			if(Math.abs(dx)==1&&Math.abs(dy)==2)//Bridge is vertically oriented, ie two spaces high and one across
			{
				if(test.getDebugModeOn())System.out.println("Bridge is vertical");
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
													if(test.getDebugModeOn())System.out.println("Test 9 failed -- Blocked by: "+(x2-dx)+" "+y2+" "+x1+" "+(y2+dy));
												}
											}else{
												if(test.getDebugModeOn())System.out.println("Test 8 failed -- Blocked by: "+(x2)+" "+(y2+dy)+" "+(x1)+" "+(y2-dy));
											}	
										}else{
											if(test.getDebugModeOn())System.out.println("Test 7 failed -- Blocked by: "+(x2)+" "+(y2+dy)+" "+(x1+dx)+" "+(y2));
										}	
									}else{
										if(test.getDebugModeOn())System.out.println("Test 6 failed -- Blocked by: "+(x2)+" "+(y2+dy)+" "+(x1+dx)+" "+(y1));
									}	
								}else{
									if(test.getDebugModeOn())System.out.println("Test 5 failed -- Blocked by: "+(x2-dx)+" "+(y2+dy)+" "+(x1)+" "+(y2));
								}		
							}else{
								if(test.getDebugModeOn())System.out.println("Test 4 failed -- Blocked by: "+(x2)+" "+(y1)+" "+(x1)+" "+(y2));
							}	
						}else{
							if(test.getDebugModeOn())System.out.println("Test 3 failed -- Blocked by: "+(x2)+" "+(y1)+" "+(x1+dx)+" "+(y1-dy));
						}	
					}else{
						if(test.getDebugModeOn())System.out.println("Test 2 failed -- Blocked by: "+(x2-dx)+" "+(y1)+" "+(x1)+" "+(y1-dy));
					}
				}else{
					if(test.getDebugModeOn())System.out.println("Test 1 failed -- Blocked by: "+(x2)+" "+(y1+dy)+" "+(x1)+" "+(y1-dy));
				}
			}else if(Math.abs(dx)==2&&Math.abs(dy)==1)//Bridge is horizontally oriented, ie is two spaces across and one high
			{
				if(test.getDebugModeOn())System.out.println("Bridge is horizontal");
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
													if(test.getDebugModeOn())	System.out.println("Test 9 failed:"+(x2+dx)+" "+(y1)+" "+(x1+dx)+" "+(y2));
												}
											}else{
												if(test.getDebugModeOn())	System.out.println("Test 8 failed :"+(x2+dx)+" "+(y1)+" "+(x1)+" "+(y2-dy));
											}
										}else{
											if(test.getDebugModeOn())	System.out.println("Test 7 failed :"+(x2+dx)+" "+(y1)+" "+(x2)+" "+(y2-dy));
										}
									}else{
										if(test.getDebugModeOn())	System.out.println("Test 6 failed :"+(x2)+" "+(y1)+" "+(x1)+" "+(y2));
									}
								}else{
									if(test.getDebugModeOn())	System.out.println("Test 5 failed :"+(x2)+" "+(y1)+" "+(x2+dx)+" "+(y2-dy));
								}
							}else{
								if(test.getDebugModeOn())	System.out.println("Test 4 failed :"+(x2-dx)+" "+(y1)+" "+(x2+dx)+" "+(y2));
							}
						}else{
							if(test.getDebugModeOn())	System.out.println("Test 3 failed :"+(x1)+" "+(y1+dy)+" "+(x2+dx)+" "+(y2));
						}
					}else{
						if(test.getDebugModeOn())	System.out.println("Test 2 failed:"+(x2+dx)+" "+(y1+dy)+" "+(x1)+" "+(y2));
					}
					if(test.getDebugModeOn())System.out.println("Test 1 failed :"+(x2)+" "+(y1+dy)+" "+(x2+dx)+" "+(y2));
				}
			}//if neither of the above statements are true then the bridge is not a knights move across, and is not a legal move.
		}else{
			if(test.getDebugModeOn()){
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
		}
		return false;
	}
	/**
	 * Method to check to see if the removal of a certain bridge is a legal move or not
	 * @param x1 x position of the start of the bridge
	 * @param y1 y position of the start of the bridge
	 * @param x2 x position of the end of the bridge
	 * @param y2 y position of the end of the bridge
	 * @param plaerID an integer representing the player who wishes to do the move
	 * @return a boolean, true if a bridge can be removed
	 */
	public boolean canRemoveBridge(int x1, int y1, int x2, int y2, int playerID){
		return true;//as of now all the necessary checks i can think of already happen inside Board, we can update or remove this method later, i just added it to go with the design of the program
	}
	/**
	 * Checks to see if the game is over. Returns -1 if it is not, 1 if player 1 has won and 2 if player 2 has won.
	 * @return an integer
	 */
	//For now this method assumes p2 is always playing left to right, and p1 bottom to top
	public int detectEnd(){//does nothing yet
		Vector<Tower> topTowers = new Vector<Tower>();
		Vector<Tower> bottomTowers = new Vector<Tower>();
		Vector<Tower> leftTowers = new Vector<Tower>();
		Vector<Tower> rightTowers = new Vector<Tower>();
		Tower tempTower;
		for(int i=0;i<24;i++){
			//checking bottom row for towers
			tempTower=mBoard.getTower(i,0);
			if(tempTower!=null)
			{
				bottomTowers.add(tempTower);
			}
			//checking top row for towers
			tempTower=mBoard.getTower(i,23);
			if(tempTower!=null)
			{
				topTowers.add(tempTower);
			}
			//checking left row for towers
			tempTower=mBoard.getTower(0,i);
			if(tempTower!=null)
			{
				leftTowers.add(tempTower);
			}
			//checking right row for towers
			tempTower=mBoard.getTower(23,i);
			if(tempTower!=null)
			{
				rightTowers.add(tempTower);
			}
		}
			
		boolean foundConnection;
		//check for vertical connections
		if(!topTowers.isEmpty()){
			//if(hasConnection(bottomTowers,topTowers))
			//	return 2;
			while(!bottomTowers.isEmpty()){
				tempTower = bottomTowers.firstElement();
				bottomTowers.remove(0);
				foundConnection = hasConnection(tempTower,topTowers,mBoard.getBridgeList());
				if(foundConnection){
					return 1;
				}
			}
		}
		//check for horizontal connections
		if(!rightTowers.isEmpty()){
			//check to see of any of the towers in leftTowers link up to any of the Towers in rightTowers
			while(!leftTowers.isEmpty()){//
				tempTower = leftTowers.firstElement();//the tower to be checked
				leftTowers.remove(0);//remove it form the list
				foundConnection = hasConnection(tempTower,rightTowers,mBoard.getBridgeList());//returns true if tempTower is linked to any of the towrs in rightTower
				if(foundConnection){
					return 2;
				}
			}
		}
		
		return -1;
	}
	/**
	 * Recursive Method which returns true if the Tower start is connected to endList by the bridges listed in bridgeList
	 * @param start This tower will be checked to see if it is linked to endList
	 * @param endList A list of destination towers
	 * @param bridgeList A list of the bridges which could connect start and endList
	 * @return A boolean, true if there is a connection false otherwise
	 */
	private boolean hasConnection(Tower start, Vector<Tower> endList, Vector<Bridge> bridgeList){
		if(endList.contains(start)){//If we have reached a destination tower 
			return true;//then return true
		}
		Bridge currentBridge;
		//otherwise keep looking
		
		int SIZEME;
		
		if(!bridgeList.isEmpty()){
		for(int i=0;i<bridgeList.size();i++){//check all of the bridges
			SIZEME=bridgeList.size();
			if(test.getDebugModeOn())System.out.println("i: "+i+"Size "+SIZEME);
			currentBridge = bridgeList.get(i);
			if(currentBridge.getStart().compare(start)){//if the current bridge is connected to our source tower at one end
				bridgeList.remove(i);//then remove it from the bridge List to avoid looping and redundancy
				i--;//remove one from i to account for the removed element
				if(hasConnection(currentBridge.getEnd(),endList,bridgeList)){//and continue from the other end of the bridge to look for connections
					return true;
				}
			}else if(currentBridge.getEnd().compare(start)){//other wise check the other end to see if its connected to our source and start again
				bridgeList.remove(i);
				i--;
				if(hasConnection(currentBridge.getEnd(),endList,bridgeList)){
					return true;
				}
			}
		}
		}
		return false;//After having checked all of the bridges and not returned true, return false
	}
}
