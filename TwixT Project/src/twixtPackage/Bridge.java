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
	
	
	
	}
}
