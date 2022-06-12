package com.soliddowant.gregtechenergistics.items;

import appeng.api.parts.IPart;
import appeng.api.parts.IPartItem;
import com.soliddowant.gregtechenergistics.GregTechEnergisticsMod;
import com.soliddowant.gregtechenergistics.items.stats.IModelProvider;
import com.soliddowant.gregtechenergistics.items.stats.IPartProvider;
import gregtech.api.items.metaitem.MetaItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public abstract class ModMetaItem<T extends ModMetaItem<?>.ModMetaValueItem> extends MetaItem<T> implements IPartItem<IPart> {
    protected static final ModelResourceLocation MISSING_LOCATION = new ModelResourceLocation("builtin/missing",
            "inventory");

    public ModMetaItem(short metaItemOffset) {
        super(metaItemOffset);
    }

    @Override
    public IPart createPartFromItemStack(ItemStack is) {
        T metaValueItem = getItem(is);
        if(metaValueItem == null)
            return null;

        IPartProvider partProvider = metaValueItem.getPartProvider();
        if(partProvider == null)
            return null;

        return partProvider.getPart(is);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        for (short itemMetaKey : metaItems.keySet()) {
            T metaValueItem = metaItems.get(itemMetaKey);

            IModelProvider itemModelProvider = metaValueItem.getModelProvider();
            if(itemModelProvider != null) {
                ModelResourceLocation resourceLocation = itemModelProvider.getModel();
                ModelBakery.registerItemVariants(this, resourceLocation);
                metaItemsModels.put((short) (metaItemOffset + itemMetaKey), resourceLocation);
            } else {
                ResourceLocation resourceLocation = new ResourceLocation(GregTechEnergisticsMod.MODID, formatModelPath(metaValueItem));
                ModelBakery.registerItemVariants(this, resourceLocation);
                metaItemsModels.put((short) (metaItemOffset + itemMetaKey),
                    new ModelResourceLocation(resourceLocation, "inventory"));
            }
        }

        ModelLoader.setCustomMeshDefinition(this, itemStack -> {
            short itemDamage = formatRawItemDamage((short) itemStack.getItemDamage());

            if (specialItemsModels.containsKey(itemDamage))
                return specialItemsModels.get(itemDamage)[getModelIndex(itemStack)];

            if (metaItemsModels.containsKey(itemDamage))
                return metaItemsModels.get(itemDamage);

            return MISSING_LOCATION;
        });
    }

    public class ModMetaValueItem extends MetaValueItem {
        protected IPartProvider partProvider;
        protected IModelProvider modelProvider;

        protected ModMetaValueItem(int metaValue, String unlocalizedName) {
            super(metaValue, unlocalizedName);
        }

        @Nullable
        public IPartProvider getPartProvider() {
            return partProvider;
        }

        @Nullable
        public IModelProvider getModelProvider() {
            return modelProvider;
        }
    }
}
