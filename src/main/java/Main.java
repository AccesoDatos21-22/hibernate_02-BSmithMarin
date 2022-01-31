import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class Main {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tax = session.beginTransaction();

        try{

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            if(tax.isActive())
                tax.rollback();
        }

    }
}
