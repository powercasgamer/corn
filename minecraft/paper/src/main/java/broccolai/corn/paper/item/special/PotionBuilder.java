/*
 * This file is part of corn, licensed under the GNU Lesser General Public License (LGPL) version 3 license.
 *
 * Copyright (C)  2020-2023 broccolai
 * Copyright (C)  2020-2023 Contributors
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link PotionMeta}.
 */
@SuppressWarnings("unused")
public final class PotionBuilder extends AbstractPaperItemBuilder<PotionBuilder, PotionMeta> {

    private PotionBuilder(final @NonNull ItemStack itemStack, final @NonNull PotionMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@link PotionBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link PotionBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull PotionBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new PotionBuilder(itemStack, castMeta(itemStack.getItemMeta(), PotionMeta.class));
    }

    /**
     * Creates a {@link PotionBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link PotionBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull PotionBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return PotionBuilder.of(getItem(material));
    }

    /**
     * Gets the custom effects.
     *
     * @return the custom effects
     */
    public @NonNull List<@NonNull PotionEffect> customEffects() {
        return this.itemMeta.getCustomEffects();
    }

    /**
     * Sets the custom effects. Pass {@code null} to reset.
     *
     * @param customEffects custom effects
     * @return the builder
     */
    public @NonNull PotionBuilder customEffects(final @Nullable List<@NonNull PotionEffect> customEffects) {
        this.itemMeta.clearCustomEffects();
        if (customEffects != null) {
            for (final @NonNull PotionEffect item : customEffects) {
                this.itemMeta.addCustomEffect(item, true);
            }
        }
        return this;
    }

    /**
     * Adds a custom effect.
     *
     * @param customEffect the custom effect to add
     * @param overwrite    whether to overwrite {@link PotionEffect}s of the same type
     * @return the builder
     */
    public @NonNull PotionBuilder addCustomEffect(final @NonNull PotionEffect customEffect, final boolean overwrite) {
        this.itemMeta.addCustomEffect(customEffect, overwrite);
        return this;
    }

    /**
     * Removes a custom effect type.
     *
     * @param customEffectType the custom effect type to remove
     * @return the builder
     */
    public @NonNull PotionBuilder removeCustomEffect(final @NonNull PotionEffectType... customEffectType) {
        for (final PotionEffectType item : customEffectType) {
            this.itemMeta.removeCustomEffect(item);
        }
        return this;
    }

    /**
     * Gets whether a custom effect type is present.
     *
     * @param customEffectType the custom effect type
     * @return the builder
     */
    public boolean hasCustomEffect(final @NonNull PotionEffectType customEffectType) {
        return this.itemMeta.hasCustomEffect(customEffectType);
    }

    /**
     * Gets the {@code Color}.
     *
     * @return the {@code Color}
     */
    public @Nullable Color color() {
        return this.itemMeta.getColor();
    }

    /**
     * Sets the {@code Color}. Pass {@code null} to reset.
     *
     * @param color the {@code Color}
     * @return the builder
     */
    public @NonNull PotionBuilder color(final @Nullable Color color) {
        this.itemMeta.setColor(color);
        return this;
    }

    /**
     * Gets the base {@link PotionData}.
     *
     * @return the base {@link PotionData}
     */
    public @NonNull PotionData basePotionData() {
        return this.itemMeta.getBasePotionData();
    }

    /**
     * Sets the base {@link PotionData}.
     *
     * @param basePotionData the base {@link PotionData}
     * @return the builder
     */
    public @NonNull PotionBuilder basePotionData(final @NonNull PotionData basePotionData) {
        this.itemMeta.setBasePotionData(basePotionData);
        return this;
    }

}
