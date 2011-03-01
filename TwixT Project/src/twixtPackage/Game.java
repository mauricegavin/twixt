package twixtPackage;

public class Game 
{
	SetupView setupFrame;
	GameView gameFrame;
	
	Game()
	{
		setupFrame = new SetupView(this);
	}

	public void createNewGameView(boolean state) 
	{
		if (state)
			gameFrame = new GameView();
		else
			gameFrame = null;
	}
}
