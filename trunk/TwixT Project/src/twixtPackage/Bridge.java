package twixtPackage;

public class Bridge {
	
	private int playerId;
    private int x1Coordinate;
    private int y1Coordinate;
    private int x2Coordinate;
    private int y2Coordinate;
	
	Bridge(){
		
		playerId = x1Coordinate = y1Coordinate = x2Coordinate = y2Coordinate = 0;
	}

	Bridge(int x1, int y1, int x2, int y2, int owner){
		
		x1Coordinate = x1;
    	y1Coordinate = y1;
    	x2Coordinate = x2;
    	y2Coordinate = y2;
    	playerId = owner;
		
	}
	
	
}
