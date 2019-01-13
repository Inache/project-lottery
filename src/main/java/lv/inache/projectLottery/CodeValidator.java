package lv.inache.projectLottery;

public class CodeValidator {
//    Код должен состоять из 16 цифр. Любые лругие символы не подходят
//    Первые 6 цифр - дата стратра лотереи по формату DDMMYY. Т.е. если лотерея стартовала 27.12.2018 - первые 6 цифр должны быть 271218
//            7 и 8 цифры - кол-во символов в емейле регистрирующегося. Т.е. если емейл as@df.gh, то должно быть 08. Если емейл test@mail.com - 13.
//    Остальные 8 цифр, случайные
//    Каждый код должен быть уникальным
    public boolean checkLength(Integer length){
        if (length == 8)
            return true;
        else return false;
    }
}
