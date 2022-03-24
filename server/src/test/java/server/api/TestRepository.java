package server.api;
import commons.Reachable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestRepository<T extends Reachable>{
    protected Map<Long,T> repo;
    protected List<String> calledMethods;
    public TestRepository() {
        this.repo = new HashMap<>();
        this.calledMethods = new ArrayList<>();
    }

    public void register(String callName){
        calledMethods.add(callName);
    }

    public List<T> findAll() {
        register("findAll");
        return repo.values().stream().collect(Collectors.toList());
    }

    public List<T> findAll(Sort sort) {
        register("findAllWithSortOrder");
        return null;
    }

    public Page<T> findAll(Pageable pageable) {
        register("findAllWithPageAble");
        return null;
    }

    public List<T> findAllById(Iterable<Long> longs) {
        register("findById");
        List<T> ans = new ArrayList<>();
        longs.forEach(id ->{
            if(repo.containsKey(id))ans.add(repo.get(id));
        });
        return ans;
    }

    public long count() {
        register("count");
        return findAll().size();
    }

    public void deleteById(Long aLong) {
        register("deleteById");
        repo.remove(aLong);
    }


    public void delete(T entity) {
        register("delete");
        Set<Long>keys = Set.copyOf(repo.keySet());
        for(Long key: keys){
            if(repo.get(key).equals(entity))repo.remove(key);
        }
    }

    public void deleteAllById(Iterable<? extends Long> longs) {
        register("deleteAllById");
        for(Long id: longs){
            repo.remove(id);
        }
    }

    public void deleteAll(Iterable<? extends T> entities) {
        register("deleteAll");
        for(T entity: entities)delete(entity);
    }

    public void deleteAll() {
        register("deleteAll");
        repo.clear();
    }

    public <S extends T> S save(S entity) {
        register("save");
        long id = entity.getId();
        repo.put(id,entity);
        return entity;
    }

    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        register("saveAll");
        List<S> ans = new ArrayList<>();
        for(S entity: entities)ans.add(save(entity));
        return ans;
    }

    public Optional<T> findById(Long aLong) {
        register("findById");
        Optional<T>ans = Optional.empty();
        if(repo.get(aLong)!=null)ans = Optional.of(repo.get(aLong));
        return ans;
    }

    public boolean existsById(Long aLong) {
        register("existsById");
        return repo.containsKey(aLong);
    }

    public void flush() {
        register("flush");
    }

    public <S extends T> S saveAndFlush(S entity) {
        register("saveAndFlush");
        return save(entity);
    }

    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        register("saveAllAndFlush");
        return saveAll(entities);
    }

    public void deleteAllInBatch(Iterable<T> entities) {
        register("deleteAllInBatch");
        deleteAll(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        register("deleteAllByIdInBatch");
        deleteAllById(longs);
    }

    public void deleteAllInBatch() {
        register("deleteAllInBatch");
        deleteAll();
    }

    public T getOne(Long aLong) {
        register("getOne");
        return repo.get(aLong);
    }

    public T getById(Long aLong) {
        register("getById");
        return repo.get(aLong);
    }

    public <S extends T> Optional<S> findOne(Example<S> example) {
        register("FindOne");
        for(T entity: repo.values()){
            if(entity.equals(example))return Optional.of((S)entity);
        }
        return Optional.empty();
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        register("FindAll");
        List<S>ans = new ArrayList<>();
        for(T entity: repo.values()){
            if(entity.equals(example))ans.add((S)entity);
        }
        return ans;
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        register("findAllForSort");
        return null;
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        register("FindAllWithPageable");
        return null;
    }

    public <S extends T> long count(Example<S> example) {
        register("countWithExample");
        return findAll(example).size();
    }

    public <S extends T> boolean exists(Example<S> example) {
        register("existsWithExample");
        return findOne(example).isPresent();
    }

    public <S extends T, R> R findBy(Example<S> example,
                                     Function<FluentQuery.FetchableFluentQuery<S>, R>
                                             queryFunction) {
        return null;
    }

}
