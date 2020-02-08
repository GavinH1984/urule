package cn.gavinhuang.actions;

import cn.gavinhuang.model.Customer;
import com.bstek.urule.model.ExposeAction;

public class DemoActions1 {
    private String ruleName;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    @ExposeAction("发送短信")
    public String Action1(Object customerObj, String actionName) {
        Customer customer = (Customer) customerObj;

        System.out.println( "Result | " + actionName + "|" + customer.getName());
        return "Result | " + actionName + "|" + customer.getName();

    }
}
