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

    /**
     * create a new test repository
     */
    public TestRepository() {
        this.repo = new HashMap<>();
        this.calledMethods = new ArrayList<>();
    }

    /**
     * track what method is called and save it in calledMethods
     * @param callName the method that is called
     */
    public void register(String callName){
        calledMethods.add(callName);
    }

    /**
     * get everything in the current repository
     * @return a list with items in the repository
     */
    public List<T> findAll() {
        register("findAll");
        return repo.values().stream().collect(Collectors.toList());
    }

    /**
     * get everything in the current repository with a sorted list,
     * I leave it and let it return null because till now we retrieve all
     * items first and sort later by java function, so we dont need this method
     * @param sort sort criteria
     * @return a sort list contains all items in the repo
     */
    public List<T> findAll(Sort sort) {
        register("findAllWithSortOrder");
        return null;
    }

    /**
     * arrange everything in the repository as a pageble list
     * @param pageable a pageable object
     * @return null because this is only relevant to database not the hashmap
     */
    public Page<T> findAll(Pageable pageable) {
        register("findAllWithPageAble");
        return null;
    }

    /**
     * find the all values that matches a list of ids
     * @param longs the list of ids
     * @return a list that contains values that matches the id list
     */
    public List<T> findAllById(Iterable<Long> longs) {
        register("findById");
        List<T> ans = new ArrayList<>();
        longs.forEach(id ->{
            if(repo.containsKey(id))ans.add(repo.get(id));
        });
        return ans;
    }

    /**
     * find the size of the repository
     * @return the size of the repository
     */
    public long count() {
        register("count");
        return findAll().size();
    }

    /**
     * delete a row that belongs to a certain id
     * @param aLong the id that needs to be deleted
     */
    public void deleteById(Long aLong) {
        register("deleteById");
        repo.remove(aLong);
    }

    /**
     * delete a row
     * @param entity the entity that needs to be deleted
     */
    public void delete(T entity) {
        register("delete");
        Set<Long>keys = Set.copyOf(repo.keySet());
        for(Long key: keys){
            if(repo.get(key).equals(entity))repo.remove(key);
        }
    }

    /**
     * delete all rows that matches a list of ids
     * @param longs the list of ids
     */
    public void deleteAllById(Iterable<? extends Long> longs) {
        register("deleteAllById");
        for(Long id: longs){
            repo.remove(id);
        }
    }

    /**
     * delete several rows form the entity
     * @param entities the entities that needs to be deleted
     */
    public void deleteAll(Iterable<? extends T> entities) {
        register("deleteAll");
        for(T entity: entities)delete(entity);
    }

    /**
     * delete everything in the current repository
     */
    public void deleteAll() {
        register("deleteAll");
        repo.clear();
    }

    /**
     * save an entity to the repository
     * @param entity the entity that needs to be saved
     * @param <S> the type of the entity
     * @return the saved entity
     */
    public <S extends T> S save(S entity) {
        register("save");
        long id = entity.getId();
        repo.put(id,entity);
        return entity;
    }

    /**
     * save a list of entities to the repository
     * @param entities the list of the entities
     * @param <S> the type of the entities
     * @return a list of the saved entity
     */
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        register("saveAll");
        List<S> ans = new ArrayList<>();
        for(S entity: entities)ans.add(save(entity));
        return ans;
    }

    /**
     * find an entity that matches a certain id
     * @param aLong the id as the search key
     * @return an optional value which contains an entity if there
     * is any match in the repository
     */
    public Optional<T> findById(Long aLong) {
        register("findById");
        Optional<T>ans = Optional.empty();
        if(repo.get(aLong)!=null)ans = Optional.of(repo.get(aLong));
        return ans;
    }

    /**
     * check if there is any match for an id
     * @param aLong the id that needs to be checked
     * @return ture iff items related to the id exist in the repository
     */
    public boolean existsById(Long aLong) {
        register("existsById");
        return repo.containsKey(aLong);
    }

    /**
     * refresh the repository
     * no implementation in this one because it is only related to the database
     */
    public void flush() {
        register("flush");
    }

    /**
     * save and refresh the repository
     * @param entity the entity that is needs to be saved
     * @param <S> the type of the entity
     * @return the saved entity
     */
    public <S extends T> S saveAndFlush(S entity) {
        register("saveAndFlush");
        return save(entity);
    }

    /**
     * save a list of entity and refresh the repository
     * @param entities the list of entities that needs to be saved
     * @param <S> the type of the entities
     * @return a list which contains the saved entities
     */
    public <S extends T> List<S> saveAllAndFlush(Iterable<S> entities) {
        register("saveAllAndFlush");
        return saveAll(entities);
    }

    /**
     * delete a list of entities from the repository
     * @param entities the list that needs ro be deleted
     */
    public void deleteAllInBatch(Iterable<T> entities) {
        register("deleteAllInBatch");
        deleteAll(entities);
    }

    /**
     * delete all entities that have a match in a certain list of ids
     * @param longs the given list of the ids
     */
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        register("deleteAllByIdInBatch");
        deleteAllById(longs);
    }

    /**
     * delete everything in the repository
     */
    public void deleteAllInBatch() {
        register("deleteAllInBatch");
        deleteAll();
    }

    /**
     * get an item that matched a given id
     * @param aLong the given id
     * @return an entity related to this id
     */
    public T getOne(Long aLong) {
        register("getOne");
        return repo.get(aLong);
    }

    /**
     * get an item that matched a given id
     * @param aLong the given id
     * @return an entity related to this id
     */
    public T getById(Long aLong) {
        register("getById");
        return repo.get(aLong);
    }

    /**
     * find one item that has the same value with a certain example
     * @param example the given example
     * @param <S> the type of the example
     * @return an optional instance which contains the matching entity
     * if there is a match
     */
    public <S extends T> Optional<S> findOne(Example<S> example) {
        register("FindOne");
        for(T entity: repo.values()){
            if(entity.equals(example))return Optional.of((S)entity);
        }
        return Optional.empty();
    }

    /**
     * find all items that have the same velue with a certain example
     * @param example the given example
     * @param <S> the type of the example
     * @return the list that contains all matching items
     */
    public <S extends T> List<S> findAll(Example<S> example) {
        register("FindAll");
        List<S>ans = new ArrayList<>();
        for(T entity: repo.values()){
            if(entity.equals(example))ans.add((S)entity);
        }
        return ans;
    }

    /**
     * find all values that match a pattern and return as an ordered list
     * no implementation because we sort in java not in the database
     * @param example the certain pattern
     * @param sort the sort criteria
     * @param <S> the type of the pattern
     * @return a list of the matching items
     */
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        register("findAllForSort");
        return null;
    }

    /**
     * find all values that match a pattern and return as a pageable list
     * no implementation because it is only related to database
     * @param example the certain pattern
     * @param pageable the pageable object
     * @param <S> the type of the pattern
     * @return a list of the matching items
     */
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        register("FindAllWithPageable");
        return null;
    }

    /**
     * count the number of items that have a certain value
     * @param example the certain value
     * @param <S> the type of the value
     * @return the number of the matching items
     */
    public <S extends T> long count(Example<S> example) {
        register("countWithExample");
        return findAll(example).size();
    }

    /**
     * check if there is a match with a search key
     * @param example the search key
     * @param <S> the type of the pattern
     * @return true off the input object exsits in the repository
     */
    public <S extends T> boolean exists(Example<S> example) {
        register("existsWithExample");
        return findOne(example).isPresent();
    }

    /**
     * no implementaion currently.
     */
    public <S extends T, R> R findBy(Example<S> example,
                                     Function<FluentQuery.FetchableFluentQuery<S>, R>
                                             queryFunction) {
        return null;
    }

}
