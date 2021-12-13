package es.florida.AEV5_AD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Principal {

	/**
	 * Mètode main que mostra un menu i executa el mètode que tria l'usuari
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Configuration configuration = new Configuration().configure("llibre.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

		Scanner teclado = new Scanner(System.in);
		int opcio = 0;
		int id;

		while (opcio != 6) {
			// Obri una nova sessió de la session factory
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			System.out.println("\n\n=============================================");
			System.out.println("              MENÚ BIBLIOTECA");
			System.out.println("=============================================");
			System.out.println("1. Mostrar tots els títols de la biblioteca.");
			System.out.println("2. Mostrar la informació detallada d’un llibre a partir del seu id.");
			System.out.println("3. Afegir un nou llibre a la biblioteca.");
			System.out.println("4. Modificar atributs d’un llibre a partir del seu id.");
			System.out.println("5. Esborrar un llibre a partir del seu id.");
			System.out.println("6. Tancar la biblioteca");
			System.out.print("\n >>> Tria una opció: ");
			opcio = Integer.parseInt(teclado.next());
			switch (opcio) {
			case 1:
				mostrarTitols(session);
				break;
			case 2:
				mostrarTitols(session);
				System.out.print(" >> Indica el nombre del llibre que vols mostrar: ");
				id = Integer.parseInt(teclado.next());
				mostrarLlibre(session, id);
				break;
			case 3:
				crearLlibre(session);
				break;
			case 4:
				mostrarTitols(session);
				System.out.print(" >> Indica el nombre del llibre a modificar: ");
				id = Integer.parseInt(teclado.next());
				modificarLlibre(session, id);
				break;
			case 5:
				mostrarTitols(session);
				System.out.print(" >> Indica el nombre del llibre a esborrar: ");
				id = Integer.parseInt(teclado.next());
				eliminarLlibre(session, id);
				break;
			case 6:
				teclado.close();
				break;
			default:
				break;
			}
			session.getTransaction().commit();
			session.close();

		}

	}

	/**
	 * Mètode que mostra la id i el titol de tots els llibres de la biblioteca
	 * 
	 * @param session La sessió
	 * @author Claudiu Andrei Nechitescu
	 */
	public static void mostrarTitols(Session session) {
		List llibres = new ArrayList();
		llibres = session.createQuery("FROM Llibre").list();
		for (Object obj : llibres) {
			Llibre llibre = (Llibre) obj;
			System.out.println(llibre.getId() + "." + llibre.getTitol());
		}
	}

	/**
	 * Mètode que mostra l'informació d'un llibre donada la seua id
	 * 
	 * @param session La sessió
	 * @param id      La id del llibre
	 * @author Claudiu Andrei Nechitescu
	 */
	public static void mostrarLlibre(Session session, int id) {
		Llibre llibre = (Llibre) session.get(Llibre.class, id);

		Llibre llib = recuperarLlibre(session, id);
		System.out.println("\nDetalls del llibre: ");
		System.out.print("ID: ");
		System.out.println(llib.getId());
		System.out.print("Titol: ");
		System.out.println(llib.getTitol());
		System.out.print("Autor: ");
		System.out.println(llib.getAutor());
		System.out.print("Any Naixement: ");
		System.out.println(llib.getAnyNaixement());
		System.out.print("Any Publicacio: ");
		System.out.println(llib.getAnyPublicacio());
		System.out.print("Editorial: ");
		System.out.println(llib.getEditorial());
		System.out.print("Pàgines: ");
		System.out.println(llib.getNombrePagines());

	}

	/**
	 * Mètode que recupera un llibre de la BBDD donada la seua id
	 * 
	 * @param session La sessió
	 * @param id      La id del llibre
	 * @return El llibre corresponent a la id
	 * @author Claudiu Andrei Nechitescu
	 */
	public static Llibre recuperarLlibre(Session session, int id) {
		List llibres = new ArrayList();
		llibres = session.createQuery("FROM Llibre WHERE id= " + id).list();
		return (Llibre) llibres.get(0);
	}

	/**
	 * Mètode que crea un llibre en la BBDD
	 * 
	 * @param session La sessió
	 * @author Claudiu Andrei Nechitescu
	 */
	public static void crearLlibre(Session session) {
		String titol, autor, anyN, anyP, editorial, nompag;
		Scanner teclado = new Scanner(System.in);
		System.out.println("Dades del nou llibre: ");
		System.out.print("Titol: ");
		titol = teclado.next();
		System.out.print("Autor: ");
		autor = teclado.next();
		System.out.print("Any Naixement: ");
		anyN = teclado.next();
		System.out.print("Any Publicacio: ");
		anyP = teclado.next();
		System.out.print("Editorial: ");
		editorial = teclado.next();
		System.out.print("Pàgines: ");
		nompag = teclado.next();
		Llibre llib = new Llibre(titol, autor, anyN, anyP, editorial, nompag);
		Serializable id = session.save(llib);
		System.out.println("Llibre amb ID " + id + " creat");
	}

	/**
	 * Mètode que modifica l'informació d'un llibre donada la seua id
	 * 
	 * @param session La sessió
	 * @param id      La id del llibre
	 * @author Claudiu Andrei Nechitescu
	 */
	public static void modificarLlibre(Session session, int id) throws IOException {
		Llibre li = (Llibre) session.load(Llibre.class, id);

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int resposta;

		do {
			System.out.println(">> Què vols canviar?" + "\n1. Títol" + "\n2. Autor" + "\n3. Any de publicació"
					+ "\n4. Editorial" + "\n5. Nombre de pàgines" + "\n6. Res");

			resposta = Integer.parseInt(br.readLine());
			switch (resposta) {
			case 1: {
				System.out.println("Introdueix el nou titol: ");
				String nou = br.readLine();
				li.setTitol(nou);
				break;
			}
			case 2: {
				System.out.println("Introdueix el nou autor: ");
				String nou = br.readLine();
				li.setAutor(nou);
				break;

			}
			case 3: {
				System.out.println("Introdueix el nou any: ");
				String nou = br.readLine();
				li.setAnyPublicacio(nou);
				break;

			}
			case 4: {
				System.out.println("Introdueix la nou editorial: ");
				String nou = br.readLine();
				li.setEditorial(nou);
				break;
			}
			case 5: {
				System.out.println("Introdueix el nou nom de pàgines: ");
				String nou = br.readLine();
				li.setNombrePagines(nou);
				break;

			}
			case 6: {
				break;
			}
			default: {
				System.out.println("Has d'introduir un numero de 1 a 6");
			}
			}
			resposta=resposta!=6?0:6;
		}while ((resposta > 6 || resposta < 1)&&resposta!=6);

		session.update(li);

	}

	/**
	 * Mètode que esborra un llibre donada la seua id
	 * 
	 * @param session La sessió
	 * @param id      La id del llibre
	 * @author Claudiu Andrei Nechitescu
	 */
	public static void eliminarLlibre(Session session, int id) {
		Llibre llibre = new Llibre();
		llibre.setId(id);
		session.delete(llibre);
	}
}
