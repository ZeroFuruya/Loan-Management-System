package e2p.icotp.layout.accounts;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
public class SignUpWrapper {

    private List<Account> signUpList = new ArrayList<Account>();

    @XmlElement(name = "users")
    public List<Account> getSignUpList() {
        return signUpList;
    }

    public void setAccountList(List<Account> list) {
        signUpList = list;
    }

}
