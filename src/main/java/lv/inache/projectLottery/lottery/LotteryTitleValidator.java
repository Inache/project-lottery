package lv.inache.projectLottery.lottery;

import java.util.List;

public class LotteryTitleValidator {
    public boolean checkIfTitleIsAlreadyUsed(LotteryDaoImplementation lottDao, Lottery lottery) {
        List<Lottery> lotteries = lottDao.getAll();
        for (int i = 0; i < lotteries.size(); i++) {
            Lottery l = lotteries.get(i);
            if (lottery.getTitle().equals(l.getTitle())) {
                return true;
            }
        }
        return false;
    }
}
