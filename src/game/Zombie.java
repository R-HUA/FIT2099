package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring. It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {

	private int arm_no;
	private int leg_no;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	private Behaviour[] behaviours = { new AttackBehaviour(ZombieCapability.ALIVE), 
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour() ,
			new PickItemBehaviour()};

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		arm_no = 2;
		leg_no = 2;
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		// 50% probability of using bite instead of punch
		if (rand.nextBoolean()) {
			return new IntrinsicWeapon(10, "punches");
		}
		else {
			// The bite attack should do more damage.
			return new IntrinsicWeapon(20, "bites");
		}
	}

	/**
	 * If a Zombie can attack, it will. If not, it will chase any human within 10
	 * spaces. If no humans are close enough it will wander randomly.
	 * 
	 * @param actions    list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map        the map where the current Zombie is
	 * @param display    the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Every turn, each  Zombie have a 10% chance of saying ¡°Braaaaains¡±
		if (rand.nextInt(100) < 10) {
			Behaviour theBehaviour = new SayBehaviour();
			return theBehaviour.getAction(this, map);
		}
		// TODO check the number of arms and legs
		// 
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}
}
