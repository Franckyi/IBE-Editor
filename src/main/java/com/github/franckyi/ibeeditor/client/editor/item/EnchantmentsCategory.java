package com.github.franckyi.ibeeditor.client.editor.item;

import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.editor.Category;
import com.github.franckyi.ibeeditor.client.editor.property.PropertyInteger;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.Consumer;

public class EnchantmentsCategory extends Category {

    private final ItemStack itemStack;

    public EnchantmentsCategory(ItemStack itemStack) {
        this.itemStack = itemStack;
        NBTTagList enchTag = itemStack.getEnchantmentTagList();
        Map<Enchantment, Integer> itemEnch = new HashMap<>(enchTag.size());
        for (int i = 0; i < enchTag.size(); i++) {
            NBTTagCompound c = enchTag.getCompound(i);
            String id = c.getString("id");
            int level = c.getInt("lvl");
            itemEnch.put(ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(id)), level);
        }
        List<Enchantment> enchantments = new ArrayList<>(ForgeRegistries.ENCHANTMENTS.getValues());
        enchantments.sort(new EnchantmentComparator());
        for (Enchantment e : enchantments) {
            this.getChildren().add(new PropertyEnchantment(itemStack, e,
                    itemEnch.getOrDefault(e, 0), i -> this.setEnchantment(e, i)));
        }
    }

    private void setEnchantment(Enchantment ench, int value) {
        if (value > 0) {
            itemStack.addEnchantment(ench, value);
        }
    }

    @Override
    public void apply() {
        itemStack.getOrCreateTag().remove("Enchantments");
        super.apply();
    }

    private class EnchantmentComparator implements Comparator<Enchantment> {

        @Override
        public int compare(Enchantment e1, Enchantment e2) {
            if (e1.isCurse()) {
                if (e2.isCurse()) {
                    return e1.getName().compareTo(e2.getName());
                } else {
                    return 1;
                }
            } else {
                if (e2.isCurse()) {
                    return -1;
                } else {
                    if (e1.canApply(itemStack)) {
                        if (e2.canApply(itemStack)) {
                            return e1.getName().compareTo(e2.getName());
                        } else {
                            return -1;
                        }
                    } else {
                        if (e2.canApply(itemStack)) {
                            return 1;
                        } else {
                            return e1.getName().compareTo(e2.getName());
                        }
                    }
                }
            }
        }
    }

    public class PropertyEnchantment extends PropertyInteger {

        protected TexturedButton plusButton;
        protected TexturedButton minusButton;
        protected Enchantment enchantment;

        public PropertyEnchantment(ItemStack itemStack, Enchantment enchantment, Integer initialValue, Consumer<Integer> action) {
            super(enchantment.getDisplayName(0).getUnformattedComponentText(), initialValue, action, 0, 128);
            this.enchantment = enchantment;
            nameLabel.setPrefWidth(COMPUTED_SIZE);
            nameLabel.setColor(enchantment.isCurse() ? TextFormatting.RED.getColor() : (enchantment.canApply(itemStack) ? TextFormatting.GREEN.getColor() : 0xffffff));
            updateButtons(initialValue);
        }

        @Override
        protected void build() {
            super.build();
            this.addAll(
                    plusButton = new TexturedButton("add.png", TextFormatting.GREEN + "+1 level"),
                    minusButton = new TexturedButton("minus.png", TextFormatting.RED + "-1 level")
            );
            integerField.getOnValueChangedListeners().add((oldVal, newVal) -> updateButtons(newVal));
            plusButton.getOnMouseClickedListeners().add(event -> this.setValue(this.getValue() + (this.getValue() == integerField.getMax() ? 0 : 1)));
            minusButton.getOnMouseClickedListeners().add(event -> this.setValue(this.getValue() - (this.getValue() == integerField.getMin() ? 0 : 1)));
            this.getNode().setAlignment(Pos.RIGHT);
        }

        private void updateButtons(int val) {
            plusButton.setDisabled(val == integerField.getMax() - 1);
            minusButton.setDisabled(val == integerField.getMin());
        }

        @Override
        public void updateSize(int listWidth) {
            integerField.setPrefWidth(listWidth - 250);
        }
    }
}