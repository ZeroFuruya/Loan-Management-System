package e2p.icotp.layout.accounts;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="users")
public class SignUpWrapper {

    private List<SignUp> signUpList = new ArrayList<SignUp>();

    @XmlElement(name = "users")
    public List<SignUp> getSignUpList(){
        return signUpList;
    }

    public void setAccountList(List<SignUp> list){
        signUpList = list;
    }

}
