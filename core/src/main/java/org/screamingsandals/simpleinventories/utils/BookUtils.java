package org.screamingsandals.simpleinventories.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class BookUtils {

    public static void openBook(Player player) {
        try {
            if (pc == null || ppocp == null || pds == null)
                throw new NullPointerException("Craftbukkit classes not found!");
            int number = Integer.parseInt(version.split("_")[0].substring(1)) * 100
                    + Integer.parseInt(version.split("_")[1]);
            if (number < 113) {
                pc.getMethod("sendPacket", p).invoke(getConnection(player),
                        ppocp.getConstructor(String.class, pds).newInstance("MC|BOpen",
                                pds.getConstructor(ByteBuf.class).newInstance(
                                        Unpooled.buffer(256).setByte(0, (byte) 0).writerIndex(1))));
            } else if (number == 113) {
                Class<?> mk = getNMSClass("MinecraftKey");
                pc.getMethod("sendPacket", p).invoke(getConnection(player),
                        ppocp.getConstructor(mk, pds).newInstance(
                                mk.getMethod("a", String.class).invoke(mk, "minecraft:book_open"),
                                pds.getConstructor(ByteBuf.class).newInstance(
                                        Unpooled.buffer(256).setByte(0, (byte) 0).writerIndex(1))));
            } else if (number > 113) {
                pc.getMethod("sendPacket", p).invoke(getConnection(player), ppoob.getConstructor(eh)
                        .newInstance(eh.getDeclaredMethod("valueOf", String.class).invoke(eh, "MAIN_HAND")));
            }

        } catch (Throwable ignored) {
        }
    }

    private static Class<?> getNMSClass(String nmsClassString) {
        try {
            return Class.forName("net.minecraft.server." + version + "." + nmsClassString);
        } catch (Throwable ignore) {
        }
        return null;
    }

    private static Object getConnection(Player player) throws SecurityException, NoSuchMethodException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
        return nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
    }

    private static final String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",")
            .split(",")[3];
    private static final Class<?> ppocp = getNMSClass("PacketPlayOutCustomPayload");
    private static final Class<?> pc = getNMSClass("PlayerConnection");
    private static final Class<?> pds = getNMSClass("PacketDataSerializer");
    private static final Class<?> ppoob = getNMSClass("PacketPlayOutOpenBook");
    private static final Class<?> eh = getNMSClass("EnumHand");
    private static final Class<?> p = getNMSClass("Packet");
}
