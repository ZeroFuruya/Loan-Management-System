package e2p.icotp.service;

import java.io.File;
import java.util.ArrayList;

import e2p.icotp.App;
import e2p.icotp.layout.accounts.Login;
import e2p.icotp.layout.accounts.LoginWrapper;
import e2p.icotp.layout.accounts.Account;
import e2p.icotp.layout.accounts.SignUpWrapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XMLService {
    public static ArrayList<Account> unwrap_signUpXML(App app, File xml) { // -- converts xml File into java readable
                                                                           // File --
        ArrayList<Account> signUpList = new ArrayList<>();

        try {
            JAXBContext context = JAXBContext.newInstance(SignUpWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            SignUpWrapper unWrapper = (SignUpWrapper) um.unmarshal(xml);
            signUpList.clear();

            signUpList.addAll(unWrapper.getSignUpList());
            RegistryService.setXML_toRegistry(app, xml);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return signUpList;
    }

    public static void wrap_signUpXML(App app, File xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(SignUpWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            SignUpWrapper wrapper = new SignUpWrapper();
            wrapper.setAccountList(app.accountsMasterlist());

            m.marshal(wrapper, xml);
            RegistryService.setXML_toRegistry(app, xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // --------------------------------------------------------------------------------

    public static ArrayList<Login> unwrap_loginXML(App app, File xml) {
        ArrayList<Login> loginList = new ArrayList<>();

        try {
            JAXBContext context = JAXBContext.newInstance(LoginWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            LoginWrapper unWrapper = (LoginWrapper) um.unmarshal(xml);
            loginList.clear();
            loginList.addAll(unWrapper.getLoginList());
            RegistryService.setXML_toRegistry(app, xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return loginList;
    }

    public static void wrap_loginXML(App app, File xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(LoginWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            LoginWrapper wrapper = new LoginWrapper();
            wrapper.setLoginList(app.getLoginList());

            m.marshal(wrapper, xml);
            RegistryService.setXML_toRegistry(app, xml);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
