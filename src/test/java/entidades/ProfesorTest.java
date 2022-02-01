package entidades;


import org.hibernate.Session;
import org.junit.jupiter.api.*;
import util.HibernateUtil;

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

            profesor.getCorreos().add(correo1); //Asignar correos a profesor
            profesor.getCorreos().add(correo2);

            session.save(modulo1);  //Se guardan las entidades
            session.save(modulo2);
            session.save(correo1);
            session.save(correo2);

            session.persist(profesor);

            session.getTransaction().commit();

        });
    }

    @Test
    @Order(3)
    void comprobarRelacionDireccion(){
        assertDoesNotThrow(()->{
            Session otraSesion = HibernateUtil.getSessionFactory().openSession();

            Profesor profesorBuscado = otraSesion.get(Profesor.class,1);
            Direccion direccionBuscada = otraSesion.get(Direccion.class,1);

            assertNotNull(profesorBuscado);//Que no sean nulo
            assertNotNull(direccionBuscada);

            assertEquals(profesor,profesorBuscado); //que los datos coincidan con los insertados
            assertEquals(direccion,direccionBuscada);

            assertEquals(direccion,profesorBuscado.getDireccion()); //Se haya referenciado correctamente la direccion

            otraSesion.close();
        });
    }

    @Test
    @Order(4)
    void comprobarRelacionCorreos(){
        assertDoesNotThrow(()->{

            Correo correoInsertado1 = session.get(Correo.class,1);
            Correo correoInsertado2 = session.get(Correo.class,2);
            Profesor profesorInsertado = session.get(Profesor.class,1);

            session.refresh(profesorInsertado); //Recarga la informacion de la BD

            assertNotNull(profesorInsertado);
            assertNotNull(correoInsertado1);    //Que no sean nulos
            assertNotNull(correoInsertado2);
            //Recuperar correos a partir de profesor
            assertTrue(profesorInsertado.getCorreos().contains(correoInsertado1));
            assertTrue(profesorInsertado.getCorreos().contains(correoInsertado2));
            //Que sean iguales a los introducidos
            assertEquals( profesorInsertado,profesor);
            assertEquals(correoInsertado1,correo1);
            assertEquals(correoInsertado2,correo2);
            //Que el profesor de cada correo sea el especificado
            assertEquals(correoInsertado1.getProfesor(),profesorInsertado);
            assertEquals(correoInsertado2.getProfesor(),profesorInsertado);
        });
    }

    @Test
    @Order(5)
    void comprobarRelacionModulos(){
        assertDoesNotThrow(()->{
            Profesor profesorInsertado = session.get(Profesor.class,1);
            Modulo moduloInsertado1 = session.get(Modulo.class,1);
            Modulo moduloInsertado2 = session.get(Modulo.class,2);
            //Que no sean nulos
            assertNotNull(profesorInsertado);
            assertNotNull(moduloInsertado1);
            assertNotNull(moduloInsertado2);
            //Que sean iguales a los introducidos
            assertEquals(profesorInsertado,profesor);
            assertEquals(moduloInsertado1,modulo1);
            assertEquals(moduloInsertado2,modulo2);
            //Que se puedan recuperar los modulos desde el profesor
            assertTrue(profesorInsertado.getModulos().contains(moduloInsertado1));
            assertTrue(profesorInsertado.getModulos().contains(moduloInsertado2));
            //Que se puedan recuperar los profesores a partir de los modulos
            assertTrue(moduloInsertado1.getProfesores().contains(profesorInsertado));
            assertTrue(moduloInsertado2.getProfesores().contains(profesorInsertado));
        });
    }


    @AfterAll
    static void cerrarRecurosos(){
        if (session != null)
            session.close();
        HibernateUtil.closeSessionFactory();
    }

}