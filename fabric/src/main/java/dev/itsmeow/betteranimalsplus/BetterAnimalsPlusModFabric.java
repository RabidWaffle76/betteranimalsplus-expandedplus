package dev.itsmeow.betteranimalsplus;

import dev.itsmeow.betteranimalsplus.util.SquirrelKillsComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.LazyLoadedValue;

public class BetterAnimalsPlusModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        BetterAnimalsPlusMod.construct();
        BetterAnimalsPlusMod.init(Runnable::run);
    }

    public static final class BetterAnimalsPlusModComponents implements EntityComponentInitializer {

        public static LazyLoadedValue<ComponentKey<SquirrelKillsComponent>> SQUIRREL_KILLS_COMPONENT = new LazyLoadedValue(() -> ComponentRegistry.getOrCreate(new ResourceLocation(Ref.MOD_ID, "squirrel_kills"), SquirrelKillsComponent.class));

        @Override
        public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
            registry.registerForPlayers(SQUIRREL_KILLS_COMPONENT.get(), player -> new SquirrelKillsComponent(), RespawnCopyStrategy.ALWAYS_COPY);
        }
    }
}
