package broccolai.corn.examination;

import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

final class DirtyExaminableMapImpl<K, V extends Examinable> implements DirtyExaminableMap<K, V> {

    private final Map<K, V> base;
    private final Map<K, List<ExaminableProperty>> previousProperties = new HashMap<>();
    private final Set<K> knownDirty = new HashSet<>();

    DirtyExaminableMapImpl(final @NonNull Map<K, V> base) {
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
        V value = this.base.get(key);

        if (this.knownDirty.contains(key)) {
            return true;
        }

        List<ExaminableProperty> currentProperties = value.examinableProperties().collect(Collectors.toList());

        if (previousProperties.get(key).containsAll(currentProperties)) {
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
        this.base.forEach((key, value) -> {
            this.previousProperties.put(key, value.examinableProperties().collect(Collectors.toUnmodifiableList()));
        });
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
        this.previousProperties.put(key, value.examinableProperties().collect(Collectors.toUnmodifiableList()));
        return this.base.put(key, value);
    }

    @Override
    public V remove(final Object key) {
        return this.base.remove(key);
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> m) {
        this.base.putAll(m);
    }

    @Override
    public void clear() {
        this.base.clear();
        this.previousProperties.clear();
        this.knownDirty.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.base.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.base.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.base.entrySet();
    }

}
