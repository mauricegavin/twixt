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
		System.out.println(rm.canPlaceTower(12, 12));
		System.out.println(rm.canPlaceTower(14, 13));
		b.placeTower(12, 12, 1);
		b.placeTower(14, 13, 1);
		b.placeTower(13, 14, 1);
		b.placeTower(14, 12, 1);
		if(b.getTower(12, 12)!=null){
			System.out.println("Tower found placed at :"+b.getTower(12, 12).getX()+", "+b.getTower(12, 12).getY());
		}
		if(b.getTower(14, 13)!=null){
			System.out.println("Tower found placed at :"+b.getTower(14, 13).getX()+", "+b.getTower(14, 13).getY());
		}
		testCanPlaceBridge(12,12,14,13,1,rm);
		testPlaceBridge(12,12,14,13,1,b);
		testCanPlaceBridge(12,12,13,14,1,rm);
		testPlaceBridge(12,12,13,14,1,b);
		testCanPlaceBridge(13,14,14,12,1,rm);
		//testPlaceBridge(13,14,14,12,1,b);
	}
	private static void testCanPlaceBridge(int x1,int y1,int x2,int y2,int pID,RuleMaster rm){
		System.out.println("---------------------------------------------");
		System.out.println("Testing canPlaceBridge");
		System.out.println("Can place "+pID+"bridge between "+x1+" "+y1+" "+x2+" "+y2+" :"+rm.canPlaceBridge(x1, y1, x2, y2, pID));
		System.out.println("---------------------------------------------");
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
