import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import metier.BanqueRemote;
import metier.entities.Compte;

import java.util.ArrayList;
import java.util.List;

public class ClientRemote {

    public static void main(String[] args) {
        try {
            Context ctx = new InitialContext();

            String appName = "";
            String moduleName = "ejb-project";
            String beanName = "BK";
            String remoteInterface = "metier.BanqueRemote";

            String name = "ejb:" + appName + "/"
                    + moduleName + "/"
                    + beanName + "!"
                    + remoteInterface;


            BanqueRemote proxy = (BanqueRemote) ctx.lookup(name);

//            proxy.addCompte(new Compte());
//            proxy.addCompte(new Compte());
//            proxy.addCompte(new Compte());

            Compte compte = proxy.getCompte(1L);

            System.out.println(compte.getSolde());

            proxy.verser(1L,5000);
            proxy.retirer(1L,1500);
            proxy.virement(1L,2L,3000);

            List<Compte> liste = proxy.listComptes();

            for (Compte c : liste) {
                System.out.println(c.getId()+ " : " +c.getSolde());
            }

            ctx.close();

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}