package org.screamingsandals.simpleguiformat.operations.arithmetic;

import org.bukkit.entity.Player;
import org.screamingsandals.simpleguiformat.SimpleGuiFormat;

public class MultiplicationArithmetic extends AbstractArithmetic {

	public MultiplicationArithmetic(SimpleGuiFormat format, Object obj1, Object obj2) {
		super(format, obj1, obj2);
	}

	@Override
	public Object resolveFor(Player player, Object obj1, Object obj2) {
		if (obj1 instanceof Number && obj2 instanceof Number) {
			return ((Number) obj1).doubleValue() * ((Number) obj2).doubleValue();
		}
		return Double.NaN;
	}

}