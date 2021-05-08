package br.com.drogaria.util;

// importes que necessita
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
	
		//fabrica de sessão, está estática
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Cria um SessionFactory a partir do hibernate.cfg.xml
        	Configuration  configuration = new Configuration();
        	configuration.configure(); //busca configuração do hibernate
        	
        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        			.applySettings(configuration.getProperties()).build(); //carrega as configurações que ja foram feitas

        	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        	System.out.println("SESSÃO INICIADA!");
           
        	return sessionFactory;
       
        } catch (Throwable ex) {
            // exibe mensagem de erro
            System.err.println("FALHA AO CRIAR SESSÃO." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // metodo publico estático, para capturar a sessão criada
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    

}


