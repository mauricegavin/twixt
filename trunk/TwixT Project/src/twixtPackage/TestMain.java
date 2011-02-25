package twixtPackage;

/**
 * Just a little class for testing purposes so i can muck about with what we have so far
 * @author Jack
 *
 */
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board b = new Board();
		RuleMaster rm = new RuleMaster(b);
		testPlaceTower(12,12,1,b);
		testPlaceTower(13,14,1,b);
		testPlaceTower(14,13,1,b);
		testCanPlaceBridge(12,12,13,14,1,rm);
		testCanPlaceBridge(12,12,14,13,1,rm);
	}
	private static void testCanPlaceBridge(int x1,int y1,int x2,int y2,int pID,RuleMaster rm){
		System.out.println("---------------------------------------------");
		System.out.println("Testing canPlaceBridge");
		System.out.println("Can place "+pID+"bridge between "+x1+" "+y1+" "+x2+" "+y2+" :"+rm.canPlaceBridge(x1, y1, x2, y2, pID));
		System.out.println("---------------------------------------------");
	}
	private static void testCanPlaceTower(int x, int y, int pID, RuleMaster rm){
		System.out.println("Testing player "+pID+" canPlaceTower @:"+x+" "+y+" "+rm.canPlaceTower(x, y));
	}
	private static void testPlaceTower(int x, int y, int pID, Board b){
		b.placeTower(x, y, pID);
		if(b.getTower(x, y)!=null){
			System.out.println("Tower found placed at :"+b.getTower(x, y).getX()+", "+b.getTower(x, y).getY());
		}
	}
	private static void testPlaceBridge(int x1,int y1,int x2,int y2,int pID, Board b){
		b.placeBridge(x1, y1, x2, y2, pID);
		if(b.getBridge(x1, y1, x2, y2)!=null){
			System.out.println("bridge found placed @ "+x1+" "+y1+" "+x2+" "+y2);
		}else{
			System.out.println("bridge NOT found placed @ "+x1+" "+y1+" "+x2+" "+y2);
		}
	}

}
