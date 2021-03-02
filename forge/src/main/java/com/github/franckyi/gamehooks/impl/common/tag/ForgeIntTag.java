package com.github.franckyi.gamehooks.impl.common.tag;

import com.github.franckyi.gamehooks.api.common.tag.IntTag;
import net.minecraft.nbt.IntNBT;

public class ForgeIntTag implements IntTag {
    private final IntNBT tag;

    public ForgeIntTag() {
        this(0);
    }

    public ForgeIntTag(int value) {
        this(IntNBT.valueOf(value));
    }

    public ForgeIntTag(IntNBT tag) {
        this.tag = tag;
    }

    @Override
    public int getValue() {
        return tag.getInt();
    }

    @Override
    @SuppressWarnings("unchecked")
    public IntNBT get() {
        return tag;
    }

    @Override
    public String toString() {
        return Integer.toString(getValue());
    }
}