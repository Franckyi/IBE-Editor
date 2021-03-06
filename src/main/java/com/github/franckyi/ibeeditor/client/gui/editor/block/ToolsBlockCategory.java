package com.github.franckyi.ibeeditor.client.gui.editor.block;

import com.github.franckyi.ibeeditor.client.ClientUtils;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.ToolsCategory;

public class ToolsBlockCategory extends ToolsCategory {
    private final BlockEditor editor;

    public ToolsBlockCategory(BlockEditor editor) {
        this.editor = editor;
        this.addCommand("setblock", this::copySetblockCommand, this::copySetblockCommandWithoutFormatting);
        this.addCommand("give", this::copyGiveCommand, this::copyGiveCommandWithoutFormatting);
    }

    private void copySetblockCommand() {
        ClientUtils.copySetblockCommand(editor.getBlockState(), editor.getTileEntity());
    }

    private void copySetblockCommandWithoutFormatting() {
        ClientUtils.copySetblockCommandWithoutFormatting(editor.getBlockState(), editor.getTileEntity());
    }

    private void copyGiveCommand() {
        ClientUtils.copyGiveCommand(editor.getBlockState(), editor.getTileEntity());
    }

    private void copyGiveCommandWithoutFormatting() {
        ClientUtils.copyGiveCommandWithoutFormatting(editor.getBlockState(), editor.getTileEntity());
    }
}
