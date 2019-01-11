package lv.inache.projectLottery;


import lv.inache.projectLottery.lottery.Lottery;
import lv.inache.projectLottery.participant.Participant;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Participant.class)
                .addAnnotatedClass(Lottery.class)
                .configure()
                .buildSessionFactory();
    }
}
