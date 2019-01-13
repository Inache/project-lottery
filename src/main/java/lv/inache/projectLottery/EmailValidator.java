package lv.inache.projectLottery;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    public boolean checkEmail(String email){
        if (null!= email){
            String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
