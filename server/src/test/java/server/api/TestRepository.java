package server.api;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class TestRepository<T>{
    protected Map<Long,T> repo;
    protected List<String> calledMethods;
    public TestRepository() {
        this.repo = new HashMap<>();
        this.calledMethods = new ArrayList<>();
    }
    public List<T> findAll() {
        return null;
    }

    public List<T> findAll(Sort sort) {
        return null;
    }

    public Page<T> findAll(Pageable pageable) {
        return null;
    }

    public List<T> findAllById(Iterable<Long> longs) {
        return null;
    }

    public long count() {
        return 0;
    }

    public void deleteById(Long aLong) {

    }


    public void delete(T entity) {

    }

    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    public void deleteAll(Iterable<? extends T> entities) {

    }

    public void deleteAll() {

    }

    public <S extends T> S save(S entity) {
        return null;
    }

    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    public Optional<T> findById(Long aLong) {
        return Optional.empty();
    }

    public boolean existsById(Long aLong) {
        return false;
    }

    public void flush() {

    }

    public <S extends T> S saveAndFlush(S entity) {
        return null;
    }

    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    public void deleteAllInBatch(Iterable<T> entities) {

    }

    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    public void deleteAllInBatch() {

    }

    public T getOne(Long aLong) {
        return null;
    }

    public T getById(Long aLong) {
        return null;
    }

    public <S extends T> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return null;
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    public <S extends T> long count(Example<S> example) {
        return 0;
    }

    public <S extends T> boolean exists(Example<S> example) {
        return false;
    }

    public <S extends T, R> R findBy(Example<S> example,
                                     Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
