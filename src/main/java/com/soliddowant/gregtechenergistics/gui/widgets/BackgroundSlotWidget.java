package com.soliddowant.gregtechenergistics.gui.widgets;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.IRenderContext;
import gregtech.api.gui.resources.IGuiTexture;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.util.Position;
import gregtech.api.util.Size;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

public class BackgroundSlotWidget extends SlotWidget {
    protected TextureArea BorderTexture = GuiTextures.SLOT;
    public BackgroundSlotWidget(IItemHandler itemHandler, int slotIndex, int xPosition, int yPosition, boolean canTakeItems, boolean canPutItems) {
        super(itemHandler, slotIndex, xPosition, yPosition, canTakeItems, canPutItems);
    }

    @SuppressWarnings("unused")
    public BackgroundSlotWidget(IItemHandler itemHandler, int slotIndex, int xPosition, int yPosition) {
        this(itemHandler, slotIndex, xPosition, yPosition, true, true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawInBackground(int mouseX, int mouseY, float partialTicks, IRenderContext context) {
        if(!isEnabled())
            return;

        Position pos = getPosition();
        Size size = getSize();

        BorderTexture.draw(pos.x, pos.y, size.width, size.height);

        if(backgroundTexture == null)
            return;

        for (IGuiTexture backgroundTexture : this.backgroundTexture) {
            backgroundTexture.draw(pos.x + 1, pos.y + 1, size.width - 2, size.height - 2);
            backgroundTexture.draw(pos.x + 1, pos.y + 1, size.width - 2, size.height - 2);
        }
    }
}
