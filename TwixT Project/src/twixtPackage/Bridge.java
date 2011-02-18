package twixtPackage;

public class Bridge {
	
	private int playerId;
	private Tower T1;
	private Tower T2;
    
    
	
	Bridge(){
		
		playerId = 0;
		T1 = T2 = null; 
		
	}

	Bridge(Tower Start, Tower End,  int owner){
		
		T1 = Start;
		T2 = End; 
    	playerId = owner;
		
	}
	
	Tower getStart(){
		return T1;
	}
	Tower getEnd(){
		return T2;
	}
	int getID(){
		return playerId;
	}
	void setStart(Tower T){
		T1 = T;
	}
	void setEnd(Tower T){
		T2 = T;
		}
	void setPlayerID(int id){
		assert(id>0&&id<3): "Error: value must be between 1 and 2 ";
		playerId = id; 
	}
		
	
}
