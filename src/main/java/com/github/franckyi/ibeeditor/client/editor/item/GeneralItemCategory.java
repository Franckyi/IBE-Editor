package com.github.franckyi.ibeeditor.client.editor.item;

import com.github.franckyi.ibeeditor.client.editor.common.AbstractProperty;
import com.github.franckyi.ibeeditor.client.editor.common.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.editor.common.property.IOrderableEditableCategoryProperty;
import com.github.franckyi.ibeeditor.client.editor.common.property.PropertyBoolean;
import com.github.franckyi.ibeeditor.client.editor.common.property.PropertyFormattedText;
import com.github.franckyi.ibeeditor.client.editor.common.property.PropertyInteger;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.util.Constants;

import java.util.function.Consumer;

public class GeneralItemCategory extends EditableCategory<String> {

    private final ItemStack itemStack;
    private boolean flag;

    public GeneralItemCategory(ItemStack itemStack) {
        super(4);
        this.itemStack = itemStack;
        this.addAll(
                new PropertyFormattedText("Name", itemStack.getDisplayName().getFormattedText(), this::setName),
                new PropertyBoolean("Unbreakable", this.hasUnbreakable(), this::setUnbreakable),
                new PropertyInteger("Count", itemStack.getCount(), itemStack::setCount, 1, 127),
                new PropertyInteger("Damage", itemStack.getDamage(), this::setDamage),
                new AddButton("Add lore")
        );
        if (itemStack.hasTag()) {
            NBTTagCompound tag = itemStack.getOrCreateTag();
            if (tag.contains("display", Constants.NBT.TAG_COMPOUND)) {
                NBTTagCompound display = itemStack.getOrCreateChildTag("display");
                if (display.contains("Lore", Constants.NBT.TAG_LIST)) {
                    NBTTagList loreTag = display.getList("Lore", Constants.NBT.TAG_STRING);
                    for (int i = 0; i < loreTag.size(); i++) {
                        this.addProperty(loreTag.getString(i));
                    }
                    for (int i = 0; i < this.getPropertyCount(); i++) {
                        this.getProperty(i).update(i);
                    }
                }
            }
        }
    }

    private void setDamage(int damage) {
        if (damage == 0) {
            itemStack.getOrCreateTag().remove("Damage");
        } else {
            itemStack.setDamage(damage);
        }
    }

    private void setName(String s) {
        TextComponentTranslation baseName = new TextComponentTranslation(itemStack.getItem().getTranslationKey(itemStack));
        if (baseName.getUnformattedComponentText().equals(s)) {
            itemStack.getOrCreateTag().remove("display");
        } else {
            itemStack.setDisplayName(new TextComponentString(s));
        }
    }

    private boolean hasUnbreakable() {
        return itemStack.getOrCreateTag().contains("Unbreakable", Constants.NBT.TAG_BYTE);
    }

    @Override
    public void apply() {
        itemStack.getOrCreateTag().remove("Unbreakable");
        flag = true;
        super.apply();
    }

    private void setLore(String lore) {
        if (flag) {
            itemStack.getOrCreateChildTag("display").put("Lore", new NBTTagList());
            flag = false;
        }
        itemStack.getOrCreateChildTag("display")
                .getList("Lore", Constants.NBT.TAG_STRING).add(new NBTTagString(lore));
    }

    private void setUnbreakable(boolean unbreakable) {
        if (unbreakable) {
            itemStack.getOrCreateTag().putBoolean("Unbreakable", true);
        }
    }

    @Override
    protected AbstractProperty<String> createNewProperty(String initialValue, int index) {
        return new PropertyLore(index, initialValue, this::setLore);
    }

    @Override
    protected String getDefaultPropertyValue() {
        return "";
    }

    public class PropertyLore extends PropertyFormattedText implements IOrderableEditableCategoryProperty {

        private final OrderablePropertyControls controls;

        public PropertyLore(int index, String value, Consumer<String> action) {
            super("", value, action);
            this.controls = new OrderablePropertyControls(GeneralItemCategory.this, index);
            IOrderableEditableCategoryProperty.super.build();
        }

        @Override
        public OrderablePropertyControls getControls() {
            return controls;
        }

        @Override
        public void build() {
            super.build();
        }

        @Override
        public void update(int newIndex) {
            IOrderableEditableCategoryProperty.super.update(newIndex);
            nameLabel.setText("Lore #" + (newIndex + 1) + " :");
        }

        @Override
        public void updateSize(int listWidth) {
            textField.setPrefWidth(listWidth - 197);
        }

    }
}
