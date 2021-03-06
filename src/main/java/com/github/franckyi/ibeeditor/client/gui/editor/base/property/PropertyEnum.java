package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.node.EnumButton;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public class PropertyEnum<T> extends LabeledProperty<T> {

    protected EnumButton<T> enumButton;

    public PropertyEnum(String name, T initialValue, Collection<T> values, Consumer<T> action) {
        this(name, initialValue, values, action, t -> StringUtils.capitalize(t.toString().toLowerCase()));
    }

    public PropertyEnum(String name, T initialValue, Collection<T> values, Consumer<T> action, Function<T, String> renderer) {
        super(name, initialValue, action);
        enumButton.setValues(new ArrayList<>(values));
        enumButton.setValue(initialValue);
        enumButton.setRenderer(renderer);
    }

    @Override
    protected void build() {
        super.build();
        this.addAll(enumButton = new EnumButton<>());
        enumButton.setMargin(Insets.right(5));
    }

    @Override
    public void updateSize(int listWidth) {
        nameLabel.setPrefWidth(listWidth - enumButton.getWidth() - OFFSET - 67);
    }

    @Override
    protected T getValue() {
        return enumButton.getValue();
    }

    @Override
    protected void setValue(T value) {
        enumButton.setValue(value);
    }
}
