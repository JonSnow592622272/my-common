/*
经过验证，feign是支持集合校验的

package com.shotgun.mycommon.base.base.valid;

import com.shotgun.mycommon.base.base.entity.BombVo;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

*/
/**
 * 用于支持验证器验证的集合类（是Collection的实现类，当成ArrayList和Hashset类似使用）
 *
 * @author wulm
 **//*

public class ValidCollection<E> extends BombVo implements Collection<E> {

    @Valid
    @NotEmpty
    private Collection<E> collection;

    public ValidCollection() {
        this.collection = new ArrayList<>();
    }

    public ValidCollection(Collection<E> collection) {
        this.collection = collection;
    }

    */
/**
     * 生成一个默认的集合(ArrayList)
     *
     * @author wulm
     **//*

    public static ValidCollection of() {
        return new ValidCollection();
    }

    public static <Z> ValidCollection<Z> of(Collection<Z> c) {
        return new ValidCollection(c);
    }

    public Collection<E> getCollection() {
        return collection;
    }

    public void setCollection(Collection<E> collection) {
        this.collection = collection;
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return collection.iterator();
    }

    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return collection.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return collection.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return collection.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return collection.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return collection.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return collection.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return collection.retainAll(c);
    }

    @Override
    public void clear() {
        collection.clear();
    }
}
*/
