package com.github.franckyi.guapi.impl.theme.vanilla;

import com.github.franckyi.gamehooks.api.client.Matrices;
import com.github.franckyi.gamehooks.impl.client.ForgeMatrices;
import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.Node;
import com.mojang.blaze3d.matrix.MatrixStack;

public class ForgeVanillaListViewRenderer<E> extends AbstractForgeVanillaListNodeRenderer<ListView<E>, E, ForgeVanillaListViewRenderer.NodeEntry<E>> {
    public ForgeVanillaListViewRenderer(ListView<E> node) {
        super(node);
        node.getItems().addListener(this::shouldRefreshList);
    }

    @Override
    protected void createList() {
        for (E item : node.getItems()) {
            Node view = node.getRenderer().getView(item);
            view.setParent(node);
            addEntry(new NodeEntry<>(this, item, view));
        }
    }

    protected static class NodeEntry<E> extends AbstractForgeVanillaListNodeRenderer.NodeEntry<ListView<E>, E, NodeEntry<E>> {
        public NodeEntry(ForgeVanillaListViewRenderer<E> list, E item, Node node) {
            super(list, item, node);
        }

        @Override
        public void render(MatrixStack matrixStack, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
            Matrices matrices = ForgeMatrices.of(matrixStack);
            entryWidth = getList().getMaxScroll() == 0 ? entryWidth + 6 : entryWidth;
            getNode().setX(x);
            getNode().setY(y);
            getNode().setParentPrefWidth(entryWidth);
            getNode().setParentPrefHeight(entryHeight);
            renderBackground(matrices, x, y, entryWidth, entryHeight);
            while (getNode().preRender(matrices, mouseX, mouseY, tickDelta)) ;
            getNode().render(matrices, mouseX, mouseY, tickDelta);
        }
    }
}