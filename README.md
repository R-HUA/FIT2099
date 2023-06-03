# FIT2099 Zombie Apocalypse Game

This is a text-based "rogue-like" game where you take on the role of a survivor in a Zombie Apocalypse. The world has been overrun by zombies, and you, along with a small group of survivors, have sought refuge in a compound in the woods. Your objective is to defend the compound and survive as long as possible.

## Game Overview

In this game, you will encounter various challenges and gameplay mechanics related to zombies, crafting weapons, farming, and survival. Here are the key features of the game:

### Zombie Attacks

- Zombies should have a 50% probability of using a bite attack instead of their normal attack. The bite attack should have a lower chance of hitting but inflict more damage.
- A successful bite attack should restore 5 health points to the zombie.
- If there is a weapon at the zombie's location, it should pick it up and use it for attacks instead of its intrinsic punch attack.

Additionally, each zombie should have a 10% chance of saying "Braaaaains" or a similar phrase every turn to add a touch of zombie-like behavior.

### Beating up the Zombies

Zombies are known for falling apart easily due to decay but keep on going regardless. 

- Any attack on a zombie that causes damage should have a chance to knock off one of its limbs (e.g., arms or legs). You can experiment with the probabilities to make it more enjoyable.
- Each zombie starts with two arms and two legs, and it cannot lose more than that.
- Losing an arm should halve the probability of punching, and there should be a 50% chance of dropping any held weapon. Losing both arms should definitively drop any held weapon.
- Losing a leg should reduce the zombie's movement speed by half, meaning it can only move every second turn.
- Losing both legs should render the zombie immobile but still capable of biting and punching.
- Lost limbs should drop to the ground at either the zombie's location or an adjacent location, whichever adds more fun and interest to the gameplay.
- Cast-off zombie limbs can be wielded as simple clubs, the damage amount being determined.

### Crafting Weapons

To enhance gameplay and resource management, introduce the ability for players to craft better weapons using the dropped zombie limbs:

- If the player is holding a zombie arm, they can craft it into a zombie club that inflicts significantly more damage.
- If the player is holding a zombie leg, they can craft it into a zombie mace, which deals even more damage.

### Rising from the Dead

When a human is killed by a zombie, they become a zombie themselves. Implement the mechanic where a human corpse rises from the dead as a zombie after 5-10 turns.

### Farmers and Food

Introduce a new kind of human character called "Farmers" who possess unique abilities related to farming and food production:

- Farmers share the same characteristics and abilities as regular humans but have additional farming-related actions.
- When standing next to a patch of dirt, a farmer has a 33% chance of sowing a crop on it.
- Crops take 20 turns to ripen if left alone.
- A farmer can fertilize an unripe crop by standing on it, reducing the time left to ripen by 10 turns.
- When standing on or next to a ripe crop, a farmer (or the player) can harvest it for food. If a farmer harvests the food, it is dropped to the ground. If the player harvests the food, it is placed in the player's inventory.
- Food can be eaten by the player or damaged humans to recover some health points.
