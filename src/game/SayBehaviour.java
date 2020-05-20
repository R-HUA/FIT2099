/**
 * 
 */
package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * @author H
 *
 */
public class SayBehaviour implements Behaviour {
	
	@Override
	public  Action getAction(Actor actor, GameMap map) {
		return new SayAction();
		
	}

}
