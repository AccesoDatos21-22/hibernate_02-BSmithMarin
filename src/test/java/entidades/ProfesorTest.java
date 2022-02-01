package entidades;

import org.hibernate.Session;
import org.junit.jupiter.api.*;
import util.HibernateUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProfesorTest {

    static Session session;
    static Profesor profesor;
    static Correo correo1;
    static Correo correo2;
    static Direccion direccion;
    static Modulo modulo1;
    static Modulo modulo2;

    @BeforeAll
    static void iniciar(){
        assertDoesNotThrow(()->{
            //Iniciamos la sesion
            session = HibernateUtil.getSessionFactory().openSession();
            //Datos a insertar
            direccion = new Direccion("C/Magnolias","Madrid","Madrid",96);
            profesor = new Profesor("Margarita","Robles","Martinez",direccion);
            correo1 = new Correo("profesor1@gmail.com","GMAIL");
            correo2 = new Correo("aprofesor1_empresa@outlook.com","OUTLOOK");
            modulo1 = new Modulo("Sistemas Informaticos",1,6);
            modulo2 = new Modulo("Programacion II",2,12);
        });
    }

    @Test
    @Order(2)
    void insertar(){
        assertDoesNotThrow(()->{
            session.getTransaction().begin();

            profesor.getModulos().add(modulo1); //Asignar modulos a profesor
            profesor.getModulos().add(modulo2);

            profesor.setCorreos( Arrays.asList(correo1,correo2) ); //Asignar correos a profesor

            session.save(modulo1);  //Se guardan las entidades
            session.save(modulo2);
            session.save(correo1);
            session.save(profesor);

            session.getTransaction().commit();
        });
    }

    @Test
    @Order(3)
    void comprobarRelacionDireccion(){
        assertDoesNotThrow(()->{

            Profesor profesorBuscado = session.get(Profesor.class,1);
            Direccion direccionBuscada = session.get(Direccion.class,1);

            assertNotNull(profesorBuscado);//Que no sean nulo
            assertNotNull(direccionBuscada);

            assertEquals(profesor,profesorBuscado); //que los datos coincidan con los insertados
            assertEquals(direccion,direccionBuscada);

            assertEquals(direccion,profesorBuscado.getDireccion()); //Se haya referenciado correctamente la direccion
        });
    }

    @Test
    @Order(4)
    void comprobarRelacionCorreos(){
        assertDoesNotThrow(()->{

            Profesor profesorInsertado = session.get(Profesor.class,1);
            Correo correoInsertado1 = session.get(Correo.class,1);
            Correo correoInsertado2 = session.get(Correo.class,2);

            assertNotNull(profesorInsertado);
            assertNotNull(correoInsertado1);
            assertNotNull(correoInsertado2);

            assertEquals(profesorInsertado,profesor);
            assertEquals(correoInsertado1,correo1);
            assertEquals(correoInsertado2,correo2);

            assertTrue(profesorInsertado.getCorreos().contains(correoInsertado1));
            assertTrue(profesorInsertado.getCorreos().contains(correoInsertado2));

            System.out.println(profesorInsertado.getCorreos());

            assertEquals(profesorInsertado,correoInsertado1.getProfesor());
            assertEquals(correoInsertado2.getProfesor(),profesorInsertado);
        });
    }

    @Test
    @Order(5)
    void comprobarRelacionModulos(){
        assertDoesNotThrow(()->{

        });
    }


    @AfterAll
    static void cerrarRecurosos(){
        if (session != null)
            session.close();
       // HibernateUtil.closeSessionFactory();
    }

}