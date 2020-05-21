package game;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;

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
	private String lostLimb;
	private int turn;

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
		turn=0;
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		// 50% probability of using bite instead of punch
		// If a Zombie loses one arm, its probability of punching (rather than biting) is halved
		if (rand.nextInt(100) * (arm_no / 2) < 50) {
			return new IntrinsicWeapon(10, "punches");
		}
		// The bite attack should do more damage.
		return new IntrinsicWeapon(20, "bites");
		
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
		turn+=1;
		// Every turn, each  Zombie have a 10% chance of saying ¡°Braaaaains¡±
		if (rand.nextInt(100) < 10) {
			Behaviour theBehaviour = new SayBehaviour();
			return theBehaviour.getAction(this, map);
		}
		
		//  Lost limbs drop to the ground
		if (lostLimb!=null) {
			System.out.println(this+" lost its "+lostLimb);
			map.locationOf(this).addItem(new PortableItem("Zombie's "+lostLimb, lostLimb.charAt(0)));
		}
		
		// check the number of arms and legs
		if (arm_no == 0) {
			Actions dropActions = new Actions();
			for (Item item : this.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(this, map);
		}
		if (leg_no == 0 || (leg_no == 1 && turn % 2 != 0)) {
			Behaviour[] thisBehaviours = { new AttackBehaviour(ZombieCapability.ALIVE), new PickItemBehaviour() };
			for (Behaviour behaviour : thisBehaviours) {
				Action action = behaviour.getAction(this, map);
				if (action != null)
					return action;
			}
		} 
		else {
			for (Behaviour behaviour : behaviours) {
				Action action = behaviour.getAction(this, map);
				if (action != null)
					return action;
			}
		}
		return new DoNothingAction();
	}
	/**
	
	 */
	@Override
	public void hurt(int points) {
		hitPoints -= points;
		//Any attack on a Zombie that causes damage has a chance to knock at least one of its limbs off (25%)
		if (rand.nextInt(100) < 25) {
			if (rand.nextBoolean()) {
				arm_no -= 1;
				arm_no = Math.max(0, arm_no);
				lostLimb="arm";
			}
			else {
				leg_no -= 1;
				leg_no = Math.max(0, leg_no);
				lostLimb="leg";
			}
		}
		else {
			lostLimb=null;
		}
	}
	
}
