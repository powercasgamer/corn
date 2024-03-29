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
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@link ItemMeta} of {@link BlockStateMeta}.
 */
@SuppressWarnings("unused")
public final class BlockStateBuilder extends AbstractPaperItemBuilder<BlockStateBuilder, BlockStateMeta> {

    private BlockStateBuilder(final @NonNull ItemStack itemStack, final @NonNull BlockStateMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@link BlockStateBuilder}.
     *
     * @param itemStack the {@link ItemStack} to base the builder off of
     * @return instance of {@link BlockStateBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BlockStateBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new BlockStateBuilder(itemStack, castMeta(itemStack.getItemMeta(), BlockStateMeta.class));
    }

    /**
     * Creates a {@link BlockStateBuilder}.
     *
     * @param material the {@link Material} to base the builder off of
     * @return instance of {@link BlockStateBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@link ItemMeta} is not the correct type
     */
    public static @NonNull BlockStateBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return BlockStateBuilder.of(getItem(material));
    }

    /**
     * Gets a copy of {@link BlockState}. Creates a new one if it doesn't currently exist.
     *
     * @return the {@link BlockState}
     */
    public @NonNull BlockState blockState() {
        return this.itemMeta.getBlockState();
    }

    /**
     * Sets the {@link BlockState}.
     *
     * @param blockState the {@link BlockState}
     * @return the builder
     */
    public @NonNull BlockStateBuilder blockState(final @NonNull BlockState blockState) {
        this.itemMeta.setBlockState(blockState);
        return this;
    }

    /**
     * Gets whether a {@link BlockState} is currently attached.
     *
     * @return whether a {@link BlockState} is currently attached
     */
    public boolean hasBlockState() {
        return this.itemMeta.hasBlockState();
    }

}
