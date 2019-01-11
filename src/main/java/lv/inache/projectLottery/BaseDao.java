package lv.inache.projectLottery;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    List<T> getAll(Class<T> clazz);

    Optional<T> getById(Long id, Class<T> clazz);

    Long insert(T lottery);

    void delete(Long id, Class<T> clazz);

    void update(T lottery);
}
