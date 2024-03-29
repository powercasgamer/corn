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
package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

final class DirtyPropertiesMapImpl<K, V extends PropertyHolder> implements DirtyPropertiesMap<K, V> {

    private final Map<K, V> base;
    private final Map<K, PropertySnapshot> previousProperties = new HashMap<>();
    private final Set<K> knownDirty = new HashSet<>();

    DirtyPropertiesMapImpl(final @NonNull Map<K, V> base) {
        this.base = base;
        this.fillPreviousProperties();
    }

    @Override
    public void clean() {
        this.knownDirty.clear();
        this.fillPreviousProperties();
    }

    @Override
    public @NonNull Collection<@NonNull V> dirty() {
        Collection<V> dirtyValues = new ArrayList<>();

        for (final K key : this.base.keySet()) {
            if (this.isDirty(key)) {
                dirtyValues.add(this.base.get(key));
            }
        }

        return dirtyValues;
    }

    @Override
    public boolean isDirty(@NonNull final K key) {
        if (this.knownDirty.contains(key)) {
            return true;
        }

        V propertyHolder = this.base.get(key);
        PropertySnapshot previousSnapshot = previousProperties.get(key);

        if (!previousSnapshot.equals(propertyHolder.properties())) {
            this.knownDirty.add(key);
            return true;
        }

        return false;
    }

    @Override
    public void setDirty(@NonNull final K key) {
        this.knownDirty.add(key);
    }

    private void fillPreviousProperties() {
        this.base.forEach((key, value) -> this.previousProperties.put(key, value.properties()));
    }

    @Override
    public int size() {
        return this.base.size();
    }

    @Override
    public boolean isEmpty() {
        return this.base.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return this.base.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return this.base.containsValue(value);
    }

    @Override
    public V get(final Object key) {
        return this.base.get(key);
    }

    @Override
    public V put(final K key, final V value) {
        this.previousProperties.put(key, value.properties());
        return this.base.put(key, value);
    }

    @Override
    public V remove(final Object key) {
        return this.base.remove(key);
    }

    @Override
    public void putAll(final @NonNull Map<? extends K, ? extends V> m) {
        this.base.putAll(m);
    }

    @Override
    public void clear() {
        this.base.clear();
        this.previousProperties.clear();
        this.knownDirty.clear();
    }

    @Override
    public @NonNull Set<K> keySet() {
        return this.base.keySet();
    }

    @Override
    public @NonNull Collection<V> values() {
        return this.base.values();
    }

    @Override
    public @NonNull Set<Entry<K, V>> entrySet() {
        return this.base.entrySet();
    }

}
