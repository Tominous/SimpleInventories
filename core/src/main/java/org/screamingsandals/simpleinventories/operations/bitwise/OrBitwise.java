package org.screamingsandals.simpleinventories.operations.bitwise;

import org.bukkit.entity.Player;
import org.screamingsandals.simpleinventories.SimpleInventories;

public class OrBitwise extends AbstractBitwise {

	public OrBitwise(SimpleInventories format, Object obj1, Object obj2) {
		super(format, obj1, obj2);
	}

	@Override
	public Object resolveFor(Player player, Object obj1, Object obj2) {
		if (obj1 instanceof Number && obj2 instanceof Number) {
			return ((Number) obj1).intValue() | ((Number) obj2).intValue();
		}
		return 0;
	}

}
