package twixtPackage;

public class HumanController implements Controller{
		private int myID;
		private int lastXClick=-1;
		private int lastYClick=-1;
		private Game mGame;
		public Test test= new Test(true);
		public HumanController(int playerID, Game game){
			myID=playerID;
			mGame=game;
		}
		public void doMove(int x, int y){
			if(mGame.isMyGo(myID)){
			//if there is a valid last click and you are clicking on a tower of your own then place a bridge/remove a bridge
			if((mGame.getTower(x, y)!=null)&&(mGame.getRealID(mGame.getTower(x, y).getPlayerId())==myID)){
				if(lastXClick>=0){
					if(mGame.getBridge(x,y,lastXClick,lastYClick,myID)==null){//if there is not a bridge of mine already here
						if(test.getDebugModeOn())System.out.println("HControler "+myID+" placing Bridge");
						mGame.placeBridge(x, y, lastXClick, lastYClick, myID);//place a bridge
						lastXClick=x;
						lastYClick=y;
					}else{//otherwise, there is a bridge of mine already here
						if(test.getDebugModeOn())System.out.println("HControler "+myID+" removing bridge");
						mGame.removeBridge(x, y, lastXClick, lastYClick, myID);//so remove a bridge
						lastXClick=x;
						lastYClick=y;
					}
				}else{
					//if there is no valid last move and you are clicking on a tower of your own, then store the move as a future bridge end
					if(test.getDebugModeOn())System.out.println("HControler "+myID+" first half of a bridge place");
					lastXClick=x;
					lastYClick=y;
				}
			}else{
			//if there is no tower of yours here then attempt to place a tower
				if(test.getDebugModeOn())System.out.println("HControler "+myID+" placing tower");
				if(mGame.placeTower(x, y, myID)){
					lastXClick=x;
					lastYClick=y;
				};
			}
			}
		}
		public void endTurn(){
			if(mGame.isMyGo(myID)){
				mGame.endTurn(myID);
				lastXClick=-1;
				lastYClick=-1;
			}
		}
		public void piRule() {
			if(mGame.isMyGo(myID)){
				mGame.piRule(myID);
			}
		}
}
