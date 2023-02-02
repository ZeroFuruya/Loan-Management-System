package e2p.icotp.layout.accounts;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "users")
public class LoginWrapper {
    
    private List<Login> loginList;

    @XmlElement (name = "users")
    public List<Login> getLoginList(){
        return loginList;
    }

    public void setLoginList(List<Login> list){
        loginList = list;
    }
}
