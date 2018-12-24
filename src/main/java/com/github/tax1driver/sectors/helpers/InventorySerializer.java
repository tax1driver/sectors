package com.github.tax1driver.sectors.helpers;


import net.minecraft.server.v1_8_R3.NBTReadLimiter;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventoryCustom;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;

public class InventorySerializer {
    public static String serializeInventory(Inventory inv) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(outputStream);
        NBTTagList tagList = new NBTTagList();

        for (int i = 0; i < inv.getSize(); i++) {
            NBTTagCompound tag = new NBTTagCompound();
            net.minecraft.server.v1_8_R3.ItemStack is = CraftItemStack.asNMSCopy(inv.getItem(i));

            if (is != null) {
               tag = is.save(tag);
               System.out.println("tag: " + tag.toString());
            }

            tagList.add(tag);
        }

        writeToOutputStream(tagList, dataOutput);
        return new BigInteger(1, outputStream.toByteArray()).toString(32);
    }

    public static String serializeItemArray(ItemStack... stacks) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutput = new DataOutputStream(outputStream);
        NBTTagList tagList = new NBTTagList();

        for (int i = 0; i < stacks.length; i++) {
            NBTTagCompound tag = new NBTTagCompound();
            net.minecraft.server.v1_8_R3.ItemStack is = CraftItemStack.asNMSCopy(stacks[i]);

            if (is != null) {
                tag = is.save(tag);
                System.out.println("tag: " + tag.toString());
            }

            tagList.add(tag);
        }

        writeToOutputStream(tagList, dataOutput);
        return new BigInteger(1, outputStream.toByteArray()).toString(32);
    }

    public static Inventory deserializeInventory(String base32) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new BigInteger(base32, 32).toByteArray());
        DataInputStream dataInput = new DataInputStream(inputStream);
        NBTTagList tagList = loadFromInputStream(dataInput);

        System.out.print(tagList.toString());

        Inventory inv = new CraftInventoryCustom(null, tagList.size());

        for (int i = 0; i < tagList.size(); i++) {
            NBTTagCompound tag = tagList.get(i);
            inv.setItem(i, CraftItemStack.asCraftMirror(net.minecraft.server.v1_8_R3.ItemStack.createStack(tag)));
        }


        return inv;
    }

    public static ItemStack[] deserializeItemArray(String base32) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new BigInteger(base32, 32).toByteArray());
        DataInputStream dataInput = new DataInputStream(inputStream);
        NBTTagList tagList = loadFromInputStream(dataInput);

        System.out.print(tagList.toString());

        ItemStack[] ret = new ItemStack[4];

        for (int i = 0; i < tagList.size(); i++) {
            NBTTagCompound tag = tagList.get(i);
            ret[i] = CraftItemStack.asCraftMirror(net.minecraft.server.v1_8_R3.ItemStack.createStack(tag));
        }


        return ret;
    }


    public static void deserializeAndSetPlayerInventory(String base32, Player player) {
        Inventory inv = deserializeInventory(base32);

        for (int i = 0; i < inv.getSize(); i++) {
            player.getInventory().setItem(i, inv.getItem(i));
        }
    }


    private static void writeToOutputStream(NBTTagList list, DataOutputStream outputStream) {
            try {
                Method method = list.getClass().getDeclaredMethod("write", DataOutput.class);
                method.setAccessible(true);
                method.invoke(list, outputStream);
            } catch(NoSuchMethodException e) {
                e.printStackTrace();
            } catch(InvocationTargetException e) {
                e.printStackTrace();
            } catch(IllegalAccessException e) {
                e.printStackTrace();
            }
    }

    private static NBTTagList loadFromInputStream(DataInputStream stream) {
        NBTTagList ret = new NBTTagList();
        try {
            Method method = ret.getClass().getDeclaredMethod("load", DataInput.class, int.class, NBTReadLimiter.class);
            method.setAccessible(true);
            method.invoke(ret, stream, 0, new NBTReadLimiter(1024*1024));
        } catch(NoSuchMethodException e) {
            e.printStackTrace();
        } catch(InvocationTargetException e) {
            e.printStackTrace();
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
