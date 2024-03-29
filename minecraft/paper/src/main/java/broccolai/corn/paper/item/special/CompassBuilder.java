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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link CompassMeta}.
 */
@SuppressWarnings("unused")
public final class CompassBuilder extends AbstractPaperItemBuilder<CompassBuilder, CompassMeta> {

    private CompassBuilder(final @NonNull ItemStack itemStack, final @NonNull CompassMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@link CompassBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link CompassBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull CompassBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new CompassBuilder(itemStack, castMeta(itemStack.getItemMeta(), CompassMeta.class));
    }

    /**
     * Creates a {@link CompassBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link CompassBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull CompassBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return CompassBuilder.of(getItem(material));
    }

    /**
     * Creates a {@link CompassBuilder} of type {@link Material#COMPASS}. A convenience method.
     *
     * @return instance of {@link CompassBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull CompassBuilder ofCompass() throws IllegalArgumentException {
        return ofType(Material.COMPASS);
    }

    /**
     * Gets the {@link Location} of the lodestone that this compass is tracking.
     *
     * @return the the lodestone
     */
    public @Nullable Location lodestone() {
        return this.itemMeta.getLodestone();
    }

    /**
     * Sets the {@link Location} of the lodestone that this compass is tracking. Pass {@code null} to reset.
     *
     * @param lodestone the the lodestone
     * @return the builder
     */
    public @NonNull CompassBuilder lodestone(final @Nullable Location lodestone) {
        this.itemMeta.setLodestone(lodestone);
        return this;
    }

    /**
     * Gets whether the compass is tracking the lodestone.
     *
     * @return whether the compass is tracking the lodestone
     */
    public boolean lodestoneTracked() {
        return this.itemMeta.isLodestoneTracked();
    }

    /**
     * Sets whether the compass is tracking the lodestone.
     *
     * @param lodestoneTracked whether the compass is tracking the lodestone
     * @return the builder
     */
    public @NonNull CompassBuilder lodestoneTracked(final boolean lodestoneTracked) {
        this.itemMeta.setLodestoneTracked(lodestoneTracked);
        return this;
    }

}
