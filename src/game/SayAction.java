package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SayAction extends Action {

	@Override
	public String execute(Actor actor, GameMap map) {
		String result = actor + " says ˇ°Braaaaainsˇ±" ;
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		
		return actor+" says";
	}

}
