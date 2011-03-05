package twixtPackage;

public class Bridge {
	
	private int playerId;
	private Tower tower1;
	private Tower tower2;
       
	
	Bridge(){
		
		playerId = 0;
		tower1 = tower2 = null; 
		
	}

	Bridge(Tower start, Tower end,  int owner)
	{	
		assert(owner>0&&owner<3&&tower1!=null&&tower2!=null): "Error: Invalid player id or null tower";
		tower1 = start;
		tower2 = end; 
    	playerId = owner;	
	}
	
	Tower getStart(){
		return tower1;
	}
	Tower getEnd(){
		return tower2;
	}
	int getID(){
		return playerId;
	}
	void setStart(Tower T){
		tower1 = T;
	}
	void setEnd(Tower T){
		tower2 = T;
		}
	void setPlayerID(int id){
		assert(id>0&&id<3): "Error: value must be between 1 and 2 ";
		playerId = id; 
	}
		
	
}
