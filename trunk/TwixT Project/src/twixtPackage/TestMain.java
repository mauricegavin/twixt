package twixtPackage;

import java.awt.Dimension;

/**
 * Just a little class for testing purposes so i can muck about with what we have so far
 * @author Jack
 *
 */
public class TestMain {
	static Test test = new Test(false);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board b = new Board();
		RuleMaster rm = new RuleMaster(b);
		System.out.println("Is over :"+rm.detectEnd());
		
		//the following section tests to see if the rulemaster can detect the vertical end of a game where a loop is present
		//testPlaceTower(0,12,1,b);//1
		//testPlaceTower(2,11,1,b);//2
		//testPlaceTower(4,12,1,b);//3
		//testPlaceTower(6,11,1,b);//4	
		//testPlaceTower(8,12,1,b);//5
		//testPlaceTower(10,11,1,b);//6
		//testPlaceTower(12,12,1,b);//7
		//testPlaceTower(14,11,1,b);//8
		//testPlaceTower(16,12,1,b);//9
		//testPlaceTower(18,11,1,b);//10
		//testPlaceTower(20,12,1,b);//11
		//testPlaceTower(22,11,1,b);//12
		//testPlaceTower(23,12,1,b);//13
		//testPlaceTower(10,13,1,b);//loop tower
		
		//testPlaceBridge(0,12,2,11,1,b);//1
		//testPlaceBridge(2,11,4,12,1,b);//2
		//testPlaceBridge(4,12,6,11,1,b);//3
		//testPlaceBridge(6,11,8,12,1,b);//4
		//testPlaceBridge(8,12,10,11,1,b);//5
		//testPlaceBridge(10,11,12,12,1,b);//6
		//testPlaceBridge(12,12,14,11,1,b);//7
		//testPlaceBridge(14,11,16,12,1,b);//8
		//testPlaceBridge(16,12,18,11,1,b);//9
		//testPlaceBridge(18,11,20,12,1,b);//10
		//testPlaceBridge(20,12,22,11,1,b);//11
		//testPlaceBridge(22,11,23,12,1,b);//12
		//testPlaceBridge(8,12,10,13,1,b);//loop bridge
		//testPlaceBridge(10,13,12,12,1,b);//loop bridge
		
		GameView gv = new GameView(b);
		Game g = new Game(b, rm);
		g.placeTower(9, 9, 1);
		g.endTurn(1);
		g.piRule(2);
		//g.placeTower(3, 1, 2);
		//g.endTurn(2);
		g.placeTower(10, 11, 1);
		//g.placeBridge(9, 9, 10, 11, 1);
		
		System.out.println("Is over :"+rm.detectEnd());
	}
	private static void testCanPlaceBridge(int x1,int y1,int x2,int y2,int pID,RuleMaster rm){
		if(test.getDebugModeOn()){
			System.out.println("---------------------------------------------");
			System.out.println("Testing canPlaceBridge");
			System.out.println("Can place "+pID+"bridge between "+x1+" "+y1+" "+x2+" "+y2+" :"+rm.canPlaceBridge(x1, y1, x2, y2, pID));
			System.out.println("---------------------------------------------");
		}
	}
	private static void testCanPlaceTower(int x, int y, int pID, RuleMaster rm){
		if(test.getDebugModeOn()){	
			System.out.println("Testing player "+pID+" canPlaceTower @:"+x+" "+y+" "+rm.canPlaceTower(x, y, pID));
		}
	}
	private static void testPlaceTower(int x, int y, int pID, Board b){
		b.placeTower(x, y, pID);
		if(b.getTower(x, y)!=null){
			if(test.getDebugModeOn()){
			System.out.println("Tower found placed at :"+b.getTower(x, y).getX()+", "+b.getTower(x, y).getY());}
		}
	}
	private static void testPlaceBridge(int x1,int y1,int x2,int y2,int pID, Board b){
		b.placeBridge(x1, y1, x2, y2, pID);
		if(b.getBridge(x1, y1, x2, y2)!=null){
			if(test.getDebugModeOn()){
				System.out.println("bridge found placed @ "+x1+" "+y1+" "+x2+" "+y2);}
		}else{
			if(test.getDebugModeOn()){
			System.out.println("bridge NOT found placed @ "+x1+" "+y1+" "+x2+" "+y2);}
		}
	}

}
