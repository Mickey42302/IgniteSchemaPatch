package com.mickey42302.schema.mixin;

import com.mojang.logging.LogUtils;
import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import org.slf4j.Logger;

@Mixin({SharedConstants.class})
public abstract class SharedConstantsMixin {
  @Shadow
  @Mutable
  public static boolean CHECK_DATA_FIXER_SCHEMA;

  @Unique
  private static final Logger schemaPatch$LOGGER = LogUtils.getLogger();

  @Inject(method = {"<clinit>"}, at = {@At("TAIL")})
  private static void setCheckDataFixerSchema$clinit(CallbackInfo ci) {
    CHECK_DATA_FIXER_SCHEMA = false;
    schemaPatch$LOGGER.warn("Warning: The Check Data Fixer Schema feature is disabled. This may cause issues.");
  }
}
