package com.github.franckyi.ibeeditor.gui.property;

import com.github.franckyi.ibeeditor.gui.base.GuiDoubleTextField;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DoubleProperty extends BaseProperty<Double> {

    private final GuiDoubleTextField textField;

    public DoubleProperty(String name, Supplier<Double> value, Consumer<Double> apply) {
        super(name, value, apply);
        textField = new GuiDoubleTextField(0, mc.fontRenderer, 0, 0, 100, 14);
        init();
    }

    @Override
    protected void init() {
        textField.setText(getValue().toString());
    }

    @Override
    protected void place() {
        super.place();
        textField.x = slotRight - 140;
        textField.y = slotTop + 3;
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        textField.textboxKeyTyped(typedChar, keyCode);
        setValue(textField.getValue());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        textField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
        super.drawEntry(slotIndex, x, y, listWidth, slotHeight, mouseX, mouseY, isSelected, partialTicks);
        mc.fontRenderer.drawString(getName(), x + 5, slotTop + 6, 0xffffff);
        textField.drawTextBox();
    }

    @Override
    public void updateScreen() {
        textField.updateCursorCounter();
    }
}
