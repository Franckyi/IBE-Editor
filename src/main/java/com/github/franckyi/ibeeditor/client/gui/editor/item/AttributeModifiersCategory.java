package com.github.franckyi.ibeeditor.client.gui.editor.item;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.DoubleField;
import com.github.franckyi.guapi.node.EnumButton;
import com.github.franckyi.guapi.node.TextField;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractProperty;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.EditableCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.property.IEditableCategoryProperty;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class AttributeModifiersCategory extends EditableCategory<AttributeModifiersCategory.AttributeModifierModel> {

    private final List<AttributeModifierModel> initialModifiers;
    private final ItemStack itemStack;
    private final List<AttributeModifierModel> modifiers;

    public AttributeModifiersCategory(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.modifiers = this.getModifiers(itemStack::getAttributeModifiers);
        this.initialModifiers = this.getModifiers(slot -> itemStack.getItem().getAttributeModifiers(slot, itemStack));
        this.getChildren().add(new AddButton("Add attribute modifier"));
        modifiers.forEach(this::addProperty);
    }

    private List<AttributeModifierModel> getModifiers(Function<EquipmentSlotType, Multimap<Attribute, AttributeModifier>> getAttributeModifiers) {
        List<AttributeModifierModel> res = new ArrayList<>();
        Stream.of(EquipmentSlotType.values())
                .forEach(slot -> getAttributeModifiers
                        .apply(slot)
                        .asMap()
                        .forEach((attribute, modifiers) -> modifiers
                                .stream()
                                .map(modifier -> new AttributeModifierModel(attribute.getRegistryName(), modifier, slot))
                                .forEach(res::add)
                        )
                );
        return res;
    }

    @Override
    public void apply() {
        itemStack.getOrCreateTag().remove("AttributeModifiers");
        modifiers.clear();
        super.apply();
        if (!modifiers.equals(initialModifiers)) {
            modifiers.forEach(modifier -> {
                Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(modifier.getAttributeName());
                if (attribute != null) {
                    itemStack.addAttributeModifier(attribute, modifier.getModifier(), modifier.getSlot());
                }
            });
        }
    }

    @Override
    protected AbstractProperty<AttributeModifierModel> createNewProperty(AttributeModifierModel initialValue, int index) {
        return new PropertyAttributeModifier(index, initialValue, modifiers::add);
    }

    @Override
    protected AttributeModifierModel getDefaultPropertyValue() {
        return new AttributeModifierModel(new ResourceLocation(""), new AttributeModifier("", 0, AttributeModifier.Operation.ADDITION), EquipmentSlotType.MAINHAND);
    }

    protected static class AttributeModifierModel {

        private final ResourceLocation attributeName;
        private final AttributeModifier modifier;
        private final EquipmentSlotType slot;

        public AttributeModifierModel(ResourceLocation attributeName, AttributeModifier modifier, EquipmentSlotType slot) {
            this.attributeName = attributeName;
            this.modifier = modifier;
            this.slot = slot;
        }

        public ResourceLocation getAttributeName() {
            return attributeName;
        }

        public AttributeModifier getModifier() {
            return modifier;
        }

        public EquipmentSlotType getSlot() {
            return slot;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj instanceof AttributeModifierModel) {
                AttributeModifierModel model = (AttributeModifierModel) obj;
                return slot.equals(model.slot) && modifier.getName().equals(model.modifier.getName())
                        && modifier.getOperation() == model.modifier.getOperation()
                        && modifier.getAmount() == model.modifier.getAmount() && attributeName.equals(model.attributeName);
            }
            return false;
        }
    }

    public class PropertyAttributeModifier extends AbstractProperty<AttributeModifierModel> implements IEditableCategoryProperty {

        private final PropertyControls controls;

        private TextField nameField;
        private EnumButton<EquipmentSlotType> slotButton;
        private EnumButton<AttributeModifier.Operation> operationButton;
        private DoubleField amountField;

        public PropertyAttributeModifier(int index, AttributeModifierModel initialValue, Consumer<AttributeModifierModel> action) {
            super(initialValue, action);
            controls = new PropertyControls(AttributeModifiersCategory.this, index);
            IEditableCategoryProperty.super.build();
        }

        @Override
        public AttributeModifierModel getValue() {
            return new AttributeModifierModel(ResourceLocation.tryCreate(nameField.getValue()), new AttributeModifier(
                    initialValue.getModifier().getID(), initialValue.getModifier().getName(),
                    amountField.getValue(), operationButton.getValue()), slotButton.getValue());
        }

        @Override
        protected void setValue(AttributeModifierModel value) {
            nameField.setValue(value.getAttributeName().toString());
            slotButton.setValue(value.getSlot());
            operationButton.setValue(value.getModifier().getOperation());
            amountField.setValue(value.getModifier().getAmount());
        }

        @Override
        public void build() {
            this.getNode().setAlignment(Pos.LEFT);
            this.addAll(
                    nameField = new TextField(initialValue.getAttributeName().toString()),
                    slotButton = new EnumButton<>(EquipmentSlotType.values()),
                    operationButton = new EnumButton<>(AttributeModifier.Operation.values()),
                    amountField = new DoubleField(initialValue.getModifier().getAmount())
            );
            nameField.getTooltipText().add("Attribute name");
            slotButton.setValue(initialValue.getSlot());
            slotButton.setRenderer(aSlot -> StringUtils.capitalize(aSlot.getName().toLowerCase()));
            slotButton.getTooltipText().add(ITextComponent.getTextComponentOrEmpty("Slot"));
            operationButton.setValue(initialValue.getModifier().getOperation());
            operationButton.setRenderer(operation -> Integer.toString(operation.getId()));
            operationButton.setPrefWidth(12);
            operationButton.getTooltipText().add(ITextComponent.getTextComponentOrEmpty("Operation"));
            amountField.setMargin(Insets.left(2));
            amountField.getTooltipText().add("Amount");
        }

        @Override
        public void updateSize(int listWidth) {
            amountField.setPrefWidth(listWidth - OFFSET - 252);
        }

        @Override
        public PropertyControls getControls() {
            return controls;
        }
    }
}
