package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.Group;
import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.EditableCategory;
import net.minecraft.util.text.TextFormatting;

public interface IEditableCategoryProperty {

    Group getNode();

    PropertyControls getControls();

    default void build() {
        Group root = this.getNode();
        getControls().getRemove().setPrefSize(20, 20);
        getControls().getRemove().getOnMouseClickedListeners().add(event -> getControls().getCategory().removeProperty(getControls().getIndex()));
        root.getChildren().add(getControls().getRemove());
    }

    default void update(int newIndex) {
        getControls().setIndex(newIndex);
    }

    class PropertyControls {

        private final EditableCategory category;
        private final TexturedButton remove;
        private int index;

        public PropertyControls(EditableCategory category, int index) {
            this.category = category;
            this.index = index;
            this.remove = new TexturedButton("delete.png", TextFormatting.RED + "Remove");
        }

        public EditableCategory getCategory() {
            return category;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public TexturedButton getRemove() {
            return remove;
        }
    }

}
