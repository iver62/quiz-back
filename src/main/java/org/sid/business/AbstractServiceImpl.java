package org.sid.business;

import org.sid.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public abstract class AbstractServiceImpl<T extends Serializable> implements Operations<T> {

    @Override
    public T getOne(final Long id) {
        return getRepository().findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Page<T> getAll(final Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public T create(final T t) {
        return null;
    }

    @Override
    public T update(final Long id, final T t) {
        return null;
    }

    @Override
    public void delete(final Long id) {
        getRepository().deleteById(id);
    }

    protected abstract JpaRepository<T, Long> getRepository();
}
