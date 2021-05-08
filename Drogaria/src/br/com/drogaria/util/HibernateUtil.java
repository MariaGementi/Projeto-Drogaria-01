package br.com.drogaria.util;

// importes que necessita
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
	
		//fabrica de sess�o, est� est�tica
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Cria um SessionFactory a partir do hibernate.cfg.xml
        	Configuration  configuration = new Configuration();
        	configuration.configure(); //busca configura��o do hibernate
        	
        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        			.applySettings(configuration.getProperties()).build(); //carrega as configura��es que ja foram feitas

        	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        	System.out.println("SESS�O INICIADA!");
           
        	return sessionFactory;
       
        } catch (Throwable ex) {
            // exibe mensagem de erro
            System.err.println("FALHA AO CRIAR SESS�O." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // metodo publico est�tico, para capturar a sess�o criada
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    

}


