package its_meow.betteranimalsplus.common.item;

import its_meow.betteranimalsplus.BetterAnimalsPlusMod;
import its_meow.betteranimalsplus.client.model.ModelWolfCape;
import its_meow.betteranimalsplus.init.ModItems;
import its_meow.betteranimalsplus.util.ArmorMaterialWolfCape;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemWolfCape extends ArmorItem {

    public final int variant;
    
    public ItemWolfCape(int variant) {
        super(new ArmorMaterialWolfCape(variant), EquipmentSlotType.CHEST, new Properties().group(BetterAnimalsPlusMod.group));
        this.variant = variant;
        this.setRegistryName("wolfcape" + variant);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A defaultModel) {
        if(itemStack != null) {
            if(itemStack.getItem() instanceof ArmorItem) {

                @SuppressWarnings("unchecked")
                A armorModel = (A) ModelWolfCape.INSTANCE;
                armorModel.bipedHead.showModel = true;

                armorModel.isSneak = defaultModel.isSneak;
                armorModel.isSitting = defaultModel.isSitting;
                armorModel.isChild = defaultModel.isChild;
                armorModel.rightArmPose = defaultModel.rightArmPose;
                armorModel.leftArmPose = defaultModel.leftArmPose;

                return armorModel;
            }
        }
        return super.getArmorModel(entityLiving, itemStack, armorSlot, defaultModel);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        Item matchPelt = null;
        switch(this.variant) {
        case 1: matchPelt = ModItems.WOLF_PELT_SNOWY; break;
        case 2: matchPelt = ModItems.WOLF_PELT_TIMBER; break;
        case 3: matchPelt = ModItems.WOLF_PELT_BLACK; break;
        default: return false;
        }
        return repair.getItem() == matchPelt;
    }

}