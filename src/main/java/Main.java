import entidades.Correo;
import entidades.Direccion;
import entidades.Modulo;
import entidades.Profesor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Arrays;

public class Main {

    /**
     * Se crean objetos de los modelos, se insertan en la base de datos, y posteriormente
     * se recuperan sus atributos para validar las relaciones entre entidades.
     * @param args
     */
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tax = session.getTransaction();

        Direccion direccion1 = new Direccion("P Castellana","Madrid","Madrid",23);
        Direccion direccion2 = new Direccion("C/Magnolias","Madrid","Madrid",96);

        Profesor profesor1 = new Profesor("Juan","Gonzalez","Izquierdo",direccion1);
        Profesor profesor2 = new Profesor("Margarita","Robles","Martinez",direccion2);

        Correo correo1 = new Correo("profesor1@gmail.com","GMAIL");
        Correo correo2 = new Correo("aprofesor1_empresa@outlook.com","OUTLOOK");

        Modulo modulo1 = new Modulo("Sistemas Informaticos",1,6);
        Modulo modulo2 = new Modulo("Programacion II",2,12);

        try{
            tax.begin();

            profesor1.getModulos().add(modulo1);//Asignamos modulos al profesor 1
            profesor1.getModulos().add(modulo2);

            profesor2.getModulos().add(modulo2);

            profesor1.setCorreos(Arrays.asList(correo1,correo2)); //Ambos correos son del profesor 1

            session.save(modulo1);
            session.save(modulo2);
            session.save(profesor1);  //Guadado de profesor y todos sus atributos
            session.save(profesor2);
            tax.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(tax.isActive())
                tax.rollback();
        }finally {
            session.close();
        }

        Session otraSession = HibernateUtil.getSessionFactory().openSession();

        try{

            Profesor profesorInsertado = otraSession.get(Profesor.class,1);
            Modulo moduloInsertado = otraSession.get(Modulo.class,2);
            Correo correoInsertado = otraSession.get(Correo.class,1);

            System.out.println("ENTIDADES RECUPERADAS");
            System.out.println(profesorInsertado);
            System.out.println(moduloInsertado);
            System.out.println(correoInsertado);

           /* System.out.println("DAtos profesor----------");
            System.out.println(profesorInsertado.getCorreos());
            System.out.println(profesorInsertado.getModulos());
            System.out.println(profesorInsertado.getDireccion());*/

            System.out.println("\nDireccion del profesor: ************");
            System.out.println(profesorInsertado.getDireccion() +"\n");
            System.out.println("Profesores por modulo: **************");
            System.out.println(moduloInsertado.getProfesores() +"\n");
            System.out.println("Modulos por profesor: *************");
            System.out.println(profesorInsertado.getModulos() +"\n");
            System.out.println("Correos del profesor: **************");
            System.out.println(profesorInsertado.getCorreos() +"\n");
            System.out.println("Profesor del correo ******************");
            System.out.println(correoInsertado.getProfesor() +"\n");

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
