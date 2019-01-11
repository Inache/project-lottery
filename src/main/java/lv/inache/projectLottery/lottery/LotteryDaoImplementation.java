package lv.inache.projectLottery.lottery;

import lv.inache.projectLottery.BaseDaoImplementation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LotteryDaoImplementation extends BaseDaoImplementation<Lottery> {
    @Autowired
    protected LotteryDaoImplementation(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Lottery> getAll() {
        return super.getAll(Lottery.class);
    }

    public Optional<Lottery> getById(Long id) {
        return super.getById(id, Lottery.class);
    }

    public void delete(Long id) {
        super.delete(id, Lottery.class);
    }

}
