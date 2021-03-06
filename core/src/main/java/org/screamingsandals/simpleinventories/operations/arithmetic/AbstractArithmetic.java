package org.screamingsandals.simpleinventories.operations.arithmetic;

import org.bukkit.entity.Player;
import org.screamingsandals.simpleinventories.SimpleInventories;
import org.screamingsandals.simpleinventories.item.PlayerItemInfo;
import org.screamingsandals.simpleinventories.operations.Operation;

public abstract class AbstractArithmetic implements Operation {
	protected SimpleInventories format;
	protected Object obj1;
	protected Object obj2;

	public AbstractArithmetic(SimpleInventories format, Object obj1, Object obj2) {
		this.format = format;
		this.obj1 = obj1;
		this.obj2 = obj2;
	}
	
	@Override
	public Object resolveFor(Player player, PlayerItemInfo info) {
		Object ob1 = this.obj1;
		Object ob2 = this.obj2;
		if (ob1 instanceof Operation) {
			ob1 = ((Operation) ob1).resolveFor(player, info);
		}
		if (ob2 instanceof Operation) {
			ob2 = ((Operation) ob2).resolveFor(player, info);
		}
		if (ob1 instanceof String) {
			ob1 = format.processPlaceholders(player, (String) ob1, info);
		}
		if (ob2 instanceof String) {
			ob2 = format.processPlaceholders(player, (String) ob2, info);
		}
		if (ob1 instanceof String) {
			try {
				ob1 = Double.parseDouble((String) ob1);
			} catch (NumberFormatException ex) {
			}
		}
		if (ob2 instanceof String) {
			try {
				ob2 = Double.parseDouble((String) ob2);
			} catch (NumberFormatException ex) {
			}
		}
		return resolveFor(player, ob1, ob2);
	}
	
	public abstract Object resolveFor(Player player, Object obj1, Object obj2);

	@Override
	public String toString() {
		return this.getClass().getName() + "[format=" + format + ";obj1=" + obj1 + ";obj2=" + obj2 + "]";
	}
}
